package syuu.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import syuu.dataObject.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Transactional
    @Query(value="select * from User u where u.username like CONCAT('%',:username,'%')",nativeQuery=true)
    List<User> findByUsername(@Param("username") String username);
}
