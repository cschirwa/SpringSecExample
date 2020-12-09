package za.co.kemtech.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import za.co.kemtech.entity.MyUserDetails;
import za.co.kemtech.entity.User;
import za.co.kemtech.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository UserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = UserRepository.findByUsername(username);
		if(user.isPresent()) {
			return new MyUserDetails(user.get());
		}
		throw new UsernameNotFoundException(String.format("%s not found on database", username));
	}

}
