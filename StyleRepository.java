package syuu.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import syuu.dataObject.Style;

import java.util.List;


public interface StyleRepository  extends JpaRepository<Style,Integer> {
    List<Style> findByUserIdAndName(int userId, String name);
}
