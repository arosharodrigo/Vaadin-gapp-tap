package l1;

import com.vaadin.data.validator.*;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.io.Serializable;


public class NewUser extends AbsoluteLayout implements Property.ValueChangeListener, Serializable{
 Window w;
 ComApp capp;
 DataHub dhub;
 TextField name,add,email,detail;
 Button reset,create;
 VerticalLayout v1;
 NewUser ab1;
 HorizontalLayout hl;
 ListSelect listSel;
 List<String> usrGrps=new ArrayList<String>();
 HashMap<String, String> usrD=new HashMap<String, String>();

  public NewUser(ComApp capp,DataHub dhub1,Window w1){
      this.w=w1;
      this.capp=capp;
      this.dhub=dhub1;
      listSel=new ListSelect("Available Groups");
      listSel.addListener(this);
      name=new TextField("User Name");
      add=new TextField("User Address");
      email=new TextField("User Email");
      detail=new TextField("User Mobile No.");
      detail.setWidth("350px");
      detail.setRows(1);
      reset=new Button("Reset");
      reset.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
            name.setValue("");
            add.setValue("");
            email.setValue("");
            detail.setValue("");
            }
        });
      create=new Button("Create");
      create.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
		if(email.isValid() && detail.isValid() && name.isValid() && add.isValid()){
		try{
	    usrD.put("Name",(String)name.getValue());
            usrD.put("Address",(String)add.getValue());
            usrD.put("Email",(String)email.getValue());
            usrD.put("Detail",(String)detail.getValue());
            (dhub).newUsr(usrD,(String)name.getValue(), usrGrps);
	    name.setValue("");
            add.setValue("");
            email.setValue("");
            detail.setValue("");
		} catch(Exception e){
		}
		}
            }

        });
      v1=new VerticalLayout();
      // v1.setHeight("650px");
      v1.setWidth("400px");
      v1.setSpacing(true);
      hl=new HorizontalLayout();
      ab1=this;
      ab1.setWidth("99.9%");
      ab1.setHeight("700px");

      email.addValidator(new EmailValidator("Email address is invalid"));
      detail.addValidator(new RegexpValidator("[+]{1}[0-9]{11}","Not a number"));
      detail.addValidator(new StringLengthValidator("Use country code",12,12,true));
      name.setRequired(true); 
      add.setRequired(true);
      email.setRequired(true);
      detail.setRequired(true);

      v1.addComponent(name);
      v1.addComponent(add);
      v1.addComponent(email);
      v1.addComponent(detail);
      hl.setSpacing(true);
      hl.addComponent(create);
      hl.addComponent(reset);
      v1.addComponent(hl);
      listSel.setWidth("300px");
      listSel.setHeight("90%");
      listSel.setRows(7);
      listSel.setNullSelectionAllowed(true);
      listSel.setMultiSelect(true);
      listSel.setImmediate(true);
      ab1.addComponent(v1,"top:50px; left:80px");
      ab1.addComponent(listSel,"top:50px;right:100px");

  }

    public void valueChange(ValueChangeEvent event) {
      usrGrps.clear();
        Property grps=event.getProperty();
       Set hs1=(Set)grps.getValue();
        Iterator it1=(Iterator)hs1.iterator();
        while(it1.hasNext()){
              usrGrps.add((String)it1.next());
             }
       


    }
 public void setGrpList(List<Label> l1){
          listSel.removeAllItems();
          for(Label s:l1){
              listSel.addItem((String)s.getValue());

          }

      }
    
}
