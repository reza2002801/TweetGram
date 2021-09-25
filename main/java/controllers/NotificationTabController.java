package controllers;

import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.jmx.Server;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class NotificationTabController {
    private static Logger logger = LogManager.getLogger(NotificationTabController.class);
    @FXML
    private GridPane gp;
    @FXML
    private Button UserNotif;
    @FXML
    private Button SystemNotif;
    @FXML
    private AnchorPane ap;
    private ServerCommunicator serverCommunicator;


    public void initialize(){
        this.serverCommunicator=CurrentClient.getClientManager();
    }
    public void loadUserNotif() throws IOException {
        logger.debug("in loadUserNotif from NotificationTabController class on values");

        Config config1=Config.getConfig("style");
        Config config=Config.getConfig("fxml");

        gp.getChildren().clear();
        UserNotif.setStyle(config1.getProperty("white"));
        SystemNotif.setStyle(config1.getProperty("blue"));
//        List<User> accounts=new Filemethods().loadFromFile();
        if(serverCommunicator.isConnected()) {
        User currentUser= CurrentClient.getUser();
        double t=0;
        for (int i=0;i<currentUser.getUsernotif().size();i++){
            System.out.println(currentUser.getUsernotif().get(i));
            FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("RequestNotificationComponent")));
            t+=110;
            gp.setPrefHeight(t);
            try {
                gp.add((Node)loader.load(),0,i+1);
                Config config3=Config.getConfig("number");
                gp.setVgap(Integer.valueOf(config3.getProperty("VGap")));
                RequestNotificationComponentController childController=loader.getController();
                childController.updateNotification(currentUser.getUsernotif().get(i));
//                childController.setParentController(this);
//                gp.setLayoutX(+150);
                gp.setLayoutY(-10);

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        serverCommunicator.sendClicked("NOTIF");
        serverCommunicator.sendClicked("CLEAR");;
//        serverCommunicator.sendClicked(CurrentClient.getUser().getUsername());

        }else{

        }
//        AccountsMethods.findAccount(accounts,LoginController.USERNAME).getUsernotif().clear();
//        new Filemethods().saveToFile(accounts);
    }
    public void loadSystemNotif() throws IOException {
        logger.debug("in loadSystemNotif from NotificationTabController class on values");
        Config config1=Config.getConfig("style");
        Config config=Config.getConfig("fxml");
        gp.getChildren().clear();
        SystemNotif.setStyle(config1.getProperty("white"));
        UserNotif.setStyle(config1.getProperty("blue"));

        if(serverCommunicator.isConnected()) {
//        List<User> accounts=new Filemethods().loadFromFile();
        User currentUser=CurrentClient.getUser();
        double t=0;
        for (int i=0;i<currentUser.getSystemnotif().size();i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("RequestNotificationComponent")));
            t+=110;
            gp.setPrefHeight(t);
            try {
                gp.add((Node)loader.load(),0,i+1);
                gp.setVgap(5);
                RequestNotificationComponentController childController=loader.getController();
                childController.updateNotification(currentUser.getSystemnotif().get(i));
//                childController.setParentController(this);
//                gp.setLayoutX(+150);
                gp.setLayoutY(-10);

            }catch (Exception e){
                e.printStackTrace();
            }

        }

        }else{

        }
    }

}
