package com.dynamic.config.core.file;

/**
 * Created by IntelliJ IDEA.
 * User: gangwen.xu
 * Date: 17-7-18
 * Time: 下午5:51
 * 类描述:
 */
public class VersionInfo {
    private long version;
    private String profile;//对应到maven-

    VersionInfo(long version, String profile){
        this.version = version;
        this.profile = profile;
    }

}
