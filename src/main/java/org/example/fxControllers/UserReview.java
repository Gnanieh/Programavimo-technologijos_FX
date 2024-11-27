package org.example.fxControllers;

import jakarta.persistence.EntityManagerFactory;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.Model.Client;
import org.example.Model.Comment;
import org.example.Model.User;
import org.example.hibernateControllers.CustomHibernate;
import org.example.hibernateControllers.GenericHibernate;

public class UserReview {
    @FXML
    public TreeView<Comment> userReview;
    @FXML
    public ContextMenu commentContextMenu;
    @FXML
    public MenuItem deleteItem;
    @FXML
    public TextArea commentBody;
    @FXML
    public TextField commentTitle;
    @FXML
    public Button updateButton;



    private CustomHibernate customHibernate;
    private GenericHibernate genericHibernate;
    private User currentUser;
    private Client targetClient;

    public void setData(EntityManagerFactory entityManagerFactory, User user, Client client) {
        this.customHibernate = new CustomHibernate(entityManagerFactory);
        this.genericHibernate = new GenericHibernate(entityManagerFactory);
        this.currentUser = user;
        this.targetClient = client;
        fillTree();

        if (currentUser instanceof Client) {
            updateButton.setDisable(true);
            commentContextMenu.hide();
            //deleteItem.setDisable(true);
        }
    }

    private void fillTree() {
        userReview.setRoot(new TreeItem<>());
        userReview.setShowRoot(false);
        userReview.getRoot().setExpanded(true);
        Client clientFromDb = genericHibernate.getEntityById(Client.class, targetClient.getId());
        clientFromDb.getCommentList().forEach(c -> addTreeItem(c, userReview.getRoot()));
    }

    public void addTreeItem(Comment comment, TreeItem<Comment> parentComment) {
        TreeItem<Comment> treeItem = new TreeItem<>(comment);
        parentComment.getChildren().add(treeItem);
        comment.getReplies().forEach(sub -> addTreeItem(sub, treeItem));
    }
}