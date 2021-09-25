package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangeScene {
    private static Logger logger = LogManager.getLogger(ChangeScene.class);
    public void change_scene(ActionEvent event,String xmlfilename) throws Exception{
        logger.debug("in change_scene from ChangeScene class on values"+event+"and"+xmlfilename);
        Parent sighnin= FXMLLoader.load(getClass().getResource(xmlfilename));
        Scene sign=new Scene(sighnin);
        Stage w=(Stage) ((Node)event.getSource()).getScene().getWindow();
        w.setScene(sign);
        w.show();
    }
}
