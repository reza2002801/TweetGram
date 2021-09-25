package controllers;

import com.google.gson.Gson;
import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.*;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GroupChatSurfaceController {
    private static Logger logger = LogManager.getLogger(GroupChatSurfaceController.class);
    @FXML
    private Label GroupNamelbl;
    @FXML
    private Label Memberslbl;
    @FXML
    private TextField myMessgetxt;
    @FXML
    private Label Attachlbl;
    @FXML
    private GridPane gp;
    @FXML
    private TextField userNametxt;
    private String GroupName;
    private String pictureLink="";
    private ServerCommunicator serverCommunicator;
    private Gson gson;
    public void initialize(){
        this.gson = new Gson();
        this.serverCommunicator = CurrentClient.getClientManager();
    }

    public void updateSurfacePage(GroupChats groupChats){
        logger.debug("in updateSurfacePage from GroupChatSurfaceController class on values"+groupChats);
        this.GroupName=groupChats.getGroupName();
        GroupNamelbl.setText(GroupNamelbl.getText()+groupChats.getGroupName());
        String s="";
        for(int i=0;i<groupChats.getMembers().size();i++){
            s+=",";
            s+=groupChats.getMembers().get(i);
        }
        Memberslbl.setText(Memberslbl.getText()+s);
        loadGroupChatMessage(groupChats);

    }
    public void loadGroupChatMessage(GroupChats groupChats){
        logger.debug("in loadGroupChatMessage from GroupChatSurfaceController class on values"+groupChats);
        Config config=Config.getConfig("fxml");
        double t=0;
        for (int i=0;i<groupChats.getChats().size();i++){
            FXMLLoader loader;
            if(!groupChats.getChats().get(i).getPictureLink().equals("")){
                loader = new FXMLLoader(getClass().getResource(config.getProperty("PictureMessageComponent")));
            }else {
                loader = new FXMLLoader(getClass().getResource(config.getProperty("MessageComponent")));
            }
            t+=110;
            gp.setPrefHeight(t);
            try {
                gp.add((Node)loader.load(),0,i+1);
                Config config3=Config.getConfig("number");
                gp.setVgap(Integer.valueOf(config3.getProperty("VGap")));
                MessageComponentController childController=loader.getController();
                childController.loadMessage(groupChats.getChats().get(i));
//                childController.setParentController(this);
//                gp.setLayoutX(+150);
                gp.setLayoutY(-10);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void addUserToGroup() throws IOException {
        if(serverCommunicator.isConnected()){

        logger.debug("in addUserToGroup from GroupChatSurfaceController class on values");

        if(!userNametxt.getText().equals("")){
            serverCommunicator.sendClicked("GROUP");
            serverCommunicator.sendClicked("addUserToGroup");
            serverCommunicator.sendClicked(userNametxt.getText());
            serverCommunicator.sendClicked(this.GroupName);
            String res=serverCommunicator.read();
            if(res.equals("Yes")){
                updateSurfacePage(ChatMethods.findChat(CurrentClient.groupss,this.GroupName));
            }
            else {
            }

        }}
    }
    File file;
    public void loadImage() throws IOException {
        if(serverCommunicator.isConnected()) {
            logger.debug("in loadImage from GroupChatSurfaceController class on values");
            Config config2 = Config.getConfig("other");
            file = AccountsMethods.getFile();
            Random r = new Random();
            int t = r.nextInt(10000);
            String f = String.valueOf(t);
            this.pictureLink = f + ".jpg";
            if (!this.pictureLink.equals("")) {
                Attachlbl.setText(config2.getProperty("attachReaction"));
            }
        }else{
            logger.debug("in loadImage from GroupChatSurfaceController class on values");
            Config config2 = Config.getConfig("other");
            file = AccountsMethods.getFile();
            Random r = new Random();
            int t = r.nextInt(10000);
            String f = String.valueOf(t);
            this.pictureLink = f + ".jpg";
            if (!this.pictureLink.equals("")) {
                Attachlbl.setText(config2.getProperty("attachReaction"));
            }
        }
    }
    public void writeMessage() throws IOException {
        if(serverCommunicator.isConnected()){
        logger.debug("in writeMessage from GroupChatSurfaceController class on values");

        Groupmessage newmessage=new Groupmessage(myMessgetxt.getText(),GroupName,LoginController.USERNAME,this.pictureLink);
        if(!this.pictureLink.equals("")){
            newmessage.setPictureLink(this.pictureLink);
            serverCommunicator.sendImage(file,this.pictureLink);


        }
        serverCommunicator.sendClicked("GROUP");
        serverCommunicator.sendClicked("writeMessage");
        serverCommunicator.sendClicked(gson.toJson(newmessage));
        serverCommunicator.sendClicked(this.GroupName);
//        MessageMethods.sendMessage(newmessage,this.chat.getContactname());
//        List<GroupChats> groups=new Filemethods().loadFromFileGroup();
//        ChatMethods.findChat(groups,this.GroupName).getChats().add(newmessage);
//        new Filemethods().saveToFileGroup(groups);
        loadGroupChatMessage(ChatMethods.findChat(CurrentClient.groupss,this.GroupName));
        }
        else {
            logger.debug("in writeMessage from GroupChatSurfaceController class on values");

            Groupmessage newmessage=new Groupmessage(myMessgetxt.getText(),this.GroupName,LoginController.USERNAME,this.pictureLink);
            if(!this.pictureLink.equals("")){
                newmessage.setPictureLink(this.pictureLink);
//                serverCommunicator.sendImage(file,this.pictureLink);
                AccountsMethods.irdn(file,this.pictureLink);

            }
//            serverCommunicator.sendClicked("GROUP");
//            serverCommunicator.sendClicked("writeMessage");
//            serverCommunicator.sendClicked(gson.toJson(newmessage));
//            serverCommunicator.sendClicked(this.GroupName);
            List<Change> ch=new Filemethods().loadFromChange();
            if(ch.size()==0){
                Change change=new Change();
                change.getGroupmessages().add(newmessage);
                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
            else {
//                Change change=ch.get(0);
                ch.get(0).getGroupmessages().add(newmessage);
//                ch.add(change);
                new Filemethods().saveToChange(ch);
            }
            ChatMethods.findChat(CurrentClient.groupss,this.GroupName).getChats().add(newmessage);
//        MessageMethods.sendMessage(newmessage,this.chat.getContactname());
//        List<GroupChats> groups=new Filemethods().loadFromFileGroup();
//        ChatMethods.findChat(groups,this.GroupName).getChats().add(newmessage);
//        new Filemethods().saveToFileGroup(groups);
            loadGroupChatMessage(ChatMethods.findChat(CurrentClient.groupss,this.GroupName));
        }
//        loadChatMessages(MessageMethods.findChat(accounts,this.chat.getContactname(),this.chat.getUsername()));
    }

}
