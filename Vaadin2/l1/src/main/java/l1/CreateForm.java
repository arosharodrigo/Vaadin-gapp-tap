package l1;

import com.vaadin.data.validator.*;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.HashMap;
import javax.swing.plaf.ButtonUI;
import com.vaadin.ui.ListSelect;
import java.util.List;
import java.io.Serializable;
import com.vaadin.ui.*;

@SuppressWarnings("serial")
public class CreateForm extends VerticalLayout implements Serializable{

    Window form1;
    TextField usrName, usrAdd, usrMail, usrDetail,grpName,grpDetail,rlName,rlDetail;
    Button change, reset;
    VerticalLayout vl1;
    ComApp comapp;
    DataHub dataH;
    Window main;
    String prev;
    AbsoluteLayout ab1;
    
    public CreateForm(HashMap<String, String> detail, int catgry, Window main1, ComApp app, DataHub d,String prev) {
        this.comapp = app;
        this.dataH = d;
        this.main = main1;
        this.prev=prev;
        final String labelVal = detail.get("Name");
        change = new Button("Change");
        reset = new Button("Reset");
        if (catgry == 1) {
            this.createUsrForm(detail, prev);

        } else if(catgry==2){
            this.createGrpForm(detail, prev);

        }else if(catgry==3){
            this.createRlForm(detail, prev);

        }

	 vl1.setSpacing(true);
	 vl1.setMargin(true);
        vl1.setImmediate(true);
        vl1.setWidth("99%");
        vl1.setHeight("99%");
	vl1.setWidth("500px");
        vl1.setHeight("450px");
        form1.setDraggable(false);
        form1.setResizable(false);
        form1.setClosable(true);
       
        main.addWindow(form1);




    }

    private void createUsrForm(HashMap<String, String> detail, final String prev) {
///////////////////////  b  
     String temp=prev;   
       if(temp.startsWith("<b>", 0)){
       temp = temp.substring(3, (temp.length() - 4));
       }
       final String temp2=temp;
	ListSelect listSelUsr = new ListSelect("User Groups");
        listSelUsr.setWidth("250px");
        listSelUsr.setHeight("300px");
        listSelUsr.setRows(5);
        listSelUsr.setNullSelectionAllowed(true);
 /////////////////////////////////////////////////////////e
        form1 = new Window("User Detail");
        form1.setModal(true);
        HorizontalLayout hl1 = new HorizontalLayout();
        usrName = new TextField("Name",prev);
        usrAdd = new TextField("Address", ((detail.get("Address") == (null)) ? "" : detail.get("Address")));
        usrMail = new TextField("Email", ((detail.get("Email") == (null)) ? "" : detail.get("Email")));
        usrDetail = new TextField("Detail",((detail.get("Detail") == (null)) ? "" : detail.get("Detail")));
        usrDetail.setRows(7);
        ab1=new AbsoluteLayout();
        vl1 = (VerticalLayout) form1.getContent();
	vl1.addComponent(ab1);

	usrName.setRequired(true);
	usrAdd.setRequired(true);
	usrMail.setRequired(true);
	usrDetail.setRequired(true);

        ab1.addComponent(usrName,"top:10%;left:5%");
        ab1.addComponent(usrAdd,"top:20%;left:5%");
        ab1.addComponent(usrMail,"top:30%;left:5%");
        ab1.addComponent(usrDetail,"top:40%;left:5%");
        hl1.addComponent(reset);
        hl1.addComponent(change);
///////////////////b
        ab1.addComponent(listSelUsr,"top:10%;left:45%");
///////////////////////////// e      
        hl1.setSpacing(true);
        hl1.setComponentAlignment(change, Alignment.MIDDLE_RIGHT);
        hl1.setComponentAlignment(reset, Alignment.MIDDLE_LEFT);
        ab1.addComponent(hl1,"top:85%;left:5%");
         
         ///////////b
         List<Label> grpsLa=dataH.getUsrGrp(temp);
         for(Label l:grpsLa){
          listSelUsr.addItem((String)l.getValue());
        }//////////////////////e
        change.addListener(new Button.ClickListener() {

		public void buttonClick(ClickEvent event) {
		    if(usrName.isValid() && usrAdd.isValid() && usrMail.isValid() && usrDetail.isValid()){ 

                HashMap<String, String> h = new HashMap<String, String>();
                h.put("Name", (String) usrName.getValue());
                h.put("Address", (String) usrAdd.getValue());
                h.put("Email", (String) usrMail.getValue());
                h.put("Detail", (String) usrDetail.getValue());
		
		try{
                 dataH.setUsrDetail(h, (String) usrName.getValue(),temp2, 1);
                }catch(Exception e){
                  dataH.setUsrDetail(h,temp2+" ",temp2, 1);                 
		}
                comapp.usrListBuild();
                main.removeWindow(form1);
		    }
		}
        });
        reset.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
                usrName.setValue("");
                usrAdd.setValue("");
                usrMail.setValue("");
                usrDetail.setValue("");
            }
        });


    }

    private void createGrpForm(HashMap<String, String> detail, final String prev){
////////////////////////b
       String temp=prev;   
       if(temp.startsWith("<b>", 0)){
       temp = temp.substring(3, (temp.length() - 4));
      
    }
       final String temp2=temp;
       /////////////////////////////////e
        form1 = new Window("Group Detail");
        form1.setModal(true);/////////////////b
        ListSelect listSelUsr = new ListSelect("Group Users");
        ListSelect listSelUsr2 = new ListSelect("Group Roles");
        listSelUsr.setWidth("200px");
        listSelUsr.setHeight("150px");
        listSelUsr.setRows(5);
        listSelUsr.setNullSelectionAllowed(true);
        listSelUsr2.setWidth("200px");
        listSelUsr2.setHeight("150px");
        listSelUsr2.setRows(5);
        listSelUsr2.setNullSelectionAllowed(true);//////////////////////e
        HorizontalLayout hl1 = new HorizontalLayout();
        grpName = new TextField("Name", ((detail.get("Name") == null) ? " " : detail.get("Name")));
        grpDetail = new TextField("Detail", ((detail.get("Detail") == null) ? " " : detail.get("Detail")));
        grpDetail.setRows(7);
        ab1=new AbsoluteLayout();
        vl1 = (VerticalLayout) form1.getContent();
	vl1.addComponent(ab1);

	grpName.setRequired(true);

        ab1.addComponent(grpName,"top:10%;left:5%");
        ab1.addComponent(grpDetail,"top:40%;left:5%");
        hl1.addComponent(reset);
        hl1.addComponent(change);
///////////////////b
        ab1.addComponent(listSelUsr,"top:10%;left:48%");
	ab1.addComponent(listSelUsr2,"bottom:10%;left:48%");
///////////////////////////// e      
        hl1.setSpacing(true);
        hl1.setComponentAlignment(change, Alignment.MIDDLE_RIGHT);
        hl1.setComponentAlignment(reset, Alignment.MIDDLE_LEFT);
        ab1.addComponent(hl1,"top:85%;left:5%");
       
        //////
        /////////////////b
        List<Label> usrsLa=dataH.getGrpUsr(temp);
         for(Label l:usrsLa){
          listSelUsr.addItem((String)l.getValue());
        }
         List<Label> usrsLa2=dataH.getGrpRl(temp);
         for(Label l:usrsLa2){
          listSelUsr2.addItem((String)l.getValue());
        }/////////////////////////e
        change.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
		if(grpName.isValid()){

                HashMap<String, String> h = new HashMap<String, String>();
                h.put("Name", (String) grpName.getValue());
                h.put("Detail", (String) grpDetail.getValue());
                try{
                dataH.setGrpDetail(h, (String) grpName.getValue(), prev, 1);
                }catch(Exception e){
                dataH.setGrpDetail(h,temp2+" ", temp2, 1);
		}
                comapp.grpListBuild();

                main.removeWindow(form1);
		}
	    }
        });
        reset.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
                grpName.setValue("");
                grpDetail.setValue("");
            }
        });




    }
 private void createRlForm(HashMap<String, String> detail, final String prev){
//////////////b
         String temp=prev;   
       if(temp.startsWith("<b>", 0)){
	   temp = temp.substring(3, (temp.length() - 4));
        }
       final String temp2=temp;
	ListSelect listSelUsr = new ListSelect("Role Groups");
        listSelUsr.setWidth("250px");
        listSelUsr.setHeight("300px");
        listSelUsr.setRows(5);
        listSelUsr.setNullSelectionAllowed(true);///////////////////e
        form1 = new Window("Role Detail");
        form1.setModal(true);
        HorizontalLayout hl1 = new HorizontalLayout();
        rlName = new TextField("Name", ((detail.get("Name") == null) ? " " : detail.get("Name")));
        rlDetail = new TextField("Detail", ((detail.get("Detail") == null) ? " " : detail.get("Detail")));
        rlDetail.setRows(7);

	rlName.setRequired(true);

        ab1=new AbsoluteLayout();
        vl1 = (VerticalLayout) form1.getContent();
	vl1.addComponent(ab1);
        ab1.addComponent(rlName,"top:10%;left:5%");
        ab1.addComponent(rlDetail,"top:40%;left:5%");
        hl1.addComponent(reset);
        hl1.addComponent(change);
///////////////////b
        ab1.addComponent(listSelUsr,"top:10%;left:45%");
	
///////////////////////////// e      
        hl1.setSpacing(true);
        hl1.setComponentAlignment(change, Alignment.MIDDLE_RIGHT);
        hl1.setComponentAlignment(reset, Alignment.MIDDLE_LEFT);
        ab1.addComponent(hl1,"top:85%;left:5%");
      ///////////////b
        List<Label> rlsLa=dataH.getRlGrp(temp);
         for(Label l:rlsLa){
          listSelUsr.addItem((String)l.getValue());
        }/////////////////////////e
        change.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
		if(rlName.isValid()){ 
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("Name", (String) rlName.getValue());
                h.put("Detail", (String) rlDetail.getValue());
                try{
                dataH.setRlDetail(h, (String) rlName.getValue(), prev, 1);
                }catch(Exception e){
                  dataH.setRlDetail(h,temp2+" ",temp2, 1);
		}
                comapp.rlListBuild();

                main.removeWindow(form1);
		}
	    }
        });
        reset.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
                rlName.setValue("");
                rlDetail.setValue("");
            }
        });




    }
}
