package com.yuyh.github.utils.core;

import android.text.TextUtils;

import com.yuyh.github.bean.resp.GitReference;

/**
 * Utilities for working with {@link GitReference}s
 */
public class RefUtils {

    private static final String PREFIX_REFS = "refs/";

    private static final String PREFIX_PULL = PREFIX_REFS + "pull/";

    private static final String PREFIX_TAG = PREFIX_REFS + "tags/";

    private static final String PREFIX_HEADS = PREFIX_REFS + "heads/";

    /**
     * Is reference a branch?
     *
     * @param ref
     * @return true if branch, false otherwise
     */
    public static boolean isBranch(final GitReference ref) {
        if (ref != null) {
            String name = ref.ref;
            return !TextUtils.isEmpty(name) && name.startsWith(PREFIX_HEADS);
        } else
            return false;
    }

    /**
     * Is reference a tag?
     *
     * @param ref
     * @return true if tag, false otherwise
     */
    public static boolean isTag(final GitReference ref) {
        return ref != null && isTag(ref.ref);
    }

    /**
     * Is reference a tag?
     *
     * @param name
     * @return true if tag, false otherwise
     */
    public static boolean isTag(final String name) {
        return !TextUtils.isEmpty(name) && name.startsWith(PREFIX_TAG);
    }

    /**
     * Get path of ref with leading 'refs/' segment removed if present
     *
     * @param ref
     * @return full path
     */
    public static String getPath(final GitReference ref) {
        if (ref == null)
            return null;
        String name = ref.ref;
        if (!TextUtils.isEmpty(name) && name.startsWith(PREFIX_REFS))
            return name.substring(PREFIX_REFS.length());
        else
            return name;
    }

    /**
     * Get short name for ref
     *
     * @param ref
     * @return short name
     */
    public static String getName(final GitReference ref) {
        if (ref != null)
            return getName(ref.ref);
        else
            return null;
    }

    /**
     * Get short name for ref
     *
     * @param name
     * @return short name
     */
    public static String getName(final String name) {
        if (TextUtils.isEmpty(name))
            return name;
        if (name.startsWith(PREFIX_HEADS))
            return name.substring(PREFIX_HEADS.length());
        else if (name.startsWith(PREFIX_TAG))
            return name.substring(PREFIX_TAG.length());
        else if (name.startsWith(PREFIX_REFS))
            return name.substring(PREFIX_REFS.length());
        else
            return name;
    }

    /**
     * Should the given reference be included as valid?
     * <p>
     * This filters out pull request refs
     *
     * @param ref
     * @return true if valid, false otherwise
     */
    public static boolean isValid(final GitReference ref) {
        if (ref == null)
            return false;

        String name = ref.ref;
        return !TextUtils.isEmpty(name) && !name.startsWith(PREFIX_PULL);
    }
}