package searchman.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import searchman.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	//	User findByUsername(String username);
	Optional<User> findByUsername(String username);

}
