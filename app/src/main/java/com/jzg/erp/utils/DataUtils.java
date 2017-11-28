package com.jzg.erp.utils;

import com.jzg.erp.appraiser.model.ParamOption;

import java.util.List;

/**
 * Created by zealjiang on 2016/8/16 11:13.
 * Email: zealjiang@126.com
 */
public class DataUtils {

    
    /**
     * 将List数组中Item对象的Name值保存到String[]中
     * @author zealjiang
     * @time 2016/8/16 11:24
     */
    public static String[] getListItemNameArray(List<ParamOption.DataBean.KeyValueItem> list){
        String[] array = new String[list.size()];
        for (int i=0;i<list.size();i++) {
            array[i] = list.get(i).getName();
        }
        return array;
    }

    /**
     * 获得对应的ParamOption中Name对应的Id
     * @author zealjiang
     * @time 2016/8/16 13:49
     */
    public static int getParamOpIdByName(List<ParamOption.DataBean.KeyValueItem> list,String name){
        if(name==null||list==null){
            return 0;
        }
        int id;
        for (ParamOption.DataBean.KeyValueItem item:list) {
            if(name.equals(item.getName())){
                id = item.getId();
                return id;
            }
        }
        return 0;
    }

    /**
     * 获得对应的ParamOption中Id对应的Name
     * @author zealjiang
     * @time 2016/8/16 13:53
     */
    public static String getParamOpNameById(List<ParamOption.DataBean.KeyValueItem> list,int id){
        if(id==0||list==null){
            return "";
        }
        String name;
        for (ParamOption.DataBean.KeyValueItem item:list) {
            if(id==item.getId()){
                name = item.getName()+"";
                return name;
            }
        }
        return "";
    }
}
