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
import com.mycompany.myapp.entities.Formateurs;
import com.mycompany.myapp.entities.Participants;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Mehdi
 */
public class UserServices {
    
    
    public ArrayList<Participants> participant;
    public ArrayList<Formateurs> formateur;
    public Participants p = new Participants();
    public Formateurs f = new Formateurs();
    public static UserServices instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
   private  String verif = new String();

    private UserServices() {
         req = new ConnectionRequest();
    }

    public static UserServices getInstance() {
        if (instance == null) {
            instance = new UserServices();
        }
        return instance;
    }
    
    public boolean addParticipant(Participants p) {
        
        
        
        
        String url = Statics.BASE_URL+"addParticipantsJSON?nom="+ p.getNom()+"&prenom="+p.getPrenom()+
                "&date="+p.getDateNaissance()+"&cin="+p.getCin()+"&email="+p.getEmail()+"&login="+p.getLogin()+
                "&pwd="+p.getPassword()+"&num="+p.getNum()+"&cero="+p.getCertificatsObtenus()+"&about="+p.getInterssePar()+
                "&niveau="+p.getNiveauEtude()+"&nbfor="+p.getNombreDeFormation(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
               
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    public boolean addFormateur(Formateurs f) {
        
        
        
        
        String url = Statics.BASE_URL+"addFormateursJSON?nom="+ f.getNom()+"&prenom="+f.getPrenom()+
                "&date="+f.getDateNaissance()+"&cin="+f.getCin()+"&email="+f.getEmail()+"&login="+f.getLogin()+
                "&pwd="+f.getPassword()+"&num="+f.getNum()+"&spec="+f.getSpecialite()+"&just="+f.getJustificatif()+
                "&etat="+f.getEtat(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
     public ArrayList<Participants> parseParticipants(String jsonText){
        try {
            participant=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
            Map<String,Object> participantListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
         
            //System.out.println("OUI"+participantListJson.get("root")); 
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)participantListJson.get("root");
           
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Participants t = new Participants();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date d;
            try {
                   d = formatter.parse(obj.get("datenaissance").toString().substring(0, 10));
                    t.setDateNaissance(d);
                        System.out.println(t.getDateNaissance());
                     
               } catch (ParseException ex) {
            
               }
                t.setCin(obj.get("cin").toString());
                t.setEmail(obj.get("email").toString());
                 t.setLogin(obj.get("login").toString());
                 t.setPassword(obj.get("pwd").toString());
                t.setNum(obj.get("num").toString());
                if (obj.get("image")!=null)
                 t.setImage(obj.get("image").toString());
               
                t.setNiveauEtude(obj.get("niveauetude").toString());
                float certif = Float.parseFloat(obj.get("certificatsobtenus").toString());
                t.setCertificatsObtenus((int)certif);
               
                t.setInterssePar(obj.get("interessepar").toString());
                
                float nb = Float.parseFloat(obj.get("nombredeformation").toString());
                t.setNombreDeFormation((int)nb);
                
                
              
               
                //Ajouter la tâche extraite de la réponse Json à la liste
                participant.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return participant;
    }
     
     
     
     public Participants parseOneParticipants(String jsonText){
           Participants t = new Participants();
        
           try {
            
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
           
            Map<String,Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
         
            
              
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
           
                
                
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date d;
            try {
                   d = formatter.parse(obj.get("datenaissance").toString().substring(0, 10));
                    t.setDateNaissance(d);
                        System.out.println(t.getDateNaissance());
                     
               } catch (ParseException ex) {
            
               }
               
           
               
                //String DateConverter = obj.get("datenaissance").toString().substring(obj.get("datenaissance").toString().indexOf("timestamp")+10,obj.get("datenaissance").toString().lastIndexOf(")"));
                //Date currentdate = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                //System.out.println("eeee"+currentdate);
               
                t.setCin(obj.get("cin").toString());
                t.setEmail(obj.get("email").toString());
                 t.setLogin(obj.get("login").toString());
                 t.setPassword(obj.get("pwd").toString());
                t.setNum(obj.get("num").toString());
                if (obj.get("image")!=null)
                 t.setImage(obj.get("image").toString());
               
                t.setNiveauEtude(obj.get("niveauetude").toString());
                float certif = Float.parseFloat(obj.get("certificatsobtenus").toString());
                t.setCertificatsObtenus((int)certif);
               
                t.setInterssePar(obj.get("interessepar").toString());
                
                float nb = Float.parseFloat(obj.get("nombredeformation").toString());
                t.setNombreDeFormation((int)nb);
                
                
              
              
                //Ajouter la tâche extraite de la réponse Json à la liste
               
            
            
            
        } catch (IOException ex) {
            
        }
        
        return t;
    }
     
      public ArrayList<Participants> getAllParticipants(){
        String url = Statics.BASE_URL+"getParticipantsJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                participant = parseParticipants(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return participant;
    }
    
    public Participants getParticipantById(int id){
        
        String url = Statics.BASE_URL+"getParticipantsByIdJSON/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                p = parseOneParticipants(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return p;
    }  
    
    
    
    public ArrayList<Formateurs> parseFormateurs(String jsonText){
        try {
            formateur=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            
            Map<String,Object> formateurListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
         
            
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)formateurListJson.get("root");
           
            
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Formateurs t = new Formateurs();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date d;
            try {
                   d = formatter.parse(obj.get("datenaissance").toString().substring(0, 10));
                    t.setDateNaissance(d);
                        System.out.println(t.getDateNaissance());
                     
               } catch (ParseException ex) {
            
               }
                
                
                t.setCin(obj.get("cin").toString());
                t.setEmail(obj.get("email").toString());
                 t.setLogin(obj.get("login").toString());
                 t.setPassword(obj.get("pwd").toString());
                t.setNum(obj.get("num").toString());
                if (obj.get("image")!=null)
                 t.setImage(obj.get("image").toString());
               
                
                t.setSpecialite(obj.get("specialite").toString());
                t.setJustificatif(obj.get("justificatif").toString());
                if (obj.get("etat").toString().equals("true"))
                t.setEtat(true);
                if (obj.get("etat").toString().equals("false"))
                t.setEtat(false);
                
               
                
                
               formateur.add(t);
                
              
               
               
            }
            
            
        } catch (IOException ex) {
            
        }
        
        return formateur;
    }
    
    
    public Formateurs parseOneFormateurs(String jsonText){
           Formateurs t = new Formateurs();
        
           try {
            
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
           
            Map<String,Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
         
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                t.setPrenom(obj.get("prenom").toString());
               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date d;
            try {
                   d = formatter.parse(obj.get("datenaissance").toString().substring(0, 10));
                    t.setDateNaissance(d);
                        System.out.println(t.getDateNaissance());
                     
               } catch (ParseException ex) {
            
               }
                t.setCin(obj.get("cin").toString());
                t.setEmail(obj.get("email").toString());
                 t.setLogin(obj.get("login").toString());
                 t.setPassword(obj.get("pwd").toString());
                t.setNum(obj.get("num").toString());
                if (obj.get("image")!=null)
                 t.setImage(obj.get("image").toString());
                
                t.setSpecialite(obj.get("specialite").toString());
                t.setJustificatif(obj.get("justificatif").toString());
                if (obj.get("etat").toString().equals("true"))
                t.setEtat(true);
                if (obj.get("etat").toString().equals("false"))
                t.setEtat(false);
               
                
                
               
              
                //Ajouter la tâche extraite de la réponse Json à la liste
               
            
            
            
        } catch (IOException ex) {
            
        }
        
        return t;
    }
    
     public ArrayList<Formateurs> getAllFormateurs(){
        String url = Statics.BASE_URL+"getFormateursJSON";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                formateur = parseFormateurs(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return formateur;
    }
     
     
     public Formateurs getFormateurById(int id){
        
        String url = Statics.BASE_URL+"getFormateursByIdJSON/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                f = parseOneFormateurs(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return f;
    }  
     
     
     public boolean ModifierParticipant(Participants p) {
        
        
        
        
        String url = Statics.BASE_URL+"modifierParticipantsJSON/"+p.getId()+"?nom="+ p.getNom()+"&prenom="+p.getPrenom()+
                "&date="+p.getDateNaissance()+"&cin="+p.getCin()+"&email="+p.getEmail()+"&login="+p.getLogin()+
                "&pwd="+p.getPassword()+"&num="+p.getNum()+"&cero="+p.getCertificatsObtenus()+"&about="+p.getInterssePar()+
                "&niveau="+p.getNiveauEtude()+"&nbfor="+p.getNombreDeFormation(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
               
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     public boolean ModifierFormateur(Formateurs f) {
        
        
        
        
        String url = Statics.BASE_URL+"modifierFormateursJSON/"+f.getId()+"?nom="+ f.getNom()+"&prenom="+f.getPrenom()+
                "&date="+f.getDateNaissance()+"&cin="+f.getCin()+"&email="+f.getEmail()+"&login="+f.getLogin()+
                "&pwd="+f.getPassword()+"&num="+f.getNum()+"&spec="+f.getSpecialite()+"&just="+f.getJustificatif()+
                "&etat="+f.getEtat(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     public boolean DesactiverFormateur(Formateurs f) {
        
        
        
        
        String url = Statics.BASE_URL+"desactiverFormateursJSON/"+f.getId(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
      public Formateurs getFormateurByEmail(String email){
        
        String url = Statics.BASE_URL+"getFormateursByEmailJSON?email="+email;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                formateur = parseFormateurs(new String(req.getResponseData()));
            
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return formateur.get(0);
    }  
      
      
     
     
      public Participants getParticipantByEmail(String email){
        
        String url = Statics.BASE_URL+"getParticipantsByEmailJSON?email="+email;
        req.setUrl(url);
        req.setPost(false);
       
     
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
           
             participant = parseParticipants(new String(req.getResponseData()));
            
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
         
          
        return participant.get(0);
    }  
      
      
      
      public String verifierUser(String email,String password){
        
        String url = Statics.BASE_URL+"verifierUserJSON?email="+email+"&password="+password;
        req.setUrl(url);
        req.setPost(false);
        
       
     
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
           
              JSONParser j = new JSONParser();
              String json = new String (new String(req.getResponseData())+"");
              verif = json;
              
            
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
         
          
        return verif;
    }  
      
      
      
      public String sendEmail(String email,String number){
        
        String url = Statics.BASE_URL+"sendEmail?email="+email+"&number="+number;
        req.setUrl(url);
        req.setPost(false);
        
       
     
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
           
              JSONParser j = new JSONParser();
              String json = new String (new String(req.getResponseData())+"");
              verif = json;
              
            
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
         
          
        return verif;
    }  
      
      
      
      
      public boolean ChangePassword(String email,String password) {

        String url = Statics.BASE_URL+"modifierPassword?email="+email+"&password="+password; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      
      
      
}


 
      
      
      


