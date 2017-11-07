package control;

import entity.Cashier;
import entity.ForeignCurrency;
import entity.PLU;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import service.HQService_Part1;
import service.HQService_Part2;

import java.io.IOException;

/**
 * 在intellij快捷键：ctrl+/:注释及反注释；alt+L是格式化代码，ctrl+X删除行，sout、psvm是全拼
 * return返回值规定：
 * 0表示受影响行数为0 数据库出错;
 * 1表示受影响行数为1或者是成功的标志;
 * -1表示程序运行出错，服务器出错;
 * -2表示该条记录在数据库中已存在;
 * null也表示程序运行出错，服务器出错
 * -3表示用户登录超时，需要重新登录;
 * 向前台值返回数字：如0,1,-1,-2等
 */
public class HQAction_Part1 extends BaseAction {
    /**
     * 序列化
     */
//    private static final long serialVersionUID = 415712263988003225L;
    private HQService_Part1 hqService_part1 = new HQService_Part1();
    private String currentUserId = "cbb418cc-8520-459f-ab02-ae3516388eb5";  //当前用户名Id，软件发布的时候把该字符内容删除掉
    private String databaseUrl = "jdbc:sqlite:D:/database/";  //sqlite数据库基础路径
    private String programmingDB = "/programmingDB.db";  //连接的是programmingDB.db
    private String systemDB = "/systemDB.db";  //连接的是systemDB.db
    /**
     * 方法序号：1_1 查询所有收银员
     */
    public void findAllCashiers() throws IOException, JSONException {
        //如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            connectionTimeOut();
        }
        //登录未超时
        else {
            String userId = this.getSession().getAttribute("userId").toString();//获取用户UserId
            String result = hqService_part1.findAllCashiers(databaseUrl + userId + programmingDB);
            returnJsonObject(result);//可能的返回值：-1，[],json数组字符串
        }
    }

    /**
     * 方法序号：1_2 查询收银员最大编号
     */
    public void getCashiersMaxNumber() throws IOException, JSONException {
        //如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            connectionTimeOut();//返回值：-3
        }
        //登录未超时
        else {
            String userId = this.getSession().getAttribute("userId").toString();//获取用户userId
            String result = hqService_part1.getCashiersMaxNumber(databaseUrl + userId + programmingDB);// 0表示0条记录，1表示有1条记录
            if (result == null) {
                result = "null";
            }
//            System.out.println("输出1：" + result);
            returnJsonObject(result);//可能的返回值：-1,null
        }
    }

    /**
     * 方法序号：1_3 查询收银员最大code
     */
    public void getCashiersMaxCode() throws IOException, JSONException {
        //如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            connectionTimeOut();//返回值：-3
        }
        //登录未超时
        else {
            String userId = this.getSession().getAttribute("userId").toString();//获取用户userId
            String result = hqService_part1.getCashiersMaxCode(databaseUrl + userId + programmingDB);// 0表示0条记录，1表示有1条记录
            if (result == null) {
                result = "null";
            }
            returnJsonObject(result);//可能的返回值：-1,null
        }
    }

    /**
     * 方法序号：1_4 验证收银员code是否存在
     */
    public void verifyCashierCode() throws IOException, JSONException {
        //如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            connectionTimeOut();//返回值：-3
        }
        //登录未超时
        else {
            String barcode = this.getRequest().getParameter("value1");
            String userId = this.getSession().getAttribute("userId").toString();//获取用户userId
            String result = hqService_part1.verifyCashierCode(databaseUrl + userId + programmingDB, barcode);// 0表示0条记录，1表示有1条记录
            returnJsonObject(result);//可能的返回值：-1,0,1
        }
    }

    /**
     * 方法序号：1_5 保存收银员
     */
    public void saveCashier() throws IOException, JSONException {
        //如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            connectionTimeOut();//返回值：-3
        }
        //登录未超时
        else {
            Cashier cashier = new Cashier();
            cashier.setNumber(this.getRequest().getParameter("value1"));
            cashier.setCode(this.getRequest().getParameter("value2"));
            cashier.setName(this.getRequest().getParameter("value3"));
            cashier.setPassword(this.getRequest().getParameter("value4"));
            String userId = this.getSession().getAttribute("userId").toString();//获取用户userId
            String result = hqService_part1.saveCashier(databaseUrl + userId + programmingDB, cashier);// 0表示0条记录，1表示有1条记录
            returnJsonObject(result);//可能的返回值：-1,0,1
        }
    }

    /**
     * 方法序号：1_6 保存修改后的收银员
     */
    public void modifyCashier() throws IOException, JSONException {
        //如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            connectionTimeOut();//返回值：-3
        }
        //登录未超时
        else {
            Cashier cashier = new Cashier();
            cashier.setNumber(this.getRequest().getParameter("value1"));
            cashier.setCode(this.getRequest().getParameter("value2"));
            cashier.setName(this.getRequest().getParameter("value3"));
            cashier.setPassword(this.getRequest().getParameter("value4"));
            String userId = this.getSession().getAttribute("userId").toString();//获取用户userId
            String result = hqService_part1.modifyCashier(databaseUrl + userId + programmingDB, cashier);// 0表示0条记录，1表示有1条记录
            returnJsonObject(result);//可能的返回值：-1,0,1
        }
    }

    /**
     * 方法序号：1_7 删除收银员
     */
    public void deleteOneCashier() throws IOException, JSONException {
        //如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            connectionTimeOut();//返回值：-3
        }
        //登录未超时
        else {
            Cashier cashier = new Cashier();
            cashier.setNumber(this.getRequest().getParameter("value1"));
            String userId = this.getSession().getAttribute("userId").toString();//获取用户userId
            String result = hqService_part1.deleteOneCashier(databaseUrl + userId + programmingDB, cashier);// 0表示0条记录，1表示有1条记录
            returnJsonObject(result);//可能的返回值：-1,0,1
        }
    }

    /**
     * 如果用户登录超时，则需要重新登录
     */
    public void connectionTimeOut() throws IOException, JSONException {
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", -3);//-3为登录超时
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
    }

    /**
     * 统一向前台返回数据，返回的是jsonObject
     */
    public void returnJsonObject(String result) throws IOException, JSONException {
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", result);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
    }

    /**
     * 方法序号：2_1 查询所有税率
     */
    public void findAllFiscals() throws IOException, JSONException {
        //如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            connectionTimeOut();
        }
        //登录未超时
        else {
            String userId = this.getSession().getAttribute("userId").toString();//获取用户UserId
            String result = hqService_part1.findAllFiscals(databaseUrl + userId + systemDB);
            returnJsonObject(result);//可能的返回值：-1，[],json数组字符串
        }
    }
    /**
     * 方法序号：3_1 查询所有外汇
     */
    public void findAllCurrency() throws IOException, JSONException {
        //如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            connectionTimeOut();
        }
        //登录未超时
        else {
            String userId = this.getSession().getAttribute("userId").toString();//获取用户UserId
            String result = hqService_part1.findAllCurrency(databaseUrl + userId + programmingDB);
            returnJsonObject(result);//可能的返回值：-1，[],json数组字符串
        }
    }
    /**
     * 方法序号：3_2 保存修改后的外汇
     */
    public void modifyAbbreviation() throws IOException, JSONException {
        //如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            connectionTimeOut();//返回值：-3
        }
        //登录未超时
        else {
            ForeignCurrency currency = new ForeignCurrency();
            currency.setNumber(this.getRequest().getParameter("value1"));
            currency.setAbbreviation(this.getRequest().getParameter("value2"));
            currency.setExchangeRate(this.getRequest().getParameter("value3"));
            String userId = this.getSession().getAttribute("userId").toString();//获取用户userId
            String result = hqService_part1.modifyAbbreviation(databaseUrl + userId + programmingDB, currency);// 0表示0条记录，1表示有1条记录
            returnJsonObject(result);//可能的返回值：-1,0,1
        }
    }
}
