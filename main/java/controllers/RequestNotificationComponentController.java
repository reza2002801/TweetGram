package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import config.Config;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import models.*;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

import static models.notifpage.notif_find;
import static models.notifpage.notif_find1;

public class RequestNotificationComponentController {
    private static Logger logger = LogManager.getLogger(RequestNotificationComponentController.class);
    @FXML
    private Label Messagelbl;
    @FXML
    private Label Timelbl;
    @FXML
    private Circle profilecircle;
    @FXML
    private Button deletebtn;
    @FXML
    private Button confirmbtn;
    @FXML
    private AnchorPane ap;
    private String notifUserName;
    private Notification notification;
    private ServerCommunicator serverCommunicator;
    private Gson gson;

    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }



    public String getNotifUserName() {
        return notifUserName;
    }

    public void setNotifUserName(String notifUserName) {
        logger.debug("in setNotifUserName from RequestNotificationComponentController class on values"+notifUserName);
        this.notifUserName = notifUserName;
    }

    public Notification getNotification() {
        logger.debug("in getNotification from RequestNotificationComponentController class on values");
        return notification;
    }

    public void setNotification(Notification notification) {
        logger.debug("in setNotification from RequestNotificationComponentController class on values"+notification);
        this.notification = notification;
    }

    public void updateNotification(Notification notif) throws IOException {
        logger.debug("in updateNotification from RequestNotificationComponentController class on values"+notif);
        Config config2=Config.getConfig("other");
        serverCommunicator.sendClicked("User");
        serverCommunicator.sendClicked("showBiggerProfile");
        serverCommunicator.sendClicked(notif.getUsername());
        String s=serverCommunicator.read();
//        List<User> a=new Filemethods().loadFromFile();
//        String s=AccountsMethods.findAccount(a,notif.getUsername()).getProfilepicUrl();
        User currentUser=CurrentClient.getUser();
        if(!s.equals("")){
//            Image image = new Image(s);
            profilecircle.setFill(new ImagePattern(serverCommunicator.getImage(s)));
        }
        setNotifUserName(notif.getUsername());
        this.notifUserName=notif.getUsername();
        Timelbl.setText("@"+notif.getUsername()+"."+notif.getDatetime());
        Messagelbl.setText(notif.getMessage());
        this.notification=notif;
        setNotification(notif);
        if(!notif.getMessage().equals(config2.getProperty("followRequestMessage"))){
            deletebtn.setVisible(false);
            confirmbtn.setVisible(false);
        }

    }
    public void acceptFollowRequest() throws IOException {
        logger.debug("in acceptFollowRequest from RequestNotificationComponentController class on values");
        serverCommunicator.sendClicked("NOTIF");
        serverCommunicator.sendClicked("acceptFollowRequest");
        serverCommunicator.sendClicked(notifUserName);
        serverCommunicator.sendClicked(gson.toJson(notification));
//        List<User> a=new Filemethods().loadFromFile();
//
//        User currentUser=AccountsMethods.findAccount(a,LoginController.USERNAME);
//
//        AccountsMethods.findAccount(a,LoginController.USERNAME).getFollowers().add(notifUserName);
//
//        AccountsMethods.findAccount(a,notifUserName).getFollowings().add(LoginController.USERNAME);
//        //announce your request accepted
//        notif_find1(AccountsMethods.findAccount(a,notifUserName), AccountsMethods.findAccount(a,LoginController.USERNAME));
//        Profile.transfertweets(AccountsMethods.findAccount(a,LoginController.USERNAME), AccountsMethods.findAccount(a,notifUserName));
//        AccountsMethods.findAccount(a,LoginController.USERNAME).getUsernotif().remove(notification);
//        new Filemethods().saveToFile(a);
        ap.setVisible(false);
    }



    public void rejectFollowRequest() throws IOException {
        logger.debug("in rejectFollowRequest from RequestNotificationComponentController class on values");
        serverCommunicator.sendClicked("NOTIF");
        serverCommunicator.sendClicked("rejectFollowRequest");
        serverCommunicator.sendClicked(notifUserName);
        serverCommunicator.sendClicked(gson.toJson(notification));
//        List<User> a=new Filemethods().loadFromFile();
//
//        User currentUser=AccountsMethods.findAccount(a,LoginController.USERNAME);
//        notif_find(AccountsMethods.findAccount(a,notifUserName), AccountsMethods.findAccount(a,LoginController.USERNAME));
//        AccountsMethods.findAccount(a,LoginController.USERNAME).getUsernotif().remove(notification);
//        new Filemethods().saveToFile(a);
        ap.setVisible(false);

    }
}
