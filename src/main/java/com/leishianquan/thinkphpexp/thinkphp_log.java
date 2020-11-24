package com.leishianquan.thinkphpexp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

public class thinkphp_log implements BasePayload {

    public boolean checkVUL(String url) throws Exception{
        try {
            String test_result = HttpTool.getHttpReuest(url + "/runtime/log/20_11_11.log","utf-8");

            if (test_result.length()>500){
                return true;}
        }  catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
        }
        return false;
    }


}