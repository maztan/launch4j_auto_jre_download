package net.sf.launch4j.form;

import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/*
 * Created by JFormDesigner on Sun Apr 05 00:30:43 CEST 2015
 */

/**
 * @author Ed Moss
 */
public class JreForm extends JPanel {
	public JreForm() {
		initComponents();
	}

	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY
		// //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Ed Moss
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		panel2 = new JPanel();
		jrePathLabel = new JLabel();
		jrePathField = new JTextField();
		bundledJre64BitCheck = new JCheckBox();
		bundledJreAsFallbackCheck = new JCheckBox();
		jreDownloadUrlLabel = new JLabel();
		jreDownloadUrlField = new JTextField();
		downloadAndInstallJreCheck = new JCheckBox();
		jreDownloadCookieLabel = new JLabel();
		jreDownloadCookieField = new JTextField();
		jreMinLabel = new JLabel();
		jreMaxLabel = new JLabel();
		jvmOptionsTextLabel = new JLabel();
		jreMinField = new JTextField();
		jreMaxField = new JTextField();
		scrollPane1 = new JScrollPane();
		jvmOptionsTextArea = new JTextArea();
		initialHeapSizeLabel = new JLabel();
		maxHeapSizeLabel = new JLabel();
		label1 = new JLabel();
		label2 = new JLabel();
		initialHeapSizeField = new JTextField();
		maxHeapSizeField = new JTextField();
		maxHeapPercentField = new JTextField();
		initialHeapPercentField = new JTextField();
		jdkPreferenceCombo = new JComboBox();
		label3 = new JLabel();
		label4 = new JLabel();
		runtimeBitsCombo = new JComboBox();
		panel1 = new JPanel();
		varCombo = new JComboBox();
		propertyButton = new JButton();
		optionButton = new JButton();
		envPropertyButton = new JButton();
		label5 = new JLabel();
		label6 = new JLabel();
		envOptionButton = new JButton();
		envVarField = new JTextField();
		separator1 = compFactory.createSeparator(Messages.getString("searchOptions"));
		separator2 = compFactory.createSeparator(Messages.getString("options"));

		// ======== this ========

		addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent e) {
				if ("border".equals(e.getPropertyName()))
					throw new RuntimeException();
			}
		});

		setLayout(new FormLayout("$ugap, right:[65dlu,default], 3dlu, 60dlu, 3dlu, default, 7dlu, 60dlu, 3dlu, default:grow, $ugap",
				"2*($pgap, default), 2*($lgap, default), $pgap, 3*(default, $lgap), fill:50dlu:grow, $lgap, default, $pgap, default"));

		// ======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
					"right:[65dlu,default], 3dlu, 60dlu:grow, 3dlu, default:grow(0.25), 7dlu, 60dlu:grow(0.25), 3dlu, default",
					"2*(default, $lgap), default"));

			// ---- jrePathLabel ----
			jrePathLabel.setText(Messages.getString("jrePath"));
			panel2.add(jrePathLabel, CC.xy(1, 1));

			// ---- jrePathField ----
			jrePathField.setToolTipText(Messages.getString("jrePathTip"));
			panel2.add(jrePathField, CC.xy(3, 1));

			// ---- bundledJre64BitCheck ----
			bundledJre64BitCheck.setActionCommand(Messages.getString("bundledJre64Bit"));
			bundledJre64BitCheck.setText(Messages.getString("bundledJre64Bit"));
			bundledJre64BitCheck.setToolTipText(Messages.getString("bundledJre64BitTip"));
			panel2.add(bundledJre64BitCheck, CC.xy(5, 1));

			// ---- bundledJreAsFallbackCheck ----
			bundledJreAsFallbackCheck.setActionCommand(Messages.getString("bundledJreAsFallback"));
			bundledJreAsFallbackCheck.setText(Messages.getString("bundledJreAsFallback"));
			bundledJreAsFallbackCheck.setToolTipText(Messages.getString("bundledJreAsFallbackTip"));
			panel2.add(bundledJreAsFallbackCheck, CC.xy(7, 1));

			// ---- jreDownloadUrlLabel ----
			jreDownloadUrlLabel.setText(Messages.getString("jreDonwloadUrl"));
			panel2.add(jreDownloadUrlLabel, CC.xy(1, 3));

			// ---- jreDownloadUrlField ----
			jreDownloadUrlField.setToolTipText(Messages.getString("jreDownloadUrlTip"));
			panel2.add(jreDownloadUrlField, CC.xy(3, 3));

			// ---- downloadAndInstallJreCheck ----
			downloadAndInstallJreCheck.setText(Messages.getString("downloadAndInstallJre"));
			downloadAndInstallJreCheck.setToolTipText(Messages.getString("downloadAndInstallJreTip"));
			panel2.add(downloadAndInstallJreCheck, CC.xy(5, 3));

			// ---- jreDownloadCookieLabel ----
			jreDownloadCookieLabel.setText(Messages.getString("jreDownloadCookie"));
			panel2.add(jreDownloadCookieLabel, CC.xy(1, 5));

			// ---- jreDownloadCookieField ----
			jreDownloadCookieField.setToolTipText(Messages.getString("jreDownloadCookieTip"));
			panel2.add(jreDownloadCookieField, CC.xy(3, 5));
		}
		add(panel2, CC.xywh(2, 2, 9, 1));

		// ---- jreMinLabel ----
		jreMinLabel.setText(Messages.getString("jreMin"));
		add(jreMinLabel, CC.xy(2, 6));

		// ---- jreMaxLabel ----
		jreMaxLabel.setText(Messages.getString("jreMax"));
		add(jreMaxLabel, CC.xy(2, 8));

		// ---- jvmOptionsTextLabel ----
		jvmOptionsTextLabel.setText(Messages.getString("jvmOptions"));
		add(jvmOptionsTextLabel, CC.xy(2, 16, CC.DEFAULT, CC.TOP));
		add(jreMinField, CC.xy(4, 6));
		add(jreMaxField, CC.xy(4, 8));

		// ======== scrollPane1 ========
		{

			// ---- jvmOptionsTextArea ----
			jvmOptionsTextArea.setToolTipText(Messages.getString("jvmOptionsTip"));
			scrollPane1.setViewportView(jvmOptionsTextArea);
		}
		add(scrollPane1, CC.xywh(4, 16, 7, 1));

		// ---- initialHeapSizeLabel ----
		initialHeapSizeLabel.setText(Messages.getString("initialHeapSize"));
		add(initialHeapSizeLabel, CC.xy(2, 12));

		// ---- maxHeapSizeLabel ----
		maxHeapSizeLabel.setText(Messages.getString("maxHeapSize"));
		add(maxHeapSizeLabel, CC.xy(2, 14));

		// ---- label1 ----
		label1.setText("MB");
		add(label1, CC.xy(6, 12));

		// ---- label2 ----
		label2.setText("MB");
		add(label2, CC.xy(6, 14));
		add(initialHeapSizeField, CC.xy(4, 12));
		add(maxHeapSizeField, CC.xy(4, 14));
		add(maxHeapPercentField, CC.xy(8, 14));
		add(initialHeapPercentField, CC.xy(8, 12));
		add(jdkPreferenceCombo, CC.xywh(8, 6, 3, 1));

		// ---- label3 ----
		label3.setText(Messages.getString("availableMemory"));
		add(label3, CC.xy(10, 12));

		// ---- label4 ----
		label4.setText(Messages.getString("availableMemory"));
		add(label4, CC.xy(10, 14));

		// ---- runtimeBitsCombo ----
		runtimeBitsCombo.setToolTipText("");
		add(runtimeBitsCombo, CC.xywh(8, 8, 3, 1));

		// ======== panel1 ========
		{
			panel1.setLayout(new FormLayout("right:[65dlu,default], $lcgap, default:grow, 2*($lcgap, default)", "default, $lgap, default"));
			panel1.add(varCombo, CC.xy(3, 1));

			// ---- propertyButton ----
			propertyButton.setActionCommand("Add");
			propertyButton.setIcon(loadImage("images/edit_add16.png"));
			propertyButton.setText(Messages.getString("property"));
			propertyButton.setToolTipText(Messages.getString("propertyTip"));
			panel1.add(propertyButton, CC.xy(5, 1));

			// ---- optionButton ----
			optionButton.setActionCommand("Add");
			optionButton.setIcon(loadImage("images/edit_add16.png"));
			optionButton.setText(Messages.getString("option"));
			optionButton.setToolTipText(Messages.getString("optionTip"));
			panel1.add(optionButton, CC.xy(7, 1));

			// ---- envPropertyButton ----
			envPropertyButton.setActionCommand("Add");
			envPropertyButton.setIcon(loadImage("images/edit_add16.png"));
			envPropertyButton.setText(Messages.getString("property"));
			envPropertyButton.setToolTipText(Messages.getString("propertyTip"));
			panel1.add(envPropertyButton, CC.xy(5, 3));

			// ---- label5 ----
			label5.setText(Messages.getString("varsAndRegistry"));
			panel1.add(label5, CC.xy(1, 1));

			// ---- label6 ----
			label6.setIcon(loadImage("images/asterix.gif"));
			label6.setText(Messages.getString("envVar"));
			panel1.add(label6, CC.xy(1, 3));

			// ---- envOptionButton ----
			envOptionButton.setActionCommand("Add");
			envOptionButton.setIcon(loadImage("images/edit_add16.png"));
			envOptionButton.setText(Messages.getString("option"));
			envOptionButton.setToolTipText(Messages.getString("optionTip"));
			panel1.add(envOptionButton, CC.xy(7, 3));
			panel1.add(envVarField, CC.xy(3, 3));
		}
		add(panel1, CC.xywh(2, 18, 9, 1));
		add(separator1, CC.xywh(2, 4, 9, 1));
		add(separator2, CC.xywh(2, 10, 9, 1));
		// JFormDesigner - End of component initialization
		// //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY
	protected JPanel panel2;
	protected JLabel jrePathLabel;
	protected JTextField jrePathField;
	protected JCheckBox bundledJre64BitCheck;
	protected JCheckBox bundledJreAsFallbackCheck;
	protected JLabel jreDownloadUrlLabel;
	protected JTextField jreDownloadUrlField;
	protected JCheckBox downloadAndInstallJreCheck;
	protected JLabel jreDownloadCookieLabel;
	protected JTextField jreDownloadCookieField;
	protected JLabel jreMinLabel;
	protected JLabel jreMaxLabel;
	protected JLabel jvmOptionsTextLabel;
	protected JTextField jreMinField;
	protected JTextField jreMaxField;
	protected JScrollPane scrollPane1;
	protected JTextArea jvmOptionsTextArea;
	protected JLabel initialHeapSizeLabel;
	protected JLabel maxHeapSizeLabel;
	protected JLabel label1;
	protected JLabel label2;
	protected JTextField initialHeapSizeField;
	protected JTextField maxHeapSizeField;
	protected JTextField maxHeapPercentField;
	protected JTextField initialHeapPercentField;
	protected JComboBox jdkPreferenceCombo;
	protected JLabel label3;
	protected JLabel label4;
	protected JComboBox runtimeBitsCombo;
	protected JPanel panel1;
	protected JComboBox varCombo;
	protected JButton propertyButton;
	protected JButton optionButton;
	protected JButton envPropertyButton;
	protected JLabel label5;
	protected JLabel label6;
	protected JButton envOptionButton;
	protected JTextField envVarField;
	protected JComponent separator1;
	protected JComponent separator2;
	// JFormDesigner - End of variables declaration //GEN-END:variables
	
	/**
	    * Helper method to load an image file from the CLASSPATH
	    * @param imageName the package and name of the file to load relative to the CLASSPATH
	    * @return an ImageIcon instance with the specified image file
	    * @throws IllegalArgumentException if the image resource cannot be loaded.
	    */
	   public ImageIcon loadImage( String imageName )
	   {
	      try
	      {
	         ClassLoader classloader = getClass().getClassLoader();
	         java.net.URL url = classloader.getResource( imageName );
	         if ( url != null )
	         {
	            ImageIcon icon = new ImageIcon( url );
	            return icon;
	         }
	      }
	      catch( Exception e )
	      {
	         e.printStackTrace();
	      }
	      throw new IllegalArgumentException( "Unable to load image: " + imageName );
	   }
}
