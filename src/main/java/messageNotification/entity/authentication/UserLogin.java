package messageNotification.entity.authentication;

//named Queries use the class name and class variable names instead of table name and table attribute names

//we have to use the Hibernate cascade for Session.save

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_login")
@NamedQueries({
	@NamedQuery(
			name = "UserLogin.findByEmail",
			query = "FROM UserLogin ul WHERE ul.email =:email"
			),
	@NamedQuery(
			name = "UserLogin.findAll",
			query = "FROM UserLogin"
			)
})
public class UserLogin 
{	
	@Id
	@Column(name = "email")
	private String email;
	
	@JsonIgnore
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@OneToOne(mappedBy ="userLogin")
	@Cascade({CascadeType.ALL})
	@JsonBackReference
	private User user;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade({CascadeType.SAVE_UPDATE})
	@JoinTable(
			name = "user_role", 
			joinColumns = {@JoinColumn(name = "email")}, 
			inverseJoinColumns = {@JoinColumn(name = "user_role_id")}
			)
	@JsonManagedReference
	private Set<Role> roles;
	
	public UserLogin(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public UserLogin() 
	{
		super();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public boolean addRole(Role role) {
		return this.roles.add(role);
	}
	
	public boolean removeRole(Role role) {
		return this.roles.remove(role);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
