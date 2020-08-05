package bg.avi.common.db.repository;

import java.io.Serializable;
import java.util.List;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

/**
 * @author Alexander Verbovskiy
 * @version 1.0
 * @since 2020-03-21
 * 
 * @param <T>
 * @param <ID>
 */
public class AbstractJPARepository<T, ID extends Serializable> {
	@PersistenceContext
	private EntityManager entityManager;
	private Class<T> persistentClass;
	
	@SuppressWarnings("unchecked")
	protected AbstractJPARepository() {
		this.persistentClass =
				(Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	public final void setClazz(Class<T> classToSet){
		this.persistentClass = classToSet;
	}
	 
	public T findById(ID id){
		return entityManager.find(persistentClass, id);
	}
	   
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return entityManager.createQuery("from " + persistentClass.getName())
				.getResultList();
	}
	
	public void create(T entity){
		entityManager.persist(entity);
	}
	 
	public T update(T entity){
		return entityManager.merge(entity);
	}
	
	public T saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
		return entity;
	}
	 
	public void delete(T entity){
		entityManager.remove( entity );
	}
	
	public final void delete(List<T> entities) {
		for (T entity : entities) {
			delete(entity);
		}
	}
	
	public final void deleteAll() {
		entityManager.createQuery("delete from " + persistentClass.getName()).executeUpdate();
	}
	
	public final void deleteById(ID id) {
		T entity = findById(id);
		delete(entity);
	}
	 
	//
	public final CriteriaBuilder criteriaBuilder() {
		return entityManager.getCriteriaBuilder();
	}
	
	public final <U> TypedQuery<U> createQuery(CriteriaQuery<U> criteria) {
		return entityManager.createQuery(criteria);
	}
	
	public final <U> Query createQuery(CriteriaDelete<U> criteria) {
		return entityManager.createQuery(criteria);
	}
	
	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}
}