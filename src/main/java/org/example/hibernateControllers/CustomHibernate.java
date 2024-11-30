package org.example.hibernateControllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import javafx.scene.control.Alert;
import org.example.Model.*;
import org.example.Model.Enum.PublicationStatus;
import org.example.Utils.FxUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomHibernate{
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public CustomHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Publication> getPublicationByUserId(Client client)
    {
        entityManager = null;
        List<Publication> publications = new ArrayList<>();
        try {
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Publication> cq = cb.createQuery(Publication.class);
            Root<Publication> root = cq.from(Publication.class);

            cq.select(root).where(cb.equal(root.get("owner"), client));

            Query query = entityManager.createQuery(cq);
            publications = query.getResultList();
            return publications;
        } catch (Exception e) {
            e.printStackTrace();
            FxUtils.generateAlert(Alert.AlertType.ERROR, "FETCH error", "Error during FETCH operation");
            return null;
        } finally {
            if (entityManager != null) { entityManager.close(); }
        }
    }
    public List<Publication> getAvailablePublications(User user) {

        List<Publication> publications = new ArrayList<>();
        try {
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Publication> query = cb.createQuery(Publication.class);
            Root<Publication> root = query.from(Publication.class);
            if(user instanceof Client) {
                query.select(root).where(cb.and(cb.equal(root.get("publicationStatus"), PublicationStatus.AVAILABLE), cb.equal(root.get("owner"), user)));
            }
            else if (user instanceof Admin){
                query.select(root).where(cb.equal(root.get("publicationStatus"), PublicationStatus.AVAILABLE));
            }
            Query q = entityManager.createQuery(query);
            publications = q.getResultList();
            return publications;
        } catch (Exception e) {
            e.printStackTrace();
            FxUtils.generateAlert(Alert.AlertType.ERROR, "FETCH error", "Error during FETCH operation");
            return null;
        } finally {
            if (entityManager != null) { entityManager.close(); }
        }

    }

    public void deleteComment(int id) {
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            var comment = entityManager.find(Comment.class, id);

            if (comment.getParentComment() != null) {
                Comment parentComment = entityManager.find(Comment.class, comment.getParentComment().getId());
                parentComment.getReplies().remove(comment);
                entityManager.merge(parentComment);
            }

            comment.getReplies().clear();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (entityManager != null) entityManager.close();
        }
    }

    public List<Publication> getOwnPublications(User user) {

        List<Publication> publications = new ArrayList<>();
        try {
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Publication> query = cb.createQuery(Publication.class);
            Root<Publication> root = query.from(Publication.class);

            query.select(root).where(cb.equal(root.get("owner"), user));
            //query.orderBy(cb.desc(root.get("requestDate")));

            Query q = entityManager.createQuery(query);
            publications = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return publications;
    }

    public List<PeriodicRecord> getPeriodicById(int id) {
        List<PeriodicRecord> periodicRecords = new ArrayList<>();
        try {
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<PeriodicRecord> query = cb.createQuery(PeriodicRecord.class);
            Root<PeriodicRecord> root = query.from(PeriodicRecord.class);
            Publication publication = entityManager.find(Publication.class, id);

            query.select(root).where(cb.equal(root.get("publication"), publication));
            query.orderBy(cb.desc(root.get("transactionDate")));

            Query q = entityManager.createQuery(query);
            periodicRecords = q.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return periodicRecords;
    }
}
