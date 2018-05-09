package syuu.service.VO;

import syuu.dataObject.Comment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentVo {
    private int id;
    private UserVo fromUser;
    private UserVo toUser;
    //private String yd;
    private String content;
    private String time;
    private int momentid;

    private int commenttype;

    public CommentVo(int id,UserVo fromUser,UserVo toUser,String content,Date time,int momentid,int commenttype)
    {
        this.id=id;
        this.fromUser=fromUser;
        this.toUser=toUser;
       // this.yd=yd;
        this.content=content;
        this.momentid=momentid;
        this.commenttype=commenttype;


        String date = new SimpleDateFormat("yyyy-MM-dd").format(time);
        if(date.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
            this.time = new SimpleDateFormat("HH:mm").format(time);
        }else{
            this.time = new SimpleDateFormat("yyyy-MM-dd").format(time);
        }
    }

    public CommentVo(Comment comment)
    {
        this.id=comment.getId();
        this.content=comment.getContent();
        this.fromUser=new UserVo(comment.getFromuser());
        this.toUser=new UserVo(comment.getTouser());
        this.commenttype=comment.getCommenttype();
    }

    public void setMomentid(int momentid) {
        this.momentid = momentid;
    }

    public void setCommenttype(int commenttype) {
        this.commenttype = commenttype;
    }

    public int getCommenttype() {

        return commenttype;
    }

    public int getMomentid() {

        return momentid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
