package Gapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class loginpage extends Application implements HttpServletRequestListener{

    
    Window main = new Window("Hello window"); 
    Window loginWindow;
    Window logoutWindow;
    Button googleButton, yahooButton, logoutButton;

    @Override
    public void init(){
	//init method is called
        setMainWindow(main);
	main.addComponent(new Label("hello"));
	Button button = new Button ("Make a request");
        main.addComponent(button);
    }

    @Override
    public void onRequestStart(HttpServletRequest req, HttpServletResponse response){
	//on request start
	final HttpServletRequest request = req;
	final UserService uService = UserServiceFactory.getUserService();
	final User user = uService.getCurrentUser();

	if(user == null){
	    /*if(loginWindow != null){
		main.removeWindow(loginWindow);
		}*/
	    if(loginWindow == null){
		loginWindow = new Window("login");
	    }

	    VerticalLayout layout = (VerticalLayout)loginWindow.getContent();

	    layout.setMargin(true);
	    layout.setSpacing(true);
	    
	    //for each poen id create a button to fetch the link;
	    /* for(String name : openIds.keySet()){
		//layout.addComponent(new Link(name,new ExternalResource(uService.createLoginURL(openIds.get(name)))));
		layout.addComponent(new Button(name,new Button.ClickListener(){
			public void buttonClick(ClickEvent event){
			    loginWindow.open(new ExternalResource(uService.createLoginURL(request.getRequestURI(),null,openIds.get(name),null)));
			    // (loginWindow.getParent()).removaWindow(loginWindow);
			}
		    }));
		    }*/
	    if(googleButton == null){
		layout.addComponent(new Label("please log in to proceed"));
		googleButton = new Button("google",new Button.ClickListener(){
			public void buttonClick(ClickEvent event){
			    String requesturl = request.getRequestURI();
			    main.addComponent(new Label(requesturl));
			    String LoginUrl = uService.createLoginURL(requesturl,null,"gmail.com",null);
			    main.addComponent(new Label(LoginUrl));
			    loginWindow.open(new ExternalResource(LoginUrl));
			    //(loginWindow.getParent()).removeWindow(loginWindow);
			}
		    });
		layout.addComponent(googleButton);
	    }

	    if(yahooButton == null){
		yahooButton = new Button("yahoo",new Button.ClickListener(){
			public void buttonClick(ClickEvent event){
			    loginWindow.open(new ExternalResource(uService.createLoginURL(request.getRequestURI(),null,"yahoo.com",null)));
			}
		    });
		layout.addComponent(yahooButton);
	    }

	    if(loginWindow.getParent() == null){
		main.addWindow(loginWindow);
	    }
	    
	}else{
	    if(logoutButton == null){
		main.addComponent(new Label("hello " + user.getNickname()));
		logoutButton = new Button("logout", new Button.ClickListener(){
			public void buttonClick(ClickEvent event){
			    /*if(logoutWindow==null){
				logoutWindow = new Window("logout");
				logoutWindow.open(new ExternalResource(uService.createLogoutURL(request.getRequestURI())));
				main.addWindow(logoutWindow);
				}*/
			    ExternalResource er = new ExternalResource(uService.createLogoutURL("index.jsp"));
			    main.open(er);
			    main.removeComponent(logoutButton);
			}
		    });
		main.addComponent(logoutButton);
	    }
	}
    }

    @Override
    public void onRequestEnd(HttpServletRequest request, HttpServletResponse response){
	//on request end
    }
}
