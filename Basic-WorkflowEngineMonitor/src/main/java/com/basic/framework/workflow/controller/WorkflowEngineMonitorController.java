package com.basic.framework.workflow.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.common.model.DealResult;
import com.basic.framework.common.utils.Exceptions;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.messagequeue.impl.MessageSenderActiveMQ;
import com.basic.framework.web.BaseController;
import com.basic.framework.workflow.bean.WorkflowIbean;
import com.basic.framework.workflow.define.CommandQueueCode;
import com.basic.framework.workflow.define.CommandSendFlag;
import com.basic.framework.workflow.define.NoticeQueueCode;
import com.basic.framework.workflow.define.NoticeSendFlag;
import com.basic.framework.workflow.define.WorkflowError;
import com.basic.framework.workflow.exception.WorkflowException;
import com.basic.framework.workflowcli.model.CliWorkflowCommand;
import com.basic.framework.workflowcli.model.CliWorkflowNoticeExec;
import com.basic.framework.workflowcli.service.CliWorkflowCommandService;
import com.basic.framework.workflowcli.service.CliWorkflowNoticeExecService;
import com.basic.framework.workflowser.model.SerWorkflowCommandExec;
import com.basic.framework.workflowser.model.SerWorkflowNotice;
import com.basic.framework.workflowser.service.SerWorkflowCommandExecService;
import com.basic.framework.workflowser.service.SerWorkflowNoticeService;

/**
 * 
 *
 * @date 2017年8月8日 下午4:59:25
 * 
 * @Description: 流程监控
 *
 */
@Controller
@RequestMapping(value = WorkflowEngineMonitorController.REQUEST_PATH)
public class WorkflowEngineMonitorController extends BaseController{

	protected static final String REQUEST_PATH = "/workflow";
	
	private static Logger logger = LoggerFactory.getLogger(WorkflowEngineMonitorController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	private static final int SEND_RESULT_LENGTH = 256;
	
	@Autowired
	private CliWorkflowCommandService cliWorkflowCommandService;
	@Autowired
	private CliWorkflowNoticeExecService cliWorkflowNoticeExecService;
	@Autowired
	private SerWorkflowCommandExecService serWorkflowCommandExecService;
	@Autowired
	private SerWorkflowNoticeService serWorkflowNoticeService;
	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private MessageSenderActiveMQ messageSenderActiveMQ;

	/**
	 * 
	 * @date 2017年8月8日 下午5:00:46
	 * 
	 * @Description: 流程监控主页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/workflowEngine")
	public ModelAndView intoPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  intoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/workflow/workflowEngine/workflowEngine");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年8月8日 下午5:02:23
	 * 
	 * @Description: 流程监控主页面列表查询
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/workflowEngine/pagin")
	@ResponseBody
	public String pagin(WorkflowIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  pagin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<HistoricProcessInstance> list = null;
		
		//查询流程实例
		if(StringUtils.isNoneBlank(ibean.getProcessInstanceId())){
			list = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(ibean.getProcessInstanceId()).list();
		}else if(StringUtils.isNoneBlank(ibean.getProcessDefineKey())){
			list = processEngine.getHistoryService().createHistoricProcessInstanceQuery().processDefinitionKey(ibean.getProcessDefineKey()).orderByProcessInstanceStartTime().asc().list();
		}else{
			list = processEngine.getHistoryService().createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().asc().list();
		}
		
		json = this.toDataGridJson(null, 0, 0, list);

		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	/**
	 * 
	 * @date 2017年8月9日 下午2:14:47
	 * 
	 * @Description: 查询客户端命令
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/workflowEngine/qryCliWorkflowCommandList")
	@ResponseBody
	public String qryCliWorkflowCommandList(WorkflowIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryCliWorkflowCommandList getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<CliWorkflowCommand> list = cliWorkflowCommandService.qryCliWorkflowCommandList(ibean);
		
		json = this.toDataGridJson(null, 0, 0, list);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	/**
	 * 
	 * @date 2017年8月9日 下午2:14:47
	 * 
	 * @Description: 查询服务端命令执行
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/workflowEngine/qrySerWorkflowCommandExecList")
	@ResponseBody
	public String qrySerWorkflowCommandExecList(WorkflowIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qrySerWorkflowCommandExecList getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<SerWorkflowCommandExec> list = serWorkflowCommandExecService.qrySerWorkflowCommandExecList(ibean);
		
		json = this.toDataGridJson(null, 0, 0, list);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	/**
	 * 
	 * @date 2017年8月9日 下午2:14:47
	 * 
	 * @Description: 查询服务端通知
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/workflowEngine/qrySerWorkflowNoticeList")
	@ResponseBody
	public String qrySerWorkflowNoticeList(WorkflowIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qrySerWorkflowNoticeList getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<SerWorkflowNotice> list = serWorkflowNoticeService.qrySerWorkflowNoticeList(ibean);
		
		json = this.toDataGridJson(null, 0, 0, list);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	/**
	 * 
	 * @date 2017年8月9日 下午2:14:47
	 * 
	 * @Description: 查询客户端通知执行
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/workflowEngine/qryCliWorkflowNoticeExecList")
	@ResponseBody
	public String qryCliWorkflowNoticeExecList(WorkflowIbean ibean,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryCliWorkflowNoticeExecList getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<CliWorkflowNoticeExec> list = cliWorkflowNoticeExecService.qryCliWorkflowNoticeExecList(ibean);
		
		json = this.toDataGridJson(null, 0, 0, list);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	/**
	 * 
	 * @date 2017年8月9日 下午3:54:02
	 * 
	 * @Description: 发队列，服务端执行客户端的命令
	 * @param bean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws WorkflowException 
	 *
	 */
	@RequestMapping(value = "/func/workflowEngine/rerunCliWorkflowCommand")
	@ResponseBody
	public String rerunCliWorkflowCommand(CliWorkflowCommand bean,HttpServletRequest request,HttpServletResponse response, Model model) throws WorkflowException {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  rerunCliWorkflowCommand getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		bean.setErrorCode(null);
		
        String commandBeanJsonStr = JSON_UTILS.objectToJson(bean);
        logger.info("发送指令,commandBeanJsonStr:{}",commandBeanJsonStr);
//        GidClientUtils gidClientUtils = GidClientUtils.getInstance();
//        Long commandId = gidClientUtils.getGidValue(GidCodes.WFC_CLI_WORKFLOW_COMMAND_SEQ);
//        bean.setCommandId(commandId);
//        bean.setCreateTime(DateUtils.now());
        try{ //发送请求
            //写入队列
            bean.setSendFlag(CommandSendFlag.SUCCESS.getCode());
//            throw new Exception("ERROR?????????????");
            String key = bean.getCommandType();
            String message = JSON_UTILS.objectToJson(bean);
            messageSenderActiveMQ.sendMessage(key, CommandQueueCode.WORKFLOW_COMMAND_SENDCOMMAND_QUEUE.getCode(), message);
            
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
        }catch (Exception e){
            bean.setSendFlag(CommandSendFlag.FAILURE.getCode());
            String sendResult = Exceptions.getStackTraceAsString(e,SEND_RESULT_LENGTH);
            bean.setSendResult(sendResult);
            bean.setErrorCode(WorkflowError.SEND_COMMAND_ERROR_11001.getCode());
            
			dealResult.setReturnCode(DealResult.FAILURE);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
            
            throw new WorkflowException(WorkflowError.SEND_COMMAND_ERROR_11001.getCode(),null,e);
        }finally {

        	cliWorkflowCommandService.createSelective(bean);
        }
        
		json = JSON_UTILS.objectToJson(dealResult);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	/**
	 * 
	 * @date 2017年8月9日 下午3:54:02
	 * 
	 * @Description: 发队列，客户端执行服务端的通知
	 * @param bean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/workflowEngine/rerunSerWorkflowNotice")
	@ResponseBody
	public String rerunSerWorkflowNotice(SerWorkflowNotice bean,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  rerunSerWorkflowNotice getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		bean.setErrorCode(null);
		
        try{
            //将消息发送到队列
        	bean.setSendFlag(NoticeSendFlag.SUCCESS.getCode());
            
            String key = bean.getBusiOrderId().toString();
            String message = JSON_UTILS.objectToJson(bean);
            messageSenderActiveMQ.sendMessage(key, NoticeQueueCode.WORKFLOW_NOTICE_SENDNOTICE_QUEUE.getCode(), message);
            
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
        }catch (Exception e){
            String str = JSON_UTILS.objectToJson(bean);
            logger.error("通知发送异常,workflowNoticeBean:{}",str,e);
            bean.setSendFlag(NoticeSendFlag.FAILURE.getCode());
            bean.setErrorCode(WorkflowError.SEND_NOTICE_ERROR_12001.getCode());
            bean.setSendResult(Exceptions.getStackTraceAsString(e,SEND_RESULT_LENGTH));
            
			dealResult.setReturnCode(DealResult.FAILURE);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
        }finally {
        	serWorkflowNoticeService.createSelective(bean);
        }
        
		json = JSON_UTILS.objectToJson(dealResult);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}

	
}
