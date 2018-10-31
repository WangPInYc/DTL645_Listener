package com.wp.DTL645_Listener.export;

import java.io.Serializable;


public class ComDataBean implements Serializable, Viewable {

	private static final long serialVersionUID = -8190411767839262720L;

	private String s1;
	private String s2;
	private String s3;
	private String s4;
	private String qj;
	private String lp;
	
	
	/**
	 * 
	 */
	public ComDataBean() {
		super();
	}

	public ComDataBean(String s1, String s2, String s3, String s4, String qj,
			String lp) {
		super();
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
		this.qj = qj;
		this.lp = lp;
	}
	
	public String getS1() {
		return s1;
	}
	public void setS1(String s1) {
		this.s1 = s1;
	}
	public String getS2() {
		return s2;
	}
	public void setS2(String s2) {
		this.s2 = s2;
	}
	public String getS3() {
		return s3;
	}
	public void setS3(String s3) {
		this.s3 = s3;
	}
	public String getS4() {
		return s4;
	}
	public void setS4(String s4) {
		this.s4 = s4;
	}
	public String getQj() {
		return qj;
	}
	public void setQj(String qj) {
		this.qj = qj;
	}
	public String getLp() {
		return lp;
	}
	public void setLp(String lp) {
		this.lp = lp;
	}

	/** 转换成为字符串数值 */
	public String[] convert() {
		String[] result = { this.s1, this.s2 , this.s3 , this.s4 , this.qj , this.lp };
		return result;
	}
}
