package com.basic.framework.processframe.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.processframe.model.ProcessState;
import com.basic.framework.processframe.thread.ThreadLauncher;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.server.JettyServiceInterface;
import com.basic.framework.threadframe.dto.ThreadState;

/**
 * 
 *
 * @date 2017年7月21日 下午4:16:08
 * 
 * @Description: 线程监控的jetty实现类
 *
 */
//@Service
public class ProcessJettyServiceImpl implements JettyServiceInterface{

	private static final Logger logger = LoggerFactory.getLogger(ProcessJettyServiceImpl.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	private static final String METHOD_GET_THREAD_STATE = "getThreadState";

//	private ThreadManager threadManager;
	
	public ProcessJettyServiceImpl(){
		
	}
	
//	public ProcessJettyServiceImpl(ThreadManager threadManager){
//		this.threadManager = threadManager;
//	}

	@Override
	public void invoke(Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String result = null;
		String method = request.getParameter("method");
		logger.debug("method:{}",method);
		if(METHOD_GET_THREAD_STATE.equals(method)){
			Map<String,ThreadLauncher> map = ProcessElection.map;
//			Map<String, List<ThreadState>> threadStateMap = new HashMap<>();
			List<ProcessState> processStates = new ArrayList<>();
			Set<String> keySet = map.keySet();
			ThreadLauncher threadLauncher;
			for(String key : keySet){
				threadLauncher = map.get(key);
				List<ThreadState> list = threadLauncher.getThreadState();
//				threadStateMap.put(key, list);
				ProcessState processState = new ProcessState();
				processState.setKey(key);
				processState.setValue(JSON_UTILS.objectToJson(list));
				processStates.add(processState);
			}
			result = JSON_UTILS.objectToJson(processStates);
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(result);
		response.getWriter().close();
	}

	
}
