package controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;

public class BiggerProfileController {
    private static Logger logger = LogManager.getLogger(BiggerProfileController.class);
    @FXML
    private Rectangle BiggerProfileFrame;
    @FXML
    private AnchorPane ap;
    private ServerCommunicator serverCommunicator;
    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
    }
    public void loadImageBigger(String Url) throws IOException {
        logger.debug("in loadImageBigger from BiggerProfileController class on values"+Url);
//        String s=AccountsMethods.findAccount(a,userName).getProfilepicUrl();
        if(!Url.equals("")){
//            Image image = new Image(Url);
            BiggerProfileFrame.setFill(new ImagePattern(serverCommunicator.getImage(Url)));
        }
    }
    public void disApear(){
        logger.debug("in disApear from BiggerProfileController class ");
        ap.setVisible(false);
        BiggerProfileFrame.setVisible(false);
    }
}
