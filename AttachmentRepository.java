package syuu.repository;

import syuu.dataObject.Attachment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AttachmentRepository  extends JpaRepository<Attachment,Integer> {
    List<Attachment> findByXgId(Integer xgId);
}
