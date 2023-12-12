package org.TeamCodeDefy.persistance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * A generic DAO somewhat inspired by http://rodrigouchoa.wordpress.com
 *
 */
public class GenericDao<T> {

    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the entity type, for example, User.
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    public Boolean exists(int id) {
        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity != null;
    }

    /**
     * Gets an entity by id
     * @param id entity id to search by
     * @return entity
     */
    public <T> T getById(int id) {
        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Deletes the entity.
     *
     * @param entity entity to be deleted
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Inserts the entity.
     *
     * @param entity entity to be inserted
     */
    public int insert(T entity) {
        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Inserts or updates the entity.
     *
     * @param entity entity to be inserted/saved
     */
    public void saveOrUpdate(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Finds entities by one of its properties.
     *
     * @param properties HashMap of property values
     * @return entities list of entities
     */
    public List<T> findByPropertyEqual(Map<String, Object> properties) {
        // Search for the entity based on an hashmap of properties and values
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);

        query.select(root);

        for (Map.Entry<String, Object> key : properties.entrySet()) {
            query.where(builder.equal(root.get(key.getKey()), key.getValue()));
        }

        List<T> entities = session.createQuery(query).getResultList();
        session.close();
        return entities;
    }

    /**
     * Returns an open session from the SessionFactory
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }

}