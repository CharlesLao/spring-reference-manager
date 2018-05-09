package syuu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syuu.dataObject.Share;

import java.util.List;

public interface ShareRepository extends JpaRepository<Share,Integer> {
    List<Share> findByMassageId(int massageId);
}
