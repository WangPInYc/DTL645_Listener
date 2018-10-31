package com.wp.DTL645_Listener.dataUtils;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 串行口侦听
 * 
 * @author HK
 * 
 */
public class SerialPortListener implements SerialPortEventListener {

	/** 接收到的二进制数据应答 */
	private List<Byte> dataList = new ArrayList<Byte>();

	/** 串行口接收数据的事件 */
	public void serialEvent(SerialPortEvent event) {
		SerialPort port = (SerialPort) event.getSource();
		if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				InputStream inputStream = port.getInputStream();
				byte[] buffer = new byte[inputStream.available()];
				inputStream.read(buffer);
				for (byte value : buffer) {
					dataList.add(value);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	/** 获取应答数据 */
	public byte[] getResponse() {
		byte[] buf = new byte[dataList.size()];
		for (int i = 0; i < buf.length; i++) {
			buf[i] = dataList.get(i);
		}
		return buf;
	}

	/** 清除应答数据 */
	public void clearResponse() {
		dataList = new ArrayList<Byte>();
	}
}
