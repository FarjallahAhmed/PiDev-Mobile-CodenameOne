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
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *GUI builder created Form
 * @author User
 */
public class FinaliserForm extends BaseForm{

    public FinaliserForm() {
        //this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
   public  FinaliserForm(BaseForm previous,com.codename1.ui.util.Resources resourceObjectInstance){
           setTitle("checkout");
           BaseForm f =  new BaseForm();
             f.addComponent(new Label("ID"));
        final TextField id = new TextField();
        f.addComponent(id);

        f.addComponent(new Label("Name"));
        final TextField name = new TextField();
        f.addComponent(name);

        f.addComponent(new Label("email"));
        final TextField email = new TextField();
        f.addComponent(email);
        f.addComponent(new Label("Num Card"));
        final TextField numcard = new TextField();
        f.addComponent(numcard);

        final Picker date = new Picker();
        f.addComponent(date);
        
       final Button passer=new Button("passer");
       f.addComponent(passer);
       
       Stripe.apiKey="sk_test_UyGOFESdBdhmN6TtUwAY3Knn00fbOUdEE4";
       passer.addActionListener((ActionEvent e)->{
           String idd=id.getText();
       String nom=name.getText();
       String mail=email.getText();
       String num=numcard.getText();
       float prixtotal=0;
       for(Commande c:com.mycompany.myapp.services.ServiceCommande.getInstance().getallcommandes()){
           prixtotal+=c.getPrix();
       }
       Customer c=new Customer();
            Map<String,Object> chargeparam=new HashMap<String,Object>();
             Map<String, Object> retrieveParams =
                    new HashMap<>();
              List<String> expandList = new ArrayList<>();
                    expandList.add("sources");
                    retrieveParams.put("expand", expandList);
               try {
                   c =
                           c.retrieve(idd, retrieveParams, null);
                    Card card =
                    (Card) c.getSources().retrieve(c.getDefaultSource());
                      if( card.getLast4().equals(numcard.getText().substring(numcard.getText().length()-4))&& date.getValue().toString().contains(card.getExpYear().toString())/*&& date.getValue().toString().contains(card.getExpMonth().toString())*/&& card.getName().equals(nom)&&c.getEmail().equals(mail)){
                    chargeparam.put("amount",String.valueOf((int)Math.round(prixtotal*100*0.36)));
                 chargeparam.put("currency","usd");
                 chargeparam.put("customer",c.getId());
                 Charge.create(chargeparam);
                 com.mycompany.myapp.services.ServiceCommande.getInstance().valider(1);
                Dialog.show("Success","paiement effectue",new Command("OK"));
                 new TrendingForm(resourceObjectInstance).show();
               }else
                          Dialog.show("ERROR","verifier les champs",new Command("OK"));
               
               } catch (StripeException ex) {
                   System.out.println(ex.getMessage());
               }
               
           
           
       });
       
        add(f);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> {new PanierForm(resourceObjectInstance).show(); });
    }
    
    
 
    
     
}
