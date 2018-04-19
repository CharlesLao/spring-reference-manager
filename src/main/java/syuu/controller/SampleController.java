package syuu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Song on 2017/2/15.
 * 官方示例工程中的测试代码
 */
@Controller
@RequestMapping("/sample")
public class SampleController {


    /**
     *
     */

    private String path = "sample/";

    @RequestMapping("/home")
    ModelAndView home() {
        ModelAndView mv = new ModelAndView(path+"home");
        String testContent = "homepage content";
        mv.addObject("testContent",testContent);
        return mv;
    }


}
