package controllers;

import com.google.gson.Gson;
import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import models.*;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.ProfileResponse;
import utils.CurrentClient;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static models.Profile.delete_tweet_blacklist;
import static models.Profile.transfertweets;

public class ProfileComponentController {
    private static Logger logger = LogManager.getLogger(ProfileComponentController.class);
    public MainController MainPage;

    public void setMainPage(MainController mainPage) {
        MainPage = mainPage;
    }
    @FXML
    private ScrollPane sspp;

    @FXML
    private Label UserName;
    @FXML
    private Label FullName;
    @FXML
    private Label Bio;
    @FXML
    private Label PhoneNumber;
    @FXML
    private Label LastSeen;
    @FXML
    private Label Birthday;
    @FXML
    private Button Followbtn;
    @FXML
    private Circle ProfileFrame;
    @FXML
    private GridPane gp;
    @FXML
    private AnchorPane ap;
    public void disapear(){
        sspp.setVisible(false);
    }
    private String Name;
    private ServerCommunicator serverCommunicator;
    private Gson gson ;
    //--------------------methods---------------------------
    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }

    public void updateProfile(String username) throws IOException {
        logger.debug("in updateProfile from ProfileComponentController class on values"+username);
        serverCommunicator.sendClicked("User");
        serverCommunicator.sendClicked("goToProfile");
        serverCommunicator.sendClicked(username);
        ProfileResponse profileResponse=gson.fromJson(serverCommunicator.read(),ProfileResponse.class);
        Config config2=Config.getConfig("other");
        Config config1=Config.getConfig("style");
        Config config=Config.getConfig("fxml");

//        List<User> accounts=new Filemethods().loadFromFile();
//        User currentuser=AccountsMethods.findAccount(accounts,username);
        this.Name=profileResponse.getUsername();
        UserName.setText(UserName.getText()+profileResponse.getUsername());
        FullName.setText(FullName.getText()+profileResponse.getFirstName()+" "+profileResponse.getLastName());
        Bio.setText(Bio.getText()+profileResponse.getBio());
        PhoneNumber.setText(PhoneNumber.getText()+profileResponse.getPhoneNumber());
        if(CurrentClient.getUser().getFollowers().contains(profileResponse.getUsername())){
            if(profileResponse.getIsLastSeen().equals(true)){
                LastSeen.setText(LastSeen.getText()+profileResponse.getLastSeen());
            }
            else {
                LastSeen.setText(LastSeen.getText()+config2.getProperty("LastSeenForRecently"));
            }
        }else {
            LastSeen.setText(LastSeen.getText()+config2.getProperty("LastSeenForRecently"));
        }
        Birthday.setText(Birthday.getText()+profileResponse.getBirthDate());
        // constructing ...
        String s=profileResponse.getPicUrl();
        if(!s.equals("")){
//            Image image = new Image(s);
            ProfileFrame.setFill(new ImagePattern(serverCommunicator.getImage(s)));
        }
        //.........
        if(CurrentClient.getUser().getFollowers().contains(profileResponse.getUsername())){
            Followbtn.setStyle(config1.getProperty("white"));
            Followbtn.setText(config2.getProperty("ExpresstionforFollowing"));
        }
//        double t=0;
//        for (int i=0;i<profileResponse.getUserTweet().size();i++){
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("MyTweetComponent")));
//             t+=110;
//            gp.setPrefHeight(t);
//            try {
//                gp.add((Node)loader.load(),0,i+1);
//                gp.setVgap(5);
//                MyTweetComponentController childController=loader.getController();
//                childController.updateTweetComponent1(profileResponse.getUserTweet().get(i));
//                childController.setProfilecomp(this);
////                gp.setLayoutX(+150);
//                gp.setLayoutY(300);
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//        }
    }

    public void follow()throws IOException{
        logger.debug("in follow from ProfileComponentController class on values");
        serverCommunicator.sendClicked("User");
        serverCommunicator.sendClicked("follow");
        serverCommunicator.sendClicked(UserName.getText().replaceAll("UserName : ",""));


        Config config2=Config.getConfig("other");
//        List<User> accounts=new Filemethods().loadFromFile();
//        String username=UserName.getText().replaceAll("UserName : ","");
//        User currentuser=AccountsMethods.findAccount(accounts,username);
//        User me=AccountsMethods.findAccount(accounts,LoginController.USERNAME);
//        if(!me.getFollowings().contains(LoginController.USERNAME)){
//            if(!currentuser.getBlackList().contains(LoginController.USERNAME)){
//                if(currentuser.getPublicaccount()){
//                    AccountsMethods.findAccount(accounts,username).getFollowers().add(LoginController.USERNAME);
//                    AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowings().add(currentuser.getUsername());
//                    transfertweets(currentuser,AccountsMethods.findAccount(accounts,LoginController.USERNAME));
//                    Notification n=new Notification(LoginController.USERNAME,config2.getProperty("StartFollowingMessage"));
//                    AccountsMethods.findAccount(accounts,username).getSystemnotif().add(n);
//
//                }else{
//                    Notification n=new Notification(LoginController.USERNAME,config2.getProperty("followRequestMessage"));
//                    Notification m=new Notification(currentuser.getUsername(),config2.getProperty("YourRequest"));
//                    AccountsMethods.findAccount(accounts,username).getUsernotif().add(n);
//                    AccountsMethods.findAccount(accounts,LoginController.USERNAME).getUsernotif().add(m);
//                }
//
//            }
//        }
//        new Filemethods().saveToFile(accounts);
//        if(!a.getBlackList().contains(login.UserName)){
//            if(a.getPublicaccount()){
//                MainPage.acc_info(e,a.getUsername()).getFollowers().add(login.UserName);
//                MainPage.acc_info(e, login.UserName).getFollowings().add(a.getUsername());
//                transfertweets(a,MainPage.acc_info(e, login.UserName));
//                notification n=new notification(login.UserName,"has started to follow you ");
//                MainPage.acc_info(e,a.getUsername()).getSystemnotif().add(n);
//                System.out.println("done!");
//            }else{
//                notification n=new notification(login.UserName,"has requested to follow you ");
//                notification m=new notification(a.getUsername(),"You have sent a follow request for ");
//                MainPage.acc_info(e,a.getUsername()).getUsernotif().add(n);
//                MainPage.acc_info(e, login.UserName).getUsernotif().add(m);
//            }
//
//        }




    }
    public void viewChatPage() throws IOException {
        serverCommunicator.sendClicked("User");
        serverCommunicator.sendClicked("viewChatPage");
        serverCommunicator.sendClicked(this.Name);
        String res=serverCommunicator.read();
        System.out.println(res);
        List<User> accounts=new ArrayList<>();
        if(res.equals("YesChat")){
            accounts.add(CurrentClient.getUser());
            new PersonalChatTabController().loadChatSurfForProfile(MessageMethods.findChat(accounts,this.Name,LoginController.USERNAME),ap);
        }
        else if(res.equals("NoChat")){
            String res2=serverCommunicator.read();
            if(res2.equals("f")){
                User user=gson.fromJson(serverCommunicator.read(),User.class);
                accounts.add(user);
                new PersonalChatTabController().loadChatSurfForProfile(MessageMethods.findChat(accounts,this.Name,LoginController.USERNAME),ap);

            }

        }

//        List<User> accounts=new Filemethods().loadFromFile();
//        if(MessageMethods.existchat(accounts,this.Name)){
//            new PersonalChatTabController().loadChatSurfForProfile(MessageMethods.findChat(accounts,this.Name,LoginController.USERNAME),ap);
//        }else {
//            if(LoginController.USERNAME.equals(this.Name)|| AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowers().contains(this.Name)|| AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowings().contains(this.Name) ){
////                System.out.println("else");
//                Chat newchat=new Chat(this.Name, LoginController.USERNAME);
//                Chat newchat2=new Chat(LoginController.USERNAME,this.Name);
////                newchat.getMessages().add(newmessage);
////                newchat2.getMessages().add(newmessage);
//                AccountsMethods.findAccount(accounts,LoginController.USERNAME).getUserchats().add(newchat);
//                AccountsMethods.findAccount(accounts,this.Name).getUserchats().add(newchat2);
////            System.out.println(MainPage.acc_info(e,contactname).getUserchats());
//                MessageMethods.findChat(accounts, LoginController.USERNAME,this.Name).setIsunread(true);
//                MessageMethods.findChat(accounts, LoginController.USERNAME,this.Name).setUnreadmessage(MessageMethods.findChat(accounts, LoginController.USERNAME,this.Name).getUnreadmessage()+1);
////                System.out.println("done! ");
//                new PersonalChatTabController().loadChatSurfForProfile(MessageMethods.findChat(accounts,this.Name,LoginController.USERNAME),ap);
//                new Filemethods().saveToFile(accounts);
//            }

//        }

    }
    public void showBiggerProfile() throws IOException {
        serverCommunicator.sendClicked("User");
        serverCommunicator.sendClicked("showBiggerProfile");
        serverCommunicator.sendClicked(this.Name);
        String Url=serverCommunicator.read();




        Config config=Config.getConfig("fxml");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("BiggerProfile")));
//        List<User> accounts=new Filemethods().loadFromFile();

        try {
            ap.getChildren().add((Node) loader.load());
            BiggerProfileController childController=loader.getController();
            childController.loadImageBigger(Url);

        }catch (Exception e){
            e.printStackTrace();
        }
        //.....
    }

    public void blockUser() throws IOException {
        serverCommunicator.sendClicked("User");
        serverCommunicator.sendClicked("blockUser");
        serverCommunicator.sendClicked(this.Name);

//        List<User> accounts=new Filemethods().loadFromFile();
//        AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowings().remove(this.Name);
//        AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowers().remove(this.Name);
//        AccountsMethods.findAccount(accounts,LoginController.USERNAME).getBlackList().add(this.Name);
//        AccountsMethods.findAccount(accounts,this.Name).getFollowings().remove(LoginController.USERNAME);
//        AccountsMethods.findAccount(accounts,this.Name).getFollowers().remove(LoginController.USERNAME);
////        MainPage.acc_info(e, login.UserName).getFollowings().remove(a.getUsername());
////        MainPage.acc_info(e, login.UserName).getFollowers().remove(a.getUsername());
////        MainPage.acc_info(e, login.UserName).getBlackList().add(a.getUsername());
////        MainPage.acc_info(e,a.getUsername()).getFollowers().remove(a.getUsername());
////        MainPage.acc_info(e,a.getUsername()).getFollowings().remove(a.getUsername());
//        delete_tweet_blacklist(AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowingstweet(),this.Name);
//        new Filemethods().saveToFile(accounts);
    }
    public void unblockUser() throws IOException{
        serverCommunicator.sendClicked("User");
        serverCommunicator.sendClicked("unblockUser");
        serverCommunicator.sendClicked(this.Name);


//        MainPage.acc_info(e, login.UserName).getBlackList().remove(a.getUsername());

    }
    public void unMute()throws IOException{
        serverCommunicator.sendClicked("User");
        serverCommunicator.sendClicked("unMute");
        serverCommunicator.sendClicked(this.Name);


    }
}
