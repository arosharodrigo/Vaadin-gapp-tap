package l1;
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


public class NewUser extends AbsoluteLayout implements Property.ValueChangeListener{
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
      detail=new TextField("User Detail");
      detail.setWidth("350px");
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
            usrD.put("Name",(String)name.getValue());
            usrD.put("Address",(String)add.getValue());
            usrD.put("Email",(String)email.getValue());
            usrD.put("Detail",(String)detail.getValue());
            (dhub).newUsr(usrD,(String)name.getValue(), usrGrps);
            //w.showNotification((String)usrGrps.get(2));
            }

        });
      v1=new VerticalLayout();
      v1.setHeight("650px");
      v1.setWidth("400px");
      v1.setSpacing(true);
      hl=new HorizontalLayout();
      ab1=this;
      ab1.setWidth("1000px");
      ab1.setHeight("700px");
      v1.addComponent(name);
      v1.addComponent(add);
      v1.addComponent(email);
      v1.addComponent(detail);
      hl.setSpacing(true);
      hl.addComponent(create);
      hl.addComponent(reset);
      v1.addComponent(hl);
      listSel.setWidth("250px");
      listSel.setHeight("450");
      listSel.setRows(7);
      listSel.setNullSelectionAllowed(true);
      listSel.setMultiSelect(true);
      listSel.setImmediate(true);
     // listSel.addItem("a1");
     // listSel.addItem("a2");
     // listSel.addItem("a3");
      ab1.addComponent(v1,"top:50px; left:80px");
      ab1.addComponent(listSel,"top:50px;right:100px");

  }

    public void valueChange(ValueChangeEvent event) {
      usrGrps.clear();
        Property grps=event.getProperty();
       Set hs1=(Set)grps.getValue();
        Iterator it1=(Iterator)hs1.iterator();
        while(it1.hasNext()){
             w.showNotification("h111");
            usrGrps.add((String)it1.next());
            // w.showNotification((String)usrGrps.get(1));
        }
       


    }
 public void setGrpList(List<Label> l1){
          listSel.removeAllItems();
          for(Label s:l1){
              listSel.addItem((String)s.getValue());

          }

      }
    
}
