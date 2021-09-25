package controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.GroupChats;
import models.User;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CreateGroupTabController {
    private static Logger logger = LogManager.getLogger(CreateGroupTabController.class);
    @FXML
    private TextField GroupNametxt;
    @FXML
    private TextField AddUsertxt;
    @FXML
    private Label Userslbl;
    @FXML
    private Label sthlbl;
    private ServerCommunicator serverCommunicator;
    private Gson gson;
    public void initialize(){
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }
    private LinkedList<String> members=new LinkedList<>();
    public void addUser(){
        logger.debug("in addUser from CreateGroupTabController class on values");
        if(!AddUsertxt.getText().equals("")){
            this.members.add(AddUsertxt.getText());
            Userslbl.setText(Userslbl.getText()+AddUsertxt.getText()+",");
        }
    }
    public void createGroup() throws IOException {

        logger.debug("in createGroup from CreateGroupTabController class on values");
        if(!GroupNametxt.getText().equals("")){
            serverCommunicator.sendClicked("GROUP");
            serverCommunicator.sendClicked("createGroup");
            GroupChats newGroup=new GroupChats(GroupNametxt.getText());
            newGroup.setMembers(this.members);
            newGroup.getMembers().add(LoginController.USERNAME);
            serverCommunicator.sendClicked(gson.toJson(newGroup));
            serverCommunicator.sendClicked(gson.toJson(this.members));
            serverCommunicator.sendClicked(GroupNametxt.getText());

//            List<GroupChats> groups=new Filemethods().loadFromFileGroup();
//            groups.add(newGroup);
//            List<User> accounts=new Filemethods().loadFromFile();
//            addToMembersChat(accounts,this.members,GroupNametxt.getText());
//            new Filemethods().saveToFileGroup(groups);
//            new Filemethods().saveToFile(accounts);
            this.members.clear();
            Userslbl.setText("");
            sthlbl.setText("Group Created Successfully!");
        }
    }
    public void addToMembersChat(List<User> accounts, LinkedList<String> members, String GroupName){
        logger.debug("in addToMembersChat from CreateGroupTabController class on values"+accounts+"and"+members);
        for (int i=0;i<accounts.size();i++){
            if(members.contains(accounts.get(i).getUsername())){
                accounts.get(i).getGroupChats().add(GroupName);
            }
        }
    }
    public void discard(){
        logger.debug("in discard from CreateGroupTabController class on values");
        this.members.clear();
        Userslbl.setText("");
    }
}
