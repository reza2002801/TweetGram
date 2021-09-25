package models;

import sun.awt.image.ImageWatched;
import utils.CurrentClient;

import java.util.LinkedList;

public class Change {
    private Boolean isPublic;
    private Boolean isLastSeen;
    private Boolean isActive;
    private LinkedList<message> messages;
    private LinkedList<Groupmessage> groupmessages;
    private String newPassword;

    public Change() {
        this.isPublic = CurrentClient.getUser().getPublicaccount();
        this.isLastSeen = CurrentClient.getUser().getIslastseen();
        this.isActive = CurrentClient.getUser().getIsactive();
        this.messages = new LinkedList<>();
        this.groupmessages = new LinkedList<>();
        this.newPassword = CurrentClient.getUser().getPassword();
    }

    public LinkedList<Groupmessage> getGroupmessages() {
        return groupmessages;
    }

    public void setGroupmessages(LinkedList<Groupmessage> groupmessages) {
        this.groupmessages = groupmessages;
    }


    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Boolean getLastSeen() {
        return isLastSeen;
    }

    public void setLastSeen(Boolean lastSeen) {
        isLastSeen = lastSeen;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LinkedList<message> getMessages() {
        return messages;
    }

    public void setMessages(LinkedList<message> messages) {
        this.messages = messages;
    }
}
