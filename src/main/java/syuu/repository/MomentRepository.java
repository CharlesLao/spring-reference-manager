package syuu.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import syuu.dataObject.Moment;
import syuu.dataObject.Reference;
import syuu.dataObject.Research;
import syuu.dataObject.User;
import syuu.service.VO.UserVo;

import java.util.List;

@Repository
public interface MomentRepository  extends JpaRepository<Moment,Integer> {
    List<Moment> findMomentByUser(User user);
}
