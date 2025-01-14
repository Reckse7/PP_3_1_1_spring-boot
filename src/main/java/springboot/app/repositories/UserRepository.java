package springboot.app.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
