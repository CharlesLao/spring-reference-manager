package syuu.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import syuu.dataObject.Invitation;
import syuu.dataObject.Style;
import syuu.dataObject.User;

import java.util.List;


public interface InvitationRepository  extends JpaRepository<Invitation,Integer> {
    List<Invitation> findByToUser(int id);

    List<Invitation> findByToUserAndFromUser(Integer integer, User fromUser);
}
