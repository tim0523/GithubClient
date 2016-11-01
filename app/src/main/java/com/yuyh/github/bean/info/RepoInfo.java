package com.yuyh.github.bean.info;

import java.io.Serializable;
import java.security.Permissions;

public class RepoInfo implements Serializable {
    public String owner;
    public String name;
    public String branch;
    public Permissions permissions = new Permissions();
}