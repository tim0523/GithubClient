package com.yuyh.github.bean.resp;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class ReleaseAsset {
    public String url;
    public String browser_download_url;
    public int id;
    public String name;
    public String labnel;
    public String state;
    public String content_type;
    public long size = 0;
    public int download_count;
    public String created_at;
    public String updated_at;
    public User uploader;
}