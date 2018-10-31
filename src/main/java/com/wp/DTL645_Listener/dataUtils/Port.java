package com.wp.DTL645_Listener.dataUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.TooManyListenersException;


import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

/**
 * 端口类，包装了未open及已经open的串口类
 * 
 * @author wp
 * 
 */
public class Port {

	/** 初始化信息 */
	private String initMessage;

	/** 串口事件侦听器 */
	private SerialPortListener listener;

	/** 通信口，通信口可以open，及获取是否被占用 */
	private CommPortIdentifier commPortIdentifier;

	/** 通信口打开则获取端口，端口可以close */
	private SerialPort serialPort;

	/** 输出流 */
	private OutputStream os;

	/** 波特率 */
	private int baudRate;

	/** 数据位数 */
	private int dataBits;

	/** 停止位 */
	private int stopBits;

	/** 校验位 */
	private int parity;

	/** 超时时间 */
	private int timeOut;

	/** 构造函数 */
	public Port(CommPortIdentifier commPortIdentifier, SerialPort serialPort,
			String initMessage) {
		super();
		this.commPortIdentifier = commPortIdentifier;
		this.serialPort = serialPort;
		this.initMessage = initMessage;
	}

	/** 获取端口名称 */
	public String getName() {
		return commPortIdentifier.getName();
	}

	/** 端口是否被占用 */
	public boolean isInUse() {
		return commPortIdentifier.isCurrentlyOwned();
	}

	/** 添加事件监听器 */
	public void addEventListener(SerialPortListener listener) {
		this.listener = listener;
		if (serialPort != null) {
			try {
				serialPort.removeEventListener();
				serialPort.addEventListener(listener);
				serialPort.notifyOnDataAvailable(true);
			} catch (TooManyListenersException e) {
				e.printStackTrace();
			}
		}
	}

	/** 移除事件监听器 */
	public void removeEventListener() {
		if (serialPort != null) {
			serialPort.removeEventListener();
		}
		listener = null;
	}

	/** 设置参数 */
	public void setParam(int baudRate, int dataBits, int stopBits, int parity,
			int timeOut) {
		this.baudRate = baudRate;
		this.dataBits = dataBits;
		this.stopBits = stopBits;
		this.parity = parity;
		this.timeOut = timeOut;
	}

	/** 打开端口 */
	public String open() {
		if (serialPort == null) {
			try {
				serialPort = (SerialPort) commPortIdentifier.open(
						commPortIdentifier.getName(), timeOut);
				try {
					serialPort.addEventListener(listener);
					serialPort.notifyOnDataAvailable(true);
				} catch (TooManyListenersException e) {
					e.printStackTrace();
				}
			} catch (PortInUseException e) {
				return getName() + "打开失败(" + e.getMessage() + ")\n";
			}
		}
		try {
			serialPort
					.setSerialPortParams(baudRate, dataBits, stopBits, parity);// 用指定参数初始化串行口
		} catch (UnsupportedCommOperationException e) {
			return e.getMessage();
		}
		return null;
	}

	/** 关闭端口 */
	public void close() {
		if (serialPort == null) {
			return;
		}
		serialPort.close();
		serialPort = null;
		os = null;
	}

	/** 发送数据 */
	public String sendMessage(byte[] msg) {
		if (serialPort == null) {
			return "串口没有打开\n";
		}
		if (os == null) {
			try {
				os = serialPort.getOutputStream();
			} catch (IOException e) {
				return "获取输出流错误\n";
			}
		}
		try {
			listener.clearResponse(); // 发送应答以前，清除应答缓冲区数据
			os.write(msg);
			os.flush();
		} catch (IOException e) {
			return "输出数据错误：" + e.getMessage() + "\n";
		}
		return null;
	}

	public String getInitMessage() {
		return initMessage;
	}

	/** 获取应答 */
	public byte[] getResponse() {
		if (listener == null) {
			return null;
		} else {
			return listener.getResponse();
		}
	}

	public int getBaudRate() {
		return baudRate;
	}

	public SerialPort getSerialPort() {
		return serialPort;
	}
}
