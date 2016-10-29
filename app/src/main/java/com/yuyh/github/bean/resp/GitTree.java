package com.yuyh.github.bean.resp;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class GitTree extends ShaUrl {

  public List<GitTreeEntry> tree;
  public boolean truncated;
}