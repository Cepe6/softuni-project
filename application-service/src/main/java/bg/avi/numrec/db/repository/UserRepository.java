package bg.avi.numrec.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bg.avi.numrec.db.entity.security.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAll();
	
	@Query("SELECT case when count(u) > 0 then true else false end from User u WHERE u.username = ?1 and u.password = ?2")
	boolean checkIfUserExists(String username, String password);
}
