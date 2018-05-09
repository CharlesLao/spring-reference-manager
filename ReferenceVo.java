package syuu.service.VO;

import syuu.dataObject.Reference;

import java.util.List;

public class ReferenceVo {
    private Integer id;
    private String name;
    private String authors;
    private Integer beginPage;
    private Integer endPage;
    private String conference;
    private String hydd;
    private Integer jh;
    private Integer qh;
    private String fulltext;
    private Integer researchId;
    private String lx;
    private int year;

    public ReferenceVo(Reference reference) {
        this.id = reference.getId();
        this.name = reference.getName();
        this.authors = reference.getAuthors();
        this.beginPage = reference.getBeginPage();
        this.endPage = reference.getEndPage();
        this.conference = reference.getConference();
        this.fulltext = reference.getAttachment();
        this.researchId = reference.getResearch().getId();
        this.year = reference.getYear();
        this.hydd = reference.getHydd();
        this.qh = reference.getQh();
        this.jh = reference.getJh();
        this.lx = reference.getLx();
    }

    public ReferenceVo(String name, String authors, String year, String conference,String hydd,String qh, String jh, String beginPage, String endPage, String researchId, String id,String lx) {
        this.name = name;
        this.authors = authors;
        this.year = Integer.valueOf(year);
        this.conference = conference;
        this.beginPage = Integer.valueOf(beginPage);
        this.endPage = Integer.valueOf(endPage);
        this.researchId = Integer.valueOf(researchId);
        this.qh = Integer.valueOf(qh);
        this.jh = Integer.valueOf(jh);
        this.hydd = hydd;
        if(id!=null){
            this.id=Integer.valueOf(id);
        }
        this.lx = lx;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        authors = authors;
    }

    public Integer getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(Integer beginPage) {
        this.beginPage = beginPage;
    }

    public Integer getEndPage() {
        return endPage;
    }

    public void setEndPage(Integer endPage) {
        this.endPage = endPage;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    public Integer getResearchId() {
        return researchId;
    }

    public void setResearchId(Integer researchId) {
        this.researchId = researchId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getHydd() {
        return hydd;
    }

    public void setHydd(String hydd) {
        this.hydd = hydd;
    }

    public Integer getJh() {
        return jh;
    }

    public void setJh(Integer jh) {
        this.jh = jh;
    }

    public Integer getQh() {
        return qh;
    }

    public void setQh(Integer qh) {
        this.qh = qh;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }
}
