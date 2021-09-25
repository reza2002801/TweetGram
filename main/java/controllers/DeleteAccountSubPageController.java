package controllers;

import config.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.jmx.Server;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class DeleteAccountSubPageController {
    private static Logger logger = LogManager.getLogger(DeleteAccountSubPageController.class);
    @FXML
    TextField deleteAccountPassword;
    @FXML
    Label deleteAccountLabel;
    private ServerCommunicator serverCommunicator;

    public void initialize() throws IOException{
        this.serverCommunicator= CurrentClient.getClientManager();

    }
    public void deleteAccount1() throws IOException {
        logger.debug("in deleteAccount1 from DeleteAccountSubPageController class on values");
//
        AccountsMethods.deleteFromFollowers(LoginController.USERNAME);
        AccountsMethods.deleteFromFollowings(LoginController.USERNAME);
        AccountsMethods.deleteFromTweets(LoginController.USERNAME);
        List<User> Accounts=new Filemethods().loadFromFile();

        Accounts.remove(AccountsMethods.findAccount(Accounts,LoginController.USERNAME));

//        del_from_followings(s);
//        del_from_followers(s);
//        del_from_tweets(s);
        new Filemethods().saveToFile(Accounts);
    }
    public void deleteAccount(ActionEvent event) throws Exception {
        logger.debug("in deleteAccount from DeleteAccountSubPageController class on values");

        Config config2=Config.getConfig("other");
        Config config=Config.getConfig("fxml");
        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("SETTING");
        serverCommunicator.sendClicked("deleteAccount");
        serverCommunicator.sendClicked(deleteAccountPassword.getText());
//        List<User> Accounts=new Filemethods().loadFromFile();
        String res=serverCommunicator.read();
        if(res.equals("Done")){
//            deleteAccount1();
            new ChangeScene().change_scene(event,config.getProperty("LoginPage"));
        }else {
            deleteAccountLabel.setText(config2.getProperty("DeleteAccWrongPass"));
        }
    }else{

    }

    }
}
