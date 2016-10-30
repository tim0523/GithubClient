package com.yuyh.github.widget.ptr.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.yuyh.github.R;
import com.yuyh.github.utils.ScreenUtils;
import com.yuyh.github.widget.ptr.StoreHouseHeader;

/**
 * @author yuyh.
 * @date 2016/10/30.
 */
public class HeaderUtils {

    public static StoreHouseHeader getPtrHeader(Context context) {
        StoreHouseHeader header = new StoreHouseHeader(context);
        header.setPadding(0, ScreenUtils.dpToPxInt(15), 0, 0);
        header.initWithString("Github");
        header.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        return header;
    }
}
