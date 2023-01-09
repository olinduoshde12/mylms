package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import util.Navigation;
import util.Route;

import java.io.IOException;
import java.net.URL;

public class MainFormController {
    public Rectangle rctContainer;
    public Rectangle rstLoading;
    public Label lblloading;

    public void initialize(){
        Timeline timeline=new Timeline();

        KeyFrame keyFrame1=new KeyFrame(Duration.millis(500),actionEvent -> {
            lblloading.setText("Initialize Application");
            rstLoading.setWidth(rctContainer.getWidth()*0.3);
        });
        KeyFrame keyFrame2=new KeyFrame(Duration.millis(1000),actionEvent -> {
            lblloading.setText("Loading internal Resourse");
            rstLoading.setWidth(rctContainer.getWidth()*0.5);
        });
        KeyFrame keyFrame3=new KeyFrame(Duration.millis(1500),actionEvent -> {
            lblloading.setText("Loading Images..");
            rstLoading.setWidth(rctContainer.getWidth()*0.6);
        });
        KeyFrame keyFrame4=new KeyFrame(Duration.millis(2000),actionEvent -> {
            lblloading.setText("Loading Uis..");
            rstLoading.setWidth(rctContainer.getWidth()*0.8);
        });
        KeyFrame keyFrame5=new KeyFrame(Duration.millis(2500),actionEvent -> {
            lblloading.setText("Getting Started..");
            rstLoading.setWidth(rctContainer.getWidth()*0.9);
        });
        KeyFrame keyFrame6=new KeyFrame(Duration.millis(3000),actionEvent -> {
            try {
            URL resourse=this.getClass().getResource("/view/SpalashScreen.fxml");
            Stage stage=new Stage();
                AnchorPane container=FXMLLoader.load(resourse);
                AnchorPane pneContainer= (AnchorPane) container.lookup("#pneContainer");
               // pneContainer.getChildren().clear();
                Navigation.init(pneContainer);
                URL resourse1=this.getClass().getResource("/view/WelcomeForm.fxml");
                AnchorPane welcomeform=FXMLLoader.load(resourse1);
                pneContainer.getChildren().add(welcomeform);
                stage.setScene(new Scene(container));
                stage.centerOnScreen();
                Stage window= (Stage) lblloading.getScene().getWindow();
                window.hide();
                Navigation.navigate(Route.WELCOME);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        timeline.getKeyFrames().addAll(keyFrame1,keyFrame2,keyFrame3,keyFrame4,keyFrame5,keyFrame6);
        timeline.playFromStart();
    }
}
