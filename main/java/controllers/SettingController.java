package controllers;

import config.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class SettingController {
    private static Logger logger = LogManager.getLogger(SettingController.class);
    public void backToHome(ActionEvent event) throws Exception {
        Config config=Config.getConfig("fxml");
        new ChangeScene().change_scene(event,"../HomePage.fxml");
    }
    @FXML
    private AnchorPane gp;
    @FXML
    private Button privacybtn;
    @FXML
    private Button deleteAccountbtn;
    @FXML
    private Button logOutbtn;
    @FXML
    private Button ChangePasswordbtn;

    public void goTOPrivacyTab() throws IOException {
        logger.debug("in goTOPrivacyTab from SettingController class on values");
        Config config1=Config.getConfig("style");
        Config config=Config.getConfig("fxml");
        gp.getChildren().clear();
        privacybtn.setStyle(config1.getProperty("white"));
        deleteAccountbtn.setStyle(config1.getProperty("blue"));
        logOutbtn.setStyle(config1.getProperty("blue"));
        ChangePasswordbtn.setStyle(config1.getProperty("blue"));


        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("PrivacySubPage")));

        try {
            gp.getChildren().add((Node) loader.load());
//            PrivacySubPageController childController = loader.getController();
//
//            childController.updateMenubutton();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void goToLogOutTab(){
        logger.debug("in goToLogOutTab from SettingController class on values");
        Config config1=Config.getConfig("style");
        Config config=Config.getConfig("fxml");
        logOutbtn.setStyle(config1.getProperty("white"));
        privacybtn.setStyle(config1.getProperty("blue"));
        deleteAccountbtn.setStyle(config1.getProperty("blue"));
        ChangePasswordbtn.setStyle(config1.getProperty("blue"));

        gp.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("LogoutPage")));

        try {
            gp.getChildren().add((Node) loader.load());



        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void goToChangePasswordTab(){
        logger.debug("in goToChangePasswordTab from SettingController class on values");
        Config config1=Config.getConfig("style");
        Config config=Config.getConfig("fxml");
        gp.getChildren().clear();
        ChangePasswordbtn.setStyle(config1.getProperty("white"));
        deleteAccountbtn.setStyle(config1.getProperty("blue"));
        logOutbtn.setStyle(config1.getProperty("blue"));
        privacybtn.setStyle(config1.getProperty("blue"));


        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ChangePasswordtab")));

        try {
            gp.getChildren().add((Node) loader.load());
//            PrivacySubPageController childController = loader.getController();
//
//            childController.updateMenubutton();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goToDeleteAccountTab(){
        logger.debug("in goToDeleteAccountTab from SettingController class on values");
        Config config1=Config.getConfig("style");
        Config config=Config.getConfig("fxml");
        deleteAccountbtn.setStyle(config1.getProperty("white"));
        privacybtn.setStyle(config1.getProperty("blue"));
        logOutbtn.setStyle(config1.getProperty("blue"));
        ChangePasswordbtn.setStyle(config1.getProperty("blue"));

//        privacybtn.setStyle("fx-text-fill:  #ffffff");
        gp.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("DeleteAccountSubPage")));

        try {
            gp.getChildren().add((Node) loader.load());



        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
