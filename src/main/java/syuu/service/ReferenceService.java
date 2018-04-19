package syuu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syuu.dataObject.Reference;
import syuu.dataObject.Research;
import syuu.repository.ReferenceRepository;
import syuu.repository.ResearchRepository;
import syuu.repository.UserRepository;
import syuu.service.VO.ReferenceVo;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReferenceService {

    @Autowired
    ReferenceRepository referenceRepository;
    @Autowired
    ResearchRepository researchRepository;
    @Autowired
    UserRepository userRepository;

    public List<ReferenceVo> getReferenceOfResearch(Integer id) {

        List<Reference> referenceList = referenceRepository.getReferenceByResearch(researchRepository.findOne(id));
        List<ReferenceVo> referenceVoList = convert(referenceList);
        return referenceVoList;
    }

    private List<ReferenceVo> convert(List<Reference> referenceList) {
        List<ReferenceVo> referenceVoList = new ArrayList<ReferenceVo>();
        for(Reference reference: referenceList){
            referenceVoList.add(new ReferenceVo(reference));
        }
        return referenceVoList;
    }

    public void saveReference(ReferenceVo referenceVo) {
        Reference reference = new Reference(referenceVo);
        reference.setResearch(researchRepository.findOne(referenceVo.getResearchId()));
        referenceRepository.save(reference);
    }

    public ReferenceVo getReferenceById(String referenceId) {
        Reference reference = referenceRepository.findOne(Integer.valueOf(referenceId));
        return new ReferenceVo(reference);
    }

    public void removeReferenceById(String referenceId) {
        referenceRepository.delete(Integer.valueOf(referenceId));
    }

    public List<ReferenceVo> getReferenceByUserId(int id) {
        List<Research> researchList = researchRepository.getResearchByUser(userRepository.findOne(id));
        ArrayList<ReferenceVo> allReferenceList = new ArrayList<ReferenceVo>();
        for(Research research:researchList){
            List<Reference> referenceList = referenceRepository.getReferenceByResearch(research);
            for(Reference reference:referenceList){
                allReferenceList.add(new ReferenceVo(reference));
            }
        }
        return allReferenceList;
    }
}
