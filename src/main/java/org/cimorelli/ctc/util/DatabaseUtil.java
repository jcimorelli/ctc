package org.cimorelli.ctc.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

public class DatabaseUtil {

	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory( "ctcPU" );

	public static EntityManager getEntityManager() {

		return emf.createEntityManager();
	}

	public static void updateLiquibase() {

		EntityManager em = getEntityManager();
		Connection connection = null;
		try {
			// Unwrap a JDBC Connection from the EntityManager. This uses the persistence.xml settings.
			connection = em.unwrap( Connection.class );
			JdbcConnection jdbcConnection = new JdbcConnection( connection );
			Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation( jdbcConnection );
			Liquibase liquibase = new Liquibase( "schema_init.xml", new ClassLoaderResourceAccessor(), database );
			liquibase.update( ( String )null );
		} catch( LiquibaseException e ) {
			throw new RuntimeException( e );
		} finally {
			closeConnection( connection );
			em.close();
		}
	}

	private static void closeConnection( Connection connection ) {

		if( connection != null ) {
			try {
				connection.rollback();
				connection.close();
			} catch( SQLException e ) {
				System.out.println( "Failed to close Liquibase db connection!" );
			}
		}
	}
}

