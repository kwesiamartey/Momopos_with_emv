package com.payswitch.momopos.sdkdemo.readcard

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.RemoteException
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.payswitch.momopos.R
import com.payswitch.momopos.sdkdemo.util.ByteUtil
import com.payswitch.momopos.sdkdemo.util.keyrandom
import wangpos.sdk4.libbasebinder.BankCard
import wangpos.sdk4.libbasebinder.Core
import wangpos.sdk4.libbasebinder.HEX

class CardReader : AppCompatActivity(), View.OnClickListener {
    private var tvcardreadershow: TextView? = null
    private var mBtnPicc: Button? = null
    private var buttonreadcardicms: Button? = null
    private var mBtnIc: Button? = null
    private var mBtnMag: Button? = null
    private var mBtnPsam1: Button? = null
    private var mBtnPsam2: Button? = null
    private var buttonM0: Button? = null
    private var buttonM1: Button? = null
    private var buttonM1_quick: Button? = null
    private var mBtnAT24: Button? = null
    private var button4428: Button? = null
    private var button4442: Button? = null
    private var buttonat88sc102: Button? = null
    private var buttonDesFire: Button? = null
    private var mBtnStop: Button? = null
    private var mBtnNoEMV: Button? = null
    private var mBtnExit: Button? = null

    private var mCore: Core? = null
    private var mBankCard: BankCard? = null

    private lateinit var mBtnArray: Array<Button?>
    private var stringBuilder: StringBuilder? = null

    private var mDetect = false

    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            mThread = null
            when (msg.what) {
                -1 -> {
                    val cardType = msg.obj as String
                    tvcardreadershow!!.text = msg.arg1.toString() + "---/" + cardType
                }

                PICC -> if (msg.arg1 == 0) {
                    tvcardreadershow!!.text = "PICC read card and send apdu success"
                } else if (msg.arg1 == 3) {
                    tvcardreadershow!!.text = "time out"
                } else if (msg.arg1 == 4) {
                    Toast.makeText(this@CardReader, "cancel readcard", Toast.LENGTH_SHORT).show()
                } else {
                    tvcardreadershow!!.text = "PICC fail"
                }

                IC -> if (msg.arg1 == 0) {
                    tvcardreadershow!!.text = "IC read card and send apdu success"
                } else if (msg.arg1 == 3) {
                    tvcardreadershow!!.text = "time out"
                } else if (msg.arg1 == 4) {
                    Toast.makeText(this@CardReader, "cancel readcard", Toast.LENGTH_SHORT).show()
                } else {
                    tvcardreadershow!!.text = "IC error"
                }

                MAG -> if (msg.arg1 == 0) {
                    tvcardreadershow!!.text = "MAG Test success"
                } else if (msg.arg1 == 3) {
                    tvcardreadershow!!.text = "time out"
                } else if (msg.arg1 == 4) {
                    Toast.makeText(this@CardReader, "cancel readcard", Toast.LENGTH_SHORT).show()
                } else {
                    tvcardreadershow!!.text = "MAG Test error"
                }

                PSAM1 -> if (msg.arg1 == 0) {
                    tvcardreadershow!!.text = "PSAM1 send apdu success"
                } else if (msg.arg1 == 3) {
                    tvcardreadershow!!.text = "time out"
                } else {
                    tvcardreadershow!!.text = "PSAM1 error"
                }

                PSAM2 -> if (msg.arg1 == 0) {
                    tvcardreadershow!!.text = "PSAM2 send apdu success"
                } else if (msg.arg1 == 3) {
                    tvcardreadershow!!.text = "time out"
                } else {
                    tvcardreadershow!!.text = "PSAM2 error"
                }

                AT24 -> {
                    Log.e("zys", "mAT24 = $mAT24")
                    if (msg.arg1 == 0) {
                        tvcardreadershow!!.text = mAT24
                    } else if (msg.arg1 == 3) {
                        tvcardreadershow!!.text = "time out"
                    } else {
                        tvcardreadershow!!.text = "AT24CXX read fail"
                    }
                }

                M0 -> if (msg.arg1 == 0) {
                    tvcardreadershow!!.text = stringBuilder.toString()
                } else if (msg.arg1 == 3) {
                    tvcardreadershow!!.text = "time out"
                } else {
                    tvcardreadershow!!.text = stringBuilder.toString()
                }

                M1 -> if (msg.arg1 == 0) {
                    tvcardreadershow!!.text = stringBuilder.toString()
                } else if (msg.arg1 == 3) {
                    tvcardreadershow!!.text = "time out"
                } else {
                    tvcardreadershow!!.text = stringBuilder.toString()
                }

                C4428 -> if (msg.arg1 == 0) {
                    tvcardreadershow!!.text = "4428Card read/Write card and Verify success"
                } else if (msg.arg1 == 3) {
                    tvcardreadershow!!.text = "time out"
                } else {
                    tvcardreadershow!!.text = "4428Card error"
                }

                C4442 -> if (msg.arg1 == 0) {
                    tvcardreadershow!!.text = "4442Card read/Write card and Verify success"
                } else if (msg.arg1 == 3) {
                    tvcardreadershow!!.text = "time out"
                } else {
                    tvcardreadershow!!.text = "4442Card error"
                }

                at88sc -> if (msg.arg1 == 0) {
                    tvcardreadershow!!.text = "at88sc102Card read/Write card and Verify success"
                } else if (msg.arg1 == 3) {
                    tvcardreadershow!!.text = "time out"
                } else {
                    tvcardreadershow!!.text = "at88sc102 error"
                }

                DESFIRE -> if (msg.arg1 == 0) {
                    tvcardreadershow!!.text = stringBuilder.toString()
                } else if (msg.arg1 == 3) {
                    tvcardreadershow!!.text = "time out"
                } else {
                    tvcardreadershow!!.text = stringBuilder.toString()
                }
            }
            refreshButton(false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)


        object : Thread() {
            override fun run() {
                mBankCard = BankCard(applicationContext)
                mCore = Core(applicationContext)
            }
        }.start()

        tvcardreadershow = findViewById<View>(R.id.cardtextview) as TextView
        mBtnPicc = findViewById<View>(R.id.card) as Button
        buttonreadcardicms = findViewById<View>(R.id.buttonreadcardicms) as Button
        mBtnIc = findViewById<View>(R.id.iccard) as Button
        mBtnMag = findViewById<View>(R.id.mag) as Button
        mBtnPsam1 = findViewById<View>(R.id.buttonpsm1) as Button
        mBtnPsam2 = findViewById<View>(R.id.buttonpsm2) as Button
        buttonM0 = findViewById<View>(R.id.buttonM0) as Button
        buttonM1 = findViewById<View>(R.id.buttonM1) as Button
        buttonM1_quick = findViewById<View>(R.id.buttonM1_quick) as Button
        mBtnAT24 = findViewById<View>(R.id.atx24) as Button
        button4428 = findViewById<View>(R.id.button4428) as Button
        button4442 = findViewById<View>(R.id.button4442) as Button
        buttonat88sc102 = findViewById<View>(R.id.buttonat88sc102) as Button
        buttonDesFire = findViewById<View>(R.id.buttonDesFire) as Button
        mBtnExit = findViewById<View>(R.id.exit) as Button
        mBtnStop = findViewById<View>(R.id.btn_stop) as Button
        mBtnNoEMV = findViewById<View>(R.id.but_no_emv) as Button

        mBtnPicc!!.setOnClickListener(this)
        mBtnIc!!.setOnClickListener(this)
        mBtnMag!!.setOnClickListener(this)
        mBtnPsam1!!.setOnClickListener(this)
        mBtnPsam2!!.setOnClickListener(this)
        buttonM0!!.setOnClickListener(this)
        buttonM1!!.setOnClickListener(this)
        buttonM1_quick!!.setOnClickListener(this)
        mBtnAT24!!.setOnClickListener(this)
        mBtnExit!!.setOnClickListener(this)
        mBtnStop!!.setOnClickListener(this)
        button4428!!.setOnClickListener(this)
        button4442!!.setOnClickListener(this)
        buttonat88sc102!!.setOnClickListener(this)
        buttonDesFire!!.setOnClickListener(this)
        buttonreadcardicms!!.setOnClickListener(this)
        mBtnNoEMV!!.setOnClickListener(this)


        mBtnArray = arrayOf(
            mBtnPicc,
            mBtnIc,
            mBtnMag,
            mBtnPsam1,
            mBtnPsam2,
            buttonM0,
            buttonM1,
            buttonM1_quick,
            mBtnAT24,
            button4428,
            button4442,
            buttonat88sc102,
            buttonDesFire,
            buttonreadcardicms
        )
        refreshButton(false)
        setMBtnPsam2Visibility()
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            mBankCard!!.breakOffCommand()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    private var mThread: Thread? = null

    override fun onClick(v: View) {
        tvcardreadershow!!.setText(R.string.ditips)
        stringBuilder = StringBuilder()
        when (v.id) {
            R.id.but_no_emv -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        No_EMV_Reader()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.card -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        piccCardTest()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.buttonreadcardicms -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        piccCardTestms()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.iccard -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        icCardTest()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.mag -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        magTest()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.buttonpsm1 -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        psam1()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.buttonpsm2 -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        psam2()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.buttonM0 -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        M0()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.buttonM1 -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        M1()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.buttonM1_quick -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        M1_quick()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.atx24 -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        at24CardTest()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.button4428 -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        Card4428Test()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.button4442 -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        Card4442Test()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.buttonat88sc102 -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        Cardbuttonat88sc102Test()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.buttonDesFire -> if (mThread == null) {
                mThread = object : Thread() {
                    override fun run() {
                        mDetect = true
                        desFireTest()
                    }
                }
                (mThread as Thread).start()
            }

            R.id.btn_stop -> {
                /*------ Create zhanghai 2018-11-6 Note Begin -----*/
                /*------ Update zhanghai 2018-11-6 Note-----*/
                try {
                    mDetect = false
                    mThread!!.interrupt()
                    mThread = null
                    mBankCard!!.breakOffCommand()
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
                tvcardreadershow!!.setText(R.string.test_please)
            }

            R.id.exit -> finish()
        }
        if (v.id != R.id.exit) {
            if (v.id == R.id.btn_stop) refreshButton(false)
            else refreshButton(true)
        }
    }

    private fun No_EMV_Reader() {
        var retvalue = -1
        try {
            mCore!!.buzzer()


            //        int cardMode = 0x0100 | 0x4100 | 0x8100 | 0x0200 | 0x0400 | 0x0202 | 0x0208 | 0x0211 | 0x0140 | 0x0204 | 0x0214 | 0x0224 | 0x0234;
//        int cardMode =
//                0x0100 | 0x4100 | 0x8100 |
//                0x0200 | 0x0202| 0x0208 | 0x0211 | 0x0140 ;
//                | 0x0400 | 0x0202 | 0x0208 | 0x0211 | 0x0140 | 0x0204 | 0x0214 | 0x0224 | 0x0234;
//            0x00: Bank Card
//            0x00:M1(Mifare-One) Card
//            0x08: ID Card(Chinese Standard)
//            0x11: NFC Tag
//            0x40:sle4442/4428/at88sc102/AT24Cxx Apple VAS:
//            0x04:vas or payment
//            0x14:vas and payment
//            0x24:vas only
//            0x34:payment only
            val cardMode = 0x00
            //                    | 0x08| 0x11| 0x40| 0x04| 0x14|  0x24|0x34;
            val respdata = ByteArray(100)
            val resplen = IntArray(1)
            retvalue = mBankCard!!.readCard(
                BankCard.CARD_TYPE_NORMAL,
                BankCard.CARD_MODE_ICC or BankCard.CARD_MODE_PICC,
                60,
                respdata,
                resplen,
                "app1"
            )

            Log.i("CardData", "${retvalue}")

            //            retvalue = mBankCard.cardReaderDetact(0, 0x02 , cardMode, respdata, resplen, "app1");
            if (retvalue == 0) {
                val respdatum = respdata[0].toInt()
                var cardType = ""
                when (respdatum) {
                    0X00 -> cardType = " Magnetic Track Data"
                    0X01 -> cardType = "  Read Card failed"
                    0X02 -> cardType = " Got Card Magnetic Track Data, Data Encrypt failed"
                    0X03 -> cardType = " Timeout"
                    0X04 -> cardType = " Cancel Searching Card"
                    0X05 -> cardType = "Contact IC card insert"
                    0X15 -> cardType = " 4442 Card"
                    0X25 -> cardType = "  4428 Card"
                    0X35 -> cardType = "  AT88SC102 Card detected"
                    0X45 -> cardType = "  AT24CXX Card detected."
                    0X07 -> cardType = " Contactless IC Card"
                    0X27 -> cardType = " multi-Contactless IC Card are detected"
                    0X37 -> cardType = " M1-S50 Card detected"
                    0X47 -> cardType = " M1-S70 Card detected"
                    0X57 -> cardType = " UL(UltraLight) /NTAG203 Card detected"
                    0x87 -> cardType = "  DesFire Card detected"
                }
                val msg = mHandler.obtainMessage(PSAM1)
                msg.arg1 = respdatum
                msg.obj = cardType
                msg.what = -1
                mHandler.sendMessage(msg)
            }
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    private fun refreshButton(isRead: Boolean) {
        for (i in mBtnArray.indices) {
            mBtnArray[i]!!.isEnabled = !isRead
        }
        mBtnStop!!.isEnabled = isRead
    }

    fun psam1() {
        //蜂鸣器
        try {
            mCore!!.buzzer()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        val respdata = ByteArray(100)
        val resplen = IntArray(1)
        var retvalue = -1
        Log.v(TAG, "readcard")
        try {
            //打开psam
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
        if (!mDetect) {
            // Don't do the other process because press stop button.
            return
        }

        if (respdata[0].toInt() != 0x05) {
            val msg = mHandler.obtainMessage(PSAM1)
            if (respdata[0].toInt() == 0x03) {
                msg.arg1 = 3
            } else {
                msg.arg1 = 1
            }
            mHandler.sendMessage(msg)
            return
        }

        val s1 = keyrandom.bytesToHexString(respdata)
        Log.v(TAG, "" + s1)
        Log.v(TAG, "" + resplen[0])
        Log.v(TAG, "send apdu")
        val sendapdu = ByteArray(5)
        sendapdu[0] = 0x00.toByte()
        sendapdu[1] = 0x84.toByte()
        sendapdu[2] = 0x00.toByte()
        sendapdu[3] = 0x00.toByte()
        sendapdu[4] = 0x04.toByte()
        Log.v(TAG, "" + keyrandom.bytesToHexString(sendapdu))
        val resp = ByteArray(100)
        var retapde = -1
        try {
            retapde = mBankCard!!.sendAPDU(
                BankCard.CARD_MODE_PSAM1_APDU,
                sendapdu,
                sendapdu.size,
                resp,
                resplen
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        Log.v(TAG, "" + resplen[0])
        Log.v(TAG, "" + keyrandom.bytesToHexString(resp))
        Log.v(TAG, "ret$retapde")
        val msg = mHandler.obtainMessage(PSAM1)
        if (retapde == 0 && retvalue == 0) {
            Log.v(TAG, "\nret$retapde")
            msg.arg1 = 0
        } else {
            Log.v(TAG, "\nret$retapde")
            msg.arg1 = 1
        }
        mHandler.sendMessage(msg)
    }

    fun psam2() {
        //蜂鸣器
        try {
            mCore!!.buzzer()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        Log.v(TAG, "bankcard readcard")
        val respdata = ByteArray(255)
        val resplen = IntArray(1)
        var retvalue = 0
        try {
            retvalue = mBankCard!!.readCard(
                BankCard.CARD_TYPE_NORMAL,
                BankCard.CARD_MODE_PSAM2,
                60,
                respdata,
                resplen,
                "app1"
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        if (!mDetect) {
            // Don't do the other process because press stop button.
            return
        }

        if (respdata[0].toInt() != 0x05) {
            val msg = mHandler.obtainMessage(PSAM2)
            if (respdata[0].toInt() == 0x03) {
                msg.arg1 = 3
            } else {
                msg.arg1 = 1
            }
            mHandler.sendMessage(msg)
            return
        }

        val s1 = HEX.bytesToHex(respdata)
        Log.v(TAG, "" + retvalue)
        Log.v(TAG, "" + s1)
        Log.v(TAG, "" + resplen[0])

        Log.v(TAG, "send apdu")
        val sendapdu = ByteArray(7)
        sendapdu[0] = 0x00.toByte()
        sendapdu[1] = 0xa4.toByte()
        sendapdu[2] = 0x00.toByte()
        sendapdu[3] = 0x00.toByte()
        sendapdu[4] = 0x02.toByte()
        sendapdu[5] = 0x3f.toByte()
        sendapdu[6] = 0x01.toByte()
        Log.v(TAG, "" + keyrandom.bytesToHexString(sendapdu))
        val resp = ByteArray(256)
        var psm2apdu = -1
        try {
            //BankCard.CARD_MODE_PSAM1_APDU
            psm2apdu = mBankCard!!.sendAPDU(
                BankCard.CARD_MODE_PSAM2_APDU,
                sendapdu,
                sendapdu.size,
                resp,
                resplen
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        Log.v(TAG, "" + resplen[0])
        Log.v(TAG, "" + keyrandom.bytesToHexString(resp))
        val msg = mHandler.obtainMessage(PSAM2)
        if (psm2apdu == 0) {
            msg.arg1 = 0
        } else {
            msg.arg1 = 1
        }
        mHandler.sendMessage(msg)
    }

    fun piccCardTest() {
        Log.v(TAG, "CardReader, piccCardTest")
        try {
            mCore!!.buzzer()
            Log.v(TAG, "CardReader, piccCardTest, buzzer--->>>")
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        val respdata = ByteArray(1024)
        val resplen = IntArray(1)
        var retvalue = -1
        try {
            retvalue = mBankCard!!.readCard(
                BankCard.CARD_TYPE_NORMAL,
                BankCard.CARD_MODE_PICC,
                60,
                respdata,
                resplen,
                "app1"
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        if (retvalue == 11) {
            val msg = mHandler.obtainMessage(MAG)
            msg.arg1 = 10
            mHandler.sendMessage(msg)
            return
        }
        if (respdata[0].toInt() == 0x03) {
            val msg = mHandler.obtainMessage(PICC)
            msg.arg1 = 3
            mHandler.sendMessage(msg)
            return
        } else if (respdata[0].toInt() == 0x04) {
            val msg = mHandler.obtainMessage(PICC)
            msg.arg1 = 4
            mHandler.sendMessage(msg)
            return
        }

        if (!mDetect) {
            // Don't do the other process because press stop button.
            return
        }

        val s1 = HEX.bytesToHex(respdata)
        Log.v(TAG, "CardReader, piccCardTest, s1 = $s1")
        Log.v(TAG, "CardReader, piccCardTest, resplen = " + resplen[0])

        Log.v(TAG, "getcardsnfunction")
        val outdata = ByteArray(512)
        val len = IntArray(1)
        try {
            mBankCard!!.getCardSNFunction(outdata, len)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        Log.v(TAG, "CardReader, piccCardTest, outdata = " + HEX.bytesToHex(outdata))
        Log.v(TAG, "CardReader, piccCardTest, len = " + len[0])

        val sendapdu = ByteArray(256)
        sendapdu[0] = 0x00.toByte()
        sendapdu[1] = 0xa4.toByte()
        sendapdu[2] = 0x00.toByte()
        sendapdu[3] = 0x00.toByte()
        sendapdu[4] = 0x02.toByte()
        sendapdu[5] = 0x3f.toByte()
        sendapdu[6] = 0x01.toByte()
        val resp = ByteArray(256)
        var retpicc = -1
        try {
            retpicc = mBankCard!!.sendAPDU(BankCard.CARD_MODE_PICC, sendapdu, 7, resp, resplen)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        Log.v(TAG, "CardReader, piccCardTest, resplen = " + resplen[0])
        Log.v(TAG, "CardReader, piccCardTest, resp = " + HEX.bytesToHex(resp))

        var whileCondition = false
        do {
            SystemClock.sleep(200)
            Log.v(TAG, "CardReader, piccCardTest, picc detecting--------------mDetect = $mDetect")
            //picc 卡在位检测
            try {
                whileCondition = BankCard.CARD_DETECT_EXIST != mBankCard!!.piccDetect()
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        } while (whileCondition && mDetect)
        val msg = mHandler.obtainMessage(PICC)
        if (retvalue == 0 && retpicc == 0) {
            msg.arg1 = 0
        } else {
            msg.arg1 = 1
        }
        mHandler.sendMessage(msg)
    }

    fun icCardTest() {
        Log.v(TAG, "CardReader, icCardTest")
        try {
            mCore!!.buzzer()
            Log.v(TAG, "CardReader, icCardTest, buzzer--->>>")
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        val respdata = ByteArray(256)
        val resplen = IntArray(1)
        var retvalue = -1
        try {
            retvalue = mBankCard!!.readCard(
                BankCard.CARD_TYPE_NORMAL,
                BankCard.CARD_MODE_ICC,
                60,
                respdata,
                resplen,
                "app1"
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        if (retvalue == 11) {
            val msg = mHandler.obtainMessage(MAG)
            msg.arg1 = 10
            mHandler.sendMessage(msg)
            return
        }
        if (respdata[0].toInt() == 0x03) {
            val msg = mHandler.obtainMessage(IC)
            msg.arg1 = 3
            mHandler.sendMessage(msg)
            return
        } else if (respdata[0].toInt() == 0x04) {
            val msg = mHandler.obtainMessage(IC)
            msg.arg1 = 4
            mHandler.sendMessage(msg)
            return
        }
        if (!mDetect) {
            // Don't do the other process because press stop button.
            return
        }
        Log.d(TAG, "CardReader, icCardTest, retvalue = $retvalue")
        //        String s1 = new String(respdata);
        Log.v(TAG, "CardReader, icCardTest, s1 = " + HEX.bytesToHex(respdata))
        Log.v(TAG, "CardReader, icCardTest, resplen = " + resplen[0])

        val sendapdu = ByteArray(13)
        sendapdu[0] = 0x00.toByte()
        sendapdu[1] = 0xa4.toByte()
        sendapdu[2] = 0x00.toByte()
        sendapdu[3] = 0x00.toByte()
        sendapdu[4] = 0x02.toByte()
        sendapdu[5] = 0x3f.toByte()
        sendapdu[6] = 0x01.toByte()
        Log.v(TAG, "CardReader, icCardTest, sendapdu = " + String(sendapdu))
        val resp = ByteArray(100)
        var apduret = -1
        try {
            apduret = mBankCard!!.sendAPDU(BankCard.CARD_MODE_ICC, sendapdu, 7, resp, resplen)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        Log.d(TAG, "CardReader, icCardTest, apduret = $apduret")
        Log.v(TAG, "CardReader, icCardTest, resplen = $resplen")
        Log.v(TAG, "CardReader, icCardTest, resp = $resp")

        var whileCondition = false
        do {
            SystemClock.sleep(200)
            Log.v(TAG, "CardReader, icCardTest, detecting---------mDetect = $mDetect")
            //IC 卡在位检测
            try {
                whileCondition = BankCard.CARD_DETECT_EXIST != mBankCard!!.iccDetect()
                Log.d(TAG, "-=-=-= whileCondition = $whileCondition")
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        } while (whileCondition && mDetect)
        val msg = mHandler.obtainMessage(IC)
        if (apduret == 0 && retvalue == 0) {
            msg.arg1 = 0
        } else {
            msg.arg1 = 1
        }
        mHandler.sendMessage(msg)
    }

    private var mAT24: String? = null
    fun at24CardTest() {
        Log.v(TAG, "CardReader, at24CardTest")
        try {
            mCore!!.buzzer()
            Log.v(TAG, "CardReader, at24CardTest, buzzer--->>>")
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        val respdata = ByteArray(100)
        val resplen = IntArray(1)
        var retvalue = -1
        try {
            retvalue = mBankCard!!.readCard(
                BankCard.CARD_TYPE_NORMAL,
                0x0140,
                60,
                respdata,
                resplen,
                "app1"
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        if (respdata[0].toInt() == 0x03) {
            val msg = mHandler.obtainMessage(AT24)
            msg.arg1 = 3
            mHandler.sendMessage(msg)
            return
        } else if (respdata[0].toInt() == 0x04) {
            val msg = mHandler.obtainMessage(IC)
            msg.arg1 = 4
            mHandler.sendMessage(msg)
            return
        }
        if (!mDetect) {
            // Don't do the other process because press stop button.
            return
        }
        Log.d(TAG, "CardReader, icCardTest, retvalue = $retvalue")
        Log.v(TAG, "CardReader, icCardTest, s1 = " + HEX.bytesToHex(respdata))
        Log.v(TAG, "CardReader, icCardTest, resplen = " + resplen[0])

        val msg = mHandler.obtainMessage(AT24)
        if (respdata[0] == 0x45.toByte() && respdata[1] == 0x01.toByte()) {
            msg.arg1 = 0
            mAT24 = when (respdata[2]) {
                0x01.toByte() -> "AT24C01"
                0x02.toByte() -> "AT24C02"
                0x03.toByte() -> "AT24C04"
                0x04.toByte() -> "AT24C08"
                0x05.toByte() -> "AT24C16"
                0x06.toByte() -> "AT24C32"
                0x07.toByte() -> "AT24C64"
                0x08.toByte() -> "AT24C128"
                0x09.toByte() -> "AT24C256"
                0x0A.toByte() -> "AT24C512"
                else -> null  // Optional: Handle unexpected values
            }


        val pwd = ByteArray(3)

            mAT24 += "\nWrite data is: "
            val ori = "0102030405060708"
            mAT24 += """
                
                $ori
                """.trimIndent()
            val data = ByteArray(8)
            data[0] = 0x01.toByte()
            data[1] = 0x02.toByte()
            data[2] = 0x03.toByte()
            data[3] = 0x04.toByte()
            data[4] = 0x05.toByte()
            data[5] = 0x06.toByte()
            data[6] = 0x07.toByte()
            data[7] = 0x08.toByte()

            try {
                mBankCard!!.WriteLogicCardData(pwd, 0, data.size, data)
            } catch (ex: RemoteException) {
                ex.printStackTrace()
            }

            mAT24 += "\nRead Data is: "

            val outdata = ByteArray(8)
            val len = IntArray(1)

            try {
                mBankCard!!.ReadLogicCardData(0, 8, outdata, len)
                Log.i("carddy", mBankCard.toString())
            } catch (ex: RemoteException) {
                ex.printStackTrace()
            }
            mAT24 += ByteUtil.bytes2HexString(outdata)
        } else {
            msg.arg1 = 1
        }
        mHandler.sendMessage(msg)
    }

    private var mC4428: String? = null
    fun Card4428Test() {
        Log.v(TAG, "CardReader, Card4428Test")
        try {
            mCore!!.buzzer()
            Log.v(TAG, "CardReader, Card4428Test, buzzer--->>>")
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        val respdata = ByteArray(100)
        val resplen = IntArray(1)
        var retvalue = -1
        try {
            retvalue =
                mBankCard!!.readCard(BankCard.CARD_TYPE_NORMAL, 320, 60, respdata, resplen, "app1")
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        if (respdata[0].toInt() == 0x03) {
            val msg = mHandler.obtainMessage(C4428)
            msg.arg1 = 3
            mHandler.sendMessage(msg)
            return
        } else if (respdata[0].toInt() == 0x04) {
            val msg = mHandler.obtainMessage(IC)
            msg.arg1 = 4
            mHandler.sendMessage(msg)
            return
        }
        if (!mDetect) {
            // Don't do the other process because press stop button.
            return
        }
        Log.d(TAG, "CardReader, icCardTest, retvalue = $retvalue")
        Log.v(TAG, "CardReader, icCardTest, s1 = " + HEX.bytesToHex(respdata))
        Log.v(TAG, "CardReader, icCardTest, resplen = " + resplen[0])

        val msg = mHandler.obtainMessage(C4428)
        if (respdata[0].toInt() == 0x25) {
            msg.arg1 = 0
            mC4428 += "4428 "

            val pwd = ByteArray(3)
            pwd[0] = 0xff.toByte()
            pwd[1] = 0xff.toByte()
            pwd[2] = 0xff.toByte()

            try {
                mBankCard!!.VerifyLogicCardPwd(pwd)
            } catch (ex: RemoteException) {
                ex.printStackTrace()
            }

            val data = ByteArray(8)

            mC4428 += "\nWrite data is: "
            for (i in data.indices) {
                data[i] = i.toByte()
                mC4428 += data[i]
            }

            try {
                mBankCard!!.WriteLogicCardData(pwd, 0x0A, data.size, data)
            } catch (ex: RemoteException) {
                ex.printStackTrace()
            }

            mC4428 += "\nRead Data is: "

            val outdata = ByteArray(16)
            val len = IntArray(1)

            try {
                mBankCard!!.ReadLogicCardData(0x0A, 8, outdata, len)
            } catch (ex: RemoteException) {
                ex.printStackTrace()
            }
            mC4428 += HEX.bytesToHex(outdata)
            Log.v(TAG, mC4428!!)

            //4428卡 APDU Verify
            var APDU_CMD = ByteArray(7)
            APDU_CMD[0] = 0xFF.toByte()
            APDU_CMD[1] = 0x20.toByte()
            APDU_CMD[2] = 0x00.toByte()
            APDU_CMD[3] = 0x00.toByte()
            APDU_CMD[4] = 0x02.toByte()
            APDU_CMD[5] = 0xFF.toByte()
            APDU_CMD[6] = 0xFF.toByte()
            Log.v(TAG, "CardReader, 4428 Verify, send APDU = " + ByteUtil.bytes2HexString(APDU_CMD))
            var APDU_Result = ByteArray(2)
            var APDU_Result_Len = IntArray(1)
            var apduret = -1
            try {
                apduret = mBankCard!!.sendAPDU(
                    BankCard.CARD_MODE_ICC,
                    APDU_CMD,
                    APDU_CMD.size,
                    APDU_Result,
                    APDU_Result_Len
                )
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
            Log.d(TAG, "CardReader, 4428 Verify, apduret = $apduret")
            Log.v(
                TAG,
                "CardReader, 4428 Verify, result data = " + ByteUtil.bytes2HexString(APDU_Result)
            )
            Log.v(TAG, "CardReader, 4428 Verify, result data length = " + APDU_Result_Len[0])
            //Read
            APDU_CMD = ByteArray(7)
            APDU_CMD[0] = 0xFF.toByte()
            APDU_CMD[1] = 0xB0.toByte()
            APDU_CMD[2] = 0x00.toByte()
            APDU_CMD[3] = 0x0A.toByte()
            APDU_CMD[4] = 0x08.toByte()
            Log.v(TAG, "CardReader, 4428 Read, send APDU = " + ByteUtil.bytes2HexString(APDU_CMD))
            APDU_Result = ByteArray(10)
            APDU_Result_Len = IntArray(1)
            apduret = -1
            try {
                apduret = mBankCard!!.sendAPDU(
                    BankCard.CARD_MODE_ICC,
                    APDU_CMD,
                    APDU_CMD.size,
                    APDU_Result,
                    APDU_Result_Len
                )
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
            Log.d(TAG, "CardReader, 4428 Read, apduret = $apduret")
            Log.v(
                TAG,
                "CardReader, 4428 Read, result data = " + ByteUtil.bytes2HexString(APDU_Result)
            )
            Log.v(TAG, "CardReader, 4428 Read, result data length = " + APDU_Result_Len[0])

            //Whrite
            APDU_CMD = ByteArray(13)
            APDU_CMD[0] = 0xFF.toByte()
            APDU_CMD[1] = 0xD6.toByte()
            APDU_CMD[2] = 0x00.toByte()
            APDU_CMD[3] = 0x0A.toByte()
            APDU_CMD[4] = 0x08.toByte()
            APDU_CMD[5] = 0x01.toByte()
            APDU_CMD[6] = 0x02.toByte()
            APDU_CMD[7] = 0x03.toByte()
            APDU_CMD[8] = 0x04.toByte()
            APDU_CMD[9] = 0x05.toByte()
            APDU_CMD[10] = 0x06.toByte()
            APDU_CMD[11] = 0x07.toByte()
            APDU_CMD[12] = 0x08.toByte()
            Log.v(TAG, "CardReader, 4428 whrite, send APDU = " + ByteUtil.bytes2HexString(APDU_CMD))
            APDU_Result = ByteArray(2)
            APDU_Result_Len = IntArray(1)
            apduret = -1
            try {
                apduret = mBankCard!!.sendAPDU(
                    BankCard.CARD_MODE_ICC,
                    APDU_CMD,
                    APDU_CMD.size,
                    APDU_Result,
                    APDU_Result_Len
                )
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
            Log.d(TAG, "CardReader, 4428 whrite, apduret = $apduret")
            Log.v(
                TAG,
                "CardReader, 4428 whrite, result data = " + ByteUtil.bytes2HexString(APDU_Result)
            )
            Log.v(TAG, "CardReader, 4428 whrite, result data length = " + APDU_Result_Len[0])
        } else {
            msg.arg1 = 1
        }
        mHandler.sendMessage(msg)
    }

    private var mC4442: String? = null
    private fun Card4442Test() {
        Log.v(TAG, "CardReader, Card4442Test")
        try {
            mCore!!.buzzer()
            Log.v(TAG, "CardReader, Card4442Test, buzzer--->>>")
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        val respdata = ByteArray(100)
        val resplen = IntArray(1)
        var retvalue = -1
        try {
            retvalue =
                mBankCard!!.readCard(BankCard.CARD_TYPE_NORMAL, 320, 60, respdata, resplen, "app1")
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        if (respdata[0].toInt() == 0x03) {
            val msg = mHandler.obtainMessage(C4442)
            msg.arg1 = 3
            mHandler.sendMessage(msg)
            return
        } else if (respdata[0].toInt() == 0x04) {
            val msg = mHandler.obtainMessage(IC)
            msg.arg1 = 4
            mHandler.sendMessage(msg)
            return
        }
        if (!mDetect) {
            // Don't do the other process because press stop button.
            return
        }
        Log.d(TAG, "CardReader, Card4442Test, retvalue = $retvalue")
        Log.v(TAG, "CardReader, Card4442Test, s1 = " + HEX.bytesToHex(respdata))
        Log.v(TAG, "CardReader, Card4442Test, resplen = " + resplen[0])

        val msg = mHandler.obtainMessage(C4442)
        if (respdata[0].toInt() == 0x15) {
            msg.arg1 = 0
            mC4442 += "4442 "

            val pwd = ByteArray(3)
            pwd[0] = 0xff.toByte()
            pwd[1] = 0xff.toByte()
            pwd[2] = 0xff.toByte()

            try {
                val res = mBankCard!!.VerifyLogicCardPwd(pwd)
                Log.d(TAG, "xxxxxxxxxx, res= " + res);
            } catch (ex: RemoteException) {
                ex.printStackTrace()
            }

            val data = ByteArray(8)

            mC4442 += "\nWrite data is: "
            for (i in data.indices) {
                data[i] = i.toByte()
                mC4442 += data[i]
            }

            try {
                mBankCard!!.WriteLogicCardData(pwd, 0x0A, data.size, data)
            } catch (ex: RemoteException) {
                ex.printStackTrace()
            }

            mC4442 += "\nRead Data is: "

            val outdata = ByteArray(8)
            val len = IntArray(1)

            try {
                mBankCard!!.ReadLogicCardData(0x0A, 8, outdata, len)
            } catch (ex: RemoteException) {
                ex.printStackTrace()
            }
            mC4442 += HEX.bytesToHex(outdata)
            Log.v(TAG, mC4442!!)
        } else {
            msg.arg1 = 1
        }
        mHandler.sendMessage(msg)
    }

    private var at88sc102: String? = null
    private fun Cardbuttonat88sc102Test() {
        Log.v(TAG, "CardReader, at88sc102")
        try {
            mCore!!.buzzer()
            Log.v(TAG, "CardReader, at88sc102, buzzer--->>>")
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        val respdata = ByteArray(100)
        val resplen = IntArray(1)
        var retvalue = -1
        try {
            retvalue = mBankCard!!.readCard(
                BankCard.CARD_TYPE_NORMAL,
                0x0140,
                60,
                respdata,
                resplen,
                "app1"
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        if (respdata[0].toInt() == 0x03) {
            val msg = mHandler.obtainMessage(at88sc)
            msg.arg1 = 3
            mHandler.sendMessage(msg)
            return
        } else if (respdata[0].toInt() == 0x04) {
            val msg = mHandler.obtainMessage(IC)
            msg.arg1 = 4
            mHandler.sendMessage(msg)
            return
        }
        if (!mDetect) {
            // Don't do the other process because press stop button.
            return
        }
        Log.d(TAG, "CardReader, at88sc102, retvalue = $retvalue")
        Log.v(TAG, "CardReader, at88sc102, s1 = " + HEX.bytesToHex(respdata))
        Log.v(TAG, "CardReader, at88sc102, resplen = " + resplen[0])

        val msg = mHandler.obtainMessage(C4442)
        if (respdata[0].toInt() == 0x35) {
            msg.arg1 = 0
            at88sc102 += "at88sc102 "

            val pwd = ByteArray(3)
            pwd[0] = 0xff.toByte()
            pwd[1] = 0xff.toByte()
            pwd[2] = 0xff.toByte()

            try {
                mBankCard!!.VerifyLogicCardPwd(pwd)
            } catch (ex: RemoteException) {
                ex.printStackTrace()
            }

            val data = ByteArray(8)

            at88sc102 += "\nWrite data is: "
            for (i in data.indices) {
                data[i] = i.toByte()
                at88sc102 += data[i]
            }
            try {
                mBankCard!!.WriteLogicCardData(pwd, 0x0A, data.size, data)
            } catch (ex: RemoteException) {
                ex.printStackTrace()
            }
            at88sc102 += "\nRead Data is: "

            val outdata = ByteArray(8)
            val len = IntArray(1)

            try {
                mBankCard!!.ReadLogicCardData(0x0A, 8, outdata, len)
            } catch (ex: RemoteException) {
                ex.printStackTrace()
            }
            at88sc102 += HEX.bytesToHex(outdata)
            Log.v(TAG, at88sc102!!)
        } else {
            msg.arg1 = 1
        }
        mHandler.sendMessage(msg)
    }

    fun M0() {
        //蜂鸣器
        try {
            mCore!!.buzzer()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        Log.v(TAG, "bankcard readcard")
        val respdata = ByteArray(255)
        val resplen = IntArray(1)
        var retvalue = 0
        try {
            retvalue = mBankCard!!.readCard(
                BankCard.CARD_TYPE_NORMAL,
                BankCard.CARD_MODE_PICC,
                60,
                respdata,
                resplen,
                "app1"
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        if (respdata[0].toInt() == 0x03) {
            val msg = mHandler.obtainMessage(M0)
            msg.arg1 = 3
            mHandler.sendMessage(msg)
            return
        } else if (respdata[0].toInt() == 0x04) {
            val msg = mHandler.obtainMessage(IC)
            msg.arg1 = 4
            mHandler.sendMessage(msg)
            return
        }
        if (!mDetect) {
            return
        }
        val msg = mHandler.obtainMessage(M0)
        if (retvalue == 0x00) {
            if (respdata[0].toInt() == 0x57) {
                stringBuilder!!.append("detecte M0 success\n")
                try {
//                    byte[] sn = new byte[16];
//                    int[] pes = new int[1];
//                    int resSn = mBankCard.getCardSNFunction(sn,pes);
//                    Log.v(TAG, "m1CardSNFunction, respes--->>>"+pes[0]);
//                    Log.v(TAG, "m1CardSNFunction, resSn--->>>"+HEX.bytesToHex(sn));
//
//                    byte[] res = new byte[pes[0]];
//                    System.arraycopy(sn,0,res,0,pes[0]);
//                    String result = HEX.bytesToHex(res);
//                    Log.d("xxx","获取ID"+result);

                    val indata = ByteArray(4)
                    indata[0] = 0x00.toByte()
                    indata[1] = 0x01.toByte()
                    indata[2] = 0x02.toByte()
                    indata[3] = 0x03.toByte()
                    val resIn = mBankCard!!.NFCTagWriteBlock(0x0A, indata)
                    Log.v(TAG, "CardReader, resIn--->>>$resIn")
                    if (resIn == 0) {
                        stringBuilder!!.append("M0 Write success\n")
                        val outdata = ByteArray(20)
                        val oulen = IntArray(1)
                        val resOut = mBankCard!!.NFCTagReadBlock(0x0A, outdata, oulen)
                        if (resOut == 0) {
                            val snData = ByteArray(oulen[0] - 1)
                            System.arraycopy(outdata, 1, snData, 0, oulen[0] - 1)
                            msg.arg1 = 0
                            stringBuilder!!.append("M0 read success\n")
                            Log.v(TAG, "CardReader, M0--->>>" + HEX.bytesToHex(snData))
                        } else {
                            stringBuilder!!.append("M0 read fail\n")
                        }
                    } else {
                        stringBuilder!!.append("M0 write fail\n")
                    }
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            } else {
                stringBuilder!!.append("detecte M0 fail\n")
                msg.arg1 = 1
            }
        } else {
            stringBuilder!!.append("detecte M0 fail\n")
            msg.arg1 = 1
        }
        mHandler.sendMessage(msg)
    }

    fun M1() {
        //蜂鸣器
        try {
            mCore!!.buzzer()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        Log.v(TAG, "bankcard readcard")
        val respdata = ByteArray(255)
        val resplen = IntArray(1)
        var retvalue = 0
        try {
            retvalue = mBankCard!!.readCard(
                BankCard.CARD_TYPE_NORMAL,
                BankCard.CARD_MODE_PICC,
                60,
                respdata,
                resplen,
                "app1"
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        if (respdata[0].toInt() == 0x03) {
            val msg = mHandler.obtainMessage(M1)
            msg.arg1 = 3
            mHandler.sendMessage(msg)
            return
        } else if (respdata[0].toInt() == 0x04) {
            val msg = mHandler.obtainMessage(IC)
            msg.arg1 = 4
            mHandler.sendMessage(msg)
            return
        }
        if (!mDetect) {
            return
        }
        val msg = mHandler.obtainMessage(M1)
        if (retvalue == 0x00) {
            Log.v(TAG, "m1CardSNFunction, respdata--->>>" + respdata[0])
            if (respdata[0].toInt() == 0x37 || respdata[0].toInt() == 0x47) { //0x37:M1-S50卡;0x47:M1-S70卡
                try {
                    val keyData = ByteArray(6)
                    for (i in keyData.indices) {
                        keyData[i] = 0xFF.toByte()
                    }

                    val sn = ByteArray(16)
                    val pes = IntArray(1)
                    val resSn = mBankCard!!.getCardSNFunction(sn, pes)
                    if (resSn == 0) {
                        stringBuilder!!.append("M1 CardSNFunction success\n")
                    } else {
                        stringBuilder!!.append("M1 CardSNFunction fail\n")
                    }
                    Log.v(TAG, "m1CardSNFunction, respes--->>>" + pes[0])
                    Log.v(TAG, "m1CardSNFunction, resSn--->>>" + HEX.bytesToHex(sn))
                    val snData = ByteArray(pes[0])
                    System.arraycopy(sn, 0, snData, 0, pes[0])
                    Log.v(TAG, "m1CardSNFunction, ressnData--->>>" + HEX.bytesToHex(snData))
                    val resKeyAuth = mBankCard!!.m1CardKeyAuth(
                        0x41,
                        0x0A,
                        keyData.size,
                        keyData,
                        snData.size,
                        snData
                    )
                    Log.v(TAG, "m1CardKeyAuth, resKeyAuth--->>>$resKeyAuth")
                    if (resKeyAuth == 0) {
                        stringBuilder!!.append("M1 CardKeyAuth success\n")
                        val writeData = ByteArray(16)
                        writeData[0] = 0x00.toByte()
                        writeData[1] = 0x01.toByte()
                        writeData[2] = 0x02.toByte()
                        writeData[3] = 0x03.toByte()
                        val resWrite =
                            mBankCard!!.m1CardWriteBlockData(0x0A, writeData.size, writeData)
                        if (resWrite == 0) {
                            stringBuilder!!.append("M1 Write success\n")
                            val readData = ByteArray(20)
                            val readLen = IntArray(1)
                            val resRead = mBankCard!!.m1CardReadBlockData(0x0A, readData, readLen)
                            if (resRead == 0) {
                                stringBuilder!!.append("M1 Read success\n")
                                Log.v(
                                    TAG,
                                    "m1CardReadBlockData, readDataOrl--->>>" + HEX.bytesToHex(
                                        readData
                                    )
                                )
                                val read = ByteArray(readLen[0] - 1)
                                System.arraycopy(readData, 1, read, 0, read.size / 2)
                                Log.v(
                                    TAG,
                                    "m1CardReadBlockData, readData--->>>" + HEX.bytesToHex(read)
                                )
                                msg.arg1 = 0
                                val resValue = mBankCard!!.m1CardValueOperation(0x2B, 0x0A, 1, 0x0A)
                                if (resValue == 0) {
                                    msg.arg1 = 0
                                    stringBuilder!!.append("M1 ValueOperation success\n")
                                } else {
                                    stringBuilder!!.append("M1 ValueOperation fail\n")
                                    msg.arg1 = 1
                                }
                            } else {
                                stringBuilder!!.append("M1 read fail\n")
                                msg.arg1 = 1
                            }
                        } else {
                            stringBuilder!!.append("M1 Write fail\n")
                            msg.arg1 = 1
                        }
                    } else {
                        stringBuilder!!.append("M1 CardKeyAuth fail\n")
                        msg.arg1 = 1
                    }
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            } else {
                stringBuilder!!.append("detecte M1 fail\n")
                msg.arg1 = 1
            }
        } else {
            stringBuilder!!.append("detecte M1 fail\n")
            msg.arg1 = 1
        }
        mHandler.sendMessage(msg)
    }

    fun M1_quick() {
        //蜂鸣器
        try {
            mCore!!.buzzer()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        Log.v(TAG, "bankcard readcard")
        val respdata = ByteArray(255)
        val resplen = IntArray(1)
        var retvalue = 0
        try {
            retvalue = mBankCard!!.readCard(
                BankCard.CARD_TYPE_NORMAL,
                BankCard.CARD_MODE_PICC,
                60,
                respdata,
                resplen,
                "app1"
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        if (respdata[0].toInt() == 0x03) {
            val msg = mHandler.obtainMessage(M1)
            msg.arg1 = 3
            mHandler.sendMessage(msg)
            return
        } else if (respdata[0].toInt() == 0x04) {
            val msg = mHandler.obtainMessage(IC)
            msg.arg1 = 4
            mHandler.sendMessage(msg)
            return
        }
        if (!mDetect) {
            return
        }
        val msg = mHandler.obtainMessage(M1)
        if (retvalue == 0x00) {
            Log.v(TAG, "m1CardSNFunction, respdata--->>>" + respdata[0])
            if (respdata[0].toInt() == 0x37 || respdata[0].toInt() == 0x47) { //0x37:M1-S50卡;0x47:M1-S70卡
                try {
                    val keyData = ByteArray(6)
                    for (i in keyData.indices) {
                        keyData[i] = 0xFF.toByte()
                    }

                    val sn = ByteArray(16)
                    val pes = IntArray(1)
                    val resSn = mBankCard!!.getCardSNFunction(sn, pes)
                    if (resSn == 0) {
                        stringBuilder!!.append("M1 CardSNFunction success\n")
                    } else {
                        stringBuilder!!.append("M1 CardSNFunction fail\n")
                    }
                    Log.v(TAG, "m1CardSNFunction, respes--->>>" + pes[0])
                    Log.v(TAG, "m1CardSNFunction, resSn--->>>" + HEX.bytesToHex(sn))
                    val snData = ByteArray(pes[0])
                    System.arraycopy(sn, 0, snData, 0, pes[0])
                    Log.v(TAG, "m1CardSNFunction, ressnData--->>>" + HEX.bytesToHex(snData))
                    val len_readblocknum1 = IntArray(1)
                    val data_readblocknum1 = ByteArray(20)
                    val len_readblocknum2 = IntArray(1)
                    val data_readblocknum2 = ByteArray(20)
                    val len_readblocknum3 = IntArray(1)
                    val data_readblocknum3 = ByteArray(20)
                    val resKeyAuth = mBankCard!!.m1CardKeyAuthAndReadBlockData(
                        0x41,
                        0x08,
                        keyData.size,
                        keyData,
                        snData.size,
                        snData,
                        0x08,
                        0x09,
                        0x0A,
                        len_readblocknum1,
                        data_readblocknum1,
                        len_readblocknum2,
                        data_readblocknum2,
                        len_readblocknum3,
                        data_readblocknum3
                    )
                    Log.v(TAG, "m1CardKeyAuthAndReadBlockData, resKeyAuth--->>>$resKeyAuth")
                    Log.i(
                        TAG,
                        "m1CardKeyAuthAndReadBlockData: len_readblocknum1,data_readblocknum1:" + ByteUtil.bytes2HexString(
                            data_readblocknum1
                        ) +
                                "len_readblocknum2, data_readblocknum2:" + ByteUtil.bytes2HexString(
                            data_readblocknum2
                        ) +
                                "len_readblocknum3, data_readblocknum3:" + ByteUtil.bytes2HexString(
                            data_readblocknum3
                        )
                    )
                    if (resKeyAuth == 0) {
                        stringBuilder!!.append("M1 m1CardKeyAuthAndReadBlockData success\n")
                        val writeData = ByteArray(16)
                        writeData[0] = 0x55.toByte()
                        writeData[1] = 0x55.toByte()
                        writeData[2] = 0x55.toByte()
                        writeData[3] = 0x55.toByte()
                        writeData[4] = 0xAA.toByte()
                        writeData[5] = 0xAA.toByte()
                        writeData[6] = 0xAA.toByte()
                        writeData[7] = 0xAA.toByte()
                        writeData[8] = 0x55.toByte()
                        writeData[9] = 0x55.toByte()
                        writeData[10] = 0x55.toByte()
                        writeData[11] = 0x55.toByte()
                        writeData[12] = 0x01.toByte()
                        writeData[13] = 0xFE.toByte()
                        writeData[14] = 0x01.toByte()
                        writeData[15] = 0xFE.toByte()
                        val outdata = ByteArray(20)
                        val outdatalen = IntArray(1)
                        val resWrite = mBankCard!!.m1CardWriteAndReadBlockData(
                            0x09,
                            writeData.size,
                            writeData,
                            outdata,
                            outdatalen
                        )
                        if (resWrite == 0) {
                            stringBuilder!!.append("M1 Write success\n")
                            val readData = ByteArray(20)
                            val readLen = IntArray(1)
                            val resRead = mBankCard!!.m1CardWriteAndReadBlockData(
                                0x0A,
                                writeData.size,
                                writeData,
                                readData,
                                readLen
                            )
                            if (resRead == 0) {
                                stringBuilder!!.append("M1 Read success\n")
                                Log.v(
                                    TAG,
                                    "m1CardReadBlockData, readDataOrl--->>>" + HEX.bytesToHex(
                                        readData
                                    )
                                )
                                val read = ByteArray(readLen[0] - 1)
                                System.arraycopy(readData, 1, read, 0, read.size / 2)
                                Log.v(
                                    TAG,
                                    "m1CardReadBlockData, readData--->>>" + HEX.bytesToHex(read)
                                )
                                msg.arg1 = 0
                                val data = ByteArray(20)
                                val dataLen = IntArray(1)
                                val resValue = mBankCard!!.m1CardValueOperationAndReadBlockData(
                                    0x2B,
                                    0x0A,
                                    1,
                                    0x0A,
                                    data,
                                    dataLen
                                )
                                if (resValue == 0) {
                                    msg.arg1 = 0
                                    stringBuilder!!.append("M1 ValueOperation success\n")
                                } else {
                                    stringBuilder!!.append("M1 ValueOperation fail\n")
                                    msg.arg1 = 1
                                }
                            } else {
                                stringBuilder!!.append("M1 read fail\n")
                                msg.arg1 = 1
                            }
                        } else {
                            stringBuilder!!.append("M1 Write fail\n")
                            msg.arg1 = 1
                        }
                    } else {
                        stringBuilder!!.append("M1 CardKeyAuth fail\n")
                        msg.arg1 = 1
                    }
                } catch (e: RemoteException) {
                    e.printStackTrace()
                }
            } else {
                stringBuilder!!.append("detecte M1 fail\n")
                msg.arg1 = 1
            }
        } else {
            stringBuilder!!.append("detecte M1 fail\n")
            msg.arg1 = 1
        }
        mHandler.sendMessage(msg)
    }

    fun magTest() {
        val respdata = ByteArray(1024)
        val resplen = IntArray(1)
        var retvalue = -1
        try {
            mCore!!.buzzer()
            Log.v(TAG, "CardReader, magTest, buzzer--->>>")
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        try {
            retvalue = mBankCard!!.readCard(
                BankCard.CARD_TYPE_NORMAL,
                BankCard.CARD_MODE_MAG,
                60,
                respdata,
                resplen,
                "app1"
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        if (retvalue == 11) {
            val msg = mHandler.obtainMessage(MAG)
            msg.arg1 = 10
            mHandler.sendMessage(msg)
            return
        }
        if (respdata[0].toInt() == 0x03) {
            val msg = mHandler.obtainMessage(MAG)
            msg.arg1 = 3
            mHandler.sendMessage(msg)
            return
        } else if (respdata[0].toInt() == 0x04) {
            val msg = mHandler.obtainMessage(MAG)
            msg.arg1 = 4
            mHandler.sendMessage(msg)
            return
        } else if (respdata[0].toInt() == 0x00) {
            val s1 = HEX.bytesToHex(respdata)
            Log.v(TAG, "CardReader, magTest, s1 = $s1")
            Log.v(TAG, "CardReader, magTest, resplen = " + resplen[0])

            val mag1 = ByteArray(128)
            val magLen1 = IntArray(1)
            val mag2 = ByteArray(64)
            val magLen2 = IntArray(1)
            val mag3 = ByteArray(128)
            val magLen3 = IntArray(1)
            retvalue = mBankCard!!.parseMagnetic(
                respdata,
                respdata.size,
                mag1,
                magLen1,
                mag2,
                magLen2,
                mag3,
                magLen3
            )
            if (retvalue == 0) {
                val m1 = HEX.bytesToHex(mag1)
                Log.v(TAG, "CardReader, magTest, HEX-m1 = $m1")
                Log.v(TAG, "CardReader, magTest, resplen1 = " + magLen1[0])
                Log.v(TAG, "CardReader, magTest, m1 = " + String(mag1).substring(0, magLen1[0]))

                val m2 = HEX.bytesToHex(mag2)
                Log.v(TAG, "CardReader, magTest, HEX-m2 = $m2")
                Log.v(TAG, "CardReader, magTest, resplen2 = " + magLen2[0])
                Log.v(TAG, "CardReader, magTest, m2 = " + String(mag2).substring(0, magLen2[0]))

                val m3 = HEX.bytesToHex(mag3)
                Log.v(TAG, "CardReader, magTest, HEX-m3 = $m3")
                Log.v(TAG, "CardReader, magTest, resplen3 = " + magLen3[0])
                Log.v(TAG, "CardReader, magTest, m3 = " + String(mag3).substring(0, magLen3[0]))
            }
        }
        if (!mDetect) {
            // Don't do the other process because press stop button.
            return
        }

        val msg = mHandler.obtainMessage(MAG)
        if (retvalue == 0) {
            msg.arg1 = 0
        } else {
            msg.arg1 = 1
        }
        mHandler.sendMessage(msg)
    }

    private fun desFireTest() {
        Log.v(TAG, "CardReader, DesFire Card Test")
        try {
            mCore!!.buzzer()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        val respdata = ByteArray(100)
        val resplen = IntArray(1)
        var retvalue = -1
        try {
            retvalue = mBankCard!!.readCard(
                BankCard.CARD_TYPE_NORMAL,
                BankCard.CARD_MODE_PICC,
                60,
                respdata,
                resplen,
                "app1"
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        if (respdata[0].toInt() == 0x03) {
            val msg = mHandler.obtainMessage(C4442)
            msg.arg1 = 3
            mHandler.sendMessage(msg)
            return
        } else if (respdata[0].toInt() == 0x04) {
            val msg = mHandler.obtainMessage(IC)
            msg.arg1 = 4
            mHandler.sendMessage(msg)
            return
        }
        if (!mDetect) {
            // Don't do the other process because press stop button.
            return
        }
        Log.d(TAG, "CardReader, icCardTest, retvalue = $retvalue")
        Log.v(TAG, "CardReader, icCardTest, s1 = " + HEX.bytesToHex(respdata))
        Log.v(TAG, "CardReader, icCardTest, resplen = " + resplen[0])

        val msg = mHandler.obtainMessage(DESFIRE)
        val code = ByteUtil.intToHexString(respdata[0].toInt())
        if ("0087" == code) {
//            byte[] apdu = new byte[5];
//            apdu[0] = (byte) 0x00;
//            apdu[1] = (byte) 0x84;
//            apdu[2] = (byte) 0x00;
//            apdu[3] = (byte) 0x00;
//            apdu[4] = (byte) 0x08;
//            try {
//                byte[] outData = new byte[256];
//                int [] outDataLen = new int[1];
//                retvalue = mBankCard.DesFire_ISO7816(apdu, outData, outDataLen);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//            if (retvalue != 0){
//                    msg.arg1 = 1;
//                    Msg = "DesFire_ISO7816 fail=="+retvalue;
//                    mHandler.sendMessage(msg);
//                    return;
//                }
//
//            if (true) {
//                return;
//            }

            try {
                val aidData = byteArrayOf(0x00, 0x00, 0x01)
                val outData = ByteArray(256)
                val outDataLen = IntArray(1)
                retvalue = mBankCard!!.DesFire_SelApp(aidData.size, aidData, outData, outDataLen)
                if (retvalue != 0) {
                    msg.arg1 = 1
                    stringBuilder!!.append("DesFire_SelApp fail\n")
                    mHandler.sendMessage(msg)
                    return
                } else {
                    stringBuilder!!.append("DesFire_SelApp success\n")
                }
                Log.d(
                    TAG, """
     Data=${ByteUtil.bytes2HexString(outData)}
     outDataLen=${outDataLen[0]}
     """.trimIndent()
                )
                val keyNo = 0
                val keyType = 0
                val keyData = byteArrayOf(
                    0x11,
                    0x11,
                    0x11,
                    0x11,
                    0x11,
                    0x11,
                    0x11,
                    0x11,
                    0x22,
                    0x22,
                    0x22,
                    0x22,
                    0x22,
                    0x22,
                    0x22,
                    0x22
                )
                val keyLen = keyData.size
                retvalue = mBankCard!!.DesFire_Auth(keyNo, keyType, keyLen, keyData)
                if (retvalue != 0) {
                    msg.arg1 = 1
                    stringBuilder!!.append("DesFire_Auth fail\n")
                    mHandler.sendMessage(msg)
                    return
                } else {
                    stringBuilder!!.append("DesFire_Auth success\n")
                }
                val mode = 0
                val id = 2
                retvalue = mBankCard!!.DesFire_GetCardInfo(mode, id, outData, outDataLen)
                if (retvalue != 0) {
                    msg.arg1 = 1
                    stringBuilder!!.append("DesFire_GetCardInfo fail\n")
                    mHandler.sendMessage(msg)
                    return
                } else {
                    stringBuilder!!.append("DesFire_GetCardInfo success\n")
                }
                Log.d(
                    TAG, """
     Data=${ByteUtil.bytes2HexString(outData)}
     outDataLen=${outDataLen[0]}
     """.trimIndent()
                )
                val fileType = 0
                val fileId = 0
                val offset = 0
                val writeData = byteArrayOf(
                    0x01,
                    0x02,
                    0x03,
                    0x04,
                    0x05,
                    0x06,
                    0x07,
                    0x08,
                    0x01,
                    0x02,
                    0x03,
                    0x04,
                    0x05,
                    0x06,
                    0x07,
                    0x08
                )
                val writeLen = writeData.size
                retvalue = mBankCard!!.DesFire_WriteFile(
                    fileType,
                    fileId,
                    offset,
                    writeLen,
                    writeData,
                    outData,
                    outDataLen
                )
                if (retvalue != 0) {
                    msg.arg1 = 1
                    stringBuilder!!.append("DesFire_WriteFile fail\n")
                    mHandler.sendMessage(msg)
                    return
                } else {
                    stringBuilder!!.append("DesFire_WriteFile success\n")
                }
                var readLen = 16
                retvalue = mBankCard!!.DesFire_ReadFile(
                    fileType,
                    fileId,
                    offset,
                    readLen,
                    outData,
                    outDataLen
                )
                if (retvalue != 0) {
                    msg.arg1 = 1
                    stringBuilder!!.append("DesFire_ReadFile fail\n")
                    mHandler.sendMessage(msg)
                    return
                } else {
                    stringBuilder!!.append("DesFire_ReadFile success\n")
                }
                readLen = 0
                retvalue = mBankCard!!.DesFire_ReadFile(
                    fileType + 2,
                    fileId + 1,
                    offset,
                    readLen,
                    outData,
                    outDataLen
                )
                if (retvalue != 0) {
                    msg.arg1 = 1
                    stringBuilder!!.append("DesFire_ReadFile fail\n")
                    mHandler.sendMessage(msg)
                    return
                } else {
                    stringBuilder!!.append("DesFire_ReadFile success\n")
                }
                Log.d(
                    TAG, """
     Data1=${ByteUtil.bytes2HexString(outData)}
     outDataLen=${outDataLen[0]}
     """.trimIndent()
                )

                val operateValue = byteArrayOf(0x00, 0x00, 0x00, 0x01)
                retvalue = mBankCard!!.DesFire_ValueFileOpr(
                    fileType + 1,
                    fileId + 1,
                    operateValue,
                    outData,
                    outDataLen
                )
                if (retvalue != 0) {
                    msg.arg1 = 1
                    stringBuilder!!.append("DesFire_ValueFileOpration fail\n")
                    mHandler.sendMessage(msg)
                    return
                } else {
                    stringBuilder!!.append("DesFire_ValueFileOpration success\n")
                }
                Log.d(
                    TAG, """
     Data2=${ByteUtil.bytes2HexString(outData)}
     outDataLen=${outDataLen[0]}
     """.trimIndent()
                )
                retvalue = mBankCard!!.DesFire_Comfirm_Cancel(fileType + 1, outData, outDataLen)
                if (retvalue != 0) {
                    msg.arg1 = 1
                    stringBuilder!!.append("DesFire_Comfirm_Cancel fail\n")
                    mHandler.sendMessage(msg)
                    return
                } else {
                    stringBuilder!!.append("DesFire_Comfirm_Cancel success\n")
                }
                readLen = 0
                retvalue = mBankCard!!.DesFire_ReadFile(
                    fileType + 2,
                    fileId + 1,
                    offset,
                    readLen,
                    outData,
                    outDataLen
                )
                if (retvalue != 0) {
                    msg.arg1 = 1
                    stringBuilder!!.append("DesFire_ReadFile fail\n")
                    mHandler.sendMessage(msg)
                    return
                } else {
                    stringBuilder!!.append("DesFire_ReadFile success\n")
                }
                Log.d(
                    TAG, """
     Data3=${ByteUtil.bytes2HexString(outData)}
     outDataLen=${outDataLen[0]}
     """.trimIndent()
                )
                msg.arg1 = 0
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        } else {
            msg.arg1 = 1
            stringBuilder!!.append("detecte DesFire fail\n")
        }
        mHandler.sendMessage(msg)
    }

    private fun setMBtnPsam2Visibility() {
        val model = Build.MODEL
        if (WANG_POS_TAB == model || WANG_POS_MIN == model) {
            mBtnPsam2!!.visibility = View.GONE
        }
    }

    /*------ Create zhanghai 2018-11-6 Note Begin -----*/ /*------ Update zhanghai 2018-11-6 Note-----*/
    override fun onStop() {
        super.onStop()
        try {
            mDetect = false
            if (mThread != null) {
                mThread!!.interrupt()
                mThread = null
            }
            mBankCard!!.breakOffCommand()
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        tvcardreadershow!!.setText(R.string.test_please)
    }

    fun piccCardTestms() {
        Log.v(TAG, "CardReader, piccCardTest")
        try {
            mCore!!.buzzer()
            Log.v(TAG, "CardReader, piccCardTest, buzzer--->>>")
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        val respdata = ByteArray(1024)
        val resplen = IntArray(1)
        var retvalue = -1
        try {
            retvalue = mBankCard!!.readCardms(
                BankCard.CARD_TYPE_NORMAL,
                BankCard.CARD_MODE_PICC,
                1024,
                respdata,
                resplen,
                "app1"
            )
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        if (respdata[0].toInt() == 0x03) {
            val msg = mHandler.obtainMessage(PICC)
            msg.arg1 = 3
            mHandler.sendMessage(msg)
            return
        } else if (respdata[0].toInt() == 0x04) {
            val msg = mHandler.obtainMessage(PICC)
            msg.arg1 = 4
            mHandler.sendMessage(msg)
            return
        }

        if (!mDetect) {
            // Don't do the other process because press stop button.
            return
        }

        val s1 = HEX.bytesToHex(respdata)
        Log.v(TAG, "CardReader, piccCardTest, s1 = $s1")
        Log.v(TAG, "CardReader, piccCardTest, resplen = " + resplen[0])

        Log.v(TAG, "getcardsnfunction")
        val outdata = ByteArray(512)
        val len = IntArray(1)
        try {
            mBankCard!!.getCardSNFunction(outdata, len)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        Log.v(TAG, "CardReader, piccCardTest, outdata = " + HEX.bytesToHex(outdata))
        Log.v(TAG, "CardReader, piccCardTest, len = " + len[0])

        val sendapdu = ByteArray(256)
        sendapdu[0] = 0x00.toByte()
        sendapdu[1] = 0xa4.toByte()
        sendapdu[2] = 0x00.toByte()
        sendapdu[3] = 0x00.toByte()
        sendapdu[4] = 0x02.toByte()
        sendapdu[5] = 0x3f.toByte()
        sendapdu[6] = 0x01.toByte()
        val resp = ByteArray(256)
        var retpicc = -1
        try {
            retpicc = mBankCard!!.sendAPDU(BankCard.CARD_MODE_PICC, sendapdu, 7, resp, resplen)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        Log.v(TAG, "CardReader, piccCardTest, resplen = " + resplen[0])
        Log.v(TAG, "CardReader, piccCardTest, resp = " + HEX.bytesToHex(resp))

        var whileCondition = false
        do {
            SystemClock.sleep(200)
            Log.v(TAG, "CardReader, piccCardTest, picc detecting--------------mDetect = $mDetect")
            //picc 卡在位检测
            try {
                whileCondition = BankCard.CARD_DETECT_EXIST != mBankCard!!.piccDetect()
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        } while (whileCondition && mDetect)
        val msg = mHandler.obtainMessage(PICC)
        if (retvalue == 0 && retpicc == 0) {
            msg.arg1 = 0
        } else {
            msg.arg1 = 1
        }
        mHandler.sendMessage(msg)
    }

    companion object {
        private const val TAG = "CardReader"
        private const val WANG_POS_TAB = "WPOS-TAB"
        private const val WANG_POS_MIN = "WPOS-MINI"
        private const val PICC = 1
        private const val IC = 2
        private const val MAG = 3
        private const val PSAM1 = 4
        private const val PSAM2 = 5
        private const val AT24 = 6
        private const val M0 = 7
        private const val M1 = 8
        private const val C4428 = 9
        private const val C4442 = 10
        private const val DESFIRE = 11
        private const val at88sc = 12
    }
}
