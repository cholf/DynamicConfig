package com.dynamic.config.core;

import com.dynamic.config.core.file.FileInfo;
import com.dynamic.config.core.file.FileStore;
import com.dynamic.config.core.file.VersionInfo;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: gangwen.xu
 * Date: 17-7-18
 * Time: 下午8:17
 * 类描述:
 */
public class LoadData {
    static {

        Map<FileInfo, VersionInfo> map = FileStore.getAllFiles();
        for (Map.Entry<FileInfo, VersionInfo> entry : map.entrySet()) {

        }

    }
    
}
