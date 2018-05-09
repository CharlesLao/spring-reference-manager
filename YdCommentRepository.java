package syuu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import syuu.dataObject.User;
import syuu.dataObject.YdComment;

import java.util.List;

/**
 * Created by liujun on 2018/4/18.
 */
@Repository
public interface YdCommentRepository extends JpaRepository<YdComment,Integer> {
    List<YdComment> findYdCommentByTouser(User touserid);
}
