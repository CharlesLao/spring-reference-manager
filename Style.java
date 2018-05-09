package syuu.dataObject;

import javax.persistence.*;

@Entity
@Table(name="style")
public class Style {
    @Id
    @GeneratedValue
    private int id;


    private String name;
    private String regularExpression;
    //最后一位作者是否用and隔开
    private String andLastAuthor;
    //是否用et al代替三位以后的作者
    private String etalAuthor;
    //是否将作者firstname用大写代替
    private String upperFirstname;
    //是否将作者middleName用大写代替
    private String upperMiddlename;
    //表明创建者，如果为0则为默认风格
    private int userId;


    public final static String STANDARD="standard";
    public Style(String styleName){
        this.regularExpression="";
    }

    public Style(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegularExpression() {
        return regularExpression;
    }

    public void setRegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAndLastAuthor() {
        return andLastAuthor;
    }

    public void setAndLastAuthor(String andLastAuthor) {
        this.andLastAuthor = andLastAuthor;
    }

    public String getEtalAuthor() {
        return etalAuthor;
    }

    public void setEtalAuthor(String etalAuthor) {
        this.etalAuthor = etalAuthor;
    }

    public String getUpperFirstname() {
        return upperFirstname;
    }

    public void setUpperFirstname(String upperFirstname) {
        this.upperFirstname = upperFirstname;
    }

    public String getUpperMiddlename() {
        return upperMiddlename;
    }

    public void setUpperMiddlename(String upperMiddlename) {
        this.upperMiddlename = upperMiddlename;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
