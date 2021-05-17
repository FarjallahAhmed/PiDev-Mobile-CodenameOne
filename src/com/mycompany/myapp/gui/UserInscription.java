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

import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Formateurs;
import com.mycompany.myapp.entities.Participants;
import com.mycompany.myapp.services.UserServices;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class UserInscription extends BaseFormProfile {

    public UserInscription(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> new loginUser().show());
        setUIID("SignIn");
                
        TextField nom = new TextField("", "First Name", 20, TextField.ANY);
        TextField prenom = new TextField("", "Last Name", 20, TextField.ANY);
        Picker date = new Picker();
        TextField cin = new TextField("", "CIN", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField login = new TextField("", "UserName", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField num = new TextField("", "Number", 20, TextField.ANY);
        
        
        ComboBox type = new ComboBox("Participant","Formateur");
        
        
        ComboBox niveau = new ComboBox("Etudiant","Eleve");
        TextField about = new TextField("", "About", 20, TextField.ANY);
        
        
        
        TextField specialite = new TextField("", "Specialite", 20, TextField.ANY);
        
        
        
        
        
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        Button AddFile = new Button("Add Justificatif");
        
        
        
        
        nom.setSingleLineTextArea(false);
        login.setSingleLineTextArea(false);
        cin.setSingleLineTextArea(false);
        
        
        about.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        num.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
         specialite.setSingleLineTextArea(false);
        Button next = new Button("Sign Up");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(prenom),
                createLineSeparator(),
                
                date,
                createLineSeparator(),
                new FloatingHint(cin),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(login),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(num),
                createLineSeparator(),
                type,
                createLineSeparator(),
                niveau,
                createLineSeparator(),
                about,
                createLineSeparator(),
                AddFile,
                createLineSeparator(),
                specialite,
                createLineSeparator()
                        
        );
        
        ActionListener callback = e->{
                if (e != null && e.getSource() != null) {
                    String filePath = (String)e.getSource();
                    File f = new File(filePath);
                    System.out.println(f.getName());
                    
                    
                 InputStream stream;
                    try {
                        stream = FileSystemStorage.getInstance().openInputStream(filePath);
                        
                       
                        OutputStream out = Storage.getInstance().createOutputStream("C:\\Users\\Mehdi\\Desktop\\uploadProjet");
                       
                        Util.copy(stream, out);
                        
                        Util.cleanup(stream);
                        Util.cleanup(out);
                    } catch (IOException ex) {
                        System.out.println("Error");
                       
                    }   

                }
                             
                
             };
          
          AddFile.addActionListener((evt) -> {
              
            
              
               if (FileChooser.isAvailable()) {
                      FileChooser.showOpenDialog(".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg", callback);
            
                
            } else {
                       Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
               
            }
              
          });
       
       /*
       
       AddFile.addActionListener((evt)-> {
           
           
            String path = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            if (path!=null)
            {
                try {
                    Image im = Image.createImage(path);
                    System.out.println(im.getImageName());
                } catch (IOException ex) {
                    
                }
            }
       
       
       
       });*/
        
        
        
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
        about.setVisible(false);
        niveau.setVisible(false);
        AddFile.setVisible(false);
        specialite.setVisible(false);
        
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
      //  next.addActionListener(e -> new ActivateForm(res).show());
      
      next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (type.getSelectedItem().toString().equals("Participant"))
                {
                    Participants p = new Participants();
                    
                    p.setNom(nom.getText());
                    p.setPrenom(prenom.getText());
                    p.setDateNaissance(date.getDate());
                    p.setCin(cin.getText());
                    p.setEmail(email.getText());
                    p.setLogin(login.getText());
                    p.setPassword(password.getText());
                    p.setNum(num.getText());
                    
                    p.setCertificatsObtenus(0);
                    p.setInterssePar(about.getText());
                    p.setNiveauEtude(niveau.getSelectedItem().toString());
                    p.setNombreDeFormation(0);
                    
                    
                    
                        if( UserServices.getInstance().addParticipant(p))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                    
                    
                    
                   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String format = formatter.format(p.getDateNaissance());
                    System.out.println("xxxxxxxx"+format);
                    
                }
                else if (type.getSelectedItem().toString().equals("Formateur"))
                {
                    
                    Formateurs f = new Formateurs();
                    
                    f.setNom(nom.getText());
                    f.setPrenom(prenom.getText());
                    f.setDateNaissance(date.getDate());
                    f.setCin(cin.getText());
                    f.setEmail(email.getText());
                    f.setLogin(login.getText());
                    f.setPassword(password.getText());
                    f.setNum(num.getText());
                    
                    f.setEtat(false);
                    f.setJustificatif("hbjhj.xlsx"); // FILE UPLOAD
                    f.setSpecialite(specialite.getText());
                    
                    if( UserServices.getInstance().addFormateur(f))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                    
                }
                        
            }
      });
      
      
    }
    
    
    
}
