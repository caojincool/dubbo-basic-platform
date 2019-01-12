package com.basic.framework.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.basic.framework.common.model.TreeBean;
import com.basic.framework.common.utils.datatype.JsonUtils;

public class TreeUtils {
	
	private static TreeUtils inst = new TreeUtils();
	private static final Long ROOT_ID = -1L;//根节点的父节点标识
//	private static final String TRUE = "true";
//	private static final String FALSE = "false";
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	private TreeUtils(){
		
	}
	
	public static TreeUtils getInstance(){
		return inst;
	}
	
	/**
	 * 生成树型结构，即填充：isLeaf、expanded、level
	 * @param treeBeanList
	 * @return
	 * @throws Exception
	 */
	public List<TreeBean> makeTree(List<? extends TreeBean> treeBeanList) throws Exception{
		
		List<TreeBean> retList = new ArrayList<TreeBean>();
		if(treeBeanList != null && !treeBeanList.isEmpty()){
			TreeBean root = this.getRoot(treeBeanList);//获取根节点
			//root.setLevel(0);
			retList.add(root);
			
			this.makeNodeTree(root, treeBeanList, retList);
		}
		return retList;
	}
	
	private void makeNodeTree(TreeBean node,List<? extends TreeBean> srcList,List<? super TreeBean> targetList) throws Exception{
		System.out.println("makeNodeTree nodeID:"+node.getId());
		
		TreeBean child = this.getChild(node, srcList);
		if(child!=null){
			//node.setIsLeaf(FALSE);
			//node.setExpanded(TRUE);
			//node.setIsParent(TRUE);
			targetList.add(child);
			
			//child.setExpanded(TRUE);
			//child.setLevel(node.getLevel()+1);
			
			this.makeNodeTree(child, srcList, targetList);
			
		}else{
			//node.setIsLeaf(TRUE);
			//node.setExpanded(FALSE);
			//node.setIsParent(FALSE);
		}
		TreeBean brotherNode = this.getBrotherNode(node, srcList);
		if(brotherNode!=null){
			//brotherNode.setLevel(node.getLevel());
			
			targetList.add(brotherNode);
			
			this.makeNodeTree(brotherNode, srcList, targetList);
			
		}
		
	}
	
	/**
	 * 获取兄弟节点
	 * @param node
	 * @param treeBeanList
	 * @return
	 */
	private TreeBean getBrotherNode(TreeBean node,List<? extends TreeBean> treeBeanList){
		System.out.println("getBrotherNode node:"+node.getId());
		if(treeBeanList !=null ){
			for(TreeBean treeBean:treeBeanList){
				//if( node.getParent().equals(treeBean.getParent())){
				if(node.getpId().equals(treeBean.getpId())){
					treeBeanList.remove(treeBean);
					System.out.println("getBrotherNode:"+treeBean.getId());
					return treeBean;
				}
			}
		}
		return null;
	}
	
	/**
	 * 获取子节点
	 * @param node
	 * @param treeBeanList
	 * @return
	 */
	public TreeBean getChild(TreeBean node,List<? extends TreeBean> treeBeanList){
		System.out.println("getChild node:"+node.getId());
		if(treeBeanList !=null ){
			for(TreeBean treeBean:treeBeanList){
				//if( node.getId().equals(treeBean.getParent())){
				if( node.getId().equals(treeBean.getpId())){
					treeBeanList.remove(treeBean);
					System.out.println("getChild:"+treeBean.getId());
					return treeBean;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 获取所有根节点，并将根节点在列表中移移
	 * @param treeBeanList
	 * @return
	 * @throws Exception
	 */
	private TreeBean getRoot(List<? extends TreeBean> treeBeanList) throws Exception{
		if(treeBeanList !=null ){
			for(TreeBean treeBean:treeBeanList){
				//if(treeBean.getParent() == ROOT_ID){
				if(treeBean.getpId().equals(ROOT_ID)){
					treeBeanList.remove(treeBean);
					return treeBean;
				}
			}
		}
		return null;
	}
	
	
	
	public static void main(String args[]) throws Exception{
		List<TreeBean> list = makeList();
		
		List<TreeBean> retList = TreeUtils.getInstance().makeTree(list);
		
		System.out.println(JSON_UTILS.objectToJson(retList));
		
		
	}
	
	private static List<TreeBean> makeList(){
		List<TreeBean> list = new ArrayList<TreeBean>();
		
		list.add(new TreeBean(12L,1L));
		list.add(new TreeBean(14L,1L));
		list.add(new TreeBean(21L,2L));
		
		list.add(new TreeBean(1L,-1L));
		list.add(new TreeBean(2L,-1L));
		list.add(new TreeBean(11L,1L));
		
		list.add(new TreeBean(111L,11L));
		list.add(new TreeBean(112L,11L));
		list.add(new TreeBean(13L,1L));
		
		
		return list;
	}
	
}
