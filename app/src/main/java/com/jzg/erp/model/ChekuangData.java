/**
 * @Title: ChekuangData.java
 * @Package jzg.bjxdesc.vo
 * Copyright: Copyright (c) 2011 
 * Company:北京精真估信息技术有限公司
 * 
 * @author Comsys-wangyd
 * @date 2015-9-23 上午10:04:22
 * @version V1.0
 */

package com.jzg.erp.model;

import java.io.Serializable;

/**
 * @ClassName: ChekuangData
 * @Description: 车况列表实体
 * @author Comsys-wangyd
 * @date 2015-9-23 上午10:04:22
 * 
 */

public class ChekuangData implements Serializable
{
	/**
	  * @Fields serialVersionUID :
	  */
	
	private static final long serialVersionUID = 1L;

	private String content;

	private String subContent;

	private boolean hasSubContent;

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getSubContent()
	{
		return subContent;
	}

	public void setSubContent(String subContent)
	{
		this.subContent = subContent;
	}

	public boolean isHasSubContent()
	{
		return hasSubContent;
	}

	public void setHasSubContent(boolean hasSubContent)
	{
		this.hasSubContent = hasSubContent;
	}

	@Override
	public String toString()
	{
		return "ChekuangData [content=" + content + ", subContent="
				+ subContent + ", hasSubContent=" + hasSubContent + "]";
	}
}
