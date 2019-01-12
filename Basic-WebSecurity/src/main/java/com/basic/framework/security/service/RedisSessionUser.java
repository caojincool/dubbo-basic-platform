//package com.basic.framework.security.service;
//
//import java.util.List;
//
//import com.basic.framework.security.model.SessionUser;
//
///**
// * 
// *
// * @date 2017年6月23日 下午5:03:16
// * 
// * @Description: 操作redis上面的session
// *
// */
//public interface RedisSessionUser {
//
//	/**
//	 * 
//	 * @date 2017年6月23日 下午5:12:30
//	 * 
//	 * @Description: 存储Session
//	 * @param sessionUser
//	 *
//	 */
//    public void saveSession(SessionUser sessionUser);
//
//    /**
//     * 
//     * @date 2017年6月23日 下午5:12:58
//     * 
//     * @Description: 删除session
//     * @param sessionUser
//     *
//     */
//    public void removeSession(SessionUser sessionUser);
//
//    /**
//     * 
//     * @date 2017年6月23日 下午5:13:48
//     * 
//     * @Description: 获取session
//     * @param sessionUser
//     * @return
//     *
//     */
//    public SessionUser getSession(SessionUser sessionUser);
//
//    /**
//     * 
//     * @date 2017年6月23日 下午5:15:27
//     * 
//     * @Description: 获取所有sessoin
//     * @return
//     *
//     */
//    public List<SessionUser> getAllSessions();
//}
