package com.yuyh.github.bean.support;

import java.io.Serializable;

public class ActionEventPayload extends GithubEventPayload implements Serializable {
  public String action;
}