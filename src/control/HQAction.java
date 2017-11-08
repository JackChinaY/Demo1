package control;

import entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import service.HQService;
import util.SendEmail;
import util.UtilsAll;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 在intellij快捷键：ctrl+/:注释及反注释；alt+L是格式化代码，ctrl+X删除行，sout、psvm是全拼，
 * ctrl+ +/-:某个函数打开或折叠;ctrl+shift+  +/-:某个函数打开或折叠；每次修改完页面ctrl+F9进行重新编译
 * return返回值规定：
 * 0表示受影响行数为0 数据库出错;
 * 1表示受影响行数为1或者是成功的标志;
 * -1表示程序运行出错，服务器出错;
 * -2表示该条记录在数据库中已存在;
 * null也表示程序运行出错，服务器出错
 * -3表示用户登录超时，需要重新登录;
 * 向前台值返回数字：如0,1,-1,-2等
 */
public class HQAction extends BaseAction {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 415712263988003225L;
    private HQService hqService = new HQService();
    private String currentUserId = "cbb418cc-8520-459f-ab02-ae3516388eb5";  //当前用户名Id，软件发布的时候把该字符内容删除掉

    /**
     * 方法序号：1_1 登录
     *
     * @throws NoSuchAlgorithmException
     */
    public String login() throws IOException, JSONException {
        String username = this.getRequest().getParameter("username");
        String password = DigestUtils.md5Hex(this.getRequest().getParameter("password"));//对密码进行加密
//		System.out.println("用户id1："+ this.getSession().getAttribute("userId"));
        String result = hqService.login(username, password);
        String flag;
        if (result != null) {
            flag = "1";//登录成功
            this.getSession().setAttribute("userId", result);//在session中保存用户的id
        } else {
            flag = "0";//登录失败
        }
//		System.out.println(this.getSession().getAttribute("userId"));
//		System.out.println("用户id2："+ this.getSession().getAttribute("userId"));
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", flag);
        jo.put("username", username);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }

    /**
     * 方法序号：1_2验证用户名是否存在
     */
    public void verifyUsername() throws IOException, JSONException {
        String id = this.getRequest().getParameter("username");
        int result = hqService.verifyUsername(id);// 0表示0条记录，1表示有1条记录
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", result);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
    }

    /**
     * 方法序号：1_3 注册
     */
    public String register() throws IOException, JSONException {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(this.getRequest().getParameter("username"));
        user.setPassword(DigestUtils.md5Hex(this.getRequest().getParameter("password")));//对密码进行加密
        user.setEmail(this.getRequest().getParameter("email"));
        user.setTel(this.getRequest().getParameter("tel"));
        user.setAdress(this.getRequest().getParameter("address"));
        user.setMachineType(this.getRequest().getParameter("machineType"));
        user.setMachineId(this.getRequest().getParameter("machineId"));
//		System.out.println(user.toString());
        boolean result = hqService.register(user);
        String flag;
        if (result) {
            flag = "1";//注册成功
        } else {
            flag = "0";//注册失败
        }
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", flag);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }

    /**
     * 方法序号：1_4 找回密码
     */
    public String callbackPassword() throws IOException, JSONException {
        User user = new User();
        user.setUsername(this.getRequest().getParameter("username"));
        user.setEmail(this.getRequest().getParameter("email"));
        user.setTel(this.getRequest().getParameter("tel"));
        user.setMachineType(this.getRequest().getParameter("machineType"));
        user.setMachineId(this.getRequest().getParameter("machineId"));
//		System.out.println(user.toString());
        int result = hqService.verifyUsernameIsExist(user);
        String flag;
        if (result == 0) {//该用户不存在
            flag = "该用户账号不存在或其他信息填写不正确！";
        } else if (result == 1) {//该用户存在 TODO
            String newPassword = UtilsAll.getRandomString(8);//随机生成8位新密码
            user.setPassword(DigestUtils.md5Hex(newPassword));
            try {
                SendEmail.toEmail(user.getEmail(), newPassword);//发送带新密码的邮件给用户
            } catch (Exception e) {
                e.printStackTrace();
            }
            boolean resultOfReset = hqService.resetPassord(user);
            System.out.println(newPassword);
            if (resultOfReset) {
                flag = "您用户密码已重置成功，请到您的注册邮箱：" + user.getEmail() + "查看新密码！";
            } else {
                flag = "密码重置失败！";
            }
        } else {
            flag = "该账号存在多个用户！";
        }
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", flag);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }

    /**
     * 方法序号：1_5 注销
     */
    public String loginOut() throws IOException, JSONException {
        HttpSession session = this.getRequest().getSession(false);//防止创建Session
        if (session == null) {
            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
            this.getResponse().getWriter().print("1");// 向前台发送json数据
            return null;
        }
        session.removeAttribute("userId");
        session.invalidate();//清除session
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print("1");// 向前台发送json数据
        return null;
    }

    /**
     * 方法序号：1_6  验证验证机器是否为正品
     */
    public String verifyMachine() throws IOException, JSONException {
        String machineType = this.getRequest().getParameter("machineType");
        String machineId = this.getRequest().getParameter("machineId");
//		System.out.println(machineType+machineId);
        int result = hqService.verifyMachine(machineType, machineId);//1表示正品。0表示赝品
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", result);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }


//    /**后续功能，暂此不删
//     * 方法序号：3_1_1查询部类日报表
//     */
//    public String findDayDep() throws IOException, JSONException {
//        String result = hqService.findDayDep();
////		System.out.println(result);
//        JSONArray allJsonArray = new JSONArray(result);
//        if (result.equals(null)) {
//            result = "0";//失败
//        }
//        JSONObject jo = new JSONObject();
//        jo.put("allJsonArray", allJsonArray);
////		System.out.println(jo.toString());
//        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//        this.getResponse().getWriter().print(jo);// 向前台发送json数据
//        return null;
//    }
//
//    /**
//     * 方法序号：3_1_2查询单品日报表
//     */
//    public String findDayPlu() throws IOException, JSONException {
//        String result = hqService.findDayPlu();
//        System.out.println(result);
//        JSONArray allJsonArray = new JSONArray(result);
//        if (result.equals(null)) {
//            result = "0";//失败
//        }
//        JSONObject jo = new JSONObject();
//        jo.put("allJsonArray", allJsonArray);
//        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//        this.getResponse().getWriter().print(jo);// 向前台发送json数据
//        return null;
//    }
//
//    /**
//     * 方法序号：3_1_3查询时段日报表
//     */
//    public String findDayTime() throws IOException, JSONException {
//        String result = hqService.findDayTime();
//        System.out.println(result);
//        JSONArray allJsonArray = new JSONArray(result);
//        if (result.equals(null)) {
//            result = "0";//失败
//        }
//        JSONObject jo = new JSONObject();
//        jo.put("allJsonArray", allJsonArray);
//        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//        this.getResponse().getWriter().print(jo);// 向前台发送json数据
//        return null;
//    }
//
//    /**
//     * 方法序号：3_1_4查询交易日报表
//     */
//    public String findDayTrade() throws IOException, JSONException {
//        String result = hqService.findDayTrade();
//        System.out.println(result);
//        JSONArray allJsonArray = new JSONArray(result);
//        if (result.equals(null)) {
//            result = "0";//失败
//        }
//        JSONObject jo = new JSONObject();
//        jo.put("allJsonArray", allJsonArray);
//        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//        this.getResponse().getWriter().print(jo);// 向前台发送json数据
//        return null;
//    }
//    /**
//     * 方法序号：3_1_5查询单品日报表
//     */
////	public String findDayDep() throws IOException, JSONException {
////		String result = hqService.findDayDep();
////		System.out.println(result);
////		JSONArray allJsonArray =new JSONArray(result);
////		if (result.equals(null)) {
////			result = "0";//失败
////		}
////		JSONObject jo = new JSONObject();
////		jo.put("allJsonArray", allJsonArray);
////		this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
////		this.getResponse().getWriter().print(jo);// 向前台发送json数据
////		return null;
////	}



}
