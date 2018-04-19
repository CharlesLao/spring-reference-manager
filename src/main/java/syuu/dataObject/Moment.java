package syuu.dataObject;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="moment")
public class Moment {
    @Id
    @GeneratedValue
    private int id;

    private String content;

    private Date time;

    //记录共享了哪些引用文献
    @ManyToMany
    @JoinColumn(name="shareReferenceList", referencedColumnName = "id")
    private List<Reference> reference;


    @ManyToOne
    @JoinColumn(name="user",referencedColumnName = "id",nullable = false)
    private User user;

    @ManyToMany
    @JoinColumn(name="blockList", referencedColumnName = "id")
    private List<User> blockList;




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Reference> getReference() {
        return reference;
    }

    public void setReference(List<Reference> reference) {
        this.reference = reference;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<User> blockList) {
        this.blockList = blockList;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
