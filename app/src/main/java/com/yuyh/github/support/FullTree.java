package com.yuyh.github.support;

import android.text.TextUtils;

import com.yuyh.github.bean.resp.GitReference;
import com.yuyh.github.bean.resp.GitTree;
import com.yuyh.github.bean.resp.GitTreeEntry;
import com.yuyh.github.bean.support.GitTreeType;
import com.yuyh.github.utils.core.CommitUtils;
import com.yuyh.github.utils.core.RefUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.String.CASE_INSENSITIVE_ORDER;

public class FullTree {

    /**
     * Entry in a tree
     */
    public static class Entry implements Comparable<Entry> {

        /**
         * Parent folder
         */
        public final Folder parent;

        /**
         * Raw tree entry
         */
        public final GitTreeEntry entry;

        /**
         * Name
         */
        public final String name;

        private Entry() {
            this.parent = null;
            this.entry = null;
            this.name = null;
        }

        private Entry(GitTreeEntry entry, Folder parent) {
            this.entry = entry;
            this.parent = parent;
            this.name = CommitUtils.getName(entry.path);
        }

        @Override
        public int compareTo(Entry another) {
            return CASE_INSENSITIVE_ORDER.compare(name, another.name);
        }
    }

    /**
     * Folder in a tree
     */
    public static class Folder extends Entry {

        /**
         * Sub folders
         */
        public final Map<String, Folder> folders = new TreeMap<>(
                CASE_INSENSITIVE_ORDER);

        /**
         * Files
         */
        public final Map<String, Entry> files = new TreeMap<>(
                CASE_INSENSITIVE_ORDER);

        private Folder() {
            super();
        }

        private Folder(GitTreeEntry entry, Folder parent) {
            super(entry, parent);
        }

        private void addFile(GitTreeEntry entry, String[] pathSegments, int index) {
            if (index == pathSegments.length - 1) {
                Entry file = new Entry(entry, this);
                files.put(file.name, file);
            } else {
                Folder folder = folders.get(pathSegments[index]);
                if (folder != null)
                    folder.addFile(entry, pathSegments, index + 1);
            }
        }

        private void addFolder(GitTreeEntry entry, String[] pathSegments, int index) {
            if (index == pathSegments.length - 1) {
                Folder folder = new Folder(entry, this);
                folders.put(folder.name, folder);
            } else {
                Folder folder = folders.get(pathSegments[index]);
                if (folder != null)
                    folder.addFolder(entry, pathSegments, index + 1);
            }
        }

        private void add(final GitTreeEntry entry) {
            String path = entry.path;
            if (TextUtils.isEmpty(path))
                return;

            if (entry.type == GitTreeType.blob) {
                String[] segments = path.split("/");
                if (segments.length > 1) {
                    Folder folder = folders.get(segments[0]);
                    if (folder != null)
                        folder.addFile(entry, segments, 1);
                } else if (segments.length == 1) {
                    Entry file = new Entry(entry, this);
                    files.put(file.name, file);
                }
            } else if (entry.type == GitTreeType.tree) {
                String[] segments = path.split("/");
                if (segments.length > 1) {
                    Folder folder = folders.get(segments[0]);
                    if (folder != null)
                        folder.addFolder(entry, segments, 1);
                } else if (segments.length == 1) {
                    Folder folder = new Folder(entry, this);
                    folders.put(folder.name, folder);
                }
            }
        }
    }

    /**
     * Tree
     */
    public final GitTree tree;

    /**
     * Root folder
     */
    public final Folder root;

    /**
     * Reference
     */
    public final GitReference reference;

    /**
     * Branch where tree is present
     */
    public final String branch;

    /**
     * Create tree with branch
     *
     * @param tree
     * @param reference
     */
    public FullTree(final GitTree tree, final GitReference reference) {
        this.tree = tree;
        this.reference = reference;
        this.branch = RefUtils.getName(reference);

        root = new Folder();
        List<GitTreeEntry> entries = tree.tree;
        if (entries != null && !entries.isEmpty())
            for (GitTreeEntry entry : entries) {
                root.add(entry);
            }
    }
}