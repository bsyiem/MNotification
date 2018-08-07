package messageNotification.entity.authentication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


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
		PUBLIC_ENQUIRY, 
		MIS_USER,
		ACCOUNTANT,
		AAO,
		AO,
		DOMAIN_ADMIN,
		APPLICATION_ADMIN
	};
	
	
	@Id
	@Column(name = "role_id")
	private int id;
	
	@Column(name = "role")
	private String role;

	public Role() {
		super();
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
		Role role = (Role)o;
		return (this.id - role.id) == 0?true:false;
	}

}
