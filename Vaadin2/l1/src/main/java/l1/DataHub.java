
//package com.example.vaadin;
package l1;

import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.Serializable;

public class DataHub implements Serializable{

    ComApp comapp1;
    Window w;
    Database db;
    static List<Label> allGrps = new ArrayList<Label>();
    static List<Label> allUsrs = new ArrayList<Label>();
    static List<Label> allRls = new ArrayList<Label>();
    HashMap<String, HashMap<String, String>> grpD = new HashMap<String, HashMap<String, String>>();
    HashMap<String, HashMap<String, String>> usrD = new HashMap<String, HashMap<String, String>>();
    HashMap<String, HashMap<String, String>> rlD = new HashMap<String, HashMap<String, String>>();
    HashMap<String, List<String>> grpUsrs = new HashMap<String, List<String>>();
    HashMap<String, List<String>> grpRls = new HashMap<String, List<String>>();
    HashMap<String, List<String>> usrGrps = new HashMap<String, List<String>>();
    HashMap<String, List<String>> rlGrps = new HashMap<String, List<String>>();

// getters and setters for the lists...
    public DataHub(ComApp app,Window w) {
        db=new Database();
        this.w=w;
	this.comapp1 = app;
        allGrps.removeAll(allGrps);
        allUsrs.removeAll(allUsrs);
        allRls.removeAll(allRls);
        grpD.clear();
        usrD.clear();
        rlD.clear();
        grpUsrs.clear();
        grpRls.clear();
        usrGrps.clear();
        rlGrps.clear();

        this.updateAllGrps();
        this.updateAllUsrs();
        this.updateAllRls();

     
    }

    public List<Label> getGrpUsr(String grpname1) {
        List<String> tempusr = (this.grpUsrs).get(grpname1);
        List<Label> returnL = new ArrayList<Label>();
        for (String s : tempusr) {
            returnL.add(new Label(s));

        }
        return returnL;
    }

    public List<Label> getGrpRl(String grpname1) {
        List<String> tempusr = (this.grpRls).get(grpname1);
        List<Label> returnL = new ArrayList<Label>();
        for (String s : tempusr) {
            returnL.add(new Label(s));

        }
        return returnL;
    }

    public void storeGrpUsrsNRlsNDetails(String grpName) {//store a given grps users and rls in a list.
        
        List mainGrpL = db.getGroupDetails(grpName);
        HashMap<String, String> h1 = new HashMap<String, String>();
        List g1 = (List) mainGrpL.get(0);
        List g2 = (List) mainGrpL.get(1);
        List g3 = (List) mainGrpL.get(2);
        h1.put("Name", (String) g1.get(0));
        h1.put("Detail", (String) g1.get(1));
        grpD.put(grpName, h1);
        grpUsrs.put(grpName, g2);
        grpRls.put(grpName, g3);

    }

    public void storeUsrGrpsNDetails(String usrName) {//store a given grps users and rls in a list.
        List mainUsrL = db.getUserDetails(usrName);
        HashMap<String, String> h1 = new HashMap<String, String>();
        List g1 = (List) mainUsrL.get(0);
        List g2 = (List) mainUsrL.get(1);
        h1.put("Name", (String) g1.get(0));
        h1.put("Address", (String) g1.get(1));
        h1.put("Email", (String) g1.get(2));
        h1.put("Detail", (String) g1.get(3));
        usrD.put(usrName, h1);
        usrGrps.put(usrName, g2);


    }

    public void storeRlGrpsNDetails(String rlName) {//store a given grps users and rls in a list.
        List mainRlL = db.getRoleDetails(rlName);
        HashMap<String, String> h1 = new HashMap<String, String>();
        List g1 = (List) mainRlL.get(0);
        List g2 = (List) mainRlL.get(1);

        h1.put("Name", (String) g1.get(0));
        h1.put("Detail", (String) g1.get(1));
        rlD.put(rlName, h1);
        rlGrps.put(rlName, g2);


    }

    public HashMap<String, String> getUsrDetail(String userName) {


        return usrD.get(userName);
    }

    public HashMap<String, String> getGrpDetail(String groupName) {


        return grpD.get(groupName);
    }

    public HashMap<String, String> getRlDetail(String roleName) {

        return rlD.get(roleName);
    }

    public void setUsrDetail(HashMap<String, String> d, String usr, String prev, int type) {//1=edit;

        //save in data base.

        usrD.put(usr, d);
        usrD.remove(prev);
        List li = (List) usrGrps.get(prev);
        usrGrps.remove(prev);
        usrGrps.put(usr, li);
        for (Label l : allUsrs) {
            if (prev.equals((String) l.getValue())) {
                l.setContentMode(Label.CONTENT_TEXT);
                l.setValue(usr);

            }

        }

         db.createUser(usr,usrD.get(usr),usrGrps.get(usr));
         db.removeUser(prev);
         updateAllGrps();



        comapp1.usrListBuild();
    }

    public void deleteUsrDB(String prev) {
        List li = (List) usrGrps.get(prev);
        usrGrps.remove(prev);
        usrD.remove(prev);
        List<Label> temp1=allUsrs;
        int i=0;
        for(i=0;i<temp1.size();i++){
               if (prev.equals((String)(temp1.get(i)).getValue())) {
                
	    allUsrs.remove(i);
              }
           }

         db.removeUser(prev);
        updateAllGrps();
        comapp1.usrListBuild();
    }

    public void newUsr(HashMap<String, String> d, String usr, List grps1) {
        db.createUser(usr,d,grps1);
        updateAllGrps();
        updateAllUsrs();
    }
    public void removeUsrfrmGrp(List<Label> li,String grpname2){
        List<String> temp1=grpUsrs.get(grpname2);
        int y=0,x=0; 
       for(x=0;x<temp1.size();x++){
           for(Label lb1:li){
               if(temp1.get(x).equals((String)lb1.getValue())){
                   temp1.remove(x);
                  break;
               }

           }
        }

        grpUsrs.remove(grpname2);
        grpUsrs.put(grpname2, temp1);
        db.createGroup(grpname2,grpD.get(grpname2),grpUsrs.get(grpname2),grpRls.get(grpname2));
        updateAllGrps();
        updateAllUsrs();
    }
    public void AddUsrtGrp(List<Label> li,String grpname2){
       w.showNotification("okGrp1");
       List<String> temp1=grpUsrs.get(grpname2);
       int x=0;
       
         
       boolean f1=false;
       try{ 
    
       for(Label lb1:li){       
          for(x=0;x<temp1.size();x++){
              
              if((temp1.get(x).equals((String)lb1.getValue()))){
                   f1=true;
                  break;
               }

           }
          if(!f1){
            temp1.add((String)lb1.getValue());
            f1=false;
          }
        }
        grpUsrs.remove(grpname2);
        grpUsrs.put(grpname2, temp1);
      
        } catch(Exception e) {
           temp1=new ArrayList<String>();
          for(Label lb1:li){
             temp1.add((String)lb1.getValue());
            }
           grpUsrs.put(grpname2, temp1);
        }

    
     
        List<String> temp2=grpRls.get(grpname2); 
        try{

        db.createGroup(grpname2,grpD.get(grpname2),grpUsrs.get(grpname2),grpRls.get(grpname2));
        }catch(Exception e){
        
           db.createGroup(grpname2,grpD.get(grpname2),grpUsrs.get(grpname2),grpUsrs.get(grpname2));
          }
         updateAllGrps();
        updateAllUsrs();



    }

    public void setGrpDetail(HashMap<String, String> d, String grp, String prev, int type) {//1=edit,2=delete,3=new;

        //save in data base.

        grpD.put(grp, d);
        grpD.remove(prev);
        List li = (List) grpUsrs.get(prev);
        grpUsrs.remove(prev);
        grpUsrs.put(grp, li);
        List li2 = (List) grpRls.get(prev);
        grpRls.remove(prev);
        grpRls.put(grp, li);

        for (Label l : allGrps) {
            if (prev.equals((String) l.getValue())) {
                l.setContentMode(Label.CONTENT_TEXT);
                l.setValue(grp);

            }

        }
         db.createGroup(grp,grpD.get(grp),grpUsrs.get(grp),grpRls.get(grp));
         db.removeGroup(prev);
         updateAllUsrs();
         updateAllRls();

        comapp1.grpListBuild();
    }

    public void deleteGrpDB(String prev) {///////////////////////////////////Group del
        List<Label> temp1=allGrps;
	grpD.remove(prev);
        List li = (List) grpUsrs.get(prev);

        grpUsrs.remove(prev);
        
        List li2 = (List) grpRls.get(prev);
        grpRls.remove(prev);
        
        int i=0;
        for(i=0;i<temp1.size();i++){
               if (prev.equals((String)(temp1.get(i)).getValue())) {
                
	    allGrps.remove(i);
              }
           }

         db.removeGroup(prev);
         updateAllUsrs();
	 updateAllRls();
        comapp1.grpListBuild();
w.showNotification("comapp...");
}

    public void newGrp(HashMap<String, String> d, String grp, List usrs1, List rls1) {
          db.createGroup(grp,d,usrs1,rls1);
          w.showNotification(d.get("Detail"));  
          updateAllGrps();
          updateAllUsrs();
          updateAllRls();
    }

    public void setRlDetail(HashMap<String, String> d, String rl, String prev, int type) {//1=edit,2=delete,3=new;

        //save in data base.

        rlD.put(rl, d);
        rlD.remove(prev);
        List li = (List) rlGrps.get(prev);
        rlGrps.remove(prev);
        rlGrps.put(rl, li);
        for (Label l : allRls) {
            if (prev.equals((String) l.getValue())) {
                l.setContentMode(Label.CONTENT_TEXT);
                l.setValue(rl);

            }

        }
        db.createRole(rl,rlD.get(rl),rlGrps.get(rl));
        db.removeRole(prev);
        updateAllGrps();

        comapp1.rlListBuild();
    }

    public void deleteRlDB(String prev) {
        rlD.remove(prev);
        List li = (List) rlGrps.get(prev);
        rlGrps.remove(prev);
        List<Label> temp1=allRls;
        int i=0;
        for(i=0;i<temp1.size();i++){
               if (prev.equals((String)(temp1.get(i)).getValue())) {
                
	    allRls.remove(i);
              }
           }

        db.removeRole(prev);
        updateAllGrps();
        comapp1.rlListBuild();
    }

    public void newRl(HashMap<String, String> d, String rl, List grps1) {
         db.createRole(rl,d,grps1);
         updateAllGrps();
         updateAllRls();
    }
    public void removeRlfrmGrp(List<Label> li,String grpname2){/////////////////////
        List<String> temp1=grpRls.get(grpname2);
   int x=0;        
        for(x=0;x<temp1.size();x++){
           for(Label lb1:li){
               if(temp1.get(x).equals((String)lb1.getValue())){
                   temp1.remove(x);
                  break;
               }

           }
        }

        grpRls.remove(grpname2);
        grpRls.put(grpname2, temp1);
        db.createGroup(grpname2,grpD.get(grpname2),grpUsrs.get(grpname2),grpRls.get(grpname2));
        updateAllGrps();
        updateAllRls();
    }
    public void AddRltGrp(List<Label> li,String grpname2){
       List<String> temp1=grpRls.get(grpname2);
        int x=0;        
      w.showNotification(temp1.size()+"");
     boolean f1=false;
      for(Label lb1:li){       
          for(x=0;x<temp1.size();x++){
              
              if((temp1.get(x).equals((String)lb1.getValue()))){
                   f1=true;
                  break;
               }

           }
          if(!f1){
            temp1.add((String)lb1.getValue());
            f1=false;
          }
        }
           
       
        grpRls.remove(grpname2);
        grpRls.put(grpname2, temp1);
        db.createGroup(grpname2,grpD.get(grpname2),grpUsrs.get(grpname2),grpRls.get(grpname2));
        updateAllGrps();
        updateAllRls();



    }



    public void updateAllGrps() {
        List ll = this.getGrpList();
       grpD.clear();
       grpUsrs.clear();
       grpRls.clear();

        for (Object la : ll) {
            Label lb = (Label) la;
            this.storeGrpUsrsNRlsNDetails((String) lb.getValue());
        }


    }

    public void updateAllUsrs() {
        List ll = this.getUsrList();
        usrD.clear();
        usrGrps.clear();

        for (Object la : ll) {
            Label lb = (Label) la;
            this.storeUsrGrpsNDetails((String) lb.getValue());
        }


    }

    public void updateAllRls() {
        List ll = this.getRlList();
        rlD.clear();
        rlGrps.clear();
        for (Object la : ll) {
            Label lb = (Label) la;
            this.storeRlGrpsNDetails((String) lb.getValue());
        }


    }

    public List<Label> getGrpList() {
        allGrps.removeAll(allGrps);

        List allGrpsN=db.getAllGroups();
          for(Object s:allGrpsN){
            allGrps.add(new Label((String)s));
        }

        return allGrps;

    }

    public List<Label> getUsrList() {

        allUsrs.removeAll(allUsrs);

        List allUsrsN=db.getAllUsers();
           for(Object s:allUsrsN){
              allUsrs.add(new Label((String)s));
           }

        return allUsrs;

    }

    public List<Label> getRlList() {
        allRls.removeAll(allRls);

         List allRlsN=db.getAllRoles();
          for(Object s:allRlsN){
              allRls.add(new Label((String)s));
         }
        return allRls;

    }

   /* private void createTempUsrData(String get) {
        HashMap<String, String> m = new HashMap<String, String>();
        m.put("Name", get);
        m.put("Address", "helloww");
        m.put("Email", "xyz@gmail.com");
        m.put("Detail", "heeelll11");
        usrD.put(get, m);
        allUsrs.add(new Label(get));
        

    }

    private void createTempGrpData(String get) {
        HashMap<String, String> m = new HashMap<String, String>();
        m.put("Name", get);
        m.put("Detail", "helloww");
        grpD.put(get, m);
        allGrps.add(new Label(get));
    }

    private void createTempRlData(String get) {
        HashMap<String, String> m = new HashMap<String, String>();
        m.put("Name", get);
        m.put("Detail", "gaggagagg");
        rlD.put(get, m);
        allRls.add(new Label(get));
    } */
}
