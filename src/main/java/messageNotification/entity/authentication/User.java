package messageNotification.entity.authentication;

import java.util.Date;

import org.hibernate.annotations.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "user")
@NamedQueries({
	@NamedQuery(
			name = "User.findByEmail",
			query = "FROM User u WHERE u.userLogin.email =:email"
			),
	@NamedQuery(
			name = "User.findById",
			query = "FROM User u WHERE u.id =:id"
			),
	@NamedQuery(
			name = "User.findAll",
			query = "FROM User"
			)
})
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Temporal(value = TemporalType.DATE)
	@Column(name = "dob")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dob;
	
	@OneToOne
	@Cascade({CascadeType.ALL})
	@JoinColumn(name = "email",referencedColumnName ="email")
	@JsonManagedReference
	private UserLogin userLogin;
	
	//and Address class and table must be created
		//private Address address;

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String firstName, String lastName, Date dob, UserLogin userLogin) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.userLogin = userLogin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
	}
}
