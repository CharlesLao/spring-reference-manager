package syuu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syuu.dataObject.*;
import syuu.repository.*;
import syuu.service.VO.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<MomentVo> getMomentByUser(UserVo user,UserVo user1) {
        //System.out.println("调用getMomentByUser函数");
        List<Moment> momentList = momentRepository.findMomentByUser(userRepository.findOne(user.getId()));
        List<MomentVo> momentVoList = new ArrayList<MomentVo>();
        if(momentList!=null){
            boolean addmoment;
                for(Moment moment:momentList) {
                    addmoment=true;//用于屏蔽列表控制朋友圈的添加。

                    int id = moment.getId();
                    String content = moment.getContent();
                    Date time = moment.getTime();


                    List<UserVo> blockList = new ArrayList<UserVo>();
                    if (moment.getBlockList() != null) {
                        for (User blockUser : moment.getBlockList()) {
                            if(blockUser.getId()==user1.getId())
                                addmoment=false;
                            blockList.add(new UserVo(blockUser));
                        }
                    }


                    List<ReferenceVo> referenceVoList = new ArrayList<ReferenceVo>();
                    if (moment.getReference() != null) {
                        for (Reference reference : moment.getReference()) {
                            referenceVoList.add(new ReferenceVo(reference));
                        }
                    }



                    List<LikeVo> likeVoList=new ArrayList<LikeVo>();
                    List<Like> likeList=likeRepository.findLikeByMoment(moment);
                    if(likeList!=null)
                    {
                        //System.out.println("LIKE_MOMENT:"+likeList.size());
                        for(Like like:likeList)
                        {
                           // System.out.println("LIKE_MOMENT:"+like.getMoment().getId());
                            likeVoList.add(new LikeVo(like));
                        }
                    }



                    List<CommentVo> commentVoList = new ArrayList<CommentVo>();
                    List<Comment> commentList = commentRepository.findCommentByMoment(moment);
                    if (commentList != null) {
                        boolean addcomment = false;
                        //只有评论的双方都在好友列表中，该用户才能看见这条评论
                        for (Comment comment : commentList) {
                            addcomment = false  ;
                            //System.out.println("comment: " + comment.getContent() + " commentID: " + comment.getId());

                            if (user1.getFriendList() != null) {
                               // System.out.println( "  FRIEND_FOR     comment: " + comment.getContent() + " commentID: " + comment.getId()+" commenttype: "+comment.getCommenttype());
                              for (UserVo friendVo : user1.getFriendList()) {

                                  //System.out.println("frinendName:"+friendVo.getId());

                                      if (friendVo.getId() == comment.getFromuser().getId() || user1.getId() == comment.getFromuser().getId()) {
                                        for (UserVo friendVo1 : user1.getFriendList()) {
                                            if (friendVo1.getId() == comment.getTouser().getId() || user1.getId() == comment.getTouser().getId()) {
                                                addcomment = true;
                                                continue;
                                            }
                                        }
                                    }
                                }
                            }
                            else
                                System.out.println("好友列表为NULL！");

                            if (addcomment==true) {
                                commentVoList.add(new CommentVo(comment));
                               // System.out.println( "  FRIEND_FOR     comment: " + comment.getContent() + " commentID: " + comment.getId()+" commenttype: "+comment.getCommenttype());

                            }
                        }
                    }


                    List<CommentVo> ydCommentVoList=new ArrayList<CommentVo>();
                    List<YdComment> ydCommentList=ydCommentRepository.findYdCommentByTouser(userRepository.findOne(user1.getId()));
                    System.out.println("USERID:"+user1.getId());
                    System.out.println(ydCommentList.size());

                    UserVo userVo = new UserVo(userRepository.findOne(user.getId()));
                    MomentVo momentVo = new MomentVo(id, content, time, user, blockList, referenceVoList, commentVoList,likeVoList.size(),ydCommentVoList);
                    //System.out.println("LIKE_MOMENT:"+momentVo.getLike_num());
                    if(addmoment==true)
                    {
                        momentVoList.add(momentVo);
                    }

                }



                }


        return momentVoList;
    }

    //获取该用户应该看到的全部朋友圈
    public List<MomentVo> getMoments(UserVo user){
        List<MomentVo> momentVoList = new ArrayList<MomentVo>();
        if(user.getFriendList()!=null){
            for(UserVo friendVo:user.getFriendList()){

                momentVoList.addAll(getMomentByUser(friendVo,user));
            }
        }
        momentVoList.addAll(getMomentByUser(user,user));
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

    public  MomentVo getMoment(int momentid)
    {
       Moment moment= momentRepository.getOne(momentid);
        int id = moment.getId();
        String content = moment.getContent();
        Date time = moment.getTime();


        List<UserVo> blockList = new ArrayList<UserVo>();
        if (moment.getBlockList() != null) {
            for (User blockUser : moment.getBlockList()) {
                blockList.add(new UserVo(blockUser));
            }
        }


        List<ReferenceVo> referenceVoList = new ArrayList<ReferenceVo>();
        if (moment.getReference() != null) {
            for (Reference reference : moment.getReference()) {
                referenceVoList.add(new ReferenceVo(reference));
            }
        }



        List<CommentVo> commentVoList = new ArrayList<CommentVo>();
        List<Comment> commentList = commentRepository.findCommentByMoment(moment);
        if (commentList != null) {


            for (Comment comment : commentList) {
                commentVoList.add(new CommentVo(comment));
            }

        }

        List<LikeVo> likeVoList=new ArrayList<LikeVo>();
        List<Like> likeList=likeService.getLikeByMoment(momentid);
        if(likeList!=null)
        {
            for(Like like:likeList)
            {
                likeVoList.add(new LikeVo(like));
            }
        }

        UserVo userVo = new UserVo(userRepository.findOne(moment.getId()));
        MomentVo momentVo = new MomentVo(id, content, time, userVo, blockList, referenceVoList, commentVoList,likeVoList.size(),new ArrayList<CommentVo>());

        return momentVo;
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
