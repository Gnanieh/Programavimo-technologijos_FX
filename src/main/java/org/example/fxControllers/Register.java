package org.example.fxControllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.example.Model.User;
import org.example.hibernateControllers.GenericHibernate;
import org.example.hibernateControllers.UserHibernate;

import java.io.IOException;

public class Register {
    @FXML
    public TextField nameTextField;
    @FXML
    public TextField surNameTextField;
    @FXML
    public TextField addressTextField;
    @FXML
    public TextField emailTextField;
    @FXML
    public TextField numberTextField;
    @FXML
    public DatePicker birthDateDatePicker;
    @FXML
    public ComboBox<User> userTypeComboBox;
    @FXML
    public Button registerButton;
    @FXML
    public Button clearButton;
    @FXML
    public TextField loginTextField;
    @FXML
    public TextField passwordTextField;


    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookExchange");
    GenericHibernate hibernate = new GenericHibernate(entityManagerFactory);
    UserHibernate userHib = new UserHibernate(entityManagerFactory);


    public void RegisterNewUser() throws IOException
    {

    }
}
