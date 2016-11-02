package com.yuyh.github.ui.repos.code;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;

import com.yuyh.easyadapter.abslistview.EasyLVAdapter;
import com.yuyh.easyadapter.abslistview.EasyLVHolder;
import com.yuyh.github.R;
import com.yuyh.github.support.FullTree;
import com.yuyh.github.utils.ScreenUtils;
import com.yuyh.github.utils.TypefaceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public class CodeListAdapter extends EasyLVAdapter<Object> {

    private static final int INDENTED_PADDING = 16;

    private final int indentedPaddingLeft;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int paddingBottom;

    private boolean indented;

    public CodeListAdapter(Context context, List<Object> list) {
        super(context, list, R.layout.item_code_folder, R.layout.item_code_file);
        indentedPaddingLeft = ScreenUtils.getIntPixels(mContext.getResources(), INDENTED_PADDING);
    }

    @Override
    public void convert(EasyLVHolder holder, int position, Object item) {

        View view = holder.getConvertView();
        paddingLeft = view.getPaddingLeft();
        paddingRight = view.getPaddingRight();
        paddingTop = view.getPaddingTop();
        paddingBottom = view.getPaddingBottom();

        if (indented) {
            view.setPadding(indentedPaddingLeft, paddingTop,
                    paddingRight, paddingBottom);
        } else {
            view.setPadding(paddingLeft, paddingTop, paddingRight,
                    paddingBottom);
        }

        if (item instanceof FullTree.Folder) {
            TypefaceUtils.setOcticons((TextView) holder.getView(R.id.tv_folder_icon),
                    (TextView) holder.getView(R.id.tv_folders_icon),
                    (TextView) holder.getView(R.id.tv_files_icon));

            FullTree.Folder folder = (FullTree.Folder) item;
            holder.setText(R.id.tv_folder, folder.name)
                    .setText(R.id.tv_folders, folder.folders.size() + "")
                    .setText(R.id.tv_files, folder.files.size() + "");
        } else {
            TypefaceUtils.setOcticons((TextView) holder.getView(R.id.tv_file_icon));

            FullTree.Entry file = (FullTree.Entry) item;
            holder.setText(R.id.tv_file, file.name)
                    .setText(R.id.tv_size, Formatter.formatFileSize(mContext, file.entry.size));
        }
    }

    @Override
    public int getLayoutIndex(int position, Object item) {
        if (item instanceof FullTree.Folder)
            return 0;
        else
            return 1;
    }

    public void setIndented(boolean indented) {
        this.indented = indented;
    }

    /**
     * Set root folder to display
     *
     * @param root
     */
    public void setItems(final FullTree.Folder root) {
        clear();

        addAll(new ArrayList<Object>(root.folders.values()));
        addAll(new ArrayList<Object>(root.files.values()));

        notifyDataSetChanged();
    }
}
