package com.dynamic.config.core.file;

import com.dynamic.config.constant.CommonConstants;
import com.dynamic.config.core.file.VersionInfo;
import com.dynamic.config.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gangwen.xu
 * Date: 17-7-18
 * Time: 下午4:27
 * 类描述:文件存储相关
 */
public class FileStore {
    private static final Logger logger = LoggerFactory.getLogger(FileStore.class);
    private static final File CONF_FILE_NAME= new File("dynamicconfig");

    /**
     * 获取版本描述文件-profile关联map
     * @return
     */
   public static Map<FileInfo, VersionInfo> getAllFiles() {
        Map<FileInfo, VersionInfo> fvMap = new HashMap<FileInfo, VersionInfo>();
        for (File f : CONF_FILE_NAME.listFiles()) {
            if (!f.isDirectory()) {continue;}
            String groupName = f.getName();
            for (File cf : f.listFiles()) {
                String cfName = cf.getName();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(cf), CommonConstants.UTF_8));
                    String line = bufferedReader.readLine();
                    String[] strings = line.split(",");
                    VersionInfo vi = new VersionInfo(NumberUtils.toLong(strings[0]), strings[1]);
                    FileInfo fileInfo = new FileInfo(cfName, groupName);
                    fvMap.put(fileInfo, vi);
                } catch (IOException e) {
                    logger.error("deal_file_error", e);
                }
            }
        }
        return fvMap;
    }

}
