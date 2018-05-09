package syuu.service.VO;

import syuu.dataObject.User;

import java.util.List;

public class UserVo {
    private int id;
    private String username;
    private List<UserVo> friendList;

    public UserVo(User user) {
        this.setId(user.getId());
        this.setUsername(user.getUsername());
    }

    public UserVo() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UserVo> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<UserVo> friendList) {
        this.friendList = friendList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
