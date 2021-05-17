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
import com.mycompany.myapp.entities.Comment;
import com.mycompany.myapp.entities.Workshop;
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
 * @author dell
 */
public class ServiceEvent {
    
    public ArrayList<Workshop> events;
    public Workshop event;
    public static ServiceEvent instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private ServiceEvent(){
        req = new ConnectionRequest();
    }
    
    public static ServiceEvent getInstance(){
        if(instance == null){
            instance = new ServiceEvent();
        }
        return instance;
    }
    
    public boolean addEvent(Workshop w){
        String url = Statics.BASE_URL +"APIEvent/addEvent?"
                    +"nomevent=" +w.getNomEvent() + "&namecalendar="
                    + w.getNomCalendar() + "&datedebut="
                    + w.getDatedebut() + "&datefin="
                    + w.getDatefin() + "&hdebut="
                    + w.gethDebut() + "&hfin="
                    + w.gethFin() + "&description="
                    + w.getDescription() + "&lieu="
                    + w.getLieu() + "&type="
                    + w.getType() + "&prix="
                    + w.getPrix() + "&nbparticipant="
                    + w.getNbParticipant();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    public boolean addComment(Comment c){
        String url = Statics.BASE_URL +"APIEvent/addComment?" 
                    +"authorName=" +c.getAuthorName()+ "&content="
                    + c.getContent() + "&workshop="
                    + c.getWorkshop() ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
        public Workshop parseOneEventJson(String jsonText){
        try {
            event = new Workshop();
            JSONParser j = new JSONParser();
            JSONParser j2 = new JSONParser();
            
            Map<String,Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
    
                
                Workshop w = new Workshop();
               
               ArrayList<Comment> ac = new ArrayList<>();
                
                w.setId(((int)Float.parseFloat(eventsListJson.get("id").toString())));
                w.setNomCalendar(eventsListJson.get("namecalendar").toString());
                w.setNomEvent(eventsListJson.get("nomevent").toString());
                
                String pattern = "yyyy-mm-dd";
                String pattern3 = "yyyy-MM-dd'T'hh:mm:ss";
/*                 
                 */
                 
                 List<Map<String,Object>> list = (List<Map<String,Object>>)eventsListJson.get("comments");
            
            for (Map<String,Object>obj : list){
                Comment c = new Comment();
                c.setId(((int)Float.parseFloat(obj.get("id").toString())));
                c.setContent(obj.get("content").toString());
                 c.setAuthorName(obj.get("authorName").toString());
                 ac.add(c);
                 
            }
                
               System.out.println(ac);
                 w.setComment(ac);
                  
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm");  

                //System.out.println(obj.get("hfin").toString());
                Date date1=new SimpleDateFormat(pattern).parse(eventsListJson.get("datedebut").toString()); 
                String datedebut = formatter.format(date1);  
                
                Date date2=new SimpleDateFormat(pattern3).parse(eventsListJson.get("hfin").toString());
                
                String hfin = formatter1.format(date2);
                
                Date date3=new SimpleDateFormat(pattern).parse(eventsListJson.get("datefin").toString());
                String datefin = formatter.format(date3); 
                
                Date date4=new SimpleDateFormat(pattern3).parse(eventsListJson.get("hdebut").toString()); 
                String hdebut = formatter1.format(date4); 
                
                
                w.setDatedebut(datedebut);
                w.setDatefin(datefin);
                w.sethDebut(hdebut);
                w.sethFin(hfin);
                w.setLieu(eventsListJson.get("lieu").toString());
                w.setType(eventsListJson.get("type").toString());
                w.setDescription(eventsListJson.get("description").toString());
                w.setPrix(((int)Float.parseFloat(eventsListJson.get("prix").toString())));
                event =w;

           
            
        } catch (IOException ex) {
        } catch (ParseException ex) {
        }
        return event;
        
    }
    
    public ArrayList<Workshop> parseEventJson(String jsonText){
        
        try {
            events = new ArrayList<>();
            JSONParser j = new JSONParser();
            
            Map<String,Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            
            List<Map<String,Object>> list = (List<Map<String,Object>>) eventsListJson.get("root");
            
            for (Map<String,Object>obj : list){
                
                Workshop w = new Workshop();
                
                w.setId(((int)Float.parseFloat(obj.get("id").toString())));
                w.setNomCalendar(obj.get("namecalendar").toString());
                w.setNomEvent(obj.get("nomevent").toString());
                
                String pattern = "yyyy-mm-dd";
                String pattern3 = "yyyy-MM-dd'T'hh:mm:ss";
                
                 
                
                  
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm");  

                //System.out.println(obj.get("hfin").toString());
                Date date1=new SimpleDateFormat(pattern).parse(obj.get("datedebut").toString()); 
                String datedebut = formatter.format(date1);  
                
                Date date2=new SimpleDateFormat(pattern3).parse(obj.get("hfin").toString());
                
                String hfin = formatter1.format(date2);
                
                Date date3=new SimpleDateFormat(pattern).parse(obj.get("datefin").toString());
                String datefin = formatter.format(date3); 
                
                Date date4=new SimpleDateFormat(pattern3).parse(obj.get("hdebut").toString()); 
                String hdebut = formatter1.format(date4); 
                
                
                w.setDatedebut(datedebut);
                w.setDatefin(datefin);
                w.sethDebut(hdebut);
                w.sethFin(hfin);
                w.setLieu(obj.get("lieu").toString());
                w.setType(obj.get("type").toString());
                w.setDescription(obj.get("description").toString());
                w.setPrix(((int)Float.parseFloat(obj.get("prix").toString())));
                
                events.add(w);
            }
           
            
        } catch (IOException ex) {
        } catch (ParseException ex) {
        }
        return events;
        
    }
    
    public ArrayList<Workshop> getAllEvent(){
        
        String url = Statics.BASE_URL+"APIEvent/AllEvent/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEventJson(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }
    
    public Workshop getEvent(String id){
        
        String url = Statics.BASE_URL+"APIEvent/getEvent/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(req.getResponseData());
                event = parseOneEventJson(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return event;
    }
    
    public boolean deletEvent(String id){
        
        String url = Statics.BASE_URL+"APIEvent/deleteEvent/"+id;
        
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);  
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        
        return resultOK;
    }
    
    
}
