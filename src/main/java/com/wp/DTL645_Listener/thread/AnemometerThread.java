package com.wp.DTL645_Listener.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.wp.DTL645_Listener.dataUtils.Electric;
import com.wp.DTL645_Listener.dataUtils.ModbusUtil;
import com.wp.DTL645_Listener.dataUtils.ProtocolUtil;
import com.wp.DTL645_Listener.service.DTL645Service;
import com.wp.DTL645_Listener.service.IDtl645Service;
import com.wp.DTL645_Listener.start.MainFunction;


/**
 * 监控电能表的线程
 * 
 * @author Wp
 * 
 */
public class AnemometerThread extends Thread {
	public static Logger logger = Logger.getLogger(AnemometerThread.class);
	IDtl645Service service = new DTL645Service();
	boolean isrunning = true;
	
	@Override
	public void run() {
		try{
			while (isrunning) {
				Electric ecEntity = new Electric();
				for(int i = 1;i < 9;i++){
					double str_data = 0.0;
					String errorMessage = MainFunction.modbusPort.sendMessage(MainFunction.getBuf_order(i));
					if (errorMessage != null) {
//						logger.info("errorMessage1:" + errorMessage + ":");
					} 
					try {
						sleep(300);//延迟读取，尽量保证数据完整
					} catch (InterruptedException e1) {
						logger.info(e1.getMessage());
					}
					byte[] rxdBuf = MainFunction.modbusPort.getResponse();
//					rxdBuf = new byte[] {0x68,0x61,0x01,0x00,0x00,0x00,0x00,0x68,(byte)0x91,0x07,0x33,0x33,0x38,0x35,0x39,(byte)0x83,0x33,(byte)0x8C,0x16};
//					logger.info("从设备返回的数据："+ProtocolUtil.byte2HexStr(rxdBuf));
					
					if (rxdBuf != null && rxdBuf.length > 0 && rxdBuf[rxdBuf.length - 2] == ModbusUtil.checkCsForAll(rxdBuf)[0]) {
						str_data = ModbusUtil.asynDTL645Data(rxdBuf,i);
					}else {
						logger.info("从设备返回的数据校验未通过："+ProtocolUtil.byte2HexStr(rxdBuf));
						continue;
					}
					
					/**写入参数**/
					switch (i) {
					case 1:
						ecEntity.setSszszgl(str_data);
						break;
					case 2:
						ecEntity.setSszyggl(str_data);
						break;
					case 3:
						ecEntity.setAv(str_data);
						break;
					case 4:
						ecEntity.setBv(str_data);
						break;
					case 5:
						ecEntity.setCv(str_data);
						break;
					case 6:
						ecEntity.setAa(str_data);
						break;
					case 7:
						ecEntity.setBa(str_data);
						break;
					case 8:
						ecEntity.setCa(str_data);
						break;
					default:
						break;
					}
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
				ecEntity.setTime(sdf.format(new Date()));
				service.save(ecEntity);
			}
		}catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
			logger.info(e2.getMessage());
		}
	}
}
