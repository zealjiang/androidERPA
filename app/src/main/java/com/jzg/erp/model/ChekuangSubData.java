/**
 * @Title: ChekuangSubData.java
 * @Package jzg.bjxdesc.vo
 * Copyright: Copyright (c) 2011 
 * Company:北京精真估信息技术有限公司
 * 
 * @author Comsys-wangyd
 * @date 2015-9-22 下午1:51:19
 * @version V1.0
 */

package com.jzg.erp.model;

import java.io.Serializable;

/**
 * @ClassName: ChekuangSubData
 * @Description: 车况子列表实体
 * @author Comsys-wangyd
 * @date 2015-9-22 下午1:51:19
 * 
 */

public class ChekuangSubData implements Serializable
{
	private String content;// 显示内容

	private String id; // 显示内容对应的id

	private boolean select = false;// 标记是否选中

	private String title;// 标题

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public boolean isSelect()
	{
		return select;
	}

	public void setSelect(boolean select)
	{
		this.select = select;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Override
	public String toString()
	{
		return "ChekuangSubData [content=" + content + ", id=" + id
				+ ", select=" + select + ", title=" + title + "]";
	}
}
