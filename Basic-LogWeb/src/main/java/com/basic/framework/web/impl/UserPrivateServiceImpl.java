package com.basic.framework.web.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.basic.framework.web.api.UserPrivateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.demo.model.DemoCatalog;
import com.basic.framework.demo.model.DemoMenu;

/**
 * 
 *
 * @date 2017年8月28日 上午10:32:21
 * 
 * @Description: 用于加载用户的权限，包括菜单等
 *
 */
public class UserPrivateServiceImpl implements UserPrivateService {
    
	private Logger logger = LoggerFactory.getLogger(UserPrivateServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

	/* (non-Javadoc)
	 * @see com.basic.framework.web.api.UserPrivateService#appendUserMenuData(javax.servlet.http.HttpServletRequest, org.springframework.ui.Model)
	 */
	@Override
	public void appendUserMenuData(HttpServletRequest request, Model model) {
		getMenu(request, model);
	}
	
	/**
	 * 
	 * @date 2017年8月28日 上午10:35:46
	 * 
	 * @Description: TODO(后台加载菜单，后面去掉该方法)
	 * @param request
	 * @param model
	 *
	 */
	private void getMenu(HttpServletRequest request, Model model) {
		logger.debug("所有菜单");
		
		List<DemoMenu> children = new ArrayList<>();
		
		DemoMenu childrenMenu1 = new DemoMenu();
		childrenMenu1.setMenuId(1L);
		childrenMenu1.setMenuName("空页面");
		childrenMenu1.setMenuUrl("demo/menuPage/demoBlank.do");
		children.add(childrenMenu1);
		
		DemoMenu childrenMenu2 = new DemoMenu();
		childrenMenu2.setMenuId(2L);
		childrenMenu2.setMenuName("基础表单");
		childrenMenu2.setMenuUrl("demo/menuPage/demoForm.do");
		children.add(childrenMenu2);
		
		DemoMenu childrenMenu3 = new DemoMenu();
		childrenMenu3.setMenuId(3L);
		childrenMenu3.setMenuName("列表");
		childrenMenu3.setMenuUrl("demo/menuPage/demoGrid.do");
		children.add(childrenMenu3);
		
		DemoMenu childrenMenu4 = new DemoMenu();
		childrenMenu4.setMenuId(4L);
		childrenMenu4.setMenuName("列表勾选");
		childrenMenu4.setMenuUrl("demo/menuPage/demoGridCheck.do");
		children.add(childrenMenu4);
		
		DemoMenu childrenMenu5 = new DemoMenu();
		childrenMenu5.setMenuId(5L);
		childrenMenu5.setMenuName("列表分页");
		childrenMenu5.setMenuUrl("demo/menuPage/demoGridPage.do");
		children.add(childrenMenu5);
		
		DemoMenu childrenMenu6 = new DemoMenu();
		childrenMenu6.setMenuId(6L);
		childrenMenu6.setMenuName("页面布局");
		childrenMenu6.setMenuUrl("demo/menuPage/demoPageLayout.do");
		children.add(childrenMenu6);
		
		DemoMenu childrenMenu7 = new DemoMenu();
		childrenMenu7.setMenuId(7L);
		childrenMenu7.setMenuName("弹窗");
		childrenMenu7.setMenuUrl("demo/menuPage/demoOpenWin.do");
		children.add(childrenMenu7);
		
		DemoMenu childrenMenu8 = new DemoMenu();
		childrenMenu8.setMenuId(8L);
		childrenMenu8.setMenuName("表格切换");
		childrenMenu8.setMenuUrl("demo/menuPage/demoTab.do");
		children.add(childrenMenu8);
		
		DemoMenu childrenMenu9 = new DemoMenu();
		childrenMenu9.setMenuId(9L);
		childrenMenu9.setMenuName("ZTree");
		childrenMenu9.setMenuUrl("demo/menuPage/demoZTree.do");
		children.add(childrenMenu9);
		
		DemoMenu childrenMenu10 = new DemoMenu();
		childrenMenu10.setMenuId(10L);
		childrenMenu10.setMenuName("Demo展示");
		childrenMenu10.setMenuUrl("demo/menuPage/demoPlan.do");
		children.add(childrenMenu10);
		
		DemoMenu childrenMenu11 = new DemoMenu();
		childrenMenu11.setMenuId(11L);
		childrenMenu11.setMenuName("Demo图标");
		childrenMenu11.setMenuUrl("demo/menuPage/demoIcon.do");
		children.add(childrenMenu11);
		
		DemoMenu childrenMenu12 = new DemoMenu();
		childrenMenu12.setMenuId(11L);
		childrenMenu12.setMenuName("系統日志配置");
		childrenMenu12.setMenuUrl("log/menuPage/systemConfig.do");
		children.add(childrenMenu12);
		
		DemoMenu childrenMenu13 = new DemoMenu();
		childrenMenu13.setMenuId(11L);
		childrenMenu13.setMenuName("系統日志");
		childrenMenu13.setMenuUrl("log/menuPage/systemLog.do");
		children.add(childrenMenu13);
		
		
		List<DemoCatalog>  menuTreeList = new ArrayList<DemoCatalog>();
		
		DemoCatalog catalog1 = new DemoCatalog();
		catalog1.setCatalogName("演示");
		catalog1.setChildObj(new ArrayList<Object>(children));
		
		
		menuTreeList.add(catalog1);
		if (menuTreeList != null && menuTreeList.size() > 0) {
			model.addAttribute("menuTreeList", menuTreeList);
			model.addAttribute("menuNamePath", "一级|二级|写死的");
		}
	}


}
