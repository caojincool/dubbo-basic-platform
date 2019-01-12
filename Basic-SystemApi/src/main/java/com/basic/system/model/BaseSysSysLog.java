package com.basic.system.model;
import com.basic.framework.common.model.AbstractModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

 /**
 * 
 * <pre> 
 * 描述：记录与外围系统接口调用的日志 实体对象
 * 构建组：basic
 * 作者:lengzj
 * 邮箱:mail@com
 * 日期:2018-06-14 12:58:25
 * 版权：companyName
 * </pre>
 */
public class BaseSysSysLog extends AbstractModel<Long>{
	
	
	/**
	* 日志标识
	*/
	protected Long logId; 
	
	
	/**
	* 请求URL
	*/
	protected String requestUrl; 
	
	
	/**
	* 入参
	*/
	protected String inParms; 
	
	
	/**
	* 出参
	*/
	protected String outParams; 
	
	
	/**
	* 接口状态
	*/
	protected String interfaceStatus; 
	
	
	/**
	* 异常消息
	*/
	protected String errMsg; 
	
	
	/**
	* 接口类型，按数据字典
	*/
	protected String interfaceType; 
	
	
	/**
	* 调用系统标识
	*/
	protected String sysCode; 
	
	
	/**
	* 备注
	*/
	protected String remarks; 
	

	
	public void setLogId(Long logId) {
		this.logId = logId;
	}
	
	/**
	 * 返回 日志标识
	 * @return
	 */
	public Long getLogId() {
		return this.logId;
	}
	
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	
	/**
	 * 返回 请求URL
	 * @return
	 */
	public String getRequestUrl() {
		return this.requestUrl;
	}
	
	public void setInParms(String inParms) {
		this.inParms = inParms;
	}
	
	/**
	 * 返回 入参
	 * @return
	 */
	public String getInParms() {
		return this.inParms;
	}
	
	public void setOutParams(String outParams) {
		this.outParams = outParams;
	}
	
	/**
	 * 返回 出参
	 * @return
	 */
	public String getOutParams() {
		return this.outParams;
	}
	
	public void setInterfaceStatus(String interfaceStatus) {
		this.interfaceStatus = interfaceStatus;
	}
	
	/**
	 * 返回 接口状态
	 * @return
	 */
	public String getInterfaceStatus() {
		return this.interfaceStatus;
	}
	
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	/**
	 * 返回 异常消息
	 * @return
	 */
	public String getErrMsg() {
		return this.errMsg;
	}
	
	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}
	
	/**
	 * 返回 接口类型，按数据字典
	 * @return
	 */
	public String getInterfaceType() {
		return this.interfaceType;
	}
	
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	
	/**
	 * 返回 调用系统标识
	 * @return
	 */
	public String getSysCode() {
		return this.sysCode;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * 返回 备注
	 * @return
	 */
	public String getRemarks() {
		return this.remarks;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
		return new ToStringBuilder(this)
		.append("logId", this.logId) 
		.append("requestUrl", this.requestUrl) 
		.append("inParms", this.inParms) 
		.append("outParams", this.outParams) 
		.append("interfaceStatus", this.interfaceStatus) 
		.append("errMsg", this.errMsg) 
		.append("interfaceType", this.interfaceType) 
		.append("sysCode", this.sysCode) 
		.append("remarks", this.remarks) 
		.append("backup1", this.backup1) 
		.append("backup2", this.backup2) 
		.append("backup3", this.backup3) 
		.append("backup4", this.backup4) 
		.append("backup5", this.backup5) 
		.append("state", this.state) 
		.append("createUserId", this.createUserId) 
		.append("createTime", this.createTime) 
		.append("modifyUserId", this.modifyUserId) 
		.append("modifyTime", this.modifyTime) 
		.append("accountSet", this.accountSet) 
		.toString();
	}
}