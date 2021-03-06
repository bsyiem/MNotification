package messageNotification.dao.authentication;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import messageNotification.entity.authentication.UserLogin;

@Transactional
@Repository
public class UserLoginDAO implements IDAO{

	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	private Session getSession() 
	{
		SessionFactory sessionFactory = this.entityManagerFactory.unwrap(SessionFactory.class);
		return (sessionFactory.getCurrentSession());
	}
	
	@Override
	public void create(Object userLogin) {
		Session session = getSession();
		session.save((UserLogin)userLogin);
		
	}

	@Override
	public void deleteById(Object email) {
		Session session = getSession();
		
		UserLogin userLoginToDelete = session.get(UserLogin.class, (String)email);
		session.delete(userLoginToDelete);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserLogin> getAll() {
		Session session = getSession();
		
		Query<UserLogin> query = session.getNamedQuery("UserLogin.findAll");
		
		return (query.list());
	}

	@Override
	public Object getById(Object email) {
		Session session = getSession();
		
		UserLogin userLogin = session.get(UserLogin.class, (String)email);
		
		return userLogin;
	}
	
	public Object loadById(Object email) {
		Session session = getSession();
		
		UserLogin userLogin = session.load(UserLogin.class, (String)email);
		
		return userLogin;
	}
	
	
	public void updateUserLogin(UserLogin uLogin) {
		Session session = getSession();
		session.update(uLogin);
//		UserLogin userLoginToUpdate = session.load(UserLogin.class, uLogin.getEmail());
//		userLoginToUpdate.setRoles(uLogin.getRoles());
//		session.update(userLoginToUpdate);
	}
	
	public void updatePassword(UserLogin userLogin) {
		Session session = getSession();		
		UserLogin selectedUserLogin = session.load(UserLogin.class, userLogin.getEmail());
		selectedUserLogin.setPassword(userLogin.getPassword());
		session.update(selectedUserLogin);
	}

}
