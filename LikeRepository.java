package syuu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import syuu.dataObject.Like;
import syuu.dataObject.Moment;
import syuu.dataObject.User;

import java.util.List;

/**
 * Created by liujun on 2018/4/12.
 */
@Repository
public interface LikeRepository extends JpaRepository<Like,Integer>{
    List<Like> findLikeByUser(User user);
    Like findLikeByMomentAndUser(Moment moment,User user);
    List<Like> findLikeByMoment(Moment moment);
    Like findLikeByIdAndUser(int id, User user);

}
