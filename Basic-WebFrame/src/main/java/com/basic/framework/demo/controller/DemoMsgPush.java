package com.basic.framework.demo.controller;


//import java.util.ArrayList;
//import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.web.BaseController;
import com.basic.framework.common.utils.datatype.JsonUtils;
//import com.basic.framework.message.define.PartyTypeEnum;
//import com.basic.framework.message.model.MessagePartyBean;
//import com.basic.framework.message.model.MessageSendBean;
//import com.basic.framework.message.service.MessageService;

/**
 * 消息推送controller
 *
 * @date 2016年8月18日 下午2:37:00
 * @author lzj
 * @Description: 空白页controller
 *
 */
@Controller
@RequestMapping(value = DemoMsgPush.REQUEST_PATH)
public class DemoMsgPush extends BaseController {

	protected static final String REQUEST_PATH = "/demo";

	private static Logger logger = LoggerFactory.getLogger(DemoMsgPush.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
//	@Autowired
//	private MessageService messageService;
	
	/**
	 * 演示页
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/menuPage/demoMsgPush")
	public ModelAndView demoBlank(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		long t0 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoMsgPush");
		
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		long t1 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{},耗时:{}ms", JSON_UTILS.objectToJson(request.getParameterMap()),(t1-t0));
		}
		
		return pageView;
	}

	//发送消息，有目标的
	@RequestMapping(value = "/func/sendMsg")
	@ResponseBody
	public Object sendMsg(Long toUserId,String msgText,HttpServletRequest request,HttpServletResponse response, Model model)  {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  del getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		
		String json = null;
		try {
//			MessageSendBean messageSendBean = new MessageSendBean();
//			messageSendBean.setMsgTitle("标题");
//			messageSendBean.setMsgContent(msgText);
//			messageSendBean.setMsgType("消息类型");
//			messageSendBean.setSourceId(null);
//			messageSendBean.setPushTag("100");
//			messageSendBean.setImmediateFlag(true);
//			messageSendBean.setSendTime(null);
//			
//			List<MessagePartyBean> userIds = new ArrayList<MessagePartyBean>();
//			MessagePartyBean userId = new MessagePartyBean();
//			userId.setPartyType(PartyTypeEnum.USER.getCode());
//			userId.setPartyId(toUserId);
//			userId.setPartyName(null);
//			userIds.add(userId);
//			messageSendBean.setMessagePartyList(userIds);
//			
//			messageService.sendMessage(messageSendBean);
			//json = "success";
		} catch (Exception e) {
			logger.error("删除失败:{}", e);
			//json = "failure";
		}
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	


}
