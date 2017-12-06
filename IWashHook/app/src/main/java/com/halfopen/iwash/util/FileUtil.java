package com.halfopen.iwash.util;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.halfopen.iwash.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by h on 17-12-6.
 */

public class FileUtil {
    final File sdcard = Environment.getExternalStorageDirectory();
    String fileName="iwash_log.txt";

    Context context;

    public FileUtil(Context context) {
        this.context = context;
    }

    public void write(String content){
        File myFile = new File(sdcard, fileName);
        if (!sdcard.exists()) {
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(myFile, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "utf-8");
            osw.write(content+"\n");
            osw.flush();
            fos.flush();
            osw.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read(){
        File myfile = new File(sdcard, fileName);
        if (myfile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(myfile);
                InputStreamReader isr = new InputStreamReader(fis, "utf-8");
                char input[] = new char[fis.available()];
                isr.read(input);
                String str = new String(input);
                return str;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return "";
    }
}
