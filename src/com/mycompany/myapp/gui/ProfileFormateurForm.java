/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.myapp.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Formateurs;
import com.mycompany.myapp.services.UserServices;
import com.mycompany.myapp.userSession.UserSession;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileFormateurForm extends BaseFormProfile {

    private Formateurs f = new Formateurs();
    
    public ProfileFormateurForm(Resources res) {

        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
       f=UserServices.getInstance().getFormateurById(UserSession.getInstace("",0,"").getId());
        
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label fullname = new Label(f.getNom() + " " + f.getPrenom(), "BottomPad");
        Label bdate = new Label(f.getDateNaissance().toString(), "BottomPad");
        
         
         
        fullname.setTextPosition(BOTTOM);
        bdate.setTextPosition(BOTTOM);
        
         
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            fullname,
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                            bdate
                    )
                )
        ));

        TextField username = new TextField(f.getLogin());
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        TextField email = new TextField(f.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
        TextField password = new TextField(f.getPassword(), "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
        
        
        TextField number = new TextField(f.getNum(), "E-Mail", 20, TextField.ANY);
        number.setUIID("TextFieldBlack");
        addStringValue("num", number);
        
        TextField specialite = new TextField(f.getSpecialite(), "Speciality", 20, TextField.ANY);
        specialite.setUIID("TextFieldBlack");
        addStringValue("spec", specialite);
        
        

        CheckBox cb1 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb1.setUIID("Label");
        cb1.setPressedIcon(res.getImage("on-off-on.png"));
        CheckBox cb2 = CheckBox.createToggle(res.getImage("on-off-off.png"));
        cb2.setUIID("Label");
        cb2.setPressedIcon(res.getImage("on-off-on.png"));
        
        addStringValue("Facebook", FlowLayout.encloseRightMiddle(cb1));
        addStringValue("Twitter", FlowLayout.encloseRightMiddle(cb2));
        
        Button save = new Button("Save");
        addStringValue("", save);
        
        Button desactiver = new Button("Desactivate Account");
        addStringValue("", desactiver);
     
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if (!f.getLogin().equals(username.getText()))
                   f.setLogin(username.getText());
               if (!f.getEmail().equals(email.getText()))
                   f.setEmail(email.getText());
               if (!f.getNum().equals(number.getText()))
                   f.setNum(number.getText());
               if (!f.getSpecialite().equals(specialite.getText()))
                   f.setSpecialite(specialite.getText());
               if (!f.getPassword().equals(password.getText()))
                   f.setPassword(password.getText());
               
               if( UserServices.getInstance().ModifierFormateur(f))
                            Dialog.show("Success","Done",new Command("OK"));
              
         //     f = UserServices.getInstance().getFormateurByEmail("modif");
           //     System.out.println(f);
               
            }
        });
        
        
        desactiver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                if( UserServices.getInstance().DesactiverFormateur(f))
                            Dialog.show("Success","Account Desactivated",new Command("OK"));
               
            }
        });
        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
