package com.yuyh.github.ui.home.followers;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;
import com.yuyh.github.R;
import com.yuyh.github.bean.resp.User;
import com.yuyh.github.utils.ScreenUtils;
import com.yuyh.github.utils.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/30.
 */
public class FollowersAdapter extends EasyRVAdapter<User> {

    public FollowersAdapter(Context context, List<User> list) {
        super(context, list, R.layout.item_home_followers);
    }

    @Override
    protected void onBindData(EasyRVHolder viewHolder, int position, User item) {
        viewHolder.setText(R.id.mTvFollowerAccount, item.login);

        Glide.with(mContext).load(item.avatar_url)
                .override(ScreenUtils.dpToPxInt(50), ScreenUtils.dpToPxInt(50))
                .placeholder(R.mipmap.ic_default_avatar)
                .transform(new GlideRoundTransform(mContext))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into((ImageView) viewHolder.getView(R.id.mIvAvatar));

    }
}
