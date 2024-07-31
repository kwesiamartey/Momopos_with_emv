package com.payswitch.momopos.sdkdemo.print;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.payswitch.momopos.R;

import wangpos.sdk4.libbasebinder.Printer;

public class PrinterManager extends Activity {
    Button btn_in,btn_bt,btn_usb , btnprintstatus , btnexit,btnpmkilometres,kilometres_clear;
    TextView tvshow;
    private Printer mPrinter;
    private String device = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printmanager);
        btn_in = (Button)findViewById(R.id.btn_in);
        btn_bt = (Button)findViewById(R.id.btn_bt);
        btn_usb = (Button)findViewById(R.id.btn_usb);
        btnprintstatus = (Button)findViewById(R.id.btnpmstatus);
        tvshow = (TextView)findViewById(R.id.tvshowprinttips);
        btnexit = (Button)findViewById(R.id.btnpmexit);

        device = Build.MODEL;
        if (device.endsWith("TAB")||device.endsWith("MINI") ||
            "WISEMINI2".equals(device) || "WISEASY MINI2".equals(device)){
            btnprintstatus.setVisibility(View.GONE);
        }

        new Thread(){
            @Override
            public void run() {
                mPrinter = new Printer(getApplicationContext());
            }
        }.start();
        if (device.endsWith("TAB")||device.endsWith("MINI") ||
                device.endsWith("MINI2")){
            btn_in.setVisibility(View.GONE);
        }
        btn_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intentprincon = new Intent(PrinterManager.this , USBPrinting.class);
                    intentprincon.putExtra("TYPE",0);
                    startActivity(intentprincon);

            }
        });
        btn_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentprincon = new Intent(PrinterManager.this , USBPrinting.class);
                intentprincon.putExtra("TYPE",1);
                startActivity(intentprincon);

            }
        });
        btn_usb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentprincon = new Intent(PrinterManager.this , USBPrinting.class);
                intentprincon.putExtra("TYPE",2);
                startActivity(intentprincon);

            }
        });

        btnprintstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            int[] status = new int[1];
            int ret = -1;
            try {
                ret = mPrinter.getPrinterStatus(status);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            //GETPrinterstatus(int[] status) 状态值	N	HEX1
            // 00 打印机正常
            // 0x01：参数错误
            // 0x06：不可执行
            // 0x8A：缺纸,
            // 0x8B：过热
            if(ret == 0){
                tvshow.setText("Printer status: "+status[0]);
            }else{
                tvshow.setText("Fail");
            }
            }
        });


        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
