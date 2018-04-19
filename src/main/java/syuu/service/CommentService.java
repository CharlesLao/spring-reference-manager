package syuu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syuu.dataObject.Comment;
import syuu.dataObject.User;
import syuu.dataObject.YdComment;
import syuu.repository.CommentRepository;
import syuu.repository.MomentRepository;
import syuu.repository.UserRepository;
import syuu.repository.YdCommentRepository;
import syuu.service.VO.CommentVo;
import syuu.service.VO.YdCommentVo;

import java.util.Date;

/**
 * Created by liujun on 2018/4/14.
 */
@Service
public class CommentService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    MomentRepository momentRepository;
    @Autowired
    YdCommentRepository ydCommentRepository;


    public void saveComment(CommentVo commentVo)
    {
        Comment comment=new Comment();
        comment.setTime(new Date());
        comment.setFromuser(userRepository.getOne(commentVo.getFromUser().getId()));
        comment.setTouser(userRepository.getOne(commentVo.getToUser().getId()));
        comment.setContent(commentVo.getContent());
        comment.setMoment(momentRepository.getOne(commentVo.getMomentid()));
        comment.setCommenttype(commentVo.getCommenttype());
        commentRepository.save(comment);

    }


    public  void saveydComment(CommentVo commentVo)
    {
        YdComment ydComment=new YdComment();
        ydComment.setComment(commentVo.getId());
        ydComment.setTouser(userRepository.getOne(commentVo.getToUser().getId()));
        ydCommentRepository.save(ydComment);
    }


    public void deleteComment(int commentid)
    {
        commentRepository.delete(commentRepository.findOne(commentid));
    }
}
