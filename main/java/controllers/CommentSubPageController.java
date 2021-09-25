package controllers;

import com.google.gson.Gson;
import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Comment;
import models.Tweet;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CommentSubPageController {
    private static Logger logger = LogManager.getLogger(CommentSubPageController.class);
    @FXML
    private GridPane gp;
    @FXML
    private AnchorPane ap;
    @FXML
    private SplitPane sp;
    private Comment comment;
    @FXML
    private TextField Messagetxt;
    private Tweet tweet;
    private ServerCommunicator serverCommunicator;
    private Gson gson;

    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }
    public void loadComments(LinkedList<Comment> Comments, Tweet tw) throws IOException {
        logger.debug("in loadComments from CommentSubPageController class on values"+Comments+"and"+tw);
        Config config=Config.getConfig("fxml");
        double t=0;
        this.tweet=tw;
        if(this.tweet!=(null)){
            serverCommunicator.sendClicked("TWEET");
            serverCommunicator.sendClicked(gson.toJson(this.tweet));
            serverCommunicator.sendClicked("f");
            serverCommunicator.sendClicked(gson.toJson(this.tweet));
        }

//        TweetMethods.updateTweet(this.tweet);
        for (int i=0;i<Comments.size();i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("CommentComponent")));
            t+=110;
            gp.setPrefHeight(t);
            try {
                gp.add((Node)loader.load(),0,i+1);
                Config config3=Config.getConfig("number");
                gp.setVgap(Integer.valueOf(config3.getProperty("VGap")));
                CommentComponentController childController=loader.getController();
                childController.loadComments(Comments.get(i));
                childController.setTweet(this.tweet);
                    childController.setCspc(this);
//                gp.setLayoutX(+150);
                gp.setLayoutY(-10);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void SeeComments(Comment t){
        logger.debug("in SeeComments from CommentSubPageController class on values"+t);
        Config config=Config.getConfig("fxml");
        this.comment=t;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("CommentSubPage")));

        try {
            ap.getChildren().add((Node) loader.load());
            CommentSubPageController childController=loader.getController();
            childController.loadComments(t.getComments(),t);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void addComment() throws IOException {
        logger.debug("in addComment from CommentSubPageController class on values");
        serverCommunicator.sendClicked("TWEET");
        serverCommunicator.sendClicked(gson.toJson(this.tweet));
        serverCommunicator.sendClicked("addComment");
        try{
            this.tweet.getComments().add(new Comment(Messagetxt.getText(),LoginController.USERNAME));
            loadComments(this.tweet.getComments(), this.tweet);
            serverCommunicator.sendClicked(gson.toJson(this.tweet));


        }catch (Exception e){
            this.tweet.getComments().add(new Comment(Messagetxt.getText(),LoginController.USERNAME));
            loadComments(this.tweet.getComments(), this.tweet);
            serverCommunicator.sendClicked(gson.toJson(this.tweet));

        }


    }
    public void disApear(){
        sp.setVisible(false);
    }
}
