package com.halfopen.iwash.hook;

import android.content.Context;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * Created by qxk on 2017/12/3.
 */

public class MainHook  implements IXposedHookLoadPackage ,IXposedHookZygoteInit, IXposedHookInitPackageResources {

    public static Context applicationContext=null;

    /**
     * 加载时
     * @param lpparam
     * @throws Throwable
     */
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("Loaded app: " + lpparam.packageName);

        if (lpparam.packageName.equals("com.aijie.xidi")) {

            try {
                Class<?> ContextClass = findClass("android.content.ContextWrapper", lpparam.classLoader);
                findAndHookMethod(ContextClass, "getApplicationContext", new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        if (applicationContext != null)
                            return;
                        applicationContext = (Context) param.getResult();
                        XposedBridge.log("CSDN_LQR-->得到上下文");
                    }
                });
            } catch (Throwable t) {
                XposedBridge.log("CSDN_LQR-->获取上下文出错");
                XposedBridge.log(t);
            }


            //new AppContextHook().hook(lpparam);
            new HttpHook().hook(lpparam);

        }

        if( lpparam.packageName.equals("com.eg.laundry")){
            //new LaundryMachineHook().hook(lpparam);
        }

    }

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam resparam) throws Throwable {

        /**
         * <string name="wdzl">我的资料</string>
         <string name="wdzh">我的账户</string>
         <string name="tsjy">投诉建议</string>
         */
        XposedBridge.log("handleInitPackageResources"+resparam.packageName);
        // 修改标题
        if (resparam.packageName.equals("com.aijie.xidi")){
            XposedBridge.log("iwash_hook:repalce string");
            resparam.res.setReplacement("com.aijie.xidi", "string", "app_name", "iwash已修改");
            resparam.res.setReplacement("com.aijie.xidi", "string", "wdzl", "我没有资料");
            resparam.res.setReplacement("com.aijie.xidi", "string", "wdzh", "我没有账户");
            resparam.res.setReplacement("com.aijie.xidi", "string", "tsjy", "我不投诉");
            resparam.res.setReplacement("com.aijie.xidi", "string", "tcdl", "logout");
        }
    }

    @Override
    public void initZygote(StartupParam startupParam) throws Throwable {

    }
}
