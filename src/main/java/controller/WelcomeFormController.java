package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Navigation;
import util.Route;

import java.io.IOException;
import java.net.URL;

public class WelcomeFormController {
    public JFXButton btnMenu;
    public AnchorPane pneContainer;

    public void MenuOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.MENU);
    }
}
