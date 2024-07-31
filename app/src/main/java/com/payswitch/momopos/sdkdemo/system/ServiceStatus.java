package com.payswitch.momopos.sdkdemo.system;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.payswitch.momopos.R;

import java.util.List;

import sdk4.wangpos.libemvbinder.EmvCore;
import wangpos.sdk4.libbasebinder.Core;
import wangpos.sdk4.libkeymanagerbinder.Key;

public class ServiceStatus extends Activity {

    TextView tvResult;
    Button keyManagerStatus;
    private Key mKey;
    private Core mCore;
    private EmvCore emvCore;
    String SCVersion = "null";
    String SPVersion = "null";
    String PPVersion = "null";
    String SKVersion = "null";
    int KeyLogStatus = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicestatus);
        new Thread() {
            @Override
            public void run() {
                mKey = new Key(getApplicationContext());
                mCore = new Core(getApplicationContext());
                emvCore = new EmvCore(getApplicationContext());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String ver = null;
                        try {
//                            SCVersion = mCore.getSCVersion();
//                            byte[] DevicesVersion = new byte[128];
//                            int[] len = new int[1];
//                            int i = mCore.getDevicesVersion(DevicesVersion, len);
//                            if (i == RspCode.OK) {
//                                SPVersion = new String(DevicesVersion);
//
//                            }
//                            i = mCore.getSPKernelVersion(DevicesVersion, len);
//                            if (i == RspCode.OK) {
//                                SKVersion = new String(DevicesVersion);
//
//                            }
//
//                            PPVersion = emvCore.getPayPassLibVersion();

                            getInfo();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        }.start();

        tvResult = (TextView)findViewById(R.id.textviewsys);

//        getInfo();

        findViewById(R.id.checkSDKServiceStatus).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });

        findViewById(R.id.exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        keyManagerStatus = (Button) findViewById(R.id.KeyManagerStatus);
        keyManagerStatus.setVisibility(View.GONE);
        keyManagerStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(KeyLogStatus == 1){
                        //关闭Log
                        if(mKey.CloseSDKLog() == 0){
                            KeyLogStatus = 2;
                            keyManagerStatus.setText("Key Log Closed");
                        }else{
                            keyManagerStatus.setText("Close Key Log failed");
                        }
                    }else if(KeyLogStatus == 2){
                        //开启Log
                        if(mKey.OpenSDKLog() == 0){
                            KeyLogStatus = 1;
                            keyManagerStatus.setText("Key Log Opened");
                        }else{
                            keyManagerStatus.setText("Open Key Log failed");
                        }
                    }else{
                        KeyLogStatus = mKey.GetSDKLogStatus();

                        if(KeyLogStatus == 1){
                            keyManagerStatus.setText("Key Log Opened");
                        }else if(KeyLogStatus == 2){
                            keyManagerStatus.setText("Key Log Closed");
                        }else{
                            keyManagerStatus.setText("get Key Log Status failed");
                        }
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void getInfo(){
        tvResult.setText("");
        String packName = "wangpos.sdk4.emv";
        if(isServiceInstalled(packName)){
            tvResult.append("EmvService: " + packageVersionName(packName));
        }else{
            tvResult.append("EmvService is not Installed!");
        }

        packName = "wangpos.sdk4.base";
        if(isServiceInstalled(packName)){
            tvResult.append("\n\nBaseService: " + packageVersionName(packName));
        }else{
            tvResult.append("\n\nBaseService is not Installed!");
        }

        packName = "wangpos.sdk4.keymanager";
        if(isServiceInstalled(packName)){
            tvResult.append("\n\nKeyService: " + packageVersionName(packName));
        }else{
            tvResult.append("\n\nKeyService is not Installed!");
        }
//        if(SCVersion !=null&&!SCVersion.equals(""))
//            tvResult.append("\n\nSC:"+SCVersion);
//        if(SPVersion !=null&&!SPVersion.equals(""))
//            tvResult.append("\n\nSP:"+SPVersion);
//        if(PPVersion !=null&&!PPVersion.equals(""))
//            tvResult.append("\n\nPP:"+PPVersion);
//        if(SKVersion !=null&&!SKVersion.equals(""))
//            tvResult.append("\n\nSK:"+SKVersion);

    }

    //判断服务是否安装
    public boolean isServiceInstalled(String packName){
        PackageManager packageMgr = getPackageManager();
        List<PackageInfo> installedPackages = packageMgr.getInstalledPackages(0);
        for (PackageInfo pkg : installedPackages) {
            if (packName.equals(pkg.packageName)) {
                return true;
            }
        }
        return false;
    }
    public String packageVersionName(String packName) {
        PackageManager manager = getPackageManager();
        String vName = "";
        try {
            PackageInfo info = manager.getPackageInfo(packName, 0);
            vName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return vName;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
