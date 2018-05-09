package syuu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import syuu.dataObject.Research;
import syuu.dataObject.User;

import java.util.List;

@Repository
public interface ResearchRepository extends JpaRepository<Research,Integer> {
    List<Research> getResearchByUser(User user);
}
