package controllers;

import com.google.gson.Gson;
import config.Config;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.util.List;

public class SearchTabController {
    private static Logger logger = LogManager.getLogger(SearchTabController.class);
    @FXML
    private TextField Searchtxt;
    @FXML
    private GridPane gp;
    @FXML
    private AnchorPane ap;
    private ServerCommunicator serverCommunicator;
    private Gson gson;
    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }
    private StringProperty accountName=new SimpleStringProperty();
    public void c(){
        Searchtxt.textProperty().addListener((obs, oldText, newText) -> {
            try {
                loadAc();
            } catch (Exception e) {
                System.out.println("");
            }
            // ...
        });
    }
    public void loadAc() throws Exception {
        logger.debug("in loadAc from SearchTabController class on values");
        if(serverCommunicator.isConnected()) {

            serverCommunicator.sendClicked("SEARCH");
        serverCommunicator.sendClicked("loadAc");
        serverCommunicator.sendClicked(Searchtxt.getText());

        Config config=Config.getConfig("fxml");
        String res=serverCommunicator.read();
        if(res.equals("Yes")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("UserMiniProfileComponent")));
            try {
                gp.add((Node) loader.load(),0,1);
                UserMiniProfileComponentController childController=loader.getController();
                childController.updateMiniProfile(Searchtxt.getText());
                childController.setSearchTabController(this);

            }catch (Exception e){
                System.out.println("");
            }
        }else if(res.equals("No")){
            gp.getChildren().clear();

        }
        }else{

        }
//        List<User> a=new Filemethods().loadFromFile();
//        if(!AccountsMethods.findAccount(a,Searchtxt.getText()).equals(null)){
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("UserMiniProfileComponent")));
//
//            try {
//                gp.add((Node) loader.load(),0,1);
//                UserMiniProfileComponentController childController=loader.getController();
//                childController.updateMiniProfile(Searchtxt.getText());
//                childController.setSearchTabController(this);
//
//            }catch (Exception e){
//                System.out.println("");
//            }
//        }else {
//            gp.getChildren().clear();
//        }
    }
    public void goToProfile(String UserName){
        logger.debug("in goToProfile from SearchTabController class on values"+UserName);
        if(serverCommunicator.isConnected()) {
        Config config=Config.getConfig("fxml");
//        gb.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("ProfileComponent")));


        try {
            ap.getChildren().add((Node) loader.load());
            ProfileComponentController childController=loader.getController();
//            childController.setMainPage(this);
            childController.updateProfile(UserName);

        }catch (Exception e){
            e.printStackTrace();
        }

        }else{

        }

    }



}
