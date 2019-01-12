package com.basic.oaas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.basic.framework.common.model.DealResult;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.BaseController;
import com.basic.oaas.bean.StaffJobIbean;
import com.basic.oaas.model.StaffJob;
import com.basic.oaas.service.StaffJobService;

/**
 * 
 *
 * @date 2017年8月16日 下午4:01:57
 * @author Kevin
 * @Description: 员工职位表
 *
 */
@Controller
@RequestMapping(value = StaffJobController.REQUEST_PATH)
public class StaffJobController extends BaseController {

	protected static final String REQUEST_PATH = "/oaas";

	private static Logger logger = LoggerFactory.getLogger(StaffJobController.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

	@Autowired
	private StaffJobService staffJobService;

	/**
	 * 
	 * @date 2017年8月2日 下午2:48:15
	 * @author Kevin
	 * @Description: 员工职位列表查询
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/staffJob/pagin")
	@ResponseBody
	public String pagin(StaffJobIbean ibean, HttpServletRequest request, HttpServletResponse response, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  pagin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}

		// 业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;

		List<StaffJob> list = staffJobService.qryStaffJobList(ibean);
		int records = (int) staffJobService.qryStaffJobListCount(ibean);// 总记录数

		Integer page = ibean.getPage();

		int total = ibean.getTotalPage(records);

		json = this.toDataGridJson(page, total, records, list);

		// 业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);

		if (logger.isDebugEnabled()) {
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}

	/**
	 * 
	 * @date 2017年8月2日 下午2:56:49
	 * @author Kevin
	 * @Description: 员工职位新增编辑操作
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/func/staffJob/createOrModify")
	@ResponseBody
	public Object createOrModify(HttpServletRequest request, HttpServletResponse response, Model model)
			throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  createOrModify getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		// 业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		try {
			// 获取并校验参数
			StaffJob ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"), StaffJob.class);

			staffJobService.createOrModify(ibean);

			dealResult.setReturnCode(DealResult.SUCCESS);
		} catch (Exception e) {
			logger.error("员工职位新增编辑操作失败：{}", e);

			dealResult.setReturnCode(DealResult.FAILURE);
			dealResult.setReturnMsg(e.getMessage());

		}

		json = JSON_UTILS.objectToJson(dealResult);
		// 业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}

	/**
	 * 
	 * @date 2017年8月2日 下午2:59:50
	 * @author Kevin
	 * @Description: 员工删除，批量修改状态
	 * @param dictId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/staffJob/del")
	@ResponseBody
	public Object del(Long[] staffJobIds, HttpServletRequest request, HttpServletResponse response, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  del getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		// 业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);

		String json = null;
		DealResult dealResult = new DealResult();

		try {
			if (staffJobIds != null && staffJobIds.length > 0) {
				staffJobService.removeBatchByStaffJobIds(staffJobIds);
				dealResult.setReturnCode(DealResult.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("删除失败:{}", e);
			dealResult.setReturnCode(DealResult.FAILURE);
			dealResult.setReturnMsg(e.getMessage());
		}

		json = JSON_UTILS.objectToJson(dealResult);
		// 业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}

	/**
	 * 
	 * @date 2017年8月11日 下午5:44:38
	 * @author Kevin
	 * @Description: 验证员工号是否存在
	 * @param Ibean
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/staffJob/verifyJob")
	@ResponseBody
	public Object verifyJob(StaffJobIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  verifyJob StaffJobIbean:", JSON_UTILS.objectToJson(ibean));
		}

		// 业务逻辑开始
		this.doAfterFuncPageAction(null, null, null, null);
		String json = "{\"flag\":false}";

		int count = staffJobService.qryExistStaffJob(ibean);
		if (count == 0) {
			json = "{\"flag\":true}";
		}

		// 业务逻辑结束
		this.doAfterFuncPageAction(null, null, null, null);
		return json;
	}

}
