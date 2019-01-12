package com.basic.framework.gid.server;

import org.junit.*;

import static junit.framework.Assert.*;  

public class TestDAO {

	@Test
	public void testDAO() throws Exception{
		
		GidDAO gidDAO = BeanFactoryUtils.getBeanByName("gidDAO", GidDAO.class);
		int count = gidDAO.updateCurrValue(2L, 1L, "TEST_SEQ");
		assertEquals("1",count);
	}
	
	public static void main(String args[]){
		GidDAO gidDAO = BeanFactoryUtils.getBeanByName("gidDAO", GidDAO.class);
		int count = gidDAO.updateCurrValue(2L, 1L, "TEST_SEQ");
		System.out.println(count);
	}
}
