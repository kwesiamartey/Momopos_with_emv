package com.payswitch.momopos.sdkdemo.print

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.payswitch.momopos.R
import wangpos.sdk4.libbasebinder.BankCard
import wangpos.sdk4.libbasebinder.Printer
import java.io.IOException
import java.io.InputStream

class Printing : AppCompatActivity() {
    private val mSystem: System? = null
    private var mPrinter: Printer? = null

    private var bloop = false
    private var bthreadrunning = false
    private var mCore: BankCard? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_printer)
        object : Thread() {
            override fun run() {
                mCore = BankCard(applicationContext)
                mPrinter = Printer(applicationContext)

                //                try {
//                    mPrinter.setPrintType(0);
//                    mPrinter.setPrintPaperType(0);
//                }  catch (RemoteException e) {
//                    e.printStackTrace();
//                }
            }
        }.start()

        findViewById<View>(R.id.buttonprinter).setOnClickListener {
            bloop = false
            if (bthreadrunning == false) PrintThread().start()
        }

        findViewById<View>(R.id.buttonpres).setOnClickListener {
            bloop = true
            findViewById<View>(R.id.buttonprinter).isEnabled = false
            findViewById<View>(R.id.buttonpres).isEnabled = false
            PrintThread().start()
        }

        findViewById<View>(R.id.buttonexitprint).setOnClickListener {
            bloop = false
            finish()
        }

        registerReceiver(mInfoReceiver, IntentFilter("com.wpos.printer_card"))
    }

    private val mInfoReceiver: BroadcastReceiver? = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val res = intent.getIntExtra("printer_c", 0)
            Log.e("TAG", "res===$res")
            //do something
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mInfoReceiver != null) {
            unregisterReceiver(mInfoReceiver)
        }
    }

    private fun testPrintLaguage(result: Int) {
        var result = result
        try
          {
            result = mPrinter!!.printString(
                "A number of national languages, for example：",
                22,
                Printer.Align.LEFT,
                false,
                false
            )
            //法语
            result = mPrinter!!.printString(
                "Bonjour Comment ça va Au revoir!",
                25,
                Printer.Align.CENTER,
                false,
                false
            )
            //德语
            result = mPrinter!!.printString(
                "Guten Tag Wie geht's?Auf Wiedersehen.",
                25,
                Printer.Align.CENTER,
                false,
                false
            )
            //阿拉伯语
            result = mPrinter!!.printString(
                " في متصفحه. عبارة اصفحة ارئيسية تستخدم أيضاً إشا",
                25,
                Printer.Align.CENTER,
                false,
                false
            )
            //乌克兰语
            result = mPrinter!!.printString(
                "Доброго дня Як справи? Бувайте!",
                25,
                Printer.Align.CENTER,
                false,
                false
            )
            //格鲁吉亚语
            result = mPrinter!!.printString(
                "გამარჯობა（gamarǰoba）კარგად（kargad）",
                25,
                Printer.Align.CENTER,
                false,
                false
            )
            //韩语
            result = mPrinter!!.printString(
                "안녕하세요 잘 지내세요 안녕히 가세요!",
                25,
                Printer.Align.CENTER,
                false,
                false
            )
            //日语
            result = mPrinter!!.printString(
                "こんにちは お元気ですか またね！",
                25,
                Printer.Align.CENTER,
                false,
                false
            )
            //印尼语
            result = mPrinter!!.printString(
                "Selamat Pagi/Siang Apa kabar? Sampai nanti!",
                25,
                Printer.Align.CENTER,
                false,
                false
            )
            //南非荷兰语
            result = mPrinter!!.printString(
                "Goeie dag Hoe gaan dit? Totsiens!",
                25,
                Printer.Align.CENTER,
                false,
                false
            )
            //蒙古语
            result = mPrinter!!.printString(
                "И-БАРИМТ ХЭВЛЭХ ХАМГИЙН ХЯЛБАР ШИЙДЭЛ",
                25,
                Printer.Align.CENTER,
                false,
                false
            )
            //....
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    private fun testPrintString(result: Int) {
        var result = result
        try {
            //default content print
            result =
                mPrinter!!.printString("www.wiseasy.com", 25, Printer.Align.CENTER, true, false)
            result = mPrinter!!.printString(
                "北京微智全景信息技术有限公司",
                25,
                Printer.Align.CENTER,
                false,
                false
            )
            result = mPrinter!!.printString("  ", 30, Printer.Align.CENTER, false, false)
            result = mPrinter!!.printString(
                "--------------------------------------------",
                30,
                Printer.Align.CENTER,
                false,
                false
            )
            result = mPrinter!!.printString(
                "Meal Package:KFC $100 coupons",
                25,
                Printer.Align.LEFT,
                false,
                false
            )
            result =
                mPrinter!!.printString("Selling Price:$90", 25, Printer.Align.LEFT, false, false)
            result = mPrinter!!.printString(
                "Merchant Name:KFC（ZS Park）",
                25,
                Printer.Align.LEFT,
                false,
                false
            )
            result = mPrinter!!.printString(
                "Payment Time:17/3/29 9:27",
                25,
                Printer.Align.LEFT,
                false,
                false
            )
            result = mPrinter!!.printString(
                "--------------------------------------------",
                30,
                Printer.Align.CENTER,
                false,
                false
            )
            result =
                mPrinter!!.printString("NO. of Coupons:5", 25, Printer.Align.LEFT, false, false)
            result =
                mPrinter!!.printString("Total Amount:$450", 25, Printer.Align.LEFT, false, false)
            result =
                mPrinter!!.printString("SN:1234 4567 4565,", 25, Printer.Align.LEFT, false, false)
            //The content is too long and automatically moves to the next line
            result = mPrinter!!.printString(
                "1、content too long and moves to the next line automatically ，，，",
                25,
                Printer.Align.LEFT,
                false,
                false
            )
            //The content is too long to move to the next line,According to the set lineSpacing display
            result = mPrinter!!.printStringExt(
                "2、content too long but not move to next line，，，",
                0,
                0f,
                1.0f,
                Printer.Font.SERIF,
                25,
                Printer.Align.LEFT,
                false,
                false,
                false
            )
            //font style print
            result = mPrinter!!.printStringExt(
                "Default Bold Font ",
                0,
                0f,
                2.0f,
                Printer.Font.DEFAULT,
                30,
                Printer.Align.LEFT,
                false,
                false,
                false
            ) //Default Font
            result = mPrinter!!.printStringExt(
                "Default Bold Font ",
                0,
                0f,
                2.0f,
                Printer.Font.DEFAULT_BOLD,
                30,
                Printer.Align.CENTER,
                false,
                false,
                false
            )
            result = mPrinter!!.printStringExt(
                "Monospace Font ",
                0,
                0f,
                2.0f,
                Printer.Font.MONOSPACE,
                30,
                Printer.Align.RIGHT,
                false,
                false,
                false
            )
            result = mPrinter!!.printStringExt(
                "Sans Serif Font ",
                0,
                0f,
                1.0f,
                Printer.Font.SANS_SERIF,
                30,
                Printer.Align.LEFT,
                false,
                false,
                false
            )
            result = mPrinter!!.printStringExt(
                "Sans Serif Font",
                0,
                0f,
                1.0f,
                Printer.Font.SERIF,
                25,
                Printer.Align.LEFT,
                true,
                false,
                false
            )

            //two content left and right in one line
            result = mPrinter!!.print2StringInLine(
                "left",
                "right",
                1.0f,
                Printer.Font.DEFAULT,
                25,
                Printer.Align.LEFT,
                false,
                false,
                false
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    private fun testPrintImageBase(pic: String) {
        try {
            var inputStream: InputStream? = null
            var bitmap: Bitmap? = null
            if (pic == "logo") {
                inputStream = assets.open("logo.png")
                bitmap = BitmapFactory.decodeStream(inputStream)
                mPrinter!!.printImageBase(bitmap, 100, 100, Printer.Align.LEFT, 0)
            } else if (pic == "wiseasy") {
                inputStream = assets.open("wechat.png")
                bitmap = BitmapFactory.decodeStream(inputStream)
                mPrinter!!.printImageBase(bitmap, 300, 300, Printer.Align.CENTER, 0)
            }
            bitmap!!.recycle()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (ex: RemoteException) {
            ex.printStackTrace()
        }
    }

    /**
     * PrinterControl(int command,请求状态，必需域	1	HEX1	0x01 开始打印（打开打印机）
     * 0x02 打印中，用于数据传输
     * 0x03 走纸
     * 0x0A 结束打印（关闭打印机）
     * int length,打印数据长度/走纸长度，必需域	N	HEX2	最大1024，高字节在前，开始和结束时如果无数据，传0
     * byte[] data)打印点阵数据（可选域）	N	HEX	开始和结束也可以传输数据
     */
    inner class PrintThread : Thread() {
        override fun run() {
            bthreadrunning = true
            val datalen = 0
            var result = 0
            val senddata: ByteArray? = null
            do {
                try {
                    result = mPrinter!!.printInit()
                    //clear print cache
                    mPrinter!!.clearPrintDataCache()
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
                try {
                    testPrintImageBase("logo")
                    //                    // Print text
                    testPrintString(result)
                    //                    // print bar_Code
//                    result = mPrinter.printBarCodeBase("1234567890abcdefg", Printer.BarcodeType.CODE_128, Printer.BarcodeWidth.LARGE, 50, 20);
//                    //print QR_Code(text)
//                    result = mPrinter.printQRCode("http://www.wangpos.com/",400);
////                    testPrintImageBase("wiseasy");
                    //laguage print
                    testPrintLaguage(result)
                    Log.d("hank", "run: hank")
                    //print end reserve height
                    result = mPrinter!!.printPaper(20)
                    if (result == 138) {
                        bloop = false
                        return
                    } else {
                        result = mPrinter!!.printFinish()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } while (bloop)
            bthreadrunning = false
        }
    }

    //一个全角空格替换两个半角空格
    private fun strToSBC(input: String): String {
        val c = input.toCharArray()
        val res = StringBuilder("")
        var space = false
        var index = 0
        for (i in c.indices) {
            if (c[i] != ' ') {
                if (index > 0) {
                    res.append(' ')
                }
                res.append(c[i])
                space = false
                index = 0
            } else {
                if (space && index == 1) {
                    res.append('　')
                    index = 0
                    space = false
                } else {
                    space = true
                    index++
                }
            }
        }
        return res.toString()
    }
}