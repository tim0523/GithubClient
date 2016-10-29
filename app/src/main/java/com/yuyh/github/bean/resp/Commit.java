package com.yuyh.github.bean.resp;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class Commit extends ShaUrl {

    private static final int MAX_COMMIT_LENGHT = 80;
    public GitCommit commit;
    public User author;
    public List<ShaUrl> parents;
    public GitChangeStatus stats;
    public User committer;
    public String message;
    public boolean distinct;
    public GitCommitFiles files;
    public int days;
    public int comment_count;
}
