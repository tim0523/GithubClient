package com.yuyh.github.bean.resp;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class CompareCommit {
    public String url;
    public String html_url;
    public String permalink_url;
    public String diff_url;
    public String patch_url;
    public Commit base_commit;
    public Commit merge_base_commit;
    public String status;
    public int ahead_by;
    public int behind_by;
    public int total_commits;
    public List<Commit> commits;
    public List<CommitFile> files;
}