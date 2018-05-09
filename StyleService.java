package syuu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syuu.dataObject.Reference;
import syuu.dataObject.Style;
import syuu.repository.ReferenceRepository;
import syuu.repository.StyleRepository;
import syuu.service.VO.ReferenceVo;

import java.util.ArrayList;
import java.util.List;

@Service
public class StyleService {

    @Autowired
    ReferenceRepository referenceRepository;
    @Autowired
    StyleRepository styleRepository;
    @Autowired
    UserService userService;

    public List<String> getReferenceListWithStyle(ArrayList<ReferenceVo> referenceVoList, Style style) {
        ArrayList<String> stringList = new ArrayList<String>();
        for (ReferenceVo referenceVo: referenceVoList){
            Reference reference = referenceRepository.findOne(referenceVo.getId());
            stringList.add(convert(style,reference));
        }
        return stringList;
    }

    private String convert(Style style, Reference reference) {
        String result = "";
        if(style.getRegularExpression().equals("")){
            if(reference.getQh()!=null&&reference.getJh()!=null){
                result+=(reference.getAuthors()+":"+reference.getName()+"."+reference.getConference()+" "+reference.getJh()+"("+reference.getQh()+")"+":"+reference.getBeginPage()+"-"+reference.getEndPage()+"("+reference.getYear()+")");
            }else{
                result+=(reference.getAuthors()+":"+reference.getName()+"."+reference.getConference()+" "+":"+reference.getBeginPage()+"-"+reference.getEndPage()+"("+reference.getYear()+")");
            }
        }

        return result;
    }

    public List<Style> getAllStyle() {
        List<Style> allStyle = styleRepository.findAll();
        return allStyle;
    }

    public String styleTheReference(int referenceId, int styleId) {
        Reference reference = referenceRepository.findOne(referenceId);
        Style style = styleRepository.findOne(styleId);
        //拆分作者
        String[] authorList = reference.getAuthors().split(",");
        for(int i=0; i<authorList.length; i++){
            authorList[i]=authorList[i].trim();
        }
        if(style.getUpperFirstname().equals("true")){
            for(int i=1;i<authorList.length-1;i++){
                char firstname = authorList[i].charAt(0);
                if(firstname>='a'&&firstname<='z'){
                    firstname = (char)(firstname-32);
                }
                String[] name = authorList[i].split(" ");
                name[0]=String.valueOf(firstname)+".";
                authorList[i] = "";
                for(int j = 0;j<name.length;j++){
                    authorList[i]+=name[j];
                    if(j!=name.length-1){
                        authorList[i]+=" ";
                    }
                }
            }
        }

        if(style.getUpperMiddlename().equals("true")){
            for(int i=1;i<authorList.length-1;i++){
                String[] name = authorList[i].split(" ");
                if(name.length>2){
                    char middlename = name[1].charAt(0);
                    if(middlename>='a'&&middlename<='z'){
                        middlename = (char)(middlename-32);
                    }
                    name[1]=String.valueOf(middlename)+".";
                    authorList[i] = name[0]+" "+name[1]+" "+name[name.length-1];
                }
            }
        }



        String author = "";
        if(authorList.length>3&&style.getEtalAuthor().equals("true")){
            author = authorList[0]+","+authorList[1]+","+authorList[2]+",et al." ;
        }else{
            author+=authorList[0];
            for(int i=1;i<authorList.length-1;i++){
                author+=","+authorList[i];
            }
            if(authorList.length>1&&style.getAndLastAuthor().equals("true")){
                author+=",and"+authorList[authorList.length-1];
            }else{
                author+=","+authorList[authorList.length-1];
            }
        }

        //解析正则表达式
        String exp = style.getRegularExpression();
        String[] exps = exp.split("###");
        String result = "";
        for(int j = 0; j<exps.length; j++){
            if(exps[j].equals("WZBT")){
                exps[j] = reference.getName().trim();
            }else if(exps[j].equals("ZZ")){
                exps[j] = author;
            }else if(exps[j].equals("NF")){
                exps[j] = String.valueOf(reference.getYear()).trim();
            }else if(exps[j].equals("QSY")){
                exps[j] = String.valueOf(reference.getBeginPage()).trim();
            }else if(exps[j].equals("MWY")){
                exps[j] = String.valueOf(reference.getEndPage()).trim();
            }else if(exps[j].equals("HYM")){
                exps[j] = reference.getConference().trim();
            }else if(exps[j].equals("QKM")){

            }else if(exps[j].equals("QH")){
                exps[j] = String.valueOf(reference.getQh()).trim();
            }else if(exps[j].equals("JH")){
                exps[j] = String.valueOf(reference.getQh()).trim();
            }else if(exps[j].equals("HYDD")){
                exps[j] = reference.getHydd().trim();
            }
            result += exps[j];
        }
        return result;
    }

    public void saveStyle(String expression, String name, String andLastAuthor, String etalAuthor, String upperFirstname, String upperMiddlename) {
        expression = expression.replaceAll("<img src="+'"'+"/img/","###");
        expression = expression.replaceAll(".png"+'"'+">","###");
        expression = expression.replaceAll("&nbsp;"," ");
        Style style = new Style();
        style.setName(name);
        style.setRegularExpression(expression);
        style.setAndLastAuthor(andLastAuthor);
        style.setEtalAuthor(etalAuthor);
        style.setUpperFirstname(upperFirstname);
        style.setUpperMiddlename(upperMiddlename);
        style.setUserId(userService.getLoginUser().getId());
        styleRepository.save(style);
    }

    public String isNameExist(String name) {
        List<Style> styleList = styleRepository.findByUserIdAndName(userService.getLoginUser().getId(),name);
        if(styleList.size()>0){
            return "名称已经存在";
        }else{
            return "名称可使用";
        }
    }
}
