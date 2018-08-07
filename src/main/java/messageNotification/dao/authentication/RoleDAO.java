package messageNotification.dao.authentication;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import messageNotification.entity.authentication.Role;

public class RoleDAO implements IDAO
{

	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	private Session getSession() 
	{
		SessionFactory sessionFactory = this.entityManagerFactory.unwrap(SessionFactory.class);
		return (sessionFactory.getCurrentSession());
	}
	
	@Override
	public void create(Object object) {
		Session session = getSession();
		session.save((Role)object);
		
	}

	@Override
	public void deleteById(Object id) {
		Session session = getSession();
		Role roleToDelete = session.get(Role.class, (Integer) id);
		session.delete(roleToDelete);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getAll() {
		Session session = getSession();
		Query<Role> query = session.getNamedQuery("Role.findAll");
		return query.list();
	}

	@Override
	public Object getById(Object id) {
		Session session = getSession();
		Role role = session.get(Role.class,(Integer) id);
		return role;
	}

}
