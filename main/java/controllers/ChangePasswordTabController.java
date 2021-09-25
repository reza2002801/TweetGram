package controllers;

import config.Config;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

public class ChangePasswordTabController {
    private static Logger logger = LogManager.getLogger(ChangePasswordTabController.class);
    @FXML
    private PasswordField olderPasswordtxtfield;
    @FXML
    private PasswordField newPasswordtxtfield;
    @FXML
    private Label changePasswordlbl;
    private ServerCommunicator serverCommunicator;
    public void initialize() {
        this.serverCommunicator= CurrentClient.getClientManager();
    }

    public void changePasswod() throws IOException {
        logger.debug("in changePasswod from ChangePasswordTabController class");
        Config config2=Config.getConfig("other");
        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("SETTING");
        serverCommunicator.sendClicked("changePasswod");
        serverCommunicator.sendClicked(olderPasswordtxtfield.getText());
        serverCommunicator.sendClicked(newPasswordtxtfield.getText());
        String result = serverCommunicator.read();
        if(result.equals("PassRepeatWarning")){
            changePasswordlbl.setText(config2.getProperty("PassRepeatWarning"));
        }
        else if(result.equals("PassChangeDone")){
            changePasswordlbl.setText(config2.getProperty("PassChangeDone"));
        }
        else if(result.equals("WrongPassWarn")){
            changePasswordlbl.setText(config2.getProperty("WrongPassWarn"));
        }
        }else{

        }
//        List<User> accounts=new Filemethods().loadFromFile();
//        String olderpass=olderPasswordtxtfield.getText();
//        String newpass=newPasswordtxtfield.getText() ;
//        String accPass=AccountsMethods.findAccount(accounts,LoginController.USERNAME).getPassword();
//        if(accPass.equals(olderpass)){
//            if(newpass.equals(olderpass)){
//                changePasswordlbl.setText(config2.getProperty("PassRepeatWarning"));
//            }
//            else{
//                AccountsMethods.findAccount(accounts,LoginController.USERNAME).setPassword(newpass);
//                changePasswordlbl.setText(config2.getProperty("PassChangeDone"));
//                new Filemethods().saveToFile(accounts);
//            }
//        }else {
//            changePasswordlbl.setText(config2.getProperty("WrongPassWarn"));
//        }

    }
}
