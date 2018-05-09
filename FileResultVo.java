package syuu.service.VO;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件Vo
 */
public class FileResultVo extends ResultVo {

    private int id;// 如果使用附件表，且只有一个文件，传回一个id
    private List<Integer> ids;// 如果使用附件表，且有多个文件，传回一个ids(多个id用,隔开)
    // 如果不使用附件表，则传回文件名和路径
    private String name;
    private String path;
    private List<String> errorNames;// 图片上传失败，则记录

    public FileResultVo() {

    }

    public FileResultVo(boolean result, String message, int id) {
        this.result = result;
        this.message = message;
            this.id = id;
    }

    public FileResultVo(boolean result, String message,  List<Integer> ids) {
        this.result = result;
        this.message = message;
        this.ids = ids;
    }

    public FileResultVo(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public FileResultVo(boolean result, String message, String name, String path) {
        this.result = result;
        this.message = message;
        this.name = name;
        this.path = path;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }


    public List<String> getErrorNames() {
        return errorNames;
    }

    public void setErrorNames(List<String> errorNames) {
        this.errorNames = errorNames;
    }

    public void addErrorName(String originalFileName) {
        errorNames.add(originalFileName);
    }
}