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

    /**
     * 方法序号：2_2 保存发票抬头
     */
    public String saveHeaderOfInvoice() throws IOException, JSONException {
        HeaderOfInvoice header = new HeaderOfInvoice();
        header.setId(UUID.randomUUID().toString());
        header.setTaitou(this.getRequest().getParameter("value1"));
        header.setShuihao(this.getRequest().getParameter("value2"));
        header.setDizhi(this.getRequest().getParameter("value3"));
        header.setDianhua(this.getRequest().getParameter("value4"));
        header.setKaihuhang(this.getRequest().getParameter("value5"));
        header.setYinhangzhanghao(this.getRequest().getParameter("value6"));
        header.setQita(this.getRequest().getParameter("value7"));
        header.setFlag(this.getRequest().getParameter("value8"));
        boolean result = hqService.saveHeaderOfInvoice(header);
        String flag;
        if (result) {
            flag = "1";//成功
        } else {
            flag = "0";//失败
        }
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", flag);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }

    /**
     * 方法序号：2_3 保存收银员
     */
    public String saveCashier() throws IOException, JSONException {
        Cashier cashier = new Cashier();
        cashier.setId(this.getRequest().getParameter("value1"));
        cashier.setNumber(this.getRequest().getParameter("value1"));
        cashier.setName(this.getRequest().getParameter("value2"));
        cashier.setCode(this.getRequest().getParameter("value3"));
        cashier.setFlag(this.getRequest().getParameter("value4"));
        boolean result = hqService.saveCashier(cashier);
        String flag;
        if (result) {
            flag = "1";//成功
        } else {
            flag = "0";//失败
        }
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", flag);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }

    /**
     * 方法序号：2_4保存税率
     */
    public String saveTax() throws IOException, JSONException {
        Tax tax = new Tax();
        tax.setId(this.getRequest().getParameter("value1"));
        tax.setNumber(this.getRequest().getParameter("value1"));
        tax.setName(this.getRequest().getParameter("value2"));
        tax.setVatrate(this.getRequest().getParameter("value3"));
        tax.setUpperlimit(this.getRequest().getParameter("value4"));
        tax.setFlag(this.getRequest().getParameter("value5"));
//		System.out.println(tax);
        boolean result = hqService.saveTax(tax);
        String flag;
        if (result) {
            flag = "1";//成功
        } else {
            flag = "0";//失败
        }
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", flag);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }

    /**
     * 方法序号：2_5保存部类
     */
    public String saveDepartmentClass() throws IOException, JSONException {
        DepartmentClass dc = new DepartmentClass();
        dc.setId(this.getRequest().getParameter("value1"));
        dc.setNumber(this.getRequest().getParameter("value1"));
        dc.setName(this.getRequest().getParameter("value2"));
        dc.setPrice(this.getRequest().getParameter("value3"));
        dc.setVaxIndex(this.getRequest().getParameter("value4"));
        dc.setSubGroup("");
        dc.setPluNo("0");
        dc.setUpperLimit("");
        dc.setFlag(this.getRequest().getParameter("value5"));
//		System.out.println(dc.toString());
        boolean result = hqService.saveDepartmentClass(dc);
        String flag;
        if (result) {
            flag = "1";//成功
        } else {
            flag = "0";//失败
        }
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", flag);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }

    /**
     * 方法序号：2_6保存折扣加成及发票限额
     */
    public String saveDiscount() throws IOException, JSONException {
        Discount dis = new Discount();
        dis.setId("1");
        dis.setIncreaseRate(this.getRequest().getParameter("value1"));
        dis.setDecreaseRate(this.getRequest().getParameter("value2"));
        dis.setReceiptUpperPriceLimit(this.getRequest().getParameter("value3"));
        dis.setReportPassword("");
        dis.setProgramPassword("");
        dis.setServicePassword("");
//		System.out.println(dis.toString());
        boolean result = hqService.saveDiscount(dis);
        String flag;
        if (result) {
            flag = "1";//成功
        } else {
            flag = "0";//失败
        }
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", flag);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }

    /**
     * 方法序号：2_7保存折扣加成及发票限额
     */
    public String saveForeignCurrency() throws IOException, JSONException {
        ForeignCurrency fc = new ForeignCurrency();
        fc.setId(this.getRequest().getParameter("value1"));
        fc.setNumber(this.getRequest().getParameter("value1"));
        fc.setAbbreviation(this.getRequest().getParameter("value2"));
        fc.setSymbol(this.getRequest().getParameter("value3"));
        fc.setSymbolDirection(this.getRequest().getParameter("value4"));
        fc.setExchangeRate(this.getRequest().getParameter("value5"));
        fc.setThousandSeparator(this.getRequest().getParameter("value6"));
        fc.setCentSeparator(this.getRequest().getParameter("value7"));
        fc.setDecimalPlaces(this.getRequest().getParameter("value8"));
        fc.setFlag(this.getRequest().getParameter("value9"));
        fc.setCode("");
//		System.out.println(fc.toString());
        boolean result = hqService.saveForeignCurrency(fc);
        String flag;
        if (result) {
            flag = "1";//成功
        } else {
            flag = "0";//失败
        }
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", flag);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }

    /**
     * 方法序号：3_1_1查询部类日报表
     */
    public String findDayDep() throws IOException, JSONException {
        String result = hqService.findDayDep();
//		System.out.println(result);
        JSONArray allJsonArray = new JSONArray(result);
        if (result.equals(null)) {
            result = "0";//失败
        }
        JSONObject jo = new JSONObject();
        jo.put("allJsonArray", allJsonArray);
//		System.out.println(jo.toString());
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }

    /**
     * 方法序号：3_1_2查询单品日报表
     */
    public String findDayPlu() throws IOException, JSONException {
        String result = hqService.findDayPlu();
        System.out.println(result);
        JSONArray allJsonArray = new JSONArray(result);
        if (result.equals(null)) {
            result = "0";//失败
        }
        JSONObject jo = new JSONObject();
        jo.put("allJsonArray", allJsonArray);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }

    /**
     * 方法序号：3_1_3查询时段日报表
     */
    public String findDayTime() throws IOException, JSONException {
        String result = hqService.findDayTime();
        System.out.println(result);
        JSONArray allJsonArray = new JSONArray(result);
        if (result.equals(null)) {
            result = "0";//失败
        }
        JSONObject jo = new JSONObject();
        jo.put("allJsonArray", allJsonArray);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }

    /**
     * 方法序号：3_1_4查询交易日报表
     */
    public String findDayTrade() throws IOException, JSONException {
        String result = hqService.findDayTrade();
        System.out.println(result);
        JSONArray allJsonArray = new JSONArray(result);
        if (result.equals(null)) {
            result = "0";//失败
        }
        JSONObject jo = new JSONObject();
        jo.put("allJsonArray", allJsonArray);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
        return null;
    }
    /**
     * 方法序号：3_1_5查询单品日报表
     */
//	public String findDayDep() throws IOException, JSONException {
//		String result = hqService.findDayDep();
//		System.out.println(result);
//		JSONArray allJsonArray =new JSONArray(result);
//		if (result.equals(null)) {
//			result = "0";//失败
//		}
//		JSONObject jo = new JSONObject();
//		jo.put("allJsonArray", allJsonArray);
//		this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
//		this.getResponse().getWriter().print(jo);// 向前台发送json数据
//		return null;
//	}

    /**
     * 方法序号：4_1 查询所有商品
     */
    public void findAllGoods() throws IOException, JSONException {
//		String userId=this.currentUserId;//获取用户UserId 本地测试用 TODO 软件正式发布时需将此行注释掉，并将下一行解注
        String userId = this.getSession().getAttribute("userId").toString();//获取用户UserId

        int pageIndex = Integer.parseInt(this.getRequest().getParameter("pageIndex"));//当前页码
        int pageSize = Integer.parseInt(this.getRequest().getParameter("pageSize"));//分页大小
        String result = hqService.findAllGoods(userId, pageIndex, pageSize);
        // System.out.println(result);
        JSONArray allJsonArray = new JSONArray(result);
        if (result.equals(null)) {
            result = "-1";// 服务器出错
        }
        JSONObject jo = new JSONObject();
        jo.put("allJsonArray", allJsonArray);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
    }

    /**
     * 方法序号：4_2 查询商品总记录数
     */
    public void getGoodsCount() throws IOException, JSONException {
        //如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            JSONObject jo = new JSONObject();
            jo.put("jsonObject", -3);//-3为登录超时
            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
            this.getResponse().getWriter().print(jo);// 向前台发送json数据
        }
        //登录未超时
        else {
//			String id=this.currentUserId;//获取用户UserId 本地测试用  TODO 软件正式发布时需将此行注释掉，并将下一行解注
            String userId = this.getSession().getAttribute("userId").toString();//获取用户userId
            int result = hqService.getGoodsCount(userId);// 0表示0条记录，1表示有1条记录
            JSONObject jo = new JSONObject();
            jo.put("jsonObject", result);
            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
            this.getResponse().getWriter().print(jo);// 向前台发送json数据
        }
    }

    /**
     * 方法序号：4_3 查询商品最大编号
     */
    public void getGoodsMaxNumber() throws IOException, JSONException {
        //如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            JSONObject jo = new JSONObject();
            jo.put("jsonObject", -3);//-3为登录超时
            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
            this.getResponse().getWriter().print(jo);// 向前台发送json数据
        }
        //登录未超时
        else {
//			String userId=this.currentUserId;//获取用户UserId 本地测试用  TODO 软件正式发布时需将此行注释掉，并将下一行解注
            String userId = this.getSession().getAttribute("userId").toString();//获取用户userId
            int result = hqService.getGoodsMaxNumber(userId);// 0表示0条记录，1表示有1条记录
            JSONObject jo = new JSONObject();
            jo.put("jsonObject", result);
            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
            this.getResponse().getWriter().print(jo);// 向前台发送json数据
        }
    }

    /**
     * 方法序号：4_4 验证商品条形码是否存在
     */
    public void verifyGoodsbarcode() throws IOException, JSONException {
        String id = this.currentUserId;//获取用户UserId 本地测试用  TODO 软件正式发布时需将此行注释掉，并将下一行解注
//		String userId=this.getSession().getAttribute("id").toString();//获取用户UserId 
        String barcode = this.getRequest().getParameter("value1");
        int result = hqService.verifyGoodsbarcode(id, barcode);// 0表示0条记录，1表示有1条记录
        JSONObject jo = new JSONObject();
        jo.put("jsonObject", result);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
    }

    /**
     * 方法序号：4_5 保存商品 Id,Number,Name,Barcode,Price,Tax_Index,Stock_Control,Stock_Amount
     */
    public void saveGoods() throws IOException, JSONException {
        //如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            JSONObject jo = new JSONObject();
            jo.put("jsonObject", -3);//-3为登录超时
            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
            this.getResponse().getWriter().print(jo);// 向前台发送json数据
        }
        // 登录未超时
        else {
            PLU plu = new PLU();
            plu.setId(UUID.randomUUID().toString());
            plu.setNumber(this.getRequest().getParameter("value1"));
            plu.setName(this.getRequest().getParameter("value2"));
            plu.setBarcode(this.getRequest().getParameter("value3"));
            plu.setPrice(this.getRequest().getParameter("value4"));
            plu.setTax_Index(this.getRequest().getParameter("value5"));
            plu.setStock_Control(this.getRequest().getParameter("value6"));
            plu.setStock_Amount(this.getRequest().getParameter("value7"));
//			plu.setUserId(this.currentUserId);//TODO 软件正式发布时需将此行注释掉，并将下一行解注
            plu.setUserId(this.getSession().getAttribute("userId").toString());// 获取用户userId
            // System.out.println(plu.toString());
            // System.out.println(this.getSession().getAttribute("userId"));
            boolean result = hqService.saveGoods(plu);
            String flag;
            if (result) {
                flag = "1";// 成功
            } else {
                flag = "0";// 失败
            }
            JSONObject jo = new JSONObject();
            jo.put("jsonObject", flag);
            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
            this.getResponse().getWriter().print(jo);// 向前台发送json数据
        }

    }

    /**
     * 方法序号：4_6 查询所有税种税目索引
     */
    public void getGoodsTaxTariff() throws IOException, JSONException {
//			String userId = this.currentUserId;// 获取用户UserId 本地测试用 TODO 软件正式发布时需将此行注释掉，并将下一行解注
        String userId = this.getSession().getAttribute("userId").toString();//获取用户UserId
        String result = hqService.getGoodsTaxTariff(userId);
        // System.out.println(result);
        JSONArray allJsonArray = new JSONArray(result);
        if (result.equals(null)) {
            result = "-1";// 服务器出错
        }
        JSONObject jo = new JSONObject();
        jo.put("allJsonArray", allJsonArray);
        this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
        this.getResponse().getWriter().print(jo);// 向前台发送json数据
    }

    /**
     * 方法序号：4_7 删除一条商品
     */
    public void deleteOneGoods() throws IOException, JSONException {
        // 如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            JSONObject jo = new JSONObject();
            jo.put("jsonObject", -3);// -3为登录超时
            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
            this.getResponse().getWriter().print(jo);// 向前台发送json数据
        }
        // 登录未超时
        else {
//			String userId = this.currentUserId;// 获取用户UserId 本地测试用 TODO // 软件正式发布时需将此行注释掉，并将下一行解注
            String userId = this.getSession().getAttribute("userId").toString();//获取用户UserId
            String goodsId = this.getRequest().getParameter("value1");
            // System.out.println(machineType+machineId);
            int result = hqService.deleteOneGoods(userId, goodsId);// >=1表示成功，0表示失败
            // System.out.println(result);
            if (result > 0) {
                result = 1;
            }
            JSONObject jo = new JSONObject();
            jo.put("jsonObject", result);
            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
            this.getResponse().getWriter().print(jo);// 向前台发送json数据
        }
    }

    /**
     * 方法序号：4_8 修改一个商品 Id,Number,Name,Barcode,Price,Tax_Index,Stock_Control,Stock_Amount
     */
    public void updateOneGoods() throws IOException, JSONException {
        // 如果用户登录超时，则需要重新登录
        if (this.getSession().getAttribute("userId") == null) {
            JSONObject jo = new JSONObject();
            jo.put("jsonObject", -3);// -3为登录超时
            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
            this.getResponse().getWriter().print(jo);// 向前台发送json数据
        }
        // 登录未超时
        else {
            PLU plu = new PLU();
            plu.setId(this.getRequest().getParameter("Id"));
            plu.setNumber(this.getRequest().getParameter("value1"));
            plu.setName(this.getRequest().getParameter("value2"));
            plu.setBarcode(this.getRequest().getParameter("value3"));
            plu.setPrice(this.getRequest().getParameter("value4"));
            plu.setTax_Index(this.getRequest().getParameter("value5"));
            plu.setStock_Control(this.getRequest().getParameter("value6"));
            plu.setStock_Amount(this.getRequest().getParameter("value7"));
//			plu.setUserId(this.currentUserId);// TODO 软件正式发布时需将此行注释掉，并将下一行解注
            plu.setUserId(this.getSession().getAttribute("userId").toString());//获取用户UserId
            System.out.println(plu.toString());
            boolean result = hqService.updateOneGoods(plu);
            String flag;
            if (result) {
                flag = "1";// 成功
            } else {
                flag = "0";// 失败
            }
            JSONObject jo = new JSONObject();
            jo.put("jsonObject", flag);
            this.getResponse().setContentType("text/html;charset=UTF-8");// 设置响应数据类型
            this.getResponse().getWriter().print(jo);// 向前台发送json数据
        }
    }


}
