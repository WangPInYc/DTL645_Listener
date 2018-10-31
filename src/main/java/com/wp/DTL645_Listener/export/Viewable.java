package com.wp.DTL645_Listener.export;

/**
 * 字段可显示的接口
 * 
 * @author HK
 * 
 */
public interface Viewable {
	/** 将需要显示的字段转换成为字符串 */
	public abstract String[] convert();
}
