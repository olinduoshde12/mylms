package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Navigation {
    public static AnchorPane pneContainer;

    public static void init(AnchorPane pneContainer){
        Navigation.pneContainer=pneContainer;
    }
    public static void navigate(Route route) throws IOException {
        pneContainer.getChildren().clear();
        Stage container= (Stage) pneContainer.getScene().getWindow();
        URL resourse=null;
        switch (route){
            case WELCOME:
                resourse=Navigation.class.getResource("/view/WelcomeForm.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;
            case MENU:
                resourse=Navigation.class.getResource("/view/MenuForm.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;
            case MEMBER:
                resourse=Navigation.class.getResource("/view/Addmember.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;
            case  BOOK:
                resourse=Navigation.class.getResource("/view/AddbookForm.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;
            case  ISSUEBOOK:
                resourse=Navigation.class.getResource("/view/IssueBookForm.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;
            case  RETURN:
                resourse=Navigation.class.getResource("/view/ReturnForm.fxml");
                container.setTitle("Welcome to LMS v1.0.0");
                break;

        }
        FXMLLoader fxmlLoader=new FXMLLoader(resourse);
        AnchorPane load=fxmlLoader.load();
        pneContainer.getChildren().addAll(load);
    }
}
