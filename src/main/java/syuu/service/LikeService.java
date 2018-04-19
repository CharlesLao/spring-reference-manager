package syuu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syuu.dataObject.Friend;
import syuu.dataObject.Like;
import syuu.dataObject.Moment;
import syuu.dataObject.User;
import syuu.repository.FriendRepository;
import syuu.repository.LikeRepository;
import syuu.repository.MomentRepository;
import syuu.repository.UserRepository;
import syuu.service.VO.LikeVo;
import syuu.service.VO.MomentVo;
import syuu.service.VO.UserVo;

import java.util.List;

/**
 * Created by liujun on 2018/4/12.
 */
@Service
public class LikeService {
    @Autowired
    LikeRepository likeRepository;

    @Autowired
    MomentRepository momentRepository;
    @Autowired
    UserService userService;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    UserRepository userRepository;

    public List<Like> getLikeByMoment(int momentid)
    {
        UserVo userVo=userService.getLoginUser();
        List<Like> likeList=likeRepository.findLikeByUser(userRepository.getOne(userVo.getId()));
        if(likeList!=null)
        {
            for(Like like:likeList)
            {
                System.out.println(" userid: "+like.getUser().getId());
            }
        }

        return likeList;

    }

    public Like getLikeByMomentAndUser(int userid,int id)
    {
        UserVo userVo=userService.getLoginUser();
        Like like=likeRepository.findLikeByIdAndUser(id,userRepository.findOne(userid));
        return like;
    }

    public int  setLike(LikeVo likevo)
    {
        Like like= likeRepository.findLikeByMomentAndUser(momentRepository.findOne(likevo.getMomentid()),userRepository.findOne(likevo.getUserVo().getId()));
        if(like==null)
        {
            Like li=new Like();
            li.setId(likevo.getId());
            li.setMoment(momentRepository.findOne(likevo.getMomentid()));
            li.setUser(userRepository.findOne(likevo.getUserVo().getId()));
            likeRepository.save(li);
            return 1;
        }
        return 0;//控制重复点赞。
    }

}
