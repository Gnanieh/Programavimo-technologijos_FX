package org.example.fxControllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Model.User;
import org.example.StartGUI;
import org.example.hibernateControllers.GenericHibernate;
import org.example.hibernateControllers.UserHibernate;

import java.io.IOException;

public class Login {
    @FXML
    public TextField loginTextField;
    @FXML
    public PasswordField passwordTextField;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookExchange");
    GenericHibernate hibernate = new GenericHibernate(entityManagerFactory);
    UserHibernate userHib = new UserHibernate(entityManagerFactory);

    public void validateAndConnect() throws IOException {
        User user = userHib.getEntityByLogin(loginTextField.getText(), passwordTextField.getText());
        if (user != null) {
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(StartGUI.class.getResource("Main.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setTitle("Book Exchange Test");
                    stage.setScene(scene);
                    stage.showAndWait();

        }
    }

    public void registerNewUser() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StartGUI.class.getResource("Register.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Book Exchange Test");
        stage.setScene(scene);
        stage.showAndWait();
    }

}
