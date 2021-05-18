/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.services.ServiceCommande;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.stripe.Stripe;



/**
 *GUI builder created Form
 * @author User
 */
public class PanierForm extends BaseForm{
    BaseForm current;
    public PanierForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }

     public PanierForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        current=this;
        initGuiBuilderComponents(resourceObjectInstance);
         gui_separator1.setShowEvenIfBlank(true);
        gui_Label_1_1_1.setShowEvenIfBlank(true);
        
        ScaleImageLabel sl = new ScaleImageLabel(resourceObjectInstance.getImage("skate-park.jpg"));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        gui_imageContainer1.add(BorderLayout.CENTER, sl);
        sl = new ScaleImageLabel(resourceObjectInstance.getImage("bridge.jpg"));
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        gui_imageContainer2.add(BorderLayout.CENTER, sl);
        
        installSidemenu(resourceObjectInstance);
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_PUBLIC, e -> {});
        
        FontImage.setMaterialIcon(gui_LA, FontImage.MATERIAL_LOCATION_ON);
        gui_LA.setIconPosition(BorderLayout.EAST);

        FontImage.setMaterialIcon(gui_newYork, FontImage.MATERIAL_LOCATION_ON);
        gui_newYork.setIconPosition(BorderLayout.EAST);
        
        gui_Text_Area_2.setRows(2);
        gui_Text_Area_2.setColumns(100);
        gui_Text_Area_2.setGrowByContent(false);
        gui_Text_Area_2.setEditable(false);
        gui_Text_Area_1.setRows(2);
        gui_Text_Area_1.setColumns(100);
        gui_Text_Area_1.setGrowByContent(false);
        gui_Text_Area_1.setEditable(false);
    }
    
    
    //-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
    private com.codename1.components.MultiButton gui_LA = new com.codename1.components.MultiButton();
    private com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_separator1 = new com.codename1.ui.Label();
    private com.codename1.ui.Container gui_null_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.components.MultiButton gui_null_1_1_1 = new com.codename1.components.MultiButton();
    private com.codename1.components.MultiButton gui_newYork = new com.codename1.components.MultiButton();
    private com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    private com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
    private com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
    private com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();
   private com.codename1.ui.Button gui_Button_b = new com.codename1.ui.Button(); 

// <editor-fold defaultstate="collapsed" desc="Generated Code">  

  private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
       setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));

 
    getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Panier", "Title"),
                        new Label(String.valueOf(com.mycompany.myapp.services.ServiceCommande.getInstance().getallcommandes().size()), "InboxNumber")
                )
        );
        
        for(Commande c:com.mycompany.myapp.services.ServiceCommande.getInstance().getallcommandes()){
              com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
             com.codename1.components.MultiButton gui_Multi_Button_1 = new com.codename1.components.MultiButton();
            com.codename1.components.MultiButton gui_LA = new com.codename1.components.MultiButton();
     com.codename1.ui.Container gui_imageContainer1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
     com.codename1.ui.Container gui_Container_2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    com.codename1.ui.TextArea gui_Text_Area_1 = new com.codename1.ui.TextArea();
    com.codename1.ui.Button gui_Button_1 = new com.codename1.ui.Button();
     com.codename1.ui.Label gui_separator1 = new com.codename1.ui.Label();
     com.codename1.ui.Container gui_null_1_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
     com.codename1.components.MultiButton gui_null_1_1_1 = new com.codename1.components.MultiButton();
     com.codename1.components.MultiButton gui_newYork = new com.codename1.components.MultiButton();
     com.codename1.ui.Container gui_imageContainer2 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
     com.codename1.ui.Container gui_Container_3 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());
    com.codename1.ui.TextArea gui_Text_Area_2 = new com.codename1.ui.TextArea();
     com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
     com.codename1.ui.Label gui_Label_1_1_1 = new com.codename1.ui.Label();
     

//        addComponent(gui_Container_1);
//        gui_Container_1.setName("Container_1");
//        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2);
//        gui_Container_2.setName("Container_2");
//        gui_Container_2.addComponent(gui_Label_1);
//        gui_Label_1.setText("11:31 AM");
//        gui_Label_1.setUIID("SmallFontLabel");
//        gui_Label_1.setName("Label_1");
//        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.WEST, gui_Container_4);
//        gui_Container_4.setName("Container_4");
//        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
//        gui_Container_4.addComponent(gui_Label_4);
//        gui_Label_4.setUIID("Padding2");
//        gui_Label_4.setName("Label_4");
//        gui_Label_4.setIcon(resourceObjectInstance.getImage("label_round.png"));
//        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_3);
//        gui_Container_3.setName("Container_3");
//        gui_Container_3.addComponent(gui_Label_3);
//        gui_Container_3.addComponent(gui_Label_2);
//        gui_Container_3.addComponent(gui_Text_Area_1);
//
//        gui_Label_3.setText(c.getF().getObjet());
//        gui_Label_3.setName("Label_3");
//        gui_Label_2.setText(String.valueOf(c.getPrix()));
//        gui_Label_2.setUIID("RedLabel");
//        gui_Label_2.setName("Label_2");
//        gui_Text_Area_1.setText(c.getF().getType());
//        gui_Text_Area_1.setUIID("SmallFontLabel");
//        gui_Text_Area_1.setName("Text_Area_1");
//      
//        gui_Container_2.setName("Container_2");
//        gui_Container_4.setName("Container_4");
//        ((com.codename1.ui.layouts.FlowLayout)gui_Container_4.getLayout()).setAlign(com.codename1.ui.Component.CENTER);
//        gui_Container_3.setName("Container_3");
//        addComponent(gui_Label_6);
//         
//        addComponent(gui_Container_1_1);
//        gui_Container_1_1.setName("Container_1_1");
//      
//       
//        gui_Container_1_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Container_2_1);
//        gui_Container_2_1.setName("Container_2_1");
//        gui_Container_2_1.addComponent(gui_Label_1_1);
            
         addComponent(gui_Container_1);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Multi_Button_1);
        gui_Container_1.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_LA);
        gui_Multi_Button_1.setUIID("Label");
        gui_Multi_Button_1.setName("Multi_Button_1");
       
        gui_Multi_Button_1.setPropertyValue("line1", "Objet");
        gui_Multi_Button_1.setPropertyValue("line2", "Prix");
        gui_Multi_Button_1.setPropertyValue("uiid1", "Label");
        gui_Multi_Button_1.setPropertyValue("uiid2", "RedLabel");
        gui_LA.setUIID("Label");
        gui_LA.setName("LA");
        gui_LA.setPropertyValue("line1", c.getF().getObjet());
        gui_LA.setPropertyValue("line2",String.valueOf(c.getPrix()));
        gui_LA.setPropertyValue("uiid1", "SlightlySmallerFontLabel");
        gui_LA.setPropertyValue("uiid2", "RedLabelRight");
        addComponent(gui_imageContainer1);
        gui_imageContainer1.setName("imageContainer1");
        gui_imageContainer1.addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, gui_Container_2);
        gui_Container_2.setName("Container_2");
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Text_Area_1);
        gui_Container_2.addComponent(com.codename1.ui.layouts.BorderLayout.EAST, gui_Button_1);
        gui_Text_Area_1.setText(c.getF().getType());
        gui_Text_Area_1.setUIID("SlightlySmallerFontLabelLeft");
        gui_Text_Area_1.setName("Text_Area_1");
        gui_Button_1.setText("");
        gui_Button_1.setUIID("Label");
        gui_Button_1.setName("Button_1");
        gui_Button_1.addActionListener(e->{
            Dialog dig=new Dialog("Suppression");
            if(dig.show("Suppression","êtes vous sûr de supprimer cette commande?","Annuler","oui")){
                dig.dispose();
            }else{
                dig.dispose();
                if(ServiceCommande.getInstance().supprimercommande(c.getId(),1)){
                    new PanierForm(resourceObjectInstance).show(); 
                }
            }
            
           
          
        });
         
        com.codename1.ui.FontImage.setMaterialIcon(gui_Button_1,com.codename1.ui.FontImage.MATERIAL_DELETE);
        
      
        }
       Button vider = new Button("viderpanier");
        addStringValue("", vider); 
      vider.addActionListener(e->{ServiceCommande.getInstance().viderpanier(1);
            new PanierForm(resourceObjectInstance).show();
      });
       Button checkout = new Button("checkout");
        addStringValue("", checkout); 
      checkout.addActionListener(e->{
            new FinaliserForm(current,resourceObjectInstance).show();
      });
      
  }
 private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        
    }
  
      @Override
    protected boolean isCurrentPanier() {
        return true;
    }
}
