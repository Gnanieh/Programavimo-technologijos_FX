package org.example.fxControllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.Model.Client;
import org.example.Model.User;
import org.example.hibernateControllers.GenericHibernate;

import java.net.URL;
import java.util.ResourceBundle;

public class Main implements Initializable {
    @FXML
    public ListView<User> userListField;
    public TextField loginField;
    public TextField nameField;
    public TextField pswField;
    public TextField surnameField;
    public TextField addressField;
    public DatePicker bDate;

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookExchange");

    public void createNewUser() {
        Client client = new Client(loginField.getText(), pswField.getText(), nameField.getText(), surnameField.getText(), addressField.getText(), bDate.getValue());
        GenericHibernate hibernate = new GenericHibernate(entityManagerFactory);

        hibernate.create(client);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
