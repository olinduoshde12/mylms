package controller;

import Model.BookModel;
import Model.MemberModel;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import tm.BookTm;
import tm.MemberTm;
import to.Book;
import to.Member;
import util.Navigation;
import util.Route;

import java.io.IOException;
import java.sql.SQLException;

public class AddbookFormController {
    public AnchorPane pneContainer;
    public Label lblid1;
    public JFXTextField txtTitle;
    public JFXTextField txtAutohr;
    public JFXTextField txtIsbn;
    public TableView tbl1;
    public TableColumn colId;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colIsbn;
    public Label lblid2;
    public Label lbl3;


    public void initialize(){
        loadNextOrderId();
        lblid2.setVisible(false);
        lbl3.setVisible(false);
        tbl1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setdata((BookTm) newValue);
            lblid2.setVisible(true);
            lbl3.setVisible(true);
        });
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("tittle"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
    }

    private void setdata(BookTm tm) {
        lblid2.setText(tm.getId());
        txtTitle.setText(tm.getTittle());
        txtAutohr.setText(tm.getAuthor());
        txtIsbn.setText(tm.getIsbn());

    }

    private void loadNextOrderId() {
        try {
            String orderId = BookModel.generateNextBookid();
            lblid1.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void AddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = lblid1.getText();
        String tittle = txtTitle.getText();
        String author = txtAutohr.getText();
        String isbn = txtIsbn.getText();



        Book book=new Book(id,tittle,author,isbn);
        try {
            boolean isAdded = BookModel.addBook(book);
            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "success..!!").show();
            }
        } catch (
                SQLException throwables) {
            new Alert(Alert.AlertType.ERROR, "Duplicate Value....!!!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        oblist();

    }
    private void cleatText(){
        txtTitle.clear();
        txtAutohr.clear();
        txtIsbn.clear();

    }
    private void oblist() throws SQLException, ClassNotFoundException {
        ObservableList<BookTm> tmList = FXCollections.observableArrayList();
        for (Book a1 : BookModel.getAllbooks()) {
            BookTm tm = new BookTm(a1.getId(),a1.getTittle(),a1.getAuthor(),a1.getIsbn());
            tmList.add(tm);
        }
        tbl1.setItems(tmList);
        new ZoomIn(tbl1).play();
        cleatText();
        tbl1.refresh();
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        try {

            boolean isDeleted=BookModel.deleteBook(lblid2.getText());
            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION, "Delete SuccesFully completed").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Delete Fail..").show();
            }
        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }

    public void viewOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
       oblist();
    }

    public void BackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Route.MENU);
    }
}
