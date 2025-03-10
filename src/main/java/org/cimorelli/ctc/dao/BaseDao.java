package org.cimorelli.ctc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.cimorelli.ctc.dbo.BaseEntity;
import org.cimorelli.ctc.util.DatabaseUtil;

public abstract class BaseDao {

	protected EntityManager em;

	protected void init() {

		em = DatabaseUtil.getEntityManager();
	}

	public void create( BaseEntity entity ) {

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
	}

	public void update( BaseEntity entity ) {

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
	}

	public void delete( BaseEntity entity ) {

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			if( !em.contains( entity ) ) {
				entity = em.merge( entity );
			}
			em.remove( entity );
			tx.commit();
		} catch( Exception e ) {
			if( tx.isActive() ) {
				tx.rollback();
			}
			throw e;
		}
	}

	public void close() {

		if( em.isOpen() ) {
			em.close();
		}
	}
}
