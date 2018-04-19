package syuu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import syuu.dataObject.Comment;
import syuu.dataObject.Moment;
import syuu.dataObject.User;

import java.util.List;

/**
 * Created by liujun on 2018/4/14.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer>{
    List<Comment> findCommentByMoment(Moment moment);
    Comment findCommentByIdAndTouser(int commentid, int userid);
}
