package controllers;


import com.google.gson.Gson;
import config.Config;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.Chat;
import models.message;
import models.Tweet;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ManualChooseTabController {
    private static Logger logger = LogManager.getLogger(ManualChooseTabController.class);
    private LinkedList<String> contactlist=new LinkedList<>();
    @FXML
    private Label userlbl;
    @FXML
    private TextField userNametxt;
    @FXML
    private TextField Messgetxt;
    @FXML
    private AnchorPane ap;
    @FXML
    private Label Attachlbl;
    public String pictureLink;
    private ServerCommunicator serverCommunicator;
    private Gson gson;
    public void initialize(){
        this.gson=new Gson();
        this.serverCommunicator= CurrentClient.getClientManager();
        this.pictureLink="";
    }
    public void addUser(){
        logger.debug("in addUser from ManualChooseTabController class on values");
        if(!userNametxt.getText().equals("")){
            this.contactlist.add(userNametxt.getText());
            userlbl.setText(userlbl.getText()+userNametxt.getText()+",");
            userNametxt.clear();

        }
    }
    public void loadImage() throws IOException {
        //constructing .....
        logger.debug("in loadImage from ChatSurfacePageController class on values");
        Config config2=Config.getConfig("other");
        file=AccountsMethods.getFile();
        Random r=new Random();
        int t=r.nextInt(10000);
        String f=String.valueOf(t);
        this.pictureLink=f+".jpg";
        if(!this.pictureLink.equals("")){
            Attachlbl.setText(config2.getProperty("attachReaction"));
        }
        //.....
    }
    File file;
    public void sendManual() throws IOException {
        logger.debug("in sendManual from ManualChooseTabController class on values");
//        List<user> accounts=new Filemethods().loadFromFile();
//        for (int i=0;i<this.contactlist.size();i++){
//            if(AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowings().contains(this.contactlist.get(i))||AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowers().contains(this.contactlist.get(i))){
//                message newmessage=new message(LoginController.USERNAME,Messgetxt.getText());
//                MessageMethods.sendMessage(newmessage,this.contactlist.get(i));
////                List<user> accounts=new Filemethods().loadFromFile();
//            }
//        }
        serverCommunicator.sendClicked("CHAT");
        serverCommunicator.sendClicked("sendManual");
        if((this.pictureLink.equals("")||this.pictureLink.equals("MSG"))){
            serverCommunicator.sendClicked("");

        }else {
            serverCommunicator.sendClicked(this.pictureLink);
            serverCommunicator.sendImage(file,this.pictureLink);
            this.pictureLink="";
        }
        serverCommunicator.sendClicked(gson.toJson(this.contactlist));
        serverCommunicator.sendClicked(Messgetxt.getText());


//        List<User> accounts=new Filemethods().loadFromFile();
//
//        for (int i=0;i<this.contactlist.size();i++){
//            if(MessageMethods.existchat(accounts,this.contactlist.get(i))){
//                message newmessage=new message(LoginController.USERNAME,Messgetxt.getText());
//                if(!this.pictureLink.equals("")){
//                    newmessage.setPictureLink(this.pictureLink);
//
//                }
//                MessageMethods.sendMessage(newmessage,this.contactlist.get(i));
////                List<user> accounts=new Filemethods().loadFromFile();
//            }else {
//                if(LoginController.USERNAME.equals(this.contactlist.get(i))|| AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowers().contains(this.contactlist.get(i))|| AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowings().contains(this.contactlist.get(i)) ){
////                System.out.println("else");
//                    message newmessage=new message(LoginController.USERNAME,Messgetxt.getText());
//                    if(!this.pictureLink.equals("")){
//                        newmessage.setPictureLink(this.pictureLink);
//
//                    }
//                    Chat newchat=new Chat(this.contactlist.get(i), LoginController.USERNAME);
//                    Chat newchat2=new Chat(LoginController.USERNAME,this.contactlist.get(i));
//                newchat.getMessages().add(newmessage);
//                newchat2.getMessages().add(newmessage);
//                    AccountsMethods.findAccount(accounts,LoginController.USERNAME).getUserchats().add(newchat);
//                    AccountsMethods.findAccount(accounts,this.contactlist.get(i)).getUserchats().add(newchat2);
////            System.out.println(MainPage.acc_info(e,contactname).getUserchats());
//                    MessageMethods.findChat(accounts, LoginController.USERNAME,this.contactlist.get(i)).setIsunread(true);
//                    MessageMethods.findChat(accounts, LoginController.USERNAME,this.contactlist.get(i)).setUnreadmessage(MessageMethods.findChat(accounts, LoginController.USERNAME,this.contactlist.get(i)).getUnreadmessage()+1);
////                System.out.println("done! ");
////                    new PersonalChatTabController().loadChatSurfForProfile(MessageMethods.findChat(accounts,accounts.get(i).getUsername(),LoginController.USERNAME),ap);
//                    new Filemethods().saveToFile(accounts);
//                }
//
//            }
//        }
    }
    public void loadForward(Tweet t){
        logger.debug("in loadForward from ManualChooseTabController class on values");
        Messgetxt.setText(t.getTweetphrase());
        if(!t.getPictureLink().equals("")){
            this.pictureLink=t.getPictureLink();
        }


    }
    public void Discard() throws IOException {
        logger.debug("in Discard from ManualChooseTabController class on values");
        ap.setVisible(false);
    }

}
