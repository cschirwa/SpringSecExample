package za.co.kemtech.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private boolean active;
	private String roles;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.active = true;
		this.roles = "ROLE_USER,ROLE_ADMIN";
	}

}
