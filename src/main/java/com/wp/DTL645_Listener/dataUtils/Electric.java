package com.wp.DTL645_Listener.dataUtils;

import com.wp.DTL645_Listener.export.Viewable;

/**
 * Electric entity. @author MyEclipse Persistence Tools
 */

public class Electric implements java.io.Serializable,Viewable {

	private static final long serialVersionUID = -383220390879934163L;
	
	private Integer id;
	private String time;
	private Double sszszgl;
	private Double sszyggl;
	private Double av;
	private Double bv;
	private Double cv;
	private Double aa;
	private Double ba;
	private Double ca;
	private Double attr1;
	private Double attr2;
	private Double attr3;
	private Double attr4;
	private Double attr5;
	private Double attr6;
	private String attr7;
	private String attr8;

	// Constructors

	/** default constructor */
	public Electric() {
	}

	/** full constructor */
	public Electric(String time, Double sszszgl, Double sszyggl, Double av,
			Double bv, Double cv, Double aa, Double ba, Double ca,
			Double attr1, Double attr2, Double attr3, Double attr4,
			Double attr5, Double attr6, String attr7, String attr8) {
		this.time = time;
		this.sszszgl = sszszgl;
		this.sszyggl = sszyggl;
		this.av = av;
		this.bv = bv;
		this.cv = cv;
		this.aa = aa;
		this.ba = ba;
		this.ca = ca;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.attr3 = attr3;
		this.attr4 = attr4;
		this.attr5 = attr5;
		this.attr6 = attr6;
		this.attr7 = attr7;
		this.attr8 = attr8;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getSszszgl() {
		return this.sszszgl;
	}

	public void setSszszgl(Double sszszgl) {
		this.sszszgl = sszszgl;
	}

	public Double getSszyggl() {
		return this.sszyggl;
	}

	public void setSszyggl(Double sszyggl) {
		this.sszyggl = sszyggl;
	}

	public Double getAv() {
		return this.av;
	}

	public void setAv(Double av) {
		this.av = av;
	}

	public Double getBv() {
		return this.bv;
	}

	public void setBv(Double bv) {
		this.bv = bv;
	}

	public Double getCv() {
		return this.cv;
	}

	public void setCv(Double cv) {
		this.cv = cv;
	}

	public Double getAa() {
		return this.aa;
	}

	public void setAa(Double aa) {
		this.aa = aa;
	}

	public Double getBa() {
		return this.ba;
	}

	public void setBa(Double ba) {
		this.ba = ba;
	}

	public Double getCa() {
		return this.ca;
	}

	public void setCa(Double ca) {
		this.ca = ca;
	}

	public Double getAttr1() {
		return this.attr1;
	}

	public void setAttr1(Double attr1) {
		this.attr1 = attr1;
	}

	public Double getAttr2() {
		return this.attr2;
	}

	public void setAttr2(Double attr2) {
		this.attr2 = attr2;
	}

	public Double getAttr3() {
		return this.attr3;
	}

	public void setAttr3(Double attr3) {
		this.attr3 = attr3;
	}

	public Double getAttr4() {
		return this.attr4;
	}

	public void setAttr4(Double attr4) {
		this.attr4 = attr4;
	}

	public Double getAttr5() {
		return this.attr5;
	}

	public void setAttr5(Double attr5) {
		this.attr5 = attr5;
	}

	public Double getAttr6() {
		return this.attr6;
	}

	public void setAttr6(Double attr6) {
		this.attr6 = attr6;
	}

	public String getAttr7() {
		return this.attr7;
	}

	public void setAttr7(String attr7) {
		this.attr7 = attr7;
	}

	public String getAttr8() {
		return this.attr8;
	}

	public void setAttr8(String attr8) {
		this.attr8 = attr8;
	}

	@Override
	public String[] convert() {
		// TODO Auto-generated method stub
		return null;
	}
}