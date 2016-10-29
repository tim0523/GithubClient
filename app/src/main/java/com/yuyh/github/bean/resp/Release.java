package com.yuyh.github.bean.resp;

import java.util.Date;
import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class Release {

    public String body;
    public String upload_url;
    public String assets_url;
    public String tag_name;
    public String url;
    public String published_at;
    public String html_url;
    public String id;
    public String target_commitish;
    public List<ReleaseAsset> assets;
    public boolean draft;
    public User author;
    public String zipball_url;
    public boolean prerelease;
    public String tarball_url;
    public String name;
    public Date created_at;
}