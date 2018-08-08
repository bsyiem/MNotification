package messageNotification.configs;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class EntityManagerFactoryConfig 
{
	@Autowired
	DataSource dataSource;
	
	@Bean(name = "entityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		
		emf.setDataSource(dataSource);
		
		JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		
		
		/*
		 * JPA/HIBERNATE properties
		 */
		Properties jpaProperties = new Properties();
		jpaProperties.setProperty(Environment.SHOW_SQL, "true");
		jpaProperties.setProperty(Environment.DEFAULT_SCHEMA, "main");
		jpaProperties.setProperty(Environment.DRIVER, "org.postgresql.Driver");		
		jpaProperties.setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
		jpaProperties.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "org.springframework.orm.hibernate5.SpringSessionContext");
		
		emf.setJpaProperties(jpaProperties);
		/*
		 * set which packages to scan for entities
		 * replaces hibernate mappings
		 */
		emf.setPackagesToScan("messageNotification.entity");
		emf.setPersistenceUnitName("default");
		emf.afterPropertiesSet();
		
		return emf.getObject();
	}
}
