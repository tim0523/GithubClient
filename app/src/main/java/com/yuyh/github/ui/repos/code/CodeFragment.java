package com.yuyh.github.ui.repos.code;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseLazyFragment;
import com.yuyh.github.bean.resp.GitReference;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.support.FullTree;
import com.yuyh.github.utils.core.RefUtils;
import com.yuyh.github.utils.StyledText;
import com.yuyh.github.utils.TypefaceUtils;

import java.util.ArrayList;
import java.util.LinkedList;

import butterknife.Bind;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public class CodeFragment extends BaseLazyFragment
        implements CodeContract.View, AdapterView.OnItemClickListener {

    public static final String BUNDLE_REPO = "repo";

    public static CodeFragment instance(Repo repo) {
        CodeFragment fragment = new CodeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_REPO, repo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Bind(R.id.lvCodeTree)
    ListView mLvCodeTree;
    @Bind(R.id.progressBar)
    ProgressBar mProgressBar;
    @Bind(R.id.rl_branch)
    View mRlBranch;
    @Bind(R.id.tvBranchIcon)
    TextView mTvBranchIcon;
    @Bind(R.id.tvBranch)
    TextView mTvBranch;

    private View mPathHeader;
    private TextView mTvPath;

    private Repo repo;
    private FullTree.Folder folder;
    private FullTree tree;
    private CodeListAdapter mAdapter;

    private CodePresenter mPresenter;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_repo_code);

        repo = (Repo) getArguments().getSerializable(BUNDLE_REPO);

        mPresenter = new CodePresenter(this);

        mPathHeader = LayoutInflater.from(mActivity).inflate(R.layout.item_code_path, null);
        mTvPath = (TextView) mPathHeader.findViewById(R.id.tv_path);
        mTvPath.setMovementMethod(LinkMovementMethod.getInstance());

        mLvCodeTree.setAdapter(mAdapter = new CodeListAdapter(mActivity, new ArrayList<>()));
        mLvCodeTree.setOnItemClickListener(this);

        TypefaceUtils.setOcticons(mTvBranchIcon, (TextView) mPathHeader.findViewById(R.id.tv_folder_icon));

        if (tree == null || folder == null) {
            refreshTree(null);
        } else {
            setFolder(tree, folder);
        }
    }

    private void refreshTree(GitReference reference) {
        showLoadding();
        mPresenter.refreshTree(repo, reference);
    }

    @Override
    public void renderFullTree(FullTree fullTree) {
        if (folder == null || folder.parent == null) {
            setFolder(fullTree, fullTree.root);
        } else {
            // Look for current folder in new tree or else reset to root
            FullTree.Folder current = folder;
            LinkedList<FullTree.Folder> stack = new LinkedList<>();
            while (current.parent != null) {
                stack.addFirst(current);
                current = current.parent;
            }
            FullTree.Folder refreshed = fullTree.root;
            while (!stack.isEmpty()) {
                refreshed = refreshed.folders
                        .get(stack.removeFirst().name);
                if (refreshed == null)
                    break;
            }
            if (refreshed != null) {
                setFolder(fullTree, refreshed);
            } else {
                setFolder(fullTree, fullTree.root);
            }
        }
    }

    private void setFolder(final FullTree tree, final FullTree.Folder folder) {
        this.folder = folder;
        this.tree = tree;

        updateBranchView(tree);

        updatePath(tree, folder);

        mAdapter.setIndented(folder.entry != null);
        mAdapter.setItems(folder);
        mLvCodeTree.setSelection(0);

        hideLoadding();
    }

    private void updatePath(final FullTree tree, final FullTree.Folder folder) {
        if (folder.entry != null) {
            int textLightColor = ContextCompat.getColor(mActivity, R.color.text_light);
            final String[] segments = folder.entry.path.split("/");
            StyledText text = new StyledText();
            text.url("..", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FullTree.Folder clicked = folder;
                    for (int i = 0; i < segments.length; i++) {
                        clicked = clicked.parent;
                        if (clicked == null)
                            return;
                    }
                    setFolder(tree, clicked);
                }
            }).append(' ').foreground('/', textLightColor).append(' ');
            for (int i = 0; i < segments.length - 1; i++) {
                final int index = i;
                text.url(segments[i], new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        FullTree.Folder clicked = folder;
                        for (int i = index; i < segments.length - 1; i++) {
                            clicked = clicked.parent;
                            if (clicked == null)
                                return;
                        }
                        setFolder(tree, clicked);
                    }
                }).append(' ').foreground('/', textLightColor).append(' ');
            }
            text.bold(segments[segments.length - 1]);
            mTvPath.setText(text);
            if (mLvCodeTree.getHeaderViewsCount() <= 0) {
                mLvCodeTree.addHeaderView(mPathHeader);
            }
        } else {
            if (mLvCodeTree.getHeaderViewsCount() > 0) {
                mLvCodeTree.removeHeaderView(mPathHeader);
            }
        }
    }

    private void updateBranchView(FullTree tree) {
        mTvBranch.setText(tree.branch);
        mTvBranchIcon.setText(RefUtils.isTag(tree.reference) ?
                R.string.icon_tag : R.string.icon_fork);
        if (mRlBranch.getVisibility() == View.GONE) {
            Animation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                    1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
            mShowAction.setDuration(600);
            mRlBranch.startAnimation(mShowAction);
            mRlBranch.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Back up the currently viewed folder to its parent
     *
     * @return true if directory changed, false otherwise
     */
    public boolean onBackPressed() {
        if (folder != null && folder.parent != null) {
            setFolder(tree, folder.parent);
            return true;
        } else
            return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FullTree.Entry entry = (FullTree.Entry) parent.getItemAtPosition(position);
        if (tree == null || entry == null)
            return;

        if (entry instanceof FullTree.Folder) {
            // Refresh file tree
            setFolder(tree, (FullTree.Folder) entry);
        } else {
            // File preview
        }
    }
}
