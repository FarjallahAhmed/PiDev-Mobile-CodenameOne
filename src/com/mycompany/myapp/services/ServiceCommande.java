/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Commande;
import com.mycompany.myapp.entities.Formations;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class ServiceCommande {
     public boolean resultOkl=true;
    public ArrayList<Formations> formations;
    private ConnectionRequest req;
    public static ServiceCommande instance;

    public ServiceCommande() {
         req= new ConnectionRequest();
    }
    public static  ServiceCommande getInstance(){
          if(instance==null)
           instance=new ServiceCommande();
          return instance;
          
}
    public void ajoutercommande(int id,int idclient){
    
        String url=Statics.BASE_URL+"ajoutercommandejson?id="+id+"&idClient="+idclient;
        req.setUrl(url);
        req.addResponseListener(e->{
            String str=new String(req.getResponseData());
            System.out.println("data=="+str);
        
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
     public ArrayList<Commande> getallcommandes()
  {
      ArrayList<Commande> commandes=new ArrayList<>();
      String url=Statics.BASE_URL+"displayJSON";
      req.setUrl(url);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
          @Override
          public void actionPerformed(NetworkEvent evt) {
              JSONParser json;
              json=new JSONParser();
              try {
                  Map<String,Object>mapformations=json.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                  List<Map<String,Object>> listofmaps=(List<Map<String,Object>>) mapformations.get("root");
                  System.out.println(listofmaps.size());
                  for(Map<String,Object> obj:listofmaps)
                  {
                      Commande c=new Commande();
                      Formations f=new Formations();
                      float id=Float.parseFloat(obj.get("id").toString());
                        float prix=Float.parseFloat(obj.get("prix").toString());
                      String objet=obj.get("objet").toString();
                       String type=obj.get("type").toString();
                      
                       
                        
                       c.setId((int)id);
                       c.setPrix(prix);
                       f.setObjet(objet);
                       f.setType(type);
                       c.setF(f);
                       commandes.add(c);
                      
                  }
                  
              } catch (Exception e) {
              }
          }
      });
      NetworkManager.getInstance().addToQueueAndWait(req);
      System.out.println("gggggggggggggg");
      System.out.println(commandes.size());
      return commandes;
  }
     public boolean supprimercommande(int id,int idclient){
     
        String url=Statics.BASE_URL+"supprimercommandeJSON?id="+id+"&idClient="+idclient;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOkl;
     }
     public void viderpanier(int idclient){
         String url=Statics.BASE_URL+"viderpanierJSON?idClient="+idclient;
        req.setUrl(url);
        req.addResponseListener(e->{
            String str=new String(req.getResponseData());
            System.out.println("data=="+str);
        
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
     }
    public void valider(int id){
        String url=Statics.BASE_URL+"validerJSON?idClient="+id;
        req.setUrl(url);
        req.addResponseListener(e->{
            String str=new String(req.getResponseData());
            System.out.println("data=="+str);
        
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
}
