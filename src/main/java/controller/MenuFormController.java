package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Navigation;
import util.Route;

import java.io.IOException;
import java.net.URL;

public class MenuFormController {
    public AnchorPane pneContainer;

    public void MemberOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.MEMBER);
    }

    public void BookOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.BOOK);
    }

    public void IssueOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.ISSUEBOOK);
    }

    public void RetunOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.RETURN);
    }
}
