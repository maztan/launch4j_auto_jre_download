// GT_HelloWorldWin32.cpp
// compile with: /D_UNICODE /DUNICODE /DWIN32 /D_WINDOWS /c

#include <windows.h>
#include <stdlib.h>
#include <string.h>
#include <tchar.h>
#include <commctrl.h>
#include "../resource.h"
#include "../head.h"

#define PBS_MARQUEE 0x08

// The main window class name.

const char *szWindowClass;

// The string that appears in the application's title bar.
const char *szTitle;

HINSTANCE hInst = NULL;
int nCmdShow;
HWND progressBar;
HWND textLabel;
HWND hWnd;
const char *szWindowClass = _T("win32app");
const char *szTitle = _T("Glasscubes File Helper Installer");

// Forward declarations of functions included in this code module:
LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);
void progressBarUpdateCallback(double totoal, double downloaded,
		BOOL newDownload);
BOOL showMessage(int messageType, const char* messageText);
DWORD WINAPI workerThread(LPVOID lpParam);
LPSTR lpCmdLine;
BOOL stayAlive;
DWORD mainThreadId;

int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance,
		LPSTR lpCmdLine_, int nCmdShow_) {

	mainThreadId = GetCurrentThreadId();
	hInst = hInstance; // Store instance handle in our global variable
	nCmdShow = nCmdShow_;
	lpCmdLine = lpCmdLine_;

	// if we should restart on crash, we must also stay alive to check for crashes
	BOOL restartOnCrash = FALSE;

	stayAlive = restartOnCrash
			|| (loadBool(GUI_HEADER_STAYS_ALIVE)
					&& strstr(lpCmdLine, "--l4j-dont-wait") == NULL);

	WNDCLASSEX wcex;

	wcex.cbSize = sizeof(WNDCLASSEX);
	wcex.style = CS_HREDRAW | CS_VREDRAW;
	wcex.lpfnWndProc = WndProc;
	wcex.cbClsExtra = 0;
	wcex.cbWndExtra = 0;
	wcex.hInstance = hInst;
	wcex.hIcon = LoadIcon(hInst, IDI_APPLICATION);
	wcex.hCursor = LoadCursor(NULL, IDC_ARROW);
	wcex.hbrBackground = (HBRUSH) (COLOR_WINDOW + 1);
	wcex.lpszMenuName = NULL;
	wcex.lpszClassName = szWindowClass;
	wcex.hIconSm = LoadIcon(wcex.hInstance, IDI_APPLICATION);

	if (!RegisterClassEx(&wcex)) {
		MessageBox(NULL, _T("Call to RegisterClassEx failed!"),
				_T("Glasscubes File Helper Installer"), 0);

		return 1;
	}

	//compute window position (center on screen)
	int windowWidth = 500;
	int windowHeight = 100;
	int screenX = GetSystemMetrics(SM_CXSCREEN);
	int screenY = GetSystemMetrics(SM_CYSCREEN);

	int windowPosX = (screenX / 2) - (windowWidth / 2);
	int windowPosY = (screenY / 2) - (windowHeight / 2);

	// The parameters to CreateWindow explained:
	// szWindowClass: the name of the application
	// szTitle: the text that appears in the title bar
	// WS_OVERLAPPEDWINDOW: the type of window to create
	// CW_USEDEFAULT, CW_USEDEFAULT: initial position (x, y)
	// 500, 100: initial size (width, length)
	// NULL: the parent of this window
	// NULL: this application does not have a menu bar
	// hInstance: the first parameter from WinMain
	// NULL: not used in this application
	hWnd = CreateWindow(
			szWindowClass,
			szTitle,
			WS_OVERLAPPEDWINDOW&~WS_MAXIMIZEBOX,
			windowPosX, windowPosY,
			windowWidth, windowHeight,
			NULL,
			NULL,
			hInst,
			NULL
	);

	if (!hWnd) {
		MessageBox(NULL, _T("Call to CreateWindow failed!"),
				_T("Glasscubes File Helper Installer"), 0);

		return 1;
	}

	int cyVScroll = GetSystemMetrics(SM_CYVSCROLL);

	RECT client_rectangle;
	GetClientRect(hWnd, &client_rectangle);

	//create progress bar

	progressBar = CreateWindowEx(
	WS_EX_WINDOWEDGE | WS_EX_CLIENTEDGE, PROGRESS_CLASS, NULL,
	WS_CHILD | WS_VISIBLE | PBS_MARQUEE, client_rectangle.left,
			client_rectangle.bottom - cyVScroll, client_rectangle.right,
			cyVScroll, hWnd, NULL, hInst, NULL);

	//SetWindowLong(progressBar, GWL_STYLE,
	//GetWindowLong(progressBar, GWL_STYLE) | PBS_MARQUEE);
	LONG_PTR style = GetWindowLongPtr(progressBar, GWL_STYLE);
	SetWindowLongPtr(progressBar, GWL_STYLE, style | PBS_MARQUEE);

	//label will fill the whole window except the part where progress bar will reside
	textLabel =
	CreateWindow("STATIC", "Initializing...", WS_VISIBLE | WS_CHILD | SS_CENTER,
			client_rectangle.left,client_rectangle.top,client_rectangle.right,
			client_rectangle.bottom - cyVScroll, hWnd, NULL, hInst, NULL);

	//SendMessage(progressBar, PBM_SETRANGE, 0, (LPARAM) MAKELPARAM(0, 599));
	//SendMessage(progressBar, PBM_SETPOS, 0, 0);
	//SetTimer(hWnd, 0, 100, NULL);

	// The parameters to ShowWindow explained:
	// hWnd: the value returned from CreateWindow
	// nCmdShow: the fourth parameter from WinMain
	ShowWindow(hWnd, nCmdShow);
	//UpdateWindow(hWnd);
	setJreDownloadProgressCallback(progressBarUpdateCallback);
	setDisplayMessageCallback(showMessage);

	//MessageBox(hWnd, "execute()", "Debug", MB_OK);

	HANDLE workerThreadHandle = CreateThread( NULL, 0,
			workerThread, NULL, 0, NULL);
	if (workerThreadHandle == NULL) {
		ExitProcess(5);
	}

	// Wait until all threads have terminated.
	// WaitForMultipleObjects( 3,
	//   Array_Of_Thread_Handles, TRUE, INFINITE);
	//MessageBox(NULL, "GUI Exiting", "Debug", MB_OK);

	// Main message loop:
	MSG msg;
	while (GetMessage(&msg, NULL, 0, 0)) {
		TranslateMessage(&msg);
		DispatchMessage(&msg);
	}

	return (int) msg.wParam;
}

DWORD WINAPI workerThread(LPVOID lpParam) {

	int result = prepare(lpCmdLine);

	if (result == ERROR_ALREADY_EXISTS) {
		//HWND handle = getInstanceWindow();
		//ShowWindow(handle, SW_SHOW);
		//SetForegroundWindow(handle);
		closeLogFile();
		return 2;
	}

	if (result != TRUE) {
		signalError();
		return 1;
	}

	DWORD exitCode = 0;
	if (!execute(FALSE, &exitCode)) {
		signalError();
		return 1;
	}

	if(!stayAlive){
		PostThreadMessage(mainThreadId, WM_QUIT, 0, 0);
	}

	return exitCode;
}

//
//  FUNCTION: WndProc(HWND, UINT, WPARAM, LPARAM)
//
//  PURPOSE:  Processes messages for the main window.
//
//  WM_PAINT    - Paint the main window
//  WM_DESTROY  - post a quit message and return
//
//
LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam) {
	//PAINTSTRUCT ps;
	//HDC hdc;
	//TCHAR greeting[] = _T("Hello, World!");

	switch (message) {
	/*case WM_PAINT:
	 hdc = BeginPaint(hWnd, &ps);

	 // Here your application is laid out.
	 // For this introduction, we just print out "Hello, World!"
	 // in the top left corner.
	 TextOut(hdc, 5, 5, greeting, _tcslen(greeting));
	 // End application-specific layout section.

	 EndPaint(hWnd, &ps);
	 break;
	 / *case WM_TIMER:
	 pos = SendMessage(progressBar, PBM_GETPOS, 0, 0) + 1;
	 SendMessage(progressBar, PBM_SETPOS, pos, 0);
	 if (pos == 600) {
	 SendMessage(progressBar, PBM_SETPOS, 0, 0);
	 }
	 break;*/
	case WM_DESTROY:
		PostQuitMessage(0);
		break;
	default:
		return DefWindowProc(hWnd, message, wParam, lParam);
		break;
	}

	return 0;
}

void progressBarUpdateCallback(double total, double downloaded,
		BOOL newDownload) {
	if (newDownload) {
		SendMessage(progressBar, PBM_SETRANGE, 0, (LPARAM) MAKELPARAM(0, 100));
		SendMessage(progressBar, PBM_SETPOS, 0, 0);
	}

	SendMessage(progressBar, PBM_SETPOS, (int) (downloaded * 100 / total), 0);
}

BOOL showMessage(int messageType, const char* messageText) {
	switch (messageType) {
	case DISPLAY_MESSAGE_LABEL:
		SetWindowText(textLabel, TEXT(messageText));
		RedrawWindow(textLabel, NULL, NULL, RDW_ERASE);
		return TRUE;
	case DISPLAY_MESSAGE_DIALOG_YES_NO:
		if (MessageBox(hWnd,
				"Do you want this installer to try to download\n and install required Java Runtime Environment?",
				"", MB_YESNO | MB_ICONQUESTION) == IDYES) {
			return TRUE;
		} else {
			return FALSE;
		}
	}

	return FALSE;
}
