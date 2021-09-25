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

import java.awt.*;

public class ExpelorController {
    private static Logger logger = LogManager.getLogger(ExpelorController.class);
    @FXML
    private AnchorPane ap;
    @FXML
    private Button searchbtn;
    @FXML
    private Button trendbtn;
    public void backToHome(ActionEvent event) throws Exception {
        logger.debug("in backToHome from ExpelorController class on values");
        Config config=Config.getConfig("fxml");
        new ChangeScene().change_scene(event,config.getProperty("HomePage"));
    }
    public void goToSearchtab(){
        logger.debug("in goToSearchtab from ExpelorController class on values");
        Config config=Config.getConfig("fxml");
        Config config1=Config.getConfig("style");
        ap.getChildren().clear();

        searchbtn.setStyle(config1.getProperty("white"));
        trendbtn.setStyle(config1.getProperty("blue"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("SearchTab")));

        try {
            ap.getChildren().add((Node) loader.load());
            SearchTabController childController=loader.getController();
            childController.c();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void goToTrendingtab(){
        logger.debug("in goToTrendingtab from ExpelorController class on values");
        Config config=Config.getConfig("fxml");
        Config config1=Config.getConfig("style");
        ap.getChildren().clear();
        trendbtn.setStyle(config1.getProperty("white"));
        searchbtn.setStyle(config1.getProperty("blue"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("TimeLinePage")));

        try {
            ap.getChildren().add((Node) loader.load());
//            TimeLineController childController=loader.getController();
//            childController.loadFollowingsTweet();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
