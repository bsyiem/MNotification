package messageNotification.entity.authentication;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "role")
@NamedQueries({
	@NamedQuery(
			name = "Role.findAll",
			query = "FROM Role"
			)
})
public class Role 
{
	public enum RoleType 
	{
		PENDING, 
		ADMIN,
		AUTHORIZED
	};
	
	
	@Id
	@Column(name = "role_id")
	private int id;
	
	@Column(name = "role")
	private String role;

	public Role() {
		super();
	}
	
	@ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<UserLogin> userLogins;

	public Set<UserLogin> getUserLogins() {
		return userLogins;
	}

	public void setUserLogins(Set<UserLogin> userLogins) {
		this.userLogins = userLogins;
	}

	public Role(RoleType role) {
		super();
		this.id = role.ordinal();
		this.role = role.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public boolean equals(Object o) 
	{
		if(o instanceof Role) {
			Role role = (Role)o;
			return (this.id == role.id)?true:false;
		}
		return false;
	}
	
	@Override
	public int hashCode() 
	{
		return role.toString().hashCode();
	}

}
