package controllers;

import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class ListViewerSubTabController  {
    private static Logger logger = LogManager.getLogger(ListViewerSubTabController.class);
    public ViewListTabController ViewList;

    public void setViewList(ViewListTabController viewList) {
        ViewList = viewList;
    }

    @FXML
    private GridPane viewListgp;
    @FXML
    private ScrollPane gggt;
    @FXML
    private ScrollPane sp;
    @FXML
    private AnchorPane gb;
    public void updateFollowers() throws IOException {
        logger.debug("in updateFollowers from ListViewerSubTabController class on values");
        Config config=Config.getConfig("fxml");
//        List<User> accounts=new Filemethods().loadFromFile();
        if(CurrentClient.getClientManager().isConnected()) {
        User currentUser= CurrentClient.getUser();
//        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        double t=0;
        if(currentUser ==null ){
            System.out.println("y");
        }
        else {
            for (int i=0;i<currentUser.getFollowers().size();i++){
                FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("UserMiniProfileComponent")));
                t+=110;
                viewListgp.setPrefHeight(t);
                try {
                    viewListgp.add((Node)loader.load(),0,i+1);
                    viewListgp.setVgap(5);
                    UserMiniProfileComponentController childController=loader.getController();
                    childController.updateMiniProfile(currentUser.getFollowers().get(i));
                    childController.setParentController(this);
//                gp.setLayoutX(+150);
                    viewListgp.setLayoutY(-10);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

        }else{

        }

    }
    public void updateFollowings() throws IOException {
        logger.debug("in updateFollowings from ListViewerSubTabController class on values");
        Config config=Config.getConfig("fxml");
            if(CurrentClient.getClientManager().isConnected()) {
//        List<User> accounts=new Filemethods().loadFromFile();
        User currentUser=CurrentClient.getUser();
//        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        double t=0;
        for (int i=0;i<currentUser.getFollowings().size();i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("UserMiniProfileComponent")));
            t+=110;
            viewListgp.setPrefHeight(t);
            try {
                viewListgp.add((Node)loader.load(),0,i+1);
                viewListgp.setVgap(5);
                UserMiniProfileComponentController childController=loader.getController();
                childController.updateMiniProfile(currentUser.getFollowings().get(i));
                childController.setParentController(this);
//                gp.setLayoutX(+150);
                viewListgp.setLayoutY(-10);

            }catch (Exception e){
                e.printStackTrace();
            }

        }

            }else{

            }
    }


    public void updateBlockedUser() throws IOException {
        logger.debug("in updateBlockedUser from ListViewerSubTabController class on values");
        Config config=Config.getConfig("fxml");
                if(CurrentClient.getClientManager().isConnected()) {
//        List<User> accounts=new Filemethods().loadFromFile();
        User currentUser=CurrentClient.getUser();
//        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        double t=0;
        for (int i=0;i<currentUser.getBlackList().size();i++){
                FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("UserMiniProfileComponent")));
                t+=110;
                viewListgp.setPrefHeight(t);
                try {
                    viewListgp.add((Node)loader.load(),0,i+1);
                    viewListgp.setVgap(5);
                    UserMiniProfileComponentController childController=loader.getController();
                    childController.updateMiniProfile(currentUser.getBlackList().get(i));
                    childController.setParentController(this);
    //                gp.setLayoutX(+150);
                    viewListgp.setLayoutY(-10);

                }catch (Exception e){
                    e.printStackTrace();
                }

        }

                }else{

                }
    }
//    @FXML
//    private UserMiniProfileComponentController childController;
//
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        childController.valueProperty().addListener((observable, oldValue, newValue) -> goToPro("mos"));
//    }
//    @FXML
//    private UserMiniProfileComponentController childController;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        childController.valueProperty().addListener(((observable, oldValue, newValue) -> goToPro("mos")));
//    }
}
