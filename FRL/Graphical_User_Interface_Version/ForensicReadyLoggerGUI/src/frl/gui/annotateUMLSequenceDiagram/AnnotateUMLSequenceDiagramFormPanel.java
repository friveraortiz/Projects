package frl.gui.annotateUMLSequenceDiagram;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import frl.controller.annotateUMLSequenceDiagram.AnnotateUMLSequenceDiagramController;
import frl.gui.toolBar.ToolBar;
import frl.model.annotateUMLSequenceDiagram.MethodStepData;
import frl.model.annotateUMLSequenceDiagram.ReturnTypeData;
import frl.model.annotateUMLSequenceDiagram.UserData;
import frl.model.configuration.FRLConfiguration;
import frl.model.loadUMLSequenceDiagram.AttributeSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ParameterSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ReturnTypeValueSequenceDiagram;
import frl.model.loadUMLSequenceDiagram.ValueSequenceDiagram;

public class AnnotateUMLSequenceDiagramFormPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
    // Define Labels //
    public JLabel whQuestionLabel;
    public JLabel image1Label;
    public JLabel image2Label;
    public JLabel userNameLabel;  
    public JLabel userIdLabel;
    public JLabel methodNameLabel;
    public JLabel parameterNameLabel;
    public JLabel comparison2Label;
    public JLabel comparison1Label;
    //public JLabel comparison3Label;
    public JLabel attributeNameLabel;
    public JLabel annotationLabel;
    public JLabel methodIdLabel;
    public JLabel parameterIdLabel;
    public JLabel parameterDataTypeIdLabel;
    public JLabel attributeIdLabel;
    public JLabel methodStepIdLabel;
    public JLabel methodTextFileNameLabel;
    public JLabel methodImageFileNameLabel;
    public JLabel returnTypeNameLabel;
    public JLabel valueNameLabel;
    public JLabel valueIdLabel;
    public JLabel valueTextLabel1;
    public JLabel returnTypeValueLabel;
    public JLabel returnTypeIdLabel;
    public JLabel returnTypeValueIdLabel;
    public JLabel annotationTypeLabel;
    
    // Define Text Fields
    public JTextField userIdField;
    public JTextField userTextFileNameField;
    public JTextField userImageFileNameField;
    public JTextField methodIdField;
    public JTextField methodStepIdField;
    public JTextField methodTextFileNameField;
    public JTextField methodImageFileNameField;
    public JTextField parameterIdField;
    public JTextField parameterDataTypeIdField;
    public JTextField attributeIdField;
    public JTextField attributeDataTypeNameField;
    public JTextField valueIdField;
    public JTextField valueTextField1;
    public JTextField returnTypeIdField;
    public JTextField returnTypeValueIdField;
    public JTextField returnTypeNameField;
    
    // Define Image Fields //
    public ImageIcon img1Icon;  
    public ImageIcon img2Icon;  
    public BufferedImage image1, image2;
    
    // Define ComboBox Fields //
    public JComboBox<String> userNameCombo;
    public JComboBox<String> methodNameCombo;
    public JComboBox<String> parameterNameCombo;
    public JComboBox<String> attributeNameCombo;
    public JComboBox<String> valueNameCombo;
    public JComboBox<String> returnTypeValueCombo;
    public JComboBox<?> whQuestionCombo;
    public JComboBox<?> comparison2Combo; 
    public JComboBox<?> comparison1Combo;
    //public JComboBox<?> comparison3Combo;
    
    // Define ComboBox Fields //
    public ButtonGroup annotationTypeGroup;
    public JRadioButton annotationTypeParameter;
    public JRadioButton annotationTypeReturnType;
    public JRadioButton annotationTypeMethod;
    
    // Define Button Fields //
    public JButton saveBtn;
	public JButton cancelBtn;
	public JButton addAnnotationBtn; 
	public JButton deleteAnnotationBtn;
	
	// Define JPanels // 
    public JPanel panel1;
    public JPanel panel2;
    public JPanel panel3;
    public JScrollPane scrollPane1;
    public JScrollPane scrollPane2;

    // Define data Variables //
    public boolean RIGHT_TO_LEFT = false;
    
    public String [] comparison = {"=", "!=", ">", "<", ">=", "<="},
    		         logical = {"and","or"}, 
                     whQuestions = {"Who?", "When?", "What?", "How?"};
    
    String errorMessage1="", errorMessage2="", prototypeValue="XXXXXXXXXXXXXXXXXXXX";
    
    // Controller
    private AnnotateUMLSequenceDiagramController annotateUMLSequenceDiagramController;
    
    // Listener //
    private AnnotateUMLSequenceDiagramFormListener annotateUMLSequenceDiagramFileFormListener;
    
	public AnnotateUMLSequenceDiagramFormPanel(String databaseConfigFile, String featuresConfigFile, 
			                                   int projectId, Container pane, FRLConfiguration frlCon)
	{
		Dimension dim;
		Color color;
		
		dim = getPreferredSize();
		
		// Set the Size of the Form
		dim.height = 1000;
		dim.width = 1000;
		setPreferredSize(dim);
		
		// Define an object for the Controller Class
		annotateUMLSequenceDiagramController = new AnnotateUMLSequenceDiagramController();
		
        if (RIGHT_TO_LEFT) 
        {
           pane.setComponentOrientation(java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }
	    
		// Create the Fields //
	    color = Color.WHITE;
        userNameLabel = new JLabel("User Name: ");
        userNameCombo = new JComboBox<String>();
        userNameCombo.setBackground(color);
        userNameCombo.setToolTipText("List of Users that participate in the UML Sequence Diagram");
        
        color = Color.WHITE;
        userIdLabel = new JLabel("User Id:");
        
        userIdField = new JTextField(10);
        userTextFileNameField = new JTextField(30);
        userImageFileNameField = new JTextField(30);
        
        saveBtn = new JButton("Save");
        saveBtn.setToolTipText("Saves the Annotations made in the Unified Modelling Language Sequence Diagram");
     
        cancelBtn = new JButton("Cancel");
        cancelBtn.setToolTipText("Closes the Annotating the Unified Modelling Language Sequence Diagram Screen");
        
        panel1 = new JPanel();
        		
        panel1.add(userNameLabel);
        panel1.add(userNameCombo);
        
        panel1.add(saveBtn);
        panel1.add(cancelBtn);
        
        panel1.add(userIdLabel);
        panel1.add(userIdField);
        
        panel1.add(userTextFileNameField);
        panel1.add(userImageFileNameField);
        
        // PANEL 2 LEFT SIDE
        
        panel2 = new JPanel();  
        
        scrollPane1 = new JScrollPane(panel2);  
        scrollPane1.setPreferredSize(new Dimension(640, 50));
        scrollPane1.setMaximumSize(new Dimension(640, 50));
        scrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel3 = new JPanel();
        
        // PANEL 3 RIGHT SIDE
        scrollPane2 = new JScrollPane(panel3);  
        scrollPane2.setPreferredSize(new Dimension(640, 50));
        scrollPane2.setMaximumSize(new Dimension(640, 50));
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        whQuestionLabel  = new JLabel("Wh-Question:");
        comparison2Label = new JLabel("Comparison 2 :");
        comparison1Label = new JLabel("Comparison 1 :");
        //comparison3Label = new JLabel("Comparison:");
        
        methodNameLabel          = new JLabel("Method Name:");
        methodIdLabel            = new JLabel("Method Id:");
        parameterNameLabel       = new JLabel("Parameter Name:");
        parameterIdLabel         = new JLabel("Parameter Id:");
        parameterDataTypeIdLabel = new JLabel("Parameter Data Type Id:");
        attributeNameLabel       = new JLabel("Attribute Name:");
        
        attributeIdLabel         = new JLabel("Attribute Id:");
        
        annotationLabel          = new JLabel("Annotation:");
        methodStepIdLabel        = new JLabel("Method Step Id:");
        
        methodTextFileNameLabel  = new JLabel("Method Text File:");
        methodImageFileNameLabel = new JLabel("Method Image File:");
        
        returnTypeNameLabel      = new JLabel("Return Type Name:");
        valueNameLabel           = new JLabel("Value Name:");
        valueIdLabel             = new JLabel("Value Id:");
        
        valueIdLabel             = new JLabel("Value Id:");
        valueTextLabel1          = new JLabel("Value Text 1:");
        
        returnTypeValueLabel     = new JLabel("Return Type Value:");
        
        returnTypeIdLabel        = new JLabel("Return Type Id:");
        returnTypeValueIdLabel   = new JLabel("RTypeValueId:");
        
        annotationTypeLabel      = new JLabel("Annotation Type:");
        annotationTypeGroup      = new ButtonGroup();
        
        annotationTypeMethod = new JRadioButton("Method", true);
        annotationTypeMethod.setActionCommand("Method");
        annotationTypeMethod.setToolTipText("Method Annotation Type");
        
        annotationTypeParameter  = new JRadioButton("Parameter");
        annotationTypeParameter.setActionCommand("Parameter");
        annotationTypeParameter.setToolTipText("Parameter Annotation Type");

        annotationTypeReturnType = new JRadioButton("Return Type");
        annotationTypeReturnType.setActionCommand("Return Type");
        annotationTypeReturnType.setToolTipText("Return Type Annotation Type");
                
        annotationTypeGroup.add(annotationTypeParameter);
        annotationTypeGroup.add(annotationTypeReturnType);
        annotationTypeGroup.add(annotationTypeMethod);
                
        color = Color.LIGHT_GRAY;
        addAnnotationBtn     = new JButton("Add");
        addAnnotationBtn.setToolTipText("Adds a new annotation into the Unified Modelling Language Sequence Diagram");
        
        deleteAnnotationBtn  = new JButton("Delete"); 
        deleteAnnotationBtn.setToolTipText("Deletes an annotation from the Unified Modelling Language Sequence Diagram");
        
        methodNameCombo = new JComboBox<String>();
        methodNameCombo.setBackground(color);
        methodNameCombo.setToolTipText("List of the Methods that participate in the UML Sequence Diagram");
        
        methodNameCombo.setPrototypeDisplayValue(prototypeValue);
        
        
        color = Color.WHITE;
        methodIdField            = new JTextField(10);
        parameterIdField         = new JTextField(10);
        parameterDataTypeIdField = new JTextField(10);
        
        attributeIdField           = new JTextField(10);
        attributeDataTypeNameField = new JTextField(10);
        
        methodStepIdField        = new JTextField(10);
        valueIdField             = new JTextField(10);
        valueTextField1          = new JTextField(10);
        
        methodTextFileNameField  = new JTextField(10);
        methodImageFileNameField = new JTextField(10);
        
        returnTypeIdField        = new JTextField(10);
        returnTypeValueIdField   = new JTextField(10);
        
        returnTypeNameField      = new JTextField(10);
        
        parameterNameCombo = new JComboBox<String>();
        parameterNameCombo.setBackground(color);
        parameterNameCombo.setToolTipText("List of the Parameters that belongs to the selected Method");
        
        parameterNameCombo.setPrototypeDisplayValue(prototypeValue);
        
        comparison2Combo  = new JComboBox<Object> (comparison);
        comparison2Combo.setBackground(color);
        comparison2Combo.setToolTipText("List of the Comparison Operators 2");
        
        comparison1Combo  = new JComboBox<Object> (comparison);
        comparison1Combo.setBackground(color);
        comparison1Combo.setToolTipText("List of the Comparison Operators 1");
        
        /*
        comparison3Combo  = new JComboBox<Object> (comparison);
        comparison3Combo.setBackground(color);
        comparison3Combo.setToolTipText("List of the Comparison Operators");
        */
        
        attributeNameCombo = new JComboBox<String>();
        attributeNameCombo.setBackground(color);
        attributeNameCombo.setToolTipText("List of the Attributes that belongs to the selected Parameter");
        
        attributeNameCombo.setPrototypeDisplayValue(prototypeValue);
        
        whQuestionCombo = new JComboBox<Object> (whQuestions);
        whQuestionCombo.setBackground(color);
        whQuestionCombo.setToolTipText("List of the Wh-Questions");
        
        valueNameCombo = new JComboBox<String>();
        valueNameCombo.setBackground(color);
        valueNameCombo.setToolTipText("List of the Values that belongs to the selected Attribute or Return Type");
        valueNameCombo.setPrototypeDisplayValue(prototypeValue);

        returnTypeValueCombo = new JComboBox<String>();
        returnTypeValueCombo.setBackground(color);
        returnTypeValueCombo.setToolTipText("List of the Return Types Values that belongs to the selected Method");

		// Declare the Listeners //

		// Listener for the userNameCombo
		userNameCombo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String userName="";
				AnnotateUMLSequenceDiagramFormEvent ev;
				
				userName = (String) userNameCombo.getSelectedItem();  
				
				valueTextField1.setText("");
				
				// Calling the constructor # 3 for the AnnotateUMLSequenceDiagramFormEvent Event Class
				ev = new AnnotateUMLSequenceDiagramFormEvent(this, projectId, userName);
				
				if(annotateUMLSequenceDiagramFileFormListener != null) 
				{
			       annotateUMLSequenceDiagramFileFormListener.userNameComboEventOccurred(ev);
				}	
			}
			
		});
		
		// Listener for the methodNameCombo
		methodNameCombo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String methodName="", userName="", userIdText="";
				int userId=0;
				AnnotateUMLSequenceDiagramFormEvent ev;
				
				// Obtain the value of the Objects //
				userIdText = userIdField.getText();
				
				if(userIdText != null && userIdText.trim().isEmpty() == false) 	
				   userId = Integer.valueOf(userIdText);
				
				userName   = (String) userNameCombo.getSelectedItem();  
				methodName = (String) methodNameCombo.getSelectedItem();  
				
				valueTextField1.setText("");
				//valueTextField2.setText("");

				// Calling the constructor # 4 for the AnnotateUMLSequenceDiagramFormEvent Event Class
                ev = new AnnotateUMLSequenceDiagramFormEvent(this, projectId, userId,  userName, methodName);
				
				if(annotateUMLSequenceDiagramFileFormListener != null) 
				{
			       annotateUMLSequenceDiagramFileFormListener.methodNameComboEventOccurred(ev);
				}	
			}
			
		});
		
		annotationTypeMethod.addActionListener(new ActionListener() 
	    {
	       @Override
		   public void actionPerformed(ActionEvent e) 
		   {
	          int userId=0, methodId=0;
	          String methodName="", annotationType="", userName="", userIdText="", methodIdText="";
	          AnnotateUMLSequenceDiagramFormEvent ev = null;	
	          
	          userIdText = userIdField.getText();
	          
	          if(userIdText != null && userIdText.trim().isEmpty() == false)
			     userId = Integer.valueOf(userIdText);
	          
			  userName   = (String) userNameCombo.getSelectedItem();  
			  
			  methodIdText = methodIdField.getText();
			  
			  if(methodIdText != null && methodIdText.trim().isEmpty() == false)
			     methodId = Integer.valueOf(methodIdText);
			  
			  methodName = (String) methodNameCombo.getSelectedItem(); 
			  
			  annotationType = annotationTypeGroup.getSelection().getActionCommand();
			  
			  valueTextField1.setText("");
			  //valueTextField2.setText("");
	          
		      // Calling the constructor # 5 for the AnnotateUMLSequenceDiagramFormEvent Event Class
              ev = new AnnotateUMLSequenceDiagramFormEvent(this, projectId, userId,  userName, methodId, methodName, annotationType);
				
			  if(annotateUMLSequenceDiagramFileFormListener != null) 
			  {
			     annotateUMLSequenceDiagramFileFormListener.annotationTypeEventOccurred(ev);
			  }	
	    	
	       }
	    });
		
		annotationTypeParameter.addActionListener(new ActionListener() 
	    {
		   @Override
		   public void actionPerformed(ActionEvent e) 
		   {
		      int userId=0, methodId=0;
		      String methodName="", annotationType="", userName="", userIdText="", methodIdText="";
		      AnnotateUMLSequenceDiagramFormEvent ev = null;	
		      
              userIdText = userIdField.getText();
              
			  if(userIdText != null && userIdText.trim().isEmpty() == false)
			     userId = Integer.valueOf(userIdText);
              userName  = (String) userNameCombo.getSelectedItem();  
			  
              methodIdText = methodIdField.getText();
              
			  if(methodIdText != null && methodIdText.trim().isEmpty() == false)
			     methodId   = Integer.valueOf(methodIdText);
			  
			  methodName = (String) methodNameCombo.getSelectedItem(); 
				  
			  annotationType = annotationTypeGroup.getSelection().getActionCommand();
			  
			  valueTextField1.setText("");
			  //valueTextField2.setText("");
		          
			  // Calling the constructor # 5 for the AnnotateUMLSequenceDiagramFormEvent Event Class
	          ev = new AnnotateUMLSequenceDiagramFormEvent(this, projectId, userId,  userName, methodId, methodName, annotationType);
					
			  if(annotateUMLSequenceDiagramFileFormListener != null) 
			  {
			     annotateUMLSequenceDiagramFileFormListener.annotationTypeEventOccurred(ev);
			  }	
		    	
		   }
	    });
		
		annotationTypeReturnType.addActionListener(new ActionListener() 
	    {
		   @Override
		   public void actionPerformed(ActionEvent e) 
		   {
		      int userId=0, methodId=0;
		      String methodName="", annotationType="", userName="", userIdText="", methodIdText="";
		      AnnotateUMLSequenceDiagramFormEvent ev = null;	
		         
		      
              userIdText = userIdField.getText();
			  
			  if(userIdText != null && userIdText.trim().isEmpty() == false)
			     userId = Integer.valueOf(userIdText);
			  
              userName = (String) userNameCombo.getSelectedItem();  
			  
              methodIdText = methodIdField.getText();
			  
			  if(methodIdText != null && methodIdText.trim().isEmpty() == false)
			     methodId = Integer.valueOf(methodIdText);
			  
			  methodName = (String) methodNameCombo.getSelectedItem(); 
			  
			  annotationType = annotationTypeGroup.getSelection().getActionCommand();
			  
			  valueTextField1.setText("");
			  //valueTextField2.setText("");
		          
			  // Calling the constructor # 5 for the AnnotateUMLSequenceDiagramFormEvent Event Class
	          ev = new AnnotateUMLSequenceDiagramFormEvent(this, projectId, userId,  userName, methodId, methodName, annotationType);
					
			  if(annotateUMLSequenceDiagramFileFormListener != null) 
			  {
			     annotateUMLSequenceDiagramFileFormListener.annotationTypeEventOccurred(ev);
			  }	
		    	
		   }
	    });
		
		// Listener for the parameterNameCombo
		parameterNameCombo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String methodIdText="",parameterName="", parameterIdText="", parameterDataTypeIdText="";
				int methodId=0, parameterId=0, parameterDataTypeId=0;
				AnnotateUMLSequenceDiagramFormEvent ev;
				
				// Obtain the value of the Objects //
				methodIdText = methodIdField.getText();
				  
			    if(methodIdText != null && methodIdText.trim().isEmpty() == false)
				   methodId = Integer.valueOf(methodIdText);
				
				parameterIdText = parameterIdField.getText();
				
				if(parameterIdText != null && parameterIdText.trim().isEmpty() == false) 	
                   parameterId = Integer.valueOf(parameterIdText);
				
				parameterDataTypeIdText = parameterDataTypeIdField.getText();
				
				if(parameterDataTypeIdText != null && parameterDataTypeIdText.trim().isEmpty() == false) 
				   parameterDataTypeId = Integer.valueOf(parameterDataTypeIdText);
				
				parameterName = (String) parameterNameCombo.getSelectedItem();  
				
				valueTextField1.setText("");
				//valueTextField2.setText("");
				
				// Calling the constructor # 5 for the AnnotateUMLSequenceDiagramFormEvent Event Class
                ev = new AnnotateUMLSequenceDiagramFormEvent(this, projectId, methodId, parameterId,
                		                                     parameterDataTypeId, parameterName);
				
				if(annotateUMLSequenceDiagramFileFormListener != null) 
				{
			       annotateUMLSequenceDiagramFileFormListener.parameterNameComboEventOccurred(ev);
				}	
			}
			
		});
		
		// Listener for the attributeNameCombo
		attributeNameCombo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String attributeName="", methodIdText="", parameterIdText="", parameterDataTypeIdText="", attributeIdText="",
					   attributeDataTypeIdText="", attributeDataTypeName="";
				int methodId=0, parameterId=0, parameterDataTypeId=0, attributeId=0;
				AnnotateUMLSequenceDiagramFormEvent ev;
				
				// Obtain the value of the Objects //
				
				methodIdText = methodIdField.getText();
				
				if(methodIdText != null && methodIdText.trim().isEmpty() == false) 	
				   methodId = Integer.valueOf(methodIdText);
				
				parameterIdText = parameterIdField.getText();
				
				if(parameterIdText != null && parameterIdText.trim().isEmpty() == false) 	
                   parameterId = Integer.valueOf(parameterIdText);
				
				parameterDataTypeIdText = parameterDataTypeIdField.getText();
				
				if(parameterDataTypeIdText != null && parameterDataTypeIdText.trim().isEmpty() == false) 
				   parameterDataTypeId = Integer.valueOf(parameterDataTypeIdText);
				
				attributeIdText = attributeIdField.getText();
				
				if(attributeIdText != null && attributeIdText.trim().isEmpty() == false) 
				   attributeId = Integer.valueOf(attributeIdText);
				
				attributeName = (String) attributeNameCombo.getSelectedItem();  
				
				valueTextField1.setText("");
				//valueTextField2.setText("");
				
			    // Calling the constructor # 6 for the AnnotateUMLSequenceDiagramFormEvent Event Class
                ev = new AnnotateUMLSequenceDiagramFormEvent(this, projectId, methodId, parameterId,
                		                                     parameterDataTypeId, attributeId,
                		                                     attributeName);
				
				if(annotateUMLSequenceDiagramFileFormListener != null) 
				{
			       annotateUMLSequenceDiagramFileFormListener.attributeNameComboEventOccurred(ev);
				}	
			}
			
		});
		
		// Listener for the valueNameCombo
		valueNameCombo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String attributeName="", valueName="", methodIdText="", parameterIdText="", parameterDataTypeIdText="",
					   attributeIdText="", attributeDataTypeNameText="", valueIdText="";
				int methodId=0, parameterId=0, parameterDataTypeId=0, attributeId=0, attributeDataTypeId=0, valueId = 0;
				AnnotateUMLSequenceDiagramFormEvent ev;
				
				// Obtain the value of the Objects //
				methodIdText = methodIdField.getText();
				
				if(methodIdText != null && methodIdText.trim().isEmpty() == false) 	
				   methodId = Integer.valueOf(methodIdText);
				
				parameterIdText = parameterIdField.getText();
				
				if(parameterIdText != null && parameterIdText.trim().isEmpty() == false) 	
                   parameterId = Integer.valueOf(parameterIdText);
				
				parameterDataTypeIdText = parameterDataTypeIdField.getText();
				
				if(parameterDataTypeIdText != null && parameterDataTypeIdText.trim().isEmpty() == false) 
				   parameterDataTypeId = Integer.valueOf(parameterDataTypeIdText);
				
				attributeIdText = attributeIdField.getText();
				
				if(attributeIdText != null && attributeIdText.trim().isEmpty() == false) 
			       attributeId   = Integer.valueOf(attributeIdText);
				
				attributeName = (String) attributeNameCombo.getSelectedItem();  
				
				valueIdText = valueIdField.getText();
				
				if(valueIdText != null && valueIdText.trim().isEmpty() == false) 
				   valueId          = Integer.valueOf(valueIdText);
				
				valueName = (String) valueNameCombo.getSelectedItem();  
			
				valueTextField1.setText("");
				//valueTextField2.setText("");
				
			    // Calling the constructor # 7 for the AnnotateUMLSequenceDiagramFormEvent Event Class
                ev = new AnnotateUMLSequenceDiagramFormEvent(this, projectId, methodId, parameterId,
                		                                     parameterDataTypeId, attributeId,
                		                                     attributeName, valueId, valueName);
				
				if(annotateUMLSequenceDiagramFileFormListener != null) 
				{
			       annotateUMLSequenceDiagramFileFormListener.valueNameComboEventOccurred(ev);
				}	
			}
			
		});
		
		// Listener for the returnTypeNameCombo
		returnTypeValueCombo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   int methodId=0, returnTypeValueId=0;
			   String returnTypeValueName=null, methodIdText="";
			   AnnotateUMLSequenceDiagramFormEvent ev;
				
			   // Obtain the value of the Objects //
			   methodIdText = methodIdField.getText();
				
			   if(methodIdText != null && methodIdText.trim().isEmpty() == false) 	
			      methodId = Integer.valueOf(methodIdText);
				
			   returnTypeValueName = (String) returnTypeValueCombo.getSelectedItem(); 

			   // Calling the constructor # 9 for the AnnotateUMLSequenceDiagramFormEvent Event Class
               ev = new AnnotateUMLSequenceDiagramFormEvent(this, projectId, methodId, returnTypeValueId, returnTypeValueName);
				
			   if(annotateUMLSequenceDiagramFileFormListener != null) 
			   {
			      annotateUMLSequenceDiagramFileFormListener.returnTypeValueComboEventOccurred(ev);
			   }
	
			}
			
		});
		
		// Listener for the Add Annotation Button
		addAnnotationBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   AnnotateUMLSequenceDiagramFormEvent ev = null;
			   int userId=0, methodId=0, methodStepId=0, parameterId=0,
				   parameterDataTypeId=0, attributeId=0, 
				   valueId=0, returnTypeId=0,
				   returnTypeValueId=0;
			   String userName="", userTextFileName="", userImageFileName="", whQuestionName="", 
					  methodName="", methodTextFileName="", methodImageFileName="", parameterName="",
					  attributeName="", comparison2Name="", comparison1Name="",
					  valueName="", valueText1="", returnTypeName="", returnTypeValueName="", 
					  annotationType="", attributeDataTypeName="";
			   
			   // Obtain the values from the GUI //
			   
			   // User Information
			   if (userIdField.getText().isEmpty() == false) 
			   {
			      userId              = Integer.valueOf(userIdField.getText());
			      userName            = (String) userNameCombo.getSelectedItem();  
			      userTextFileName    = userTextFileNameField.getText();
			      userImageFileName   = userImageFileNameField.getText();
			   }
			   
			   // Wh-Question Information
			   whQuestionName = (String) whQuestionCombo.getSelectedItem(); 

			   // Method Information
			   if (methodIdField.getText().isEmpty() == false) 
			   {
			      methodId            = Integer.valueOf(methodIdField.getText());
			      methodStepId        = Integer.valueOf(methodStepIdField.getText());
			      
			      methodName          = (String) methodNameCombo.getSelectedItem(); 
			      methodTextFileName  = methodTextFileNameField.getText();
			      methodImageFileName = methodImageFileNameField.getText();
			   }
			   
			   annotationType = annotationTypeGroup.getSelection().getActionCommand();
			   
			   // Parameter Information
			   if (parameterIdField.getText().isEmpty() == false) 
			   {	   
			      parameterId         = Integer.valueOf(parameterIdField.getText());
			      parameterDataTypeId = Integer.valueOf(parameterDataTypeIdField.getText());
			      parameterName       = (String) parameterNameCombo.getSelectedItem(); 
			   }

			   // Attribute Information
			   if (attributeIdField.getText().isEmpty() == false) 
			   {
			      attributeId           = Integer.valueOf(attributeIdField.getText());
			      attributeDataTypeName = attributeDataTypeNameField.getText();
			      attributeName         = (String) attributeNameCombo.getSelectedItem(); 
			   }
			   
			   // Comparison Information
			   comparison1Name = (String) comparison1Combo.getSelectedItem(); 
			   comparison2Name = (String) comparison2Combo.getSelectedItem(); 
			   
			   // Value Information
			   if (valueIdField.getText().isEmpty() == false) 
			   {
			      valueId   = Integer.valueOf(valueIdField.getText());
			      valueName = (String) valueNameCombo.getSelectedItem();
			   }
			   
			   if (valueTextField1.getText().isEmpty() == false) 
			   {
			      valueText1 = valueTextField1.getText();
			   }   
   
			   // Return Type Information
			   if (returnTypeIdField.getText().isEmpty() == false) 
			   {
			      returnTypeId    = Integer.valueOf(returnTypeIdField.getText());
			      returnTypeName  = (String) returnTypeNameField.getText();
			   }
			   
			   // Comparison 3 Information
			   //comparison3Name = (String) comparison3Combo.getSelectedItem(); 
			   
			   if (returnTypeValueIdField.getText().isEmpty() == false) 
			   {
			      returnTypeValueId = Integer.valueOf(returnTypeValueIdField.getText());
			      returnTypeValueName = (String) returnTypeValueCombo.getSelectedItem();
			   }  
			   
			   
	           // Calling the constructor # 9 for the AnnotateUMLSequenceDiagramFormEvent Event Class
			   ev = new AnnotateUMLSequenceDiagramFormEvent(this, projectId, userId, userName,           
        			                                        userTextFileName, userImageFileName, whQuestionName, 
        			                                        methodId, methodStepId, methodName,       
        			                                        methodTextFileName, methodImageFileName, 
        			                                        parameterId, parameterDataTypeId, parameterName,
        			                                        attributeId, attributeName, 
        			                                        attributeDataTypeName,
        			                                        comparison1Name,     
        			                                        valueId, valueName, valueText1,   
        			                                        returnTypeId, returnTypeName, comparison2Name, 
        			                                        returnTypeValueId, 
        			                                        returnTypeValueName,
        			                                        /*comparison3Name,*/ annotationType);
        			                                        
			   if(annotateUMLSequenceDiagramFileFormListener != null) 
			   {
			      annotateUMLSequenceDiagramFileFormListener.addAnnotationEventOccurred(ev);
			   }	


			}
			
		});

		// Listener for the Save Button
		saveBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   AnnotateUMLSequenceDiagramFormEvent ev = null;
			   String userName="", userTextFileName="", userImageFileName="";
			   int userId=0;
			   
			   // Makes invisible the ConfigFileFormPanel Form
			   setVisible(false);
			   
			   // Makes visible the UserToolBar Add New Button
			   ToolBar.addNewButton.setVisible(true);
			   
			   // Obtain the values from the GUI //
			   
			   // User Information
			   if (userIdField.getText().isEmpty() == false) 
			   {
			      userId            = Integer.valueOf(userIdField.getText());
			      userName          = (String) userNameCombo.getSelectedItem();  
			      userTextFileName  = userTextFileNameField.getText();
			      userImageFileName = userImageFileNameField.getText();
			   }
			   
			   ev = new AnnotateUMLSequenceDiagramFormEvent(this, projectId, userName);
			   
			   ev = new AnnotateUMLSequenceDiagramFormEvent(this, projectId, userId, 
                                                            userName, userTextFileName, userImageFileName);
			   
			   if(annotateUMLSequenceDiagramFileFormListener != null) 
			   {
			      annotateUMLSequenceDiagramFileFormListener.saveEventOccurred(ev);
			   }
			}
		});
		
		// Listener for the Cancel Button
		cancelBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			   
			   setVisible(false);
			   
			   // Makes invisible the UserToolBar Add New Button
			   ToolBar.addNewButton.setVisible(true);
			   
			   if(annotateUMLSequenceDiagramFileFormListener != null) 
			   {
			      annotateUMLSequenceDiagramFileFormListener.cancelEventOccurred();
			   }
			}
		});
		
		
		// Clean the Fields and assign the default values
		cleanFields(databaseConfigFile, projectId, frlCon);
		
        // Allocate the visual components into the Configuration File Form
		layoutComponents(pane);
		
	}
	
 	// Method #2
	public void layoutComponents(Container pane) 
	{
        // set grid layout for the frame
	    setBackground(Color.lightGray);
	    
	    // Add Panel 1
        panel1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.add(panel1, BorderLayout.PAGE_START);
        
        // Add Panel 2
        pane.add(scrollPane1, BorderLayout.LINE_START);
        
        // Add Panel 3
    	panel3.setLayout(new GridBagLayout());
    	addComponents();
        //pane.add(panel3, BorderLayout.CENTER); 
    	pane.add(scrollPane2, BorderLayout.CENTER);
	}
	
	public void addComponents() 
	{
    	GridBagConstraints c = new GridBagConstraints();
    	
    	// First Column
    	
		// First row //
		c.gridx   = 0;
		c.gridy   = 0;
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(whQuestionLabel, c);
		
		c.gridx  = 1;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(whQuestionCombo, c);
	    
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
	    panel3.add(returnTypeIdLabel, c);
	    
	    c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(returnTypeIdField, c);
	    
		c.gridx   = 0;
		c.gridy++;
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(methodNameLabel, c);
		
		c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(methodNameCombo, c);
	    
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
	    panel3.add(returnTypeValueIdLabel, c);
	    
	    c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(returnTypeValueIdField, c);
	    
	    // Second row //
		c.gridx   = 0;
		c.gridy++;
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(annotationTypeLabel, c);
		
		c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(annotationTypeMethod, c);
	    
		c.gridx  = 1;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.CENTER;
	    panel3.add(annotationTypeParameter, c);
	    
		c.gridx  = 1;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_END;
	    panel3.add(annotationTypeReturnType, c);
	    
		// Third row //
		c.gridx   = 0;
		c.gridy++;
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(parameterNameLabel, c);
		
		c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(parameterNameCombo, c);
	    
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(returnTypeNameLabel, c);
		
		c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(returnTypeNameField, c);
	    
    	// First Column	    
		// Four row //
	    c.gridx   = 0;
	    c.gridy++;
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(attributeNameLabel, c);
		
		c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(attributeNameCombo, c);
	    
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(comparison1Label, c);
		
		c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(comparison1Combo, c);
	    
	    c.gridx = 0;
	    c.gridy++;
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(valueNameLabel, c);
		
		c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(valueNameCombo, c);
	    	    
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(returnTypeValueLabel, c);
		
		c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(returnTypeValueCombo, c);
	    
	    // Fifth row //
		c.gridx   = 0;
		c.gridy++;
		
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(comparison2Label, c);
		
		c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(comparison2Combo, c);
	    
		
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(valueTextLabel1, c);
		
			    
	    c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(valueTextField1, c);
	    
	   
	   /* 
	    // Six row //
		c.gridx   = 0;
		c.gridy++;
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(comparison3Label, c);
		
		c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(comparison3Combo, c);
	    */
	    	    
	    // Seventh row //
		c.gridx   = 0;
		c.gridy++;
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(annotationLabel, c);
		
		c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(addAnnotationBtn, c);
	    
		c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.CENTER;
	    panel3.add(deleteAnnotationBtn, c);
	    
	    // Seven row //
		c.gridx=0;
	    c.gridy++;
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
	    panel3.add(methodIdLabel, c);
	    
	    c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(methodIdField, c);
	    
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
	    panel3.add(methodStepIdLabel, c);
	    
	    c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(methodStepIdField, c);
	    
        // Eight row //
	    c.gridx=0;
	    c.gridy++;
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(parameterIdLabel, c);
		
	    c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(parameterIdField, c);
	    	
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(parameterDataTypeIdLabel, c);
		
	    c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(parameterDataTypeIdField, c);
	    
	    // Nine row //
	    c.gridx=0;
	    c.gridy++;
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(attributeIdLabel, c);
		
	    c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(attributeIdField, c);
	    				
	    c.gridx = 1;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.CENTER;
	    panel3.add(attributeDataTypeNameField, c);
	    
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(valueIdLabel, c);
		
	    c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(valueIdField, c);
	    
	    // Ten row //
	    c.gridx=0;
	    c.gridy++;
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(methodTextFileNameLabel, c);
		
	    c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(methodTextFileNameField, c);
	    
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets  = new Insets(0,0,0,5);
		c.anchor  = GridBagConstraints.LINE_END;
		c.fill    = GridBagConstraints.NONE;
		panel3.add(methodImageFileNameLabel, c);
		
	    c.gridx++;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(methodImageFileNameField, c);
	    
	    // Eleven row //
		c.gridx   = 1;
		c.gridy++;
		c.weightx = 1;
		c.weighty = 0.1;
		c.insets = new Insets(0,0,0,0);
		c.anchor = GridBagConstraints.LINE_START;
	    panel3.add(image2Label, c);  
   }
	
   public void setAnnotateUMLSequenceDiagramFormListener(AnnotateUMLSequenceDiagramFormListener listener) 
   {
	   this.annotateUMLSequenceDiagramFileFormListener = listener;
   }
   
   public void disableFields()
   {
      Color color; 
		  
	  color = Color.LIGHT_GRAY; 
		   
      // Do not allow to edit in these fields
	  
      userIdField.setFocusable(false);
      userIdField.setEnabled(false);
      userIdField.setBackground(color);
      
      userTextFileNameField.setFocusable(false);
      userTextFileNameField.setEnabled(false);
      userTextFileNameField.setBackground(color);
      
      userImageFileNameField.setFocusable(false);
      userImageFileNameField.setEnabled(false);
      userImageFileNameField.setBackground(color);
      
      methodIdField.setFocusable(false);
      methodIdField.setEnabled(false);
      methodIdField.setBackground(color);
      
      parameterIdField.setFocusable(false);
      parameterIdField.setEnabled(false);
      parameterIdField.setBackground(color);
      
      parameterDataTypeIdField.setFocusable(false);
      parameterDataTypeIdField.setEnabled(false);
      parameterDataTypeIdField.setBackground(color);
      
      attributeIdField.setFocusable(false);
      attributeIdField.setEnabled(false);
      attributeIdField.setBackground(color);

      attributeDataTypeNameField.setFocusable(false);
      attributeDataTypeNameField.setEnabled(false);
      attributeDataTypeNameField.setBackground(color);
      
      methodStepIdField.setFocusable(false);
      methodStepIdField.setEnabled(false);
      methodStepIdField.setBackground(color);
      
      methodTextFileNameField.setFocusable(false);
      methodTextFileNameField.setEnabled(false);
      methodTextFileNameField.setBackground(color);
      
      methodImageFileNameField.setFocusable(false);
      methodImageFileNameField.setEnabled(false);
      methodImageFileNameField.setBackground(color);
      
      valueIdField.setFocusable(false);
      valueIdField.setEnabled(false);
      valueIdField.setBackground(color);
      
      returnTypeIdField.setFocusable(false);
      returnTypeIdField.setEnabled(false);
      returnTypeIdField.setBackground(color);
      
      returnTypeValueIdField.setFocusable(false);
      returnTypeValueIdField.setEnabled(false);
      returnTypeValueIdField.setBackground(color);
      
      returnTypeNameField.setFocusable(false);
      returnTypeNameField.setEnabled(false);
      returnTypeNameField.setBackground(color);
		  
   } 
   
   public void loadUsers(int projectId, FRLConfiguration frlCon) 
   {
	  String errorMessage="", userName="", userIdStr="", userTextFileName="", userImageFileName="",
			 userTextFileNamePath="", userImageFileNamePath="";
	  int userId=0, i=0;
	  ArrayList<UserData> user=null;
	  
      // Delete all the current values in the Combo
	  userNameCombo.removeAllItems();
	      
	  try 
	  {
	     // Obtain an ArrayList of all the current Programming Languages from the FRL Database
	     user = annotateUMLSequenceDiagramController.loadUsers(projectId);
	  }
      catch (Exception e1) 
      {
         errorMessage = e1.getMessage();
         printErrorMessage(errorMessage, "Loading the Users from the Database");
	     //JOptionPane.showMessageDialog(this, errorMessage, "Loading the Users from the Database", JOptionPane.ERROR_MESSAGE);	
      }
	  
	  try
	  {
		 // Load all the current users from this Project
		 for (i = 0; i < user.size(); i++) 
		 {
		    userId            = user.get(i).getUserIdentifier();	 
		    userName          = user.get(i).getUserName();
		    userTextFileName  = user.get(i).getUserTextFileName(); 
		    userImageFileName = user.get(i).getUserImageFileName();
		    
		    // Convert Integer to String
		    userIdStr = String.valueOf(userId);
			    
			// Assign the UserName to the Combo
		    userNameCombo.addItem(userName);
		    
		    /*
		    System.out.println("USER INFORMATION");
		    System.out.println("User Id             : " + userId);
		    System.out.println("User Name           : " + userName);
		    System.out.println("User Text FileName  : " + userTextFileName);
		    System.out.println("User Image FileName : " + userImageFileName);
		    */
		    
		    if(i == 0)
		    {
		  	   userNameCombo.setSelectedItem(userName);
			   userIdField.setText(userIdStr);
			   
			   // Construct the complete path of the UML Text and Image Files
			   userTextFileNamePath = frlCon.projectOutputDir + File.separator +
					              	  frlCon.umlDirectoryName + File.separator + 
					              	  frlCon.projectName      + File.separator + 
					              	  userName                + File.separator + 
					              	  userTextFileName;
			   
			   userImageFileNamePath = frlCon.projectOutputDir + File.separator +
			                           frlCon.umlDirectoryName + File.separator + 
			                           frlCon.projectName      + File.separator + 
			                           userName                + File.separator + 
			                           userImageFileName;
			   
			   /*
			   System.out.println("User Text File Name Path : " + userTextFileNamePath);
			   System.out.println("User Image File Name Path: " + userImageFileNamePath);
			   */
			   
			   userTextFileNameField.setText(userTextFileNamePath);
			   userImageFileNameField.setText(userImageFileNamePath);
			   
		        
		       // Construct the complete path of the UML Text and Image Files at the User Level
		       try 
		       {
		          image1 = ImageIO.read(new File(userImageFileNamePath));
		          image1Label = new JLabel(new ImageIcon(image1));
		          panel2.add(image1Label);
		           
		           //System.out.println("UPLOADING IN THE JFRAME THE UML SEQUENCE DIAGRAM FOR THE USER: " + userName);
		  	   } 
		       catch (IOException e2) 
		       {
		   	      errorMessage1 = e2.getMessage();
		   	      errorMessage2 = "Error 2000: Ocurred while displaying the UML Sequence Diagram Image at the User Level: " + userImageFileNamePath + System.lineSeparator();
		   	      errorMessage2 = errorMessage2 + errorMessage1;
		   	     
		   	      printErrorMessage(errorMessage2, "Displaying the UML Sequence Diagram at the User Level");
		  	   }
		   
		    }
		    
	      }
	   } 
       catch (Exception e3) 
	   {
	      errorMessage = e3.getMessage();
		  //JOptionPane.showMessageDialog(this, errorMessage, "Loading the Users in the Combo.", JOptionPane.ERROR_MESSAGE);
	      printErrorMessage(errorMessage,"Loading the Users in the Combo");
	   }
   }
   
   public int loadMethods(int projectId, int userId, String userName, FRLConfiguration frlCon) 
   {
	  String errorMessage="", methodName="", methodIdStr="", methodStepIdStr="", 
			 methodTextFileName="", methodImageFileName="", methodTextFileNamePath="", 
			 methodImageFileNamePath="";
	  int i=0, methodId=0, initialMethodId=0, methodStepId=0;
	  ArrayList<MethodStepData> methodList=null;
	  
      // Delete all the current values in the Combo
	  methodNameCombo.removeAllItems();
	      
	  try 
	  {
	     // Obtain an ArrayList of all the current Programming Languages from the FRL Database
	     methodList = annotateUMLSequenceDiagramController.loadMethodsSteps(projectId, userId);
	  }	 
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
		 //JOptionPane.showMessageDialog(this, errorMessage, "Loading the Methods from the Database", JOptionPane.ERROR_MESSAGE);
	     printErrorMessage(errorMessage,"Loading the Methods from the Database");
	  }
		  		 
	  try
	  {
		 // Load all the current users from this Project
		 for (i = 0; i < methodList.size(); i++) 
		 {
		    methodName          = methodList.get(i).getMethodName();
		    methodId            = methodList.get(i).getMethodIdentifier();
		    methodTextFileName  = methodList.get(i).getMethodTextFileName();
			methodImageFileName = methodList.get(i).getMethodImageFileName();
			methodStepId        = methodList.get(i).getMethodStepIdentifier();
		    
		    // Convert Integer to String
		    methodIdStr     = String.valueOf(methodId);
		    methodStepIdStr = String.valueOf(methodStepId);
		    
		    // Assign an method to the Combo
		    methodNameCombo.addItem(methodName);
		    
		    if(i == 0)
		    {
		  	   methodNameCombo.setSelectedItem(methodName);
			   methodIdField.setText(methodIdStr);
			   methodStepIdField.setText(methodStepIdStr);
			   
			   // Construct the complete path of the UML Text and Image Files
			   methodTextFileNamePath = frlCon.projectOutputDir + File.separator +
					                    frlCon.umlDirectoryName + File.separator + 
					                    frlCon.projectName      + File.separator + 
					                    userName                + File.separator + 
					                    methodTextFileName;
			   
			   methodImageFileNamePath = frlCon.projectOutputDir + File.separator +
			                             frlCon.umlDirectoryName + File.separator + 
			                             frlCon.projectName      + File.separator + 
			                             userName                + File.separator + 
			                             methodImageFileName;
			   
			   //System.out.println("Method Text File Name  : " + methodTextFileNamePath);
			   //System.out.println("Method Image File Name : " + methodImageFileNamePath);
			   
			   methodTextFileNameField.setText(methodTextFileNamePath);
			   methodImageFileNameField.setText(methodImageFileNamePath);
			   
		       // Construct the complete path of the UML Text and Image Files at the Method Level
		       try 
		       {
		  	      image2 = ImageIO.read(new File(methodImageFileNamePath));
		          image2Label = new JLabel(new ImageIcon(image2));
		          panel3.add(image2Label);
		  	     
		  	      //System.out.println("UPLOADING THE UML SEQUENCE DIAGRAM FOR THE METHOD: "+ methodName);
		  	   } 
		       catch (IOException e2) 
		       {
		   	      errorMessage1 = e2.getMessage();
		   	      errorMessage2 = "Error 2001: Ocurred while displaying the UML Sequence Diagram Image at the Method Level: " 
		   	      + methodImageFileNamePath + System.lineSeparator();
		   	      errorMessage2 = errorMessage2 + errorMessage1;
		   	     
		   	      printErrorMessage(errorMessage2, "Displaying the UML Sequence Diagram at the Method Level");
		  	   }
			   
			   initialMethodId = methodId;
		    }
	      } 
	   } 
       catch (Exception e3) 
	   {
	      errorMessage = e3.getMessage();
		  //JOptionPane.showMessageDialog(this, errorMessage, "Loading the Methods in the Combo.", JOptionPane.ERROR_MESSAGE);	
	      printErrorMessage(errorMessage,"Loading the Methods in the Combo");
	   }
	  
      return initialMethodId;
   }
   
   public int loadParametersLocal(int projectId, int methodId) throws Exception
   {
	  String errorMessage="", parameterName="", parameterIdStr="", 
			 parameterDataTypeName="", parameterDataTypeIdStr="", elementName="", packageName="";
	  int i=0, parameterId=0, parameterDataTypeId=0, paramCase=0;
	  ArrayList<ParameterSequenceDiagram> parameterList=null;
	  
      // Delete all the current values in the Combo
	  parameterNameCombo.removeAllItems();
	  
	  //System.out.println("Project Id : " + projectId);
	  //System.out.println("Method Id  : " + methodId);
	  
	  // Initialize the Variables
	  parameterId           = 0;
	  parameterName         = "";
	  parameterDataTypeId   = 0;
	  parameterDataTypeName = "";
	  packageName           = "";
	  
	  //System.out.println("*** Inside the FORM PANEL - LoadParametersLocal Method ***");
		 
	  // Connect to the Database
	  try 
	  {
	     // Obtain an ArrayList of all the current Programming Languages from the FRL Database
	     parameterList = annotateUMLSequenceDiagramController.loadParameters(projectId, methodId);
	  }	 
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
		 throw new Exception(errorMessage); 	
	  }	 
		 
	  //System.out.println("Parameter List: " + parameterList.size());
  
      if(parameterList.size() > 0)	
      {	  
	     // Load all the current parameters from this Project
		 for (i = 0; i < parameterList.size(); i++) 
		 {
			// Get the information about the parameters 
		    parameterId           = parameterList.get(i).getParameterIdentifier();
		    parameterName         = parameterList.get(i).getParameterName();
		    parameterDataTypeId   = parameterList.get(i).getDataTypeIdentifier();
		    parameterDataTypeName = parameterList.get(i).getDataTypeName();
		    packageName           = parameterList.get(i).getPackageName();
		    
		    // Initialize the Variables
		    parameterIdStr         = "";
		    parameterDataTypeIdStr = "";
		    paramCase = 0;
		    
		    //System.out.println("PARAMETERS - 1");
		    
			// Convert from Integer to String
			try
			{
			   parameterIdStr = String.valueOf(parameterId); 
			}
			catch (Exception e2) 
			{
			   errorMessage1 = e2.getMessage();
			   errorMessage2 = "Error XXXX: Occurred in the FORM while converting to String the Parameter Id: " + parameterId + System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			   parameterIdStr = "0";
			   throw new Exception(errorMessage2);
			}	
			    	
			// Convert from Integer to String
			try
			{
		       parameterDataTypeIdStr = String.valueOf(parameterDataTypeId);
			}
			catch (Exception e3) 
			{
			   errorMessage1 = e3.getMessage();
			   errorMessage2 = "Error XXXX: Occurred in the FORM while converting to String the Parameter Data Type Id: " + parameterDataTypeId + System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			   parameterDataTypeIdStr = "0";
			   throw new Exception(errorMessage2);
			}
				
		    // Assign the Parameter Name to the Combo
			try
			{

			   if(packageName != null && packageName.trim().isEmpty() == false) 
			   {	
			      elementName = packageName + "." + parameterDataTypeName + " " + parameterName;
			      paramCase = 1; 
			   }   
			   else
		          if( (parameterDataTypeName != null && parameterDataTypeName.trim().isEmpty() == false) &&
		    	      (parameterName != null && parameterName.trim().isEmpty() == false) ) 	
		          {	   
			         elementName = parameterDataTypeName + " " + parameterName;
			         paramCase = 1; 
		          }   
		          else
		          {	   
		    	      elementName = "";
		    	      paramCase = 2; 
		          }
			}
			catch (Exception e4) 
			{
			   errorMessage1 = e4.getMessage();
			   errorMessage2 = "Error XXXX: Occurred in the FORM while assigning the Element Name: " + elementName + System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Package Name             : " + packageName + System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Parameter Data Type Name : " + parameterDataTypeName + System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Parameter Name           : " + parameterName + System.lineSeparator();
			   errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
			   parameterDataTypeIdStr = "0";
			   throw new Exception(errorMessage2);
			}
		       
		    //System.out.println("PARAMETERS - 4");
		    

		    if(i == 0)
		    {
		       try
			   {   
			      parameterIdField.setText(parameterIdStr);
			   }
			   catch (Exception e5) 
			   {
			      errorMessage1 = e5.getMessage();
				  errorMessage2 = "Error XXXX: Occurred in the FORM while setting the Value of the Parameter Id : " + parameterIdStr + System.lineSeparator();
				  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
				  throw new Exception(errorMessage2);
			   }
		       
		       try
			   {   
			      parameterDataTypeIdField.setText(parameterDataTypeIdStr);
			   }
			   catch (Exception e6) 
			   {
			      errorMessage1 = e6.getMessage();
				  errorMessage2 = "Error XXXX: Occurred in the FORM while setting the Value of the Parameter Data Type Id : " + parameterDataTypeIdStr + System.lineSeparator();
				  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
				  throw new Exception(errorMessage2);
			   }			   

		    }

		    if(paramCase == 1)
		    {	   
			   try
			   { 
		          parameterNameCombo.addItem(elementName);
			   }
			   catch (Exception e7) 
			   {
			      errorMessage1 = e7.getMessage();
				  errorMessage2 = "Error XXXX: Occurred in the FORM while adding to the Combo the Parameter: " + elementName + System.lineSeparator();
				  errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
				  throw new Exception(errorMessage2);
			   }
		    }  

	      }	 

      } 	  
      
      return parameterId;
   }  
   
   public int loadAttributes(int projectId, int methodId, int parameterId, int parameterDataTypeId) 
   {
	  String errorMessage="", attributeName="", attributeIdStr="", element="", attributeDataTypeName="";
	  int i=0, attributeId=0;
	  ArrayList<AttributeSequenceDiagram> attributeList=null;
	  
      // Delete all the current values in the Combo
	  attributeNameCombo.removeAllItems();
	  
	  try 
	  {
	     // Obtain an ArrayList of all the current Attributes from the FRL Database
	     attributeList = annotateUMLSequenceDiagramController.loadAttributes(projectId, methodId,
				                                                             parameterId, parameterDataTypeId);
	  }	 
	  catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
		 //JOptionPane.showMessageDialog(this, errorMessage, "Loading the Attributes from the Database", JOptionPane.ERROR_MESSAGE);
	     printErrorMessage(errorMessage,"Loading the Attributes from the Database");
	  }		 
  
	  try
	  {
	     // Load all the current attributes from this Project
		 for (i = 0; i < attributeList.size(); i++) 
		 {
		    attributeId   = attributeList.get(i).getAttributeIdentifier();
		    attributeName = attributeList.get(i).getAttributeName();
		    attributeDataTypeName = attributeList.get(i).getAttributeDataTypeName();
		    
		    // Convert Integer to String
		    attributeIdStr = String.valueOf(attributeId);
		    
		    if(i == 0)
		    {
		       attributeIdField.setText(attributeIdStr);
		       attributeDataTypeNameField.setText(attributeDataTypeName);
		       
		       // Enable/Disable the Comparison Combox for the Parameter DataType
		       attributeCombos(attributeDataTypeName);
		    	   
		    }
		    
		    element = attributeName;
		    
		    attributeNameCombo.addItem(element);

	      }	 
		 
	  }
      catch (Exception e2) 
	  {
	      errorMessage = e2.getMessage();
		  //JOptionPane.showMessageDialog(this, errorMessage, "Loading the Attributes in the Combo.", JOptionPane.ERROR_MESSAGE);	
	      printErrorMessage(errorMessage,"Loading the Attributes in the Combo");
	  }
	  
	  return attributeId;

   }  
    
   public void loadValues(int projectId, int methodId, int parameterId, int parameterDataTypeId,
		                  int progrLangId, int attributeId) 
   {
	  String errorMessage="", valueName="", valueIdStr="", element="";
	  //valueEquivalence="", 
			  
	  int i=0, valueId=0;
	  ArrayList<ValueSequenceDiagram> valueList=null;
	  
	  /*
	  System.out.println("*** Inside the Load Values Method - FORM ***");
	  
	  System.out.println("Project Id              : " + projectId);
	  System.out.println("Method Id               : " + methodId);
	  System.out.println("Parameter Id            : " + parameterId);
	  System.out.println("Parameter Data Type Id  : " + parameterDataTypeId);
	  System.out.println("Programming Language Id : " + progrLangId);
	  System.out.println("Attribute Id            : " + attributeId);
	  */
	  
	  // Delete all the current values in the Combo
	  valueNameCombo.removeAllItems();
	  
	  try 
	  {
	     // Obtain an ArrayList of all the current Values from the FRL Database
		 valueList = annotateUMLSequenceDiagramController.loadValues(projectId, methodId,
				                                                     parameterId, parameterDataTypeId,
				                                                     progrLangId, attributeId);
	  }
      catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
		 //JOptionPane.showMessageDialog(this, errorMessage, "Loading the Values from the Database", JOptionPane.ERROR_MESSAGE);	
	     printErrorMessage(errorMessage,"Loading the Values from the Database");
	  }
	  
	  //System.out.println("Value List: " + valueList.size());
	  
	  try
	  {
  
	     // Load all the current attributes from this Project
		 for (i = 0; i < valueList.size(); i++) 
		 {
		    valueId           = valueList.get(i).getValueIdentifier();
		    valueName         = valueList.get(i).getValueName();    
		    //valueEquivalence  = valueList.get(i).getValueEquivalence();
		    
		    // Convert Integer to String
		    valueIdStr = String.valueOf(valueId);
		    
		    if(i == 0)
		    {
		       valueIdField.setText(valueIdStr);
		    }
		    
		    //element = valueEquivalence;
		    element = valueName;
		    
		    valueNameCombo.addItem(element);

	      }	 
	  }
      catch (Exception e2) 
	  {
	      errorMessage = e2.getMessage();
		  //JOptionPane.showMessageDialog(this, errorMessage, "Loading the Values in the Combo.", JOptionPane.ERROR_MESSAGE);	
	      printErrorMessage(errorMessage,"Loading the Values in the Combo");
	  }

   }  
   
  public int loadReturnType(int projectId, int methodId) 
  {
     String errorMessage="", returnTypeIdStr="", returnTypeName1="", returnTypeName2="", returnTypePackageName="";
	 int returnTypeId=0;
	 ReturnTypeData returnType=null;
	  
     // Delete all the current values in the Combo
	 valueNameCombo.removeAllItems();
	  
	 try 
	 {
	    // Obtain an Record with the Return Type for this Project Id and Method Id
		returnType = annotateUMLSequenceDiagramController.loadReturnType(projectId, methodId);
	 }	
	 catch (Exception e1) 
	 {
        errorMessage = e1.getMessage();
		//JOptionPane.showMessageDialog(this, errorMessage, "Loading the Return Type from the Database", JOptionPane.ERROR_MESSAGE);
        printErrorMessage(errorMessage,"Loading the Return Types from the Database");
	 }	
	 
     try
     {
	    // Load all the current attributes from this Project
		returnTypeId    = returnType.getReturnTypeIdentifier();
		returnTypeName1 = returnType.getReturnTypeName();
		    
		// Convert Integer to String
		returnTypeIdStr = String.valueOf(returnTypeId);
		 
		try 
		{
		   returnTypePackageName = annotateUMLSequenceDiagramController.getReturnTypePackageName(projectId, methodId, returnTypeId);
	    } 
		catch (Exception e2) 
		{
		   errorMessage = e2.getMessage();
		   //JOptionPane.showMessageDialog(this, errorMessage, "Loading the Return Type PackageName.", JOptionPane.ERROR_MESSAGE);
		   printErrorMessage(errorMessage,"Loading the Return Typesfrom the Database");
		}
		    
		if(returnTypePackageName != null && !returnTypePackageName.trim().isEmpty()) 
		   returnTypeName2 = returnTypePackageName + " " + returnTypeName1;
		else
		   returnTypeName2 = returnTypeName1;
		    
		// Assign the values to the fields
		returnTypeIdField.setText(returnTypeIdStr);
		returnTypeNameField.setText(returnTypeName2);

	 }
     catch (Exception e3) 
	 {
	    errorMessage = e3.getMessage();
		//JOptionPane.showMessageDialog(this, errorMessage, "Loading the Return Type in the Fields.", JOptionPane.ERROR_MESSAGE);	
	    printErrorMessage(errorMessage,"Loading the Return Type in the Fields");
	 }
	 
	 return returnTypeId; 

   }  
  
   public void loadReturnTypeValues(int projectId, int methodId, int returnTypeId) 
   {
      String errorMessage="", returnTypeValueIdStr="", element="", returnTypeValueName1="",
			 returnTypeValueName2="", returnTypeValueEquivalence="", returnTypeValuePackageName="";
	  int i=0, returnTypeValueId=0;
	  ArrayList<ReturnTypeValueSequenceDiagram> returnTypeValueList=null;
	  
      // Delete all the current values in the Combo
	  returnTypeValueCombo.removeAllItems();
	  
	  try 
	  {
	     // Obtain an ArrayList of all the current Values from the FRL Database
         returnTypeValueList = annotateUMLSequenceDiagramController.loadReturnTypeValues(projectId, methodId, returnTypeId);
	  }
      catch (Exception e1) 
	  {
	     errorMessage = e1.getMessage();
		 //JOptionPane.showMessageDialog(this, errorMessage, "Loading the Return Type Values from the Database", JOptionPane.ERROR_MESSAGE);	
	     printErrorMessage(errorMessage,"Loading the Return Type Values from the Database");
	  }
	  
	  try
	  {
	     // Load all the current attributes from this Project
		 for (i = 0; i < returnTypeValueList.size(); i++) 
		 {
		    returnTypeValueId          = returnTypeValueList.get(i).getReturnTypeValueIdentifier();
			returnTypeValueName1       = returnTypeValueList.get(i).getReturnTypeValueName();
			returnTypeValueEquivalence = returnTypeValueList.get(i).getReturnTypeValueEquivalence();
			returnTypeValuePackageName = returnTypeValueList.get(i).getReturnTypeValuePackageName();
		    
		    // Convert Integer to String
			returnTypeValueIdStr = String.valueOf(returnTypeValueId);
			
		    if(returnTypeValuePackageName != null && !returnTypeValuePackageName.trim().isEmpty()) 
		       returnTypeValueName2 = returnTypeValuePackageName + "." + returnTypeValueName1;
			else
			   returnTypeValueName2 = returnTypeValueName1;
		    
		    if(i == 0)
		    {
		       returnTypeValueIdField.setText(returnTypeValueIdStr);
		       returnTypeNameField.setText(returnTypeValueName2);
		    }
		    
		    element = returnTypeValueEquivalence;
		    
		    returnTypeValueCombo.addItem(element);

	      }	 
	  }
      catch (Exception e2) 
	  {
	     errorMessage = e2.getMessage();
		 //JOptionPane.showMessageDialog(this, errorMessage, "Loading the Return Type Values in the Combo", JOptionPane.ERROR_MESSAGE);	
	     printErrorMessage(errorMessage,"Loading the Return Type Values in the Combo");
	  }

    }  
   
   public void assignDefaultValues(String databaseConfigFile, int projectId, FRLConfiguration frlCon) throws Exception
   {
      String errorMessage1="", errorMessage2="", userName="", userIdText="", parameterIdText="", parameterDataTypeIdText="";
      int userId=0, methodId=0, parameterId=0, parameterDataTypeId=0, attributeId=0, 
         progrLangId=0, returnTypeId=0;
      
      //System.out.println("Assign Default Values - 1");
      
      try 
      {
	     annotateUMLSequenceDiagramController.connect(databaseConfigFile);
	  } 
      catch (Exception e1) 
      {
 	     errorMessage1 = e1.getMessage();
 	     errorMessage2 = "Error XXXX: Occurred while connecting to the Database." + System.lineSeparator();
 	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
 		 
	  }
      
      //System.out.println("Assign Default Values - 2");
      
	  // Load the current Users from the Database
	  loadUsers(projectId, frlCon);
	  
	  //System.out.println("Assign Default Values - 3");
	  
	  userIdText = userIdField.getText();
			  
	  //System.out.println("Assign Default Values - 4");
	  
	  if(userIdText != null && !userIdText.trim().isEmpty()) 
	  {	  
		  
		 //System.out.println("Assign Default Values - 5");
		  
	     try
	     {
            userId = Integer.parseInt(userIdField.getText());
	     }
         catch (Exception e2) 
         {
 	 	    errorMessage1 = e2.getMessage();
 	 	    errorMessage2 = "Error XXXX: Occurred while getting the User Information." + System.lineSeparator();
 	 	    errorMessage2 = errorMessage2 + "The User Id is Empty. Error Message: " + errorMessage1;
 	 		 
	     }

	     //System.out.println("Assign Default Values - 6");
	     
         userName = (String) userNameCombo.getSelectedItem();
      
         //System.out.println("Assign Default Values - 7");
         
	     // Load the current Methods from the Database
	     methodId = loadMethods(projectId, userId, userName, frlCon);
	     
	     
         // Load the Return Type Details for this Method Id
         returnTypeId = loadReturnType(projectId, methodId);
         
         //System.out.println("Assign Default Values - 14");
      
         loadReturnTypeValues(projectId, methodId, returnTypeId);
	  
	     //System.out.println("Assign Default Values - 8");
	     
	     // Load the current Parameters from the Database
	     try
	     {
	        parameterId = loadParametersLocal(projectId, methodId);
	     }
	     catch (Exception e3) 
         {
 	 	    errorMessage1 = e3.getMessage();
 	 	    errorMessage2 = "Error XXXX: Occurred while getting the Parameter Information." + System.lineSeparator();
 	 	    errorMessage2 = errorMessage2 + "The Parameter Id is Empty. Error Message: " + errorMessage1;
 	 		 
	     }
	  
	     //System.out.println("Assign Default Values - 9");
	     
	     // Get the Information from the fields
	     parameterIdText         = parameterIdField.getText();
	     parameterDataTypeIdText = parameterDataTypeIdField.getText();
	     
	     if(parameterIdText != null && parameterIdText.trim().isEmpty() == false) 
	        parameterId = Integer.parseInt(parameterIdField.getText());
	     
	     //System.out.println("Assign Default Values - 10");
	     
	     if(parameterDataTypeIdText != null && parameterDataTypeIdText.trim().isEmpty() == false) 
	        parameterDataTypeId = Integer.parseInt(parameterDataTypeIdField.getText());
	    	 
	     /*
	     System.out.println("Parameter Id           : " + parameterId);
	     System.out.println("Parameter Data Type Id : " + parameterDataTypeId);
	     */
	     
	     //System.out.println("Assign Default Values - 11");
	     
	     // Load the current Attributes from the Database
	     attributeId = loadAttributes(projectId, methodId, parameterId, parameterDataTypeId);
	  
	     //System.out.println("Assign Default Values - 12");
	     
         loadValues(projectId, methodId, parameterId, parameterDataTypeId, progrLangId, attributeId); 
      
         //System.out.println("Assign Default Values - 13");
         //System.out.println("Assign Default Values - 15");
         
         try 
         {
	        annotateUMLSequenceDiagramController.disconnect();
	     } 
         catch (Exception e4) 
         {
     	     errorMessage1 = e4.getMessage();
     	     errorMessage2 = "Error XXXX: Occurred while disconnecting from the Database." + System.lineSeparator();
     	     errorMessage2 = errorMessage2 + "Error Message: " + errorMessage1;
     		 
	     }
         
         //System.out.println("Assign Default Values - 16");
      
	  }
	  else
	  {
	     errorMessage2 = "Error XXXX: Occurred while getting the User Information." + System.lineSeparator();
	 	 errorMessage2 = errorMessage2 + "The User Id is Empty. ";
	  }	  
      
   }
   
   public void cleanFields(String databaseConfigFile, int projectId, FRLConfiguration frlCon)
   {
      String annotationType="", errorMessage="";
      
      try
      {
         assignDefaultValues(databaseConfigFile, projectId, frlCon);
      }
      catch(Exception e1)
      {
         errorMessage = e1.getMessage();
         //JOptionPane.showMessageDialog(this, errorMessage, "Assigning the Default Values", JOptionPane.ERROR_MESSAGE);
         printErrorMessage(errorMessage, "Assigning the Default Values");
         
      }
      
      try
      {
	     disableFields();
      }
      catch(Exception e2)
      {
         errorMessage = e2.getMessage();
         //JOptionPane.showMessageDialog(this, errorMessage, "Assigning the Default Values", JOptionPane.ERROR_MESSAGE);
         printErrorMessage(errorMessage, "Disabling Fields");
      }
	  
      try
      {
	     // Get the current annotation Type
	     annotationType = annotationTypeGroup.getSelection().getActionCommand();
      }
      catch(Exception e3)
      {
         errorMessage = e3.getMessage();
         //JOptionPane.showMessageDialog(this, errorMessage, "Assigning the Default Values", JOptionPane.ERROR_MESSAGE);
         printErrorMessage(errorMessage, "Getting the Annotation Type");
      }
	  
      try
      {
	     enableDisableFields(annotationType);
      }
      catch(Exception e4)
      {
         errorMessage = e4.getMessage();
         printErrorMessage(errorMessage, "Enabling Fields");
         //JOptionPane.showMessageDialog(this, errorMessage, "Assigning the Default Values", JOptionPane.ERROR_MESSAGE);	
      }
      
   }
   
   public void enableDisableFields(String annotationType)
   {
      Color color;
	  boolean flag;
	   
	  if(annotationType.equals("Method"))
	  {
	      flag  = false;
		  color = Color.LIGHT_GRAY; 
		  
	      parameterNameCombo.setEnabled(flag);
	      parameterNameCombo.setFocusable(flag);
	      parameterNameCombo.setBackground(color);
	      
	      attributeNameCombo.setEnabled(flag);
	      attributeNameCombo.setFocusable(flag);
	      attributeNameCombo.setBackground(color);
	      
	      comparison2Combo.setEnabled(flag);
	      comparison2Combo.setFocusable(flag);
	      comparison2Combo.setBackground(color);
	      
	      valueNameCombo.setEnabled(flag);
	      valueNameCombo.setFocusable(flag);
	      valueNameCombo.setBackground(color);
	      
	      /*
	      comparison3Combo.setEnabled(flag);
	      comparison3Combo.setFocusable(flag);
	      comparison3Combo.setBackground(color);
	      */
	      
	      comparison1Combo.setEnabled(flag);
	      comparison1Combo.setFocusable(flag);
	      comparison1Combo.setBackground(color);
	      
	      returnTypeValueCombo.setEnabled(flag);
	      returnTypeValueCombo.setFocusable(flag);
	      returnTypeValueCombo.setBackground(color);
	      
	      valueTextField1.setEnabled(flag);
	      valueTextField1.setFocusable(flag);
	      valueTextField1.setBackground(color);
	      valueTextField1.setText("");
	      	      
	   }
	   else 
	      if(annotationType.equals("Parameter"))
	      {
		     flag  = true;
			 color = Color.GRAY; 
		     parameterNameCombo.setEnabled(flag);
		     parameterNameCombo.setFocusable(flag);
		     parameterNameCombo.setBackground(color);
		      
		     attributeNameCombo.setEnabled(flag);
		     attributeNameCombo.setFocusable(flag);
		     attributeNameCombo.setBackground(color);
		     
		     valueNameCombo.setEnabled(flag);
		     valueNameCombo.setFocusable(flag);
		     valueNameCombo.setBackground(color);
		     
		     valueTextField1.setEnabled(flag);
		     valueTextField1.setFocusable(flag);
		     valueTextField1.setBackground(color);
		     valueTextField1.setText("");
		      
		     /*
		     comparison3Combo.setEnabled(flag);
		     comparison3Combo.setFocusable(flag);
		     comparison3Combo.setBackground(color);
		     */
		     		     
		      
			 flag = false;
			 color = Color.LIGHT_GRAY; 
			 
		     comparison1Combo.setEnabled(flag);
		     comparison1Combo.setFocusable(flag);
		     comparison1Combo.setBackground(color);
		     
		     comparison2Combo.setEnabled(flag);
		     comparison2Combo.setFocusable(flag);
		     comparison2Combo.setBackground(color);
			  
      
		     returnTypeValueCombo.setEnabled(flag);
		     returnTypeValueCombo.setFocusable(flag);
		     returnTypeValueCombo.setBackground(color);
		      
	      }
	      else 
	         if(annotationType.equals("Return Type"))
	         {
		        flag  = false;
				color = Color.LIGHT_GRAY; 
			    parameterNameCombo.setEnabled(flag);
			    parameterNameCombo.setFocusable(flag);
			    parameterNameCombo.setBackground(color);
			      
			    attributeNameCombo.setEnabled(flag);
			    attributeNameCombo.setFocusable(flag);
			    attributeNameCombo.setBackground(color);
			      
			    comparison2Combo.setEnabled(flag);
			    comparison2Combo.setFocusable(flag);
			    comparison2Combo.setBackground(color);
			      
			    valueNameCombo.setEnabled(flag);
			    valueNameCombo.setFocusable(flag);
			    valueNameCombo.setBackground(color);
			    
			    
			    /*  
			    comparison3Combo.setEnabled(flag);
			    comparison3Combo.setFocusable(flag);
			    comparison3Combo.setBackground(color);
			    */
			    
			      
				flag = true;
				color = Color.GRAY; 
				  
			    comparison1Combo.setEnabled(flag);
			    comparison1Combo.setFocusable(flag);
			    comparison1Combo.setBackground(color);
			      
			    returnTypeValueCombo.setEnabled(flag);
			    returnTypeValueCombo.setFocusable(flag);
			    returnTypeValueCombo.setBackground(color);
			    
			    valueTextField1.setEnabled(flag);
			    valueTextField1.setFocusable(flag);
			    valueTextField1.setBackground(color);
			    valueTextField1.setText("");
			    
            }
   }
   
   void attributeCombos(String dataType)
   {
      Color color;
	  boolean flag;
		  
	  if(dataType.equals("Enum"))
	  {
	     flag = true;
		 color = Color.GRAY; 
			  
		 comparison1Combo.setEnabled(flag);
		 comparison1Combo.setFocusable(flag);
		 comparison1Combo.setBackground(color);  
		 
	     flag = false;
	     color = Color.LIGHT_GRAY; 
			  
		 comparison2Combo.setEnabled(flag);
		 comparison2Combo.setFocusable(flag);
		 comparison2Combo.setBackground(color);  
	  }
	  else
	  {
	     flag = false;
		 color = Color.LIGHT_GRAY; 
				  
		 comparison1Combo.setEnabled(flag);
		 comparison1Combo.setFocusable(flag);
		 comparison1Combo.setBackground(color);  
			 
		 flag = true;
		 color = Color.GRAY; 
				  
		 comparison2Combo.setEnabled(flag);
		 comparison2Combo.setFocusable(flag);
		 comparison2Combo.setBackground(color);  		  
	  }
   }
   
   void enableComparison2 ()
   {
      Color color;
	  boolean flag;	 
	  
	  flag = true;
	  color = Color.GRAY; 
				  
	  comparison2Combo.setEnabled(flag);
	  comparison2Combo.setFocusable(flag);
	  comparison2Combo.setBackground(color);  	 
   }
   
   void printErrorMessage(String errorMessage, String title)
   {
      // Validate if the errorMessage is not empty                                                              
	  if(errorMessage != null && !errorMessage.isEmpty())                                                                                                                      
	  { 
	     JOptionPane.showMessageDialog(AnnotateUMLSequenceDiagramFormPanel.this, errorMessage, title, JOptionPane.ERROR_MESSAGE);
      }
   }

}