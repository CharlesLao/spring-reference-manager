package syuu.controller;


import com.sun.xml.internal.ws.api.message.Attachment;
import org.hibernate.proxy.map.MapProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import syuu.configuration.Settings;
import syuu.dataObject.Moment;
import syuu.dataObject.Reference;
import syuu.dataObject.Research;
import syuu.service.*;
import syuu.service.VO.*;
import syuu.util.IOUtil;
import syuu.util.SelectorUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    ResearchService researchService;
    @Autowired
    UserService userService;
    @Autowired
    ReferenceService referenceService;
    @Autowired
    private Settings settings;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    MomentService momentService;

    //研究选择的页面
    @RequestMapping("/home")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("redirect:manager/allReference");
        UserVo uservo = userService.getLoginUser();
        mv.addObject("userVo",uservo);
        return mv;
    }
    //根据研究id取出需要的研究
    @RequestMapping("/researchDetail")
    public ModelAndView researchDetail(String id){
        ModelAndView mv = new ModelAndView("manager/researchDetail");
        UserVo userVo = userService.getLoginUser();
        mv.addObject("userVo",userVo);
        if(id!=null){
            ResearchVo researchVo = researchService.getReseachById(Integer.valueOf(id));
            mv.addObject("researchVo",researchVo);

            List<ReferenceVo> referenceVoList = referenceService.getReferenceOfResearch(researchVo.getId());
            mv.addObject("referenceVoList", referenceVoList);
        }
        List<ResearchVo> allResearchs = researchService.getReseachByUser(userVo.getId());
        mv.addObject("allResearchs",allResearchs);

        return mv;
    }

    //在制定研究中添加新的条目
    @RequestMapping("/addReference")
    public ModelAndView addResearch(String researchId){
        ModelAndView mv = new ModelAndView("manager/addReference");
        UserVo userVo = userService.getLoginUser();
        mv.addObject("userVo",userVo);
        ResearchVo researchVo = researchService.getReseachById(Integer.valueOf(researchId));
        mv.addObject("researchVo",researchVo);
        List<ResearchVo> allResearchs = researchService.getReseachByUser(userVo.getId());
        mv.addObject("allResearchs",allResearchs);


        return mv;
    }

    @GetMapping("/getResearch")
    public ModelAndView getResearch(){
        UserVo user = userService.getLoginUser();
        List<ResearchVo> result = researchService.getReseachByUser(user.getId());
        ModelAndView mv = new ModelAndView();
        mv.addObject("researchVoList",result);
        return mv;
    }



    //添加新的文献条目
    @RequestMapping("/saveReference")
    @ResponseBody
    public ModelAndView saveReference(String id,String name,String authors,String year,String conference,String hydd, String qh, String jh,String beginPage,String endPage,String researchId,String lx){
        ReferenceVo referenceVo= new ReferenceVo(name,authors,year,conference,hydd,qh,jh,beginPage,endPage,researchId,id,lx);
        referenceService.saveReference(referenceVo);
        ModelAndView mv = new ModelAndView("redirect:/manager/researchDetail?id="+researchId);
        return mv;
    }

    //新增新的研究
    @RequestMapping("/addResearch")
    public ModelAndView addReference(String researchName,String[] referenceIdList){
        String researchId = researchService.saveNewResearch(researchName,referenceIdList,userService.getLoginUser());
        ModelAndView mv = new ModelAndView("redirect:/manager/researchDetail?id="+researchId);
        return mv;
    }

    //修改文献条目
    @RequestMapping("/updateReference")
    public ModelAndView updateReference(String referenceId){
        ReferenceVo referenceVo = referenceService.getReferenceById(referenceId);
        ResearchVo researchVo = researchService.getReseachById(referenceVo.getResearchId());
        UserVo userVo = userService.getLoginUser();
        List<ResearchVo> allResearchs = researchService.getReseachByUser(userVo.getId());
        ModelAndView mv = new ModelAndView("manager/updateReference");
        mv.addObject("referenceVo",referenceVo);
        mv.addObject("researchVo",researchVo);
        mv.addObject("userVo",userVo);
        mv.addObject("allResearchs",allResearchs);
        return mv;

    }


    //删除文献条目
    @RequestMapping("/deleteReference")
    public ModelAndView deleteReference(String referenceId){
        String researchId =  String.valueOf(referenceService.getReferenceById(referenceId).getResearchId());

        referenceService.removeReferenceById(referenceId);
        ModelAndView mv = new ModelAndView("redirect:/manager/researchDetail?id="+researchId);

        return mv;
    }

    //批量删除文献条目
    @RequestMapping("/deleteAllReference")
    public ModelAndView deleteAllReference(String[] referenceList){
        String researchId =  String.valueOf(referenceService.getReferenceById(referenceList[0]).getResearchId());

        for(String referenceId:referenceList){
            referenceService.removeReferenceById(referenceId);
        }
        ModelAndView mv = new ModelAndView("redirect:/manager/researchDetail?id="+researchId);

        return mv;
    }

    //删除研究
    @RequestMapping("/deleteResearch")
    public ModelAndView deleteResearch(String researchId){
        researchService.deleteResearch(researchId);
        ModelAndView mv = new ModelAndView("redirect:/manager/allReference");

        return mv;
    }

    //查看文献条目
    @RequestMapping("/referenceDetail")
    public ModelAndView referenceDetail(String referenceId){
        UserVo user = userService.getLoginUser();
        ReferenceVo referenceVo  = referenceService.getReferenceById(referenceId);
        ResearchVo researchVo = researchService.getReseachById(referenceVo.getResearchId());
        ModelAndView  mv = null;
        if(researchVo.getUser().getId()==user.getId()){
            mv = new ModelAndView("manager/referenceDetail");
        }else{
            mv = new ModelAndView("manager/referenceDetail_NE");
        }
        mv.addObject("referenceVo", referenceVo);
        mv.addObject("researchVo",researchService.getReseachById(referenceVo.getResearchId()));
        List<AttachmentVo> attachmentList = attachmentService.getAttachmentByXgId(referenceId);
        mv.addObject("attachmentVoList",attachmentList);
        return mv;

    }

    //查看文献条目
    @RequestMapping("/allReference")
    public ModelAndView allReference(String referenceId){
        UserVo user = userService.getLoginUser();
        List<ResearchVo> result = researchService.getReseachByUser(user.getId());
        List<ReferenceVo> referenceVoList  = referenceService.getReferenceByUserId(user.getId());
        ModelAndView mv = new ModelAndView("manager/allReference");
        mv.addObject("referenceVoList", referenceVoList);
        return mv;
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView upload(@RequestParam("referenceId") String referenceId,@RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {
        attachmentService.addAttachment(referenceId,uploadFile);
        ModelAndView mv = new ModelAndView("redirect:/manager/referenceDetail?referenceId="+referenceId);
        return mv;
    }

    //下载附件
    @RequestMapping("/downloadAttachment")
    public ResponseEntity<byte[]> downloadAttachment(String attachmentId) throws IOException {
        String filePath = attachmentService.getAttachmentPathById(attachmentId);
        AttachmentVo attachmentVo = attachmentService.getAttachmentById(attachmentId);
        File originFile = new File(filePath);
        byte[] body = null;
        InputStream is = new FileInputStream(originFile);
        body = new byte[is.available()];
        is.read(body);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(attachmentVo.getName(),"UTF-8") );
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);

        return entity;

    }

    //删除附件
    @RequestMapping("/deleteAttachment")
    public ModelAndView deleteAttachment(String attachmentId,String referenceId){
        attachmentService.deleteAttachment(attachmentId);
        ModelAndView mv = new ModelAndView("redirect:/manager/referenceDetail?referenceId="+referenceId);
        return mv;
    }

    @RequestMapping("/getAllResearch")
    @ResponseBody
    public Map<String,Object> getAllResearch(){
        List<ResearchVo> researchVoList = researchService.getReseachByUser(userService.getLoginUser().getId());
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("AllResearch",researchVoList);
        return map;
    }

    @RequestMapping("/saveReferenceToResearch")
    @ResponseBody
    public Map<String,Object> saveReferenceToResearch(String researchId,String[] referenceIdList){
        Map<String,Object> map = new HashMap<String, Object>();
        researchService.saveReferenceToResearch(researchId,referenceIdList);
        return map;
    }


    @RequestMapping("/getSelectReferenceList")
    @ResponseBody
    public Map<String,Object> getSelectReferenceList(String[] selectedIdList){
        UserVo user = userService.getLoginUser();
        List<ResearchVo> researchVoList = researchService.getReseachByUser(user.getId());
        List<JsonNode> firstNode = SelectorUtil.convertReferenceList(researchVoList,selectedIdList);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("firstNode",firstNode);
        return map;
    }

    @RequestMapping("/getShareReferenceList")
    @ResponseBody
    public Map<String,Object> getShareReferenceList(String[] referenceIdList){
        List<ReferenceVo> shareReferenceList = new ArrayList<ReferenceVo>();
        if(referenceIdList!=null){
            for(int i=0;i<referenceIdList.length;i++){
                if(!referenceIdList[i].contains("research")){
                    shareReferenceList.add(referenceService.getReferenceById(referenceIdList[i]));
                }
            }
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("shareReferenceList",shareReferenceList);
        return map;
    }
    @RequestMapping("/getReferenceList")
    @ResponseBody
    public Map<String,Object> getTeferenceList(int momentid)
    {
        System.out.println("momentid:"+momentid);
        MomentVo momentVo=momentService.getMoment(momentid);

        List<ReferenceVo> refernceVoList=momentVo.getReferenceVoList();

        List<ReferenceVo> referencelist=new ArrayList<ReferenceVo>();
        for(ReferenceVo referenceVo:refernceVoList)
        {
            referencelist.add(referenceVo);
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("referencelist",referencelist);
        return map;
    }
}
