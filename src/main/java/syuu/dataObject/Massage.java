package syuu.dataObject;


import syuu.service.VO.MassageVo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="massage")
public class Massage {
    @Id
    @GeneratedValue
    private int id;

    private String subject;

    private String content;

    private int fromUser;

    private int toUser;

    private Date time;

    //记录共享了哪些引用文献
    @ManyToMany
    @JoinColumn(name="reference", referencedColumnName = "id")
    private List<Reference> reference;

    //记录该条记录属于哪个用户，用于删除操作时使用
    @ManyToOne
    @JoinColumn(name="user", referencedColumnName = "id", nullable = false)
    private User user;

    private String yd;
    public Massage(MassageVo massageVo) {
        this.id=massageVo.getId();
        this.subject = massageVo.getSubject();
        this.content = massageVo.getContent();
        this.fromUser = massageVo.getFromUser().getId();
        this.toUser = massageVo.getToUser().getId();
        this.yd = massageVo.getYd();
    }

    public Massage(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
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

    public int getFromUser() {
        return fromUser;
    }

    public void setFromUser(int fromUser) {
        this.fromUser = fromUser;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getYd() {
        return yd;
    }

    public void setYd(String yd) {
        this.yd = yd;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Reference> getReference() {
        return reference;
    }

    public void setReference(List<Reference> reference) {
        this.reference = reference;
    }
}
