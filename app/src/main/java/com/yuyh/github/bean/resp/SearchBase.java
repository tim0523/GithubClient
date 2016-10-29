package com.yuyh.github.bean.resp;

import com.google.gson.annotations.SerializedName;

public class SearchBase {

    @SerializedName("total_count")
    public int totalCount;
    @SerializedName("incomplete_results")
    public boolean incompleteResults;
}