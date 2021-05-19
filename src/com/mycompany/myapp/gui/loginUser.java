/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.AccessToken;
import com.codename1.io.Preferences;
import com.codename1.io.Storage;
import com.codename1.social.FacebookConnect;
import com.codename1.social.GoogleConnect;
import com.codename1.social.Login;
import com.codename1.social.LoginCallback;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Formateurs;
import com.mycompany.myapp.entities.Participants;
import com.mycompany.myapp.services.UserServices;
import com.mycompany.myapp.userSession.UserSession;

/**
 *
 * @author Mehdi
 */
public class loginUser extends Form {
    
    private Resources resT;
    private Login login;
    private String tokenPrefix;
    
    public loginUser() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public loginUser(com.codename1.ui.util.Resources resourceObjectInstance) {
        resourceObjectInstance = UIManager.initNamedTheme("/theme", "Theme");
        initGuiBuilderComponents(resourceObjectInstance);
        
       
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
       // getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("SignInForm");
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_Text_Field_2 = new com.codename1.ui.TextField("","Email");
    private com.codename1.ui.TextField gui_Text_Field_1 = new com.codename1.ui.TextField("","Password",20,TextField.PASSWORD);
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private ComboBox typeAccount = new ComboBox("Participant","Formateur");
    private Button t = new Button("google");


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
        
        
        gui_Component_Group_1.addComponent(gui_Text_Field_2);
        gui_Component_Group_1.addComponent(gui_Text_Field_1);
        gui_Component_Group_1.addComponent(typeAccount);
        
        
       
        gui_Text_Field_2.setName("Text_Field_2");
        
        
        
      
        gui_Text_Field_1.setName("Text_Field_1");
        
        
        gui_Container_1.addComponent(gui_Button_2);
        gui_Container_1.addComponent(gui_Button_3);
        gui_Container_1.addComponent(t);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Button_2.setText("Sign In");
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
        
        
         gui_Button_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                resT = UIManager.initNamedTheme("/theme", "Theme1");
              new ForgetPassword(resT).show();
              
            }
        });
        
        gui_Button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                resT = UIManager.initNamedTheme("/theme", "Theme1");
              new UserInscription(resT).show();
              
            }
        });
        
        
        t.addActionListener(new ActionListener() {
            
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                
                
                String clientId = "215780774506-dq9ovdnnvppc4clnktbmvhqnmtlui72f.apps.googleusercontent.com";
                String redirectURI = "https://www.codenameone.com/oauth2callback";                
                String clientSecret = "JM9RGS3CyalVEMEP9Ia4mNAk";
                
                if(clientSecret.length() == 0){
                    System.err.println("create your own google project follow the guide here:");
                    System.err.println("http://www.codenameone.com/google-login.html");
                    return;
                }
                
                Login gc = GoogleConnect.getInstance();
                gc.setClientId(clientId);
                gc.setRedirectURI(redirectURI);
                gc.setClientSecret(clientSecret);
                login = gc;
                        gc.setCallback(new LoginCallback() {
                                    @Override
                                    public void loginFailed(String errorMessage) {
                                        System.out.println(errorMessage);  
                                    }

                                    @Override
                                    public void loginSuccessful() {
                                        
                                        System.out.println("CV");
                                    }
    
                });
                if(!gc.isUserLoggedIn()){
                    System.out.println("Makech connecte");
                    gc.doLogin();
                   
                        
                }else{
                   
                    String Test = gc.getAccessToken().getToken();
                    System.out.println("RAK CONNECTEEEEEEEEE"+Test);
                }
                
               

            }
                
                
        });
    }// </editor-fold>

    
    
   
//-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
     //   new InboxForm().show();
     //   resT = UIManager.initNamedTheme("/theme", "Theme1");
       // new ProfileFormateurForm(resT).show();
       
       
     /*
        
        if (typeAccount.getSelectedItem().toString()=="Participant")
        {
          if(UserServices.getInstance().verifierUser(gui_Text_Field_2.getText(),gui_Text_Field_1.getText()).equals("Ok"))
          {
              Participants p = new Participants();
              p = UserServices.getInstance().getParticipantByEmail(gui_Text_Field_2.getText());
              UserSession.getInstace(p.getLogin(),p.getId(),"Participant");
              resT = UIManager.initNamedTheme("/theme", "Theme1");
              new ProfileParticipantForm(resT).show();
              
          }
          else 
               if(UserServices.getInstance().verifierUser(gui_Text_Field_2.getText(),gui_Text_Field_1.getText()).equals("Wrong"))
          {
              Dialog.show("Error","Wrong Information",new Command("OK"));
          }
          else
               {
                   Dialog.show("Error","Not Found",new Command("OK"));
               }
              
        }
        
        if (typeAccount.getSelectedItem().toString()=="Formateur")
        {
             if(UserServices.getInstance().verifierUser(gui_Text_Field_2.getText(),gui_Text_Field_1.getText()).equals("Ok"))
          {
              Formateurs f = new Formateurs();
              f = UserServices.getInstance().getFormateurByEmail(gui_Text_Field_2.getText());
              if (f.getEtat()==true)
              {
              
              UserSession.getInstace(f.getLogin(),f.getId(),"Formateur");
              resT = UIManager.initNamedTheme("/theme", "Theme1");
              new ProfileFormateurForm(resT).show();
              }
              else
              {
                   Dialog.show("Error","Account is not Activated",new Command("OK"));
              }
              
              
          }
          else 
               if(UserServices.getInstance().verifierUser(gui_Text_Field_2.getText(),gui_Text_Field_1.getText()).equals("Wrong"))
          {
              Dialog.show("Error","Wrong Information",new Command("OK"));
          }
          else
               {
                   Dialog.show("Error","Not Found",new Command("OK"));
               }
        }

*/
     
           // facebookLogout();
     
                String clientId = "606421700756191";
                String redirectURI = "http://www.codenameone.com";
                String clientSecret = "90531fef98fecacd82b2321129ee6093";
                Login fb = FacebookConnect.getInstance();
                fb.setClientId(clientId);
                fb.setRedirectURI(redirectURI);
                fb.setClientSecret(clientSecret);
                //Sets a LoginCallback listener
                fb.setCallback(new LoginCallback() {
                    
                                @Override
                                public void loginFailed(String errorMessage) {
                                    
                                    System.out.println("FAILED"); //To change body of generated methods, choose Tools | Templates.
                                    Storage.getInstance().writeObject("token", "");
                                    System.out.println("zdazd"+errorMessage);
                                    
                                }

                                @Override
                                public void loginSuccessful() {
                                    
                                    
                                    System.out.println("YESS");//To change body of generated methods, choose Tools | Templates.
                                    String token = fb.getAccessToken().getToken();
                                    Storage.getInstance().writeObject("token", token);
                                    UserSession.getInstace("FACEBOOK",121,"FACEBOOK");
                                    resT = UIManager.initNamedTheme("/theme", "Theme1");
                                    new ProfileParticipantForm(resT).show();
                                   
                                }
  
                });
     
     
                //trigger the login if not already logged in
                if(!fb.isUserLoggedIn()){
                    {
                    fb.doLogin();
                    System.out.println(" NOT LOGGED ");
                    
                   
           
                    //String token = (String) Storage.getInstance().readObject("token");
                        //System.out.println(token);
                    }
                }else
                {
                    //get the token and now you can query the facebook API
                    
                    System.out.println("YESS");//To change body of generated methods, choose Tools | Templates.
                    String token = fb.getAccessToken().getToken(); 
                    Storage.getInstance().writeObject("token", token);
                    
                }
     

    


    }
    
    
    

    
}
