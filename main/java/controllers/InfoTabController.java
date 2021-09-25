package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class InfoTabController {
    private static Logger logger = LogManager.getLogger(InfoTabController.class);
    @FXML
    private Circle profileFrame;
    @FXML
    private Label infoUserNamelbl;
    @FXML
    private Label infoFirstNamelbl;
    @FXML
    private Label infoLastNamelbl;
    @FXML
    private Label infoEmaillbl;
    @FXML
    private Label infoBiolbl;
    @FXML
    private Label infoBirthdaylbl;
    @FXML
    private Label infoPhoneNumberlbl;
    private ServerCommunicator serverCommunicator;


    public void initialize() throws IOException {
        this.serverCommunicator=CurrentClient.getClientManager();
        logger.debug("in updateInfoPage from InfoTabController class on values");
//        List<user> accounts=new Filemethods().loadFromFile();
//        List<User> accounts=new Filemethods().loadFromFile();
        if(serverCommunicator.isConnected()) {
        User currentUser= CurrentClient.getUser();

        infoUserNamelbl.setText(infoUserNamelbl.getText()+currentUser.getUsername());
        infoFirstNamelbl.setText(infoFirstNamelbl.getText()+currentUser.getFirstname());
        infoLastNamelbl.setText(infoLastNamelbl.getText()+currentUser.getLastname());
        infoBiolbl.setText(infoBiolbl.getText()+currentUser.getBio());
        infoEmaillbl.setText(infoEmaillbl.getText()+currentUser.getEmail());
        infoBirthdaylbl.setText(infoBirthdaylbl.getText()+currentUser.getBirthday());
        infoPhoneNumberlbl.setText(infoPhoneNumberlbl.getText()+currentUser.getPhonenumber());
        if(!currentUser.getProfilepicUrl().equals("")){
            profileFrame.setFill(new ImagePattern(serverCommunicator.getImage(currentUser.getProfilepicUrl())));
//            AccountsMethods.setProfile(profileFrame,currentUser);
        }

        }else{
            User currentUser= CurrentClient.getUser();

            infoUserNamelbl.setText(infoUserNamelbl.getText()+currentUser.getUsername());
            infoFirstNamelbl.setText(infoFirstNamelbl.getText()+currentUser.getFirstname());
            infoLastNamelbl.setText(infoLastNamelbl.getText()+currentUser.getLastname());
            infoBiolbl.setText(infoBiolbl.getText()+currentUser.getBio());
            infoEmaillbl.setText(infoEmaillbl.getText()+currentUser.getEmail());
            infoBirthdaylbl.setText(infoBirthdaylbl.getText()+currentUser.getBirthday());
            infoPhoneNumberlbl.setText(infoPhoneNumberlbl.getText()+currentUser.getPhonenumber());
            if(!currentUser.getProfilepicUrl().equals("")){
                profileFrame.setFill(new ImagePattern(AccountsMethods.imageLoader(currentUser.getProfilepicUrl())));
//            AccountsMethods.setProfile(profileFrame,currentUser);
            }
        }
    }
}
