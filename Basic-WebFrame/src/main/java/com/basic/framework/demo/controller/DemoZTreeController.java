package com.basic.framework.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.web.BaseController;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.demo.model.DemoGridTreeQryCondBean;
import com.basic.framework.demo.model.DemoOrg;
//import com.basic.framework.common.utils.TreeUtils;
//import com.basic.framework.demo.model.DemoOrg;
import com.basic.framework.demo.service.DemoOrgService;

/**
 * 
 *
 * @date 2016年8月10日 下午7:07:52
 * @author 杰
 * @Description: ztree的使用
 *
 */
@Controller
@RequestMapping(value = DemoZTreeController.REQUEST_PATH)
public class DemoZTreeController extends BaseController {

	protected static final String REQUEST_PATH = "/demo";

	private static Logger logger = LoggerFactory.getLogger(DemoZTreeController.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
//	@Autowired
	private DemoOrgService demoOrgService;

	public void setDemoOrgService(DemoOrgService demoOrgService) {
		this.demoOrgService = demoOrgService;
	}

	/**
	 * grid演示
	 * 
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/menuPage/demoZTree")
	public ModelAndView demoGridTree(HttpServletRequest request, HttpServletResponse response, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  demoGridTree getParameterMap:{}",
					JSON_UTILS.objectToJson(request.getParameterMap()));
		}

		this.doBeforeMenuPageAction(request, response, model, null);

		// 业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoZTree");

		// 业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);

		return pageView;
	}

	/**
	 * grid演示
	 * 
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/func/demoGridTree/qryTree")
	@ResponseBody
	public String qryTree(DemoGridTreeQryCondBean demoGridTreeQryCondBean, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  demoGridTree demoGridTreeQryCondBean:{},getParameterMap:{}",
					JSON_UTILS.objectToJson(demoGridTreeQryCondBean),
					JSON_UTILS.objectToJson(request.getParameterMap()));
		}

		this.doBeforeMenuPageAction(request, response, model, null);
		String json = null;
		Long id = demoGridTreeQryCondBean.getId();
		if(null == id){
			id = -1L;
		}
		List<DemoOrg> list = demoOrgService.qryOaasOrgTreeStep(id);
/*		Integer nLevel = demoGridTreeQryCondBean.getN_level();

		if (list != null && list.size() > 0) {
			for (DemoOrg demoOrg : list) {

				demoOrg.setLevel(nLevel + 1);
			}
		}

		Integer page = null;
		Integer total = null;
		Integer records = null;
		json = this.toDataGridJson(page, total, records, list);*/

		json = JSON_UTILS.objectToJson(list);
		// 业务逻辑结束

		this.doAfterMenuPageAction(request, response, model, null);
		return json;
	}
	
	
	/**
	 * grid一次性加载数据
	 * 
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/func/demoZTree/qryTreeAll")
	@ResponseBody
	public String qryTreeAll(DemoGridTreeQryCondBean demoGridTreeQryCondBean, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  qryTreeAll demoGridTreeQryCondBean:{},getParameterMap:{}",
					JSON_UTILS.objectToJson(demoGridTreeQryCondBean),
					JSON_UTILS.objectToJson(request.getParameterMap()));
		}

		this.doBeforeMenuPageAction(request, response, model, null);
		
		List<DemoOrg> list = demoOrgService.qryOaasOrgTree();
//		List<TreeBean> list1 = TreeUtils.getInstance().makeTree(list);
		
		//String json = this.toDataGridJson(1, 1, list1.size(), list1);
		String json = JSON_UTILS.objectToJson(list);
		
		
		// 业务逻辑结束
		if (logger.isDebugEnabled()) {
			logger.debug("qryTreeAll json:{}",json);
		}

		this.doAfterMenuPageAction(request, response, model, null);
		return json;
	}

	/**
	 * grid一次性加载数据
	 * 
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/func/demoZTree/qryTreeAll2")
	@ResponseBody
	public String qryTreeAll2(DemoGridTreeQryCondBean demoGridTreeQryCondBean, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  qryTreeAll demoGridTreeQryCondBean:{},getParameterMap:{}",
					JSON_UTILS.objectToJson(demoGridTreeQryCondBean),
					JSON_UTILS.objectToJson(request.getParameterMap()));
		}

		this.doBeforeMenuPageAction(request, response, model, null);
		
		
		
		String json = null;
		
		StringBuffer js = new StringBuffer();
		
		
		js.append("[{\"id\":0,\"orgCode\":0,\"name\":\"中国电信集团\",\"level\":0,\"namePath\":\"USA\",\"isLeaf\":\"false\",\"age\":20,\"expanded\":\"true\",\"password\":\"123\"},");
		js.append("{\"id\":1,\"orgCode\":1,\"name\":\"广东省公司\",\"level\":1,\"namePath\":\"USA\",\"isLeaf\":\"false\",\"age\":20,\"expanded\":\"true\",\"pId\":0,\"password\":\"123\"},");
		js.append("{\"id\":2,\"orgCode\":2,\"name\":\"广州分公司\",\"level\":2,\"namePath\":\"USA\",\"isLeaf\":\"false\",\"age\":20,\"expanded\":\"true\",\"pId\":1,\"password\":\"123\"},");
		js.append("{\"id\":3,\"orgCode\":3,\"name\":\"天河分局\",\"level\":3,\"namePath\":\"USA\",\"isLeaf\":\"true\",\"age\":20,\"expanded\":\"false\",\"pId\":2,\"password\":\"123\"},");
		js.append("{\"id\":4,\"orgCode\":4,\"name\":\"深圳分公司\",\"level\":2,\"namePath\":\"USA\",\"isLeaf\":\"true\",\"age\":20,\"expanded\":\"false\",\"pId\":1,\"password\":\"123\"},");
		js.append("{\"id\":5,\"orgCode\":5,\"name\":\"湖南公司\",\"level\":1,\"namePath\":\"USA\",\"isLeaf\":\"false\",\"age\":20,\"expanded\":\"true\",\"pId\":0,\"password\":\"123\"},");
		js.append("{\"id\":6,\"orgCode\":6,\"name\":\"长沙分公司\",\"level\":2,\"namePath\":\"USA\",\"isLeaf\":\"true\",\"age\":20,\"expanded\":\"false\",\"pId\":5,\"password\":\"123\"}");
		js.append(" ]");
		
		
		json = js.toString();
				
		// 业务逻辑结束
		if (logger.isDebugEnabled()) {
			logger.debug("qryTreeAll json:{}",json);
		}

		this.doAfterMenuPageAction(request, response, model, null);
		return json;
	}

}
