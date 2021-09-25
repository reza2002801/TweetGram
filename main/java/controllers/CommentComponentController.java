package controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import models.Tweet;
import models.Comment;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.MiniProfileResponse;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class CommentComponentController {
    private static Logger logger = LogManager.getLogger(CommentComponentController.class);
    @FXML
    private Label txtlbl;
    @FXML
    private Label Namelbl;
    @FXML
    private Circle Cr;
    private Comment comment;
    private ServerCommunicator serverCommunicator;
    private Gson gson;
    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson= new Gson();
    }

    public void setTweet(Tweet tweet) {
        logger.debug("in setTweet from CommentComponentController class on values"+tweet);
        this.tweet = tweet;
    }

    public Tweet tweet;
    private CommentSubPageController cspc;


    public void setCspc(CommentSubPageController cspc) {
        logger.debug("in setCspc from CommentComponentController class on values"+cspc);
        this.cspc = cspc;
    }

    public void loadComments(Comment comment) throws IOException {
        logger.debug("in loadComments from CommentComponentController class on values"+comment);
        serverCommunicator.sendClicked("User");
        serverCommunicator.sendClicked("showBiggerProfile");
        serverCommunicator.sendClicked(comment.getUsername());

////        List<User> a=new Filemethods().loadFromFile();
        String s=serverCommunicator.read();
        serverCommunicator.sendClicked("User");
        serverCommunicator.sendClicked("updateMiniProfile");
        serverCommunicator.sendClicked(comment.getUsername());
        MiniProfileResponse currentUser=gson.fromJson(serverCommunicator.read(),MiniProfileResponse.class);
        this.comment=comment;
//        this.tweet=currentUser.getUsertweets()/
        if(!s.equals("")){
//            Image image = new Image(s);
            Cr.setFill(new ImagePattern(serverCommunicator.getImage(s)));
        }
        Namelbl.setText(currentUser.getFirstName() +currentUser.getLastName()+" @"+currentUser.getUserName()+"."+comment.getDatetime());
        txtlbl.setText(comment.getTweetphrase());
    }

    public void seeComments(){

        logger.debug("in seeComments from CommentComponentController class on values");
        this.cspc.SeeComments(this.comment);
    }
}
