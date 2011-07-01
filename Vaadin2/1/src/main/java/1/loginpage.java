package Gapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.Serializable;

import java.util.Map;
import java.util.HashMap;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Window;
import com.vaadin.ui.Link;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Link;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class loginpage extends Application implements HttpServletRequestListener{

    
    Window main = new Window("Hello window"); 
    Window loginWindow;
    Window logoutWindow;
    Button googleButton, yahooButton, logoutButton;
    Link googleLink, yahooLink, logoutLink;
    //    HttpServletRequest request;
    UserService uService;
    User user;
    String rederectionUrl = "";

    @Override
    public void init(){
	//init method is called
        setMainWindow(main);
	main.addComponent(new Label("hello"));
	Button button = new Button ("Make a request");
        main.addComponent(button);

	if(user == null){
	    /*
	    if(loginWindow == null){
		loginWindow = new Window("login");
	    }
	    VerticalLayout layout = (VerticalLayout)loginWindow.getContent();

	    layout.setMargin(true);
	    //layout.setPadding(true);

	    if(googleButton == null){
		layout.addComponent(new Label("please log in to proceed"));
		googleButton = new Button("google",new Button.ClickListener(){
			public void buttonClick(ClickEvent event){
			    String LoginUrl = uService.createLoginURL(rederectionUrl,null,"gmail.com",null);
			    loginWindow.open(new ExternalResource(LoginUrl));
			}
		    });
		layout.addComponent(googleButton);
	    }

	    if(yahooButton == null){
		yahooButton = new Button("yahoo",new Button.ClickListener(){
			public void buttonClick(ClickEvent event){
			    loginWindow.open(new ExternalResource(uService.createLoginURL(rederectionUrl,null,"yahoo.com",null)));
			}
		    });
		layout.addComponent(yahooButton);
	    }

	    if(loginWindow.getParent() == null){
		main.addWindow(loginWindow);
	    }
	    */
	    if(googleLink == null){
		googlelink = new Link("google login", new ExternalResource(uService.createLoginURL(rederectionUrl,null,"gmail.com",null)));
		main.addComponent(googleLink);
		yahooLink = new Link("yahoo login", new ExternalResource(uService.createLoginURL(rederectionUrl,null,"yahoo.com",null)));
		main.addComponent(yahooLink);
	    }
	}else{
	    /*
	    if(logoutButton == null){
		main.addComponent(new Label("hello " + user.getNickname()));
		logoutButton = new Button("logout", new Button.ClickListener(){
			public void buttonClick(ClickEvent event){
			    ExternalResource er = new ExternalResource(uService.createLogoutURL(rederectionUrl));
			    main.open(er);
			    main.removeComponent(logoutButton);
			}
		    });
		main.addComponent(logoutButton);
		}*/
	    if(logoutLink == null){
		logoutLink = new Link("yahoo login", new ExternalResource(uService.createLogoutURL(rederectionUrl)));
		main.addComponent(yahooLink);
	}

    }
    
    @Override
	public void onRequestStart(HttpServletRequest req, HttpServletResponse response){
	main.addComponent(new Label("start of the method"));
	//	System.out.println("start of method");
	rederectionUrl = req.getRequestURI();
	uService = UserServiceFactory.getUserService();
        user = uService.getCurrentUser();
	//	System.out.println("end of the method");
	main.addComponent(new Label("end of the method"));
    }
    @Override
    public void onRequestEnd(HttpServletRequest request, HttpServletResponse response){
	//on request end
    }
}
