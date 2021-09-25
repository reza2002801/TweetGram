package controllers;


import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class ViewListTabController {
    private static Logger logger = LogManager.getLogger(ViewListTabController.class);
    public MainController MainPage;
    public void setMainPage(MainController mainPage) {
        MainPage = mainPage;
    }
    @FXML
    private Button Followersbtn;
    @FXML
    private Button Followingsbtn;
    @FXML
    private Button BlockedUserbtn;
    @FXML
    private AnchorPane ap;
    public void showFollowers() throws IOException {
        logger.debug("in showFollowers from ViewListTabController class on values");
        Config config=Config.getConfig("fxml");
        Config config1=Config.getConfig("style");
        ap.getChildren().clear();

        Followersbtn.setStyle(config1.getProperty("white"));
        Followingsbtn.setStyle(config1.getProperty("blue"));
        BlockedUserbtn.setStyle(config1.getProperty("blue"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ListViewerSubTab")));

        try {
            ap.getChildren().add((Node) loader.load());
            ListViewerSubTabController childController = loader.getController();
            childController.setViewList(this);

            childController.updateFollowers();
        }catch (Exception e){
            e.printStackTrace();
        }



    }
    public void showFollowings(){
        logger.debug("in showFollowings from ViewListTabController class on values");
        Config config1=Config.getConfig("style");
        Config config=Config.getConfig("fxml");
        ap.getChildren().clear();
        Followingsbtn.setStyle(config1.getProperty("white"));
        BlockedUserbtn.setStyle(config1.getProperty("blue"));
        Followersbtn.setStyle(config1.getProperty("blue"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ListViewerSubTab")));

        try {
            ap.getChildren().add((Node) loader.load());
            ListViewerSubTabController childController = loader.getController();
            childController.setViewList(this);
            childController.updateFollowings();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void showBlockedUser(){
        logger.debug("in showBlockedUser from ViewListTabController class on values");
        Config config1=Config.getConfig("style");
        Config config=Config.getConfig("fxml");
        ap.getChildren().clear();
        BlockedUserbtn.setStyle(config1.getProperty("white"));
        Followingsbtn.setStyle(config1.getProperty("blue"));
        Followersbtn.setStyle(config1.getProperty("blue"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ListViewerSubTab")));
        try {
            ap.getChildren().add((Node) loader.load());
            ListViewerSubTabController childController = loader.getController();
            childController.setViewList(this);
            childController.updateBlockedUser();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
