package syuu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import syuu.dataObject.Massage;
import syuu.dataObject.Research;
import syuu.service.FriendService;
import syuu.service.MassageService;
import syuu.service.ResearchService;
import syuu.service.UserService;
import syuu.service.VO.*;
import syuu.util.SelectorUtil;

import java.util.*;


@Controller
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    UserService userService;
    @Autowired
    ResearchService researchService;
    @Autowired
    FriendService friendService;
    @Autowired
    MassageService massageService;

    @RequestMapping("/manager")
    public ModelAndView manager(String tabId){
        ModelAndView mv = new ModelAndView("friend/friendManager");
        UserVo userVo = userService.getLoginUser();
        mv.addObject("tabId",tabId);
        mv.addObject("userVo",userVo);
        List<InvitationVo> invitationVoList = friendService.getInvitationByUser(userVo);
        mv.addObject("invitationVoList",invitationVoList);
        List<UserVo> friendVoList= friendService.getFriendByUser(userVo);
        mv.addObject("friendVoList",friendVoList);
        List<MassageVo> massageVoList = massageService.getMassagesByUser(userVo);
        mv.addObject("massageVoList", massageVoList);
        return mv;
    }
    //接受朋友申请
    @RequestMapping("/acceptInvitation")
    public ModelAndView acceptInvitation(String invitationId){
        friendService.acceptInvitation(invitationId);

        ModelAndView mv = new ModelAndView("redirect:/friend/manager?tabId=1");
        return mv;
    }
    //删除朋友申请
    @RequestMapping("/deleteInvitation")
    public ModelAndView deleteInvitation(String invitationId){
        friendService.deleteInvitation(invitationId);
        ModelAndView mv = new ModelAndView("redirect:/friend/manager?tabId=1");
        return mv;
    }
    //批量删除朋友申请
    @RequestMapping("/deleteAllInvitation")
    public ModelAndView deleteAllInvitation(String[] invitationList){
        for(int i=0;i<invitationList.length;i++){
            friendService.deleteInvitation(invitationList[i]);
        }
        ModelAndView mv = new ModelAndView("redirect:/friend/manager?tabId=1");
        return mv;
    }
    //按下搜索朋友后返回搜索结果
    @RequestMapping("/friendSearchRequest")
    @ResponseBody
    public Map<String,Object> friendSearchRequest(String username){
        List<UserVo> userVoList = new ArrayList<UserVo>();
        UserVo user = userService.getLoginUser();
        if(!username.equals("")){
            userVoList = userService.searchByUsername(username);
            List<UserVo> friendVoList = friendService.getFriendByUser(user);
            for(UserVo friend:friendVoList){
                for(UserVo userVo:userVoList){
                    if(friend.getId()==userVo.getId()){
                        userVoList.remove(userVo);
                    }
                }
            }
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userVoList",userVoList);
        return map;
    }
    //保存申请记录
    @RequestMapping("/saveInvitation")
    public ModelAndView saveInvitation(String toUser){
        UserVo userVo = userService.getLoginUser();
        friendService.saveInvitation(userVo,toUser);
        ModelAndView mv = new ModelAndView("redirect:/friend/manager?tabId=3");
        return mv;
    }
    //删除朋友
    @RequestMapping("/deleteFriend")
    public ModelAndView deleteFriend(String friendId){
        friendService.deleteFriend(friendId);
        ModelAndView mv = new ModelAndView("redirect:/friend/manager?tabId=2");
        return mv;
    }
    //批量删除朋友
    @RequestMapping("/deleteAllFriend")
    public ModelAndView deleteAllFriend(String[] friendList){
        for(int i=0;i<friendList.length;i++){
            friendService.deleteFriend(friendList[i]);
        }
        ModelAndView mv = new ModelAndView("redirect:/friend/manager?tabId=2");
        return mv;
    }
    //获取选择的朋友vo
    @RequestMapping("/getAllFriendList")
    @ResponseBody
    public Map<String,Object> getAllFriendList(String[] selectedIdList){
        UserVo user = userService.getLoginUser();
        List<UserVo> friendList = friendService.getFriendByUser(user);
        List<JsonNode> firstNode = SelectorUtil.convertFriendList(friendList,selectedIdList);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("firstNode",firstNode);
        return map;
    }

    @RequestMapping("/getSelectFriendList")
    @ResponseBody
    public Map<String,Object> getSelectFriendList(String[] friendIdList){
        List<UserVo> selectFriendList = new ArrayList<UserVo>();
        if(friendIdList!=null){
            for(int i=0;i<friendIdList.length;i++){
                selectFriendList.add(userService.getUserById(friendIdList[i]));
            }
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("selectFriendList",selectFriendList);
        return map;
    }
}
