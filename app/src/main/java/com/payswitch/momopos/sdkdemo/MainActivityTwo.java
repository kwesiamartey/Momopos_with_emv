package com.payswitch.momopos.sdkdemo;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.payswitch.momopos.R;
import com.payswitch.momopos.sdkdemo.decode.CortexActivity;
import com.payswitch.momopos.sdkdemo.decode.DecodeActivity;
import com.payswitch.momopos.sdkdemo.dock.DockTest;
import com.payswitch.momopos.sdkdemo.dukpt.DUKPT;
import com.payswitch.momopos.sdkdemo.key.KeyManager;
import com.payswitch.momopos.sdkdemo.pin.Pin;
import com.payswitch.momopos.sdkdemo.print.PrinterManager;
import com.payswitch.momopos.sdkdemo.readcard.ReadCardManager;
import com.payswitch.momopos.sdkdemo.rsa.Rsa;
import com.payswitch.momopos.sdkdemo.system.ServiceStatus;
import com.payswitch.momopos.sdkdemo.system.SystemActivity;
import com.payswitch.momopos.sdkdemo.trthreeone.TRThreeOne;

import sdk4.wangpos.libemvbinder.utils.ByteUtil;
import wangpos.sdk4.libbasebinder.BankCard;
import wangpos.sdk4.libbasebinder.Core;
import wangpos.sdk4.libbasebinder.RspCode;

/**
 * Created by Administrator on 2018/1/19.
 */

public class MainActivityTwo extends Activity implements View.OnClickListener{
    private Context context;
    private TextView txt_msg;
    private Button system,card,key,pin,en_decrypt,mac,print,dukpt,tr31,dock,decode,decode_cortex,version,psam,rsa;
    private Core mCore;
    private BankCard mBankCard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String device = Build.MODEL;
        int SDKversion = Build.VERSION.SDK_INT;
        TextView app_title;
        if(device != null&&(device.endsWith("NET5") || (SDKversion == 19))) {
            setContentView(R.layout.activity_main_net5);
            context = this;
            app_title = (TextView) findViewById(R.id.txt_app_title);
            txt_msg = (TextView) findViewById(R.id.txt_msg);
            version = (Button) findViewById(R.id.version_Id);
            psam = (Button) findViewById(R.id.psam_Id);
            print = (Button) findViewById(R.id.printer_Id);
            decode = (Button) findViewById(R.id.decode_Id);
            Geocoder geocoder =  new Geocoder(this);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mCore = new Core(getApplicationContext());
                    mBankCard = new BankCard(getApplication());
                }
            }).start();

            version.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSPVersion();
                }
            });

            psam.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Psam();
                }
            });

            print.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Printer();
                }
            });

            decode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DecodeActivity.class);
                    startActivity(intent);
                }
            });

        }else {
            setContentView(R.layout.activity_mainn);
            context = this;
            app_title = (TextView) findViewById(R.id.txt_app_title);
            system = (Button) findViewById(R.id.sys_Id);
            card = (Button) findViewById(R.id.card_Id);
            key = (Button) findViewById(R.id.key_Id);
            pin = (Button) findViewById(R.id.pin_Id);
            en_decrypt = (Button) findViewById(R.id.en_de_Id);
            mac = (Button) findViewById(R.id.mac_Id);
            print = (Button) findViewById(R.id.print_Id);
            dukpt = (Button) findViewById(R.id.dukpt_Id);
            tr31 = (Button) findViewById(R.id.tr31_Id);
            dock = (Button) findViewById(R.id.dock_Id);
            decode = (Button) findViewById(R.id.decode_Id);
            decode_cortex = (Button) findViewById(R.id.decode_cortex_Id);
            rsa = (Button) findViewById(R.id.rsa_Id);
            system.setOnClickListener(this);
            card.setOnClickListener(this);
            key.setOnClickListener(this);
            pin.setOnClickListener(this);
            en_decrypt.setOnClickListener(this);
            mac.setOnClickListener(this);
            print.setOnClickListener(this);
            dukpt.setOnClickListener(this);
            tr31.setOnClickListener(this);
            dock.setOnClickListener(this);
            decode.setOnClickListener(this);
            decode_cortex.setOnClickListener(this);
            rsa.setOnClickListener(this);
            findViewById(R.id.SDKServiceStatus).setOnClickListener(this);
            if (!device.endsWith("TAB")){
                dock.setVisibility(View.GONE);
            }
            /*
            if (device.endsWith("TAB")||device.endsWith("MINI")){
                print.setVisibility(View.GONE);
            }
            */

            com.payswitch.momopos.sdkdemo.bean.TradeInfo.getInstance().init();
        }

        try {
            app_title.setText(getString(R.string.app_name)
                    + getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()){
            case R.id.sys_Id:
                intent = new Intent(context, SystemActivity.class);
                break;
            case R.id.card_Id:
                intent = new Intent(context, ReadCardManager.class);
                break;
            case R.id.key_Id:
                intent = new Intent(context, KeyManager.class);
                break;
            case R.id.pin_Id:
                intent = new Intent(context, Pin.class);
                break;
            case R.id.en_de_Id:
                intent = new Intent(context, com.payswitch.momopos.sdkdemo.data.DataEnDecrypt.class);
                break;
            case R.id.mac_Id:
                intent = new Intent(context, com.payswitch.momopos.sdkdemo.data.Mac.class);
                break;
            case R.id.print_Id:
                intent = new Intent(context, PrinterManager.class);
                break;
            case R.id.dukpt_Id:
                intent = new Intent(context, DUKPT.class);
                break;
            case R.id.tr31_Id:
                intent = new Intent(context, TRThreeOne.class);
                break;
            case R.id.dock_Id:
                intent = new Intent(context, DockTest.class);
                break;
            case R.id.decode_Id:
                intent = new Intent(context, DecodeActivity.class);
                break;
            case R.id.decode_cortex_Id:
                intent = new Intent(context, CortexActivity.class);
                break;
            case R.id.SDKServiceStatus:
                intent = new Intent(context, ServiceStatus.class);
                break;
            case R.id.rsa_Id:
                intent = new Intent(context, Rsa.class);
                break;
        }
        startActivity(intent);
    }

    private void getSPVersion(){
        byte[] DevicesVersion = new byte[128];
        int[] len = new int[1];
        try {
            int i = mCore.getDevicesVersion(DevicesVersion, len);
            if (i == RspCode.OK) {
                String ver = new String(DevicesVersion);
                txt_msg.setText("code: " + ver);
            } else
                txt_msg.setText("code get fail"+i);
        } catch (Exception e) {
            e.printStackTrace();
            txt_msg.setText("Exception " + e.toString());
        }
    }

    private void Psam(){
        byte[] respdata = new byte[100];
        int[] resplen = new int[1];
        int retvalue = -1;
        Log.v(TAG, "readcard");
        try {
            retvalue = mBankCard.readCard(BankCard.CARD_TYPE_NORMAL, BankCard.CARD_MODE_PSAM1, 60, respdata, resplen, "app1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(respdata[0] != 0x05){
            txt_msg.setText("readCard Psam fail");
            return;
        }
        Log.v(TAG, "send apdu");
        byte[] sendapdu = new byte[5];
        sendapdu[0] = (byte) 0x00;
        sendapdu[1] = (byte) 0x84;
        sendapdu[2] = (byte) 0x00;
        sendapdu[3] = (byte) 0x00;
        sendapdu[4] = (byte) 0x04;
        byte[] resp = new byte[100];
        int retapde = -1;
        try {
            retapde = mBankCard.sendAPDU(BankCard.CARD_MODE_PSAM1_APDU, sendapdu, sendapdu.length, resp, resplen);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "resplen==" + resplen[0]);
        byte[] result = new byte[resplen[0]];
        System.arraycopy(resp,0,result,0,resplen[0]);
        Log.v(TAG, "resp==" + ByteUtil.bytes2HexString(result));
        if (retapde == 0) {
            txt_msg.setText("sendAPDU Psam success");
        } else {
            txt_msg.setText("sendAPDU Psam fail");
        }
    }

    private void Printer(){
        Intent intentprincon = new Intent(this , PrinterManager.class);
        startActivity(intentprincon);
        txt_msg.setText("");
    }

}
