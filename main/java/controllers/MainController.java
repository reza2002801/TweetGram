package controllers;

import com.google.gson.Gson;
import config.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.awt.*;
import java.io.IOException;

public class MainController {

    private static Logger logger = LogManager.getLogger(MainController.class);
    @FXML
    private AnchorPane gb;
    @FXML
    private Button Infobtn;
    @FXML
    private Button CPIbtn;
    @FXML
    private Button viewListbtn;
    @FXML
    private Button myTweetbtn;
    @FXML
    private Button Notifbtn;
private ServerCommunicator serverCommunicator;
private Gson gson ;
    //--------------------methods---------------------------
    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }
    public void goToInfoTab(){
        logger.debug("in findAccount from MainController class on values");

        Config config=Config.getConfig("fxml");
        Config config1=Config.getConfig("style");
        gb.getChildren().clear();
        Infobtn.setStyle(config1.getProperty("white"));
        CPIbtn.setStyle(config1.getProperty("blue"));
        viewListbtn.setStyle(config1.getProperty("blue"));
        myTweetbtn.setStyle(config1.getProperty("blue"));
        Notifbtn.setStyle(config1.getProperty("blue"));

//        logOutbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        ChangePasswordbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");


        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("InfoTab")));

        try {
            gb.getChildren().add((Node) loader.load());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void backToHome(ActionEvent event) throws Exception {
        logger.debug("in backToHome from MainController class on values"+event);
        Config config=Config.getConfig("fxml");
        new ChangeScene().change_scene(event,config.getProperty("HomePage"));
    }
    public void goToViewListTab(){
        logger.debug("in goToViewListTab from MainController class on values");
        Config config=Config.getConfig("fxml");
        Config config1=Config.getConfig("style");
        gb.getChildren().clear();
        viewListbtn.setStyle(config1.getProperty("white"));
        CPIbtn.setStyle(config1.getProperty("blue"));
        Infobtn.setStyle(config1.getProperty("blue"));
        myTweetbtn.setStyle(config1.getProperty("blue"));
        Notifbtn.setStyle(config1.getProperty("blue"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ViewListTab")));

        try {
            gb.getChildren().add((Node) loader.load());
            ViewListTabController childController=loader.getController();
            childController.setMainPage(this);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void goToProfile(String UserName) throws IOException {
        logger.debug("in goToProfile from MainController class on values"+UserName);

        Config config=Config.getConfig("fxml");
//        gb.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ProfileComponent")));


        try {
            gb.getChildren().add((Node) loader.load());
            ProfileComponentController childController=loader.getController();
            childController.setMainPage(this);
            childController.updateProfile(UserName);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void goToChangePersonalInfoTab(){
        logger.debug("in goToChangePersonalInfoTab from MainController class on values");
        Config config=Config.getConfig("fxml");
        Config config1=Config.getConfig("style");
        gb.getChildren().clear();
        CPIbtn.setStyle(config1.getProperty("white"));
        viewListbtn.setStyle(config1.getProperty("blue"));

        Infobtn.setStyle(config1.getProperty("blue"));
        myTweetbtn.setStyle(config1.getProperty("blue"));
        Notifbtn.setStyle(config1.getProperty("blue"));

//        logOutbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        ChangePasswordbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");


        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ChangePersonalInfoTab")));

        try {
            gb.getChildren().add((Node) loader.load());
            ChangePersonalInfoTabController childController=loader.getController();
//            childController.updateChangePersonalInfoTab();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void goToMyTweetTab(){
        logger.debug("in goToMyTweetTab from MainController class on values");
        Config config=Config.getConfig("fxml");
        Config config1=Config.getConfig("style");
        gb.getChildren().clear();
        myTweetbtn.setStyle(config1.getProperty("white"));
        viewListbtn.setStyle(config1.getProperty("blue"));

        Infobtn.setStyle(config1.getProperty("blue"));
        CPIbtn.setStyle(config1.getProperty("blue"));
        Notifbtn.setStyle(config1.getProperty("blue"));

//        logOutbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        ChangePasswordbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");


        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("MyTweetTab")));

        try {
            gb.getChildren().add((Node) loader.load());
            MyTweetTabController childController=loader.getController();
            childController.updateMyTweet();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void goToNotificationtab(){
        logger.debug("in goToNotificationtab from MainController class on values");
        Config config=Config.getConfig("fxml");
        Config config1=Config.getConfig("style");
        gb.getChildren().clear();
        Notifbtn.setStyle(config1.getProperty("white"));
        viewListbtn.setStyle(config1.getProperty("blue"));

        Infobtn.setStyle(config1.getProperty("blue"));
        CPIbtn.setStyle(config1.getProperty("blue"));
        myTweetbtn.setStyle(config1.getProperty("blue"));

//        logOutbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        ChangePasswordbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");


        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("NotificationTab")));

        try {
            gb.getChildren().add((Node) loader.load());
//            MyTweetTabController childController=loader.getController();
//            childController.updateMyTweet();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
