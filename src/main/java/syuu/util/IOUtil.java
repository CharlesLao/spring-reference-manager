package syuu.util;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IOUtil {

    public static String uploadFile(String path, MultipartFile uploadFile, String referenceId) throws IOException {
        String time = new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());
        String loacalPath = path + time+referenceId;
        File dest = new File(loacalPath);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        uploadFile.transferTo(dest);
        return loacalPath;
    }

    public static void deleteFile(String path) {
        File file = new File(path);
        file.delete();
    }
}