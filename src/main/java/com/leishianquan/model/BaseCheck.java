package com.leishianquan.model;

import com.leishianquan.MainApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BaseCheck implements Runnable{

    public int index=0;
    public String url="";
    public JTable table=null;
    public BasePayload bp=null;
    public void init(BasePayload bp){
        this.bp=bp;
    }
    @Override
    public void run() {
        try {
            final boolean isOk=bp.checkVUL(url);
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        DefaultTableModel dtm=(DefaultTableModel)table.getModel();
                        index=dtm.getRowCount()+1;
                        if (isOk==true) {
                            dtm.addRow(new Object[]{index, url, isOk});
                        }
                    }
                });

        } catch (Exception ex) {
        }
        MainApp.count.incrementAndGet();
    }
}

