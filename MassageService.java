package syuu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syuu.dataObject.Massage;
import syuu.dataObject.Reference;
import syuu.dataObject.Share;
import syuu.repository.MassageRepository;
import syuu.repository.ShareRepository;
import syuu.repository.UserRepository;
import syuu.service.VO.MassageVo;
import syuu.service.VO.ReferenceVo;
import syuu.service.VO.UserVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MassageService {
    @Autowired
    MassageRepository massageRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShareRepository shareRepository;

    public void saveMassage(MassageVo massageVo) {
        Massage massage_1 = new Massage(massageVo);
        Massage massage_2 = new Massage(massageVo);
        List<Reference> referenceList = new ArrayList<Reference>();
        for(ReferenceVo referenceVo:massageVo.getReferenceVoList()){
            referenceList.add(new Reference(referenceVo));
        }
        massage_1.setReference(referenceList);
        massage_2.setReference(referenceList);
        massage_1.setTime(new Date());
        massage_2.setTime(new Date());
        massage_1.setUser(userRepository.findOne(massage_1.getFromUser()));
        massage_2.setUser(userRepository.findOne(massage_1.getToUser()));
        massage_1.setYd("已读");
        massageRepository.save(massage_1);
        massageRepository.save(massage_2);
    }

    public void setYd(MassageVo massageVo){
        Massage massage = massageRepository.findOne(massageVo.getId());
        massage.setYd("已读");
        massageRepository.save(massage);
    }

    public List<MassageVo> getMassagesByUser(UserVo userVo){
        List<Massage> massageList = massageRepository.findByUser(userRepository.findOne(userVo.getId()));
        //排序

        List<MassageVo> allMassageVo = new ArrayList<MassageVo>();
        UserVo user = userService.getLoginUser();
        for(Massage massage:massageList){
            MassageVo massageVo = convert(massage);
            allMassageVo.add(massageVo);
        }
        List<MassageVo> resultVo = new ArrayList<MassageVo>();
        for(int i=0;i<allMassageVo.size();i++){
            resultVo.add(allMassageVo.get(allMassageVo.size()-1-i));
        }
        return resultVo;
    }

    public void deleteMassage(String massageId) {
        List<Share> shareList = shareRepository.findByMassageId(Integer.valueOf(massageId));
        for(Share share:shareList){
            shareRepository.delete(share);
        }
        massageRepository.delete(Integer.valueOf(massageId));
    }

    public MassageVo getMassagesById(String massageId) {
        Massage massage = massageRepository.findOne(Integer.valueOf(massageId));
        MassageVo massageVo = convert(massage);
        return massageVo;
    }

    private MassageVo convert(Massage massage){
        UserVo user = userService.getLoginUser();
        String subject = massage.getSubject();
        UserVo toUser = userService.getUserById(massage.getToUser());
        UserVo fromUser = userService.getUserById(massage.getFromUser());
        if(user.getId()==toUser.getId()){
            toUser.setUsername("我");
        }
        if(user.getId()==fromUser.getId()){
            fromUser.setUsername("我");
        }
        List<ReferenceVo> referenceVoList = new ArrayList<ReferenceVo>();
        if(massage.getReference()!=null){
            for(Reference reference:massage.getReference()){
                referenceVoList.add(new ReferenceVo(reference));
            }
        }
        String content = massage.getContent();
        String yd = massage.getYd();
        Date time = massage.getTime();
        MassageVo massageVo = new MassageVo(subject,toUser,referenceVoList,fromUser,content,yd,time);
        massageVo.setId(massage.getId());
        return massageVo;
    }

    public int getNumberOfMassage() {
        UserVo user = userService.getLoginUser();
        List<Massage> massageList = massageRepository.findByUser(userRepository.findOne(user.getId()));
        int result = 0;
        for(Massage massage:massageList){
            if(!massage.getYd().equals("已读")){
                result+=1;
            }
        }
        return result;

    }
}
