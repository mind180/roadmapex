package com.roadmapex.roadmapex.services.users;

import com.roadmapex.roadmapex.model.User;
import com.roadmapex.roadmapex.repository.canvas.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailServiceImpl implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  public UserDetailServiceImpl() {
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.getUserByUsername(username);

    if (user.isEmpty()) {
      throw new UsernameNotFoundException("Could not find user");
    }

    return new UserDetailsImpl(user.get());
  }
}
