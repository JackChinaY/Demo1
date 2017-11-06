package dao;

import control.HQAction_Admin;
import entity.*;

import javax.sql.DataSource;

public class HQDao extends BaseDAO {

	public HQDao(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 方法序号：1 登录
	 */
	public String login(String username, String password) throws Exception {
		String sql = " SELECT Id FROM User_Table WHERE Username=? AND Password=?";
		return this.getOneRecard(sql, username, password);
	}

	/**
	 * 方法序号：1_2验证用户名是否存在
	 */
	public int verifyUsername(String id) throws Exception {
		String sql = "SELECT count(*) AS count FROM User_Table WHERE Username=?";
		return this.getCount(sql, id);
	}

	/**
	 * 方法序号：1_3 注册
	 */
	public boolean register(User user) throws Exception {
		String sql = "INSERT INTO User_Table VALUES (?,?,?,?,?,?,?,?)";
		int result = this.saveEntity(sql, user);
		if (result > 0)
			return true;
		else
			return false;
	}

	/**
	 * 方法序号：1_4验证该找回密码的用户名是否存在
	 */
	public int verifyUsernameIsExist(User user) throws Exception {
		String sql = "SELECT count(*) AS count FROM User_Table WHERE Username=? AND Email=? AND Telephone=? AND MachineType=? AND MachineId=?";
		return this.getCount(sql, user.getUsername(), user.getEmail(),
				user.getTel(), user.getMachineType(), user.getMachineId());
	}

	/**
	 * 方法序号：1_5重置密码
	 */
	public boolean resetPassord(User user) throws Exception {
		String sql = "UPDATE User_table SET Password=? WHERE Username=?";
		int result = this.saveOrUpdateOrDelete(sql, user.getPassword(),
				user.getUsername());
		if (result > 0)
			return true;
		else
			return false;
	}

	/**
	 * 方法序号：1_6 验证验证机器是否为正品
	 */
	public int verifyMachine(String machineType, String machineId)
			throws Exception {
		String sql = " SELECT count(*) AS count FROM Machine_Table WHERE MachineType=? AND MachineId=?";
		return this.getCount(sql, machineType, machineId);
	}

//	/**
//	 * 方法序号：2_2 保存发票抬头
//	 */
//	public boolean saveHeaderOfInvoice(HeaderOfInvoice header) throws Exception {
//		String sql = "INSERT INTO Company_Info_Table2 VALUES (?,?,?,?,?,?,?,?,?)";
//		int result = this.saveEntity(sql, header);
//		if (result > 0)
//			return true;
//		else
//			return false;
//	}
//
//	/**
//	 * 方法序号：2_3 保存收银员
//	 */
//	public boolean saveCashier(Cashier cashier) throws Exception {
//		String sql = "INSERT INTO Cashier_Table VALUES (?,?,?,?,?)";
//		int result = this.saveEntity(sql, cashier);
//		if (result > 0)
//			return true;
//		else
//			return false;
//	}
//
//	/**
//	 * 方法序号：2_4保存税率
//	 */
//	public boolean saveTax(Tax tax) throws Exception {
//		String sql = "INSERT INTO TAX_Table VALUES (?,?,?,?,?,?)";
//		int result = this.saveEntity(sql, tax);
//		if (result > 0)
//			return true;
//		else
//			return false;
//	}
//
//	/**
//	 * 方法序号：2_5保存部类
//	 */
//	public boolean saveDepartmentClass(DepartmentClass dc) throws Exception {
//		String sql = "INSERT INTO Department_Table VALUES (?,?,?,?,?,?,?,?,?)";
//		int result = this.saveEntity(sql, dc);
//		if (result > 0)
//			return true;
//		else
//			return false;
//	}
//
//	/**
//	 * 方法序号：2_6保存折扣加成及发票限额
//	 */
//	public boolean saveDiscount(Discount dis) throws Exception {
//		String sql = "INSERT INTO Others_Table VALUES (?,?,?,?,?,?,?)";
//		int result = this.saveEntity(sql, dis);
//		if (result > 0)
//			return true;
//		else
//			return false;
//	}
//
//	/**
//	 * 方法序号：2_7保存折扣加成及发票限额
//	 */
//	public boolean saveForeignCurrency(ForeignCurrency fc) throws Exception {
//		String sql = "INSERT INTO Currency_Table VALUES (?,?,?,?,?,?,?,?,?,?,?)";
//		int result = this.saveEntity(sql, fc);
//		if (result > 0)
//			return true;
//		else
//			return false;
//	}
//
//	/**
//	 * 方法序号： 3_1_1查询部类日报表
//	 *
//	 * @return json数组
//	 */
//	public String findDayDep() throws Exception {
//		String sql = " SELECT Dept_Index AS value1,Name AS value2,Item_Sum AS value3,Item_Sum AS value4,Date_Time AS value5 FROM Sales_Item where PLU_No = '0' AND Date_Time = '35521' order by Dept_Index";
//		return this.getForJson(sql);
//	}
//
//	/**
//	 * 方法序号： 3_1_2查询单品日报表
//	 *
//	 * @return json数组
//	 */
//	public String findDayPlu() throws Exception {
//		String sql = " SELECT id AS value1,Date_Time AS value2,Receipt_No AS value3,Z_Number AS value4,Name AS value5 FROM Sales_Item";
//		return this.getForJson(sql);
//	}
//
//	/**
//	 * 方法序号： 3_1_3查询时段日报表
//	 *
//	 * @return json数组
//	 */
//	public String findDayTime() throws Exception {
//		String sql = " SELECT id AS value1,Date_Time AS value2,Receipt_No AS value3,Z_Number AS value4,Name AS value5 FROM Sales_Item";
//		return this.getForJson(sql);
//	}
//
//	/**
//	 * 方法序号： 3_1_4查询交易日报表
//	 *
//	 * @return json数组
//	 */
//	public String findDayTrade() throws Exception {
//		String sql = " SELECT id AS value1,Date_Time AS value2,Receipt_No AS value3,Z_Number AS value4,Name AS value5 FROM Sales_Item";
//		return this.getForJson(sql);
//	}
//
//	// /**
//	// * 方法序号： 3_1_5查询部类日报表
//	// * @return json数组
//	// */
//	// public String findDayDep() throws Exception {
//	// String sql =
//	// " SELECT id AS value1,Date_Time AS value2,Receipt_No AS value3,Z_Number AS value4,Name AS value5 FROM Sales_Item";
//	// return this.getForJson(sql);
//	// }
//	// /**
//	// * 方法序号： 3_1_6查询部类日报表
//	// * @return json数组
//	// */
//	// public String findDayDep() throws Exception {
//	// String sql =
//	// " SELECT id AS value1,Date_Time AS value2,Receipt_No AS value3,Z_Number AS value4,Name AS value5 FROM Sales_Item";
//	// return this.getForJson(sql);
//	// }
//	// /**
//	// * 方法序号： 3_1_7查询部类日报表
//	// * @return json数组
//	// */
//	// public String findDayDep() throws Exception {
//	// String sql =
//	// " SELECT id AS value1,Date_Time AS value2,Receipt_No AS value3,Z_Number AS value4,Name AS value5 FROM Sales_Item";
//	// return this.getForJson(sql);
//	// }
//	/**
//	 * 方法序号： 4_1 查询所有商品
//	 * Id,Number,Name,Barcode,Price,Tax_Index,Stock_Control,Stock_Amount
//	 *
//	 * @return json数组
//	 */
//	public String findAllGoods(String userId, int pageIndex, int pageSize)
//			throws Exception {
//		String sql = "SELECT Id AS value1, Number AS value2, Name AS value3, Barcode AS value4, Price AS value5, Tax_Index AS value6, Stock_Control AS value7, Stock_Amount AS value8 "
//				+ "FROM (SELECT * FROM goods_info WHERE UserId=? ORDER BY Number ASC LIMIT ?) AS a LIMIT ?,?";
//		return this.getForJson(sql, userId, pageSize * pageIndex, pageSize
//				* (pageIndex - 1), pageSize);
//	}
//
//	/**
//	 * 方法序号：4_2 查询商品总记录数
//	 */
//	public int getGoodsCount(String id) throws Exception {
//		String sql = " SELECT count(*) as COUNTS from goods_info WHERE UserId=?";
//		return this.getCount(sql, id);
//	}
//
//	/**
//	 * 方法序号：4_3 查询商品最大编号
//	 */
//	public int getGoodsMaxNumber(String id) throws Exception {
//		String sql = " SELECT MAX(Number) as MAXNUM from goods_info WHERE UserId=?";
//		return this.getCount(sql, id);
//	}
//
//	/**
//	 * 方法序号：4_4 验证商品条形码是否存在
//	 */
//	public int verifyGoodsbarcode(String id, String barcode) throws Exception {
//		String sql = " SELECT count(*) as COUNTS from goods_info WHERE UserId=? AND Barcode=?";
//		return this.getCount(sql, id, barcode);
//	}
//
//	/**
//	 * 方法序号：4_5 保存单品
//	 */
//	public boolean saveGoods(PLU plu) throws Exception {
//		String sql = "INSERT INTO goods_info (Id,Number,Name,Barcode,Price,Tax_Index,Stock_Control,Stock_Amount,UserId) VALUES (?,?,?,?,?,?,?,?,?)";
//		int result = this.saveOrUpdateOrDelete(sql, plu.getId(),
//				plu.getNumber(), plu.getName(), plu.getBarcode(),
//				plu.getPrice(), plu.getTax_Index(), plu.getStock_Control(),
//				plu.getStock_Amount(), plu.getUserId());
//		if (result > 0)
//			return true;
//		else
//			return false;
//	}
//
//	/**
//	 * 方法序号： 4_6 查询所有税种税目索引
//	 *
//	 * @return json数组
//	 */
//	public String getGoodsTaxTariff(String userId) throws Exception {
//		String sql = "SELECT Number AS value1 from tax_tariff WHERE UserId=? ORDER BY Number ASC";
//		return this.getForJson(sql, userId);
//	}
//
//	/**
//	 * 方法序号：4_7 删除一条商品
//	 */
//	public int deleteOneGoods(String userId, String goodsId) throws Exception {
//		String sql = "DELETE FROM goods_info where UserId=? and Id=?";
//		return this.saveOrUpdateOrDelete(sql, userId, goodsId);
//	}
//
//	/**
//	 * 方法序号：4_8 修改一个商品
//	 */
//	public boolean updateOneGoods(PLU plu) throws Exception {
//		String sql = "UPDATE goods_info set Name=?,Barcode=?,Price=?,Tax_Index=?,Stock_Control=?,Stock_Amount=? where Number=? AND Id=? and UserId=?";
//		int result = this.saveOrUpdateOrDelete(sql, plu.getName(),
//				plu.getBarcode(), plu.getPrice(), plu.getTax_Index(),
//				plu.getStock_Control(), plu.getStock_Amount(), plu.getNumber(),
//				plu.getId(), plu.getUserId());
//		if (result > 0)
//			return true;
//		else
//			return false;
//	}

}
