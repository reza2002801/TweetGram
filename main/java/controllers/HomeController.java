package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import config.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import models.GroupChats;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shared.NotifResponse;
import shared.RoomResponse;
import utils.CurrentClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class HomeController {
    private User currentUser;
        private Gson gson;
    private ServerCommunicator serverCommunicator;
    private static Logger logger = LogManager.getLogger(HomeController.class);
    @FXML
    private AnchorPane ap;
    @FXML
    private Button TimeLinebtn;
    @FXML
    private AnchorPane aap;


    public void initialize() throws IOException {
        this.gson = new Gson();
        this.currentUser = CurrentClient.getUser();
        this.serverCommunicator = CurrentClient.getClientManager();
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    if(serverCommunicator.isConnected()) {
//                        CurrentClient.setChange(new Filemethods().loadFromChange());

//                        if(CurrentClient.getChange()!=(null)) {
//                            serverCommunicator.sendClicked("changes");
//                            serverCommunicator.sendClicked(gson.toJson(CurrentClient.getChange().get(0)));
//                            CurrentClient.setChange(new ArrayList<>());
//                            new Filemethods().saveToChange(new ArrayList<>());
//
//                        }
                        serverCommunicator.sendClicked("MSG");
                    serverCommunicator.sendClicked("DATA");
//                    System.out.println("h");
                    RoomResponse rs = gson.fromJson(serverCommunicator.read(), RoomResponse.class);
//                    System.out.println(rs);
                    CurrentClient.setUser(gson.fromJson(serverCommunicator.read(), User.class));
                    List<User> accounts=new ArrayList<>();
                    accounts.add(CurrentClient.getUser());
                    new Filemethods().saveToFile(accounts);
//                    System.out.println(CurrentClient.getUser());
                    List<GroupChats> g=gson.fromJson(serverCommunicator.read(), new TypeToken<List<GroupChats>>() {}.getType());
                    CurrentClient.setGroupss(g);
                    new Filemethods().saveToFileGroup(g);
                    serverCommunicator.sendClicked("NOTIF1");
                    NotifResponse notifResponse = gson.fromJson(serverCommunicator.read(), NotifResponse.class);
                    CurrentClient.setNotifs(notifResponse.getNotifs());
                    CurrentClient.setRooms(rs.getRooms());

                    }else{
                        CurrentClient.setUser(new Filemethods().loadFromFile().get(0));
                        CurrentClient.setGroupss(new Filemethods().loadFromFileGroup());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
//        loadHome();
    }

    public void goToSetting(ActionEvent event) throws Exception {
        Config config=Config.getConfig("fxml");
        aap.getChildren().setAll((Node) FXMLLoader.
                load(Objects.requireNonNull(getClass().getResource(config.getProperty("SettingPage")))));
        logger.debug("in goToSetting from HomeController class on values"+event);

//        new ChangeScene().change_scene(event,config.getProperty("SettingPage"));
//        new ChangeScene().change_scene(event,"../SignUpPage.fxml");
    }
    public void goToTimeLine(ActionEvent event) throws Exception {
        logger.debug("in goToTimeLine from HomeController class on values"+event);
        Config config=Config.getConfig("fxml");
        Config config1=Config.getConfig("style");
//        gb.getChildren().clear();
        TimeLinebtn.setStyle(config1.getProperty("white"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("TimeLinePage")));

        try {
            ap.getChildren().add((Node) loader.load());
//            TimeLineController childController=loader.getController();
//            childController.loadFollowingsTweet();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void goToMainPage(ActionEvent event) throws Exception {
        logger.debug("in goToMainPage from HomeController class on values"+event);
        Config config=Config.getConfig("fxml");
        aap.getChildren().setAll((Node) FXMLLoader.
                load(Objects.requireNonNull(getClass().getResource(config.getProperty("MainPage")))));
//        new ChangeScene().change_scene(event,config.getProperty("MainPage"));
    }
    public void goToChatPage(ActionEvent event) throws Exception {
        logger.debug("in goToChatPage from HomeController class on values"+event);
        Config config=Config.getConfig("fxml");
        aap.getChildren().setAll((Node) FXMLLoader.
                load(Objects.requireNonNull(getClass().getResource(config.getProperty("ChatPage")))));
//        new ChangeScene().change_scene(event,config.getProperty("ChatPage"));
    }
    public void goToExpelor(ActionEvent event) throws Exception {
        logger.debug("in goToExpelor from HomeController class on values"+event);
        Config config=Config.getConfig("fxml");
        aap.getChildren().setAll((Node) FXMLLoader.
                load(Objects.requireNonNull(getClass().getResource(config.getProperty("ExpelorPage")))));
//        new ChangeScene().change_scene(event,config.getProperty("ExpelorPage"));
    }
//    public void goToSetting(ActionEvent event) throws Exception {
//        new ChangeScene().change_scene(event,"../SettingPage.fxml");
//    }

}
