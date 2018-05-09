package syuu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import syuu.configuration.Settings;
import syuu.dataObject.Attachment;
import syuu.repository.AttachmentRepository;
import syuu.service.VO.AttachmentVo;
import syuu.util.IOUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AttachmentService {
    @Autowired
    Settings settings;
    @Autowired
    AttachmentRepository attachmentRepository;
    public void addAttachment(String referenceId, MultipartFile uploadFile) throws IOException {
        String filePath = settings.getAttachmentPath();
        String fileName = uploadFile.getOriginalFilename();
        String localPath = IOUtil.uploadFile(filePath,uploadFile,referenceId);
        Attachment attachment = new Attachment();
        attachment.setName(fileName);
        attachment.setPath(localPath);
        attachment.setTime(new Date());
        attachment.setXgId(Integer.valueOf(referenceId));
        attachmentRepository.save(attachment);
    }

    public List<AttachmentVo> getAttachmentByXgId(String referenceId) {
        List<Attachment> attachmentList = attachmentRepository.findByXgId(Integer.valueOf(referenceId));
        List<AttachmentVo> attachmentVoList = new ArrayList<AttachmentVo>();
        if(attachmentList.size()>0){
            for(Attachment attachment:attachmentList){
                attachmentVoList.add(new AttachmentVo(attachment));
            }
        }
        return attachmentVoList;
    }

    public AttachmentVo getAttachmentById(String attachmentId) {
        Attachment attachment = attachmentRepository.findOne(Integer.valueOf(attachmentId));
        if(attachment!=null){
            return new AttachmentVo(attachment);
        }else{
            return new AttachmentVo();
        }
    }

    public String getAttachmentPathById(String attachmentId) {
        Attachment attachment = attachmentRepository.findOne(Integer.valueOf(attachmentId));
        if(attachment!=null){
            return attachment.getPath();
        }else{
            return "";
        }
    }

    public void deleteAttachment(String attachmentId) {
        Attachment attachment = attachmentRepository.findOne(Integer.valueOf(attachmentId));
        IOUtil.deleteFile(attachment.getPath());
        attachmentRepository.delete(attachment);
    }
}
