package com.wp.DTL645_Listener.start;

import gnu.io.SerialPort;

import java.util.List;

import org.apache.log4j.Logger;

import com.wp.DTL645_Listener.dataUtils.ModbusUtil;
import com.wp.DTL645_Listener.dataUtils.Port;
import com.wp.DTL645_Listener.dataUtils.SerialPortListener;
import com.wp.DTL645_Listener.dataUtils.ServerConfig;
import com.wp.DTL645_Listener.service.DTL645Service;
import com.wp.DTL645_Listener.service.IDtl645Service;
import com.wp.DTL645_Listener.thread.AnemometerThread;


public class MainFunction {

	static Logger logger = Logger.getLogger(MainFunction.class);
	static IDtl645Service service = new DTL645Service();
	/** modbus通信接口 */
	public static Port modbusPort = null;
	public static byte[] buf1;
	public static byte[] buf2;
	public static byte[] buf3;
	public static byte[] buf4;
	public static byte[] buf5;
	public static byte[] buf6;
	public static byte[] buf7;
	public static byte[] buf8;
	public static byte[] buf_order;
	
	public static void main(String[] args) {
//		initDataBase();
		// TODO Auto-generated method stub
		setOrder();
		modbusPort = getPort();
		
		new AnemometerThread().start();
		
//		Electric ec  = new Electric();
//		ec.setSszyggl(100.0);
//		service.save(ec);
//		
//		Electric ec2  = new Electric();
//		List list = service.findByExample(ec2);
//		System.out.println(list.size());
	}
	
	/** 找到配置文件所指定的串口 */
	public static Port getPort() {
		List<Port> portList = ModbusUtil.getSerialPortList();
		int baudRate = Integer.parseInt(ServerConfig.readData("baudRate"));
		for (Port port : portList) {
			port.setParam(baudRate, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN, 2000);
			port.addEventListener(new SerialPortListener());
			if (port.getName().equals(ServerConfig.readData("modbusPort"))) {
				String errorMessage = port.open(); // 打开串口
				if (errorMessage != null) {
					logger.error("打开串口错误:");
				}
				return port;
			}
		}
		return null;
	}

	private static void setOrder(){
		String addr = ServerConfig.readData("EQUIP_ADDR");
		//取瞬时总视在功率命令
		buf1 = ModbusUtil.setOrderForDTL645(ModbusUtil.setOrderByAddrAndData(addr, 1));
		//取瞬时总有功功率
		buf2 = ModbusUtil.setOrderForDTL645(ModbusUtil.setOrderByAddrAndData(addr, 2));
		//取A 相电压
		buf3 = ModbusUtil.setOrderForDTL645(ModbusUtil.setOrderByAddrAndData(addr, 3));
		//取B 相电压
		buf4 = ModbusUtil.setOrderForDTL645(ModbusUtil.setOrderByAddrAndData(addr, 4));
		//取C 相电压
		buf5 = ModbusUtil.setOrderForDTL645(ModbusUtil.setOrderByAddrAndData(addr, 5));
		//取A 相电流
		buf6 = ModbusUtil.setOrderForDTL645(ModbusUtil.setOrderByAddrAndData(addr, 6));
		//取B 相电流
		buf7 = ModbusUtil.setOrderForDTL645(ModbusUtil.setOrderByAddrAndData(addr, 7));
		//取C 相电流
		buf8 = ModbusUtil.setOrderForDTL645(ModbusUtil.setOrderByAddrAndData(addr, 8));
	}

	public static byte[] getBuf_order(int index) {
		switch (index) {
		case 1:
			buf_order = buf1;
			break;
		case 2:
			buf_order = buf2;
			break;
		case 3:
			buf_order = buf3;
			break;
		case 4:
			buf_order = buf4;
			break;
		case 5:
			buf_order = buf5;
			break;
		case 6:
			buf_order = buf6;
			break;
		case 7:
			buf_order = buf7;
			break;
		case 8:
			buf_order = buf8;
			break;
		default:
			break;
		}
		return buf_order;
	}
}
