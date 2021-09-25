package controllers;

import com.google.gson.Gson;
import config.Config;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import models.Tweet;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class MyTweetComponentController {
    private static Logger logger = LogManager.getLogger(MyTweetComponentController.class);
    public ProfileComponentController profilecomp;
    private ServerCommunicator serverCommunicator;

    public void setProfilecomp(ProfileComponentController profilecomp) {
        this.profilecomp = profilecomp;
    }

    @FXML
    private Label txtlbl;
    @FXML
    private Label Namelbl;
    @FXML
    private Circle Cr;
    @FXML
    private Label ReTweetlbl;
    @FXML
    private Button likebtn;
    @FXML
    private Button retweetbtn;
    @FXML
    private Rectangle PictureFrame;
    private Tweet tweet;
    private TimeLineController tlc;
    private Gson gson;
    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }

    public void setTlc(TimeLineController tlc) {
        logger.debug("in setTlc from MyTweetComponentController class on values"+tlc);
        this.tlc = tlc;
    }

    public void updateTweetComponent(int i) throws IOException {
        logger.debug("in updateTweetComponent from MyTweetComponentController class on values"+i);
        List<User> a=new Filemethods().loadFromFile();
        String s=AccountsMethods.findAccount(a,LoginController.USERNAME).getProfilepicUrl();
        User currentUser=AccountsMethods.findAccount(a,LoginController.USERNAME);
        this.tweet=currentUser.getUsertweets().get(i);
        if(!s.equals("")){
            Image image = new Image(s);
            Cr.setFill(new ImagePattern(image));
        }
        Namelbl.setText(currentUser.getFirstname() +currentUser.getLastname()+" @"+currentUser.getUsername()+"."+currentUser.getUsertweets().get(i).getDatetime());
        txtlbl.setText(currentUser.getUsertweets().get(i).getTweetphrase());

    }
    public void updateTweetComponent1(Tweet t) throws IOException {
        logger.debug("in updateTweetComponent1 from MyTweetComponentController class on values"+t);
        Config config1=Config.getConfig("style");
        serverCommunicator.sendClicked("User");
        serverCommunicator.sendClicked("showBiggerProfile");
        serverCommunicator.sendClicked(t.getUsername());
//        List<User> a=new Filemethods().loadFromFile();
        String s=serverCommunicator.read();
        User currentUser=CurrentClient.getUser();
        this.tweet=t;
        if(!s.equals("")){
//            Image image = new Image(s);
            Cr.setFill(new ImagePattern(serverCommunicator.getImage(s)));
        }
        Namelbl.setText(currentUser.getFirstname() +currentUser.getLastname()+" @"+currentUser.getUsername()+"."+t.getDatetime());
        txtlbl.setText(t.getTweetphrase());
        if(t.getIsretweet()){
            ReTweetlbl.setText(ReTweetlbl.getText()+" from @"+t.getRetweetuser());
        }else {
            ReTweetlbl.setVisible(false);
        }
        if(t.getLikeuser().contains(LoginController.USERNAME)){
            likebtn.setStyle(config1.getProperty("white"));
        }
        if(!t.getPictureLink().equals("")){
//            Image image = new Image(t.getPictureLink());
            PictureFrame.setFill(new ImagePattern(serverCommunicator.getImage(t.getPictureLink())));
        }

    }
    public void likeTweet() throws IOException {//done
        logger.debug("in likeTweet from MyTweetComponentController class on values");
        serverCommunicator.sendClicked("TWEET");
        serverCommunicator.sendClicked(gson.toJson(this.tweet));
        serverCommunicator.sendClicked("likeTweet");

        Config config1=Config.getConfig("style");
//        if(!this.tweet.getLikeuser().contains(LoginController.USERNAME)){
//            List<User> a=new Filemethods().loadFromFile();
////            System.out.println(this.tweet);
//            this.tweet.getLikeuser().add(LoginController.USERNAME);
//            AccountsMethods.findAccount(a,LoginController.USERNAME).getLikedtweet().add(this.tweet);
//            TweetMethods.addLikedTweetToFollowers(this.tweet);
////            addlikedtweettofollowers(this.tweet);
//            try {
//                int indexofTweet=TweetMethods.indexOfTweet(this.tweet,AccountsMethods.findAccount(a,LoginController.USERNAME).getFollowingstweet());
//                AccountsMethods.findAccount(a,LoginController.USERNAME).getFollowingstweet().get(indexofTweet).getLikeuser().add(LoginController.USERNAME);
//            }
//            catch (Exception e){
//                System.out.println("");
//            }
//            new Filemethods().saveToFile(a);
//            TweetMethods.updateTweet(this.tweet);
//
//
//        }
        if(serverCommunicator.read().equals("Done")){
            likebtn.setStyle(config1.getProperty("white"));
        }

    }
    public void RetweetTweet() throws IOException {//done
        logger.debug("in RetweetTweet from MyTweetComponentController class on values");
        serverCommunicator.sendClicked("TWEET");
        serverCommunicator.sendClicked(gson.toJson(this.tweet));
        serverCommunicator.sendClicked("RetweetTweet");
        Config config1=Config.getConfig("style");
        Config config2=Config.getConfig("other");
//        List<User> accounts=new Filemethods().loadFromFile();
//        Tweet a=new Tweet(this.tweet.getTweetphrase(),LoginController.USERNAME);
//        a.setIsretweet(true);
//        if(this.tweet.getRetweetuser().equals("")){
//            a.setRetweetuser(this.tweet.getUsername());
//        }else {
//            a.setRetweetuser(this.tweet.getRetweetuser());
//
//        }
//        AccountsMethods.findAccount(accounts,LoginController.USERNAME).getUsertweets().add(a);
//        AccountsMethods.findAccount(accounts,LoginController.USERNAME).getRetweet().add(a);
////        MainPage.add_com_to_follower(e,a, MainPage.acc_info(e, login.UserName));
//        TweetMethods.addRetweetToFollowers(accounts,a,AccountsMethods.findAccount(accounts,LoginController.USERNAME));
//        new Filemethods().saveToFile(accounts);
        retweetbtn.setStyle(config1.getProperty("white"));
        retweetbtn.setText(config2.getProperty("RetweetReact"));

    }
    public void showLikedUser(){//done by own
        logger.debug("in showLikedUser from MyTweetComponentController class on values");
        this.tlc.showLikedUser(this.tweet);

    }
    public void forwardTweet(){// remains
        logger.debug("in forwardTweet from MyTweetComponentController class on values");
        this.tlc.forwardTweet(this.tweet);

    }
    public void seeComments(){
        logger.debug("in seeComments from MyTweetComponentController class on values");
        this.tlc.SeeComments(this.tweet);

    }
    public void reportTweet() throws IOException {//done
        logger.debug("in reportTweet from MyTweetComponentController class on values");
        serverCommunicator.sendClicked("TWEET");
        serverCommunicator.sendClicked(gson.toJson(this.tweet));
        serverCommunicator.sendClicked("reportTweet");
//        List<User> accounts=new Filemethods().loadFromFile();
//        this.tweet.setReportNumber(this.tweet.getReportNumber()+1);
//        try {
//            int indexofTweet=TweetMethods.indexOfTweet(this.tweet,AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowingstweet());
//            AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowingstweet().get(indexofTweet).getLikeuser().add(LoginController.USERNAME);
//        }catch (Exception e){
//            System.out.println("");
//        }
//        TweetMethods.updateTweet(this.tweet);

    }
    public void muteUser() throws IOException {//done
        serverCommunicator.sendClicked("TWEET");
        serverCommunicator.sendClicked("muteUser");
//        List<User> accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(accounts,LoginController.USERNAME).getMuteduser().add(this.tweet.getUsername());
////        TweetMethods.updateTweet(this.tweet);
//        new Filemethods().saveToFile(accounts);
    }



}
