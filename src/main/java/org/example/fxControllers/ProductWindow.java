package org.example.fxControllers;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.Model.*;
import org.example.hibernateControllers.CustomHibernate;
import org.example.hibernateControllers.GenericHibernate;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductWindow implements Initializable {


    @FXML
    public ListView<Publication> publicationListField;
    @FXML
    public ListView<User> userListField;
    @FXML
    public TextField titleField;
    @FXML
    public TextField authorField;
    @FXML
    public TextField publicherField;
    @FXML
    public DatePicker yearDatePick;
    @FXML
    public RadioButton mangaCheck;
    @FXML
    public RadioButton journalCheck;
    @FXML
    public RadioButton bookCheck;
    @FXML
    public TextField illustratorField;
    @FXML
    public TextField originalLanguageField;
    @FXML
    public TextField volumeNumberField;
    @FXML
    public ToggleButton isColloredButton;
    @FXML
    public TextField isbnField;
    @FXML
    public TextField pageCountField;
    @FXML
    public TextField summaryField;


    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bookExchange");
    GenericHibernate hibernate = new GenericHibernate(entityManagerFactory);

    public void fillUserList() {
        userListField.getItems().clear();
        List<User> userList = hibernate.getAllRecords(User.class);
        userListField.getItems().addAll(userList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillUserList();
        disableFields();
    }

    public void createNewPublication() {
        User selectedClient = userListField.getSelectionModel().getSelectedItem();
        Client clientFromDB = hibernate.getEntityById(Client.class, selectedClient.getId());

        if (mangaCheck.isSelected()) {
            Manga manga = new Manga(titleField.getText(), authorField.getText(), publicherField.getText(), yearDatePick.getValue(), "Manga", clientFromDB, illustratorField.getText(), originalLanguageField.getText(), Integer.parseInt(volumeNumberField.getText()), isColloredButton.isSelected());
            hibernate.create(manga);
        }
        else if (journalCheck.isSelected()) {
            Journal journal = new Journal(titleField.getText(), authorField.getText(), publicherField.getText(), yearDatePick.getValue(), "Journal", clientFromDB);
            hibernate.create(journal);
        }
        else if (bookCheck.isSelected()) {
            Book book = new Book(titleField.getText(), authorField.getText(), publicherField.getText(), yearDatePick.getValue(), "Book", clientFromDB, isbnField.getText(), Integer.parseInt(pageCountField.getText()), summaryField.getText());
            hibernate.create(book);
        }
        fillPublicationList();
    }

    public void fillPublicationList() {
        User selectedUser = userListField.getSelectionModel().getSelectedItem();
        User userFromDB = hibernate.getEntityById(User.class, selectedUser.getId());
        CustomHibernate cusHib = new CustomHibernate(entityManagerFactory);

        publicationListField.getItems().clear();
        //List<Publication> publicationList = hibernate.getAllRecords(Publication.class);
        List <Publication> publicationList = new ArrayList<>();
        publicationList.add(cusHib.getPublicationByUserId(userFromDB.getId()));
        publicationListField.getItems().addAll(publicationList);


//        List<Publication> publicationList = new ArrayList<>();
//        for (Publication publication : publicationList)
//                publicationList.add(hibernate.getEntityById(Publication.class, userFromDB.getId()));
//
//        publicationListField.getItems().addAll(hibernate.getEntityById(Publication.class, userFromDB.getId()));
    }

    public void loadPublication()
    {
        Publication selectedPublication = publicationListField.getSelectionModel().getSelectedItem();
        Publication publicationFromDB = hibernate.getEntityById(Publication.class, selectedPublication.getId());

        titleField.setText(publicationFromDB.getTitle());
        authorField.setText(publicationFromDB.getAuthor());
        publicherField.setText(publicationFromDB.getPublisher());
        yearDatePick.setValue(publicationFromDB.getYear());

        if (publicationFromDB instanceof Manga) {
            Manga manga = (Manga) publicationFromDB;
            illustratorField.setText(manga.getIllustrator());
            originalLanguageField.setText(manga.getOriginalLanguage());
            volumeNumberField.setText(String.valueOf(manga.getVolumeNumber()));
            if(manga.isColor() == true)
            {
                isColloredButton.setSelected(true);
            }
            else
            {
                isColloredButton.setSelected(false);
            }
            mangaCheck.setSelected(true);
            journalCheck.setSelected(false);
            bookCheck.setSelected(false);
        }
        else if (publicationFromDB instanceof Book) {
            Book book = (Book) publicationFromDB;
            isbnField.setText(book.getIsbn());
            pageCountField.setText(String.valueOf(book.getPageCount()));
            summaryField.setText(book.getSummary());
            journalCheck.setSelected(false);
            mangaCheck.setSelected(false);
        }
        else {
            mangaCheck.setSelected(false);
            bookCheck.setSelected(false);
            journalCheck.setSelected(true);
        }
    }

    public void deletePublication() {
        Publication selectedPublication = publicationListField.getSelectionModel().getSelectedItem();
        hibernate.delete(Publication.class, selectedPublication.getId());
        fillPublicationList();
    }

    public void disableFields()
    {
        if (mangaCheck.isSelected())
        {
        illustratorField.setDisable(false);
        originalLanguageField.setDisable(false);
        volumeNumberField.setDisable(false);
        isColloredButton.setDisable(false);
        isbnField.setDisable(true);
        pageCountField.setDisable(true);
        summaryField.setDisable(true);
        } else if (journalCheck.isSelected()) {
            isbnField.setDisable(true);
            pageCountField.setDisable(true);
            summaryField.setDisable(true);
            volumeNumberField.setDisable(true);
            isColloredButton.setDisable(true);
            illustratorField.setDisable(true);
            originalLanguageField.setDisable(true);
        }
        else if (bookCheck.isSelected())
        {
            isbnField.setDisable(false);
            pageCountField.setDisable(false);
            summaryField.setDisable(false);
            originalLanguageField.setDisable(true);
            volumeNumberField.setDisable(true);
            isColloredButton.setDisable(true);
            illustratorField.setDisable(true);
       }
//        else{
//
//        }
    }

    public void updatePublication() {
        Publication selectedPublication = publicationListField.getSelectionModel().getSelectedItem();
        Publication publicationFromDB = hibernate.getEntityById(Publication.class, selectedPublication.getId());

        publicationFromDB.setTitle(titleField.getText());
        publicationFromDB.setAuthor(authorField.getText());
        publicationFromDB.setPublisher(publicherField.getText());
        publicationFromDB.setYear(yearDatePick.getValue());

        if (publicationFromDB instanceof Manga) {
            ((Manga) publicationFromDB).setIllustrator(illustratorField.getText());
            ((Manga) publicationFromDB).setOriginalLanguage(originalLanguageField.getText());
            ((Manga) publicationFromDB).setVolumeNumber(Integer.parseInt(volumeNumberField.getText()));
            ((Manga) publicationFromDB).setColor(isColloredButton.isSelected());
        }
        else if (publicationFromDB instanceof Book) {
            ((Book) publicationFromDB).setIsbn(isbnField.getText());
            ((Book) publicationFromDB).setPageCount(Integer.parseInt(pageCountField.getText()));
            ((Book) publicationFromDB).setSummary(summaryField.getText());
        }
        hibernate.update(publicationFromDB);
        fillPublicationList();
    }

    public void clearFields()
    {
        titleField.clear();
        authorField.clear();
        publicherField.clear();
        yearDatePick.setValue(null);
        illustratorField.clear();
        volumeNumberField.clear();
        originalLanguageField.clear();
        isColloredButton.setSelected(false);
        isbnField.clear();
        pageCountField.clear();
        summaryField.clear();
        journalCheck.setSelected(false);
        bookCheck.setSelected(false);
        mangaCheck.setSelected(false);


    }
}
