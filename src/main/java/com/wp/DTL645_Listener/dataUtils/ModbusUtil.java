package com.wp.DTL645_Listener.dataUtils;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;

public class ModbusUtil {
	static Logger logger = Logger.getLogger(ModbusUtil.class);
	// 计算CRC16
	public static byte[] crc16(byte[] buf, int bufSize) {
		byte uchCRCHi = (byte) 0xFF; // 高CRC字节初始化
		byte uchCRCLo = (byte) 0xFF; // 低CRC 字节初始化
		int uIndex; // CRC循环中的索引

		// CRC 高位字节值表
		final byte auchCRCHi[] = { (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81,
				(byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80,
				(byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40 };

		// CRC低位字节值表
		final byte auchCRCLo[] = { (byte) 0x00, (byte) 0xC0, (byte) 0xC1,
				(byte) 0x01, (byte) 0xC3, (byte) 0x03, (byte) 0x02,
				(byte) 0xC2, (byte) 0xC6, (byte) 0x06, (byte) 0x07,
				(byte) 0xC7, (byte) 0x05, (byte) 0xC5, (byte) 0xC4,
				(byte) 0x04, (byte) 0xCC, (byte) 0x0C, (byte) 0x0D,
				(byte) 0xCD, (byte) 0x0F, (byte) 0xCF, (byte) 0xCE,
				(byte) 0x0E, (byte) 0x0A, (byte) 0xCA, (byte) 0xCB,
				(byte) 0x0B, (byte) 0xC9, (byte) 0x09, (byte) 0x08,
				(byte) 0xC8, (byte) 0xD8, (byte) 0x18, (byte) 0x19,
				(byte) 0xD9, (byte) 0x1B, (byte) 0xDB, (byte) 0xDA,
				(byte) 0x1A, (byte) 0x1E, (byte) 0xDE, (byte) 0xDF,
				(byte) 0x1F, (byte) 0xDD, (byte) 0x1D, (byte) 0x1C,
				(byte) 0xDC, (byte) 0x14, (byte) 0xD4, (byte) 0xD5,
				(byte) 0x15, (byte) 0xD7, (byte) 0x17, (byte) 0x16,
				(byte) 0xD6, (byte) 0xD2, (byte) 0x12, (byte) 0x13,
				(byte) 0xD3, (byte) 0x11, (byte) 0xD1, (byte) 0xD0,
				(byte) 0x10, (byte) 0xF0, (byte) 0x30, (byte) 0x31,
				(byte) 0xF1, (byte) 0x33, (byte) 0xF3, (byte) 0xF2,
				(byte) 0x32, (byte) 0x36, (byte) 0xF6, (byte) 0xF7,
				(byte) 0x37, (byte) 0xF5, (byte) 0x35, (byte) 0x34,
				(byte) 0xF4, (byte) 0x3C, (byte) 0xFC, (byte) 0xFD,
				(byte) 0x3D, (byte) 0xFF, (byte) 0x3F, (byte) 0x3E,
				(byte) 0xFE, (byte) 0xFA, (byte) 0x3A, (byte) 0x3B,
				(byte) 0xFB, (byte) 0x39, (byte) 0xF9, (byte) 0xF8,
				(byte) 0x38, (byte) 0x28, (byte) 0xE8, (byte) 0xE9,
				(byte) 0x29, (byte) 0xEB, (byte) 0x2B, (byte) 0x2A,
				(byte) 0xEA, (byte) 0xEE, (byte) 0x2E, (byte) 0x2F,
				(byte) 0xEF, (byte) 0x2D, (byte) 0xED, (byte) 0xEC,
				(byte) 0x2C, (byte) 0xE4, (byte) 0x24, (byte) 0x25,
				(byte) 0xE5, (byte) 0x27, (byte) 0xE7, (byte) 0xE6,
				(byte) 0x26, (byte) 0x22, (byte) 0xE2, (byte) 0xE3,
				(byte) 0x23, (byte) 0xE1, (byte) 0x21, (byte) 0x20,
				(byte) 0xE0, (byte) 0xA0, (byte) 0x60, (byte) 0x61,
				(byte) 0xA1, (byte) 0x63, (byte) 0xA3, (byte) 0xA2,
				(byte) 0x62, (byte) 0x66, (byte) 0xA6, (byte) 0xA7,
				(byte) 0x67, (byte) 0xA5, (byte) 0x65, (byte) 0x64,
				(byte) 0xA4, (byte) 0x6C, (byte) 0xAC, (byte) 0xAD,
				(byte) 0x6D, (byte) 0xAF, (byte) 0x6F, (byte) 0x6E,
				(byte) 0xAE, (byte) 0xAA, (byte) 0x6A, (byte) 0x6B,
				(byte) 0xAB, (byte) 0x69, (byte) 0xA9, (byte) 0xA8,
				(byte) 0x68, (byte) 0x78, (byte) 0xB8, (byte) 0xB9,
				(byte) 0x79, (byte) 0xBB, (byte) 0x7B, (byte) 0x7A,
				(byte) 0xBA, (byte) 0xBE, (byte) 0x7E, (byte) 0x7F,
				(byte) 0xBF, (byte) 0x7D, (byte) 0xBD, (byte) 0xBC,
				(byte) 0x7C, (byte) 0xB4, (byte) 0x74, (byte) 0x75,
				(byte) 0xB5, (byte) 0x77, (byte) 0xB7, (byte) 0xB6,
				(byte) 0x76, (byte) 0x72, (byte) 0xB2, (byte) 0xB3,
				(byte) 0x73, (byte) 0xB1, (byte) 0x71, (byte) 0x70,
				(byte) 0xB0, (byte) 0x50, (byte) 0x90, (byte) 0x91,
				(byte) 0x51, (byte) 0x93, (byte) 0x53, (byte) 0x52,
				(byte) 0x92, (byte) 0x96, (byte) 0x56, (byte) 0x57,
				(byte) 0x97, (byte) 0x55, (byte) 0x95, (byte) 0x94,
				(byte) 0x54, (byte) 0x9C, (byte) 0x5C, (byte) 0x5D,
				(byte) 0x9D, (byte) 0x5F, (byte) 0x9F, (byte) 0x9E,
				(byte) 0x5E, (byte) 0x5A, (byte) 0x9A, (byte) 0x9B,
				(byte) 0x5B, (byte) 0x99, (byte) 0x59, (byte) 0x58,
				(byte) 0x98, (byte) 0x88, (byte) 0x48, (byte) 0x49,
				(byte) 0x89, (byte) 0x4B, (byte) 0x8B, (byte) 0x8A,
				(byte) 0x4A, (byte) 0x4E, (byte) 0x8E, (byte) 0x8F,
				(byte) 0x4F, (byte) 0x8D, (byte) 0x4D, (byte) 0x4C,
				(byte) 0x8C, (byte) 0x44, (byte) 0x84, (byte) 0x85,
				(byte) 0x45, (byte) 0x87, (byte) 0x47, (byte) 0x46,
				(byte) 0x86, (byte) 0x82, (byte) 0x42, (byte) 0x43,
				(byte) 0x83, (byte) 0x41, (byte) 0x81, (byte) 0x80, (byte) 0x40 };

		int index = 0;
		while (bufSize-- > 0) {
			byte ch = buf[index++];
			uIndex = uchCRCHi ^ ch;
			int idx = (uIndex + 256) % 256;
			uchCRCHi = (byte) (uchCRCLo ^ auchCRCHi[idx]);
			uchCRCLo = auchCRCLo[idx];
		}
		byte[] result = new byte[2];
		result[0] = uchCRCLo;
		result[1] = uchCRCHi;
		return result;
	}


	//计算DTL645-2007中的校验位CS(不含最后两位)（从第一个帧起始符开始到校验码之前的所有各字节的模256的和，即各字节二进制算术和，不计超过256的溢出值）
	public static byte[] checkCs(byte[] buf) {
		int count = 0;
		for(byte b : buf){
			count += b%256;
		}
		count = count%256;
		return ProtocolUtil.hexStringToBytes(Integer.toHexString(count));
	}
	
	//计算DTL645-2007中的校验位CS（从第一个帧起始符开始到校验码之前的所有各字节的模256的和，即各字节二进制算术和，不计超过256的溢出值）
	public static byte[] checkCsForAll(byte[] buf) {
		int count = 0;
		for(int i = 0;i < buf.length - 2;i++){
			count += buf[i]%256;
		}
		count = count%256;
		return ProtocolUtil.hexStringToBytes(Integer.toHexString(count));
	}
	
	/** 获取系统中的端口列表 */
	@SuppressWarnings("rawtypes")
	public static List<Port> getSerialPortList() {
		List<Port> serialPortList = new ArrayList<Port>();
		Enumeration portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			String initMessage = "";
			CommPortIdentifier commPortIdentifier = (CommPortIdentifier) portList
					.nextElement();
			if (commPortIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL) {// 提取出串口
				SerialPort serialPort = null;
				try {
					CommPort commPort = commPortIdentifier.open(
							commPortIdentifier.getName(), 2000);
					if (commPort instanceof SerialPort) {// 此端口是串行端口
						serialPort = (SerialPort) commPort;
					}
				} catch (PortInUseException e) {
					initMessage = commPortIdentifier.getName() + "正在使用";
				}
				Port port = new Port(commPortIdentifier, serialPort,
						initMessage);
				serialPortList.add(port);
			}
		}
		return serialPortList;
	}
	
	//根据源数组获取完整数组
	public static byte[] setOrderForDTL645(byte buf[]){
		byte buf2[] = new byte[buf.length+2];//要返回的buf
		try{
			byte b[] = checkCs(buf);//取得校验位CS
			System.arraycopy(buf, 0, buf2, 0, buf.length);
			buf2[buf.length] = b[0];
			buf2[buf.length + 1] = 0x16;
		}catch (Exception e) {
			logger.error("源数组获取完整数组失败："+e.getMessage());
		}
		return buf2;
	}
	
	//根据地址及数据类型生成命令
	public static byte[] setOrderByAddrAndData(String addr,int dataType){
		byte buf[] = new byte[14];
		buf[0] = 0x68;
		byte addr_buf[] = setAddr(addr);
		System.arraycopy(addr_buf, 0, buf, 1, addr_buf.length);
		buf[addr_buf.length + 1] = 0x68;
		buf[addr_buf.length + 2] = 0x11;
		buf[addr_buf.length + 3] = 0x04;
		
		byte dataType_buf[] = new byte[4];
		switch(dataType){
			case 1://瞬时总视在功率
				dataType_buf[0] = (byte)0x33;
				dataType_buf[1] = (byte)0x33;
				dataType_buf[2] = (byte)0x38;
				dataType_buf[3] = (byte)0x35;
				break;
			case 2://瞬时总有功功率
				dataType_buf[0] = (byte)0x33;
				dataType_buf[1] = (byte)0x33;
				dataType_buf[2] = (byte)0x36;
				dataType_buf[3] = (byte)0x35;
				break;	
			case 3://A 相电压
				dataType_buf[0] = (byte)0x33;
				dataType_buf[1] = (byte)0x34;
				dataType_buf[2] = (byte)0x34;
				dataType_buf[3] = (byte)0x35;
				break;
			case 4://B 相电压
				dataType_buf[0] = (byte)0x33;
				dataType_buf[1] = (byte)0x35;
				dataType_buf[2] = (byte)0x34;
				dataType_buf[3] = (byte)0x35;
				break;
			case 5://C 相电压
				dataType_buf[0] = (byte)0x33;
				dataType_buf[1] = (byte)0x36;
				dataType_buf[2] = (byte)0x34;
				dataType_buf[3] = (byte)0x35;
				break;
			case 6://A 相电流
				dataType_buf[0] = (byte)0x33;
				dataType_buf[1] = (byte)0x34;
				dataType_buf[2] = (byte)0x35;
				dataType_buf[3] = (byte)0x35;
				break;
			case 7://B 相电流
				dataType_buf[0] = (byte)0x33;
				dataType_buf[1] = (byte)0x35;
				dataType_buf[2] = (byte)0x35;
				dataType_buf[3] = (byte)0x35;
				break;
			case 8://C 相电流
				dataType_buf[0] = (byte)0x33;
				dataType_buf[1] = (byte)0x36;
				dataType_buf[2] = (byte)0x35;
				dataType_buf[3] = (byte)0x35;
				break;	
		}
		
		System.arraycopy(dataType_buf, 0, buf, addr_buf.length + 4, dataType_buf.length);
		
		return buf;
	}
	
	//将地址转换为地址位
	private static byte[] setAddr(String addr){
		byte[] bytes = new byte[6];
		long data = Long.parseLong(addr);
		bytes[0] = (byte) (data & 0xff);
		bytes[1] = (byte) ((data >> 8) & 0xff);
		bytes[2] = (byte) ((data >> 16) & 0xff);
		bytes[3] = (byte) ((data >> 24) & 0xff);
		bytes[4] = (byte) ((data >> 32) & 0xff);
		bytes[5] = (byte) ((data >> 40) & 0xff);
		return bytes;
	}
	
	/**
	 * 解析电能表返回的数据
	 * @param buf
	 * @param index
	 * @return
	 */
	public static double asynDTL645Data(byte buf[],int index){
		double rs_data = 0.0;
		StringBuffer sb_data = new StringBuffer();
		String str_value = "";
		try{
			//获取第10字节--返回的数据长度
			int d_len = buf[9] & 0xFF;
			//从第15字节开始获取d_len-4长度作为数据
			byte b_data[] = new byte[d_len-4];
			System.arraycopy(buf, 14, b_data, 0, d_len-4);
			
			String str_data[] = ProtocolUtil.Bytes2HexString(b_data).split(" ");
			for(int i = str_data.length - 1;i > -1;i--){
				sb_data.append(Integer.parseInt(str_data[i])-33);
			}
			str_value = sb_data.toString();
			
			switch (index) {
			case 1:
			case 2:
				if(str_value.substring(0, 1).equals(1)){//判断最高位,0+1-
					rs_data = Integer.parseInt(str_value.substring(1, str_value.length()-1).toString())/10000.0;
				}else {
					rs_data = Integer.parseInt(sb_data.toString())/10000.0;
				}
				break;
				
			case 3:
			case 4:
			case 5:
				rs_data = Integer.parseInt(sb_data.toString())/10.0;
				break;
				
			case 6:
			case 7:
			case 8:
				if(str_value.substring(0, 1).equals(1)){//判断最高位,0+1-
					rs_data = Integer.parseInt(str_value.substring(1, str_value.length()-1).toString())/1000.0;
				}else {
					rs_data = Integer.parseInt(sb_data.toString())/1000.0;
				}
				break;
			default:
				break;
			}
			
		}catch (Exception e) {
			logger.error("解析DTL645数据失败："+e.getMessage());
		}
		
		return rs_data;
	}
	
	public static void main(String[] args) {
		//
//		System.out.println(7%5);
//		byte buf[] = {0x68,0x61,0x01,0x00,0x00,0x00,0x00,0x68,0x11,0x04,0x33,0x33,0x39,0x35};
//		byte b[] = checkCs(buf);
//		byte buf2[] = new byte[buf.length+2];
//		System.arraycopy(buf, 0, buf2, 0, buf.length);
//		buf2[buf.length] = b[0];
//		buf2[buf.length + 1] = 0x16;
//		byte bb[] = {0x33};//
		
		byte bufff[] = {0x68,0x61,0x01,0x00,0x00,0x00,0x00,0x68,(byte)0x91,0x07,0x33,0x33,0x38,0x35,0x39,(byte)0x83,0x33,(byte)0x8C,0x16};
		System.out.println(asynDTL645Data(bufff,1));
		System.out.println(ProtocolUtil.Bytes2HexString(setAddr("28")));
		System.out.println(ProtocolUtil.Bytes2HexString(setOrderForDTL645(setOrderByAddrAndData("40", 1))));
		System.out.println(Integer.parseInt("5316")/10000.0);
	}
}
