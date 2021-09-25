import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class child extends Thread {
    public void run() {
        for (int i = 0; i < 50000000; i++) {
            add();
        }
//        notifyAll();
        b+=1;
    }

    public static int d=0;
    synchronized public static void add(){
        d+=1;
    }
    static int b=0;

    public static void main(String[] args) throws InterruptedException {
        d=loadUsers();

        child c=new child();
        child f=new child();
        c.start();
        f.start();
//        try {
//            currentThread().wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        while(true){
            if(b==2){
                break;
            }
            else {
                System.out.print("");
            }
//            System.out.println(b);
//            Thread.sleep(1);
        }
        System.out.println(d);
    }
    public static Integer loadUsers() {
        try {
            File userFile = new File("Users.txt");
//            if (userFile == null){
                System.out.println("not found");
//            } else {
                Gson gson = new Gson();
                Scanner ss = new Scanner(userFile);
//                LinkedList<User> users = new LinkedList<>();
                while (ss.hasNextLine()) {
                    Integer user = gson.fromJson(ss.nextLine(), Integer.class);
//                    users.add(user);
//                }
//                log("ModelLoader-Users Loaded");
                return user;
            }
        } catch (Exception e) {
//            log("ModelLoader-ERROR Occurred when loading users");
            System.out.println(e);
        }
        return null;
    }

}
