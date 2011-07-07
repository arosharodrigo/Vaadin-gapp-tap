package l1;
import com.vaadin.ui.TabSheet;
import java.io.Serializable;

public class ControlTabs extends TabSheet implements Serializable {
 ControlTabs tabSheet1;
public ComApp comapp;
   
  public ControlTabs(ComApp comapp) {
        this.comapp=comapp;
        tabSheet1=this;
        tabSheet1.setHeight("720px");
        tabSheet1.setWidth("1020px");




    }


}
