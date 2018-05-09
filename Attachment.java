package syuu.dataObject;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 附件
 */
@Entity
@Table(name = "attachment")
public class Attachment {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String path;
    private Date time;
    private int xgId;//与其相关联的文献的id



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getXgId() {
        return xgId;
    }

    public void setXgId(int xgId) {
        this.xgId = xgId;
    }
}