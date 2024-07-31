package com.payswitch.momopos.sdkdemo.decode;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.payswitch.momopos.R;
import com.wiseasy.cortexdecodersdk.IScanner;
import com.wiseasy.cortexdecodersdk.ScanDecoderLibrary;
import com.wiseasy.cortexdecodersdk.SymbologyConstants;



public class CortexActivity extends Activity {

    private static final String TAG = "MainActivity";
    private ScanDecoderLibrary scanDecoderLibrary;

    int count;
    public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            String message = (String) msg.obj;
//            Toast.makeText(MainActivity.this, "扫码结果: " +message, Toast.LENGTH_SHORT).show();
            showToast(CortexActivity.this,"扫码: "+(++count)+" 结果: " +message,false);

//            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//            builder.setTitle("扫描结果");
//            builder.setMessage(message);
//            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            if (!isFinishing()) {
//                builder.show();
//            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cortex);
        Button btn_1 = (Button) findViewById(R.id.btn_1);
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        Button btn_5 = (Button) findViewById(R.id.btn_5);
        Button btn_6 = (Button) findViewById(R.id.btn_6);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDecoderLibrary = ScanDecoderLibrary.initDecoder(getApplicationContext());

            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scanDecoderLibrary == null) {
                    Toast.makeText(CortexActivity.this, "请先初始化", Toast.LENGTH_SHORT).show();
                    return;
                }
                count = 0;
                scanDecoderLibrary.scanCode(false,new IScanner.IScannerCallBack() {
                    @Override
                    public void onResult(final int i, final String s) {
                        Message message = Message.obtain();
                        message.obj = s;
                        handler.sendMessage(message);
                        Log.d(TAG, "onResult: " + i + " - " + s);
                    }
                });
            }
        });
        findViewById(R.id.btn_7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scanDecoderLibrary == null) {
                    Toast.makeText(CortexActivity.this, "请先初始化", Toast.LENGTH_SHORT).show();
                    return;
                }
                count = 0;
                scanDecoderLibrary.scanCode(true,new IScanner.IScannerCallBack() {
                    @Override
                    public void onResult(final int i, final String s) {
                        Message message = Message.obtain();
                        message.obj = s;
                        handler.sendMessage(message);
                        Log.d(TAG, "onResult: " + i + " - " + s);
                    }
                });
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scanDecoderLibrary == null) {
                    Toast.makeText(CortexActivity.this, "请先初始化", Toast.LENGTH_SHORT).show();
                    return;
                }
                count = 0;
                int[] symbologys = new int[]{SymbologyConstants.SymbologyType_QR};
                scanDecoderLibrary.scanCodeFilter(false,symbologys, new IScanner.IScannerCallBack() {
                    @Override
                    public void onResult(final int i, final String s) {
                        Message message = Message.obtain();
                        message.obj = s;
                        handler.sendMessage(message);
                        Log.d(TAG, "onResult: " + i + " - " + s);
                    }
                });
            }
        });
        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scanDecoderLibrary == null) {
                    Toast.makeText(CortexActivity.this, "请先初始化", Toast.LENGTH_SHORT).show();
                    return;
                }
                count = 0;
                /**
                 * 第一个参数传递布局  第二个是包裹Camera预览图像的ViewGroup 的id
                 * 如果想在页面控件中增加返回功能  增加属性 android:contentDescription="back"
                 */
                scanDecoderLibrary.scanCodeWithLayout(false,R.layout.activity_captures, R.id.rl_scanner_view, new IScanner.IScannerCallBack() {
                    @Override
                    public void onResult(int resultcode, String message) {
                        Message msg = Message.obtain();
                        msg.obj = message;
                        handler.sendMessage(msg);
                        Log.d(TAG, "onResult: " + resultcode + " - " + message);
                    }
                });
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scanDecoderLibrary == null) {
                    Toast.makeText(CortexActivity.this, "请先初始化", Toast.LENGTH_SHORT).show();
                    return;
                }
                count = 0;
                /**
                 * 第一个参数传递布局  第二个是包裹Camera预览图像的ViewGroup 的id
                 * 如果想在页面控件中增加返回功能  增加属性 android:contentDescription="back"
                 */
                int[] symbologys = new int[]{SymbologyConstants.SymbologyType_QR, SymbologyConstants.SymbologyType_PDF417};
                scanDecoderLibrary.scanCodeWithLayoutFilter(false,symbologys, R.layout.activity_captures, R.id.rl_scanner_view, new IScanner.IScannerCallBack() {
                    @Override
                    public void onResult(int resultcode, String message) {
                        Message msg = Message.obtain();
                        msg.obj = message;
                        handler.sendMessage(msg);
                        Log.d(TAG, "onResult: " + resultcode + " - " + message);
                    }
                });
            }
        });

    }

    Toast toast;
    public void showToast(Context context, String text, boolean isLongLength) {
        int length;
        if (isLongLength) {
            length = Toast.LENGTH_LONG;
        } else {
            length = Toast.LENGTH_SHORT;
        }
        if (toast == null) {
            toast = Toast.makeText(context, text, length);
        } else {
            toast.setText(text);
            toast.setDuration(length);
        }
        toast.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        handler.removeCallbacksAndMessages(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (scanDecoderLibrary != null) {
            scanDecoderLibrary.onDestroy();
        }
    }
}
