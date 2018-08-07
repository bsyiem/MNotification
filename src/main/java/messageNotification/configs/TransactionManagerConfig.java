package messageNotification.configs;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

@Configuration
public class TransactionManagerConfig 
{
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager() 
    {
    	SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class); 
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
 
        return transactionManager;
    }
}
