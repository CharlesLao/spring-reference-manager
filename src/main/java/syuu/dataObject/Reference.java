package syuu.dataObject;

import syuu.service.VO.ReferenceVo;

import javax.persistence.*;

@Entity
@Table(name="reference")
public class Reference {

    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name="research",referencedColumnName = "id",nullable = false)
    private Research research;
    private String name;
    private String authors;
    private Integer beginPage;
    private Integer endPage;
    private String conference;
    private String attachment;
    private int year;
    private Integer jh;
    private Integer qh;
    private String hydd;
    private String lx;

    public Reference(ReferenceVo referenceVo) {
        if(referenceVo.getId()!=null){
            this.id = referenceVo.getId();
        }
        this.name = referenceVo.getName();
        this.authors = referenceVo.getAuthors();
        this.beginPage = referenceVo.getBeginPage();
        this.endPage = referenceVo.getEndPage();
        this.conference = referenceVo.getConference();
        this.year = referenceVo.getYear();
        this.attachment = referenceVo.getFulltext();
        this.qh = referenceVo.getQh();
        this.jh = referenceVo.getJh();
        this.hydd = referenceVo.getHydd();
        this.lx = referenceVo.getLx();
    }

    public Reference(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Research getResearch() {
        return research;
    }

    public void setResearch(Research research) {
        this.research = research;
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
        this.authors = authors;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
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
