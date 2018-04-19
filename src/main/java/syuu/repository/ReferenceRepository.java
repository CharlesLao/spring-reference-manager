package syuu.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import syuu.dataObject.Reference;
import syuu.dataObject.Research;

import java.util.List;

@Repository
public interface ReferenceRepository  extends JpaRepository<Reference,Integer> {
    List<Reference> getReferenceByResearch(Research one);
}
