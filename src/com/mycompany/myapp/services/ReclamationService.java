/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;


import com.mycompany.myapp.entities.Message;
import com.mycompany.myapp.entities.Utilisateurs;
import com.mycompany.myapp.entities.reclamation;


import com.mycompany.myapp.utils.DataSource;
import com.mycompany.myapp.utils.Statics;

import com.codename1.io.CharArrayReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author rania
 */
public class ReclamationService {
    private ConnectionRequest request;
	public boolean resultOK;

    private boolean responseResult;
    public ArrayList<reclamation> reclamations;
    
    public boolean resultOkl=true;
    private ConnectionRequest req;
    public static ReclamationService instance;

    
    
    public ReclamationService() {
		request = DataSource.getInstance().getRequest();
	}
    
    public ArrayList<reclamation> parseReclamation(String jsonText) throws ParseException  {
        try {
            reclamations = new ArrayList<>();
            reclamation p = new reclamation();
            JSONParser jp = new JSONParser();
            
           //String s = "["+jsonText+"]";
            System.out.println(jsonText);
            Map<String, Object> ProjectListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ProjectListJson.get("root");
            for (Map<String, Object> obj : list) {
              
		int id = (int)Float.parseFloat(obj.get("idReclamation").toString());
                String objet=obj.get("objet").toString();
   
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String _date=obj.get("date").toString();
                Date date = formatter.parse(_date); 
               
              

                reclamations.add(new reclamation(id, null, objet, null, date));
            }


        } catch (IOException ex) {
			
        }

        return reclamations;
    }
    
    public ArrayList<reclamation> getAllReclamations() {
        String url = Statics.BASE_URL+"reclamation/getReclamations";
		System.out.println(url);
		
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
               
				try {
					reclamations = parseReclamation(new String(request.getResponseData()));
					
					request.removeResponseListener(this);
				} catch (ParseException ex) {
					//Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
				}
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return reclamations;
    }
    
    public String  find_(String a){
			if(!a.substring(0,1).equals("[")){
                a = "["+a+"]";
			}
        return a;
    }
    
    public void addReclamation(String objet, String msg) {
       //try {
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"reclamation/addReclamations");
            //cr.setPost(false);
            cr.addArgument("objet", objet);
			cr.addArgument("msg", msg);
			
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Ajouter","Reclamation ajouté " , "OK",null);

                   // envoyerMail("wassim.benafia@esprit.tn", "New Reclamtion ", p.getType()+" "+p.getDescription());
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
            
        /*   }
        catch (ParseException e1) {
         e1.printStackTrace();
         }*/
    }
	
	public boolean deleteReclamation(int id) {
       //try {
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"reclamation/deleteReclamation/"+id);
            //cr.setPost(false);
			
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Supprimer","Reclamation Supprimer " , "OK",null);

                   // envoyerMail("wassim.benafia@esprit.tn", "New Reclamtion ", p.getType()+" "+p.getDescription());
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
            return true;
        /*   }
        catch (ParseException e1) {
         e1.printStackTrace();
         }*/
    }
    
    
}
