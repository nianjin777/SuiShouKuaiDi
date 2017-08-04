package com.edu.wmhxa.sskd.control;

import android.util.Log;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/7/30.
 */

public class HttpControl {
    private static String serverPath = "http://192.168.1.11:8080/KuaiShou/";

    public JSONObject postMethod(final JSONObject obj, final String path) {
        ByteArrayOutputStream bos = null;
        HttpURLConnection conn = null;
        JSONObject result = null;
        try {
            conn = (HttpURLConnection) (new URL(serverPath + path).openConnection());
            //设置延时
            conn.setReadTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStream out = conn.getOutputStream();
            out.write(obj.toString().getBytes());
            int responseCode = conn.getResponseCode();
            Log.i("MsgCenter", "responseCode:" + responseCode);
            if (responseCode == 200) {
                InputStream in = conn.getInputStream();
                bos = new ByteArrayOutputStream();
                int len = 0;
                byte[] buffer = new byte[1024];
                while ((len = in.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                result = new JSONObject(bos.toString());
                bos.flush();
                in.close();
                bos.close();
            } else {
                return null;
//                throw new Exception("服务器正忙！请稍后再试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
