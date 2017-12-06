package com.halfopen.iwash.hook;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * Created by h on 17-12-6.
 */

public class LaundryMachineHook implements Hook {
    @Override
    public void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        Class<?> LaundryMachine = findClass("com.eg.laundry.types.LaundryMachine", lpparam.classLoader);
        XposedBridge.log("myiwash" + "LaundryMachine" + LaundryMachine);


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
                param.setResult(0.01D);
            }
        });
        XposedHelpers.findAndHookMethod(LaundryMachine, "getPrice2", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(0.01D);
            }
        });
        XposedHelpers.findAndHookMethod(LaundryMachine, "getPrice3", new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                param.setResult(0.01D);
            }
        });
    }
}
