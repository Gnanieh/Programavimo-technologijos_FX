package org.example.fxControllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.Model.Admin;
import org.example.Model.Client;
import org.example.Model.User;
import org.example.StartGUI;
import org.example.hibernateControllers.GenericHibernate;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Main implements Initializable {
    @FXML
    public ListView<User> userListField;
    @FXML
    public TextField loginField;
    @FXML
    public TextField nameField;
    @FXML
    public TextField pswField;
    @FXML
    public TextField surnameField;
    @FXML
    public TextField addressField;
    @FXML
    public DatePicker bDay;
    @FXML
    public TextField phoneNumField;
    @FXML
    public TextField emailField;
    @FXML
    public RadioButton adminCheck;
    @FXML
    public RadioButton clientCheck;


    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookExchange");
    GenericHibernate hibernate = new GenericHibernate(entityManagerFactory);

    public void createNewUser() {
        if (clientCheck.isSelected()) {
            Client client = new Client(loginField.getText(), pswField.getText(), nameField.getText(), surnameField.getText(), addressField.getText(), bDay.getValue(), emailField.getText());
            hibernate.create(client);
        }
        else{
            Admin admin = new Admin(loginField.getText(), pswField.getText(), nameField.getText(), surnameField.getText(), phoneNumField.getText());
            hibernate.create(admin);
        }
        fillUserList();
    }

    public void disableFields() {
        if (clientCheck.isSelected()) {
            addressField.setDisable(false);
            bDay.setDisable(false);
            emailField.setDisable(false);
            phoneNumField.setDisable(true);
        } else {
            addressField.setDisable(true);
            bDay.setDisable(true);
            phoneNumField.setDisable(false);
            emailField.setDisable(true);
        }
    }

    public void fillUserList() {
        userListField.getItems().clear();
        nameField.clear();
        surnameField.clear();
        addressField.clear();
        loginField.clear();
        pswField.clear();
        phoneNumField.clear();
        emailField.clear();
        List<User> userList = hibernate.getAllRecords(User.class);
        userListField.getItems().addAll(userList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillUserList();
        disableFields();
    }

    public void loadUserData()
    {
        User selectedUser = userListField.getSelectionModel().getSelectedItem();
        User userInfoFromDB = hibernate.getEntityById(User.class, selectedUser.getId());

        nameField.setText(userInfoFromDB.getName());
        surnameField.setText(userInfoFromDB.getSurname());

        if (userInfoFromDB instanceof Client)
        {
            Client client = (Client) userInfoFromDB;
            addressField.setText(client.getAddress());
            emailField.setText(client.getEmail());
            bDay.setValue(client.getBirthDate());
        } else {
            Admin admin = (Admin) userInfoFromDB;
            phoneNumField.setText(admin.getPhoneNumber());
        }
    }

    public void updateUser() {
        User selectedUser = userListField.getSelectionModel().getSelectedItem();
        User userInfoFromDB = hibernate.getEntityById(User.class, selectedUser.getId());

        userInfoFromDB.setName(nameField.getText());
        userInfoFromDB.setSurname(surnameField.getText());

        if (userInfoFromDB instanceof Client)
        {
            addressField.setText(((Client) userInfoFromDB).getAddress());
            emailField.setText(((Client) userInfoFromDB).getEmail());
            bDay.setValue(((Client) userInfoFromDB).getBirthDate());
        } else {
            phoneNumField.setText(((Admin) userInfoFromDB).getPhoneNumber());
        }

        hibernate.update(userInfoFromDB);
        fillUserList();
    }

    public void deleteUser() {
        User selectedUser = userListField.getSelectionModel().getSelectedItem();
        hibernate.delete(User.class, selectedUser.getId());
        fillUserList();
    }

    public void loadProductForm() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(StartGUI.class.getResource("productWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Book Exchange Test");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();
    }

}
