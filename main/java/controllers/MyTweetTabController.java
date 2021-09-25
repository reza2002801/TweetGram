package controllers;

import com.google.gson.Gson;
import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.Tweet;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MyTweetTabController {
    private static Logger logger = LogManager.getLogger(MyTweetTabController.class);
    @FXML
    private GridPane myTweetgp;
    @FXML
    private ScrollPane sp;
    @FXML
    private TextField myTweettxt;
    @FXML
    private Label Attachlbl;
    private String pictureLink="";
    private ServerCommunicator serverCommunicator;
    private Gson gson ;
    public void initialize(){
        this.gson=new Gson();
        this.serverCommunicator=CurrentClient.getClientManager();
    }
    public void updateMyTweet() throws IOException {
        logger.debug("in updateMyTweet from MyTweetTabController class on values");

        if(serverCommunicator.isConnected()) {
        Config config3=Config.getConfig("number");
        Config config=Config.getConfig("fxml");
//        List<User> accounts=new Filemethods().loadFromFile();
        User currentUser= CurrentClient.getUser();
//        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        double t=0;
        for (int i=0;i<currentUser.getUsertweets().size();i++){
            if(currentUser.getUsertweets().get(i).getReportNumber()<Integer.valueOf(config3.getProperty("ReportNumberToDelete"))){
                FXMLLoader loader;
                if(!currentUser.getUsertweets().get(i).getPictureLink().equals("")){
                   loader = new FXMLLoader(getClass().getResource(config.getProperty("PictureTweetComponent")));
                }else {
                    loader = new FXMLLoader(getClass().getResource(config.getProperty("myTweetComponent")));
                }
                t+=120;
                myTweetgp.setPrefHeight(t);
                try {
                    myTweetgp.add((Node)loader.load(),0,i+1);

                    MyTweetComponentController childController=loader.getController();

                    childController.updateTweetComponent1(currentUser.getUsertweets().get(i));
                    config3=Config.getConfig("number");
                    myTweetgp.setVgap(Integer.valueOf(config3.getProperty("VGap")));
//                gp.setLayoutX(+150);
                    myTweetgp.setLayoutY(-20);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }


        }

        sp.setVvalue(sp.getVmax());


        }else{

        }

    }
    public void makeTweet(String tweetPhrase) throws IOException{
        logger.debug("in makeTweet from MyTweetTabController class on values"+tweetPhrase);

        if(serverCommunicator.isConnected()) {
        serverCommunicator.sendClicked("fu");

        Tweet newTweet=new Tweet(tweetPhrase,LoginController.USERNAME);
        newTweet.setPictureLink(this.pictureLink);
        serverCommunicator.sendClicked(gson.toJson(newTweet));
        if(this.pictureLink!=""){
            serverCommunicator.sendImage(file,this.pictureLink);
        }
//        List<User> a=new Filemethods().loadFromFile();
////        String s=AccountsMethods.findAccount(a,LoginController.USERNAME).getProfilepicUrl();
//        User currentUser=AccountsMethods.findAccount(a,LoginController.USERNAME);
//        AccountsMethods.findAccount(a,LoginController.USERNAME).getUsertweets().add(newTweet);
//        new Filemethods().saveToFile(a);
//
//        TweetMethods.addTweetToUserFollwers(newTweet,currentUser.getFollowers());

        myTweettxt.clear();
        }else{

        }

    }
    File file;
    public void loadImage() throws IOException {
        logger.debug("in loadImage from MyTweetTabController class on values");

        Config config2=Config.getConfig("other");
        if(serverCommunicator.isConnected()) {
        Random r=new Random();
        int t=r.nextInt(10000);
        String f=String.valueOf(t);
        file=AccountsMethods.getFile();

        this.pictureLink=f+".jpg";
        if(!this.pictureLink.equals("")){
            Attachlbl.setText(config2.getProperty("attachReaction"));
        }
        }else{

        }
    }

    public void writeTweet() throws IOException {
        logger.debug("in writeTweet from MyTweetTabController class on values");


        if(serverCommunicator.isConnected()) {
        if(!myTweettxt.getText().equals("")){

            makeTweet(myTweettxt.getText());
            updateMyTweet();
            this.pictureLink="";
            Attachlbl.setText("");
        }
        }else{

        }
    }

}
