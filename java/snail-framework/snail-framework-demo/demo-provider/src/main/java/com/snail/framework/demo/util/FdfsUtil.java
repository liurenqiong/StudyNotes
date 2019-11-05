package com.snail.framework.demo.util;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.DefaultFastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;

@Component
public class FdfsUtil {

    public static String DEFAULT_GROUPNAME = "group1";

    @Autowired
    private DefaultFastFileStorageClient client;

    /**
     *
     * @param bytes 文件的字节数组
     * @param fileExtName 后缀名  例："jpg"
     * @return 路径 例：group1/M00/00/02/wKgTBF2LHGmAZAmVAANGEWMG1WU676.jpg
     */
    public String upload(byte[] bytes, String fileExtName){
        StorePath storePath = client.uploadFile(DEFAULT_GROUPNAME, new ByteArrayInputStream(bytes), bytes.length, fileExtName);
        return storePath.getFullPath();
    }

}
