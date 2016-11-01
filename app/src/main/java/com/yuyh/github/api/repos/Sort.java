package com.yuyh.github.api.repos;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@StringDef({
        Sort.FULLNAME,
        Sort.CREATED,
        Sort.UPDATED,
        Sort.PUSHED
})

@Retention(RetentionPolicy.SOURCE)
public @interface Sort {
    String FULLNAME = "full_name";

    String CREATED = "created";

    String UPDATED = "updated";

    String PUSHED = "pushed";
}