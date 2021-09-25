package controllers;

import config.Config;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.Pane;
import models.Change;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class PrivacySubPageController {
    private static Logger logger = LogManager.getLogger(PrivacySubPageController.class);
    @FXML
    Pane gp;
    @FXML
    Button privacybtn;
    @FXML
    MenuButton lastseenmb;
    @FXML
    MenuButton activationmb;
    @FXML
    MenuButton privacymb;
    private ServerCommunicator serverCommunicator;
    public void initialize() throws IOException {

        logger.debug("in updateMenubutton from PrivacySubPageController class on values");

        serverCommunicator=CurrentClient.getClientManager();
//        if(serverCommunicator.isConnected()) {
        Config config2=Config.getConfig("other");
        List<User> Accounts=new Filemethods().loadFromFile();
//        user currentuser=AccountsMethods.findAccount(Accounts,LoginController.USERNAME);
        if(CurrentClient.getUser().getIslastseen()==true){
            lastseenmb.setText(config2.getProperty("PrivacyOptionEnable"));
        }else {
            lastseenmb.setText(config2.getProperty("PrivacyOptionDisabled"));
        }
        if(CurrentClient.getUser().getIsactive()==true){
            activationmb.setText(config2.getProperty("PrivacyOptionEnable"));
        }else {
            activationmb.setText(config2.getProperty("PrivacyOptionDisabled"));
        }
        if(CurrentClient.getUser().getPublicaccount()==true){
            privacymb.setText(config2.getProperty("PrivacyOptionEnable"));
        }else {
            privacymb.setText(config2.getProperty("PrivacyOptionDisabled"));
        }

//    }else{
//
//    }
//        lastseenmb.setText("Disabled");
    }
    public void setLastSeenEnable() throws IOException {
        logger.debug("in setLastSeenEnable from PrivacySubPageController class on values");
        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("SETTING");
        serverCommunicator.sendClicked("setLastSeenEnable");
        Config config2=Config.getConfig("other");
//        List<User> Accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(Accounts,LoginController.USERNAME).setIslastseen(true);
//        new Filemethods().saveToFile(Accounts);
        lastseenmb.setText(config2.getProperty("PrivacyOptionEnable"));

        }else{
            List<Change> ch=new Filemethods().loadFromChange();
            if(ch.size()==0){
                Change change=new Change();
                change.setLastSeen(true);
                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
            else {
//                Change change=ch.get(0);
                ch.get(0).setLastSeen(true);
//                ch.add(change);
                new Filemethods().saveToChange(ch);
            }

        }
    }
    public void setPublicAccountEnable() throws IOException {
        logger.debug("in setPublicAccountEnable from PrivacySubPageController class on values");
        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("SETTING");
        serverCommunicator.sendClicked("setPublicAccountEnable");
        Config config2=Config.getConfig("other");
//        List<User> Accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(Accounts,LoginController.USERNAME).setPublicaccount(true);
//        new Filemethods().saveToFile(Accounts);
        privacymb.setText(config2.getProperty("PrivacyOptionEnable"));

        }else{
            List<Change> ch=new Filemethods().loadFromChange();
            if(ch.size()==0){
                Change change=new Change();
                change.setPublic(true);
                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
            else {
//                Change change=ch.get(0);
                ch.get(0).setPublic(true);
//                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
        }
    }
    public void setIsActiveEnable() throws IOException {
        logger.debug("in setIsActiveEnable from PrivacySubPageController class on values");
        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("SETTING");
        serverCommunicator.sendClicked("setIsActiveEnable");

        Config config2=Config.getConfig("other");
//        List<User> Accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(Accounts,LoginController.USERNAME).setIsactive(true);
//        new Filemethods().saveToFile(Accounts);
        activationmb.setText(config2.getProperty("PrivacyOptionEnable"));

        }else{
            List<Change> ch=new Filemethods().loadFromChange();
            if(ch.size()==0){
                Change change=new Change();
                change.setActive(true);
                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
            else {
//                Change change=ch.get(0);
                ch.get(0).setActive(true);
//                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
        }
    }
    public void setLastSeenDisable() throws IOException {
        logger.debug("in setLastSeenDisable from PrivacySubPageController class on values");
        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("SETTING");
        serverCommunicator.sendClicked("setLastSeenDisable");

        Config config2=Config.getConfig("other");
//        List<User> Accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(Accounts,LoginController.USERNAME).setIslastseen(false);
//        new Filemethods().saveToFile(Accounts);
        lastseenmb.setText(config2.getProperty("PrivacyOptionDisabled"));

        }else{
            List<Change> ch=new Filemethods().loadFromChange();
            if(ch.size()==0){
                Change change=new Change();
                change.setLastSeen(false);
                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
            else {
//                Change change=ch.get(0);
                ch.get(0).setLastSeen(false);
//                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
        }
    }
    public void setPublicAccountDisable() throws IOException {
        logger.debug("in setPublicAccountDisable from PrivacySubPageController class on values");
        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("SETTING");
        serverCommunicator.sendClicked("setPublicAccountDisable");

        Config config2=Config.getConfig("other");
//        List<User> Accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(Accounts,LoginController.USERNAME).setPublicaccount(false);
//        new Filemethods().saveToFile(Accounts);
        privacymb.setText(config2.getProperty("PrivacyOptionDisabled"));

        }else{
            List<Change> ch=new Filemethods().loadFromChange();
            if(ch.size()==0){
                Change change=new Change();
                change.setPublic(false);
                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
            else {
//                Change change=ch.get(0);
                ch.get(0).setPublic(false);
//                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
        }
//        privacymb.setVisible(false);
    }
        public void setIsActiveDisable() throws IOException {
        logger.debug("in setIsActiveDisable from PrivacySubPageController class on values");
        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("SETTING");
        serverCommunicator.sendClicked("setIsActiveDisable");

        Config config2=Config.getConfig("other");
//        List<User> Accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(Accounts,LoginController.USERNAME).setIsactive(false);
//        new Filemethods().saveToFile(Accounts);
        activationmb.setText(config2.getProperty("PrivacyOptionDisabled"));

        }else{
            List<Change> ch=new Filemethods().loadFromChange();
            if(ch.size()==0){
                Change change=new Change();
                change.setActive(false);
                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
            else {
//                Change change=
                ch.get(0).setActive(false);
//                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
        }
    }

}
