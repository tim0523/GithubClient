package com.yuyh.github.ui.repos.commit;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;
import com.yuyh.github.R;
import com.yuyh.github.bean.resp.Commit;
import com.yuyh.github.utils.StyledText;
import com.yuyh.github.utils.TypefaceUtils;
import com.yuyh.github.utils.core.CommitUtils;
import com.yuyh.github.utils.glide.GlideRoundTransform;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public class CommitListAdapter extends EasyRVAdapter<Commit> {

    public CommitListAdapter(Context context, List<Commit> list) {
        super(context, list, R.layout.item_repo_commit);
    }

    @Override
    protected void onBindData(EasyRVHolder holder, int position, Commit item) {
        Glide.with(mContext).load(item.author.avatar_url)
                .placeholder(new ColorDrawable(Color.GRAY))
                .transform(new GlideRoundTransform(mContext))
                .into((ImageView) holder.getView(R.id.iv_avatar));

        holder.setText(R.id.tv_commit_id, item.sha)
                .setText(R.id.tv_commit_message, item.commit.message)
                .setText(R.id.tv_commit_comments, CommitUtils.getCommentCount(item));

        StyledText authorText = new StyledText();
        authorText.bold(CommitUtils.getAuthor(item));
        authorText.append(' ');
        authorText.append(CommitUtils.getAuthorDate(item));

        TextView author = holder.getView(R.id.tv_commit_author);
        author.setText(authorText);

        TypefaceUtils.setOcticons((TextView) holder.getView(R.id.tv_comment_icon));
    }
}
