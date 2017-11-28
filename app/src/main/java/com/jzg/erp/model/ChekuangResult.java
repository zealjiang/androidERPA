/**
 * @Title: ChekuangResult.java
 * @Package jzg.bjxdesc.vo
 * Copyright: Copyright (c) 2011 
 * Company:北京精真估信息技术有限公司
 * 
 * @author Comsys-wangyd
 * @date 2015-9-24 上午10:33:37
 * @version V1.0
 */

package com.jzg.erp.model;

import java.io.Serializable;

/**
 * @ClassName: ChekuangResult
 * @Description: 车况结果集对象
 * @author Comsys-wangyd
 * @date 2015-9-24 上午10:33:38
 * 
 */

public class ChekuangResult implements Serializable
{
	private String ids;// 车况id信息

	private String content;// 工况填写内容

	public String getIds()
	{
		return ids;
	}

	public void setIds(String ids)
	{
		this.ids = ids;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	@Override
	public String toString()
	{
		return "ChekuangResult [ids=" + ids + ", content=" + content + "]";
	}

	
}
