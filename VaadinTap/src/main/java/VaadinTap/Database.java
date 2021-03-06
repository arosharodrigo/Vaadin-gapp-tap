package l1;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.*;
import com.google.appengine.api.datastore.*;
import java.util.*;
import java.lang.String;
import java.io.Serializable;

public class Database implements Serializable{
    
    //    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

   //Creating Fields

   //1. Creating and Editing of Group
    public void createGroup(String groupName, HashMap<String,String> hm,  List<String> usersList, List<String> rolesList){
	
	String groupDescription=hm.get("Detail");


	String[] users=new String [usersList.size()];
	Iterator i1 = usersList.iterator();
	int p=0;
	while(i1.hasNext()) {
        Object 	o1 = i1.next();
	users[p]=(String)o1;
	p++;
	}
	p=0;
	
	String[] roles=new String [rolesList.size()];
	Iterator i2 = rolesList.iterator();
	int q=0;
	while(i2.hasNext()) {
        Object o2 = i2.next();
	roles[q]=(String)o2;
	q++;
	}
	q=0;

	Key groupNameKey = KeyFactory.createKey("Groups",groupName);
	try{
	Entity group = DatastoreServiceFactory.getDatastoreService().get(groupNameKey);
	group.setProperty("groupName", groupName);
	group.setProperty("groupDescription", groupDescription);
	DatastoreServiceFactory.getDatastoreService().put(group);
	
	Query userQuery = new Query("Users");
	userQuery.setAncestor(group.getKey());
	List<Entity> results = DatastoreServiceFactory.getDatastoreService().prepare(userQuery).asList(FetchOptions.Builder.withDefaults());
	Iterator itr = results.iterator(); 
	while(itr.hasNext()) {
        Object element = itr.next();
	Entity e=(Entity)element;
	Key k =e.getKey();
	try{
	    DatastoreServiceFactory.getDatastoreService().delete(k); 
	}catch(Exception ex){}
	
	}
	for(int i=0;i<users.length;i++) {
	    Entity ent = new Entity("Users", users[i], group.getKey());
	    ent.setProperty("userName", users[i]);
	    DatastoreServiceFactory.getDatastoreService().put(ent);
	}

	Query userQuery2 = new Query("Roles");
	userQuery2.setAncestor(group.getKey());
	List<Entity> results2 = DatastoreServiceFactory.getDatastoreService().prepare(userQuery2).asList(FetchOptions.Builder.withDefaults());
	Iterator itr2 = results2.iterator(); 
	while(itr2.hasNext()) {
        Object element2 = itr2.next();
	Entity e2=(Entity)element2;
	Key k2 =e2.getKey();
	try{
	    DatastoreServiceFactory.getDatastoreService().delete(k2); 
	}catch(Exception e){}
	
	}
	for(int i=0;i<roles.length;i++) {
	    Entity ent = new Entity("Roles", roles[i], group.getKey());
	    ent.setProperty("roleName", roles[i]);
	    DatastoreServiceFactory.getDatastoreService().put(ent);
	}
	//Adding info into cloned users & roles as well
	Query q3=new Query("UsersCloned");
	List<Entity> results3 = DatastoreServiceFactory.getDatastoreService().prepare(q3).asList(FetchOptions.Builder.withDefaults());
	Iterator itr3 = results3.iterator(); 
	while(itr3.hasNext()) {
        Object element3 = itr3.next();
	Entity e3=(Entity)element3;
	Key k3 = KeyFactory.createKey(e3.getKey(),"GroupsClonedUsers",groupName);
	try{
	    DatastoreServiceFactory.getDatastoreService().delete(k3); 
	}catch(Exception e){}

	for(int i=0;i<users.length;i++){
	    if((e3.getProperty("userName")).equals(users[i])){
		Entity ent = new Entity("GroupsClonedUsers", groupName, e3.getKey());
		ent.setProperty("groupName", groupName);
		DatastoreServiceFactory.getDatastoreService().put(ent);
	    }
	}
	}
	
	Query q4=new Query("RolesCloned");
	List<Entity> results4 = DatastoreServiceFactory.getDatastoreService().prepare(q4).asList(FetchOptions.Builder.withDefaults());
	Iterator itr4 = results4.iterator(); 
	while(itr4.hasNext()) {
        Object element4 = itr4.next();
	Entity e4=(Entity)element4;
	Key k4 = KeyFactory.createKey(e4.getKey(),"GroupsClonedRoles",groupName);
	try{
	    DatastoreServiceFactory.getDatastoreService().delete(k4); 
	}catch(Exception e){}

	for(int i=0;i<roles.length;i++){
	    if((e4.getProperty("roleName")).equals(roles[i])){
		Entity ent = new Entity("GroupsClonedRoles", groupName, e4.getKey());
		ent.setProperty("groupName", groupName);
		DatastoreServiceFactory.getDatastoreService().put(ent);
	    }
	}
	}


	/*Adding info into cloned users & roles as well*/

	}catch(Exception e){        

	Entity group = new Entity("Groups", groupName);
	group.setProperty("groupName", groupName);
	group.setProperty("groupDescription",  groupDescription);
	DatastoreServiceFactory.getDatastoreService().put(group);

	for(int i=0;i<users.length;i++) {
	    Entity ent1 = new Entity("Users", users[i], group.getKey());
	    ent1.setProperty("userName", users[i]);
	    DatastoreServiceFactory.getDatastoreService().put(ent1);
	}
	for(int i=0;i<roles.length;i++) {
	    Entity ent2 = new Entity("Roles", roles[i], group.getKey());
	    ent2.setProperty("roleName", roles[i]);
	    DatastoreServiceFactory.getDatastoreService().put(ent2);
	}
	
	Query q5=new Query("UsersCloned");
	List<Entity> results5 = DatastoreServiceFactory.getDatastoreService().prepare(q5).asList(FetchOptions.Builder.withDefaults());
	Iterator itr5 = results5.iterator(); 
	while(itr5.hasNext()) {
        Object element5 = itr5.next();
	Entity e5=(Entity)element5;
	for(int i=0;i<users.length;i++){
	    if((e5.getProperty("userName")).equals(users[i])){
		Entity ent5 = new Entity("GroupsClonedUsers", groupName, e5.getKey());
		ent5.setProperty("groupName", groupName);
		DatastoreServiceFactory.getDatastoreService().put(ent5);
	    }
	}
	}

	Query q6=new Query("RolesCloned");
	List<Entity> results6 = DatastoreServiceFactory.getDatastoreService().prepare(q6).asList(FetchOptions.Builder.withDefaults());
	Iterator itr6 = results6.iterator(); 
	while(itr6.hasNext()) {
        Object element6 = itr6.next();
	Entity e6=(Entity)element6;
	for(int i=0;i<roles.length;i++){
	    if((e6.getProperty("roleName")).equals(roles[i])){
		Entity ent6 = new Entity("GroupsClonedRoles", groupName, e6.getKey());
		ent6.setProperty("groupName", groupName);
		DatastoreServiceFactory.getDatastoreService().put(ent6);
	    }
	}
	}
	}
    }
    
   //2. Creating and Editing of User
    public void createUser(String userName, HashMap<String,String> hm ,List<String> groupsList){

	String userAddress=hm.get("Address");
	String userMobile=hm.get("Detail");
	String userEmail=hm.get("Email");

	String[] groupsClonedUsers=new String [groupsList.size()];
	Iterator i1 = groupsList.iterator();
	int p=0;
	while(i1.hasNext()) {
        Object o1 = i1.next();
	groupsClonedUsers[p]=(String)o1;
	p++;
	}
	p=0;

	Key userNameKey = KeyFactory.createKey("UsersCloned",userName);
	try{
	Entity user = DatastoreServiceFactory.getDatastoreService().get(userNameKey);
	user.setProperty("userName",userName);
	user.setProperty("userAddress",userAddress);
	user.setProperty("userMobile",userMobile);
	user.setProperty("userEmail",userEmail);
	DatastoreServiceFactory.getDatastoreService().put(user);
	
	Query groupQuery = new Query("GroupsClonedUsers");
	groupQuery.setAncestor(user.getKey());
	List<Entity> results = DatastoreServiceFactory.getDatastoreService().prepare(groupQuery).asList(FetchOptions.Builder.withDefaults());
	Iterator itr = results.iterator(); 
	while(itr.hasNext()) {
        Object element = itr.next();
	Entity e=(Entity)element;
	Key k =e.getKey();
	try{
	    DatastoreServiceFactory.getDatastoreService().delete(k); 
	}catch(Exception ex){}
	
	}
	for(int i=0;i<groupsClonedUsers.length;i++) {
	    Entity ent = new Entity("GroupsClonedUsers", groupsClonedUsers[i], user.getKey());
	    ent.setProperty("groupName", groupsClonedUsers[i]);
	    DatastoreServiceFactory.getDatastoreService().put(ent);
	}
	Query q1=new Query("Groups");
	List<Entity> results1 = DatastoreServiceFactory.getDatastoreService().prepare(q1).asList(FetchOptions.Builder.withDefaults());
	Iterator itr1 = results1.iterator(); 
	while(itr1.hasNext()) {
        Object element1 = itr1.next();
	Entity e1=(Entity)element1;
	Key k1 = KeyFactory.createKey(e1.getKey(),"Users",userName);
	try{
	    DatastoreServiceFactory.getDatastoreService().delete(k1); 
	}catch(Exception e){}

	for(int i=0;i<groupsClonedUsers.length;i++){
	    if((e1.getProperty("groupName")).equals(groupsClonedUsers[i])){
		Entity ent = new Entity("Users", userName, e1.getKey());
		ent.setProperty("userName", userName);
		DatastoreServiceFactory.getDatastoreService().put(ent);
	    }
	}
	}

	}catch(Exception e){
	Entity usersCloned= new Entity("UsersCloned",userName);
	usersCloned.setProperty("userName",userName);
	usersCloned.setProperty("userAddress",userAddress);
	usersCloned.setProperty("userMobile",userMobile);
	usersCloned.setProperty("userEmail",userEmail);
	DatastoreServiceFactory.getDatastoreService().put(usersCloned);
	
	for(int i=0;i<groupsClonedUsers.length;i++) {
	    Entity ent = new Entity("GroupsClonedUsers", groupsClonedUsers[i], usersCloned.getKey());
	    ent.setProperty("groupName", groupsClonedUsers[i]);
	    DatastoreServiceFactory.getDatastoreService().put(ent);
     	}
	Query q2=new Query("Groups");
	List<Entity> results2 = DatastoreServiceFactory.getDatastoreService().prepare(q2).asList(FetchOptions.Builder.withDefaults());
	Iterator itr2 = results2.iterator(); 
	while(itr2.hasNext()) {
        Object element2 = itr2.next();
	Entity e2=(Entity)element2;
	for(int i=0;i<groupsClonedUsers.length;i++){
	    if((e2.getProperty("groupName")).equals(groupsClonedUsers[i])){
		Entity ent = new Entity("Users", userName, e2.getKey());
		ent.setProperty("userName", userName);
		DatastoreServiceFactory.getDatastoreService().put(ent);
	    }
	}
	}
	}	
    }

    //3. Creating and Editing of Role
    public void createRole(String roleName, HashMap<String,String> hm, List<String> groupsList){
	
	String roleDescription=hm.get("Detail");

	String[] groupsClonedRoles=new String [groupsList.size()];
	Iterator i1 = groupsList.iterator();
	int p=0;
	while(i1.hasNext()) {
        Object o1 = i1.next();
	groupsClonedRoles[p]=(String)o1;
	p++;
	}
	p=0;

	Key roleNameKey = KeyFactory.createKey("RolesCloned", roleName);
	try{
	Entity role = DatastoreServiceFactory.getDatastoreService().get(roleNameKey);
	role.setProperty("roleName",roleName);
	role.setProperty("roleDescription",roleDescription);
	DatastoreServiceFactory.getDatastoreService().put(role);

	Query groupQuery = new Query("GroupsClonedRoles");
	groupQuery.setAncestor(role.getKey());
	List<Entity> results = DatastoreServiceFactory.getDatastoreService().prepare(groupQuery).asList(FetchOptions.Builder.withDefaults());
	Iterator itr = results.iterator(); 
	while(itr.hasNext()) {
        Object element = itr.next();
	Entity e=(Entity)element;
	Key k =e.getKey();
	try{
	    DatastoreServiceFactory.getDatastoreService().delete(k); 
	}catch(Exception ex){}
	
	}
	for(int i=0;i<groupsClonedRoles.length;i++) {
	    Entity ent = new Entity("GroupsClonedRoles", groupsClonedRoles[i], role.getKey());
	    ent.setProperty("groupName", groupsClonedRoles[i]);
	    DatastoreServiceFactory.getDatastoreService().put(ent);
	}

	Query q1=new Query("Groups");
	List<Entity> results1 = DatastoreServiceFactory.getDatastoreService().prepare(q1).asList(FetchOptions.Builder.withDefaults());
	Iterator itr1 = results1.iterator(); 
	while(itr1.hasNext()) {
        Object element1 = itr1.next();
	Entity e1=(Entity)element1;
	Key k1 = KeyFactory.createKey(e1.getKey(),"Roles",roleName);
	try{
	    DatastoreServiceFactory.getDatastoreService().delete(k1); 
	}catch(Exception e){}

	for(int i=0;i<groupsClonedRoles.length;i++){
	    if((e1.getProperty("groupName")).equals(groupsClonedRoles[i])){
		Entity ent = new Entity("Roles", roleName, e1.getKey());
		ent.setProperty("roleName", roleName);
		DatastoreServiceFactory.getDatastoreService().put(ent);
	    }
	}
	}
	    
	}
	catch(Exception e){
	    
	Entity rolesCloned= new Entity("RolesCloned",roleName);
	rolesCloned.setProperty("roleName",roleName);
	rolesCloned.setProperty("roleDescription",roleDescription);
	DatastoreServiceFactory.getDatastoreService().put(rolesCloned);

	for(int i=0;i<groupsClonedRoles.length;i++){
	    Entity ent=new Entity("GroupsClonedRoles",groupsClonedRoles[i],rolesCloned.getKey());
	    ent.setProperty("groupName", groupsClonedRoles[i]);
	    DatastoreServiceFactory.getDatastoreService().put(ent);
	}

	Query q2=new Query("Groups");
	List<Entity> results2 = DatastoreServiceFactory.getDatastoreService().prepare(q2).asList(FetchOptions.Builder.withDefaults());
	Iterator itr2 = results2.iterator(); 
	while(itr2.hasNext()) {
        Object element2 = itr2.next();
	Entity e2=(Entity)element2;
	for(int i=0;i<groupsClonedRoles.length;i++){
	    if((e2.getProperty("groupName")).equals(groupsClonedRoles[i])){
		Entity ent = new Entity("Roles", roleName, e2.getKey());
		ent.setProperty("roleName", roleName);
		DatastoreServiceFactory.getDatastoreService().put(ent);
	    }
	}
	}

	}

    }


    //Removing Fields

    // 4. Remove group
    public void removeGroup(String groupName){
	Key groupNameKey = KeyFactory.createKey("Groups",groupName);
	DatastoreServiceFactory.getDatastoreService().delete(groupNameKey);
	
	Query q1=new Query("UsersCloned");
	List<Entity> results = DatastoreServiceFactory.getDatastoreService().prepare(q1).asList(FetchOptions.Builder.withDefaults());
	Iterator itr = results.iterator(); 
	while(itr.hasNext()) {
        Object element = itr.next();
	Entity e1=(Entity)element;
	Key k1 = KeyFactory.createKey(e1.getKey(),"GroupsClonedUsers",groupName);
	try{
	    DatastoreServiceFactory.getDatastoreService().delete(k1); 
	}catch(Exception e){}
	
	}
	
	Query q2=new Query("RolesCloned");
	List<Entity> results2 = DatastoreServiceFactory.getDatastoreService().prepare(q2).asList(FetchOptions.Builder.withDefaults());
	Iterator itr2 = results2.iterator(); 
	while(itr2.hasNext()) {
        Object element = itr2.next();
	Entity e2=(Entity)element;
	Key k2 = KeyFactory.createKey(e2.getKey(),"GroupsClonedRoles",groupName);
	try{
	    DatastoreServiceFactory.getDatastoreService().delete(k2); 
	}catch(Exception e){}
	} 	
    }

    // 5. Remove user
    public void removeUser(String userName){
	Key userNameKey = KeyFactory.createKey("UsersCloned",userName);
	DatastoreServiceFactory.getDatastoreService().delete(userNameKey);

	Query q1=new Query("Groups");
	List<Entity> results= DatastoreServiceFactory.getDatastoreService().prepare(q1).asList(FetchOptions.Builder.withDefaults());
	Iterator itr = results.iterator();
	while(itr.hasNext()){
	    Object element =itr.next();
	    Entity e1=(Entity)element;
	    Key k1 = KeyFactory.createKey(e1.getKey(),"Users",userName);
	try{
	    DatastoreServiceFactory.getDatastoreService().delete(k1); 
	}catch(Exception e){}
	
	}   
    }

    // 6. Remove role
    public void removeRole(String roleName){
	
	Key roleNameKey = KeyFactory.createKey("RolesCloned",roleName);
	DatastoreServiceFactory.getDatastoreService().delete(roleNameKey);
	
	Query q1=new Query("Groups");
	List<Entity> results= DatastoreServiceFactory.getDatastoreService().prepare(q1).asList(FetchOptions.Builder.withDefaults());
	Iterator itr =results.iterator();
	while(itr.hasNext()){
	    Object element =itr.next();
	    Entity e1=(Entity)element;
	    Key k1=KeyFactory.createKey(e1.getKey(),"Roles",roleName);
	    try{
		DatastoreServiceFactory.getDatastoreService().delete(k1);
	    }catch(Exception e){}
	}
    }

    
    //Getting Field Details

    // 7. Get Group Details
    public List<List> getGroupDetails(String groupName){
	List<String> set1=new ArrayList<String>();
	List<String> set2=new ArrayList<String>();
	List<String> set3=new ArrayList<String>();
	List<List> groupSet=new ArrayList<List>();

    	Key groupNameKey = KeyFactory.createKey("Groups",groupName);
	try{
	Entity group = DatastoreServiceFactory.getDatastoreService().get(groupNameKey);
	set1.add((String)group.getProperty("groupName"));
	set1.add((String)group.getProperty("groupDescription"));

       	Query groupQuery = new Query("Users");
	groupQuery.setAncestor(group.getKey());
	List<Entity> results = DatastoreServiceFactory.getDatastoreService().prepare(groupQuery).asList(FetchOptions.Builder.withDefaults());
	Iterator itr = results.iterator(); 
	while(itr.hasNext()) {
        Object element = itr.next();
	Entity e=(Entity)element;
	set2.add((String)e.getProperty("userName"));
	}
	
	Query groupQuery2 = new Query("Roles");
	groupQuery2.setAncestor(group.getKey());
	List<Entity> results2 = DatastoreServiceFactory.getDatastoreService().prepare(groupQuery2).asList(FetchOptions.Builder.withDefaults());
	Iterator itr2 = results2.iterator(); 
	while(itr2.hasNext()) {
        Object element2 = itr2.next();
	Entity e2=(Entity)element2;
	set3.add((String)e2.getProperty("roleName"));
	}
	}catch(Exception e){}



	groupSet.add(set1);
	groupSet.add(set2);
        groupSet.add(set3);
	return groupSet;
    }

    // 8. Get User Details
    public List<List> getUserDetails(String userName){
	List<String> set1=new ArrayList<String>();
	List<String> set2=new ArrayList<String>();
	List<List> userSet=new ArrayList<List>();

	Key userNameKey = KeyFactory.createKey("UsersCloned",userName);
	try{
	Entity usersCloned = DatastoreServiceFactory.getDatastoreService().get(userNameKey);
	set1.add((String)usersCloned.getProperty("userName"));
	set1.add((String)usersCloned.getProperty("userAddress"));
	set1.add((String)usersCloned.getProperty("userEmail"));
	set1.add((String)usersCloned.getProperty("userMobile"));
	
	Query groupQuery = new Query("GroupsClonedUsers");
	groupQuery.setAncestor(usersCloned.getKey());
	List<Entity> results = DatastoreServiceFactory.getDatastoreService().prepare(groupQuery).asList(FetchOptions.Builder.withDefaults());
	Iterator itr = results.iterator(); 
	while(itr.hasNext()) {
        Object element = itr.next();
	Entity e=(Entity)element;
	set2.add((String)e.getProperty("groupName"));
	}}catch(Exception e){}

	
	
	userSet.add(set1);
	userSet.add(set2);
	return userSet;
    }

    // 9. Get Role Details
    public List<List> getRoleDetails(String roleName){
	List<String> set1=new ArrayList<String>();
	List<String> set2=new ArrayList<String>();
	List<List> roleSet=new ArrayList<List>();

	Key roleNameKey = KeyFactory.createKey("RolesCloned",roleName);
	String[] s1=new String[3];
	try{
	Entity rolesCloned = DatastoreServiceFactory.getDatastoreService().get(roleNameKey);
	set1.add((String)rolesCloned.getProperty("roleName"));
	set1.add((String)rolesCloned.getProperty("roleDescription"));
	Query groupQuery = new Query("GroupsClonedRoles");
	groupQuery.setAncestor(rolesCloned.getKey());
	List<Entity> results = DatastoreServiceFactory.getDatastoreService().prepare(groupQuery).asList(FetchOptions.Builder.withDefaults());
	Iterator itr = results.iterator(); 
	while(itr.hasNext()) {
        Object element = itr.next();
	Entity e=(Entity)element;
	set2.add((String)e.getProperty("groupName"));
	}}catch(Exception e){}
	
	
	roleSet.add(set1);
	roleSet.add(set2);
	return roleSet;
    }

    //Get all information
    
    // 10. Get All Groups 
    public List<String> getAllGroups(){
	List<String> set1=new ArrayList<String>();

	Query groupQuery = new Query("Groups");
	List<Entity> results = DatastoreServiceFactory.getDatastoreService().prepare(groupQuery).asList(FetchOptions.Builder.withDefaults());
	Iterator itr = results.iterator(); 
	while(itr.hasNext()) {
        Object element = itr.next();
	Entity e=(Entity)element;
	set1.add((String)e.getProperty("groupName"));
	}
	return set1;
    }
    
    // 11. Get All Users
    public List<String> getAllUsers(){
	List<String> set1=new ArrayList<String>();

	Query userQuery = new Query("UsersCloned");
	List<Entity> results = DatastoreServiceFactory.getDatastoreService().prepare(userQuery).asList(FetchOptions.Builder.withDefaults());
	Iterator itr = results.iterator(); 
	while(itr.hasNext()) {
        Object element = itr.next();
	Entity e=(Entity)element;
	set1.add((String)e.getProperty("userName"));
	}
	return set1;
    }
    
    // 12. Get All Roles
    public List<String> getAllRoles(){
	List<String> set1=new ArrayList<String>();

	Query roleQuery = new Query("RolesCloned");
	List<Entity> results = DatastoreServiceFactory.getDatastoreService().prepare(roleQuery).asList(FetchOptions.Builder.withDefaults());
	Iterator itr = results.iterator(); 
	while(itr.hasNext()) {
        Object element = itr.next();
	Entity e=(Entity)element;
	set1.add((String)e.getProperty("roleName"));
	}
	return set1;
    }

}


