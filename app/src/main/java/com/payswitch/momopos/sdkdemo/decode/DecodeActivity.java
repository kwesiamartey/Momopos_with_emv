package com.payswitch.momopos.sdkdemo.decode;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.payswitch.momopos.R;

import wangpos.sdk4.base.IDecodeCallback;
import wangpos.sdk4.libbasebinder.Core;

/**
 * Created by HS on 2018/1/19.
 */

public class DecodeActivity extends Activity implements View.OnClickListener{
    private Context context;
    final String TAG = "DecodeActivity";
    Core mCore = null;
    private Button btnSingleScan,btnMultiScan,btnContinuousScan,btnStopScan,btnInit,btnClose;
    int ScanType = 1;
    private TextView tvResultInfo;
    int soundId;
    int decodeTimeOut = 10*1000;
    private SoundPoolUtils soundPoolUtils;
    public long sumStartTime;
    public long startTime;
    public long sumTime;
    public long endTime;
    public long sumCount;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decode_activity);
        context = this;

        btnSingleScan = (Button) findViewById(R.id.btnSingleScan);
        btnMultiScan = (Button) findViewById(R.id.btnMultiScan);
        btnContinuousScan = (Button) findViewById(R.id.btnContinuousScan);
        btnStopScan = (Button) findViewById(R.id.btnStopScan);
        btnInit = (Button) findViewById(R.id.btnInit);
        btnClose = (Button) findViewById(R.id.btnClose);
        tvResultInfo = (TextView) findViewById(R.id.tvResultInfo);



        btnSingleScan.setOnClickListener(this);
        btnMultiScan.setOnClickListener(this);
        btnContinuousScan.setOnClickListener(this);
        btnStopScan.setOnClickListener(this);
        btnInit.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        //判断是否为mini2
        if(!Build.MODEL.equals("WISEMINI2")&&!Build.MODEL.equals("WISEASY MINI2")){
            btnMultiScan.setVisibility(View.GONE);
            btnContinuousScan.setVisibility(View.GONE);
            btnStopScan.setVisibility(View.GONE);
        }
        new Thread() {
            @Override
            public void run() {
                mCore = new Core(getApplicationContext());
            }
        }.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        soundPoolUtils = new SoundPoolUtils();
        soundPoolUtils.initSound(this,R.raw.beep);
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            if(mCore!=null)mCore.decode_StopScan();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        soundPoolUtils.close();
    }

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    soundPoolUtils.soundPlay(DecodeActivity.this);
                    SDKDecodeResult decodeResult = (SDKDecodeResult) msg.obj;
                    if(decodeResult!=null) {
                        String result = "barcodeData:" + decodeResult.barcodeData +"\n"+
                                "codeId:" + decodeResult.codeId +"\n"+
                                "aimId:" + decodeResult.aimId +"\n"+
                                "aimModifier:" + decodeResult.aimModifier +"\n"+
                                "length:" + decodeResult.length+"\n";
                        if (ScanType == 2) {//多扫
                            tvResultInfo.append(result);
                        } else {
                            tvResultInfo.setText(result);
                        }
                    }
                    break;
                case 10:
                    int ret = msg.arg1;
                    Log.v(TAG,"init result is " + ret);
                    if(ret == 0){
                        tvResultInfo.setText("初始化成功");
                    }else{
                        tvResultInfo.setText("初始化失败");
                    }
                    break;
            }


        }
    };

    /**
     * Displays the decoded results (note: called from thread)
     *
     */
    private void DisplayDecodeResults(final SDKDecodeResult decodeResult)
    {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                soundPoolUtils.soundPlay(DecodeActivity.this);
                endTime = System.currentTimeMillis();
                if(startTime != 0 && endTime !=0)
                    sumTime = endTime -startTime;
                startTime = System.currentTimeMillis();
                sumCount++;
                if(decodeResult!=null) {
                    String result = "barcodeData:" + decodeResult.barcodeData +"\n"+
                            "codeId:" + decodeResult.codeId +"\n"+
                            "aimId:" + decodeResult.aimId +"\n"+
                            "aimModifier:" + decodeResult.aimModifier +"\n"+
                            "length:" + decodeResult.length+"\n"+
                            "SingleScan time:"+ sumTime+"ms \n";

                    if (ScanType == 2) {//多扫
                        tvResultInfo.append(result);
                    } else {
                        if(ScanType == 3){//连续
                            long averageTime = 0;
                            if(sumStartTime != 0 && endTime !=0) averageTime = (endTime -sumStartTime)/sumCount;
                            result = result + "average time:"+ averageTime+"ms";
                        }
                        tvResultInfo.setText(result);
                    }
                }
            }
        });

    }

    IDecodeCallback mdecodeCallback = new IDecodeCallback.Stub(){

        @Override
        public int resultCallback(int i,String barcodeData, byte codeId, byte aimId, byte aimModifier, int length, byte[] bytes) throws RemoteException {
            Log. d(TAG, "Additional Multiread Results");
            Log. d(TAG, "  barcodeData:" + barcodeData );
            Log. d(TAG, "  codeId:" + codeId );
            Log. d(TAG, "  aimId:" + aimId );
            Log. d(TAG, "  aimModifier:" + aimModifier );
            Log. d(TAG, "  length:" + length );
            SDKDecodeResult decodeResult = new SDKDecodeResult();
            decodeResult.barcodeData = barcodeData;
            decodeResult.codeId = codeId;
            decodeResult.aimId = aimId;
            decodeResult.aimModifier = aimModifier;
            decodeResult.length = length;
            DisplayDecodeResults(decodeResult);
//            Message msg = new Message();
//            msg.obj = decodeResult;
//            msg.what = 1;
//            mHandler.sendMessage(msg);
            return 0;
        }
        @Override
        public int CommonCallback(int i) throws RemoteException {
            return 0;
        }
    };
    @Override
    public void onClick(View v) {
        int result = 0;
        try {
            switch (v.getId()){
                case R.id.btnSingleScan:
                    ScanType = 1;
                    tvResultInfo.setText("");
                    result = mCore.decode_StartSingleScan(decodeTimeOut);
                    Log.e(TAG,"result = "+result);
                    if(result == -1){
                        Toast.makeText(getApplicationContext(),"Failed to open,Please check if it is initialized",Toast.LENGTH_SHORT).show();
                    }
                    sumStartTime = System.currentTimeMillis();
                    startTime = sumStartTime;
                    sumCount = 0;
                    break;
                case R.id.btnMultiScan:
                    ScanType = 2;
                    tvResultInfo.setText("");
                    result = mCore.decode_StartMultiScan(decodeTimeOut);
                    Log.e(TAG,"result = "+result);
                    if(result == -1){
                        Toast.makeText(getApplicationContext(),"Failed to open,Please check if it is initialized",Toast.LENGTH_SHORT).show();
                    }
                    sumStartTime = System.currentTimeMillis();
                    startTime = sumStartTime;
                    sumCount = 0;
                    break;
                case R.id.btnContinuousScan:
                    ScanType = 3;
                    tvResultInfo.setText("");
                    result = mCore.decode_StartContinuousScan(decodeTimeOut);
                    Log.e(TAG,"result = "+result);
                    if(result == -1){
                        Toast.makeText(getApplicationContext(),"Failed to open,Please check if it is initialized",Toast.LENGTH_SHORT).show();
                    }
                    sumStartTime = System.currentTimeMillis();
                    startTime = sumStartTime;
                    sumCount = 0;
                    break;
                case R.id.btnStopScan:
                    mCore.decode_StopScan();
                    break;
                case R.id.btnInit:
                    int ret = mCore.decode_Init(mdecodeCallback);
                    Message msg = new Message();
                    msg.arg1 = ret;
                    msg.what = 10;
                    mHandler.sendMessage(msg);
                    break;
                case R.id.btnClose:
                    mCore.decode_Close();
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mCore!=null) try {
            mCore.decode_Close();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private class SDKDecodeResult {

        public String barcodeData;
        public byte codeId;
        public byte aimId;
        public byte aimModifier;
        public int length;
        public byte[] byteBarcodeData;

    }



}