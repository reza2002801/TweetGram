package controllers;

import config.Config;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ChangePersonalInfoTabController {
    private static Logger logger = LogManager.getLogger(ChangePersonalInfoTabController.class);
    @FXML
    private TextField CPIbiotxt;
    @FXML
    private TextField CPIfirstNametxt;
    @FXML
    private TextField CPIlastNametxt;
    @FXML
    private TextField CPIemailtxt;
    @FXML
    private TextField CPIphonenumbertxt;
    @FXML
    private DatePicker CPIbirthdayDtPicker;
    @FXML
    private Circle CPIprofileFrame;
    @FXML
    private Label CPIlbl;
    private ServerCommunicator serverCommunicator;



    //------------------methods---------------------------------


    public void initialize() throws IOException {
        logger.debug("in updateChangePersonalInfoTab from ChangePersonalInfoTabController class ");
        this.serverCommunicator=CurrentClient.getClientManager();
        if(serverCommunicator.isConnected()) {
//        List<User> accounts=new Filemethods().loadFromFile();
        User currentUser= CurrentClient.getUser();
        CPIbiotxt.setText(currentUser.getBio());
        CPIfirstNametxt.setText(currentUser.getFirstname());
        CPIlastNametxt.setText(currentUser.getLastname());
        CPIemailtxt.setText(currentUser.getEmail());
        CPIphonenumbertxt.setText(currentUser.getPhonenumber());
        if(!currentUser.getProfilepicUrl().equals("")){
            CPIprofileFrame.setFill(new ImagePattern(serverCommunicator.getImage(currentUser.getProfilepicUrl())));
        }


        }else{
            User currentUser= CurrentClient.getUser();
            CPIbiotxt.setText(currentUser.getBio());
            CPIfirstNametxt.setText(currentUser.getFirstname());
            CPIlastNametxt.setText(currentUser.getLastname());
            CPIemailtxt.setText(currentUser.getEmail());
            CPIphonenumbertxt.setText(currentUser.getPhonenumber());
            if(!currentUser.getProfilepicUrl().equals("")){
                CPIprofileFrame.setFill(new ImagePattern(AccountsMethods.imageLoader(currentUser.getProfilepicUrl())));
            }
        }

//        CPIbirthdayDtPicker.se
    }

    public void changeBio() throws IOException {
        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("PI");
        serverCommunicator.sendClicked("changeBio");
        logger.debug("in changeBio from ChangePersonalInfoTabController class on values");
        Config config2=Config.getConfig("other");
        serverCommunicator.sendClicked(CPIbiotxt.getText());
//        List<User> accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(accounts,LoginController.USERNAME).setBio(CPIbiotxt.getText());
//        new Filemethods().saveToFile(accounts);
        CPIlbl.setText(config2.getProperty("TaskDone"));
        }else{

        }
    }
    public void changeFirstName() throws IOException {

        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("PI");
        serverCommunicator.sendClicked("changeFirstName");
        logger.debug("in changeFirstName from ChangePersonalInfoTabController class on values");
        Config config2=Config.getConfig("other");
        serverCommunicator.sendClicked(CPIfirstNametxt.getText());
//        List<User> accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(accounts,LoginController.USERNAME).setFirstname(CPIfirstNametxt.getText());
//        new Filemethods().saveToFile(accounts);
        CPIlbl.setText(config2.getProperty("TaskDone"));

        }else{

        }

    }
    public void changeLastName() throws IOException {
        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("PI");
        serverCommunicator.sendClicked("changeLastName");
        logger.debug("in changeLastName from ChangePersonalInfoTabController class on values");
        Config config2=Config.getConfig("other");
        serverCommunicator.sendClicked(CPIlastNametxt.getText());
//        List<User> accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(accounts,LoginController.USERNAME).setLastname(CPIlastNametxt.getText());
//        new Filemethods().saveToFile(accounts);
        CPIlbl.setText(config2.getProperty("TaskDone"));
        }else{

        }

    }
    public void changeEmail() throws IOException {


        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("PI");
        serverCommunicator.sendClicked("changeEmail");
        logger.debug("in changeEmail from ChangePersonalInfoTabController class on values");
        Config config2=Config.getConfig("other");
        serverCommunicator.sendClicked(CPIlastNametxt.getText());
//        List<User> accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(accounts,LoginController.USERNAME).setEmail(CPIemailtxt.getText());
//        new Filemethods().saveToFile(accounts);
        CPIlbl.setText(config2.getProperty("TaskDone"));

        }else{

        }

    }
    public void changePhoneNumber() throws IOException {
        if(serverCommunicator.isConnected()) {

            serverCommunicator.sendClicked("PI");
        serverCommunicator.sendClicked("changePhoneNumber");

        logger.debug("in changePhoneNumber from ChangePersonalInfoTabController class on values");
        Config config2=Config.getConfig("other");
        serverCommunicator.sendClicked(CPIphonenumbertxt.getText());
//        List<User> accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(accounts,LoginController.USERNAME).setPhonenumber(CPIphonenumbertxt.getText());
//        new Filemethods().saveToFile(accounts);
        CPIlbl.setText(config2.getProperty("TaskDone"));



        }else{

        }

    }

//--------------------------------------
    public void changeProfilePicture() throws IOException {
        logger.debug("in changeProfilePicture from ChangePersonalInfoTabController class on values");

        if(serverCommunicator.isConnected()) {

        AccountsMethods.ChangeProfilePicture(LoginController.USERNAME,CPIprofileFrame);
//        List<User> accounts=new Filemethods().loadFromFile();
//        User currentUser=AccountsMethods.findAccount(accounts,LoginController.USERNAME);



        }else{

        }
    }

//-------------------------------------------------
    public void changeDateTime() throws IOException {
        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("PI");
        serverCommunicator.sendClicked("changeDateTime");
        logger.debug("in changeDateTime from ChangePersonalInfoTabController class on values");
        serverCommunicator.sendClicked(CPIbirthdayDtPicker.getValue().toString());
//        List<User> accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(accounts,LoginController.USERNAME).setBirthday(CPIbirthdayDtPicker.getValue().toString());
//        new Filemethods().saveToFile(accounts);


        }else{

        }

    }

}
