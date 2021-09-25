package controllers;

import com.google.gson.Gson;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import models.User;
import network.ServerCommunicator;
import utils.CurrentClient;

import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class test {
    @FXML
    private Circle c1;
    @FXML
    private Circle c2;
    private ServerCommunicator serverCommunicator;
    private Gson gson;
    public void initialize() throws Exception {
        this.serverCommunicator= CurrentClient.getClientManager();
        this.gson=new Gson();
    }
    @FXML
    public  void ChangeProfilePicture() throws IOException {
//        logger.debug("in ChangeProfilePicture from AccountsMethods class on values"+usename+"and"+cr);
//        List<User> accounts=new Filemethods().loadFromFile();
        FileChooser fileChooser = new FileChooser();
//        Stage window = (Stage) idPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(null);
        FileInputStream fos = new FileInputStream(file);
//        this.serverCommunicator.sendClicked(fos);
        Random r = new Random();
        int t = r.nextInt(10000);
        String f = String.valueOf(t);
        serverCommunicator.sendClicked("IMAGE");
        serverCommunicator.sendClicked("ChangeProfilePicture");
        int i;
//        FileInputStream fis = new FileInputStream (file);

        String send=encoder(file,"jpg");
        String s="";
        int m=send.length();
        System.out.println(m);
        int k=0;
        while (m!=0 ){
            if(m>=1000){
                s=send.substring(k*1000,(k+1)*1000);
                serverCommunicator.sendClicked(s);
                k+=1;
                m-=1000;
            }
            if(m<1000){
                s=send.substring(k*1000);
                serverCommunicator.sendClicked(s);
                m=0;
            }
        }
        System.out.println("her");
        serverCommunicator.sendClicked("end");

//        serverCommunicator.sendClicked(send);






    }
    public void  fff() throws IOException {
        serverCommunicator.sendClicked("IMAGE");
        serverCommunicator.sendClicked("fff");
//        System.out.println(serverCommunicator.read());
        String a;
        String send="";

        while((a=serverCommunicator.read()) != "end"){
            System.out.println(a);
            if(a.equals("end")){
                System.out.println("hi");
                break;
            }
            send+=a;
            System.out.println(send.length());
        }
        System.out.println("sddd");
        System.out.println(send.length());

        byte[] bytes = Base64.getDecoder().decode(send);
        InputStream inputStream = new ByteArrayInputStream(bytes);
//        return ImageIO.read(inputStream);
        BufferedImage bufferedImage=ImageIO.read(inputStream);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        c2.setFill(new ImagePattern(image));


    }
    public  void recieveFile(File outputfile) throws IOException {
//        BufferedImage bufferedImage = ImageIO.read(file);
        BufferedImage bufferedImage = ImageIO.read(serverCommunicator.getSocket().getInputStream());
//        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        ImageIO.write(bufferedImage, "png", outputfile);
        serverCommunicator.getDataOutputStream().flush();
//        serverCommunicator.getSocket().close();
//        serverCommunicator.getSocket().connect("localhost",8000);

    }
    public String encoder (File img,String format) throws IOException {
        BufferedImage bufferedImage =ImageIO.read(img);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, format, byteArrayOutputStream);
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }
}
