package syuu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import syuu.dataObject.Like;
import syuu.repository.FriendRepository;
import syuu.service.*;
import syuu.service.VO.*;

import java.util.*;

@Controller
@RequestMapping("/moment")
public class MomentController {
    @Autowired
    UserService userService;
    @Autowired
    MomentService momentService;
    @Autowired
    ReferenceService referenceService;
    @Autowired
    LikeService likeService;
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    CommentService commentService;

    @RequestMapping("/manager")

    public ModelAndView manager(String tabId){
        ModelAndView mv = new ModelAndView("/moment/momentManager");
        List<MomentVo> momentVoList = momentService.getMoments(userService.getLoginUser());
        mv.addObject("tabId",tabId);
        mv.addObject("momentVoList",momentVoList);
        return mv;
    }

    @RequestMapping("/getBlockUserList")
    @ResponseBody
    public Map<String,Object> getBlockUserList(String[] blockIdList){
        List<UserVo> blockUserList = new ArrayList<UserVo>();
        if(blockIdList!=null){
            for(int i=0;i<blockIdList.length;i++){
                blockUserList.add(userService.getUserById(blockIdList[i]));
            }
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("blockUserList",blockUserList);
        return map;
    }

    @RequestMapping("/saveMoment")
    @ResponseBody
    public Map<String,Object> saveMoment(String content,String[] blockIdList,String[] referenceIdList){

        int liker_num=0;

        List<CommentVo> ydCommentVoList=new ArrayList<CommentVo>();

        UserVo user = userService.getLoginUser();
        List<UserVo> blockList = new ArrayList<UserVo>();
        List<ReferenceVo> referenceVoList = new ArrayList<ReferenceVo>();
        if(blockIdList!=null){
            for(int i=0;i<blockIdList.length;i++){
                String userId = blockIdList[i].replaceAll("friend","");
                blockList.add(userService.getUserById(userId));
            }
        }
        if(referenceIdList!=null){
            for(int i=0;i<referenceIdList.length;i++){
                String userId = referenceIdList[i].replaceAll("reference","");
                referenceVoList.add(referenceService.getReferenceById(userId));
            }
        }
        MomentVo momentVo = new MomentVo(0,content,new Date(),user,blockList,referenceVoList,new ArrayList<CommentVo>(),liker_num,ydCommentVoList,user);
        momentService.savenNewMoment(momentVo);
        Map<String,Object> map = new HashMap<String, Object>();
        return map;
    }

    //点赞：点赞的同时根据好友列表显示此条朋友圈中用户自己以及他的好友的赞。
    @RequestMapping("like")
    @ResponseBody
    public int like(int momentid,int userid)
    {

        UserVo user=userService.getLoginUser();
        LikeVo likeVo=new LikeVo(0,user,momentid);

        int i=likeService.setLike(likeVo);


        Map<String,Object> map = new HashMap<String, Object>();
        return i;
    }

    @RequestMapping("saveComment")
    @ResponseBody
    public Map<String,Object> saveComment(int momentid,int userid,String content,int commenttype)
    {
        UserVo user=userService.getLoginUser();
        String cont=content;
        MomentVo momentVo;
        int momid=momentid;
        UserVo fromuser=userService.getLoginUser();//登录用户的id
        UserVo touser=userService.getUserById(userid);//被评论的userID
        CommentVo commentVo=new CommentVo(0,fromuser,touser,content,new Date(),momentid,commenttype);
        //YdCommentVo ydCommentVo=new YdCommentVo(0,touser,;


        commentService.saveydComment(commentVo);
        commentService.saveComment(commentVo);
        String username=user.getUsername();
        System.out.println("momentid: "+momentid+" content:"+content+" username: "+username+" userid:"  +userid+" commenttype: "+commenttype);
        Map<String,Object> map=new HashMap<String,Object>();
        return map;
    }

    @RequestMapping("replyComment")
    @ResponseBody
    public Map<String,Object> replyComment(int momentid,int fromuserid,int touserid,String content,int commenttype) {
        System.out.println("momentid: "+momentid+" content:"+content+" fromuserid:"  +fromuserid+" touserid: "+touserid+" commenttype: "+commenttype);
        UserVo fromuser = userService.getUserById(fromuserid);
        UserVo touser = userService.getUserById(touserid);
        CommentVo commentVo = new CommentVo(0, fromuser, touser, content, new Date(), momentid,commenttype);
        commentService.saveComment(commentVo);
        Map<String,Object> map=new HashMap<String,Object>();
        return map;
    }
    @RequestMapping("deleteComment")
    @ResponseBody
    public void deleteComment(int commentid)
    {
        System.out.println("delete commentid:"+commentid);
        commentService.deleteComment(commentid);
    }
}
