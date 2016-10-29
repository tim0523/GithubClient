package com.yuyh.github.bean.resp;

import java.io.File;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class CommitFile extends GitChangeStatus {

    public String filename;
    public String status;
    public String raw_url;
    public String blob_url;
    public String patch;
    public String sha;

    public String getFileName() {
        if (filename != null) {
            String[] names = filename.split(File.separator);
            if (names.length > 1) {
                int last = names.length - 1;
                return names[last];
            } else {
                return names[0];
            }
        }

        return null;
    }
}