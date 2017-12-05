package com.halfopen.iwash.hook;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * Created by qxk on 2017/12/3.
 */

public class MainHook  implements IXposedHookLoadPackage ,IXposedHookZygoteInit, IXposedHookInitPackageResources {


    /**
     * 加载时
     * @param lpparam
     * @throws Throwable
     */
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("Loaded app: " + lpparam.packageName);

        if (lpparam.packageName.equals("com.aijie.xidi")) {

            XposedBridge.log("----------------------------iwash loaded--------------------------");

            Class<?> APPContext = findClass("com.aijie.xidi.activity.base.APPContext", lpparam.classLoader);
            XposedBridge.log("myiwash" + "appcontext" + APPContext);
            /**
             * A 方法是从preference读取余额的方法
             */
            XposedHelpers.findAndHookMethod(APPContext, "A", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("myiwash" + "resume");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("myiwash" + "after resume");
                    String result = (String) param.getResult();
                    XposedBridge.log("myiwash" + "after resume result" + result);
                    // 修改返回结果
                    param.setResult("8.00");
                }
            });
        }

        if( lpparam.packageName.equals("com.eg.laundry")){
            Class<?> LaundryMachine = findClass("com.eg.laundry.types.LaundryMachine", lpparam.classLoader);
            XposedBridge.log("myiwash" + "LaundryMachine" + LaundryMachine);

            XposedHelpers.findAndHookMethod(LaundryMachine, "isAuthened", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    // 修改返回结果
                    param.setResult(true);
                }
            });

            XposedHelpers.findAndHookMethod(LaundryMachine, "isPayedForWash", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.setResult(true);
                }
            });

            XposedHelpers.findAndHookMethod(LaundryMachine, "isPayedForDry", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.setResult(true);
                }
            });

            XposedHelpers.findAndHookMethod(LaundryMachine, "isPayedForStove", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.setResult(true);
                }
            });

            XposedHelpers.findAndHookMethod(LaundryMachine, "getPrice1", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.setResult(-100.0D);
                }
            });
            XposedHelpers.findAndHookMethod(LaundryMachine, "getPrice2", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.setResult(-100.0D);
                }
            });
            XposedHelpers.findAndHookMethod(LaundryMachine, "getPrice3", new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    param.setResult(-100.0D);
                }
            });
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
