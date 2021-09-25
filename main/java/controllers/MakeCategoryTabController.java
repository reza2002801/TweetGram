package controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.Category;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MakeCategoryTabController {
    private static Logger logger = LogManager.getLogger(MakeCategoryTabController.class);
    @FXML
    private Label userlbl;
    @FXML
    private TextField userNametxt;
    @FXML
    private TextField catNametxt;
    private LinkedList<String> users=new LinkedList<>();

    private Gson gson;
    private ServerCommunicator serverCommunicator;
    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }
    public void addUser(){
        logger.debug("in addUser from MakeCategoryTabController class on values");
        this.users.add(userNametxt.getText());
        userlbl.setText(userlbl.getText()+userNametxt.getText()+",");
        userNametxt.setText("");
    }
    public void Discard(){
        logger.debug("in Discard from MakeCategoryTabController class on values");
        this.users.clear();
        userNametxt.setText("");
        catNametxt.setText("");
    }
    public void saveCategory() throws IOException {
        logger.debug("in saveCategory from MakeCategoryTabController class on values");
        serverCommunicator.sendClicked("CHAT");
        serverCommunicator.sendClicked("saveCategory");
        Category cat=new Category(catNametxt.getText());
        cat.setMembers(this.users);
        userNametxt.setText("");
        catNametxt.setText("");
        serverCommunicator.sendClicked(gson.toJson(cat));

    }


}
