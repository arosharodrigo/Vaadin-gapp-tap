package Gapp;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.SplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.*;

public class SimpleAddressBook extends Application {
public void init() {
   final Window mainWindow = 
      new Window("Myproject Application");

   Label label = new Label("Hello Vaadin user");
   mainWindow.addComponent(label);

   mainWindow.addComponent(
      new Button("Click Me",
         new Button.ClickListener() {
         public void buttonClick(ClickEvent event) {
            mainWindow.showNotification(
               "Hellowww This Is Our Hellowww Worldddd");
         }
      }));

   setMainWindow(mainWindow);
}
    
}

