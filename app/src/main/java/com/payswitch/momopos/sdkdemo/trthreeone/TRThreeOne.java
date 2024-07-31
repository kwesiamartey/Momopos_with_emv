package com.payswitch.momopos.sdkdemo.trthreeone;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.payswitch.momopos.R;
import com.payswitch.momopos.sdkdemo.util.ByteUtil;

import wangpos.sdk4.libbasebinder.Core;
import wangpos.sdk4.libkeymanagerbinder.Key;

/**
 * Created by HS on 2019/7/12.
 */

public class TRThreeOne extends AppCompatActivity {
    Button btnkeyexstart , btnkeyexquit;
    TextView tvkeyexshow;
    private Key mKey;
    private Core mCore;
    private String mResultString;
    private static final int UPDATE_TEXTVIEW = 1;
    private String TAG = "TR-31";

    private MyHandler mHandler = new MyHandler();

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case UPDATE_TEXTVIEW:
                    if(tvkeyexshow!=null) {
                        tvkeyexshow.setText(mResultString);
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tr);
        btnkeyexstart = (Button)findViewById(R.id.button);
        btnkeyexquit = (Button)findViewById(R.id.buttonquit);
        tvkeyexshow = (TextView) findViewById(R.id.textViewresult);
        findViewById(R.id.note).setVisibility(View.GONE);
        new Thread() {
            @Override
            public void run() {
                mKey = new Key(getApplicationContext());
                mCore = new Core(getApplicationContext());
            }
        }.start();
        btnkeyexstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(importKBPK()==0 && injectKeyBlock()==0){
//                    if(IncreaseKSN()==0){
//                        GetKSN();
//                        EnDecrypt_getMAC();
//                    }
                }
            }
        });

        btnkeyexquit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private int importKBPK() {
        // Import KBPK
        byte[] CertData = new byte[8];
        for(int i = 0 ; i < 8 ;i ++){
            CertData[i] = 0x00;
        }
        byte[] kbpk = new byte[16];
        kbpk = ByteUtil.hexString2Bytes("89E88CF7931444F334BD7547FC3F380C");
        Log.e(TAG,"KBPK HEX = "+ sdk4.wangpos.libemvbinder.utils.ByteUtil.bytes2HexString(kbpk));
        byte[] checkval = new byte[1];
        int ret = -1;
        try {
            ret = mKey.updateKeyEx(Key.KEY_REQUEST_KBPK ,
                    Key.KEY_PROTECT_ZERO ,
                    CertData ,
                    kbpk ,
                    false,
                    0x00, checkval, "app1", 1);
            Log.e(TAG,"ret = "+ret);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(ret == 0){
            mResultString = "KBPK import success";
            tvkeyexshow.setText(mResultString);
        }else {
            mResultString = "KBPK import fail";
            tvkeyexshow.setText(mResultString);
            return -1;
        }

        return 0;
    }

    private int injectKeyBlock(){
        // Inject KSN
        byte[] KeyBlock = ByteUtil.hexString2Bytes("413030373250305445303045303030304635313631454439303238303741463236463144363232363336343442443234313932464442333139335337333033303143454538373031");


        Log.e(TAG,"KEY BLOCK  HEX = "+ sdk4.wangpos.libemvbinder.utils.ByteUtil.bytes2HexString(KeyBlock));
        Log.e(TAG,"KEY BLOCK  ASCII = "+ new String(KeyBlock));
        int ret = -1;
        try {
            ret = mKey.injectKeyBlock("app1", KeyBlock.length, KeyBlock);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(ret == 0){
            mResultString += "\n"+"inject KeyBlock success";
            tvkeyexshow.setText(mResultString);
        }else {
            mResultString += "\n"+"inject KeyBlock failed";
            tvkeyexshow.setText(mResultString);
            return -1;
        }

        return 0;
    }

    private int IncreaseKSN(){
        int ret = -1;
        try {
            ret = mKey.IncreaseKSN("app1");
            if(ret==0){
                mResultString += "\n"+"Increase KSN success";
            } else {
                mResultString += "\n"+"Increase KSN failed";
            }
            mHandler.sendEmptyMessage(UPDATE_TEXTVIEW);
        } catch (RemoteException ex){
            ex.printStackTrace();
        }
        return ret;
    }

    private int GetKSN(){
        int ret = -1;
        try {
            byte[] outData = new byte[16];
            int[] outDataLen = new int[1];
            ret = mKey.GetKSN("app1", outData, outDataLen);
            if(ret==0){
                Log.d(TAG,"GetKSN=="+ ByteUtil.bytes2HexString(outData)+"\noutDataLen=="+outDataLen[0]);
                mResultString += "\n"+"Get KSN success";
            } else {
                mResultString += "\n"+"Get KSN failed";
            }
            mHandler.sendEmptyMessage(UPDATE_TEXTVIEW);
        } catch (RemoteException ex){
            ex.printStackTrace();
        }
        return ret;
    }

    private int EnDecrypt_getMAC(){
        int len = 16;
        int dataLen = 0;
        byte[] data;
        dataLen = len;
        data = new byte[len];

        data[0] = (byte)0x05;
        data[1] = (byte)0x06;
        data[2] = (byte)0x07;
        data[3] = (byte)0x08;
        data[4] = (byte)0x01;
        data[5] = (byte)0x02;
        data[6] = (byte)0x05;
        data[7] = (byte)0x05;
        data[8] = (byte)0x05;
        data[9] = (byte)0x06;
        data[10] = (byte)0x07;
        data[11] = (byte)0x08;
        data[12] = (byte)0x01;
        data[13] = (byte)0x02;
        data[14] = (byte)0x05;
        data[15] = (byte)0x05;

        int encryptmode = 1;
        byte[] vectordata = new byte[8];
        int vectorLen = 8;
        byte[] pbOutdata = new byte[100];
        int[] pbOutdataLen = new int[50];
        int ret = -1;
        try {
            //en_decrypt
            ret = mCore.dataEnDecryptForIPEK(Core.ALGORITHM_3DES,
                    Core.ENCRYPT_MODE,
                    "app1",
                    encryptmode,
                    vectorLen,
                    vectordata,
                    dataLen,
                    data,
                    0x00,
                    pbOutdata,
                    pbOutdataLen);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if(ret==0){
            Log.d(TAG,"EnDecrypt=="+ ByteUtil.bytes2HexString(pbOutdata)+"\npbOutdataLen=="+pbOutdataLen[0]);
            mResultString += "\n"+"EnDecrypt success";
        } else {
            mResultString += "\n"+"EnDecrypt failed";
        }

        String mac = "1234567890123456";
        byte[] macByte = ByteUtil.hexString2Bytes(mac);
        byte[] Outdata = new byte[100];
        int[] OutdataLen = new int[1];
        try {
            //get MAC
            ret = mCore.getMacForIPEK("app1",Core.ALGORITHM_3DES,vectorLen,vectordata,macByte.length,macByte,0x02,Outdata,OutdataLen);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if(ret==0){
            Log.d(TAG,"getMac=="+ ByteUtil.bytes2HexString(Outdata)+"\nOutdataLen=="+OutdataLen[0]);
            mResultString += "\n"+"getMac success";
        } else {
            mResultString += "\n"+"getMac failed";
        }
        mHandler.sendEmptyMessage(UPDATE_TEXTVIEW);
        return ret;
    }
}