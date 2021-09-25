package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.GroupChats;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GroupIconComponentController {
    private static Logger logger = LogManager.getLogger(GroupIconComponentController.class);
    @FXML
    private Label memberstxt;
    @FXML
    private Label groupnametxt;
    private GroupChats groupChats;

    public GroupsListViewController getGlvc() {
        logger.debug("in GroupsListViewController from GroupIconComponentController class on values");
        return glvc;
    }

    public void setGlvc(GroupsListViewController glvc) {
        logger.debug("in setGlvc from GroupIconComponentController class on values"+glvc);
        this.glvc = glvc;
    }

    private GroupsListViewController glvc;
    public void loadGroupIcon(GroupChats group){
        logger.debug("in loadGroupIcon from GroupIconComponentController class on values"+group);
        this.groupChats=group;
        String s="";
        for(int i=0;i<group.getMembers().size();i++){
            s+=group.getMembers().get(i);
            s+=",";
        }
        memberstxt.setText(memberstxt.getText()+s);
        groupnametxt.setText(groupnametxt.getText()+group.getGroupName());
    }
    public void goToGroupChatSurf(){
        logger.debug("in goToGroupChatSurf from GroupIconComponentController class on values");
        this.glvc.loadGroupChatSurf(this.groupChats);
    }


}
