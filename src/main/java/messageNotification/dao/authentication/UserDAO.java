package messageNotification.dao.authentication;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import messageNotification.entity.authentication.User;
import messageNotification.entity.authentication.UserLogin;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.query.Query;

@Transactional
@Repository
public class UserDAO implements IDAO
{
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	public UserDAO() {
		super();
	}

	private Session getSession() 
	{
		SessionFactory sessionFactory = this.entityManagerFactory.unwrap(SessionFactory.class);
		return (sessionFactory.getCurrentSession());
	}
	
	@Override
	public void create(Object user)
	{		
		Session session = getSession();
		/*
		 * point to note
		 * session.save checks if parent is already present in a foreign key relation and does not attempt to reinsert it
		 * session.persist does not check if parent is already present in a foreign key relation 
		 * and attempts to insert it again which results in a duplicate key exception
		 */
		
		/*
		 * Also, using session.save will require the use of org.hibernate.annotations.CascadeType.Save_Update
		 * for cascades to work
		 */
		session.save((User)user);

	}

	@Override
	public void deleteById(Object id) {
				
		Session session = getSession();
		
		User userToDelete = session.get(User.class, (Integer)id);
		session.delete(userToDelete);
	}
	
	public void deleteByEmail(String email) {
		
		Session session = getSession();
		
		UserLogin userLogin = session.get(UserLogin.class, email);
		
		User userToDelete = userLogin==null?null:userLogin.getUser();
		 
		session.delete(userToDelete);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() 
	{
		Session session = getSession();
		
		Query<User> query = session.getNamedQuery("User.findAll");
		
		return (query.list());	
	}

	@Override
	public User getById(Object id) 
	{
		Session session = getSession();
		
		User user = session.get(User.class, (Integer)id);		
		return user;
	}
	
	public User getByEmail(Object email) 
	{
		Session session = getSession();
		
		UserLogin userLogin = session.get(UserLogin.class, (String)email);
		
		return userLogin == null? null:userLogin.getUser();
	}
	
}
