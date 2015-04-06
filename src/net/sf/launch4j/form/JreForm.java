package net.sf.launch4j.form;

import javax.swing.*;
import com.jeta.forms.components.separator.TitledSeparator;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public abstract class JreForm extends JPanel
{
   protected final JLabel _jrePathLabel = new JLabel();
   protected final JLabel _jreMinLabel = new JLabel();
   protected final JLabel _jreMaxLabel = new JLabel();
   protected final JLabel _jvmOptionsTextLabel = new JLabel();
   protected final JTextField _jreMinField = new JTextField();
   protected final JTextField _jreMaxField = new JTextField();
   protected final JTextArea _jvmOptionsTextArea = new JTextArea();
   protected final JLabel _initialHeapSizeLabel = new JLabel();
   protected final JLabel _maxHeapSizeLabel = new JLabel();
   protected final JTextField _initialHeapSizeField = new JTextField();
   protected final JTextField _maxHeapSizeField = new JTextField();
   protected final JTextField _maxHeapPercentField = new JTextField();
   protected final JTextField _initialHeapPercentField = new JTextField();
   protected final JComboBox _jdkPreferenceCombo = new JComboBox();
   protected final JComboBox _runtimeBitsCombo = new JComboBox();
   protected final JComboBox _varCombo = new JComboBox();
   protected final JButton _propertyButton = new JButton();
   protected final JButton _optionButton = new JButton();
   protected final JButton _envPropertyButton = new JButton();
   protected final JButton _envOptionButton = new JButton();
   protected final JTextField _envVarField = new JTextField();
   protected final JTextField _jrePathField = new JTextField();
   protected final JTextField _jreDownloadUrlField = new JTextField();
   protected final JCheckBox _bundledJre64BitCheck = new JCheckBox();
   protected final JCheckBox _bundledJreAsFallbackCheck = new JCheckBox();

   /**
    * Default constructor
    */
   public JreForm()
   {
      initializePanel();
   }

   /**
    * Adds fill components to empty cells in the first row and first column of the grid.
    * This ensures that the grid spacing will be the same as shown in the designer.
    * @param cols an array of column indices in the first row where fill components should be added.
    * @param rows an array of row indices in the first column where fill components should be added.
    */
   void addFillComponents( Container panel, int[] cols, int[] rows )
   {
      Dimension filler = new Dimension(10,10);

      boolean filled_cell_11 = false;
      CellConstraints cc = new CellConstraints();
      if ( cols.length > 0 && rows.length > 0 )
      {
         if ( cols[0] == 1 && rows[0] == 1 )
         {
            /** add a rigid area  */
            panel.add( Box.createRigidArea( filler ), cc.xy(1,1) );
            filled_cell_11 = true;
         }
      }

      for( int index = 0; index < cols.length; index++ )
      {
         if ( cols[index] == 1 && filled_cell_11 )
         {
            continue;
         }
         panel.add( Box.createRigidArea( filler ), cc.xy(cols[index],1) );
      }

      for( int index = 0; index < rows.length; index++ )
      {
         if ( rows[index] == 1 && filled_cell_11 )
         {
            continue;
         }
         panel.add( Box.createRigidArea( filler ), cc.xy(1,rows[index]) );
      }

   }

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

   public JPanel createPanel()
   {
      JPanel jpanel1 = new JPanel();
      FormLayout formlayout1 = new FormLayout("FILL:7DLU:NONE,RIGHT:MAX(65DLU;DEFAULT):NONE,FILL:3DLU:NONE,FILL:60DLU:NONE,FILL:3DLU:NONE,FILL:DEFAULT:NONE,FILL:7DLU:NONE,FILL:60DLU:NONE,FILL:3DLU:NONE,FILL:DEFAULT:GROW(1.0),FILL:7DLU:NONE","CENTER:9DLU:NONE,CENTER:DEFAULT:NONE,CENTER:9DLU:NONE,CENTER:DEFAULT:NONE,CENTER:3DLU:NONE,CENTER:DEFAULT:NONE,CENTER:3DLU:NONE,CENTER:DEFAULT:NONE,CENTER:9DLU:NONE,CENTER:DEFAULT:NONE,CENTER:3DLU:NONE,CENTER:DEFAULT:NONE,CENTER:3DLU:NONE,CENTER:DEFAULT:NONE,CENTER:3DLU:NONE,FILL:50DLU:GROW(1.0),CENTER:3DLU:NONE,CENTER:DEFAULT:NONE,CENTER:9DLU:NONE");
      CellConstraints cc = new CellConstraints();
      jpanel1.setLayout(formlayout1);

      _jrePathLabel.setName("jrePathLabel");
      _jrePathLabel.setText(Messages.getString("jrePath"));
      jpanel1.add(_jrePathLabel,cc.xy(2,2));

      _jreMinLabel.setName("jreMinLabel");
      _jreMinLabel.setText(Messages.getString("jreMin"));
      jpanel1.add(_jreMinLabel,cc.xy(2,6));

      _jreMaxLabel.setName("jreMaxLabel");
      _jreMaxLabel.setText(Messages.getString("jreMax"));
      jpanel1.add(_jreMaxLabel,cc.xy(2,8));

      _jvmOptionsTextLabel.setName("jvmOptionsTextLabel");
      _jvmOptionsTextLabel.setText(Messages.getString("jvmOptions"));
      jpanel1.add(_jvmOptionsTextLabel,new CellConstraints(2,16,1,1,CellConstraints.DEFAULT,CellConstraints.TOP));

      _jreMinField.setName("jreMinField");
      jpanel1.add(_jreMinField,cc.xy(4,6));

      _jreMaxField.setName("jreMaxField");
      jpanel1.add(_jreMaxField,cc.xy(4,8));

      _jvmOptionsTextArea.setName("jvmOptionsTextArea");
      _jvmOptionsTextArea.setToolTipText(Messages.getString("jvmOptionsTip"));
      JScrollPane jscrollpane1 = new JScrollPane();
      jscrollpane1.setViewportView(_jvmOptionsTextArea);
      jscrollpane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      jscrollpane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      jpanel1.add(jscrollpane1,cc.xywh(4,16,7,1));

      _initialHeapSizeLabel.setName("initialHeapSizeLabel");
      _initialHeapSizeLabel.setText(Messages.getString("initialHeapSize"));
      jpanel1.add(_initialHeapSizeLabel,cc.xy(2,12));

      _maxHeapSizeLabel.setName("maxHeapSizeLabel");
      _maxHeapSizeLabel.setText(Messages.getString("maxHeapSize"));
      jpanel1.add(_maxHeapSizeLabel,cc.xy(2,14));

      JLabel jlabel1 = new JLabel();
      jlabel1.setText("MB");
      jpanel1.add(jlabel1,cc.xy(6,12));

      JLabel jlabel2 = new JLabel();
      jlabel2.setText("MB");
      jpanel1.add(jlabel2,cc.xy(6,14));

      _initialHeapSizeField.setName("initialHeapSizeField");
      jpanel1.add(_initialHeapSizeField,cc.xy(4,12));

      _maxHeapSizeField.setName("maxHeapSizeField");
      jpanel1.add(_maxHeapSizeField,cc.xy(4,14));

      _maxHeapPercentField.setName("maxHeapPercentField");
      jpanel1.add(_maxHeapPercentField,cc.xy(8,14));

      _initialHeapPercentField.setName("initialHeapPercentField");
      jpanel1.add(_initialHeapPercentField,cc.xy(8,12));

      _jdkPreferenceCombo.setName("jdkPreferenceCombo");
      jpanel1.add(_jdkPreferenceCombo,cc.xywh(8,6,3,1));

      JLabel jlabel3 = new JLabel();
      jlabel3.setText(Messages.getString("availableMemory"));
      jpanel1.add(jlabel3,cc.xy(10,12));

      JLabel jlabel4 = new JLabel();
      jlabel4.setText(Messages.getString("availableMemory"));
      jpanel1.add(jlabel4,cc.xy(10,14));

      _runtimeBitsCombo.setName("runtimeBitsCombo");
      _runtimeBitsCombo.setToolTipText("");
      jpanel1.add(_runtimeBitsCombo,cc.xywh(8,8,3,1));

      jpanel1.add(createPanel1(),cc.xywh(2,18,9,1));
      TitledSeparator titledseparator1 = new TitledSeparator();
      titledseparator1.setText(Messages.getString("searchOptions"));
      jpanel1.add(titledseparator1,cc.xywh(2,4,9,1));

      TitledSeparator titledseparator2 = new TitledSeparator();
      titledseparator2.setText(Messages.getString("options"));
      jpanel1.add(titledseparator2,cc.xywh(2,10,9,1));

      jpanel1.add(createPanel2(),cc.xywh(4,2,7,1));
      addFillComponents(jpanel1,new int[]{ 1,2,3,4,5,6,7,8,9,10,11 },new int[]{ 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19 });
      return jpanel1;
   }

   public JPanel createPanel1()
   {
      JPanel jpanel1 = new JPanel();
      FormLayout formlayout1 = new FormLayout("RIGHT:MAX(65DLU;DEFAULT):NONE,FILL:3DLU:NONE,FILL:DEFAULT:GROW(1.0),FILL:3DLU:NONE,FILL:DEFAULT:NONE,FILL:3DLU:NONE,FILL:DEFAULT:NONE","CENTER:DEFAULT:NONE,CENTER:3DLU:NONE,CENTER:DEFAULT:NONE");
      CellConstraints cc = new CellConstraints();
      jpanel1.setLayout(formlayout1);

      _varCombo.setName("varCombo");
      jpanel1.add(_varCombo,cc.xy(3,1));

      _propertyButton.setActionCommand("Add");
      _propertyButton.setIcon(loadImage("images/edit_add16.png"));
      _propertyButton.setName("propertyButton");
      _propertyButton.setText(Messages.getString("property"));
      _propertyButton.setToolTipText(Messages.getString("propertyTip"));
      jpanel1.add(_propertyButton,cc.xy(5,1));

      _optionButton.setActionCommand("Add");
      _optionButton.setIcon(loadImage("images/edit_add16.png"));
      _optionButton.setName("optionButton");
      _optionButton.setText(Messages.getString("option"));
      _optionButton.setToolTipText(Messages.getString("optionTip"));
      jpanel1.add(_optionButton,cc.xy(7,1));

      _envPropertyButton.setActionCommand("Add");
      _envPropertyButton.setIcon(loadImage("images/edit_add16.png"));
      _envPropertyButton.setName("envPropertyButton");
      _envPropertyButton.setText(Messages.getString("property"));
      _envPropertyButton.setToolTipText(Messages.getString("propertyTip"));
      jpanel1.add(_envPropertyButton,cc.xy(5,3));

      JLabel jlabel1 = new JLabel();
      jlabel1.setText(Messages.getString("varsAndRegistry"));
      jpanel1.add(jlabel1,cc.xy(1,1));

      JLabel jlabel2 = new JLabel();
      jlabel2.setIcon(loadImage("images/asterix.gif"));
      jlabel2.setText(Messages.getString("envVar"));
      jpanel1.add(jlabel2,cc.xy(1,3));

      _envOptionButton.setActionCommand("Add");
      _envOptionButton.setIcon(loadImage("images/edit_add16.png"));
      _envOptionButton.setName("envOptionButton");
      _envOptionButton.setText(Messages.getString("option"));
      _envOptionButton.setToolTipText(Messages.getString("optionTip"));
      jpanel1.add(_envOptionButton,cc.xy(7,3));

      _envVarField.setName("envVarField");
      jpanel1.add(_envVarField,cc.xy(3,3));

      addFillComponents(jpanel1,new int[]{ 2,4,6 },new int[]{ 2 });
      return jpanel1;
   }

   public JPanel createPanel2()
   {
      JPanel jpanel1 = new JPanel();
      FormLayout formlayout1 = new FormLayout("FILL:DEFAULT:GROW(1.0),FILL:3DLU:NONE,FILL:DEFAULT:NONE,FILL:3DLU:NONE,FILL:DEFAULT:NONE","CENTER:DEFAULT:NONE");
      CellConstraints cc = new CellConstraints();
      jpanel1.setLayout(formlayout1);

      _jrePathField.setName("jrePathField");
      _jrePathField.setToolTipText(Messages.getString("jrePathTip"));
      jpanel1.add(_jrePathField,cc.xy(1,1));

      _bundledJre64BitCheck.setActionCommand(Messages.getString("bundledJre64Bit"));
      _bundledJre64BitCheck.setName("bundledJre64BitCheck");
      _bundledJre64BitCheck.setText(Messages.getString("bundledJre64Bit"));
      _bundledJre64BitCheck.setToolTipText(Messages.getString("bundledJre64BitTip"));
      jpanel1.add(_bundledJre64BitCheck,cc.xy(3,1));

      _bundledJreAsFallbackCheck.setActionCommand(Messages.getString("bundledJreAsFallback"));
      _bundledJreAsFallbackCheck.setName("bundledJreAsFallbackCheck");
      _bundledJreAsFallbackCheck.setText(Messages.getString("bundledJreAsFallback"));
      _bundledJreAsFallbackCheck.setToolTipText(Messages.getString("bundledJreAsFallbackTip"));
      jpanel1.add(_bundledJreAsFallbackCheck,cc.xy(5,1));

      addFillComponents(jpanel1,new int[]{ 2,4 },new int[0]);
      return jpanel1;
   }

   /**
    * Initializer
    */
   protected void initializePanel()
   {
      setLayout(new BorderLayout());
      add(createPanel(), BorderLayout.CENTER);
   }



	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Ed Moss
		DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
		jrePathLabel = new JLabel();
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
		separator1 = compFactory.createSeparator("$searchOptions");
		separator2 = compFactory.createSeparator("$options");
		panel2 = new JPanel();
		jrePathField = new JTextField();
		bundledJre64BitCheck = new JCheckBox();
		bundledJreAsFallbackCheck = new JCheckBox();

		//======== this ========

		// JFormDesigner evaluation mark
		setBorder(new javax.swing.border.CompoundBorder(
			new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
				"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
				java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

		setLayout(new FormLayout(
			"$ugap, right:[65dlu,default], 3dlu, 60dlu, 3dlu, default, 7dlu, 60dlu, 3dlu, default:grow, $ugap",
			"2*($pgap, default), 2*($lgap, default), $pgap, 3*(default, $lgap), fill:50dlu:grow, $lgap, default, $pgap"));

		//---- jrePathLabel ----
		jrePathLabel.setText("$jrePath");
		add(jrePathLabel, CC.xy(2, 2));

		//---- jreMinLabel ----
		jreMinLabel.setText("$jreMin");
		add(jreMinLabel, CC.xy(2, 6));

		//---- jreMaxLabel ----
		jreMaxLabel.setText("$jreMax");
		add(jreMaxLabel, CC.xy(2, 8));

		//---- jvmOptionsTextLabel ----
		jvmOptionsTextLabel.setText("$jvmOptions");
		add(jvmOptionsTextLabel, CC.xy(2, 16, CC.DEFAULT, CC.TOP));
		add(jreMinField, CC.xy(4, 6));
		add(jreMaxField, CC.xy(4, 8));

		//======== scrollPane1 ========
		{

			//---- jvmOptionsTextArea ----
			jvmOptionsTextArea.setToolTipText("$jvmOptionsTip");
			scrollPane1.setViewportView(jvmOptionsTextArea);
		}
		add(scrollPane1, CC.xywh(4, 16, 7, 1));

		//---- initialHeapSizeLabel ----
		initialHeapSizeLabel.setText("$initialHeapSize");
		add(initialHeapSizeLabel, CC.xy(2, 12));

		//---- maxHeapSizeLabel ----
		maxHeapSizeLabel.setText("$maxHeapSize");
		add(maxHeapSizeLabel, CC.xy(2, 14));

		//---- label1 ----
		label1.setText("MB");
		add(label1, CC.xy(6, 12));

		//---- label2 ----
		label2.setText("MB");
		add(label2, CC.xy(6, 14));
		add(initialHeapSizeField, CC.xy(4, 12));
		add(maxHeapSizeField, CC.xy(4, 14));
		add(maxHeapPercentField, CC.xy(8, 14));
		add(initialHeapPercentField, CC.xy(8, 12));
		add(jdkPreferenceCombo, CC.xywh(8, 6, 3, 1));

		//---- label3 ----
		label3.setText("$availableMemory");
		add(label3, CC.xy(10, 12));

		//---- label4 ----
		label4.setText("$availableMemory");
		add(label4, CC.xy(10, 14));

		//---- runtimeBitsCombo ----
		runtimeBitsCombo.setToolTipText("");
		add(runtimeBitsCombo, CC.xywh(8, 8, 3, 1));

		//======== panel1 ========
		{
			panel1.setLayout(new FormLayout(
				"right:[65dlu,default], $lcgap, default:grow, 2*($lcgap, default)",
				"default, $lgap, default"));
			panel1.add(varCombo, CC.xy(3, 1));

			//---- propertyButton ----
			propertyButton.setActionCommand("Add");
			propertyButton.setIcon(new ImageIcon(getClass().getResource("images/edit_add16.png")));
			propertyButton.setText("$property");
			propertyButton.setToolTipText("$propertyTip");
			panel1.add(propertyButton, CC.xy(5, 1));

			//---- optionButton ----
			optionButton.setActionCommand("Add");
			optionButton.setIcon(new ImageIcon(getClass().getResource("images/edit_add16.png")));
			optionButton.setText("$option");
			optionButton.setToolTipText("$optionTip");
			panel1.add(optionButton, CC.xy(7, 1));

			//---- envPropertyButton ----
			envPropertyButton.setActionCommand("Add");
			envPropertyButton.setIcon(new ImageIcon(getClass().getResource("images/edit_add16.png")));
			envPropertyButton.setText("$property");
			envPropertyButton.setToolTipText("$propertyTip");
			panel1.add(envPropertyButton, CC.xy(5, 3));

			//---- label5 ----
			label5.setText("$varsAndRegistry");
			panel1.add(label5, CC.xy(1, 1));

			//---- label6 ----
			label6.setIcon(new ImageIcon(getClass().getResource("images/asterix.gif")));
			label6.setText("$envVar");
			panel1.add(label6, CC.xy(1, 3));

			//---- envOptionButton ----
			envOptionButton.setActionCommand("Add");
			envOptionButton.setIcon(new ImageIcon(getClass().getResource("images/edit_add16.png")));
			envOptionButton.setText("$option");
			envOptionButton.setToolTipText("$optionTip");
			panel1.add(envOptionButton, CC.xy(7, 3));
			panel1.add(envVarField, CC.xy(3, 3));
		}
		add(panel1, CC.xywh(2, 18, 9, 1));
		add(separator1, CC.xywh(2, 4, 9, 1));
		add(separator2, CC.xywh(2, 10, 9, 1));

		//======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
				"default:grow, 2*($lcgap, default)",
				"default"));

			//---- jrePathField ----
			jrePathField.setToolTipText("$jrePathTip");
			panel2.add(jrePathField, CC.xy(1, 1));

			//---- bundledJre64BitCheck ----
			bundledJre64BitCheck.setActionCommand("$bundledJre64Bit");
			bundledJre64BitCheck.setText("$bundledJre64Bit");
			bundledJre64BitCheck.setToolTipText("$bundledJre64BitTip");
			panel2.add(bundledJre64BitCheck, CC.xy(3, 1));

			//---- bundledJreAsFallbackCheck ----
			bundledJreAsFallbackCheck.setActionCommand("$bundledJreAsFallback");
			bundledJreAsFallbackCheck.setText("$bundledJreAsFallback");
			bundledJreAsFallbackCheck.setToolTipText("$bundledJreAsFallbackTip");
			panel2.add(bundledJreAsFallbackCheck, CC.xy(5, 1));
		}
		add(panel2, CC.xywh(4, 2, 7, 1));
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Ed Moss
	private JLabel jrePathLabel;
	private JLabel jreMinLabel;
	private JLabel jreMaxLabel;
	private JLabel jvmOptionsTextLabel;
	private JTextField jreMinField;
	private JTextField jreMaxField;
	private JScrollPane scrollPane1;
	private JTextArea jvmOptionsTextArea;
	private JLabel initialHeapSizeLabel;
	private JLabel maxHeapSizeLabel;
	private JLabel label1;
	private JLabel label2;
	private JTextField initialHeapSizeField;
	private JTextField maxHeapSizeField;
	private JTextField maxHeapPercentField;
	private JTextField initialHeapPercentField;
	private JComboBox jdkPreferenceCombo;
	private JLabel label3;
	private JLabel label4;
	private JComboBox runtimeBitsCombo;
	private JPanel panel1;
	private JComboBox varCombo;
	private JButton propertyButton;
	private JButton optionButton;
	private JButton envPropertyButton;
	private JLabel label5;
	private JLabel label6;
	private JButton envOptionButton;
	private JTextField envVarField;
	private JComponent separator1;
	private JComponent separator2;
	private JPanel panel2;
	private JTextField jrePathField;
	private JCheckBox bundledJre64BitCheck;
	private JCheckBox bundledJreAsFallbackCheck;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
