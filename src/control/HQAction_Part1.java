package control;

import entity.Cashier;
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
//    /**
//     * 统一向前台返回数据，返回的是JsonArray
//     */
//    public void returnJsonArray(String result) throws IOException, JSONException {
//        JSONObject jo = new JSONObject();
//        jo.put("allJsonArray", result);
//        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//        this.getResponse().getWriter().print(jo);// 向前台发送json数据
//    }
//    /**
//     * 方法序号：4_2 查询商品总记录数
//     */
//    public void getGoodsCount() throws IOException, JSONException {
//        //如果用户登录超时，则需要重新登录
//        if (this.getSession().getAttribute("userId") == null) {
//            JSONObject jo = new JSONObject();
//            jo.put("jsonObject", -3);//-3为登录超时
//            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//            this.getResponse().getWriter().print(jo);// 向前台发送json数据
//        }
//        //登录未超时
//        else {
//            String userId = this.getSession().getAttribute("userId").toString();//获取用户userId
//            int result = hqService_part2.getGoodsCount(userId);// 0表示0条记录，1表示有1条记录
//            JSONObject jo = new JSONObject();
//            jo.put("jsonObject", result);
//            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//            this.getResponse().getWriter().print(jo);// 向前台发送json数据
//        }
//    }
//

//

//
//    /**
//     * 方法序号：4_5 保存商品 Id,Number,Name,Barcode,Price,Tax_Index,Stock_Control,Stock_Amount
//     */
//    public void saveGoods() throws IOException, JSONException {
//        //如果用户登录超时，则需要重新登录
//        if (this.getSession().getAttribute("userId") == null) {
//            JSONObject jo = new JSONObject();
//            jo.put("jsonObject", -3);//-3为登录超时
//            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//            this.getResponse().getWriter().print(jo);// 向前台发送json数据
//        }
//        // 登录未超时
//        else {
//            PLU plu = new PLU();
//            plu.setId(null);
//            plu.setNumber(this.getRequest().getParameter("value1"));
//            plu.setName(this.getRequest().getParameter("value2"));
//            plu.setBarcode(this.getRequest().getParameter("value3"));
//            plu.setPrice(this.getRequest().getParameter("value4"));
//            plu.setRRP(this.getRequest().getParameter("value5"));
//            plu.setTax_Index(this.getRequest().getParameter("value6"));
//            plu.setStock_Control(this.getRequest().getParameter("value7"));
//            plu.setStock_Amount(this.getRequest().getParameter("value8"));
//            boolean result = hqService_part2.saveGoods(plu);
//            String flag;
//            if (result) {
//                flag = "1";// 成功
//            } else {
//                flag = "0";// 失败
//            }
//            JSONObject jo = new JSONObject();
//            jo.put("jsonObject", flag);
//            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//            this.getResponse().getWriter().print(jo);// 向前台发送json数据
//        }
//
//    }
//
//    /**
//     * 方法序号：4_6 查询所有税种税目索引
//     */
//    public void getGoodsTaxTariff() throws IOException, JSONException {
//        String userId = this.getSession().getAttribute("userId").toString();//获取用户UserId
//        String result = hqService_part2.getGoodsTaxTariff(userId);
//        JSONArray allJsonArray = new JSONArray(result);
//        if (result.equals(null)) {
//            result = "-1";// 服务器出错
//        }
//        JSONObject jo = new JSONObject();
//        jo.put("allJsonArray", allJsonArray);
//        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//        this.getResponse().getWriter().print(jo);// 向前台发送json数据
//    }
//
//    /**
//     * 方法序号：4_6_1 查询所有税种税目记录
//     */
//    public void getAllGoodsTaxTariff() throws IOException, JSONException {
//        String result = hqService_part2.getAllGoodsTaxTariff();
//        JSONArray allJsonArray = new JSONArray(result);
//        if (result.equals(null)) {
//            result = "-1";// 服务器出错
//        }
//        JSONObject jo = new JSONObject();
//        jo.put("allJsonArray", allJsonArray);
//        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//        this.getResponse().getWriter().print(jo);// 向前台发送json数据
//    }
//
//    /**
//     * 方法序号：4_7 删除一条商品
//     */
//    public void deleteOneGoods() throws IOException, JSONException {
//        // 如果用户登录超时，则需要重新登录
//        if (this.getSession().getAttribute("userId") == null) {
//            JSONObject jo = new JSONObject();
//            jo.put("jsonObject", -3);// -3为登录超时
//            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//            this.getResponse().getWriter().print(jo);// 向前台发送json数据
//        }
//        // 登录未超时
//        else {
////			String userId = this.currentUserId;// 获取用户UserId 本地测试用 TODO // 软件正式发布时需将此行注释掉，并将下一行解注
//            //String userId = this.getSession().getAttribute("userId").toString();//获取用户UserId
//            String goodsNumber = this.getRequest().getParameter("value1");
//            int result = hqService_part2.deleteOneGoods(goodsNumber);// >=1表示成功，0表示失败
//            if (result > 0) {
//                result = 1;
//            }
//            JSONObject jo = new JSONObject();
//            jo.put("jsonObject", result);
//            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//            this.getResponse().getWriter().print(jo);// 向前台发送json数据
//        }
//    }
//
//    /**
//     * 方法序号：4_8 修改一个商品 Id,Number,Name,Barcode,Price,Tax_Index,Stock_Control,Stock_Amount
//     */
//   public void updateOneGoods() throws IOException, JSONException {
//        // 如果用户登录超时，则需要重新登录
//        if (this.getSession().getAttribute("userId") == null) {
//           JSONObject jo = new JSONObject();
//           jo.put("jsonObject", -3);// -3为登录超时
//           this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//           this.getResponse().getWriter().print(jo);// 向前台发送json数据
//       }
//        // 登录未超时
//        else {
//           PLU plu = new PLU();
//            plu.setId(null);
//           //plu.setId(Integer.parseInt(this.getRequest().getParameter("Id")));
//           plu.setNumber(this.getRequest().getParameter("value1"));
//           plu.setName(this.getRequest().getParameter("value2"));
//           plu.setBarcode(this.getRequest().getParameter("value3"));
//           plu.setPrice(this.getRequest().getParameter("value4"));
//           plu.setTax_Index(this.getRequest().getParameter("value5"));
//           plu.setStock_Control(this.getRequest().getParameter("value6"));
//           plu.setStock_Amount(this.getRequest().getParameter("value7"));
//           boolean result = hqService_part2.updateOneGoods(plu);
//            String flag;
//           if (result) {
//                flag = "1";// 成功
//           } else {
//                flag = "0";// 失败
//           }
//            JSONObject jo = new JSONObject();
//            jo.put("jsonObject", flag);
//            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//            this.getResponse().getWriter().print(jo);// 向前台发送json数据
//        }
//    }


}
