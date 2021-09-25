package utils;

import com.google.gson.Gson;
import controllers.Filemethods;
import models.*;
import network.ServerCommunicator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CurrentClient {
    static public ServerCommunicator clientManager;
    static public String AuthToken;
    static public User user;
    static public LinkedList<Chat> chats;
    static public LinkedList<Notification> notifs;
    static public boolean isRunning;
    static Gson gson;
    static public List<GroupChats> groupss;
    static public List<Change> change ;

    public static List<Change> getChange() {
        return change;
    }

    public static void setChange(List<Change> change) {
        CurrentClient.change = change;
    }

    public List<GroupChats> getGroupss() {
        return groupss;
    }

    public static void setGroupss(List<GroupChats> groupss) {
        CurrentClient.groupss = groupss;
    }
    public CurrentClient() throws IOException {
        change=new ArrayList<>();
        gson = new Gson();
//        rooms = null;
//        notifs = null;
        isRunning = true;
        user = null;


        clientManager = new ServerCommunicator();
//        if(clientManager.getSocket()!=null && clientManager.getSocket().isConnected()) {
            clientManager.start();
//        }
    }

    public static void setClientManager(ServerCommunicator clientManager) {
        CurrentClient.clientManager = clientManager;
    }

    public static String getAuthToken() {
        return AuthToken;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        CurrentClient.user = user;
    }

    public static LinkedList<Notification> getNotifs() {
        return notifs;
    }

    public static void setNotifs(LinkedList<Notification> notifs) {
        CurrentClient.notifs = notifs;
    }

    public static LinkedList<Chat> getRooms() {
        return chats;
    }

    public static void setRooms(LinkedList<Chat> rooms) {
        CurrentClient.chats = rooms;
    }

    public static void saveUser() throws IOException {
        clientManager.sendUsers("SAVE");
    }

    public static User getUser(String username) throws IOException {
        clientManager.sendClicked("GET_USER");
        clientManager.sendClicked(username);
        return gson.fromJson(clientManager.read(), User.class);
    }



//    public static Room getRoom(String str) throws IOException {
//        clientManager.sendClicked("GET_ROOM");
//        clientManager.sendClicked(str);
//        return gson.fromJson(clientManager.read(), Room.class);
//    }
//
//    public static Chat getChat(String str) throws IOException {
//        clientManager.sendClicked("GET_CHAT");
//        clientManager.sendClicked(str);
//        return gson.fromJson(clientManager.read(), Chat.class);
//    }


    public static void setAuthToken(String authToken) {
        AuthToken = authToken;
    }

    public static ServerCommunicator getClientManager() {
        return clientManager;
    }
}
