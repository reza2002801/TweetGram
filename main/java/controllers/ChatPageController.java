package controllers;

import config.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatPageController {
    private static Logger logger = LogManager.getLogger(ChatPageController.class);
    @FXML
    private Button personalChatbtn;
    @FXML
    private Button groupChatbtn;
    @FXML
    private AnchorPane ap;
    public void backToHome(ActionEvent event) throws Exception {
        logger.debug("in backToHome from ChatPageController class on values"+event);
        Config config=Config.getConfig("fxml");
        new ChangeScene().change_scene(event,config.getProperty("HomePage"));
    }
    public void goToPersonalChatPage(){
        logger.debug("in goToPersonalChatPage from ChatPageController class on values");
        Config config=Config.getConfig("fxml");
        Config config1=Config.getConfig("style");
        System.out.println(config1.getProperty("blue"));
        ap.getChildren().clear();
        personalChatbtn.setStyle(config1.getProperty("white"));
        groupChatbtn.setStyle(config1.getProperty("blue"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("PersonalChatTab")));

        try {
            ap.getChildren().add((Node) loader.load());
            PersonalChatTabController childController=loader.getController();
            childController.loadChats();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void goToGroupChatPage(){
        logger.debug("in goToGroupChatPage from ChatPageController class on values");
        Config config=Config.getConfig("fxml");
        Config config1=Config.getConfig("style");
        ap.getChildren().clear();
        groupChatbtn.setStyle(config1.getProperty("white"));
        personalChatbtn.setStyle(config1.getProperty("blue"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("GroupChatTab")));

        try {
            ap.getChildren().add((Node) loader.load());
//            ViewListTabController childController=loader.getController();
//            childController.setMainPage(this);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
