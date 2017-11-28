/**
 * Project Name:JingZhenGu
 * File Name:Style.java
 * Package Name:com.gc.jingzhengu.vo
 * Date:2014-6-16上午10:21:03
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.erp.model;

import java.io.Serializable;

/**
 * ClassName:Style <br/>
 * Function: 车型实体. <br/>
 * Date: 2014-6-16 上午10:21:03 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class Style implements Serializable
{


	/**
	 * Id : 116793
	 * Name : 1.5L 手动 H2E 时尚型
	 * Year : 2016
	 * NowMsrp : 4.38
	 * FullName : 北汽幻速H2 2016款 1.5L 手动 H2E 时尚型
	 * MinYEAR : 2015-06
	 * MaxYEAR : 2016-07
	 */

	private String Id;
	private String Name;
	private String Year;
	private String NowMsrp;
	private String FullName;
	private String MinYEAR;
	private String MaxYEAR;

	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String Year) {
		this.Year = Year;
	}

	public String getNowMsrp() {
		return NowMsrp;
	}

	public void setNowMsrp(String NowMsrp) {
		this.NowMsrp = NowMsrp;
	}

	public String getFullName() {
		return FullName;
	}

	public void setFullName(String FullName) {
		this.FullName = FullName;
	}

	public String getMinYEAR() {
		return MinYEAR;
	}

	public void setMinYEAR(String MinYEAR) {
		this.MinYEAR = MinYEAR;
	}

	public String getMaxYEAR() {
		return MaxYEAR;
	}

	public void setMaxYEAR(String MaxYEAR) {
		this.MaxYEAR = MaxYEAR;
	}
}
