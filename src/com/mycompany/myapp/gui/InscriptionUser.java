/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.File;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.spinner.Picker;

import javafx.scene.control.DatePicker;

/**
 *
 * @author Mehdi
 */
public class InscriptionUser extends Form {
    
     public InscriptionUser() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public InscriptionUser(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
    //    getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("SignInForm");
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    
 
    
    private com.codename1.ui.TextField nom = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField prenom = new com.codename1.ui.TextField();
    
    private Picker date = new Picker();
    private com.codename1.ui.TextField cin = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField email = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField login = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField pwd = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField num = new com.codename1.ui.TextField();
    
    
    private ComboBox type = new ComboBox("Participant","Formateur");
    
    
    private ComboBox niveau = new ComboBox("Etudiant","Eleve");
    private com.codename1.ui.TextField about = new com.codename1.ui.TextField();
    
    
    FileChooser justificatif = new FileChooser();
    
    
    private com.codename1.ui.Button AddFile = new com.codename1.ui.Button();
    private com.codename1.ui.TextField specialite = new com.codename1.ui.TextField();
    
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_Button_2.addActionListener(callback);
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if(sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == gui_Button_2) {
                onButton_2ActionEvent(ev);
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Sign In");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
      
        gui_Component_Group_1.addComponent(nom);
       
        gui_Component_Group_1.addComponent(prenom);
        gui_Component_Group_1.addComponent(date);
        gui_Component_Group_1.addComponent(cin);
        gui_Component_Group_1.addComponent(email);
        gui_Component_Group_1.addComponent(login);
        gui_Component_Group_1.addComponent(pwd);
        gui_Component_Group_1.addComponent(num);
        gui_Component_Group_1.addComponent(type);
        gui_Component_Group_1.addComponent(niveau);
        gui_Component_Group_1.addComponent(about);
        
     
      gui_Component_Group_1.addComponent(AddFile);
      gui_Component_Group_1.addComponent(specialite);
       

        niveau.setVisible(false);
        about.setVisible(false);
        
        
       AddFile.setVisible(false);
        specialite.setVisible(false);
        
        
        //gui_Component_Group_1.addComponent(gui_Text_Field_1);
        
        // TEXT FIELDS 
       // nom.setText("Nom");
        nom.setHint("First Name");
        nom.setName("Nom");
        
        //prenom.setText("Prenom");
        prenom.setHint("Last Name");
        prenom.setName("Prenom");
        
      //  prenom.setText("Cin");
        cin.setHint("Cin");
        cin.setName("Cin");
       
        email.setHint("Email");
        email.setName("Email");
        
         login.setHint("UserName");
         login.setName("Login");
         
         pwd.setHint("Password");
         pwd.setName("Pwd");
         
          num.setHint("Number");
          num.setName("Num");
          
          about.setHint("About");
          about.setName("About");
          
          AddFile.setText("Justificatif");
          
          specialite.setText("Specialite");
          
          
          ActionListener callback = e->{
                if (e != null && e.getSource() != null) {
                    String filePath = (String)e.getSource();
                    File f = new File(filePath);
                    System.out.println("XXXXXXXXXXXXXx"+filePath);
                     System.out.println(f.getAbsoluteFile());
                    System.out.println("***"+f.getName());
                    System.out.println("**zz*"+f.getPath());
                     
                    //  Now do something with this file
                }
             };
          
          AddFile.addActionListener((evt) -> {
              
            
              
               if (FileChooser.isAvailable()) {
            FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);
                
            } else {
                Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
            }
              
          });
           

          
          
          
          type.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if (type.getSelectedItem().toString().equals("Participant"))
                       {
                           niveau.setVisible(true);
                            about.setVisible(true); 
                             AddFile.setVisible(false);
                             specialite.setVisible(false);
                          
                            
                       }
               
               if (type.getSelectedItem().toString().equals("Formateur"))
                       {
                           niveau.setVisible(false);
                           about.setVisible(false); 
                            AddFile.setVisible(true);
                              specialite.setVisible(true);
                       }
            }
          });
          
          
          
        
        
        
        
        
        gui_Container_1.addComponent(gui_Button_2);
        gui_Container_1.addComponent(gui_Button_3);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        
        // BUTTONS 
        
        gui_Button_2.setText("Sign Up");
        gui_Button_2.setName("Button_2");
        gui_Button_3.setText("Forgot Your Password");
        gui_Button_3.setUIID("CenterLabelSmall");
        gui_Button_3.setName("Button_3");
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Button_1.setText("Create New Account");
        gui_Button_1.setUIID("CenterLabel");
        gui_Button_1.setName("Button_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
      //  new InboxForm().show();
    }

    
}
