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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Participants;
import com.mycompany.myapp.services.UserServices;
import com.mycompany.myapp.userSession.UserSession;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileParticipantForm extends BaseFormProfile{
    
    private Participants p = new Participants();
    Resources Ress ;

    public ProfileParticipantForm(Resources res) {
       super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
   
  // Ress = UIManager.initNamedTheme("/theme", "Theme");
   //installSidemenu(Ress);
        
        tb.addSearchCommand(e -> {});
        
        
       p = UserServices.getInstance().getParticipantById(UserSession.getInstace("",0,"").getId());
        
        
        Image img = res.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label fullname = new Label(p.getNom()+" "+p.getPrenom(), "BottomPad");
        Label bdate = new Label(p.getDateNaissance().toString(), "BottomPad");
        
         
         
        fullname.setTextPosition(BOTTOM);
        bdate.setTextPosition(BOTTOM);
        
          Label t = new Label(res.getImage("profile-pic.jpg"),"PictureWhiteBackgrond");
         Button b = new Button(res.getImage("profile-pic.jpg"),"PictureWhiteBackgrond");
         b.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
               System.out.println("XXX");
           }
         });
         
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            fullname,
                            FlowLayout.encloseCenter(
                                b),
                            bdate
                    )
                )
        ));

        TextField username = new TextField(p.getLogin());
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        TextField email = new TextField(p.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
        TextField password = new TextField(p.getPassword(), "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
        
        
        TextField number = new TextField(p.getNum(), "Number", 20, TextField.ANY);
        number.setUIID("TextFieldBlack");
        addStringValue("num", number);
        
        
        TextField about = new TextField(p.getInterssePar(), "About", 20, TextField.ANY);
        about.setUIID("TextFieldBlack");
        addStringValue("about", about);
        System.out.println("zz"+p.getInterssePar());
        
        
        

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
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if (!p.getLogin().equals(username.getText()))
                   p.setLogin(username.getText());
               if (!p.getEmail().equals(email.getText()))
                   p.setEmail(email.getText());
               if (!p.getNum().equals(number.getText()))
                   p.setNum(number.getText());
               if (!p.getInterssePar().equals(about.getText()))
                   p.setInterssePar(about.getText());
               if (!p.getPassword().equals(password.getText()))
                   p.setPassword(password.getText());
               
               if( UserServices.getInstance().ModifierParticipant(p))
                            Dialog.show("Success","Done",new Command("OK"));
               //Ress = UIManager.initNamedTheme("/theme", "Theme");
                //   new loginUser(Ress).show();
               
               
            }
        });
        
        
        
  
        
        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
