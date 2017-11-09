package control;

import service.HQService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 实现在tomcat启动时执行某段代码
 */
public class InitListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent context) {

    }
    @Override
    public void contextInitialized(ServletContextEvent context) {
        // 上下文初始化执行
        System.out.println("自动加载启动开始.");
        //需要实现的功能
        HQService hqService = new HQService();
        System.out.println("自动加载启动结束.");
    }
}
