package syuu.service.VO;

import syuu.dataObject.YdComment;

/**
 * Created by liujun on 2018/4/18.
 */
public class YdCommentVo {
    private int id;
    private UserVo touser;
    private int commentid;

    public YdCommentVo(){}

    public YdCommentVo(int id, UserVo touser, int commentid)
    {
        this.id=id;
        this.touser=touser;
        this.commentid=commentid;
    }

    public YdCommentVo(YdComment ydComment)
    {
        this.id=ydComment.getId();
        this.touser=new UserVo(ydComment.getTouser());
        this.commentid=ydComment.getComment();
    }

    public int getId() {
        return id;
    }

    public UserVo getTouser() {
        return touser;
    }

    public int getCommentVo() {
        return commentid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTouser(UserVo touser) {
        this.touser = touser;
    }

    public void setCommentVo(int commentVo) {
        this.commentid = commentVo;
    }
}
