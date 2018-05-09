package syuu.dataObject;

import javax.persistence.*;

/**
 * Created by liujun on 2018/4/18.
 */
@Entity
@Table(name = "yd_comment")
public class YdComment {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "touserid",referencedColumnName = "id",nullable = false)
    private User touser;

    private int commentid;

    public void setId(int id) {
        this.id = id;
    }

    public void setTouser(User touser) {
        this.touser = touser;
    }

    public void setComment(int comment) {
        this.commentid = comment;
    }

    public int getId() {

        return id;
    }

    public User getTouser() {
        return touser;
    }

    public int getComment() {
        return commentid;
    }
}
