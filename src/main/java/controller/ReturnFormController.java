package controller;

import javafx.event.ActionEvent;
import util.Navigation;
import util.Route;

import java.io.IOException;

public class ReturnFormController {
    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.MENU);
    }
}
