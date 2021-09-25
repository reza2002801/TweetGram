package controllers;

import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Chat;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class PersonalChatTabController {
    private static Logger logger = LogManager.getLogger(PersonalChatTabController.class);
    @FXML
    private GridPane gp;
    @FXML
    private AnchorPane ap;
    public Chat chat;
    public void loadChats() throws IOException {
        logger.debug("in loadChats from PersonalChatTabController class on values");
        Config config=Config.getConfig("fxml");
//        List<User> accounts=new Filemethods().loadFromFile();
        if(CurrentClient.getClientManager().isConnected()){
        User currentUser= CurrentClient.getUser();
//        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        double t=0;
        for (int i=0;i<currentUser.getUserchats().size();i++){
            System.out.println("D");
            FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ChatIconComponent")));
            t+=120;
            gp.setPrefHeight(t);
            try {
                gp.add((Node)loader.load(),0,i+1);

                ChatIconComponentController childController=loader.getController();
                childController.setPctc(this);

                childController.updateChatIcon(currentUser.getUserchats().get(i));
                Config config3=Config.getConfig("number");
                gp.setVgap(Integer.valueOf(config3.getProperty("VGap")));
//                gp.setLayoutX(+150);
                gp.setLayoutY(-20);

            }catch (Exception e){
                e.printStackTrace();
            }

        }}
        else{
            User currentUser= CurrentClient.getUser();
//        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            double t=0;
            for (int i=0;i<currentUser.getUserchats().size();i++){
                System.out.println("D");
                FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ChatIconComponent")));
                t+=120;
                gp.setPrefHeight(t);
                try {
                    gp.add((Node)loader.load(),0,i+1);

                    ChatIconComponentController childController=loader.getController();
                    childController.setPctc(this);

                    childController.updateChatIcon(currentUser.getUserchats().get(i));
                    Config config3=Config.getConfig("number");
                    gp.setVgap(Integer.valueOf(config3.getProperty("VGap")));
//                gp.setLayoutX(+150);
                    gp.setLayoutY(-20);

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
//        sp.setVvalue(sp.getVmax());
    }
    public void loadChatSurf(){
        logger.debug("in loadChatSurf from PersonalChatTabController class on values");
        Config config=Config.getConfig("fxml");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ChatSurfacePage")));

        try {
            ap.getChildren().add((Node) loader.load());
            ChatSurfacePageController childController=loader.getController();
            childController.loadChatMessages(this.chat);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void loadChatSurfForProfile(Chat chat, AnchorPane ap){
        logger.debug("in loadChatSurfForProfile from PersonalChatTabController class on values");
        Config config=Config.getConfig("fxml");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ChatSurfacePage")));

        try {
            ap.getChildren().add((Node) loader.load());
            ChatSurfacePageController childController=loader.getController();
            childController.loadChatMessages(chat);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
