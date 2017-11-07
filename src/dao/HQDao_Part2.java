package dao;

import entity.*;

import javax.sql.DataSource;

public class HQDao_Part2 extends BaseDAO_Sqlite {

	String URL="jdbc:sqlite:D:/database/";

	/**
	 * 方法序号： 4_1 查询所有商品
	 * Id,Number,Name,Barcode,Price,Tax_Index,Stock_Control,Stock_Amount
	 * 
	 * @return json数组
	 */
	public String findAllGoods(String userId, int pageIndex, int pageSize)
			throws Exception {
		String sql = "SELECT Number AS value1, Name AS value2, Barcode AS value3, Price AS value4,RRP ASvalue5, Tax_Index AS value6, Stock_Control AS value7, Stock_Amount AS value8 from Goods_Info ORDER BY Number ASC";
//		String sql = "SELECT Id AS value1, Number AS value2, Name AS value3, Barcode AS value4, Price AS value5, Tax_Index AS value6, Stock_Control AS value7, Stock_Amount AS value8 "
//				+ "FROM (SELECT * FROM goods_info  ORDER BY Number ASC LIMIT ?)  LIMIT ?,offset?";
		String url=URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
		return this.getForJson(sql,url);
	}

	/**
	 * 方法序号：4_2 查询商品总记录数
	 */
	public int getGoodsCount(String id) throws Exception {
		String sql = " SELECT count(*) as COUNTS from goods_info";
		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
		return this.getCount(sql,url);
	}

	/**
	 * 方法序号：4_3 查询商品最大编号
	 */
	public int getGoodsMaxNumber(String id) throws Exception {
		String sql = " SELECT MAX(Number) as MAXNUM from goods_info";
		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
		return this.getCount(sql, url);
	}

	/**
	 * 方法序号：4_4 验证商品条形码是否存在
	 */
	public int verifyGoodsbarcode(String barcode) throws Exception {
		String sql = " SELECT count(*) as COUNTS from goods_info WHERE Barcode=?";
		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
		return this.getCount(sql,url, barcode);
	}

	/**
	 * 方法序号：4_5 保存单品
	 */
	public boolean saveGoods(PLU plu) throws Exception {
		String sql = "INSERT INTO goods_info (Number,Name,Barcode,Price,RRP,Tax_Index,Stock_Control,Stock_Amount) VALUES (?,?,?,?,?,?,?,?)";
		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
		int result = this.saveOrUpdateOrDelete(sql,url, plu.getNumber(),
				plu.getName(), plu.getBarcode(),
				plu.getPrice(), plu.getRRP(), plu.getTax_Index(),
				plu.getStock_Control(), plu.getStock_Amount());
		if (result > 0)
			return true;
		else
			return false;
	}

	/**
	 * 方法序号： 4_6 查询所有税种税目索引
	 * 
	 * @return json数组
	 */
	public String getGoodsTaxTariff(String userId) throws Exception {
		String sql = "SELECT Number AS value1 from Tax_Tariff  ORDER BY Number ASC";
		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"systemDB.db ";
		return this.getForJson(sql, url);
	}

	/**
	 * 方法序号： 4_6_1 查询所有税种税目记录
	 *
	 * @return json数组
	 */
	public String getAllGoodsTaxTariff() throws Exception {
		String sql = "SELECT Number AS value1,Invoice_Code AS value2,Invoice_Name AS value3,Tax_Code AS value4,Tax_Name AS value5,Tax_Rate AS value6,Exempt_Flag AS value7,CRC32 AS value8 from Tax_Tariff  ORDER BY Number ASC";
		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"systemDB.db ";
		return this.getForJson(sql,url);
	}

	/**
	 * 方法序号：4_7 删除一条商品
	 */
	public int deleteOneGoods(String goodsNumber) throws Exception {
		String sql = "DELETE FROM goods_info where Number=?";
		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
		return this.saveOrUpdateOrDelete(sql, url, goodsNumber);
	}

	/**
	 * 方法序号：4_8 修改一个商品
	 */
	public boolean updateOneGoods(PLU plu) throws Exception {
		String sql = "UPDATE goods_info SET Name=?,Barcode=?,Price=?,Tax_Index=?,RRP=?,Stock_Control=?,Stock_Amount=? where Number=? ";
		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
		int result = this.saveOrUpdateOrDelete(sql,url, plu.getName(),
				plu.getBarcode(), plu.getPrice(),
				plu.getTax_Index(),plu.getRRP(), plu.getStock_Control(),
				plu.getStock_Amount(),plu.getNumber()
				);
		if (result > 0)
			return true;
		else
			return false;
	}

	/**
	 * 方法序号： 4_9 按商品编号查询商品信息
	 * Id,Number,Name,Barcode,Price,Tax_Index,Stock_Control,Stock_Amount
	 *
	 * @return json数组
	 */
	public String findGoodsInfByNumber(String GoodsNumber)
			throws Exception {
		String sql = "SELECT Number AS value1, Name AS value2, Barcode AS value3, Price AS value4,RRP ASvalue5, Tax_Index AS value6, Stock_Control AS value7, Stock_Amount AS value8 from Goods_Info where Number=? ";
		String url=URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
		return this.getForJson(sql,url);
	}

	/**
	 * 方法序号： 4_10 按商品名称查询商品信息
	 * Id,Number,Name,Barcode,Price,Tax_Index,Stock_Control,Stock_Amount
	 *
	 * @return json数组
	 */
	public String findGoodsInfByName(String GoodsName)
			throws Exception {
		String sql = "SELECT Number AS value1, Name AS value2, Barcode AS value3, Price AS value4,RRP ASvalue5, Tax_Index AS value6, Stock_Control AS value7, Stock_Amount AS value8 from Goods_Info where Name=? ";
		String url=URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
		return this.getForJson(sql,url);
	}
	/**
	 * 方法序号： 4_11 按商品条形码查询商品信息
	 * Id,Number,Name,Barcode,Price,Tax_Index,Stock_Control,Stock_Amount
	 *
	 * @return json数组
	 */
	public String findGoodsInfByBarcode(String GoodsBarcode)
			throws Exception {
		String sql = "SELECT Number AS value1, Name AS value2, Barcode AS value3, Price AS value4,RRP ASvalue5, Tax_Index AS value6, Stock_Control AS value7, Stock_Amount AS value8 from Goods_Info where Barcode=? ";
		String url=URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
		return this.getForJson(sql,url);
	}

	/**
	 * 方法序号： 5_1 查询所有部门信息
	 * Id,Dept_No,PLU_No
	 *
	 * @return json数组
	 */
	public String findAllDeptInf() throws Exception {
		String sql = "SELECT id AS value1, Dept_No AS value2, PLU_No AS value3 from Department_Associate ORDER BY id ASC";
		String url=URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
		return this.getForJson(sql,url);
	}

	/**
	 * 方法序号： 5_2 查询商品信息
	 * Id,Number,Name,Barcode,Price,Tax_Index,Stock_Control,Stock_Amount
	 *
	 * @return json数组
	 */
	public String findAllGoodsInf()
			throws Exception {
		String sql = "SELECT Number AS value1, Name AS value2, Barcode AS value3, Price AS value4,RRP ASvalue5, Tax_Index AS value6, Stock_Control AS value7, Stock_Amount AS value8 from Goods_Info ORDER BY Number ASC";
		String url=URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
		return this.getForJson(sql,url);
	}

	/**
	 * 方法序号：5_3 修改一个部门关联信息
	 */
	public boolean updateOneDeptInf(String Dept_No,String PLU_No,Integer Id) throws Exception {
		String sql = "UPDATE Department_Associate SET Dept_No=?,PLU_No=? where id=? ";
		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
		int result = this.saveOrUpdateOrDelete(sql,url,Dept_No,PLU_No,Id);
		if (result > 0)
			return true;
		else
			return false;
	}
}
