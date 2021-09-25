package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CategoriesComponentController {
    private static Logger logger = LogManager.getLogger(CategoriesComponentController.class);
    public CategoryListViewController clvc;

    public void setClvc(CategoryListViewController clvc) {
        logger.debug("in setClvc from CategoriesComponentController class on values"+clvc);
        this.clvc = clvc;
    }

    @FXML
    private Label Userslbl;
    @FXML
    private Label CategoryNamelbl;
    private Category category;

    public void loadCategoryComponent(Category category){
        logger.debug("in loadCategoryComponent from CategoriesComponentController class on values"+category);
        this.category=category;
        CategoryNamelbl.setText(CategoryNamelbl.getText()+category.getCatname());
        String s="";
        for(int i=0;i<category.getMembers().size();i++){
            s+=category.getMembers().get(i);
            s+=",";
        }
        Userslbl.setText(Userslbl.getText()+s);
    }
    public void goToCategorySurface(){
        logger.debug("in goToCategorySurface from CategoriesComponentController class ");
        this.clvc.loadCategorySurf(this.category);
    }

}
