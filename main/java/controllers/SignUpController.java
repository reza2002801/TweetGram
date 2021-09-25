package controllers;

import com.google.gson.Gson;
import config.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.SignUpResponse;
import utils.CurrentClient;

import java.util.List;

public class SignUpController {
    private static Logger logger = LogManager.getLogger(SignUpController.class);
    @FXML
    private TextField signinusername;
    @FXML
    private TextField signinpassword;
    @FXML
    private TextField signinconfirmpass;
    @FXML
    private TextField signinemail;
    @FXML
    private TextField signinfirstname;
    @FXML
    private TextField signinlastname;
    @FXML
    private Label signinlabel1;
    private ServerCommunicator serverCommunicator;
    private Gson gson;
    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }


    public void signInCheck(ActionEvent event) throws Exception {
        if(serverCommunicator.isConnected()) {
            serverCommunicator.sendClicked("SIGN_UP");
            logger.debug("in signInCheck from SignUpController class on values" + event);
            Config config2 = Config.getConfig("other");
            Config config = Config.getConfig("fxml");
            String username = signinusername.getText();
            String password = signinpassword.getText();
            String corfirmpass = signinconfirmpass.getText();
            String email = signinemail.getText();
            String firstname = signinfirstname.getText();
            String lastname = signinlastname.getText();
            SignUpResponse sr = new SignUpResponse(username, firstname, lastname, email, password);
            serverCommunicator.sendClicked(gson.toJson(sr));
            String res = serverCommunicator.read();
            if (res.equals("SignUp")) {
                User NewUser = new User(username, firstname, lastname, email, password);
//                Accounts.add(NewUser);
//                new Filemethods().saveToFile(Accounts);
                LoginController.USERNAME = username;
                new ChangeScene().change_scene(event, config.getProperty("HomePage"));
            } else if (res.equals("ExistAccount")) {
                signinlabel1.setText(config2.getProperty("SignUpExistAccMess"));
            }
        }else{

        }
//        List<User> Accounts=new Filemethods().loadFromFile();
//        if(new AccountsMethods().isThereAccount(Accounts,username)){
//            signinlabel1.setText(config2.getProperty("SignUpExistAccMess"));
//        }else {
//            if(corfirmpass.equals(password)){
//                User NewUser=new User(username,firstname,lastname,email,password);
//                Accounts.add(NewUser);
//                new Filemethods().saveToFile(Accounts);
//                LoginController.USERNAME=username;
//                new ChangeScene().change_scene(event,config.getProperty("HomePage"));
//            }
//            else {
//                signinlabel1.setText(config2.getProperty("SignUpConfirmPassWrong"));
//            }
//
//        }



    }
    public void backToLogin(ActionEvent event) throws Exception {
        logger.debug("in backToLogin from SignUpController class on values"+event);
        Config config=Config.getConfig("fxml");
        new ChangeScene().change_scene(event,config.getProperty("LoginPage"));
    }
}
