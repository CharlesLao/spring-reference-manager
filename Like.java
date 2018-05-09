package syuu.dataObject;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * Created by liujun on 2018/4/12.
 */
@Entity
@Table(name = "set_like")
public class Like {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "userid",referencedColumnName = "id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "momentid",referencedColumnName = "id",nullable = false)
    private Moment moment;

    public void setMoment(Moment moment) {
        this.moment = moment;
    }

    public Moment getMoment() {

        return moment;
    }

    public void setUser(User user) {


        this.user = user;
    }


    public User getUser() {

        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
