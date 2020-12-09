package za.co.kemtech.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import za.co.kemtech.entity.User;
import za.co.kemtech.repository.UserRepository;

@Component
public class Bootstrap {
	
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	@Autowired
	public Bootstrap(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		loadUser();
	}

	private void loadUser() {
		User user = new User("calvin", passwordEncoder.encode("123123"));
		userRepository.save(user);
	}
}
