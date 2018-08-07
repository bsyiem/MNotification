package messageNotification.dao.authentication;

import java.util.List;

public interface IDAO 
{
	/*
	 * used to insert a row into the user_login table
	 */
	public void create(Object object);
	
	/*
	 * used to delete a user via user_id
	 */
	public void deleteById(Object id);
	
	/*
	 * select all users
	 */
	public List<?> getAll();
	
	/*
	 * gets a user by user_id
	 */
	public Object getById(Object id);
}
