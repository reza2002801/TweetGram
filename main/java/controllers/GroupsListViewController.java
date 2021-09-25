package controllers;

import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.GroupChats;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.util.LinkedList;
import java.util.List;

public class GroupsListViewController {

    private static Logger logger = LogManager.getLogger(GroupsListViewController.class);
    @FXML
    private GridPane gp;
    @FXML
    private AnchorPane ap;

    public void loadGroups(LinkedList<String> groups){
        System.out.println(groups);
        logger.debug("in findAccount from AccountsMethods class on values"+groups);
        
        Config config=Config.getConfig("fxml");
        double t=0;
        for (int i=0;i<groups.size();i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("GroupIconComponent")));
            t+=110;
            gp.setPrefHeight(t);
            try {
                gp.add((Node)loader.load(),0,i+1);
                Config config3=Config.getConfig("number");
                gp.setVgap(Integer.valueOf(config3.getProperty("VGap")));
                List<GroupChats> groupss= CurrentClient.groupss;
                System.out.println(groupss);
                GroupIconComponentController childController=loader.getController();
                if(!ChatMethods.findChat(groupss,groups.get(i)).equals(null)){

                    childController.loadGroupIcon(ChatMethods.findChat(groupss,groups.get(i)));
                }
                childController.setGlvc(this);
//                gp.setLayoutX(+150);
                gp.setLayoutY(-10);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void loadGroupChatSurf(GroupChats groupChat){
        logger.debug("in findAccount from AccountsMethods class on values"+groupChat);
        Config config=Config.getConfig("fxml");
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("GroupChatSurface")));

        try {
            ap.getChildren().add((Node) loader.load());
            GroupChatSurfaceController childController=loader.getController();
            childController.updateSurfacePage(groupChat);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
