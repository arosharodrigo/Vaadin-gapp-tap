package Application;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
//import com.google.appengine.api.datastore.Query;


import java.io.IOException;
import java.util.*;


import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

public class secondServ extends HttpServlet {

    String content1, labelVal, id1;
    HttpSession ses1;
    frontBean fb1;

    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.setContentType("text/plain");

        ses1 = req.getSession();
        fb1 = (frontBean) ses1.getAttribute("bean");

        if (fb1 == null) {
            fb1 = new frontBean();
            ses1.setAttribute("bean", fb1);
        }
        id1 = (String) ses1.getAttribute("id");
        if (id1 == null) {
            Random ran = new Random();
            int i1 = ran.nextInt(1000);
            id1 = Integer.toString(i1);
            req.setAttribute("id", id1);
            fb1.setId(id1);
        }
        DatastoreService dt1 = DatastoreServiceFactory.getDatastoreService();
        Key k1 = KeyFactory.createKey("searchMe", Long.parseLong(id1));

        Entity en1 = new Entity(k1);
        
        en1.setProperty("name", req.getParameter("name"));
        en1.setProperty("add", req.getParameter("add"));
        en1.setProperty("email", req.getParameter("email"));
        en1.setProperty("phone", req.getParameter("phone"));
        en1.setProperty("desc", req.getParameter("desc"));
        dt1.put(en1);
        fb1.setName((String) req.getParameter("name"));
        fb1.setAdd((String)req.getParameter("add"));
        fb1.setPhone((String)req.getParameter("phone"));
        fb1.setEmail((String)req.getParameter("email"));
        fb1.setDesc((String) req.getParameter("desc"));


        req.setAttribute("frontBean", fb1);
        RequestDispatcher rd1 = req.getRequestDispatcher("/datacollector.jsp");
        try {
            rd1.forward(req, resp);
        } catch (Exception ex1) {
        }
    }

    public String method(String str) {
        return str;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.service(req, resp);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        this.service(req, resp);
    }

    public static String method2() {
        return "A1";
    }
}

