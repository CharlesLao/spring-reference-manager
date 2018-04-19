package syuu.util;

import syuu.service.VO.JsonNode;
import syuu.service.VO.ReferenceVo;
import syuu.service.VO.ResearchVo;
import syuu.service.VO.UserVo;

import java.util.ArrayList;
import java.util.List;

public class SelectorUtil {

    public static List<JsonNode> convertFriendList(List<UserVo> friendList, String[] selectedList) {
        List<JsonNode> childrenList = new ArrayList<JsonNode>();
        for(UserVo friend: friendList){
            JsonNode friendNode = new JsonNode();
            friendNode.setId(String.valueOf(friend.getId()));
            friendNode.setText(friend.getUsername());
            friendNode.setIcon("/img/user_suit.png");
            if(selectedList!=null){
                for(int i = 0;i<selectedList.length;i++){
                    if(friendNode.getId().equals(selectedList[i].replace("friend",""))){
                        friendNode.getState().setSelected(true);
                    }
                }
            }
            childrenList.add(friendNode);
        }
        return childrenList;
    }

    public static List<JsonNode> convertReferenceList(List<ResearchVo> researchVoList, String[] selectedIdList) {
        List<JsonNode> childrenList = new ArrayList<JsonNode>();
        for(ResearchVo researchVo: researchVoList){
            JsonNode researchNode = new JsonNode();
            researchNode.setId(String.valueOf("research"+researchVo.getId()));
            researchNode.setText(researchVo.getName());
            if(researchVo.getReferenceVoList()!=null){
                for(ReferenceVo referenceVo:researchVo.getReferenceVoList()){
                    JsonNode referenceNode = new JsonNode();
                    String name = referenceVo.getName();
                    //防止名字过长
                    if(name.length()>50){
                        name = name.substring(0,50)+"...";
                    }
                    referenceNode.setId(String.valueOf(referenceVo.getId()));
                    referenceNode.setText(name);
                    if(selectedIdList!=null){
                        for(int i = 0;i<selectedIdList.length;i++){
                            if(referenceNode.getId().equals(selectedIdList[i].replace("reference",""))){
                                referenceNode.getState().setSelected(true);
                            }
                        }
                    }
                    researchNode.getChildren().add(referenceNode);
                }
            }
            childrenList.add(researchNode);
        }
        return childrenList;
    }
}
