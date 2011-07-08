package l1;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.event.MouseEvents.ClickEvent;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.io.Serializable;

public class NewGroup extends AbsoluteLayout implements Serializable{

    Window w;
    ComApp capp;
    DataHub dhub;
    TextField name,detail;
    Button reset, create;
    VerticalLayout v1;
    NewGroup ab1;
    HorizontalLayout hl;
    ListSelect listSel;
    ListSelect listSel2;
    List<String> grpRls = new ArrayList<String>();
    List<String> grpUsrs = new ArrayList<String>();
    HashMap<String, String> grpD = new HashMap<String, String>();

    public NewGroup(ComApp capp, DataHub dhub1, Window w1) {
        this.w = w1;
        this.capp = capp;
        this.dhub = dhub1;
        listSel = new ListSelect("Available Roles");
        listSel2 = new ListSelect("Available Usrs");

        listSel.addListener(new Property.ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                grpRls.clear();
                Property grps = event.getProperty();
                Set hs1 = (Set) grps.getValue();
                Iterator it1 = (Iterator) hs1.iterator();
                while (it1.hasNext()) {
                   
                    grpRls.add((String) it1.next());
                    
                }

            }
        });
        listSel2.addListener(new Property.ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                grpUsrs.clear();
                Property grps = event.getProperty();
                Set hs1 = (Set) grps.getValue();
                Iterator it1 = (Iterator) hs1.iterator();
                while (it1.hasNext()) {
                    
                    grpUsrs.add((String) it1.next());
                    
                }

            }
        });
        name = new TextField("Group Name");
        detail = new TextField("Group Detail");
        detail.setWidth("350px");
        reset = new Button("Reset");
        reset.addListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                name.setValue("");
                detail.setValue("");
            }
        });
        create = new Button("Create");
        create.addListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                
                
                grpD.put("Name", (String) name.getValue());
                
                grpD.put("Detail", (String) detail.getValue());//////////////
                (dhub).newGrp(grpD, (String) name.getValue(),grpUsrs ,grpRls);
               
            }
        });
        v1 = new VerticalLayout();
        v1.setHeight("650px");
        v1.setWidth("400px");
        v1.setSpacing(true);
        hl = new HorizontalLayout();
        ab1 = this;
        ab1.setWidth("1000px");
        ab1.setHeight("700px");
        v1.addComponent(name);
        v1.addComponent(detail);
        hl.setSpacing(true);
        hl.addComponent(create);
        hl.addComponent(reset);
        v1.addComponent(hl);
        listSel.setWidth("250px");
        listSel.setHeight("300px");
        listSel.setRows(7);
        listSel.setNullSelectionAllowed(true);
        listSel.setMultiSelect(true);
        listSel.setImmediate(true);
       // listSel.addItem("a1");
        //listSel.addItem("a2");
       // listSel.addItem("a3");
        listSel2.setWidth("250px");
        listSel2.setHeight("300px");
        listSel2.setRows(7);
        listSel2.setNullSelectionAllowed(true);
        listSel2.setMultiSelect(true);
        listSel2.setImmediate(true);
        //listSel2.addItem("b1");
       // listSel2.addItem("b2");
       // listSel2.addItem("b3");
        ab1.addComponent(v1, "top:50px; left:80px");
        ab1.addComponent(listSel, "top:50px;right:100px");
         ab1.addComponent(listSel2, "bottom:70px;right:100px");

    }
     public void setRlList(List<Label> l1){
          listSel.removeAllItems();
          for(Label s:l1){
              listSel.addItem((String)s.getValue());

          }
          
      }
      public void setUsrList(List<Label> l1){
          listSel2.removeAllItems();
          for(Label s:l1){
              listSel2.addItem((String)s.getValue());

          }

      }

    
}
