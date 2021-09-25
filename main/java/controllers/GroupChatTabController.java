package controllers;

import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class GroupChatTabController {
    private static Logger logger = LogManager.getLogger(GroupChatTabController.class);
    @FXML
    private AnchorPane ap;
    public void goToCategoryListView(){
        if(CurrentClient.getClientManager().isConnected()){
        logger.debug("in goToCategoryListView from GroupChatTabController class on values");
        Config config=Config.getConfig("fxml");
        ap.getChildren().clear();
//        viewListbtn.setStyle("-fx-background-color: #ffffff;"+"-fx-text-fill:  #282A35;");
//        CPIbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        Infobtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        myTweetbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        Notifbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("CategoryListView")));

        try {
            ap.getChildren().add((Node) loader.load());
            CategoryListViewController childController=loader.getController();
            childController.loadCategories();

        }catch (Exception e){
            e.printStackTrace();
        }}
    }
    public void goToMakeCategoryTab(){
        if(CurrentClient.getClientManager().isConnected()){
        logger.debug("in goToMakeCategoryTab from GroupChatTabController class on values");
        Config config=Config.getConfig("fxml");
        ap.getChildren().clear();
//        viewListbtn.setStyle("-fx-background-color: #ffffff;"+"-fx-text-fill:  #282A35;");
//        CPIbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        Infobtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        myTweetbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        Notifbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("MakeCategoryTab")));

        try {
            ap.getChildren().add((Node) loader.load());
//            CategoryListViewController childController=loader.getController();
//            childController.loadCategories();

        }catch (Exception e){
            e.printStackTrace();
        }}
    }
    public void goToManualChooseTab(){
        logger.debug("in goToManualChooseTab from GroupChatTabController class on values");
        Config config=Config.getConfig("fxml");
        ap.getChildren().clear();
//        viewListbtn.setStyle("-fx-background-color: #ffffff;"+"-fx-text-fill:  #282A35;");
//        CPIbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        Infobtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        myTweetbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        Notifbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ManualChooseTab")));

        try {
            ap.getChildren().add((Node) loader.load());
//            CategoryListViewController childController=loader.getController();
//            childController.loadCategories();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goToSendToAllTab(){
        logger.debug("in goToSendToAllTab from GroupChatTabController class on values");
        Config config=Config.getConfig("fxml");
        ap.getChildren().clear();
//        viewListbtn.setStyle("-fx-background-color: #ffffff;"+"-fx-text-fill:  #282A35;");
//        CPIbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        Infobtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        myTweetbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        Notifbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("SendToAllTab")));

        try {
            ap.getChildren().add((Node) loader.load());
//            CategoryListViewController childController=loader.getController();
//            childController.loadCategories();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void goToGroupChatTab() throws IOException {
        logger.debug("in goToGroupChatTab from GroupChatTabController class on values");
        Config config=Config.getConfig("fxml");
        ap.getChildren().clear();
//        viewListbtn.setStyle("-fx-background-color: #ffffff;"+"-fx-text-fill:  #282A35;");
//        CPIbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        Infobtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        myTweetbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        Notifbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("GroupsListView")));
//        List<User> accounts=new Filemethods().loadFromFile();

        try {
            ap.getChildren().add((Node) loader.load());
            GroupsListViewController childController=loader.getController();
            childController.loadGroups(CurrentClient.getUser().getGroupChats());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void goToGroupMakingTab() throws IOException {
        logger.debug("in goToGroupMakingTab from GroupChatTabController class on values");
        if(CurrentClient.getClientManager().isConnected()){
        Config config=Config.getConfig("fxml");
        ap.getChildren().clear();
//        viewListbtn.setStyle("-fx-background-color: #ffffff;"+"-fx-text-fill:  #282A35;");
//        CPIbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        Infobtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        myTweetbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");
//        Notifbtn.setStyle("-fx-background-color: #282A35;"+"-fx-text-fill:  #ffffff;");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("CreateGroupTab")));
//        List<User> accounts=new Filemethods().loadFromFile();

        try {
            ap.getChildren().add((Node) loader.load());
//            GroupsListViewController childController=loader.getController();
//            childController.loadGroups(AccountsMethods.findAccount(accounts,LoginController.USERNAME).getGroupChats());
        }catch (Exception e){
            e.printStackTrace();
        }}
    }

}
