package l1;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
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

public class NewRole extends AbsoluteLayout implements Serializable{

    Window w;
    ComApp capp;
    DataHub dhub;
    TextField name,detail;
    Button reset, create;
    VerticalLayout v1;
    NewRole ab1;
    HorizontalLayout hl;
    ListSelect listSel;
    
    List<String> rlGrps = new ArrayList<String>();
   
    HashMap<String, String> rlD = new HashMap<String, String>();

    public NewRole(ComApp capp, DataHub dhub1, Window w1) {

        this.w = w1;
        this.capp = capp;
        this.dhub = dhub1;
        listSel = new ListSelect("Available Groups");
      

        listSel.addListener(new Property.ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                rlGrps.clear();
                Property grps = event.getProperty();
                Set hs1 = (Set) grps.getValue();
                Iterator it1 = (Iterator) hs1.iterator();
                while (it1.hasNext()) {
                   
                    rlGrps.add((String) it1.next());
                                   }

            }
        });
       
        name = new TextField("Role Name");
        detail = new TextField("Role Detail");
        detail.setWidth("350px");
        detail.setRows(7);
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
		try{
                rlD.put("Name", (String) name.getValue());
                rlD.put("Detail", (String) detail.getValue());
                (dhub).newRl(rlD, (String) name.getValue(),rlGrps);
		}catch(Exception e){
		}
            }
        });
        v1 = new VerticalLayout();
	// v1.setHeight("650px");
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
        
       
        ab1.addComponent(v1, "top:50px; left:80px");
        ab1.addComponent(listSel, "top:50px;right:100px");
      

    }
      public void setGrpList(List<Label> l1){
          listSel.removeAllItems();
          for(Label s:l1){
              listSel.addItem((String)s.getValue());

          }

      }
    

}
