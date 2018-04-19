package syuu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import syuu.dataObject.Style;
import syuu.service.ReferenceService;
import syuu.service.ResearchService;
import syuu.service.StyleService;
import syuu.service.UserService;
import syuu.service.VO.ReferenceVo;
import syuu.service.VO.ResearchVo;
import syuu.service.VO.UserVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/export")
public class ExportController {

    @Autowired
    ReferenceService referenceService;
    @Autowired
    UserService userService;
    @Autowired
    ResearchService researchService;
    @Autowired
    StyleService styleService;

    @RequestMapping("/exportPage")
    public ModelAndView exportPage(String[] referenceIdList){
        ArrayList<ReferenceVo> referenceVoList = new ArrayList<ReferenceVo>();
        String referenceIdJson="";
        for(int i = 0; i<referenceIdList.length; i++){
            ReferenceVo referenceVo = referenceService.getReferenceById(referenceIdList[i]);
            referenceVoList.add(referenceVo);
            referenceIdJson+=referenceIdList[i];
            if(i!=referenceIdList.length-1){
                referenceIdJson+=",";
            }
        }
        ModelAndView mv = new ModelAndView("export/exportPage");
        List<String> refereneceWithStyle = styleService.getReferenceListWithStyle(referenceVoList,new Style(Style.STANDARD));
        mv.addObject("referenceWithStyle",refereneceWithStyle);
        mv.addObject("referenceIdList",referenceIdJson);
        UserVo userVo = userService.getLoginUser();
        List<ResearchVo> allResearchs = researchService.getReseachByUser(userVo.getId());
        mv.addObject("userVo",userVo);
        mv.addObject("allResearchs",allResearchs);
        List<Style> allStyle= styleService.getAllStyle();
        mv.addObject("allStyle",allStyle);

        //测试时使用
        return mv;
    }

    @RequestMapping("/output")
    @ResponseBody
        public Map<String,Object> output(String[] referenceIdList, String style){
        String result = "";
        Map<String,Object> resultMap = new HashMap<String, Object>();
        for(int i=0;i<referenceIdList.length;i++){
            int j = i+1;
            result+="["+j+"]"+styleService.styleTheReference(Integer.valueOf(referenceIdList[i]),Integer.valueOf(style));
            result+="\n";
        }
        resultMap.put("result",result);
        return resultMap;
    }

    @RequestMapping("/saveStyle")
    @ResponseBody
    public Map<String,Object> saveStyle(String expression,String name,String andLastAuthor,String etalAuthor,String upperFirstname,String upperMiddlename){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        styleService.saveStyle(expression,name,andLastAuthor,etalAuthor,upperFirstname,upperMiddlename);
        return resultMap;
    }

    @RequestMapping("/isNameExist")
    @ResponseBody
    public Map<String,Object> isNameExist(String name){
        String result = styleService.isNameExist(name);
        Map<String,Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result",result);
        return resultMap;
    }
}