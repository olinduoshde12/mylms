package controller;

import Model.BookModel;
import Model.MemberModel;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tm.BookTm;
import tm.MemberTm;
import to.Book;
import to.Member;
import util.Navigation;
import util.Route;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class AddmemberController {

    public AnchorPane pneContainer;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public Label txtId;
    public TextField txtId1;
    public TableView tbl1;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public Label lbl1;
    public Label lbl2;
    public Label lbl3;

    public void initialize(){
        loadNextOrderId();
        lbl2.setVisible(false);
        lbl3.setVisible(false);
        tbl1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setdata((MemberTm) newValue);
            lbl2.setVisible(true);
            lbl3.setVisible(true);
        });
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contatc"));
    }

    private void setdata(MemberTm tm) {
        lbl2.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContatc());
    }

    private void loadNextOrderId() {
        try {
            String orderId = MemberModel.generateNextMeberid();
            lbl1.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void AddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = lbl1.getText();
        String name = txtName.getText();
       String address = txtAddress.getText();
       String contatact = txtContact.getText();



        Member member=new Member(id,name,address,contatact);
        try {
            boolean isAdded = MemberModel.addMember(member);
            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "success..!!").show();
            }
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.ERROR, "Duplicate Value....!!!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
       oblist();
    }

    private void cleatText(){
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();


    }
    private void oblist() throws SQLException, ClassNotFoundException {
        ObservableList<MemberTm> tmList = FXCollections.observableArrayList();
        for (Member a1 : MemberModel.getAllCustomer()) {
            MemberTm tm = new MemberTm(a1.getId(),a1.getName(),a1.getAddress(),a1.getContatc());
            tmList.add(tm);
        }
        tbl1.setItems(tmList);
        new ZoomIn(tbl1).play();
        cleatText();
        tbl1.refresh();
    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
            Navigation.navigate(Route.MENU);
    }

    public void DeleteOnAction(ActionEvent actionEvent) {
        try {

            boolean isDeleted= MemberModel.deleteMember(lbl2.getText());
            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION, "Delete SuccesFully completed").show();
                loadNextOrderId();
            }else{
                new Alert(Alert.AlertType.WARNING, "Delete Fail..").show();
            }
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }

    }

    public void ViewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        oblist();
    }
}
