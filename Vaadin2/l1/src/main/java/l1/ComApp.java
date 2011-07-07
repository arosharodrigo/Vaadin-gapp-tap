package l1;
import com.vaadin.Application;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.*;
import com.vaadin.data.*;
import com.vaadin.event.MouseEvents;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component.Event;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.Tab;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class ComApp extends Application implements TabSheet.SelectedTabChangeListener {

    Button grpDelB, usrDelB, usrAddB, usrRmvB, rlDelB, rlAddB, rlRmvB;
    ControlTabs tabLa;
    NewUser newUsr;
    NewGroup newGrp;
    NewRole newRl;
    VerticalLayout grpV, usrV, rlV;
    Panel grpPanel, usrPanel, rlPanel;
    AbsoluteLayout ab1;
    Window overallWin;
    ComApp comapp1;
    DataHub dataH;
    ThemeResource select1;
    List<Label> grp = new ArrayList<Label>();
    List<Label> usr = new ArrayList<Label>();
    List<Label> rl = new ArrayList<Label>();
    Label tempgrpHoldL;
    CheckBox tempgrpHoldC;
    List<Label> tempusrHoldL = new ArrayList<Label>();
    List<Label> temprlHoldL = new ArrayList<Label>();
    HashMap<Label, CheckBox> tempusrHold = new HashMap<Label, CheckBox>();
    HashMap<Label, CheckBox> temprlHold = new HashMap<Label, CheckBox>();

    @Override
    public void init() {
       
        comapp1 = this;
        overallWin = new Window("Combined View");
        overallWin.setBorder(5);
        select1 = new ThemeResource("../img/tickG.jpg");
        
        dataH=new DataHub(this,overallWin);

        tabLa=new ControlTabs(this);

        newUsr=new NewUser(this, dataH,overallWin);
        newGrp=new NewGroup(this, dataH,overallWin);
        newRl=new NewRole(this, dataH,overallWin);

        this.buildIniUi();

        ////////////////////////

        this.grpListBuild();
        this.usrListBuild();
        this.rlListBuild();

        ////////////////////////////
        
      
        tabLa.addListener(this);
        tabLa.addTab(newGrp,"Create Groups",null);
        tabLa.addTab(newUsr,"Create User",null);
        tabLa.addTab(newRl,"Create Roles",null);
        tabLa.addTab(ab1,"Overall Editor",null);
        overallWin.addComponent(tabLa);
        setMainWindow(overallWin);
    }

    public void grpListBuild() {
     overallWin.showNotification("grpList");        
	grp=dataH.getGrpList();
overallWin.showNotification("ok2");
        grpV.removeAllComponents();
        for (final Label la1 : grp) {
            final HorizontalLayout tempH = new HorizontalLayout();
            final CheckBox chkB = new CheckBox();
            final Button bt1 = new Button("Edit");
            bt1.setEnabled(false);
            bt1.addListener(new Button.ClickListener() {

                public void buttonClick(ClickEvent event) {
                comapp1.editGrp(la1,chkB,bt1);
                }
            });

            chkB.addListener(new Button.ClickListener() {

                public void buttonClick(ClickEvent event) {
                    boolean b1 = event.getButton().booleanValue();
                    if (b1) {

                        tempgrpHoldL = (la1);
                        tempgrpHoldC = (CheckBox) event.getButton();
                        la1.setValue("<b>" + la1.getValue() + "</b>");
                        la1.setContentMode(Label.CONTENT_XHTML);
                        bt1.setEnabled(true);
                        // la1.setIcon(select1);




                    } else {

                        String a = (String) la1.getValue();
                        a = a.substring(3, (a.length() - 4));
                        la1.setValue(a);
                        la1.setContentMode(Label.CONTENT_TEXT);
                        // la1.setIcon(null);
                        bt1.setEnabled(false);
                        tempgrpHoldL = null;
                        tempgrpHoldC = null;

                    }
                }
            });
            chkB.setImmediate(true);
            la1.setHeight("30px");
            la1.setWidth("70px");
            tempH.addComponent(la1);
            tempH.addComponent(bt1);
            tempH.addComponent(chkB);
            tempH.setHeight("30px");
            tempH.setWidth("290px");
            tempH.setComponentAlignment(la1, Alignment.MIDDLE_LEFT);
            tempH.setComponentAlignment(chkB, Alignment.MIDDLE_LEFT);
            grpV.addComponent(tempH);

        }
    }

    public void usrListBuild() {
        usr=dataH.getUsrList();
        usrV.removeAllComponents();
        for (final Label la1 : usr) {
            final HorizontalLayout tempH = new HorizontalLayout();
            final CheckBox chkB = new CheckBox();
            final Button bt1 = new Button("Edit");
            bt1.setEnabled(false);
            bt1.addListener(new Button.ClickListener() {

                public void buttonClick(ClickEvent event) {
                  comapp1.editUsr(la1,chkB,bt1);
                }
            });

            la1.setHeight("30px");
            la1.setWidth("100px");
            chkB.addListener(new Button.ClickListener() {

                public void buttonClick(ClickEvent event) {
                    boolean b1 = event.getButton().booleanValue();
                    if (b1) {

                        tempusrHold.put(la1, (CheckBox) event.getButton());
                        tempusrHoldL.add(la1);
                        la1.setValue("<b>" + la1.getValue() + "</b>");
                        la1.setContentMode(Label.CONTENT_XHTML);
                        bt1.setEnabled(true);

                        // la1.setIcon(select1);
                        // overallWin.showNotification(la1.getStyleName());


                    } else {

                        String a = (String) la1.getValue();
                        a = a.substring(3, (a.length() - 4));
                        la1.setValue(a);
                        la1.setContentMode(Label.CONTENT_TEXT);
                        bt1.setEnabled(false);
                        //  la1.setIcon(null);
                        tempusrHold.remove(la1);
                        tempusrHoldL.remove(la1);
                        // overallWin.showNotification(la1.getStyleName());
                       

                    }
                }
            });
            chkB.setImmediate(true);
            tempH.addComponent(la1);
            tempH.addComponent(bt1);
            tempH.addComponent(chkB);
            tempH.setHeight("30px");
            tempH.setWidth("290px");
            tempH.setComponentAlignment(la1, Alignment.MIDDLE_LEFT);
            tempH.setComponentAlignment(chkB, Alignment.MIDDLE_LEFT);
            usrV.addComponent(tempH);

        }



    }

    public void rlListBuild() {
        rl=dataH.getRlList();
        rlV.removeAllComponents();
        for (final Label la1 : rl) {
            final HorizontalLayout tempH = new HorizontalLayout();
            final CheckBox chkB = new CheckBox();
            final Button bt1 = new Button("Edit");
            bt1.setEnabled(false);
              bt1.addListener(new Button.ClickListener() {

                public void buttonClick(ClickEvent event) {
                  comapp1.editRl(la1,chkB,bt1);
                }
            });

            la1.setHeight("30px");
            la1.setWidth("100px");
            chkB.addListener(new Button.ClickListener() {

                public void buttonClick(ClickEvent event) {
                    boolean b1 = event.getButton().booleanValue();
                    if (b1) {

                        temprlHold.put(la1, (CheckBox) event.getButton());
                        temprlHoldL.add(la1);
                        la1.setValue("<b>" + la1.getValue() + "</b>");
                        la1.setContentMode(Label.CONTENT_XHTML);
                        bt1.setEnabled(true);

                        // la1.setIcon(select1);



                    } else {

                        String a = (String) la1.getValue();
                        a = a.substring(3, (a.length() - 4));
                        la1.setValue(a);
                        la1.setContentMode(Label.CONTENT_TEXT);
                        bt1.setEnabled(false);
                        //  la1.setIcon(null);

                        temprlHold.remove(la1);
                        temprlHoldL.remove(la1);

                    }
                }
            });
            chkB.setImmediate(true);
            tempH.addComponent(la1);
            tempH.addComponent(bt1);
            tempH.addComponent(chkB);
            tempH.setHeight("30px");

            tempH.setWidth("290px");
            tempH.setComponentAlignment(la1, Alignment.MIDDLE_LEFT);
            tempH.setComponentAlignment(chkB, Alignment.MIDDLE_LEFT);
            rlV.addComponent(tempH);

        }



    }

    public void processGrpPaintReq() {

      List<Label> usrListfGrp=dataH.getGrpUsr((String)tempgrpHoldL.getValue());
      List<Label>  rlListfGrp=dataH.getGrpRl((String)tempgrpHoldL.getValue());

        repaintUsr(usrListfGrp);
        repaintRl(rlListfGrp);
  }



    public void editGrp(Label l, CheckBox cb,Button b){
     String temp=(String)l.getValue();
     l.setContentMode(Label.CONTENT_TEXT);
      if(temp.startsWith("<b>", 0)){
       temp = temp.substring(3, (temp.length() - 4));
       l.setValue(temp);
    }
    HashMap grpDtl=dataH.getGrpDetail(temp);
    //overallWin.showNotification((String)usrDtl.get("Name"));
    CreateForm grpForm=new CreateForm(grpDtl, 2,overallWin,this,dataH);

    }
    public void editUsr(Label l, CheckBox cb,Button b){
       
     String temp=(String)l.getValue();
     l.setContentMode(Label.CONTENT_TEXT);
    if(temp.startsWith("<b>", 0)){
       temp = temp.substring(3, (temp.length() - 4));
       l.setValue(temp);
    }
    HashMap usrDtl=dataH.getUsrDetail(temp);
    //overallWin.showNotification((String)usrDtl.get("Name"));
    CreateForm usrForm=new CreateForm(usrDtl, 1,overallWin,this,dataH);
     
   
    // Apply those to a form and Display.
    //When Change is pressed sav data.




    }
    public void editRl(Label l, CheckBox cb,Button b){
      String temp=(String)l.getValue();
     l.setContentMode(Label.CONTENT_TEXT);
    if(temp.startsWith("<b>", 0)){
       temp = temp.substring(3, (temp.length() - 4));
       l.setValue(temp);
    }
    HashMap rlDtl=dataH.getRlDetail(temp);
    //overallWin.showNotification((String)usrDtl.get("Name"));
    CreateForm rlForm=new CreateForm(rlDtl, 3,overallWin,this,dataH);

    }



    public void repaintUsr(List<Label> usrL) {
        //CheckBox tempCB=new CheckBox();
        String x;

        for (Label la1 : usrL) {
            for (Label la2 : usr) {
                String temp = ((String) la1.getValue());
                if (temp.equals((String) la2.getValue())) {
                    la2.setIcon(select1);
                } else {

                    la2.setIcon(null);

                }

            }
        }

    }

    public void repaintRl(List<Label> RlL) {
        //CheckBox tempCB=new CheckBox();
        String x;

        for (Label la1 : RlL) {
            for (Label la2 : rl) {
                String temp = ((String) la1.getValue());
                if (temp.equals((String) la2.getValue())) {
                    la2.setIcon(select1);
                } else {

                    la2.setIcon(null);

                }

            }
        }

    }



    public void buildIniUi() {
        ab1 = new AbsoluteLayout();
        grpPanel = new Panel("Groups");
        usrPanel = new Panel("Users");
        rlPanel = new Panel("Roles");
        grpV = new VerticalLayout();
        usrV = new VerticalLayout();
        rlV = new VerticalLayout();

        ////// Controller Buttons.....
        // Button grpDelB,usrDelB,usrAddB,usrRmvB,rlDelB,rlAddB,rlRmvB;

        grpDelB = new Button("Delete");
        grpDelB.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
           comapp1.grpDel();
            }
        });
        usrDelB = new Button("Delete");
        usrDelB.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
           comapp1.usrDel();
            }
        });
        usrAddB = new Button("Add2Grp");
        usrAddB.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
           comapp1.addUsrGrp();
            }
        });
        usrRmvB = new Button("Remove");
        usrRmvB.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
           comapp1.rmvUsrGrp();
            }
        });
        rlAddB = new Button("Add2Grp");
        rlAddB.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
           comapp1.addRlGrp();
            }
        });
        rlDelB = new Button("Delete");
        rlDelB.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
           comapp1.rlDel();
            }
        });
        rlRmvB = new Button("Remove");
        rlRmvB.addListener(new Button.ClickListener() {

            public void buttonClick(ClickEvent event) {
           comapp1.rmvRlGrp();
            }
        });
        grpDelB.setWidth("80px");
        usrAddB.setWidth("80px");
        usrDelB.setWidth("80px");
        usrRmvB.setWidth("80px");
        rlAddB.setWidth("80px");
        rlDelB.setWidth("80px");
        rlRmvB.setWidth("80px");
        grpDelB.setImmediate(true);
        usrAddB.setImmediate(true);
        usrDelB.setImmediate(true);
        usrRmvB.setImmediate(true);
        rlAddB.setImmediate(true);
        rlDelB.setImmediate(true);
        rlRmvB.setImmediate(true);
        ////////////////


        grpV.setSpacing(true);
        usrV.setSpacing(true);
        rlV.setSpacing(true);


        grpPanel.setScrollable(true);
        usrPanel.setScrollable(true);
        rlPanel.setScrollable(true);

        grpPanel.setHeight("500px");
        grpPanel.setWidth("300px");
        usrPanel.setHeight("300px");
        usrPanel.setWidth("300px");
        rlPanel.setHeight("300px");
        rlPanel.setWidth("300px");

        grpPanel.addComponent(grpV);
        usrPanel.addComponent(usrV);
        rlPanel.addComponent(rlV);

        ab1.setHeight("700px");
        ab1.setWidth("1000px");

        ab1.addComponent(grpDelB, "bottom:100px;left:145px");
        ab1.addComponent(usrAddB, "top:100px;right:410px");
        ab1.addComponent(usrDelB, "top:150px;right:410px");
        ab1.addComponent(usrRmvB, "top:200px;right:410px");
        ab1.addComponent(rlRmvB, "bottom:100px;right:410px");
        ab1.addComponent(rlDelB, "bottom:150px;right:410px");
        ab1.addComponent(rlAddB, "bottom:200px;right:410px");
        ab1.addComponent(grpPanel, "top:50px;left:50px");
        ab1.addComponent(usrPanel, "top:50px;right:100px");
        ab1.addComponent(rlPanel, "bottom:50px;right:100px");

    }

   
    private void grpDel() {
      String temp=(String)tempgrpHoldL.getValue();
      overallWin.showNotification(temp);
       tempgrpHoldL.setContentMode(Label.CONTENT_TEXT);
      if(temp.startsWith("<b>", 0)){
       temp = temp.substring(3, (temp.length() - 4));
       tempgrpHoldL.setValue(temp);
    }
       overallWin.showNotification(temp);  
      dataH.deleteGrpDB(temp);
    }

    private void usrDel() {
      for(Label l:tempusrHoldL){
      String temp=(String)l.getValue();
     l.setContentMode(Label.CONTENT_TEXT);
      if(temp.startsWith("<b>", 0)){
       temp = temp.substring(3, (temp.length() - 4));
       l.setValue(temp);
    }

      }
        
      for(Label l:tempusrHoldL){
          
        dataH.deleteUsrDB((String)l.getValue());
        }
     tempusrHoldL.removeAll(tempusrHoldL);
    }

    private void rmvRlGrp() {
      for(Label l:temprlHoldL){
      String temp=(String)l.getValue();
      l.setContentMode(Label.CONTENT_TEXT);
      if(temp.startsWith("<b>", 0)){
       temp = temp.substring(3, (temp.length() - 4));
       l.setValue(temp);
    }

      }



        dataH.removeRlfrmGrp(temprlHoldL,(String)tempgrpHoldL.getValue());

     temprlHoldL.removeAll(temprlHoldL);



    }

    private void rlDel() {
         for(Label l:temprlHoldL){
      String temp=(String)l.getValue();
     l.setContentMode(Label.CONTENT_TEXT);
      if(temp.startsWith("<b>", 0)){
       temp = temp.substring(3, (temp.length() - 4));
       l.setValue(temp);
    }

      }

      for(Label l:temprlHoldL){

        dataH.deleteRlDB((String)l.getValue());
        }
     temprlHoldL.removeAll(temprlHoldL);

    }

    private void addRlGrp() {
        for(Label l:temprlHoldL){
      String temp=(String)l.getValue();
      l.setContentMode(Label.CONTENT_TEXT);
      if(temp.startsWith("<b>", 0)){
       temp = temp.substring(3, (temp.length() - 4));
       l.setValue(temp);
    }

      }



        dataH.AddRltGrp(temprlHoldL,(String)tempgrpHoldL.getValue());

     temprlHoldL.removeAll(temprlHoldL);




    }

    private void rmvUsrGrp() {
          for(Label l:tempusrHoldL){
      String temp=(String)l.getValue();
      l.setContentMode(Label.CONTENT_TEXT);
      if(temp.startsWith("<b>", 0)){
       temp = temp.substring(3, (temp.length() - 4));
       l.setValue(temp);
    }

      }



        dataH.removeUsrfrmGrp(tempusrHoldL,(String)tempgrpHoldL.getValue());

     tempusrHoldL.removeAll(tempusrHoldL);


    }

    private void addUsrGrp() {
      for(Label l:tempusrHoldL){
            
      String temp=(String)l.getValue();
      l.setContentMode(Label.CONTENT_TEXT);
      if(temp.startsWith("<b>", 0)){
       temp = temp.substring(3, (temp.length() - 4));
       l.setValue(temp);
    }

      }
          overallWin.showNotification("okGrp1222"+(String)tempgrpHoldL.getValue());


        dataH.AddUsrtGrp(tempusrHoldL,(String)tempgrpHoldL.getValue());

     tempusrHoldL.removeAll(tempusrHoldL);


    }

    public void selectedTabChange(SelectedTabChangeEvent event) {
     TabSheet tempTab=event.getTabSheet();

     Tab t=tempTab.getTab(tempTab.getSelectedTab());
    String cap=t.getCaption();
       // overallWin.showNotification("psaw");
    if(cap.equals("Create Groups")){

         newGrp.setRlList(dataH.getRlList());
         newGrp.setUsrList(dataH.getUsrList());
        overallWin.showNotification("grp");
     }else if(cap.equals("Create User")){
           newUsr.setGrpList(dataH.getGrpList());
            overallWin.showNotification("usr");  
     }else if(cap.equals("Create Roles")){
            newRl.setGrpList(dataH.getGrpList());
             overallWin.showNotification("rl");
     } else if(cap.equals("Overall Editor")){
           comapp1.grpListBuild();
           comapp1.usrListBuild();
           comapp1.rlListBuild();
            overallWin.showNotification("ovallEditor");
     }

    } 
}
