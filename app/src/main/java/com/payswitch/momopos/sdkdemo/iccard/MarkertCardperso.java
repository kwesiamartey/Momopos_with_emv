package com.payswitch.momopos.sdkdemo.iccard;///**
// * �ͻ������ű�������Ӧ�������Լ�Ӧ�ø��˻�
// */
//
//package com.wpos.sdkdemo.iccard;
//
//import com.anx.Anx;
//import com.anx.annotation.TestCase;
//
//
//public class MarkertCardperso {
//
//	@TestCase(index = 1, msg = "")
//	public void runTestCase() {
//
//		String MK_DF	= "58B0FD07C1FD266D6819372645CD67AE";//������Կ
//		String PK_1		= "000102030405060708090A0B0C0D0E0F";//�ⲿ��֤1
//		String PK_2		= "000102030405060708090A0B0C0D0E0F";//�ڲ���֤
//		String EPK		= "5B207F7AC85102D5CD38A1DCFE686E7A";//�ⲿ��֤2
//		String MK = "FBC1759E022368E589461994F4A8B670";
//		MarkertCardinstallWithoutSM4.runTestCase();
//		Anx.restart();
//
//		Anx.senDisplay("00A4040008A00000000300000000");
//
//	    Anx.GP_EnK = "404142434445464748494A4B4C4D4E4F";
//	    Anx.GP_MacK = "404142434445464748494A4B4C4D4E4F";
//	    Anx.GP_Dek = "404142434445464748494A4B4C4D4E4F";
//	    Anx.GP_Exauth(0);
//	    Anx.senDisplay("80E400000E4F0C534149435F43414E5445454ESW9000/6A80/6A88");
//	    Anx.senDisplay("80E60C002A085543415244312E300C534149435F43414E5445454E0C534149435F43414E5445454E010403C9010100");//sm1
//	    Anx.restart();
//	    //��ȡ�汾��
//		Anx.senDisplay("80CA0000029F7D");
//		//ѡӦ��
//		Anx.senDisplay("00A40400"+Util.toLenValue("534149435F43414E5445454E"));
//		//ѡMF
//		Anx.senDisplay("00A40000023F00SW9000/6A82");
//		//����MF�µ������ļ�
//		Anx.senDisplay("800E000000SW9000/6D00");
//		//����MF�ļ� MF�����ļ�Ȩ����3����ԿȨ����0����Կ��Ҫ���ܼ�MAC��֧�����3����Կ
//		Anx.senDisplay("80E000000E1122334455667788FF3F0F030315");
//
//		//д��������Կ
//		String Random 	= Anx.senDisplay("0084000008");
//		Random    		= Random.substring(0,Random.length() - 4);
//		String keydata 	= Anx.blockDes("17010000000F0F33FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", "1122334455667788");
//		String MAC 		= Anx.blockMacNonTel("84D400001C" + keydata, "1122334455667788", Random);
//		Anx.senDisplay("84D400001C" + keydata + MAC.substring(0,8) );
//		//д���ڲ���֤��Կ
//		Random 			= Anx.senDisplay("0084000008");
//		Random    		= Random.substring(0,Random.length() - 4);
//		keydata 		= Anx.blockDes("17011200000F0F3300113333445566778899AABBCCDDEEFF", "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
//		MAC 			= Anx.blockMacNonTel("84D400001C" + keydata, "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", Random);
//		Anx.senDisplay("84D400001C" + keydata + MAC.substring(0,8) );
//		//д���ⲿ��֤��Կ KID==00 �����Դ���==33
//		Random 			= Anx.senDisplay("0084000008");
//		Random    		= Random.substring(0,Random.length() - 4);
//		keydata 		= Anx.blockDes("17011100030F0F3300113333445566778899AABBCCDDEEFF", "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
//		MAC 			= Anx.blockMacNonTel("84D400001C" + keydata, "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", Random);
//		Anx.senDisplay("84D400001C" + keydata + MAC.substring(0,8) );
//
//		//����DDF1��3F01
//		//Anx.senDisplay("80E00200183F01000F0F100300A0000006324D4F542E435053414D3031");
//
//		Anx.senDisplay("00A40000023F00");
//		//����ADF1��DF01 �ļ�����Ȩ����03 ֧��16����Կ
//		Anx.senDisplay("80E0020018DF01003F0F100300A0000006324D4F542E435053414D3032");
//
//		//Ӧ��������Կ
//		Random 			= Anx.senDisplay("0084000008");
//		Random    		= Random.substring(0,Random.length() - 4);
//		keydata 		= Anx.blockDes("17010000000F0F33" + MK_DF, "00113333445566778899AABBCCDDEEFF");
//		MAC 			= Anx.blockMacNonTel("84D400001C" + keydata, "00113333445566778899AABBCCDDEEFF", Random);
//		Anx.senDisplay("84D400001C" + keydata + MAC.substring(0,8) );
//		//�ⲿ��֤��Կ1
//		Random 			= Anx.senDisplay("0084000008");
//		Random    		= Random.substring(0,Random.length() - 4);
//		keydata 		= Anx.blockDes("17011100030f0f33" + PK_1, MK_DF);
//		MAC 			= Anx.blockMacNonTel("84D400001C" + keydata, MK_DF, Random);
//		Anx.senDisplay("84D400001C" + keydata + MAC.substring(0,8) );
//		//�ⲿ��֤��Կ2
//		Random 			= Anx.senDisplay("0084000008");
//		Random    		= Random.substring(0,Random.length() - 4);
//		keydata 		= Anx.blockDes("17011100040f0F33" + EPK, PK_1);
//		MAC 			= Anx.blockMacNonTel("84D400011C" + keydata, PK_1, Random);
//		Anx.senDisplay("84D400011C" + keydata + MAC.substring(0,8) );
//		//�ڲ���֤��Կ
//		Random 			= Anx.senDisplay("0084000008");
//		Random    		= Random.substring(0,Random.length() - 4);
//		keydata 		= Anx.blockDes("17011200000F0F33" + PK_2, PK_1);
//		MAC 			= Anx.blockMacNonTel("84D400001C" + keydata, PK_1, Random);
//		Anx.senDisplay("84D400001C" + keydata + MAC.substring(0,8) );
//		//����6253�ļ�
//		Anx.senDisplay("80E003200862533F4F00110000");
//		//����6254�ļ�
//		Anx.senDisplay("80E003200862543F3F18000000");
//		//����6253�ļ��е�����
//		Anx.senDisplay("00a40000026253");
//		Anx.senDisplay("00D60000110200000003000000000000000000000000");
//		Anx.senDisplay("80E0040000");
//		Anx.restart();
//		Anx.closePort();
//		//�޸�GP��Կ
////		Anx.restart();
////		Anx.senDisplay("00A4040008A00000000300000000");
////		String response = Anx.senDisplay("80500000080102030405060708");
////		//����е����⣺�Ǵӵ�8λ��ʼ  Ȼ��ʮ�����ֽ�  (8,20)
////		String strKeyDate = response.substring(8,20);
////		//Anx.showMsg(strKeyDate);
////		String strGPEnkData = strKeyDate + "F001" + strKeyDate + "0F01";
////		String strGPEnk = Anx.blockDes(strGPEnkData, MK);
////		String strGPMacData = strKeyDate + "F002" + strKeyDate + "0F02";
////		String strGPMac = Anx.blockDes(strGPMacData, MK);
////		String strGPDekData = strKeyDate + "F003" + strKeyDate + "0F03";
////		String strGPDek = Anx.blockDes(strGPDekData, MK);
////
////		Anx.senDisplay("00A4040007A0000000030000sw9000");
////	    Anx.GP_EnK = "404142434445464748494A4B4C4D4E4F";
////	    Anx.GP_MacK = "404142434445464748494A4B4C4D4E4F";
////	    Anx.GP_Dek = "404142434445464748494A4B4C4D4E4F";
////		Anx.GP_Exauth(0);
////
////		String strGPEncKCV = Anx.blockDes("0000000000000000", strGPEnk).substring(0, 6);
////		String strGPMacKCV = Anx.blockDes("0000000000000000", strGPMac).substring(0, 6);
////		String strGPDekKCV = Anx.blockDes("0000000000000000", strGPDek).substring(0, 6);
////
////		String strGpEncData = Anx.blockDes(strGPEnk, Anx.GP_SeDek);
////		String strGpMacData = Anx.blockDes(strGPMac, Anx.GP_SeDek);
////		String strGpDekData = Anx.blockDes(strGPDek, Anx.GP_SeDek);
////
////		String strAPDU = "80D8008143018010"+strGpEncData+"03"+strGPEncKCV+"8010"+strGpMacData+"03"+strGPMacKCV+"8010"+strGpDekData+"03"+strGPDekKCV;
////		Anx.senDisplay(strAPDU);
//	}
//
//}
