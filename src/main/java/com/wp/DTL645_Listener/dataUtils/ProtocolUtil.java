package com.wp.DTL645_Listener.dataUtils;


import org.apache.log4j.Logger;


/**
 * 通信协议的工具类
 * 
 * @author wp
 * 
 */
public class ProtocolUtil {
	static Logger logger = Logger.getLogger(ProtocolUtil.class);
	// 从字节流中指定偏移地址获取Int类型的数据
	public static int getInt(byte[] buf, int offset) {
		int hi = (buf[offset + 0] + 256) & 0xFF;
		int lo = (buf[offset + 1] + 256) & 0xFF;
		int result = hi * 256 + lo;
		return result;
	}

	// 字符串到字节数组的转换
	public static byte[] stringToBytes(String str, int size) {
		byte[] result = new byte[size];
		byte[] bytes = str.getBytes();
		for (int i = 0; i < result.length; i++) {
			result[i] = 0;
		}
		for (int i = 0; i < bytes.length; i++) {
			result[i] = bytes[i];
		}
		return result;
	}

	// 字节到字节数组的转换
	public static byte[] byteToBytes(byte value) {
		byte[] result = new byte[1];
		result[0] = value;
		return result;
	}

	// 整数到字节数组的转换
	public static byte[] shortToBytes(short value) {
		byte[] result = new byte[2];
		result[0] = (byte) ((value & 0xFF00) >> 8);
		result[1] = (byte) ((value & 0x00FF) >> 0);
		return result;
	}

	// 整数到字节数组的转换
	public static byte[] intToBytes(int intValue) {
		byte[] result = new byte[4];
		result[0] = (byte) ((intValue & 0xFF000000) >> 24);
		result[1] = (byte) ((intValue & 0x00FF0000) >> 16);
		result[2] = (byte) ((intValue & 0x0000FF00) >> 8);
		result[3] = (byte) ((intValue & 0x000000FF));
		return result;
	}

	// 浮点到字节转换
	public static byte[] doubleToBytes(double d) {
		byte[] b = new byte[8];
		long l = Double.doubleToLongBits(d);
		for (int i = 0; i < b.length; i++) {
			b[i] = new Long(l).byteValue();
			l = l >> 8;
		}
		return b;
	}

	// 字节数组转字节
	public static byte bytesToByte(byte[] bytes) {
		return bytes[0];
	}

	// 字节数组转Short
	public static short bytesToShort(byte[] bytes) {
		int hi = (bytes[0] + 256) & 0xFF;
		int lo = (bytes[1] + 256) & 0xFF;
		int result = hi * 256 + lo;
		return (short) result;
	}

	// 字节数组转字节
	public static int bytesToInteger(byte[] bytes) {
		int a = (bytes[0] + 256) & 0xFF;
		int b = (bytes[1] + 256) & 0xFF;
		int c = (bytes[2] + 256) & 0xFF;
		int d = (bytes[3] + 256) & 0xFF;
		int result = (a << 24) + (b << 16) + (c << 8) + d;
		return result;
	}

	// 字节数组转字节
	public static double bytesToDouble(byte[] bytes) {
		long a = (bytes[7] + 256) & 0xFF;
		long b = (bytes[6] + 256) & 0xFF;
		long c = (bytes[5] + 256) & 0xFF;
		long d = (bytes[4] + 256) & 0xFF;
		long e = (bytes[3] + 256) & 0xFF;
		long f = (bytes[2] + 256) & 0xFF;
		long g = (bytes[1] + 256) & 0xFF;
		long h = (bytes[0] + 256) & 0xFF;
		long value = (a << 56);
		value = value + (b << 48);
		value = value + (c << 40);
		value = value + (d << 32);
		value = value + (e << 24);
		value = value + (f << 16);
		value = value + (g << 8);
		value = value + h;
		double result = Double.longBitsToDouble(value);
		return result;
	}

	// 字节数组转字符串
	public static String bytesToString(byte[] bytes) {
		int num = 0;
		for (; num < bytes.length; num++) {
			if (bytes[num] == 0) {
				break;
			}
		}
		if (num == 0) {
			num = bytes.length;
		}
		String result = new String(bytes, 0, num);
		return result;
	}

	/** 将一个对象转换成为适合为字节码 */
	public static byte[] converToBytes(Object value, int num) {
		if (value instanceof Byte) {
			return byteToBytes((Byte) value);
		} else if (value instanceof Short) {
			return shortToBytes((Short) value);
		} else if (value instanceof Integer) {
			return intToBytes((Integer) value);
		} else if (value instanceof String) {
			return stringToBytes((String) value, num);
		}
		return null;
	}

	/** 把字符串形式的16进制转换为byte数组 **/
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.replaceAll(" ", "").toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}
	
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
	
	public static String Bytes2HexString(byte[] b) {
		String ret = "";
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			ret += hex.toUpperCase();
			if(i != b.length - 1){
				ret += " ";
			}
		}
		return ret;
	}
	/** 
	 * bytes转换成十六进制字符串 
	 */  
	public static String byte2HexStr(byte[] b) {  
	    String hs = "";  
	    String stmp = "";  
	    try{
		    for (int n = 0; n < b.length; n++) {  
		        stmp = (Integer.toHexString(b[n] & 0XFF));  
		        if (stmp.length() == 1){  
		            hs = hs + "0" + stmp;  
		        }else{  
	        		hs = hs + stmp; 
		        }
		        if (n<b.length-1) {
		        	hs=hs+" ";  
		        }
		    }  
	    }catch (Exception e) {
			// TODO: handle exception
	    	logger.info("byte转换成String出错：" + e.getMessage());
		}
	    return hs.toUpperCase();  
	}
	/**
     * 从一个byte[]数组中截取一部分
     * @param src
     * @param begin
     * @param count
     * @return
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        for (int i=begin; i<begin+count; i++) bs[i-begin] = src[i];
        return bs;
    }
}
