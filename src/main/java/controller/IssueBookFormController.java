package controller;

import Model.BookModel;
import Model.MemberModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import util.Navigation;
import util.Route;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class IssueBookFormController {
    public AnchorPane pneContainer;
    public ComboBox cobId1;
    public ComboBox comid1;
    public void initialize(){
        loadIds();
        loadMemberIds();
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.MENU);
    }
    private void loadIds() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = BookModel.loadBookids();

            for (String type : idList) {
                observableList.add(type);
            }
            cobId1.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadMemberIds() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            ArrayList<String> idList = MemberModel.loadMemberIds();

            for (String type : idList) {
                observableList.add(type);
            }
            comid1.setItems(observableList);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
