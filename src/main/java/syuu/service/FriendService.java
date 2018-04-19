package syuu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import syuu.dataObject.Friend;
import syuu.dataObject.Invitation;
import syuu.dataObject.User;
import syuu.repository.FriendRepository;
import syuu.repository.InvitationRepository;
import syuu.repository.UserRepository;
import syuu.service.VO.InvitationVo;
import syuu.service.VO.UserVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FriendService {
    @Autowired
    FriendRepository friendRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    InvitationRepository invitationRepository;
    @Autowired
    UserService userService;
    public List<UserVo> getFriendByUser(UserVo userVo){
        List<UserVo> userVoList = new ArrayList<UserVo>();
        List<Friend> friendList = friendRepository.findByUserId(userVo.getId());
        if(friendList.size()!=0){
            for(Friend friend:friendList){
                User user = userRepository.findOne(friend.getFriendId());
                userVoList.add(new UserVo(user));
            }
        }
        return userVoList;
    }

    public List<InvitationVo> getInvitationByUser(UserVo userVo){
        List<Invitation> invitationList = invitationRepository.findByToUser(userVo.getId());
        List<InvitationVo> invitationVoList = new ArrayList<InvitationVo>();
        for(Invitation invitation:invitationList){
            invitationVoList.add(new InvitationVo(invitation));
        }
        return invitationVoList;
    }

    public void acceptInvitation(String invitationId) {
        Invitation invitation = invitationRepository.getOne(Integer.valueOf(invitationId));
        User fromUser = invitation.getFromUser();
        Friend friend = new Friend();
        friend.setUserId(userService.getLoginUser().getId());
        friend.setFriendId(fromUser.getId());

        Friend friendReverse = new Friend();
        friendReverse.setUserId(fromUser.getId());
        friendReverse.setFriendId(userService.getLoginUser().getId());

        List<Friend> friendList = friendRepository.findByUserIdAndFriendId(friend.getUserId(),friend.getFriendId());
        List<Friend> friendList_1 = friendRepository.findByUserIdAndFriendId(friend.getFriendId(),friend.getUserId());
        if(friendList.size()==0&&friendList_1.size()==0) {
            friendRepository.save(friend);
            friendRepository.save(friendReverse);
            invitationRepository.delete(invitation);
        }
    }

    public void deleteInvitation(String invitationId) {
        invitationRepository.delete(Integer.valueOf(invitationId));
    }

    public void saveInvitation(UserVo userVo, String toUser) {
        Invitation invitation = new Invitation();
        User fromUser = userRepository.findOne(userVo.getId());
        invitation.setFromUser(fromUser);
        invitation.setToUser(Integer.valueOf(toUser));
        List<Friend> friendList = friendRepository.findByUserIdAndFriendId(Integer.valueOf(toUser),fromUser.getId());
        List<Friend> friendList_1 = friendRepository.findByUserIdAndFriendId(fromUser.getId(),Integer.valueOf(toUser));
        if(friendList.size()==0&&friendList_1.size()==0){
            invitation.setTime(new Date());
            invitation.setReason("无");
            invitationRepository.save(invitation);
        }
    }

    public void deleteFriend(String friendId) {
        UserVo user = userService.getLoginUser();
        Friend myFriend = friendRepository.findByUserIdAndFriendId(user.getId(),Integer.valueOf(friendId)).get(0);
        Friend yourFriend = friendRepository.findByUserIdAndFriendId(Integer.valueOf(friendId),user.getId()).get(0);
        friendRepository.delete(myFriend);
        friendRepository.delete(yourFriend);
    }
}
