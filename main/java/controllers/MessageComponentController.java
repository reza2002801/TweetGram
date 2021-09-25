package controllers;

import com.google.gson.Gson;
import config.Config;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import models.Groupmessage;
import models.message;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class MessageComponentController {
    private static Logger logger = LogManager.getLogger(MessageComponentController.class);
    @FXML
    private Rectangle PictureFrame;
    @FXML
    private TextField Edittxt;
    @FXML
    private Button Editbtn;

    @FXML
    private Label Messagelbl;
    @FXML
    private Label usertimelbl;
    @FXML
    private Circle profileFrame;
    @FXML
    private Button editApear;
    private message message;
    private Groupmessage groupmessage;
    private ServerCommunicator serverCommunicator;
    private Gson gson;
    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }
    public void loadMessage(message mess) throws IOException {
        if(serverCommunicator.isConnected()){
        serverCommunicator.sendClicked("Message");
        serverCommunicator.sendClicked("getUrl");
        serverCommunicator.sendClicked(mess.getUsername());
        logger.debug("in loadMessage from MessageComponentController class on values"+mess);

        this.message=mess;
        Messagelbl.setText(mess.getMessage());
        usertimelbl.setText("@"+mess.getUsername()+"."+mess.getDatetime());
        String s=serverCommunicator.read();
        if(!s.equals("")){
//            javafx.scene.image.Image image = new Image(s);
            profileFrame.setFill(new ImagePattern(serverCommunicator.getImage(s)));
        }
        if(!mess.getUsername().equals(LoginController.USERNAME)){
            editApear.setVisible(false);
        }
        if(!mess.getPictureLink().equals("")){
//            Image image = new Image(mess.getPictureLink());
            PictureFrame.setFill(new ImagePattern(serverCommunicator.getImage(mess.getPictureLink())));
        }}
        else{
            this.message=mess;
            Messagelbl.setText(mess.getMessage());
            usertimelbl.setText("@"+mess.getUsername()+"."+mess.getDatetime());
            if(!mess.getUsername().equals(LoginController.USERNAME)){
                editApear.setVisible(false);
            }
            if(!mess.getPictureLink().equals("")){
//            Image image = new Image(mess.getPictureLink());
                PictureFrame.setFill(new ImagePattern(AccountsMethods.imageLoader(mess.getPictureLink())));
            }
        }

    }
    public void loadMessage(Groupmessage mess) throws IOException {
        if(serverCommunicator.isConnected()){
            serverCommunicator.sendClicked("Message");
            serverCommunicator.sendClicked("getUrl");
            serverCommunicator.sendClicked(mess.getUsername());
            logger.debug("in loadMessage from MessageComponentController class on values"+mess);

            this.groupmessage=mess;
            Messagelbl.setText(mess.getMessage());
            usertimelbl.setText("@"+mess.getUsername()+"."+mess.getDatetime());
            String s=serverCommunicator.read();
            if(!s.equals("")){
//            javafx.scene.image.Image image = new Image(s);
                profileFrame.setFill(new ImagePattern(serverCommunicator.getImage(s)));
            }
            if(!mess.getUsername().equals(LoginController.USERNAME)){
                editApear.setVisible(false);
            }
            if(!mess.getPictureLink().equals("")){
//            Image image = new Image(mess.getPictureLink());
                PictureFrame.setFill(new ImagePattern(serverCommunicator.getImage(mess.getPictureLink())));
            }}
        else{
            this.groupmessage=mess;
            Messagelbl.setText(mess.getMessage());
            usertimelbl.setText("@"+mess.getUsername()+"."+mess.getDatetime());
            if(!mess.getUsername().equals(LoginController.USERNAME)){
                editApear.setVisible(false);
            }
            if(!mess.getPictureLink().equals("")){
//            Image image = new Image(mess.getPictureLink());
                PictureFrame.setFill(new ImagePattern(AccountsMethods.imageLoader(mess.getPictureLink())));
            }
        }

    }
    public void apearEditMessage(){
        logger.debug("in apearEditMessage from MessageComponentController class on values");
        Editbtn.setVisible(true);
        Edittxt.setVisible(true);
        Edittxt.setText(Messagelbl.getText());
    }
    public void editMessage() throws IOException {
        if (serverCommunicator.isConnected()){
        serverCommunicator.sendClicked("Message");
        serverCommunicator.sendClicked("editMessage");
        serverCommunicator.sendClicked(Edittxt.getText());
        serverCommunicator.sendClicked(gson.toJson(this.message));
        logger.debug("in editMessage from MessageComponentController class on values");
        Config config2=Config.getConfig("other");
        if(Edittxt.getText().equals("")){
            Messagelbl.setText(config2.getProperty("MessageDeleteMess"));
//            this.message.setMessage(config2.getProperty("MessageDeleteMess"));
            Editbtn.setVisible(false);
            Edittxt.setVisible(false);
//            MessageMethods.editMessageForUser(this.message,LoginController.USERNAME);
            editApear.setVisible(false);
        }else {
            Messagelbl.setText(Edittxt.getText());
//            this.message.setMessage(Edittxt.getText());
            Editbtn.setVisible(false);
            Edittxt.setVisible(false);
//            MessageMethods.editMessageForUser(this.message,LoginController.USERNAME);
        }}


    }


}
