package controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.control.Label;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.MiniProfileResponse;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class UserMiniProfileComponentController {
    private static Logger logger = LogManager.getLogger(UserMiniProfileComponentController.class);
    private ListViewerSubTabController ListViewer;
    private Gson gson;

    public void setSearchTabController(SearchTabController searchTabController) {
        logger.debug("in setSearchTabController from UserMiniProfileComponentController class on values"+searchTabController);
        this.searchTabController = searchTabController;
    }

    private SearchTabController searchTabController;
    @FXML
    private AnchorPane aap;
    @FXML
    private Circle profilecircle;
    @FXML
    private Label u;
    @FXML
    private Label f;
    private ServerCommunicator serverCommunicator;
    public void initialize() {
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }
    public void updateMiniProfile(String userName) throws IOException {
        logger.debug("in updateMiniProfile from UserMiniProfileComponentController class on values"+userName);
        serverCommunicator.sendClicked("User");
        serverCommunicator.sendClicked("updateMiniProfile");
        serverCommunicator.sendClicked(userName);

//        List<User> a=new Filemethods().loadFromFile();/
        MiniProfileResponse miniProfileResponse=gson.fromJson(serverCommunicator.read(), MiniProfileResponse.class);
        String s=miniProfileResponse.getPicUrl();
        // sending Pic Consstructing



        if(!s.equals("")){
//            Image image = new Image(s);
            profilecircle.setFill(new ImagePattern(serverCommunicator.getImage(s)));
        }



        u.setText(miniProfileResponse.getUserName());
        f.setText(miniProfileResponse.getFirstName()+" "+miniProfileResponse.getLastName());
    }
//    public void goToProfile(ActionEvent event) throws IOException {
//
//
//
////
////            String s=u.getText();
////            aap.getParent().add
//////            gb.getChildren().add((Node) loader.load());
////            ListViewerSubTabController childController=new ListViewerSubTabController();
////            childController.goToProfile1(s);
//
//
//    }

public void setParentController(ListViewerSubTabController listViewer) {
    this.ListViewer = listViewer;
}

    public void goToProfile() throws Exception {
        logger.debug("in setParentController from UserMiniProfileComponentController class on values");
        try{
            this.searchTabController.goToProfile(u.getText());
        }catch (Exception e){
            this.ListViewer.ViewList.MainPage.goToProfile(u.getText());
        }
//        if(!searchTabController.equals(null)){
//
//        }else {
//
//        }
//        this.ListViewer.ViewList.MainPage.goToProfile(u.getText());
    }
}
