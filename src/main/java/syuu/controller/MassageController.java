package syuu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import syuu.service.*;
import syuu.service.VO.*;
import syuu.util.SelectorUtil;

import java.util.*;

@Controller
@RequestMapping("/massage")
public class MassageController {

    @Autowired
    UserService userService;
    @Autowired
    MassageService massageService;
    @Autowired
    ReferenceService referenceService;
    @Autowired
    FriendService friendService;
    @Autowired
    ResearchService researchService;

    //创建新消息
    @RequestMapping("/addMassage")
    public ModelAndView addMassage(String friendId){
        List<String> friendIdList = new ArrayList<String>();
        friendIdList.add(friendId);
        ModelAndView mv = new ModelAndView("massage/addMassage");
        String isReply = "false";
        mv.addObject("friendIdList",friendIdList);
        return mv;
    }

    @RequestMapping("/reply")
    public ModelAndView reply(String massageId){
        MassageVo massageVo = massageService.getMassagesById(massageId);
        UserVo friendVo = massageVo.getFromUser();
        String isReply = "true";
        ModelAndView mv = new ModelAndView("massage/addMassage");
        mv.addObject("friendVo",friendVo);
        mv.addObject("isReply",isReply);
        mv.addObject("subject",massageVo.getSubject());
        return mv;
    }
    @RequestMapping("/saveMassage")
    public ModelAndView saveMassage(String subject,String[] toUserId,String[] referenceId,String content){
        UserVo user = userService.getLoginUser();
        List<UserVo> toUserList = new ArrayList<UserVo>();
        List<ReferenceVo> referenceVoList = new ArrayList<ReferenceVo>();
        for(int i=0;i<toUserId.length;i++){
            String userId = toUserId[i].replaceAll("friend","");
            toUserList.add(userService.getUserById(userId));
        }
        for(int i=0;i<referenceId.length;i++){
            String userId = referenceId[i].replaceAll("reference","");
            referenceVoList.add(referenceService.getReferenceById(userId));
        }
        for(UserVo toUser:toUserList){
            MassageVo massageVo = new MassageVo(subject,toUser,referenceVoList,user,content,"未读",new Date());
            massageService.saveMassage(massageVo);
        }
        ModelAndView mv = new ModelAndView("redirect:/friend/manager?tabId=4");
        return mv;
    }

    //删除消息
    @RequestMapping("/deleteMassage")
    public ModelAndView deleteMassage(String massageId){
        massageService.deleteMassage(massageId);
        ModelAndView mv = new ModelAndView("redirect:/friend/manager?tabId=4");
        return mv;
    }
    //批量删除消息
    @RequestMapping("/deleteAllMassage")
    public ModelAndView deleteAllMassage(String[] massageList){
        for(int i=0;i<massageList.length;i++){
            massageService.deleteMassage(massageList[i]);
        }
        ModelAndView mv = new ModelAndView("redirect:/friend/manager?tabId=4");
        return mv;
    }
    //查看消息详情
    @RequestMapping("/massageDetail")
    public ModelAndView massageDetail(String massageId){
        MassageVo massageVo = massageService.getMassagesById(massageId);
        massageVo.setYd("已读");
        massageService.setYd(massageVo);
        ModelAndView mv = null;
        if(massageVo.getFromUser().getId()==userService.getLoginUser().getId()){
            mv = new ModelAndView("massage/massageDetail_NE");
        }else{
            mv = new ModelAndView("massage/massageDetail");
        }
        mv.addObject("massageVo",massageVo);
        return mv;
    }

    //研究页面点击分享后到创建消息页面
    @RequestMapping("/createShareMassage")
    public ModelAndView createShareMassage(String[] referenceIdList){
        List<String> referenceList = new ArrayList<String>();
        for(int i=0;i<referenceIdList.length;i++){
            referenceList.add(referenceIdList[i]);
        }
        ModelAndView mv = new ModelAndView("massage/addMassage");
        mv.addObject("referenceIdList",referenceList);
        return mv;
    }
}
