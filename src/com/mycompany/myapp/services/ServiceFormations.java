/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Evaluation;
import com.mycompany.myapp.entities.Formations;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author AMINE N
 */
public class ServiceFormations {
    public boolean resultOkl;
    public ArrayList<Formations> formations;
    private ConnectionRequest req;
    public static ServiceFormations instance;
    
    
   private  ServiceFormations()
{
    req= new ConnectionRequest();
}
   public static ServiceFormations getInstance()
   {
       if(instance==null)
           instance=new ServiceFormations();
       return instance;
   }
  public ArrayList<Formations> getallformations()
  {
      ArrayList<Formations> formation=new ArrayList<>();
      String url=Statics.BASE_URL+"allformationsjson";
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
                  int i=0;
                  for(Map<String,Object> obj:listofmaps)
                  {
                      
                      Formations f=new Formations();
                      float id=Float.parseFloat(obj.get("id").toString());
               
                      String objet=obj.get("objet").toString();
                      String objectif=obj.get("objectif").toString();
                       String type=obj.get("type").toString();
                        String categorie=obj.get("categorie").toString();
                        String image_name=obj.get("imageName").toString();
                        float prix=Float.parseFloat(obj.get("coutFin").toString());
                        
                       f.setId((int)id);
                       f.setObjet(objet);
                       f.setImage_name(image_name);
                       f.setObjectif(objectif);
                       f.setType(type);
                       f.setCategorie(categorie);
                       f.setCout_fin(prix);
                       formation.add(f);
                       i++;
                       System.out.println("ciompteiur");
                       System.out.println(i);
                      
                  }
                  
              } catch (Exception e) {
              }
          }
      });
      NetworkManager.getInstance().addToQueueAndWait(req);
      return formation;
  }
  public void ajouterevaluation(Evaluation e){

        String url=Statics.BASE_URL+"Ajouterevaluationjson?id="+e.getIdFormations().getId()+"&note="+e.getNote()+"&rapport="+e.getDescription();
        req.setUrl(url);
        req.addResponseListener(x->{
            String str=new String(req.getResponseData());
            System.out.println("data=="+str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public ArrayList<Evaluation> getallevaluations()
  {
      ArrayList<Evaluation> formation=new ArrayList<>();
      String url=Statics.BASE_URL+"recommendedjson";
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
                  int i=0;
                  for(Map<String,Object> obj:listofmaps)
                  {
                      
                      Formations f=new Formations();
                      Evaluation e=new Evaluation();
                      float id=Float.parseFloat(obj.get("id").toString());
               
                      String objet=obj.get("objet").toString();
                      String objectif=obj.get("objectif").toString();
                       String type=obj.get("type").toString();
                        String categorie=obj.get("categorie").toString();
                        String image_name=obj.get("imageName").toString();
                        float prix=Float.parseFloat(obj.get("coutFin").toString());
                        float moyenne=Float.parseFloat(obj.get("moy").toString());
                        
                       f.setId((int)id);
                       f.setObjet(objet);
                       f.setImage_name(image_name);
                       f.setObjectif(objectif);
                       f.setType(type);
                       f.setCategorie(categorie);
                       f.setCout_fin(prix);
                        e.setMoyenne(moyenne);
                        e.setIdFormations(f);
                       formation.add(e);
                       i++;
                       System.out.println("ciompteiur");
                       System.out.println(i);
                      
                  }
                  
              } catch (Exception e) {
              }
          }
      });
      NetworkManager.getInstance().addToQueueAndWait(req);
      return formation;
  }
}
