package controllers;

import config.Config;
import javafx.event.ActionEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class    LogOutController {
    private static Logger logger = LogManager.getLogger(LogOutController.class);
    public void logOut(ActionEvent event) throws Exception {
        logger.debug("in logOut from LogOutController class on values"+event);
        Config config=Config.getConfig("fxml");
        new ChangeScene().change_scene(event,config.getProperty("LoginPage"));
    }
}
