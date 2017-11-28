package com.jzg.erp.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.Set;

/**
 * Created by zealjiang on 2016/8/3 17:57.
 * Email: zealjiang@126.com
 */
public class MTextUtils{

    public static String nullIfEmpty(@Nullable String str) {
        return TextUtils.isEmpty(str) ? null : str;
    }

    public static String empty(String str) {
        return TextUtils.isEmpty(str) ? "" : (str.toLowerCase().equals("null") ? "" : str);
    }

    /**
     * http://dafd/fdf/123.jpg -------->123.jpg
     */
    public static String getName4Img(String imgpath){
        String path = "";
        if("".equals(imgpath) || null == imgpath){
            path = "";
        }else{
            if(imgpath.indexOf("/") != -1){
                path = imgpath.substring(imgpath.lastIndexOf("/")+1);
            }
        }
        return path;
    }

    public static final String[] set2Array(Set<String> set){
        return set.toArray(new String[]{});
    }

    public static String[] split(String src){
        String[] result = new String[]{"",""};
        if(src.length()>0 && src.contains("市")){
            int index = src.indexOf("市");
            String pc = src.substring(0, index+1);
            String detail = src.substring(index+1);
            result[0] = pc;
            result[1] = detail;
        }
        return result;
    }



}
