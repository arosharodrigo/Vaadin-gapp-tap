package l1;

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


@SuppressWarnings("serial")
public class CreateForm extends VerticalLayout {

    Window form1;
    TextField usrName, usrAdd, usrMail, usrDetail,grpName,grpDetail,rlName,rlDetail;
    Button change, reset;
    VerticalLayout vl1;
    ComApp comapp;
    DataHub dataH;
    Window main;

    public CreateForm(HashMap<String, String> detail, int catgry, Window main1, ComApp app, DataHub d) {
        this.comapp = app;
        this.dataH = d;
        this.main = main1;
        final String labelVal = detail.get("Name");
        change = new Button("Change");
        reset = new Button("Reset");
        if (catgry == 1) {
            this.createUsrForm(detail, labelVal);

        } else if(catgry==2){
            this.createGrpForm(detail, labelVal);

        }else if(catgry==3){
            this.createRlForm(detail, labelVal);

        }

        vl1.setSpacing(true);
        vl1.setMargin(true);
        vl1.setImmediate(true);

        vl1.setWidth("500px");
        vl1.setHeight("450px");
        form1.setDraggable(false);
        form1.setResizable(false);
        form1.setClosable(false);

        main.addWindow(form1);




    }

    private void createUsrForm(HashMap<String, String> detail, final String prev) {

        form1 = new Window("User Detail");
        form1.setModal(true);
        HorizontalLayout hl1 = new HorizontalLayout();
        String userName = ((detail.get("Name") == null) ? " " : detail.get("Name"));
        main.showNotification(userName);
        usrName = new TextField("Name", ((detail.get("Name") == null) ? " " : detail.get("Name")));
        usrAdd = new TextField("Address", ((detail.get("Address") == (null)) ? "" : detail.get("Address")));
        usrMail = new TextField("Email", ((detail.get("Email") == (null)) ? "" : detail.get("Email")));
        usrDetail = new RichTextArea();
        vl1 = (VerticalLayout) form1.getContent();
        vl1.addComponent(usrName);
        vl1.addComponent(usrAdd);
        vl1.addComponent(usrMail);
        vl1.addComponent(usrDetail);
        hl1.addComponent(reset);
        hl1.addComponent(change);
        hl1.setSpacing(true);
        hl1.setComponentAlignment(change, Alignment.MIDDLE_RIGHT);
        hl1.setComponentAlignment(reset, Alignment.MIDDLE_LEFT);
        vl1.addComponent(hl1);
        change.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("Name", (String) usrName.getValue());
                h.put("Address", (String) usrAdd.getValue());
                h.put("Email", (String) usrMail.getValue());
                h.put("Detail", (String) usrDetail.getValue());
                main.showNotification((String) usrName.getValue());
                dataH.setUsrDetail(h, (String) usrName.getValue(), prev, 1);
                main.showNotification((dataH.getUsrList()).contains(new Label((String) usrName.getValue())) + "");
                comapp.usrListBuild();

                main.removeWindow(form1);
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

        form1 = new Window("Group Detail");
        form1.setModal(true);
        HorizontalLayout hl1 = new HorizontalLayout();
        grpName = new TextField("Name", ((detail.get("Name") == null) ? " " : detail.get("Name")));
        grpDetail = new RichTextArea();
        vl1 = (VerticalLayout) form1.getContent();
        vl1.addComponent(grpName);
        vl1.addComponent(grpDetail);
        hl1.addComponent(reset);
        hl1.addComponent(change);
        hl1.setSpacing(true);
        hl1.setComponentAlignment(change, Alignment.MIDDLE_RIGHT);
        hl1.setComponentAlignment(reset, Alignment.MIDDLE_LEFT);
        vl1.addComponent(hl1);
        change.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("Name", (String) grpName.getValue());
                h.put("Detail", (String) grpDetail.getValue());
                main.showNotification((String) grpName.getValue());
               // dataH.setGrpDetail(h, (String) grpName.getValue(), prev, 1);
                main.showNotification((dataH.getGrpList()).contains(new Label((String) grpName.getValue())) + "");
                comapp.grpListBuild();

                main.removeWindow(form1);
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

        form1 = new Window("Role Detail");
        form1.setModal(true);
        HorizontalLayout hl1 = new HorizontalLayout();
        rlName = new TextField("Name", ((detail.get("Name") == null) ? " " : detail.get("Name")));
        rlDetail = new RichTextArea();
        vl1 = (VerticalLayout) form1.getContent();
        vl1.addComponent(rlName);
        vl1.addComponent(rlDetail);
        hl1.addComponent(reset);
        hl1.addComponent(change);
        hl1.setSpacing(true);
        hl1.setComponentAlignment(change, Alignment.MIDDLE_RIGHT);
        hl1.setComponentAlignment(reset, Alignment.MIDDLE_LEFT);
        vl1.addComponent(hl1);
        change.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
                HashMap<String, String> h = new HashMap<String, String>();
                h.put("Name", (String) rlName.getValue());
                h.put("Detail", (String) rlDetail.getValue());
                main.showNotification((String) rlName.getValue());
                dataH.setGrpDetail(h, (String) rlName.getValue(), prev, 1);
                main.showNotification((dataH.getGrpList()).contains(new Label((String) rlName.getValue())) + "");
                comapp.rlListBuild();

                main.removeWindow(form1);
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
