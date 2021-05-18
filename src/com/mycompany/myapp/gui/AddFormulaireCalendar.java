/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Workshop;
import com.mycompany.myapp.services.ServiceEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author dell
 */
public class AddFormulaireCalendar extends com.codename1.ui.Form {
    
    
    public AddFormulaireCalendar() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public AddFormulaireCalendar(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("Add Event");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "Add Event", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("Add Event");
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField gui_nomEvent = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_nameCalendar = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_lieu = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_nbparticipant = new com.codename1.ui.TextField();

    private com.codename1.ui.ComboBox<String> gui_type = new com.codename1.ui.ComboBox<>(
            "Choose Type",
            "Team building",
            "Soft Skills",
            "Conference",
            "Seminaire"
    );
    private com.codename1.ui.TextField gui_description = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField gui_prix = new com.codename1.ui.TextField();
    private Picker gui_dateDebut = new Picker();
    private Picker gui_dateFin = new Picker();
    private Picker gui_hDebut = new Picker(); 
    private Picker gui_hFin = new Picker();
    private com.codename1.ui.Button gui_AddEvent = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        gui_AddEvent.addActionListener(callback);
    }


    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
             
        }

        public EventCallbackClass() {
            new  CalendarForm().show();
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
             
            if(sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
                 
            }

            if(sourceComponent == gui_AddEvent) {
                if((gui_nomEvent.getText().length()==0)
                    || (gui_nameCalendar.getText().length()==0)
                    || (gui_lieu.getText().length()==0)
                    || (gui_description.getText().length()==0)
                    || (gui_lieu.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    Workshop w = new Workshop(gui_nameCalendar.getText(),
                            gui_nomEvent.getText(),
                            gui_dateDebut.getDate().toString(),
                            gui_dateFin.getDate().toString(),
                            gui_hDebut.getText(),
                            gui_hFin.getText(),
                            gui_lieu.getText(),
                            Integer.parseInt(gui_nbparticipant.getText()),
                            gui_type.getSelectedItem(),
                            gui_description.getText(),
                            Integer.parseInt(gui_prix.getText()));
                    if(ServiceEvent.getInstance().addEvent(w)){
                        Dialog.show("Success", "Event Added", new Command("OK"));
                        
   
      
                        onButton_2ActionEvent(ev);
                    }
                    else
                        Dialog.show("Erreur", "Verifier Event ", new Command("OK"));

                        
                }
                
                
                
            }
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Add Event");
        setName("Add Event");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_hDebut.setType(Display.PICKER_TYPE_TIME);
        gui_hFin.setType(Display.PICKER_TYPE_TIME);
        gui_Component_Group_1.addComponent(gui_nomEvent);
        gui_Component_Group_1.addComponent(gui_nameCalendar);
        gui_Component_Group_1.addComponent(gui_dateDebut);
        gui_Component_Group_1.addComponent(gui_dateFin);
        gui_Component_Group_1.addComponent(gui_hFin);
        gui_Component_Group_1.addComponent(gui_hDebut);
        gui_Component_Group_1.addComponent(gui_description);
        gui_Component_Group_1.addComponent(gui_type);
        gui_Component_Group_1.addComponent(gui_prix);
        gui_Component_Group_1.addComponent(gui_lieu);
        gui_Component_Group_1.addComponent(gui_nbparticipant);
        gui_nomEvent.setText("Event Name");
        //gui_nomEvent.setName("Event Name");
        gui_nameCalendar.setText("Name Calendar");
        //gui_nameCalendar.setName("Name Calendar");
        gui_dateDebut.setText("Start Date");
        //gui_dateDebut.setName("date D ");
        gui_dateFin.setText("End Date");
        //gui_dateFin.setName("date F");
        gui_hDebut.setText("Start Time");
        gui_hFin.setText("End Time");
        
        gui_lieu.setText("lieu");
        //gui_lieu.setName("lieu");
        gui_description.setText("description");
        //gui_description.setName("description");
        gui_prix.setText("prix");
        gui_nbparticipant.setText("nb ");
        //gui_prix.setName("prix");
        gui_Container_1.addComponent(gui_AddEvent);
        gui_Container_1.addComponent(gui_Button_3);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("profile_image.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        gui_AddEvent.setText("Add");
        gui_AddEvent.setName("Button_2");
        //gui_Button_3.setText("Forgot Your Password");
        //gui_Button_3.setUIID("CenterLabelSmall");
        //gui_Button_3.setName("Button_3");
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Button_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        //gui_Button_1.setText("Create New Account");
        //gui_Button_1.setUIID("CenterLabel");
        //gui_Button_1.setName("Button_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public void onButton_2ActionEvent(com.codename1.ui.events.ActionEvent ev) {
        new  CalendarForm().show();
    }

    
}
