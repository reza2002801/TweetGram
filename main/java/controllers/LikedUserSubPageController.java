package controllers;

import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;

public class LikedUserSubPageController {
    private static Logger logger = LogManager.getLogger(LikedUserSubPageController.class);
    @FXML
    private GridPane gp;
    @FXML
    private AnchorPane ap;
    public void loadLikedUser(LinkedList<String> likeusers){
        logger.debug("in loadLikedUser from LikedUserSubPageController class on values"+likeusers);
        Config config=Config.getConfig("fxml");
        double t=0;
        for (int i=0;i<likeusers.size();i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("UserMiniProfileComponent")));
            t+=110;
            gp.setPrefHeight(t);
            try {
                gp.add((Node)loader.load(),0,i+1);
                Config config3=Config.getConfig("number");
                gp.setVgap(Integer.valueOf(config3.getProperty("VGap")));
                UserMiniProfileComponentController childController=loader.getController();
                childController.updateMiniProfile(likeusers.get(i));
//                childController.setParentController(this);
//                gp.setLayoutX(+150);
                gp.setLayoutY(-10);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void disapear(){
        ap.setVisible(false);
    }
}
