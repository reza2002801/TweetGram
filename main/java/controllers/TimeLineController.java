package controllers;

import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Tweet;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class TimeLineController {
    private static Logger logger = LogManager.getLogger(TimeLineController.class);
    @FXML
    private GridPane gp;
    @FXML
    private AnchorPane ap;

    public void initialize() throws IOException {
        logger.debug("in loadFollowingsTweet from TimeLineController class on values");
        Config config3=Config.getConfig("number");
        gp.setVgap(Integer.valueOf(config3.getProperty("VGap")));

        Config config=Config.getConfig("fxml");
        if(CurrentClient.getClientManager().isConnected()) {
//        List<User> accounts=new Filemethods().loadFromFile();
//        User currentUser=AccountsMethods.findAccount(accounts,LoginController.USERNAME);
        User currentUser= CurrentClient.getUser();
        double t=0;
        for (int i=0;i<currentUser.getFollowingstweet().size();i++){
//            System.out.println(currentUser.getUsertweets().get(i));
            if(currentUser.getFollowingstweet().get(i).getReportNumber()<Integer.valueOf(config3.getProperty("ReportNumberToDelete"))&&!currentUser.getMuteduser().contains(currentUser.getFollowingstweet().get(i).getUsername())){
                FXMLLoader loader;
                if(!currentUser.getFollowingstweet().get(i).getPictureLink().equals("")){
                    loader = new FXMLLoader(getClass().getResource(config.getProperty("PictureTweetComponent")));
                }else {
                    loader = new FXMLLoader(getClass().getResource(config.getProperty("myTweetComponent")));
                }
                t+=100;
                gp.setPrefHeight(t);

                try {
                    config3=Config.getConfig("number");
                    gp.setVgap(Integer.valueOf(config3.getProperty("VGap")));
                    gp.add((Node)loader.load(),0,i+1);
                    MyTweetComponentController childController=loader.getController();
                    childController.updateTweetComponent1(currentUser.getFollowingstweet().get(i));
                    childController.setTlc(this);

                    gp.setLayoutY(10);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        }

        }else{

        }
    }
    public void showLikedUser(Tweet t){
        logger.debug("in showLikedUser from TimeLineController class on values"+t);
        Config config=Config.getConfig("fxml");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("LikedUserSubPage")));

        try {
            ap.getChildren().add((Node) loader.load());
            LikedUserSubPageController childController=loader.getController();
            childController.loadLikedUser(t.getLikeuser());

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void forwardTweet(Tweet t){
        logger.debug("in forwardTweet from TimeLineController class on values"+t);
        Config config=Config.getConfig("fxml");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ManualChooseTab")));

        try {
            ap.getChildren().add((Node) loader.load());
            ManualChooseTabController childController=loader.getController();
            childController.loadForward(t);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void SeeComments(Tweet t){
        logger.debug("in SeeComments from TimeLineController class on values"+t);
        Config config=Config.getConfig("fxml");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("CommentSubPage")));

        try {
            ap.getChildren().add((Node) loader.load());
            CommentSubPageController childController=loader.getController();
            System.out.println(t);
            childController.loadComments(t.getComments(),t);

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
