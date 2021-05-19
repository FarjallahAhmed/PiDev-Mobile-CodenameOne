/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import  com.mycompany.myapp.entities.Formations;
import  com.mycompany.myapp.entities.Evaluation;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.services.ServiceCommande;
import com.mycompany.myapp.services.ServiceFormations;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 *
 * @author AMINE N
 */
public class detailsformation extends BaseForm implements LocalNotificationCallback{
   // private Resources theme;
    //private EncodedImage placeHolder;
     private com.codename1.ui.TextField Description = new com.codename1.ui.TextField();
  private int id;
    public detailsformation(Form previous,Formations h,com.codename1.ui.util.Resources resourceObjectInstance)
    {
        setTitle("Detail Formations");
        BaseForm f =  new BaseForm();      
        ImageViewer imavu;
        id=h.getId();
//        try {
//        imavu = new ImageViewer(getImageFromServer(h.getPath()));
//        }
//        catch(Exception e) {
//       // System.out.println(h.getImage());
//       
//        }
        imavu = new ImageViewer(resourceObjectInstance.getImage(h.getImage_name()));
        Label ta = new Label("Object :"+h.getObjet()+"\n");
        Label ta2 = new Label("Categorie : \n"+h.getCategorie());
        Label ta3 = new Label("Objectif :"+h.getObjectif());
        Label ta4 = new Label("Prix  :"+h.getCout_fin());
        Label ta5 = new Label("nombre de Participants :"+h.getNb_participants());
        Label lReserver = new Label("Reserver");
          com.codename1.ui.Button gui_Button_2 = new com.codename1.ui.Button();
          gui_Button_2.setText("passer commande");
        f.addAll(imavu,ta,ta2,ta3,ta4,gui_Button_2);
        gui_Button_2.addActionListener(l->{
            ServiceCommande.getInstance().ajoutercommande(h.getId(),1);
        });
        Container hi= new Container( new BoxLayout(BoxLayout.Y_AXIS));
        
        hi.add(createRankWidget("Ajouter une evaluation ", "Slide"));
        f.add(hi);
           ShareButton sb = new ShareButton();
            sb.setText("Share this course");
            f.add(sb);
            Image screenshot = Image.createImage(f.getWidth(), f.getHeight());
            f.revalidate();
            f.setVisible(true);
            f.paintComponent(screenshot.getGraphics(), true);
            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
} catch(IOException err) {
    //Log.e(err);
}
        sb.setImageToShare(imageFile, "image/png");
        add(f);
         addStringValue("", Description);
         Description.setHint("Description");
         Description.setVisible(false);
     
        
        
        
        
//        ArrayList<Promotion> list = ServicePromotion.getInstance().promotionofhotel(h.getId());
//        for (Promotion p : list) {
//            addColumn(p,h,previous);
//        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    public SwipeableContainer createRankWidget(String title, String year) {
    MultiButton button = new MultiButton(title);
    button.setTextLine2(year); 
     
      
       
    SwipeableContainer spp= new SwipeableContainer(FlowLayout.encloseCenterMiddle(createStarRankSlider()), 
            button);
    
   // spp.addDragOverListener(l->{System.out.println("aaa tahlet");});
    spp.addSwipeOpenListener(l->{System.out.println("aaa tahlet");
                                Description.setVisible(true);  });
    
    return  spp;
}
    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}
private Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
    Font fnt = Font.createTrueTypeFont("native:MainLight", "native:MainLight").
            derive(Display.getInstance().convertToPixels(5, true), Font.STYLE_PLAIN);
   
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
    TextField ss = new TextField();
    Evaluation x=new Evaluation();
    starRank.addActionListener(e->{
        x.setNote(starRank.getProgress());
        x.setDescription(Description.getText());
        Formations f=new Formations();
        f.setId(id);
        x.setIdFormations(f);
      ServiceFormations.getInstance().ajouterevaluation(x);
        System.out.println(starRank.getProgress());
       Dialog.show("Success","Evaluation ajouter",new Command("OK")); 
        LocalNotification n = new LocalNotification();
        int badgeNumber=0;
                n.setAlertBody("aaa");
                n.setAlertTitle("aaa");
                n.setId("5");
                n.setBadgeNumber(badgeNumber++);

                int repeatType = LocalNotification.REPEAT_HOUR;
                Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis() + 10 * 1000, repeatType);
    });
   
    return starRank;
}
private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        
    }
public void localNotificationReceived(String notificationId) {
        System.out.println("Received local notification "+notificationId+" in callback localNotificationReceived");
    }

}
