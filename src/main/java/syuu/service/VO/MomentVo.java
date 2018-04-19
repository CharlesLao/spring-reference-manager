package syuu.service.VO;

import syuu.dataObject.Comment;
import syuu.dataObject.Like;
import syuu.dataObject.Moment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MomentVo {
    private int id;
    private String content;
    private UserVo user;
    private String time;
    private List<UserVo> blockList;
    private List<ReferenceVo> referenceVoList;
    private List<CommentVo> commentVoList;
    private int like_num;
    private List<CommentVo> ydCommentList;
    //private List<LikeVo> likeVoList;
    //private List<CommentVo> commentVoList;

    public  MomentVo(){}

    public MomentVo(int id, String content, Date time, UserVo user, List<UserVo> blockList, List<ReferenceVo> referenceVoList, List<CommentVo> commentVoList, int like_num,List<CommentVo> ydCommentList) {
        this.id = id;
        this.content = content;
        //如果日期是今天 不显示日期
        String date = new SimpleDateFormat("yyyy-MM-dd").format(time);
        if(date.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
            this.time = new SimpleDateFormat("HH:mm").format(time);
        }else{
            this.time = new SimpleDateFormat("yyyy-MM-dd").format(time);
        }
        this.blockList = blockList;
        this.referenceVoList = referenceVoList;
        this.commentVoList = commentVoList;
        this.like_num=like_num;
        this.user = user;
        this.ydCommentList=ydCommentList;
    }

    public void setYdCommentList(List<CommentVo> ydCommentList) {
        this.ydCommentList = ydCommentList;
    }

    public List<CommentVo> getYdCommentList() {

        return ydCommentList;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getLike_num() {

        return like_num;
    }

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

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<UserVo> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<UserVo> blockList) {
        this.blockList = blockList;
    }

    public List<ReferenceVo> getReferenceVoList() {
        return referenceVoList;
    }

    public void setReferenceVoList(List<ReferenceVo> referenceVoList) {
        this.referenceVoList = referenceVoList;
    }

    public List<CommentVo> getCommentVoList() {
        return commentVoList;
    }

    public void setCommentVoList(List<CommentVo> commentVoList) {
        this.commentVoList = commentVoList;
    }
}
