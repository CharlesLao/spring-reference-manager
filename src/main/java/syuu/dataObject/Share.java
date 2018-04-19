package syuu.dataObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="share")
public class Share {
    @Id
    @GeneratedValue
    private int id;

    private int massageId;

    private int reference;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMassageId() {
        return massageId;
    }

    public void setMassageId(int massageId) {
        this.massageId = massageId;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }
}
