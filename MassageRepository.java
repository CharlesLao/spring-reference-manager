package syuu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syuu.dataObject.Massage;
import syuu.dataObject.User;

import java.util.List;

public interface MassageRepository extends JpaRepository<Massage,Integer> {
    List<Massage> findByUser(User user);
}
