package controllers;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.Assert.assertThat;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import models.User;
import models.setting;
import network.ServerCommunicator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

public class AccountsMethods {
//    private ServerCommunicator serverCommunicator=CurrentClient.getClientManager();
    private static Logger logger = LogManager.getLogger(AccountsMethods.class);

    public static User findAccount(List<User> w, String username){
        logger.debug("in findAccount from AccountsMethods class on values"+w+"and"+username);
        for(int i=0;i<w.size();i++){
            if(w.get(i).getUsername().equals(username)){
                return w.get(i);
            }
        }
        return null;
    }

    public static boolean isThereAccount(List<User> w, String username){
        logger.debug("in isThereAccount from AccountsMethods class on values"+w+"and"+username);
        for(int i=0;i<w.size();i++){
            if(w.get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    public static void setProfile(Circle frame, User User){
        logger.debug("in findAccount from AccountsMethods class on values"+frame+"and"+User);
        Image image = new Image(LoginController.PROFILE);
        frame.setFill(new ImagePattern(image));
    }
    public static void ChangeProfilePicture(String usename,Circle cr) throws IOException {
        ServerCommunicator serverCommunicator=CurrentClient.getClientManager();
        logger.debug("in ChangeProfilePicture from AccountsMethods class on values"+usename+"and"+cr);

        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(null);
        Random r=new Random();
        int t=r.nextInt(10000);
        String f=String.valueOf(t);
        serverCommunicator.sendImage(file,f+".jpg");
        serverCommunicator.sendClicked("PI");
        serverCommunicator.sendClicked("ChangeProfilePicture");
        serverCommunicator.sendClicked(f);
//        File file1 = new File("C:\\Users\\NoteBook TANDIS\\Downloads\\untitled15\\src\\main\\resources\\images\\"+f+".jpg");
//        Files.copy(file.toPath(),file1.toPath());



        cr.setFill(new ImagePattern(serverCommunicator.getImage(f+".jpg")));
    }
    public static Image imageLoader(String name){
        File file=new File("C:\\Users\\NoteBook TANDIS\\Documents\\Tweetgram2\\untitled15\\src\\main\\resources\\images\\"+name);
        Image image = new Image(file.toURI().toString());
        return image;
    }
    public static String setPictureToTweet() throws IOException {
        logger.debug("in setPictureToTweet from AccountsMethods class");
//        List<user> accounts=new Filemethods().loadFromFile();
        FileChooser fileChooser = new FileChooser();
//        Stage window = (Stage) idPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(null);
        Random r=new Random();
        int t=r.nextInt(10000);
        String f=String.valueOf(t);
//        File file1 = new File("C:\\Users\\NoteBook TANDIS\\Downloads\\untitled15\\src\\main\\resources\\images\\"+f+".jpg");
//        Files.copy(file.toPath(),file1.toPath());
//        tweet.setPictureLink("images/"+f+".jpg");
        return file.toURI().toString();
//        new Filemethods().saveToFile(accounts);
//        Users.getCurrentUser().setProfilePic(file1.toString());
//        Image image = new Image(file.toURI().toString());
//        LoginController.PROFILE=file.toURI().toString();
//        cr.setFill(new ImagePattern(image));
    }
    public static File getFile(){
        FileChooser fileChooser = new FileChooser();
//        Stage window = (Stage) idPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(null);
        return file;
    }
    public static void irdn(File file,String name) throws IOException {
        File fout =new File("C:\\Users\\NoteBook TANDIS\\Documents\\Tweetgram2\\untitled15\\src\\main\\resources\\images\\"+name);
//        File file1 = new File("C:\\Users\\NoteBook TANDIS\\Downloads\\untitled15\\src\\main\\resources\\images\\"+f+".jpg");
        Files.copy(file.toPath(),fout.toPath());
        assert fout.exists();

    }

    public static Boolean isInFollowings(String Username)throws IOException{
        logger.debug("in isInFollowings from AccountsMethods class on values"+Username);
        List<User> accounts=new Filemethods().loadFromFile();
        User User1=findAccount(accounts,Username);
        for(int i=0;i<User1.getFollowings().size();i++){
            if(User1.getFollowings().get(i).equals(LoginController.USERNAME)){
                return true;
            }
        }
        return false;
    }
    public static void deleteFromFollowers(String username)throws IOException{
        logger.debug("in findAccount from AccountsMethods class on values"+username);
//        logger.debug("in deleteFromFollowers from class setting on"+username);
        List<User> e = new Filemethods().loadFromFile();
        for (int i=0;i<e.size();i++){
            if(e.get(i).getFollowers()!=null&&e.get(i).getFollowers().contains(username)){
                e.get(i).getFollowers().remove(username);
            }
        }new Filemethods().saveToFile(e);
    }
    public static void deleteFromFollowings(String username)throws IOException{
        logger.debug("in findAccount from AccountsMethods class on values"+username);
//        logger.debug("in deleteFromFollowings from class setting on"+username);

        List<User> e = new Filemethods().loadFromFile();

        for (int i=0;i<e.size();i++){
            if(e.get(i)!=null&&e.get(i).getFollowings().contains(username)){
                e.get(i).getFollowings().remove(username);
            }
        }
        new Filemethods().saveToFile(e);

    }
    public static void deleteFromTweets(String username)throws IOException{
        logger.debug("in findAccount from AccountsMethods class on values"+username);
//        logger.debug("in deleteFromTweets from class setting on"+username);
        List<User> e = new Filemethods().loadFromFile();
        for (int i=0;i<e.size();i++){
            for (int j=0;j<e.get(i).getFollowingstweet().size();j++){
                if(e.get(i).getFollowingstweet()!=null&&e.get(i).getFollowingstweet().get(j).getUsername().equals(username)){
                    e.get(i).getFollowingstweet().remove(j);
                }
            }

        }new Filemethods().saveToFile(e);

    }

}
