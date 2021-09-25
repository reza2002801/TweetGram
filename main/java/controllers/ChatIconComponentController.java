package controllers;

import com.google.gson.Gson;
import config.Config;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import models.Chat;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class ChatIconComponentController {
    private static Logger logger = LogManager.getLogger(ChatIconComponentController.class);
    private PersonalChatTabController pctc;

    public void setPctc(PersonalChatTabController pctc) {
        logger.debug("in setPctc from ChatIconComponentController class on values"+pctc);

        this.pctc = pctc;
    }

    @FXML
    private Label UnreadMessagelbl;
    @FXML
    private Label u;
    @FXML
    private Circle profilecircle;
    private Chat c;
    private ServerCommunicator serverCommunicator;
    private Gson gson;

    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }


    public void updateChatIcon(Chat chat) throws IOException {
        logger.debug("in updateChatIcon from ChatIconComponentController class on values"+chat);
//        serverCommunicator.sendClicked("CHAT");
//        serverCommunicator.sendClicked("updateChatIcon");
        if(serverCommunicator.isConnected()) {

            Config config2 = Config.getConfig("other");
            this.c = chat;

            u.setText("@" + chat.getContactname());
            UnreadMessagelbl.setText(String.valueOf(chat.getUnreadmessage()) + config2.getProperty("UnreadMessage"));
            // constructing pic...
            serverCommunicator.sendClicked("User");
            serverCommunicator.sendClicked("showBiggerProfile");
            serverCommunicator.sendClicked(chat.getContactname());
            String Url = serverCommunicator.read();
//        List<User> accounts=new Filemethods().loadFromFile();
//        String s=AccountsMethods.findAccount(accounts,chat.getContactname()).getProfilepicUrl();

            if (!Url.equals("")) {
//            javafx.scene.image.Image image = new Image(s);
                profilecircle.setFill(new ImagePattern(serverCommunicator.getImage(Url)));
            }
            //....
        }else {
            Config config2 = Config.getConfig("other");
            this.c = chat;

            u.setText("@" + chat.getContactname());
            UnreadMessagelbl.setText(String.valueOf(chat.getUnreadmessage()) + config2.getProperty("UnreadMessage"));
            // constructing pic...
//            serverCommunicator.sendClicked("User");
//            serverCommunicator.sendClicked("showBiggerProfile");
//            serverCommunicator.sendClicked(chat.getContactname());
//            String Url = serverCommunicator.read();
//        List<User> accounts=new Filemethods().loadFromFile();
//        String s=AccountsMethods.findAccount(accounts,chat.getContactname()).getProfilepicUrl();

//            if (!Url.equals("")) {
////            javafx.scene.image.Image image = new Image(s);
//                profilecircle.setFill(new ImagePattern(serverCommunicator.getImage(Url)));
//            }
        }

    }
    public void goToChatSurface(){
        logger.debug("in goToChatSurface from ChatIconComponentController class on values");
        this.pctc.chat=c;
        this.pctc.loadChatSurf();
    }
}
