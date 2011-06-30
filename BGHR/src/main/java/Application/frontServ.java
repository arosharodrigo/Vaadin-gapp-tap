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



import java.io.IOException;
import java.util.*;


import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;

public class frontServ extends HttpServlet {

    String labelVal, id1, tempId, hiddenVal;
    HttpSession ses1;
    frontBean fb1;
    RequestDispatcher rd1;

    public void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter out = resp.getWriter();
        ses1 = req.getSession();
        hiddenVal = (String) req.getParameter("hidden");
        out.print(hiddenVal);
        fb1 = (frontBean) ses1.getAttribute("bean");
        if (fb1 == null) {

            fb1 = new frontBean();
            ses1.setAttribute("bean", fb1);
           // fb1.setShowProf("position: absolute; top: 200px; left: 20px; width: 400px; height: 400px; display:none");
           // fb1.setNoProf("position: absolute; top: 200px; left: 20px; width: 400px; height: 400px; display:block");
            out.print("ok1");
          //  req.setAttribute("frontBean", fb1);

          //  rd1 = req.getRequestDispatcher("/frontpage.jsp");
          //  try {
          //      rd1.forward(req, resp);
          //  } catch (Exception ex1) {
          //      out.print(ex1);
          //  }

        }
        if (hiddenVal.equals("search")) {

            resp.setContentType("text/plain");

           
	    //   if (tempId == null) {
                tempId = req.getParameter("sId");
                ses1.setAttribute("id", tempId);
		//  }

            id1 = tempId;

            //ses1.setAttribute("id", id1);
            Key k1 = KeyFactory.createKey("searchMe", Long.parseLong(id1));
            DatastoreService dt1 = DatastoreServiceFactory.getDatastoreService();
           // Query q1 = new Query(k1);
           // List<Entity> ls1 = dt1.prepare(q1).asList(FetchOptions.Builder.withLimit(0));
           Entity en=null;
	   try{
            en=dt1.get(k1);
           }catch(Exception ex){}
            if (en==null) {
                fb1.setShowProf("position: absolute; top: 200px; left: 20px; width: 400px; height: 400px; display:none");
                fb1.setNoProf("position: absolute; top: 200px; left: 20px; width: 400px; height: 400px; display:block");

            } else {
                fb1.setShowProf("position: absolute; top: 200px; left: 20px; width: 400px; height: 400px; display:block");
                fb1.setNoProf("position: absolute; top: 200px; left: 20px; width: 400px; height: 400px; display:none");

              /*  for (Entity en : ls1) {
                    fb1.setName((String) en.getProperty("name"));
                    fb1.setAdd((String) en.getProperty("add"));
                    fb1.setPhone((String) en.getProperty("phone"));
                    fb1.setEmail((String) en.getProperty("email"));
                    fb1.setDesc((String) en.getProperty("desc"));
                }*/

                    fb1.setName((String) en.getProperty("name"));
                    fb1.setAdd((String) en.getProperty("add"));
                    fb1.setPhone((String) en.getProperty("phone"));
                    fb1.setEmail((String) en.getProperty("email"));
                    fb1.setDesc((String) en.getProperty("desc"));
                
            }
            req.setAttribute("frontBean", fb1);
            rd1 = req.getRequestDispatcher("/frontpage.jsp");
            try {
                rd1.forward(req, resp);
            } catch (Exception ex1) {
            }

        } else if (hiddenVal.equals("edit")) {

	      tempId = (String) ses1.getAttribute("id");
                id1=tempId;
            Key k1 = KeyFactory.createKey("searchMe", Integer.parseInt(id1));
            DatastoreService dt1 = DatastoreServiceFactory.getDatastoreService();
            Query q1 = new Query(k1);
            List<Entity> ls1 = dt1.prepare(q1).asList(FetchOptions.Builder.withLimit(2));
            for (Entity en : ls1) {
                fb1.setName((String) en.getProperty("name"));
                fb1.setAdd((String) en.getProperty("add"));
                fb1.setPhone((String) en.getProperty("phone"));
                fb1.setEmail((String) en.getProperty("email"));
                fb1.setDesc((String) en.getProperty("desc"));
                fb1.setId((String) id1);

            }

            req.setAttribute("frontBean", fb1);
            rd1 = req.getRequestDispatcher("/datacollector.jsp");



            try {
                rd1.forward(req, resp);
            } catch (Exception ex1) {
            }
       
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


