package com.basic.framework.web.impl;

import com.basic.framework.common.utils.Collections3;
import com.basic.framework.common.utils.ReflectUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.api.UserPrivateService;
import com.basic.oaas.model.PrivateMenu;
import com.basic.oaas.model.PrivateMenuCatalog;
import com.basic.oaas.service.PrivateMenuCatalogService;
import com.basic.oaas.service.PrivateMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
	
	@Autowired
	private PrivateMenuService privateMenuService;
	
	@Autowired
	private PrivateMenuCatalogService privateMenuCatalogService;
	
	//创建排序器
	private Comparator<Object> comparator = new Comparator<Object>() {

		private String fieldName = "displayIndex";
		@Override
		public int compare(Object o1, Object o2) {
			
			Long o1Index =  (Long) ReflectUtils.getFieldValue(o1, fieldName);
			Long o2Index =  (Long) ReflectUtils.getFieldValue(o2, fieldName);
			
			if(o1Index == null || o2Index == null){
				return -1;
			}
			return o1Index.compareTo(o2Index);
		}
		
	};

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
	@SuppressWarnings("unchecked")
	private void getMenu(HttpServletRequest request, Model model) {
		logger.debug("所有菜单");
		
		long startDate =  System.currentTimeMillis();
		SecurityContextImpl securityContextImpl = (SecurityContextImpl) request
				.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if(securityContextImpl != null){
			
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) securityContextImpl
					.getAuthentication().getAuthorities();
			
			//获取权限编码集合
			List<String> privateCodes = Collections3.extractToList(authorities, "authority");
			
			List<PrivateMenu> menus = privateMenuService.qryMenuByCode(privateCodes);
			
			//获取所有目录
			List<PrivateMenuCatalog> catalogs = privateMenuCatalogService.qryAllCatalog();
			
			//递归获取目录树
			//catalogs = getChild(catalogs,menus,null);
			
			List<Object> menuList = new ArrayList<Object>(catalogs);
			
			//移除空的目录
			this.removeEmptyCatalog(menuList);
			
			String url = request.getRequestURI();
			url = url.substring(url.indexOf("/", 1)+1);
			
			//根据URL获取名称路径
			String namePath =  this.getMenuNamePath(menuList,url);
			if (!Collections3.isEmpty(catalogs)) {
				model.addAttribute("menuTreeList", menuList);
				model.addAttribute("menuNamePath", namePath);
			}
		}
		long endDate = System.currentTimeMillis();
		logger.debug("消耗的时间:{}",endDate -startDate);
		
	}
	
	
	
	/**
	 * 
	 * @date 2017年9月21日 下午5:47:33
	 * @author Kevin
	 * @Description: 去除空目录
	 * @param catalogs
	 * @return
	 *
	 */
	private List<Object> removeEmptyCatalog(List<Object> objects){
		logger.debug("去除空目录 objects :{}", JSON_UTILS.objectToJson(objects));
		// 假设目录不为空
		boolean flag = true;
		
		
		
		while (flag) {
			
			if(Collections3.isEmpty(objects)){
				return null;
			}
			for (Object obj : objects) { 
				if(obj instanceof PrivateMenuCatalog){
					
					PrivateMenuCatalog cata = (PrivateMenuCatalog) obj;
					
					if(Collections3.isEmpty(cata.getChildObj())){
						objects.remove(cata);
						logger.debug("移除空目录 cata",cata.getCatalogName());
						flag = true;
						break;
					}else{
						removeEmptyCatalog(cata.getChildObj());
						flag = false;
					}
					
				}else{
					flag = false;
				}
				
			}
		}
		
		return objects;
	}
	
	
	/**
	 * 
	 * @date 2017年9月25日 上午10:05:43
	 * @author Kevin
	 * @Description: 获取菜单名称
	 * @param catalogs
	 * @param url
	 * @return
	 *
	 */
	private String getMenuNamePath(List<Object> catalogs , String url){
		
		String menuNamePath = null;
		
		for (Object c : catalogs) {
			String name = null;
			
			if(c instanceof PrivateMenuCatalog){
				PrivateMenuCatalog pmc = (PrivateMenuCatalog) c;
				name = getMenuNamePath(pmc.getChildObj(), url);
				
				if(name != null){
					menuNamePath = pmc.getCatalogName() + "|" + name;
				}
			}else if(c instanceof PrivateMenu){
				PrivateMenu pm = (PrivateMenu) c;
				
				if(pm.getMenuUrl().equals(url)){
					return pm.getMenuName();
				}
			}
			
		}
		
		return menuNamePath;
	}


}
