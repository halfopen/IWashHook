package com.halfopen.iwash.hook;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by h on 17-12-6.
 */

public interface Hook {
    void hook(XC_LoadPackage.LoadPackageParam lpparam);
}
