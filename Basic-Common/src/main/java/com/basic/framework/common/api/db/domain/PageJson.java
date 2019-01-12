package com.basic.framework.common.api.db.domain;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("rawtypes")
public class PageJson implements Serializable   {
	// 总记录数
	private Integer total = 0;

	private PageResult pageResult   = null;

	// 行记录

	private List rows = null;

	public PageJson() {

	}

	public PageJson(List rows, Integer total) {
		this.rows = rows;
		this.total = total;
	}

	public PageJson(PageList pageList) {
		this.rows = pageList;
		try {
			this.total = pageList.getPageResult().getTotalCount();
			this.pageResult = pageList.getPageResult();
		} catch (Exception e) {
		}
	}

	public PageJson(List arrayList) {
		this.rows = arrayList;
		if(arrayList instanceof PageList){
			try {
				this.total = ((PageList) arrayList).getPageResult().getTotalCount();
				this.pageResult = ((PageList) arrayList).getPageResult();
			} catch (Exception e) {
			}
		}

		else {
            this.total = arrayList.size();
        }
	}


	/**
	 * pageResult
	 *
	 * @return the pageResult
	 * @since 1.0.0
	 */

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public PageResult getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
}
