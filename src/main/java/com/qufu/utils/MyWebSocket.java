package com.qufu.utils;


import com.qufu.mapper.UserMapper;
import com.qufu.pojo.SocketMsg;
import com.qufu.pojo.User;
import com.qufu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.*;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/ws/asset/{uid}")
@Component
public class MyWebSocket {
    //导入usermapper用来查询
    @Autowired
    UserMapper mapper;
    @Autowired
    UserService service;
    private static MyWebSocket myWebSocketUtil;

    //这样才可以调用mapper的参数
    @PostConstruct
    public void init() {
        myWebSocketUtil = this;
        myWebSocketUtil.mapper = this.mapper;
        myWebSocketUtil.service = this.service;
    }

    //遍历所有的用户用来发送好友
    public static List<Integer> getId() {
        return myWebSocketUtil.mapper.Selectid();
    }

    //查询自己的好友
    public static List<User> getUserwebsocket(int id1) {
        return myWebSocketUtil.service.SelectUserr(id1);
    }

    private String uid;
    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
    //用来记录sessionId和该session进行绑定
    //之后就可以通过频道号获取session，然后使用session进行消息的发送。
    private static Map<String, Session> map = new HashMap<String, Session>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) throws IOException {
        this.session = session;
        this.uid = uid;
        map.put(uid + "", session);
        webSocketSet.add(this);//加入set中
        System.out.println("有新连接加入:" + ",当前在线人数为" + webSocketSet.size());
        this.session.getAsyncRemote().sendText("恭喜" + "成功连接上WebSocket=>频道：" + uid + ")-->当前在线人数为：" + webSocketSet.size());
        Session fromSession = map.get(uid);
        String text = "#103:";
        sendUser(fromSession, text);
        /**
         * 扩展一个新的逻辑用于，发送延时消息
         * 原理实现
         * 登录前先查询文件夹里是否有消息信息
         * 如果有的话先把数据读取出来，
         * 然后发送给客服端，
         * 再把数据清除干净，
         *
         * 存放在C:\\Livinginthemoment\\userdata文件夹
         */
        String path = "C:\\Livinginthemoment\\userdata\\" + uid + ".txt";
        File file = new File(path);
        if (file.exists()) {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
                fromSession.getAsyncRemote().sendText(str);
            }
            br.close();
            //删除
            file.delete();
        }
    }

    /**
     * * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        //从set中删除
        System.out.println("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }

    /**
     * * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息-->" + message);
        if (message.equals("112233")) {
            System.out.println("---刷新用户在线信息---");
            Session fromSession = map.get(uid);
            String text = "#104:";
            sendUser(fromSession, text);
        }

    }

    /**
     * * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * * 用于管理员给别人发送通知自定义消息
     */
    public int sendAllMessage(String message) {
        System.out.println("【websocket消息】广播消息:" + message);
        int i = 0;
        //遍历所有的用户
        List<Integer> integers = MyWebSocket.getId();
        for (Integer item : integers) {
            //同步异步说明参考：http://blog.csdn.net/who_is_xiaoming/article/details/53287691
            // this.session.getBasicRemote().sendText(message);
            //处理一下传过来的消息
            // item.session.getAsyncRemote().sendText(message); //异步发送消息.
            //接收者
            Session toSession = map.get(item);
            if (toSession == null) {
                try {
                    fileMessage(message, item);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                //发送给发送者.
                toSession.getAsyncRemote().sendText(message);
            }
            i = 1;
        }
        return i;
    }

    /**
     * 供外部调用方法
     *
     * @param socketMsg
     * @throws InterruptedException
     */
    public void sendOneMessage(SocketMsg socketMsg) {
        // 然后通过socketMsg的type进行判断是单聊还是通知消息，进行相应的处理:
        //聊天系统
        //发送者.
        Session fromSession = map.get(socketMsg.getFromUser());
        //接收者
        Session toSession = map.get(socketMsg.getToUser());
        //发送给接受者.
        if (toSession != null) {
            if (socketMsg.getType() == 1) {
                toSession.getAsyncRemote().sendText(socketMsg.getMsg());
                fromSession.getAsyncRemote().sendText(socketMsg.getMsg());
            } else if (socketMsg.getType() == 2) {
                toSession.getAsyncRemote().sendText(socketMsg.getMsg());
            } else if (socketMsg.getType() == 3) {
                toSession.getAsyncRemote().sendText(socketMsg.getMsg());
            } else if (socketMsg.getType() == 4) {
                toSession.getAsyncRemote().sendText(socketMsg.getMsg());
            } else if (socketMsg.getType() == 5) {
                toSession.getAsyncRemote().sendText(socketMsg.getMsg());
            }
        } else {
            //发送给发送者.
            fromSession.getAsyncRemote().sendText("系统消息：对方不在线或者您输入的频道号不对");
            try {
                fileMessage(socketMsg.getMsg(), Integer.valueOf(socketMsg.getFromUser()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 不在线就写入文件作为缓存
     *
     * @param meg
     * @param name
     * @throws IOException
     */
    public void fileMessage(String meg, int name) throws IOException {
        String path = "C:\\Livinginthemoment\\userdata\\" + name + ".txt";
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(meg + "\r\n");
        bw.close();
    }

    public void sendUser(Session fromSession, String text) {
        /**
         * 2020/02/19
         * 更新在线和离线信息的
         * name:名字
         * id
         * src：头像
         * newsfalg:聊天窗口显示
         * userflag:显示是否在线
         * 编码方式：类似好友消息
         */
        List<User> users = MyWebSocket.getUserwebsocket(Integer.valueOf(this.uid));
        String str;
        for (User user : users) {
            str = text;
            str += user.getName() + "#,#" + user.getId() + "#,#" + user.getSrc() + "#,#" + user.isNewsfalg() + "#,#";
            Session toSession = map.get(user.getId() + "");
            if (toSession == null) {
                str += false + "#";
            } else {
                str += true + "#";
            }

            fromSession.getAsyncRemote().sendText(str);
        }
    }
}
