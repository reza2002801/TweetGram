package controllers;

import config.Config;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import models.Category;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.CurrentClient;

import java.io.IOException;
import java.util.List;

public class CategoryListViewController {
    private static Logger logger = LogManager.getLogger(CategoryListViewController.class);
    @FXML
    private GridPane gp;
    @FXML
    private AnchorPane ap;

    public void loadCategories() throws IOException {
        logger.debug("in loadCategories from CategoryListViewController class ");
        Config config=Config.getConfig("fxml");
//        List<User> accounts=new Filemethods().loadFromFile();
//        User currentUser=AccountsMethods.findAccount(accounts,LoginController.USERNAME);
        User currentUser= CurrentClient.getUser();
//        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        double t=0;
        for (int i=0;i<currentUser.getCategories().size();i++){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("CategoriesComponent")));
            t+=110;
            gp.setPrefHeight(t);
            try {
                gp.add((Node)loader.load(),0,i+1);
                gp.setVgap(5);
                CategoriesComponentController childController=loader.getController();
                childController.loadCategoryComponent(currentUser.getCategories().get(i));
                childController.setClvc(this);
//                childController.setParentController(this);
//                gp.setLayoutX(+150);
                gp.setLayoutY(-10);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void loadCategorySurf(Category cat){
        logger.debug("in loadCategorySurf from CategoryListViewController class on values"+cat);
        Config config=Config.getConfig("fxml");

        FXMLLoader loader = new FXMLLoader(getClass().getResource(config.getProperty("GroupMessagingSurface")));

        try {
            ap.getChildren().add((Node) loader.load());
            GroupMessagingSurfaceController childController=loader.getController();
            childController.loadCategoryContacts(cat);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
