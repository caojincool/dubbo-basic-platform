/**    
* @Project: fpd 
* @Title: HttpEntity 
* @Package com.wanfin.fpd.core 
* @Description [[_XXXXX_]]文件
* @author Chenh 
* @date 2016年8月24日 上午10:41:52   
* @version V1.0.0   */ 
package com.basic.framework.common.utils.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**   
 * @author Chenh  
 * @date 2016年8月24日 上午10:41:52 
 * @Description [[_XXXXX_]] HttpEntity类
 * TODO 
 *   
 */
@JsonInclude(Include.ALWAYS)
public class HttpEntity{
	private HttpStatus status; 
	private Boolean istrue;
	private String msg;
	private Object data;
	private Boolean showDialog;
	public HttpEntity() {
		super();
	}
	public HttpEntity(HttpStatus status,Boolean istrue,String msg,Object data) {
		this.status=status;
		this.istrue=istrue;
		this.msg=msg;
		this.data=data;
		this.showDialog=false;
	}
	public HttpEntity(HttpStatus status,Boolean istrue,Boolean showTips,String msg,Object data) {
		this.status=status;
		this.istrue=istrue;
		this.msg=msg;
		this.data=data;
		this.showDialog=showTips;
	}
	public HttpStatus getStatus() {
		return status;
	}
	
	public void setStatus(HttpStatus status) {
		this.status = status;
		if(this.status != null){
			if (this.status.equals(HttpStatus.OK) || this.status.equals(HttpStatus.CREATED) || this.status.equals(HttpStatus.NO_CONTENT)) {
				this.istrue = true;
			}else{
				this.istrue = false;
			}
			this.msg = this.status.getCnreasonPhrase();
		}
	}

	
	public Boolean getShowDialog() {
		return showDialog;
	}
	public void setShowDialog(Boolean showDialog) {
		this.showDialog = showDialog;
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Boolean getIstrue() {
		return istrue;
	}
	public void setIstrue(Boolean istrue) {
		this.istrue = istrue;
	}
}