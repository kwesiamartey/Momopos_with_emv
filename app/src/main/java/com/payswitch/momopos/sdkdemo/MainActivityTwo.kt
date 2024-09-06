package com.payswitch.momopos.sdkdemo

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.payswitch.momopos.R
import com.payswitch.momopos.sdkdemo.bean.TradeInfo
import com.payswitch.momopos.sdkdemo.data.DataEnDecrypt
import com.payswitch.momopos.sdkdemo.data.Mac
import com.payswitch.momopos.sdkdemo.decode.CortexActivity
import com.payswitch.momopos.sdkdemo.decode.DecodeActivity
import com.payswitch.momopos.sdkdemo.dock.DockTest
import com.payswitch.momopos.sdkdemo.dukpt.DUKPT
import com.payswitch.momopos.sdkdemo.key.KeyManager
import com.payswitch.momopos.sdkdemo.pin.Pin
import com.payswitch.momopos.sdkdemo.print.PrinterManager
import com.payswitch.momopos.sdkdemo.readcard.ReadCardManager
import com.payswitch.momopos.sdkdemo.rsa.Rsa
import com.payswitch.momopos.sdkdemo.system.ServiceStatus
import com.payswitch.momopos.sdkdemo.system.SystemActivity
import com.payswitch.momopos.sdkdemo.trthreeone.TRThreeOne
import sdk4.wangpos.libemvbinder.utils.ByteUtil
import wangpos.sdk4.libbasebinder.BankCard
import wangpos.sdk4.libbasebinder.Core
import wangpos.sdk4.libbasebinder.RspCode

/**
 * Created by Administrator on 2018/1/19.
 */
class MainActivityTwo : AppCompatActivity(), View.OnClickListener {
    private var context: Context? = null
    private var txt_msg: TextView? = null
    private var system: Button? = null
    private var card: Button? = null
    private var key: Button? = null
    private var pin: Button? = null
    private var en_decrypt: Button? = null
    private var mac: Button? = null
    private var print: Button? = null
    private var dukpt: Button? = null
    private var tr31: Button? = null
    private var dock: Button? = null
    private var decode: Button? = null
    private var decode_cortex: Button? = null
    private var version: Button? = null
    private var psam: Button? = null
    private var rsa: Button? = null
    private var mCore: Core? = null
    private var mBankCard: BankCard? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val device = Build.MODEL
        val SDKversion = Build.VERSION.SDK_INT
        val app_title: TextView
        if (device != null && (device.endsWith("NET5") || (SDKversion == 19)))
        {
            setContentView(R.layout.activity_main_net5)
            context = this
            app_title = findViewById<View>(R.id.txt_app_title) as TextView
            txt_msg = findViewById<View>(R.id.txt_msg) as TextView
            version = findViewById<View>(R.id.version_Id) as Button
            psam = findViewById<View>(R.id.psam_Id) as Button
            print = findViewById<View>(R.id.printer_Id) as Button
            decode = findViewById<View>(R.id.decode_Id) as Button
            val geocoder = Geocoder(this)
            Thread {
                mCore = Core(applicationContext)
                mBankCard = BankCard(application)
            }.start()

            version!!.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View) {
                    sPVersion
                }
            })

            psam!!.setOnClickListener { Psam() }

            print!!.setOnClickListener { Printer() }

            decode!!.setOnClickListener {
                val intent = Intent(context, DecodeActivity::class.java)
                startActivity(intent)
            }
        }
        else
        {
            setContentView(R.layout.activity_mainn)
            context = this
            app_title = findViewById<View>(R.id.txt_app_title) as TextView
            system = findViewById<View>(R.id.sys_Id) as Button
            card = findViewById<View>(R.id.card_Id) as Button
            key = findViewById<View>(R.id.key_Id) as Button
            pin = findViewById<View>(R.id.pin_Id) as Button
            en_decrypt = findViewById<View>(R.id.en_de_Id) as Button
            mac = findViewById<View>(R.id.mac_Id) as Button
            print = findViewById<View>(R.id.print_Id) as Button
            dukpt = findViewById<View>(R.id.dukpt_Id) as Button
            tr31 = findViewById<View>(R.id.tr31_Id) as Button
            dock = findViewById<View>(R.id.dock_Id) as Button
            decode = findViewById<View>(R.id.decode_Id) as Button
            decode_cortex = findViewById<View>(R.id.decode_cortex_Id) as Button
            rsa = findViewById<View>(R.id.rsa_Id) as Button
            system!!.setOnClickListener(this)
            card!!.setOnClickListener(this)
            key!!.setOnClickListener(this)
            pin!!.setOnClickListener(this)
            en_decrypt!!.setOnClickListener(this)
            mac!!.setOnClickListener(this)
            print!!.setOnClickListener(this)
            dukpt!!.setOnClickListener(this)
            tr31!!.setOnClickListener(this)
            dock!!.setOnClickListener(this)
            decode!!.setOnClickListener(this)
            decode_cortex!!.setOnClickListener(this)
            rsa!!.setOnClickListener(this)
            findViewById<View>(R.id.SDKServiceStatus).setOnClickListener(this)
            if (!device!!.endsWith("TAB")) {
                dock!!.visibility = View.GONE
            }

            /*
            if (device.endsWith("TAB")||device.endsWith("MINI")){
                print.setVisibility(View.GONE);
            }
            */
            TradeInfo.getInstance().init()
        }
        try {
            app_title.text = (getString(R.string.app_name)
                    + packageManager.getPackageInfo(packageName, 0).versionName)
        }
        catch (e: PackageManager.NameNotFoundException)
        {
            e.printStackTrace()
        }
    }

    override fun onClick(v: View) {
        var intent: Intent? = null
        when (v.id) {
            R.id.sys_Id -> intent = Intent(context, SystemActivity::class.java)
            R.id.card_Id -> intent = Intent(context, ReadCardManager::class.java)
            R.id.key_Id -> intent = Intent(context, KeyManager::class.java)
            R.id.pin_Id -> intent = Intent(context, Pin::class.java)
            R.id.en_de_Id -> intent = Intent(context, DataEnDecrypt::class.java)
            R.id.mac_Id -> intent = Intent(context, Mac::class.java)
            R.id.print_Id -> intent = Intent(context, PrinterManager::class.java)
            R.id.dukpt_Id -> intent = Intent(context, DUKPT::class.java)
            R.id.tr31_Id -> intent = Intent(context, TRThreeOne::class.java)
            R.id.dock_Id -> intent = Intent(context, DockTest::class.java)
            R.id.decode_Id -> intent = Intent(context, DecodeActivity::class.java)
            R.id.decode_cortex_Id -> intent = Intent(context, CortexActivity::class.java)
            R.id.SDKServiceStatus -> intent = Intent(context, ServiceStatus::class.java)
            R.id.rsa_Id -> intent = Intent(context, Rsa::class.java)
        }
        startActivity(intent)
    }

    private val sPVersion: Unit
        get() {
            val DevicesVersion = ByteArray(128)
            val len = IntArray(1)
            try {
                val i = mCore!!.getDevicesVersion(DevicesVersion, len)
                if (i == RspCode.OK) {
                    val ver = String(DevicesVersion)
                    txt_msg!!.text = "code: $ver"
                } else txt_msg!!.text = "code get fail$i"
            } catch (e: Exception) {
                e.printStackTrace()
                txt_msg!!.text = "Exception $e"
            }
        }

    private fun Psam() {
        val respdata = ByteArray(100)
        val resplen = IntArray(1)
        var retvalue = -1
        Log.v(ContentValues.TAG, "readcard")
        try {
            retvalue = mBankCard!!.readCard(
                BankCard.CARD_TYPE_NORMAL,
                BankCard.CARD_MODE_PSAM1,
                60,
                respdata,
                resplen,
                "app1"
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (respdata[0].toInt() != 0x05) {
            txt_msg!!.text = "readCard Psam fail"
            return
        }
        Log.v(ContentValues.TAG, "send apdu")
        val sendapdu = ByteArray(5)
        sendapdu[0] = 0x00.toByte()
        sendapdu[1] = 0x84.toByte()
        sendapdu[2] = 0x00.toByte()
        sendapdu[3] = 0x00.toByte()
        sendapdu[4] = 0x04.toByte()
        val resp = ByteArray(100)
        var retapde = -1
        try
        {
            retapde = mBankCard!!.sendAPDU(
                BankCard.CARD_MODE_PSAM1_APDU,
                sendapdu,
                sendapdu.size,
                resp,
                resplen )
        }
        catch (e: RemoteException)
        {
            e.printStackTrace()
        }
        Log.v(ContentValues.TAG, "resplen==" + resplen[0])
        val result = ByteArray(resplen[0])
        System.arraycopy(resp, 0, result, 0, resplen[0])
        Log.v(ContentValues.TAG, "resp==" + ByteUtil.bytes2HexString(result))
        if (retapde == 0)
        {
            txt_msg!!.text = "sendAPDU Psam success"
        } else {
            txt_msg!!.text = "sendAPDU Psam fail"
        }
    }

    private fun Printer() {
        val intentprincon = Intent(this, PrinterManager::class.java)
        startActivity(intentprincon)
        txt_msg!!.text = ""
    }
}
