package org.cimorelli.ctc.dao;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.cimorelli.ctc.dbo.BaseEntity;
import org.cimorelli.ctc.util.DatabaseUtil;

public abstract class BaseDao {

	private <T> T dataAccess( Function<EntityManager, T> action ) {

		EntityManager em = DatabaseUtil.getEntityManager();
		try {
			return action.apply( em );
		} finally {
			if( em.isOpen() ) {
				em.close();
			}
		}
	}

	private void dataAccessVoid( Function<EntityManager, Void> action ) {

		EntityManager em = DatabaseUtil.getEntityManager();
		try {
			action.apply( em );
		} finally {
			if( em.isOpen() ) {
				em.close();
			}
		}
	}

	public void create( BaseEntity entity ) {

		dataAccessVoid( em -> {
			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				em.persist( entity );
				tx.commit();
			} catch( Exception e ) {
				if( tx.isActive() ) {
					tx.rollback();
				}
				throw e;
			}
			return null;
		} );
	}

	public void update( BaseEntity entity ) {

		dataAccessVoid( em -> {
			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				em.merge( entity );
				tx.commit();
			} catch( Exception e ) {
				if( tx.isActive() ) {
					tx.rollback();
				}
				throw e;
			}
			return null;
		} );
	}

	public void delete( BaseEntity entity ) {

		dataAccessVoid( em -> {
			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				BaseEntity entityToRemove = entity;
				if( !em.contains( entityToRemove ) ) {
					entityToRemove = em.merge( entityToRemove );
				}
				em.remove( entityToRemove );
				tx.commit();
			} catch( Exception e ) {
				if( tx.isActive() ) {
					tx.rollback();
				}
				throw e;
			}
			return null;
		} );
	}

	public <T extends BaseEntity> T findById( int id, Class<T> entityClass ) {

		return dataAccess( em -> em.find( entityClass, id ) );
	}

	public <T> List<T> getResultList( String query, Class<T> entityClass ) {

		return getResultList( query, entityClass, null );
	}

	public <T> List<T> getResultList( String query, Class<T> entityClass, Map<String, Object> parameters ) {

		return dataAccess( em -> {
			TypedQuery<T> q = em.createQuery( query, entityClass );
			if( parameters != null ) {
				for( Map.Entry<String, Object> entry : parameters.entrySet() ) {
					q.setParameter( entry.getKey(), entry.getValue() );
				}
			}
			return q.getResultList();
		} );
	}

	public <T> T getSingleResult( String query, Class<T> entityClass ) {

		return getSingleResult( query, entityClass, null );
	}

	public <T> T getSingleResult( String query, Class<T> entityClass, Map<String, Object> parameters ) {

		try {
			return dataAccess( em -> {
				TypedQuery<T> q = em.createQuery( query, entityClass );
				if( parameters != null ) {
					for( Map.Entry<String, Object> entry : parameters.entrySet() ) {
						q.setParameter( entry.getKey(), entry.getValue() );
					}
				}
				return q.getSingleResult();
			} );
		} catch( NoResultException e ) {
			return null;
		}
	}
}
