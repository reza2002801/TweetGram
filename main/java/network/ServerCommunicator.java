package network;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Base64;

public class ServerCommunicator extends Thread {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private int playerType;

    public Socket getSocket() {
        return socket;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public  ServerCommunicator() throws IOException {
        try {
            socket = new Socket("localhost",8000);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }
        catch (Exception e){
            socket=null;
            dataInputStream=null;
            dataOutputStream=null;
            System.out.println("No connection");
        }

//        playerType = Integer.parseInt(dataInputStream.readUTF());
    }
    public void sendFile (FileInputStream fis) throws IOException {
        int i;
//        FileInputStream fis = new FileInputStream (path);
//        Socket sock = new Socket(serverIp, serverPort);
//        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        while ((i = fis.read()) > -1)
            dataOutputStream.write(i);

        fis.close();
//        dataOutputStream.close();
//        sock.close();
    }
    public Boolean isConnected(){
        if(socket!=null && socket.isConnected()){
            try {
                dataOutputStream.writeUTF("ISCONNECT");
//                String s=dataInputStream.readUTF();
                return true;

            }catch (Exception e){
                System.out.println("no Connection");
                return false;
            }
        }
        else {
            return false;
        }
    }
    public int r() throws IOException {
        return  dataInputStream.read();
    }

    @Override
    public synchronized void start() {
        super.start();
    }


    public String read() throws IOException {
        return dataInputStream.readUTF();

    }

    public int getPlayerType() {
        return playerType;
    }

    public  void sendClicked(String json) throws IOException {
        dataOutputStream.writeUTF(json);
    }

    public void sendChats(String str) throws IOException {
        if(isConnected()){
            dataOutputStream.writeUTF("CHATS");
            dataOutputStream.writeUTF(str);
        }

    }

    public void sendUsers(String str) throws IOException {
        if(isConnected()) {
            dataOutputStream.writeUTF("USERS");
            dataOutputStream.writeUTF(str);
        }
    }

    public void sendTweets(String str) throws IOException {
        if(isConnected()) {
            dataOutputStream.writeUTF("TWEETS");
            dataOutputStream.writeUTF(str);
        }
    }
    public void sendImage(File file,String name) throws IOException {
//        if(isConnected()) {

            dataOutputStream.writeUTF("IMAGE");
            dataOutputStream.writeUTF("sendImage");
            dataOutputStream.writeUTF(name);
            int i;
//        FileInputStream fis = new FileInputStream (file);
            String send = encoder(file, "jpg");
            String s = "";
            int m = send.length();
            int k = 0;
            while (m != 0) {
                if (m >= 1000) {
                    s = send.substring(k * 1000, (k + 1) * 1000);
                    dataOutputStream.writeUTF(s);
                    k += 1;
                    m -= 1000;
                }
                if (m < 1000) {
                    s = send.substring(k * 1000);
                    dataOutputStream.writeUTF(s);
                    m = 0;
                }
            }
            dataOutputStream.writeUTF("end");
        File fout =new File("C:\\Users\\NoteBook TANDIS\\Documents\\Tweetgram2\\untitled15\\src\\main\\resources\\images\\"+name);
//        File file1 = new File("C:\\Users\\NoteBook TANDIS\\Downloads\\untitled15\\src\\main\\resources\\images\\"+f+".jpg");
        Files.copy(file.toPath(),fout.toPath());
        assert fout.exists();
//            File fout =new File("C:\\Users\\NoteBook TANDIS\\Documents\\Tweetgram2\\untitled15\\src\\main\\resources\\images\\"+name);
//            recieveFile(fout,send);
//        }
    }
    public Image getImage(String name) throws IOException {
//        if(isConnected()) {
            dataOutputStream.writeUTF("IMAGE");
            dataOutputStream.writeUTF("getImage");
            dataOutputStream.writeUTF(name);
//        System.out.println(serverCommunicator.read());
            String a;
            String send = "";

            while ((a = dataInputStream.readUTF()) != "end") {
                if (a.equals("end")) {
                    break;
                }
                send += a;

            }
            byte[] bytes = Base64.getDecoder().decode(send);
            InputStream inputStream = new ByteArrayInputStream(bytes);
//        return ImageIO.read(inputStream);
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            File fout =new File("C:\\Users\\NoteBook TANDIS\\Documents\\Tweetgram2\\untitled15\\src\\main\\resources\\images\\"+name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", fout);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            return image;
//        }
//        else {
//            return null;
//        }
    }
    public  void recieveFile(File outputfile,String encoded) throws IOException {
        byte[] bytes = Base64.getDecoder().decode(encoded);
        InputStream inputStream = new ByteArrayInputStream(bytes);
//        return ImageIO.read(inputStream);
        BufferedImage bufferedImage=ImageIO.read(inputStream);
        ImageIO.write(bufferedImage, "png", outputfile);


    }
    public String encoder (File img,String format) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(img);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, format, byteArrayOutputStream);
        return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }


}
