package es.iessoterohernandez.BBaker.repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.iessoterohernandez.BBaker.model.User;
import es.iessoterohernandez.BBaker.model.UserRole;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional <User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUser(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User a " +
            "SET a.user_role = ?1 WHERE a.email = ?2", nativeQuery = true)
    int changeRole(UserRole u, String email);

    User findById(Long user_id);
}