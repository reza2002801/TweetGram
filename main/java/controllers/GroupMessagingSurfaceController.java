package controllers;

import com.google.gson.Gson;
import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import models.Category;
import models.message;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class GroupMessagingSurfaceController {
    private static Logger logger = LogManager.getLogger(GroupMessagingSurfaceController.class);
    @FXML
    private GridPane gp;
    @FXML
    private TextField Messgetxt;
    private Category category;
    private Gson gson;
    private ServerCommunicator serverCommunicator;
    public void initialize() {
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }
    public void loadCategoryContacts(Category category){
        logger.debug("in loadCategoryContacts from GroupMessagingSurfaceController class on values"+category);
        Config config=Config.getConfig("fxml");
        this.category=category;
        double t=0;
        for (int i=0;i<category.getMembers().size();i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("UserMiniProfileComponent")));
            t+=110;
            gp.setPrefHeight(t);
            try {
                gp.add((Node)loader.load(),0,i+1);
                Config config3=Config.getConfig("number");
                gp.setVgap(Integer.valueOf(config3.getProperty("VGap")));
                UserMiniProfileComponentController childController=loader.getController();
                childController.updateMiniProfile(category.getMembers().get(i));
//                childController.setParentController(this);
//                gp.setLayoutX(+150);
                gp.setLayoutY(-10);

            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }
    public void sendGroupMessage() throws IOException {
        logger.debug("in sendGroupMessage from GroupMessagingSurfaceController class on values");
        serverCommunicator.sendClicked("CHAT");
        serverCommunicator.sendClicked("sendGroupMessage1");
        serverCommunicator.sendClicked(gson.toJson(this.category));
        serverCommunicator.sendClicked(gson.toJson(new message(LoginController.USERNAME,Messgetxt.getText())));

//        for(int i=0;i<this.category.getMembers().size();i++){
//            message newmessage=new message(LoginController.USERNAME,Messgetxt.getText());
//            MessageMethods.sendMessage(newmessage,this.category.getMembers().get(i));
//            List<User> accounts=new Filemethods().loadFromFile();
////            loadChatMessages(MessageMethods.findChat(accounts,this.category.getMembers().get(i),this.chat.getUsername()));
//        }
        Messgetxt.setText("");



    }
}
