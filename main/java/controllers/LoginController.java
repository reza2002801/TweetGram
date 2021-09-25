package controllers;

import com.google.gson.Gson;
import config.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.SignInResponse;
import utils.CurrentClient;

import java.io.File;
import java.io.IOException;
import java.util.List;



public class LoginController {
    ServerCommunicator serverCommunicator;
    Gson gson;
    private static Logger logger = LogManager.getLogger(LoginController.class);
    public static String USERNAME;
    public static String PROFILE;
    @FXML
    private TextField usernametxt;
    @FXML
    private PasswordField passwordtxt;
    @FXML
    private Label loginlabel;
    @FXML
    private  Label cl;
    public void initialize(){
        serverCommunicator= CurrentClient.getClientManager();
        gson=new Gson();

    }
    public void goToSigninpage(ActionEvent event) throws Exception {
        logger.debug("in goToSigninpage from LoginController class on values"+event);
        Config config=Config.getConfig("fxml");
       new ChangeScene().change_scene(event,config.getProperty("SignUpPage"));
    }

    public void loginCheck(ActionEvent event) throws Exception {

        logger.debug("in loginCheck from LoginController class on values"+event);
        Config config2=Config.getConfig("other");
        Config config=Config.getConfig("fxml");
        if(serverCommunicator.isConnected()){
        serverCommunicator.sendClicked("SIGN_IN");
        String username=usernametxt.getText();
        String password=passwordtxt.getText();
        Gson gson = new Gson();
        SignInResponse sr = new SignInResponse(username,password);
        serverCommunicator.sendClicked(gson.toJson(sr));
        String res = serverCommunicator.read();
        if(res.equals("Login")){
            this.USERNAME=username;
            new ChangeScene().change_scene(event,config.getProperty("HomePage"));
        }
        else if(res.equals("WrongPassword")){
            loginlabel.setText(config2.getProperty("LoginWrongPass"));
        }
        else if(res.equals("WrongUserName")){
            loginlabel.setText(config2.getProperty("LoginNoAccFound")+username);
        }}
        else {

            this.USERNAME=usernametxt.getText();;
            new ChangeScene().change_scene(event,config.getProperty("HomePage"));
            System.out.println("n");
        }
    }
    public void connect() throws IOException{
        if(serverCommunicator.isConnected()){
            cl.setText("You are already connected");
        }else {
            try {
                CurrentClient.setClientManager(new ServerCommunicator());
                serverCommunicator= CurrentClient.getClientManager();
                cl.setText("Connected");
            }catch (Exception e){
                cl.setText("Try again");
            }

        }
    }

    }




