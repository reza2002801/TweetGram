package controllers;

import com.google.gson.Gson;
import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.Change;
import models.Chat;
import models.message;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatSurfacePageController {
    private static Logger logger = LogManager.getLogger(ChatSurfacePageController.class);
    @FXML
    private GridPane myTweetgp;
    @FXML
    private TextField messagetxt;
    private Chat chat;
    private String pictureLink="";
    @FXML
    private Label Attachlbl;
    private ServerCommunicator serverCommunicator;
    private Gson gson;
    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }
    public void loadChatMessages(Chat chat) throws IOException {
        logger.debug("in loadChatMessages from ChatSurfacePageController class on values"+chat);
        Config config=Config.getConfig("fxml");
//        List<user> accounts=new Filemethods().loadFromFile();
//        user currentUser=AccountsMethods.findAccount(accounts,LoginController.USERNAME);
        if(serverCommunicator.isConnected()){
        this.chat=chat;
        this.chat.setUnreadmessage(0);
        double t=0;
        for (int i=0;i<chat.getMessages().size();i++){
            FXMLLoader loader;
            if(!chat.getMessages().get(i).getPictureLink().equals("")){
                loader = new FXMLLoader(getClass().getResource(config.getProperty("PictureMessageComponent")));
            }else {
                loader = new FXMLLoader(getClass().getResource(config.getProperty("MessageComponent")));
            }
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../MessageComponent.fxml"));
            t+=120;
            myTweetgp.setPrefHeight(t);
            try {
                if(chat.getMessages().get(i).getUsername().equals(chat.getUsername())){
                    myTweetgp.add((Node)loader.load(),2,i+1);
                }else {
                    myTweetgp.add((Node)loader.load(),0,i+1);
                }
                MessageComponentController childController=loader.getController();
                childController.loadMessage(chat.getMessages().get(i));
                Config config3=Config.getConfig("number");
                myTweetgp.setVgap(Integer.valueOf(config3.getProperty("VGap")));
//                gp.setLayoutX(+150);
                myTweetgp.setLayoutY(-20);

            }catch (Exception e){
                e.printStackTrace();
            }

        }}
        else {
            this.chat=chat;
            this.chat.setUnreadmessage(0);
            double t=0;
            for (int i=0;i<chat.getMessages().size();i++){
                FXMLLoader loader;
                if(!chat.getMessages().get(i).getPictureLink().equals("")){
                    loader = new FXMLLoader(getClass().getResource(config.getProperty("PictureMessageComponent")));
                }else {
                    loader = new FXMLLoader(getClass().getResource(config.getProperty("MessageComponent")));
                }
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../MessageComponent.fxml"));
                t+=120;
                myTweetgp.setPrefHeight(t);
                try {
                    if(chat.getMessages().get(i).getUsername().equals(chat.getUsername())){
                        myTweetgp.add((Node)loader.load(),2,i+1);
                    }else {
                        myTweetgp.add((Node)loader.load(),0,i+1);
                    }
                    MessageComponentController childController=loader.getController();
                    childController.loadMessage(chat.getMessages().get(i));
                    Config config3=Config.getConfig("number");
                    myTweetgp.setVgap(Integer.valueOf(config3.getProperty("VGap")));
//                gp.setLayoutX(+150);
                    myTweetgp.setLayoutY(-20);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }

    }
    public void writeMessage() throws IOException {
        logger.debug("in writeMessage from ChatSurfacePageController class on values");
        if (serverCommunicator.isConnected()) {
            serverCommunicator.sendClicked("CHAT");
            serverCommunicator.sendClicked("writeMessage");


            message newmessage = new message(LoginController.USERNAME, messagetxt.getText());
            if (!this.pictureLink.equals("")) {
                newmessage.setPictureLink(this.pictureLink);

            }
            serverCommunicator.sendClicked(gson.toJson(newmessage));
            serverCommunicator.sendClicked(gson.toJson(this.chat));
            if (this.pictureLink != "") {
                serverCommunicator.sendImage(file, this.pictureLink);
            }

//        this.chat.setUnreadmessage(this.chat.getUnreadmessage()+1);
//        MessageMethods.sendMessage(newmessage,this.chat.getContactname());
            List<User> accounts = new ArrayList<>();
            accounts.add(CurrentClient.getUser());
            loadChatMessages(MessageMethods.findChat(accounts, this.chat.getContactname(), this.chat.getUsername()));
        }else{
//            serverCommunicator.sendClicked("CHAT");
//            serverCommunicator.sendClicked("writeMessage");


            message newmessage = new message(LoginController.USERNAME, messagetxt.getText());
            newmessage.setContactname(chat.getContactname());
            if(!this.pictureLink.equals("")){
                newmessage.setPictureLink(this.pictureLink);
//                serverCommunicator.sendImage(file,this.pictureLink);
                AccountsMethods.irdn(file,this.pictureLink);

            }
//            serverCommunicator.sendClicked(gson.toJson(newmessage));
//            serverCommunicator.sendClicked(gson.toJson(this.chat));
            List<Change> ch=new Filemethods().loadFromChange();
            if(ch.size()==0){
                Change change=new Change();
                change.getMessages().add(newmessage);
                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
            else {
//                Change change=ch.get(0);
                ch.get(0).getMessages().add(newmessage);
//                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
//        this.chat.setUnreadmessage(this.chat.getUnreadmessage()+1);
//        MessageMethods.sendMessage(newmessage,this.chat.getContactname());
            List<User> accounts = new ArrayList<>();
            accounts.add(CurrentClient.getUser());
            MessageMethods.findChat(accounts, this.chat.getContactname(), this.chat.getUsername()).getMessages().add(newmessage);
            loadChatMessages(MessageMethods.findChat(accounts, this.chat.getContactname(), this.chat.getUsername()));
        }
    }
    File file;
    public void loadImage() throws IOException {// constructing ...
        logger.debug("in loadImage from ChatSurfacePageController class on values");
        Config config2=Config.getConfig("other");
        if(serverCommunicator.isConnected()) {
            Random r = new Random();
            int t = r.nextInt(10000);
            String f = String.valueOf(t);
            file = AccountsMethods.getFile();
            this.pictureLink = f + "jpg";

            if (!this.pictureLink.equals("")) {
                Attachlbl.setText(config2.getProperty("attachReaction"));
            }
        }
    }

}
