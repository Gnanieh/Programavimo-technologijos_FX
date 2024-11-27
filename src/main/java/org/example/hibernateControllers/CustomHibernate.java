package org.example.hibernateControllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import javafx.scene.control.Alert;
import org.example.Model.Client;
import org.example.Model.Enum.PublicationStatus;
import org.example.Model.Publication;
import org.example.Model.User;
import org.example.Utils.FxUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomHibernate {
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

            query.select(root).where(cb.and(cb.equal(root.get("publicationStatus"), PublicationStatus.AVAILABLE), cb.notEqual(root.get("owner"), user)));

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

}
