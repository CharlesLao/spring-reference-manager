package syuu.service.VO;

import org.springframework.beans.factory.annotation.Autowired;
import syuu.dataObject.Like;

/**
 * Created by liujun on 2018/4/12.
 */
public class LikeVo {
    private int id;
    private UserVo userVo;
    private int momentid;

    public LikeVo(int id, UserVo userVo, int momentid)
    {
        this.id=id;
        this.userVo=userVo;
        this.momentid=momentid;


    }

    public LikeVo(Like like)
    {
        this.userVo=new UserVo(like.getUser());
        this.id=like.getId();
    }


    public LikeVo(){}



    void setMomentid(int momentid) {
        this.momentid = momentid;
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



    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }



    public UserVo getUserVo() {
        return userVo;
    }



}
