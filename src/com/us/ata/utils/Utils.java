package com.us.ata.utils;

import android.app.Activity;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.us.ata.ormlite.DatabaseHelper;

/**
 * User: khiemvx
 * Date: 8/23/14
 */
public class Utils
{
    protected DatabaseHelper getHelper(Activity activity)
    {
        return (DatabaseHelper) OpenHelperManager.getHelper(activity, DatabaseHelper.class);
    }
}
