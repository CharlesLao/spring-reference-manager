package syuu.service.VO;

import syuu.dataObject.Attachment;

import java.text.SimpleDateFormat;

public class AttachmentVo {
    private int id;
    private String name;
    private String time;
    private int xgId;

    public AttachmentVo(){

    }

    public AttachmentVo(Attachment attachment) {
        this.id = attachment.getId();
        this.name = attachment.getName();
        this.time = new SimpleDateFormat("yyyy/MM/dd").format(attachment.getTime());
        this.xgId = attachment.getXgId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getXgId() {
        return xgId;
    }

    public void setXgId(int xgId) {
        this.xgId = xgId;
    }
}
