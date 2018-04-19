package syuu.dataObject;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liujun on 2018/4/14.
 */
@Entity
@Table(name="comment1")
public class Comment {
    @Id
    @GeneratedValue
    private  int id;
    /**
     * 如果点击的是评论按钮，from_user位当前用户，to_user为该条朋友圈的用户。
     */
   @ManyToOne
    @JoinColumn(name="from_user",referencedColumnName = "id",nullable = false)
    private  User fromuser;

    @ManyToOne
    @JoinColumn(name="to_user",referencedColumnName = "id",nullable = false)
    private User touser;

    @ManyToOne
    @JoinColumn(name = "momentid",referencedColumnName = "id",nullable = false)
    private Moment moment;

    private String content;
    private Date time;
    private int commenttype;


    public void setCommenttype(int commenttype) {
        this.commenttype = commenttype;
    }

    public int getCommenttype() {

        return commenttype;
    }



    public void setMoment(Moment moment) {
        this.moment = moment;
    }

    public Moment getMoment() {

        return moment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFromuser(User fromuser) {
        this.fromuser = fromuser;
    }

    public void setTouser(User touser) {
        this.touser = touser;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public User getFromuser() {
        return fromuser;
    }

    public User getTouser() {
        return touser;
    }

    public String getContent() {
        return content;
    }

    public Date getTime() {
        return time;
    }
}
