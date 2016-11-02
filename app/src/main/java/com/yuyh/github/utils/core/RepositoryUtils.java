package com.yuyh.github.utils.core;

import android.text.TextUtils;

import com.yuyh.github.bean.resp.Repo;

/**
 * Utilities for working with {@link Repo} objects
 */
public class RepositoryUtils {

    /**
     * Does the repository have details denoting it was loaded from an API call?
     * <p>
     * This uses a simple heuristic of either being private, being a fork, or
     * having a non-zero amount of forks or watchers, or has issues enable;
     * meaning it came back from an API call providing those details and more.
     *
     * @param repository
     * @return true if complete, false otherwise
     *
     */
    public static boolean isComplete(final Repo repository) {
        return repository.isPrivate || repository.fork
                || repository.forks_count > 0 || repository.watchers_count > 0
                || repository.has_issues;
    }

    /**
     * Is the given owner name valid?
     *
     * @param name
     * @return true if valid, false otherwise
     */
    public static boolean isValidOwner(final String name) {
        if (TextUtils.isEmpty(name))
            return false;

        return !("about".equals(name) //
                || "account".equals(name) //
                || "admin".equals(name) //
                || "api".equals(name) //
                || "blog".equals(name) //
                || "camo".equals(name) //
                || "contact".equals(name) //
                || "dashboard".equals(name) //
                || "downloads".equals(name) //
                || "edu".equals(name) //
                || "explore".equals(name) //
                || "features".equals(name) //
                || "home".equals(name) //
                || "inbox".equals(name) //
                || "languages".equals(name) //
                || "login".equals(name) //
                || "logout".equals(name) //
                || "new".equals(name) //
                || "notifications".equals(name) //
                || "organizations".equals(name) //
                || "orgs".equals(name) //
                || "repositories".equals(name) //
                || "search".equals(name) //
                || "security".equals(name) //
                || "settings".equals(name) //
                || "stars".equals(name) //
                || "styleguide".equals(name) //
                || "timeline".equals(name) //
                || "training".equals(name) //
                || "users".equals(name) //
                || "watching".equals(name));
    }

    /**
     * Is the given repo name valid?
     *
     * @param name
     * @return true if valid, false otherwise
     */
    public static boolean isValidRepo(final String name) {
        if (TextUtils.isEmpty(name))
            return false;

        return !("followers".equals(name) || "following".equals(name));
    }
}