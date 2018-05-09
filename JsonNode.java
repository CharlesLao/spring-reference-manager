package syuu.service.VO;

import java.util.ArrayList;
import java.util.List;

public class JsonNode{
    private String id;
    private String text;
    private String icon;
    private State state = new State();
    private List<JsonNode> children = new ArrayList<JsonNode>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<JsonNode> getChildren() {
        return children;
    }

    public void setChildren(List<JsonNode> children) {
        this.children = children;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
