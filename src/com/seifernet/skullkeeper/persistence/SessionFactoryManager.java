package com.seifernet.skullkeeper.persistence;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.seifernet.skullkeeper.persistence.dto.Author;
import com.seifernet.skullkeeper.persistence.dto.Comment;
import com.seifernet.skullkeeper.persistence.dto.Hashtag;

/**
 * Session factory manager creates and configurates
 * the session factory object
 * 
 * @author Seifer ( Cuauhtemoc Herrera Muñoz )
 * @version 1.0.0
 * @since 1.0.0
 *
 */
public abstract class SessionFactoryManager {

	private static SessionFactory instance;
	
	/**
	 * Session factory singleton, to avoid multiple factory
	 * instantiation.
	 * 
	 * @return The session factory object
	 */
	public static SessionFactory getSessionFactoryInstance(  ){
		
		if( instance == null ){
			initializeSessionFactory( );
		}
		return instance;
	}
	
	/**
	 * Session factory configuration
	 * 
	 */
	public static void initializeSessionFactory(  ){
		Configuration 		configuration 	= null;
		Properties			properties		= null;
		ServiceRegistry		builder 		= null;
		
		configuration = new Configuration( );
		properties = new Properties( );
		
		properties.setProperty( "hibernate.connection.datasource" , "java:jboss/datasources/skullkeeper" );
		properties.setProperty( "hibernate.dialect" , "org.hibernate.dialect.PostgreSQLDialect" );
		properties.setProperty( "hibernate.current_session_context_class", "thread" );
		 
		configuration.addAnnotatedClass( Comment.class );
		configuration.addAnnotatedClass( Author.class );
		configuration.addAnnotatedClass( Hashtag.class );
		
		configuration.setProperties( properties );
		builder = new StandardServiceRegistryBuilder( ).applySettings( configuration.getProperties(  ) ).build( );
		instance = configuration.buildSessionFactory( builder );
		
	}
	
}
