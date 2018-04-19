package syuu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syuu.dataObject.Reference;
import syuu.dataObject.Research;
import syuu.dataObject.User;
import syuu.repository.ReferenceRepository;
import syuu.repository.ResearchRepository;
import syuu.repository.UserRepository;
import syuu.service.VO.ReferenceVo;
import syuu.service.VO.ResearchVo;
import syuu.service.VO.UserVo;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResearchService {

    @Autowired
    ResearchRepository researchRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReferenceRepository referenceRepository;

    @Autowired
    ReferenceService referenceService;


    public List<ResearchVo> getReseachByUser(int userId){
        User user = userRepository.findOne(userId);
        List<Research> result = researchRepository.getResearchByUser(user);
        List<ResearchVo> researchVo = convert(result);
        return researchVo;

    }

    private List<ResearchVo> convert(List<Research> result) {
        ArrayList<ResearchVo> voList = new ArrayList<ResearchVo>();
        for(Research research:result){
            ResearchVo vo = new ResearchVo(research);
            vo.setReferenceVoList(referenceService.getReferenceOfResearch(vo.getId()));
            voList.add(vo);
        }
        return voList;
    }

    public ResearchVo getReseachById(int id) {
        Research research = researchRepository.findOne(id);
        ResearchVo researchVo = new ResearchVo(research);
        return researchVo;
    }

    public String saveNewResearch(String researchName, String[] referenceIdList, UserVo loginUser) {
        Research research = new Research();
        research.setName(researchName);
        research.setUser(userRepository.findOne(loginUser.getId()));
        research = researchRepository.save(research);
        for(int i=0;i<referenceIdList.length;i++){
            Reference reference = referenceRepository.findOne(Integer.valueOf(referenceIdList[i]));
            copyReferenceToResearch(reference,research);
        }
        return String.valueOf(research.getId());
    }

    public void copyReferenceToResearch(Reference reference,Research research){
        Reference reference1 = new Reference(new ReferenceVo(reference));
        reference1.setId(0);
        reference1.setResearch(research);
        referenceRepository.save(reference1);
    }

    public void deleteResearch(String researchId) {
        researchRepository.delete(Integer.valueOf(researchId));
    }

    public void saveReferenceToResearch(String researchId, String[] referenceIdList) {
        Research research = researchRepository.findOne(Integer.valueOf(researchId));
        for(int i=0;i<referenceIdList.length;i++){
            Reference reference = referenceRepository.findOne(Integer.valueOf(referenceIdList[i]));
            copyReferenceToResearch(reference,research);
        }
    }
}
