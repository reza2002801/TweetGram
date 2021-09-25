package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Chat;
import models.message;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class SendToAllTabController {
    private static Logger logger = LogManager.getLogger(SendToAllTabController.class);
    @FXML
    private TextField Messgetxt;
    private ServerCommunicator serverCommunicator;
//    private Gson gson;
    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
//        this.gson= new Gson();
    }
    public void sendMessageToAll() throws IOException {
        logger.debug("in sendMessageToAll from SendToAllTabController class on values");
        serverCommunicator.sendClicked("CHAT");
        serverCommunicator.sendClicked("sendMessageToAll");
        serverCommunicator.sendClicked(Messgetxt.getText());

//        List<User> accounts=new Filemethods().loadFromFile();
//        for (int i=0;i<accounts.size();i++){
//            if(MessageMethods.existchat(accounts,accounts.get(i).getUsername())){
////                System.out.println(accounts.get(i).getUsername());
//                message newmessage=new message(LoginController.USERNAME,Messgetxt.getText());
//                MessageMethods.sendMessage(newmessage,accounts.get(i).getUsername());
////                List<user> accounts=new Filemethods().loadFromFile();
//            }else {
//                if(LoginController.USERNAME.equals(accounts.get(i).getUsername())|| AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowers().contains(accounts.get(i).getUsername())|| AccountsMethods.findAccount(accounts,LoginController.USERNAME).getFollowings().contains(accounts.get(i).getUsername()) ){
////                System.out.println("else");
//                    message newmessage=new message(LoginController.USERNAME,Messgetxt.getText());
//                    Chat newchat=new Chat(accounts.get(i).getUsername(), LoginController.USERNAME);
//                    Chat newchat2=new Chat(LoginController.USERNAME,accounts.get(i).getUsername());
//                newchat.getMessages().add(newmessage);
//                newchat2.getMessages().add(newmessage);
//                    AccountsMethods.findAccount(accounts,LoginController.USERNAME).getUserchats().add(newchat);
//                    AccountsMethods.findAccount(accounts,accounts.get(i).getUsername()).getUserchats().add(newchat2);
////            System.out.println(MainPage.acc_info(e,contactname).getUserchats());
//                    MessageMethods.findChat(accounts, LoginController.USERNAME,accounts.get(i).getUsername()).setIsunread(true);
//                    MessageMethods.findChat(accounts, LoginController.USERNAME,accounts.get(i).getUsername()).setUnreadmessage(MessageMethods.findChat(accounts, LoginController.USERNAME,accounts.get(i).getUsername()).getUnreadmessage()+1);
////                System.out.println("done! ");
////                    new PersonalChatTabController().loadChatSurfForProfile(MessageMethods.findChat(accounts,accounts.get(i).getUsername(),LoginController.USERNAME),ap);
//                    new Filemethods().saveToFile(accounts);
//                }
//
//            }
//        }
    }
}
