package syuu.service.VO;

import syuu.dataObject.Massage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MassageVo {
    private int id;

    private String subject;

    private String content;

    private UserVo fromUser;

    private UserVo toUser;

    private String time;

    private List<ReferenceVo> referenceVoList;

    private String yd;

    public MassageVo(String subject, UserVo toUser, List<ReferenceVo> referenceVoList,UserVo fromUser, String content, String yd, Date time) {
        this.content = content;
        this.subject = subject;
        this.toUser = toUser;
        this.fromUser = fromUser;
        this.yd = yd;
        this.time = new SimpleDateFormat("yyyy-MM-dd").format(time);
        this.referenceVoList = referenceVoList;
    }

    public MassageVo(Massage massage) {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserVo getFromUser() {
        return fromUser;
    }

    public void setFromUser(UserVo fromUser) {
        this.fromUser = fromUser;
    }

    public UserVo getToUser() {
        return toUser;
    }

    public void setToUser(UserVo toUser) {
        this.toUser = toUser;
    }

    public List<ReferenceVo> getReferenceVoList() {
        return referenceVoList;
    }

    public void setReferenceVoList(List<ReferenceVo> referenceVoList) {
        this.referenceVoList = referenceVoList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getYd() {
        return yd;
    }

    public void setYd(String yd) {
        this.yd = yd;
    }
}
