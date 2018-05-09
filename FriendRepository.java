package syuu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import syuu.dataObject.Friend;
import syuu.dataObject.Reference;
import syuu.dataObject.User;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend,Integer> {

    List<Friend> findByUserId(int userId);

    List<Friend> findByUserIdAndFriendId(int userId,int friendId);

}
