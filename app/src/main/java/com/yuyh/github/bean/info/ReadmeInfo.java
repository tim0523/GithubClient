package com.yuyh.github.bean.info;

import com.yuyh.github.bean.resp.Links;

/**
 * @author yuyh.
 * @date 2016/11/1.
 */
public class ReadmeInfo {

    /**
     * name : README.md
     * path : README.md
     * sha : 27486378993c3cfc63da7de674f514771f275294
     * size : 2634
     * url : https://api.github.com/repos/smuyyh/ImageSelector/contents/README.md?ref=master
     * html_url : https://github.com/smuyyh/ImageSelector/blob/master/README.md
     * git_url : https://api.github.com/repos/smuyyh/ImageSelector/git/blobs/27486378993c3cfc63da7de674f514771f275294
     * download_url : https://raw.githubusercontent.com/smuyyh/ImageSelector/master/README.md
     * type : file
     * content : mmmm
     * encoding : base64
     * _links : {"self":"https://api.github.com/repos/smuyyh/ImageSelector/contents/README.md?ref=master","git":"https://api.github.com/repos/smuyyh/ImageSelector/git/blobs/27486378993c3cfc63da7de674f514771f275294","html":"https://github.com/smuyyh/ImageSelector/blob/master/README.md"}
     */

    public String name;
    public String path;
    public String sha;
    public int size;
    public String url;
    public String html_url;
    public String git_url;
    public String download_url;
    public String type;
    public String content;
    public String encoding;
    /**
     * self : https://api.github.com/repos/smuyyh/ImageSelector/contents/README.md?ref=master
     * git : https://api.github.com/repos/smuyyh/ImageSelector/git/blobs/27486378993c3cfc63da7de674f514771f275294
     * html : https://github.com/smuyyh/ImageSelector/blob/master/README.md
     */
    public Links _links;
}
