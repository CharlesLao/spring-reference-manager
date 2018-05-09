package syuu.dataObject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="invitation")
public class Invitation {
    @Id
    @GeneratedValue
    private int Id;

    @ManyToOne
    @JoinColumn(name="from_user", referencedColumnName = "id", nullable = false)
    private User fromUser;

    private int toUser;

    private String reason;

    private Date time;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }



    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
