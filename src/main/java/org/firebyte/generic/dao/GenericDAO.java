/*
 * $Id$
 * Created on Mar 25, 2011 by beardj 
 */
package org.firebyte.generic.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Generic DAO interface for common data access functionality
 * 
 * @author <a href="mailto:jeff@firebyte.org">Jeff Beard</a>
 *
 */
public interface GenericDAO<T, PK extends Serializable> {
	
   /**
     * Get the Class of the entity.
     *
     * @return the class
     */
    Class<T> getEntityClass();

    /**
     * Find an entity by its primary key
     *
     * @param id the primary key
     * @return the entity
     */
    T get(final PK id);

    /**
     * Load all entities.
     *
     * @return the list of entities
     */
    List<T> findAll();

    /**
     * Find entities based on an example.
     *
     * @param exampleInstance the example
     * @return the list of entities
     */
    List<T> findByExample(final T exampleInstance);

    /**
     * Find using a named query.
     *
     * @param queryName the name of the query
     * @param params the query parameters
     *
     * @return the list of entities
     */
    List<T> findByNamedQuery(final String queryName, Object... params);

    /**
     * Find using a named query.
     *
     * @param queryName the name of the query
     * @param params the query parameters
     *
     * @return the list of entities
     */
    List<T> findByNamedQueryAndNamedParams(final String queryName, final Map<String, ?extends Object> params);

    /**
     * Count all entities.
     *
     * @return the number of entities
     */
    int countAll();

    /**
     * Count entities based on an example.
     *
     * @param exampleInstance the search criteria
     * @return the number of entities
     */
    int countByExample(final T exampleInstance);
 
    /**
     * Save an entity. This can be either a INSERT or UPDATE in the database.
     * 
     * @param entity the entity to save
     * 
     * @return the saved entity
     */
    T save(final T entity);

    /**
     * delete an entity from the database.
     * 
     * @param entity the entity to delete
     */
    void remove(final T entity);
    
    /**
     * Delete an entity from the database by PK
     * 
     * @param id
     */
    void remove(final PK id);
    
    /**	
     * Setting for EntityManager DI
     * @param entityManager
     */
    void setEntityManager(EntityManager entityManager);
}
