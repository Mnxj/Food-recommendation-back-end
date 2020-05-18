package com.qufu.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SessionManager {
    public static Map<String, HttpSession> sessionPool = new HashMap<String, HttpSession>();

    /**
     * 存储session
     *
     * @param session
     */
    public static void saveSession(HttpSession session) {
        sessionPool.put(session.getId(), session);
    }

    /**
     * 根据sessionId获取session
     *
     * @param sid
     * @return
     */
    public static HttpSession getSession(String sid) {
        return sessionPool.get(sid);
    }

    /**
     * 移除session
     *
     * @param sid
     */
    public static void remove(String sid) {
        sessionPool.remove(sid);
    }


}
