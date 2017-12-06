package com.halfopen.iwash.hook;

import android.app.Activity;

import com.halfopen.iwash.util.FileUtil;

import org.json.JSONObject;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
import static de.robv.android.xposed.XposedHelpers.findClass;

/**
 * Created by h on 17-12-6.
 */

public class HttpHook implements Hook{
    public String[] classNames = {"activity.base.ab","activity.base.ak","activity.base.al","activity.base.x","activity.base.y","activity.base.z","activity.ac","activity.ad","activity.am","activity.az","activity.b","activity.bb","activity.be","activity.bg","activity.bj","activity.bk","activity.bl","activity.bn","activity.bo","activity.ce","activity.g","activity.n","activity.o","activity.p","activity.q","activity.w","activity.x","adapter.ab","adapter.af","adapter.aq","adapter.d","adapter.i","adapter.s","fragment.a","fragment.ab","fragment.ag","fragment.ah","fragment.ai","fragment.al","fragment.ao","fragment.ap","fragment.as","fragment.au","fragment.ax","fragment.ay","fragment.bc","fragment.br","fragment.bs","fragment.bt","fragment.bu","fragment.bx","fragment.ca","fragment.cd","fragment.ce","fragment.cg","fragment.cj","fragment.co","fragment.cq","fragment.ct","fragment.cu","fragment.cw","fragment.db","fragment.dg","fragment.dm","fragment.dy","fragment.dz","fragment.e","fragment.ec","fragment.ed","fragment.eh","fragment.eo","fragment.g","fragment.i","fragment.k","fragment.m","fragment.o","fragment.t","fragment.u","fragment.v","fragment.z","util.sort.c"};

    @Override
    public void hook(XC_LoadPackage.LoadPackageParam lpparam) {


        Class<?> a = findClass("av.c", lpparam.classLoader);
        Class<?> d = findClass("av.d", lpparam.classLoader);
        XposedBridge.log("myiwash av.a"+a);
        XposedBridge.log("myiwash av.a"+d);

        for (String name:classNames){
            try {
                final String className = "com.aijie.xidi."+name;
                Class<?> cls = lpparam.classLoader.loadClass(className);
                XposedBridge.log("myiwash try hook"+cls);
                findAndHookMethod(cls, "a", String.class, JSONObject.class, d, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        String url;
                        JSONObject jsonObject;

                        url = (String) param.args[0];
                        jsonObject = (JSONObject) param.args[1];

                        XposedBridge.log("myiwash +url"+url+" "+jsonObject);

                        new FileUtil(MainHook.applicationContext).write(url+":"+jsonObject);

                        if(url.equals("http://app.eglaundry.com/v32/Index/user_sel")){
                            jsonObject = new JSONObject("{\"msg\":\"OK\",\"status\":1,\"data\":{\"id\":\"177020\",\"nick\":\"\",\"user_img\":\"http:\\/\\/app.eglaundry.com\\/\\/upload\\/avater\\/d77498a4b106be01a9d47e6386f0eff7.jpg\",\"sex\":\"女\",\"address\":\"\",\"sign\":\"这个人很懒，什么也没有留下\",\"phone\":\"15527039473\",\"email\":\"\",\"birthday\":\"1997-12-18\"}}");
                        }

                        if(url.equals("http://app.eglaundry.com/v32/Index/my_ticket")){
                            jsonObject = new JSONObject("{\"ticketsum\":\"20.00\",\"freesum\":\"20.00\",\"freetime\":\"20\",\"content\":\"\",\"type\":\"1\"}");
                        }

                        if(url.equals("http://app.eglaundry.com/v32/Spcmd/decryption1")){
                            jsonObject = new JSONObject("{\"code\":\"1\",\"msg\":\"Success\",\"data\":{\"herdata\":\"7E1E969BD402B7E9AC7A7AB5D96CBABD089A540D\",\"price\":0}}");
                        }

                        if(url.equals("http://app.eglaundry.com/v32/Spcmd/decryption2")){
                            jsonObject = new JSONObject("{\"code\":\"1\",\"msg\":\"Success\",\"data\":{\"herdata\":\"7E194B4F9546B0FED4291DCADBB1A088EC67270D\"}}");
                        }

                        if(url.equals("http://app.eglaundry.com/v32/Spcmd/decryption3")){
                            jsonObject = new JSONObject("{\"code\":\"1\",\"msg\":\"ok\",\"data\":[]}");
                        }

                        jsonObject.put("code", "1");

                        param.args[1] = jsonObject;


                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);

                        String url;
                        JSONObject jsonObject;

                        url = (String) param.args[0];
                        jsonObject = (JSONObject) param.args[1];
                        new FileUtil(MainHook.applicationContext).write("ater>"+url+":"+jsonObject);
                    }
                });
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
