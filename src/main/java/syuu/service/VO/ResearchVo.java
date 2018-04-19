package syuu.service.VO;

import syuu.dataObject.Research;

import java.util.List;

public class ResearchVo {
    private String name;
    private Integer id;
    private UserVo user;
    private List<ReferenceVo> referenceVoList;

    public ResearchVo(Research research) {
        this.setId(research.getId());
        this.setName(research.getName());
        this.setUser(new UserVo(research.getUser()));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public List<ReferenceVo> getReferenceVoList() {
        return referenceVoList;
    }

    public void setReferenceVoList(List<ReferenceVo> referenceVoList) {
        this.referenceVoList = referenceVoList;
    }
}
