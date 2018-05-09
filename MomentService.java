package syuu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syuu.dataObject.*;
import syuu.repository.*;
import syuu.service.VO.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.*;

@Service
public class MomentService {

    @Autowired
    MomentRepository momentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ReferenceRepository referenceRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    LikeService likeService;
    @Autowired
    YdCommentRepository ydCommentRepository;
    @Autowired
    UserService userService;

    public List<Moment> getMoment(UserVo user)
    {
        List<Moment> momentList = momentRepository.findMomentByUser(userRepository.findOne(user.getId()));
        if(momentList!=null)
        return  momentList;
        else
            return null;

    }

    public List<MomentVo> getMomentByUser(List<Moment> momentList,UserVo user,UserVo user1) {
        //System.out.println("调用getMomentByUser函数");
        //List<Moment> momentList = momentRepository.findMomentByUser(userRepository.findOne(user.getId()));
        List<MomentVo> momentVoList = new ArrayList<MomentVo>();



        if(momentList!=null) {
            boolean addmoment;
            for (Moment moment : momentList) {
                addmoment = true;//用于屏蔽列表控制朋友圈的添加。

                int id = moment.getId();
                String content = moment.getContent();
                Date time = moment.getTime();


                List<UserVo> blockList = new ArrayList<UserVo>();
                if (moment.getBlockList() != null) {
                    for (User blockUser : moment.getBlockList()) {
                        if (blockUser.getId() == user1.getId())
                            addmoment = false;
                        blockList.add(new UserVo(blockUser));
                    }
                }


                List<ReferenceVo> referenceVoList = new ArrayList<ReferenceVo>();
                if (moment.getReference() != null) {
                    for (Reference reference : moment.getReference()) {
                        referenceVoList.add(new ReferenceVo(reference));
                    }
                }


                List<LikeVo> likeVoList = new ArrayList<LikeVo>();
                List<Like> likeList = likeRepository.findLikeByMoment(moment);
                if (likeList != null) {
                    for (Like like : likeList) {
                        likeVoList.add(new LikeVo(like));
                    }
                }


                List<CommentVo> commentVoList = new ArrayList<CommentVo>();
                List<Comment> commentList = commentRepository.findCommentByMoment(moment);
                if (commentList != null) {
                    boolean addcomment = false;
                    //只有评论的双方都在好友列表中，该用户才能看见这条评论
                    for (Comment comment : commentList) {
                        addcomment = false;

                        if (user1.getFriendList() != null) {
                            for (UserVo friendVo : user1.getFriendList()) {

                                if (friendVo.getId() == comment.getFromuser().getId() || user1.getId() == comment.getFromuser().getId()) {
                                    for (UserVo friendVo1 : user1.getFriendList()) {
                                        if (friendVo1.getId() == comment.getTouser().getId() || user1.getId() == comment.getTouser().getId()) {
                                            addcomment = true;
                                            continue;
                                        }
                                    }
                                }
                            }
                        } else
                            System.out.println("好友列表为NULL！");

                        if (addcomment == true) {
                            commentVoList.add(new CommentVo(comment));

                        }
                    }
                }


                List<CommentVo> ydCommentVoList = new ArrayList<CommentVo>();
                List<YdComment> ydCommentList = ydCommentRepository.findYdCommentByTouser(userRepository.findOne(user1.getId()));

                UserVo userVo = new UserVo(userRepository.findOne(moment.getUser().getId()));
                MomentVo momentVo = new MomentVo(id, content, time, userVo, blockList, referenceVoList, commentVoList, likeVoList.size(), ydCommentVoList,user);
                if (addmoment == true) {
                    momentVoList.add(momentVo);
                }
            }


        }
        return momentVoList;
    }


    //获取该用户应该看到的全部朋友圈
    public List<MomentVo> getMoments(UserVo user){
        List<MomentVo> momentVoList = new ArrayList<MomentVo>();
        List<Moment> momentList=new ArrayList<Moment>();

        if(user.getFriendList()!=null){
            for(UserVo friendVo:user.getFriendList()){
                if(getMoment(friendVo)!=null)
                momentList.addAll(getMoment(friendVo));
            }
        }
        momentList.addAll(getMoment(user));

        sortClass sort=new sortClass();
        Collections.sort(momentList,sort);
        System.out.println("LIST NUMBER:"+momentList.size());


        for(int i=0;i<momentList.size();i++){
            Moment moment=momentList.get(i);
            System.out.println("姓名:"+moment.getUser().getUsername()+",生日:"+moment.getTime());
        }



        momentVoList.addAll(getMomentByUser(momentList,user,user));


        return momentVoList;
    }

    public void savenNewMoment(MomentVo momentVo) {
        Moment moment = new Moment();
        moment.setId(momentVo.getId());
        moment.setContent(momentVo.getContent());
        moment.setTime(new Date());
        List<User> blockList = new ArrayList<User>();
        List<Reference> referenceList = new ArrayList<Reference>();
        for(UserVo userVo:momentVo.getBlockList()){
            blockList.add(userRepository.findOne(userVo.getId()));
        }
        for(ReferenceVo referenceVo:momentVo.getReferenceVoList()){
            referenceList.add(referenceRepository.findOne(referenceVo.getId()));
        }
        moment.setBlockList(blockList);
        moment.setReference(referenceList);
        moment.setUser(userRepository.findOne(momentVo.getUser().getId()));
        momentRepository.save(moment);
    }


    public int getydCommentNum(UserVo user1)
    {
        List<YdComment> ydCommentList=ydCommentRepository.findYdCommentByTouser(userRepository.findOne(user1.getId()));
        int size=ydCommentList.size();
        for(YdComment ydComment:ydCommentList)
        {
            ydCommentRepository.delete(ydComment.getId());
        }

        return size;
    }
}
class sortClass implements Comparator
{
    public int compare(Object o1,Object o2)
    {
        Moment m1=(Moment)o1;
        Moment m2=(Moment)o2;
        if(m1.getTime().before(m2.getTime()))
            return 1;
        else
            return -1;
    }
}
