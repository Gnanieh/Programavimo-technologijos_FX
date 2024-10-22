package org.example.hibernateControllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import javafx.scene.control.Alert;
import org.example.Model.Publication;
import org.example.Utils.FxUtils;

public class CustomHibernate {
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public CustomHibernate(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Publication getPublicationByUserId(int clientId)
    {
        entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Publication> cq = cb.createQuery(Publication.class);
            Root<Publication> root = cq.from(Publication.class);
            cq.select(root).where(cb.equal(root.get("client_id"), clientId));
            Query query = entityManager.createQuery(cq);
            return (Publication) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            FxUtils.generateAlert(Alert.AlertType.ERROR, "FETCH error", "Error during FETCH operation");
            return null;
        } finally {
            if (entityManager != null) { entityManager.close(); }
        }
    }

}
