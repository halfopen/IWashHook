package com.halfopen.iwash.hook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * Created by h on 17-12-6.
 */

public class AppContextHook implements Hook{
    @Override
    public void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        XposedBridge.log("----------------------------iwash loaded--------------------------");

        Class<?> APPContext = findClass("com.aijie.xidi.activity.base.APPContext", lpparam.classLoader);
        XposedBridge.log("myiwash" + "appcontext" + APPContext);
        /**
         * A 方法是从preference读取余额的方法
         */
        XposedHelpers.findAndHookMethod(APPContext, "A", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult("8.00");
            }
        });

        XposedHelpers.findAndHookMethod(APPContext, "B", new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(8.00D);
            }
        });

        XposedHelpers.findAndHookMethod(APPContext, "C", new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(8.00D);
            }
        });
        XposedHelpers.findAndHookMethod(APPContext, "D", new XC_MethodHook() {

            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult("8.00");
            }
        });
    }
}
