package syuu.service.VO;

import syuu.dataObject.Comment;
import syuu.dataObject.Like;
import syuu.dataObject.Moment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
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
    private UserVo loginUser;
    //private List<LikeVo> likeVoList;
    //private List<CommentVo> commentVoList;

    public  MomentVo(){}

    public MomentVo(int id, String content, Date time, UserVo user, List<UserVo> blockList, List<ReferenceVo> referenceVoList, List<CommentVo> commentVoList, int like_num,List<CommentVo> ydCommentList,UserVo loginUser) {

        int now_hour=Integer.parseInt(new SimpleDateFormat("HH").format(new Date()));
        int now_minute=Integer.parseInt(new SimpleDateFormat("mm").format(new Date()));
        int hour=Integer.parseInt(new SimpleDateFormat("HH").format(time));
        int minut=Integer.parseInt(new SimpleDateFormat("mm").format(time));

        String date = new SimpleDateFormat("yyyy-MM-dd").format(time);
        String date_hour=new SimpleDateFormat("HH").format(time);

        Calendar calendar=Calendar.getInstance();
        Calendar calendar_time=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar_time.setTime(time);



        calendar.add(Calendar.DAY_OF_MONTH,-1);
        Date day_brfore=calendar.getTime();

        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY,-1);
        Date hour_brfore=calendar.getTime();

        calendar.setTime(new Date());
        calendar.add(calendar.MINUTE,-1);
        Date minut_brfore=calendar.getTime();


        //多少天前
        int da=calendar.get(Calendar.DAY_OF_YEAR);
        int da_time=calendar_time.get(Calendar.DAY_OF_YEAR);


        //System.out.println("前一天："+new SimpleDateFormat("yyyy-MM-dd").format(day_brfore));


        //判断显示多少天前、多少小时前、多少分钟前。
        if(day_brfore.before(time)==true)
        {
            if(hour_brfore.before(time)==false)//一小时之外的朋友圈
            {
                if(date.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date())))
                {
                     this.time=now_hour-hour+"小时前";
                }
                else
                {
                     this.time=24-hour+now_hour+"小时前";
                }
            }
            else//一小时之内的朋友圈
            {
                if(date_hour.equals(new SimpleDateFormat("HH").format(new Date())))
                {
                    int t=now_minute-minut;
                    if(t==0)
                        this.time=1+"分钟前";
                    else
                    this.time=now_minute-minut+"分钟前";
                }
                else
                {
                    this.time=60-minut+now_minute+"分钟前";
                }
            }
        }
        else
        {
            this.time=da-da_time+"天前";
        }


        //String date = new SimpleDateFormat("yyyy-MM-dd").format(time);


        /*if(date.equals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))){
            //if()
            this.time = new SimpleDateFormat("HH:mm").format(time);
        }else{
            this.time = new SimpleDateFormat("yyyy-MM-dd").format(time);
        }*/


        this.id = id;
        this.content = content;
        this.blockList = blockList;
        this.referenceVoList = referenceVoList;
        this.commentVoList = commentVoList;
        this.like_num=like_num;
        this.user = user;
        this.ydCommentList=ydCommentList;
        this.loginUser=loginUser;
    }

    public void setLoginUser(UserVo loginUser) {
        this.loginUser = loginUser;
    }

    public UserVo getLoginUser() {

        return loginUser;
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
