package syuu.service.VO;

import syuu.dataObject.Invitation;

import java.text.SimpleDateFormat;

public class InvitationVo {
    private int id;
    private String username;
    private String reason;
    private String time;

    public InvitationVo(Invitation invitation){
        this.id = invitation.getId();
        this.username = invitation.getFromUser().getUsername();
        this.reason = invitation.getReason();
        this.time = new SimpleDateFormat("yyyy-MM-dd").format(invitation.getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
