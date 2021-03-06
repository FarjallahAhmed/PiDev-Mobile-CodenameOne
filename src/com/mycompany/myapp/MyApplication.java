package com.mycompany.myapp;


import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
//import com.mycompany.myapp.gui.CalendarForm;
import com.mycompany.myapp.gui.ForgetPassword;
import com.mycompany.myapp.gui.InscriptionUser;
import com.mycompany.myapp.gui.PanierForm;
import com.mycompany.myapp.gui.ProfileFormateurForm;
import com.mycompany.myapp.gui.ProfileParticipantForm;
import com.mycompany.myapp.gui.TrendingForm;
import com.mycompany.myapp.gui.UserForm;
import com.mycompany.myapp.gui.UserInscription;
import com.mycompany.myapp.gui.loginUser;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication {

    private Form current;
    private Resources theme;
    
    
    private Resources themeProfile;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

      //  theme = UIManager.initFirstTheme("/theme");
       
        
        
         
        

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });       
        
        
         theme = UIManager.initNamedTheme("/theme", "Theme");  // TEMPLATE THEME
        // themeProfile = UIManager.initNamedTheme("/theme", "Theme1"); // 2EME THEME POUR PROFILE + LOGIN
    }
    
    public void start() {
    //  new InscriptionUser().show();
    // new UserInscription(themeProfile).show();
    //new ProfileParticipantForm(themeProfile).show();
  //  new ProfileFormateurForm(themeProfile).show();
  // new ForgetPassword(theme).show();
     // new loginUser(theme).show();

    // new CalendarForm(theme).show();

     //new CalendarForm(theme).show();
     //new PanierForm(theme).show();

    //  new UserForm().show();
    new TrendingForm(theme).show();
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
