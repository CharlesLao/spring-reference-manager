package syuu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import syuu.configuration.WebSecurityConfig;
import syuu.dataObject.Massage;
import syuu.service.*;
import syuu.service.VO.ResearchVo;
import syuu.service.VO.UserVo;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {


    /**
     *
     */


    @Autowired
    UserService userService;

    @Autowired
    ResearchService researchService;

    @Autowired
    FriendService friendService;

    @Autowired
    MassageService massageService;

    @Autowired
    MomentService momentService;

    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
        model.addAttribute("name", account);
        return "index";
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("user/login");
        return mv;
    }

    @RequestMapping("/loginPost")
    @ResponseBody
    public  ModelAndView loginPost(String username, String password, HttpSession session) {
        ModelAndView errorMv = new ModelAndView("user/login");
        String result = userService.verifyLogin(username,password);
        if(result.equals("用户不存在")||result.equals("密码错误")){
            errorMv.addObject("success", false);
            errorMv.addObject("message", result);
            return errorMv;
        }
        ModelAndView homeMv = new ModelAndView("redirect:/manager/allReference");
        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, result);

        return homeMv;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        // 移除session
        ModelAndView mv = new ModelAndView("redirect:/user/login");
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return mv;
    }

    @RequestMapping("/userCenter")
    public ModelAndView userCenter() {
        UserVo userVo = userService.getLoginUser();
        ModelAndView mv = new ModelAndView("user/userCenter");

        mv.addObject("userVo",userVo);
        return mv;
    }

    //修改密码
    @RequestMapping("/changePassword")
    @ResponseBody
    public int changePassword(String password,String newpassword,String newpasswordconfirm)
    {
        int result=userService.changePassword(password,newpassword,newpasswordconfirm);
        return result;
       // System.out.println("password:"+password+" newpassword:"+newpassword+" newpasswordconfirm:"+newpasswordconfirm+" result:"+result);
    }

    //返回侧导航栏内容
    @RequestMapping("/sideMenuContent")
    @ResponseBody
    public Map<String,Object> sideMenuContent(){
        Map<String,Object> map = new HashMap<String,Object>();
        UserVo user = userService.getLoginUser();
        List<ResearchVo> researchVoList = researchService.getReseachByUser(user.getId());
        List<UserVo> friendList = friendService.getFriendByUser(user);
        int msgNumber = massageService.getNumberOfMassage();
        int ydCommentNum=momentService.getydCommentNum(user);
        map.put("researchVoList",researchVoList);
        map.put("friendList",friendList);
        map.put("username",user.getUsername());
        map.put("msgNumber",msgNumber);
        map.put("ydCommentNum",ydCommentNum);
        return map;
    }
}
