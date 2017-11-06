package dao;

import entity.Cashier;
import entity.PLU;

public class HQDao_Part1 extends BaseDAO_Sqlite {

    String URL = "jdbc:sqlite:D:/database/";

    /**
     * 方法序号： 1_1 查询所有收银员
     * Id,Number,Name,Barcode,Price,Tax_Index,Stock_Control,Stock_Amount
     *
     * @return json数组
     */
    public String findAllCashiers(String databaseUrl)
            throws Exception {
        String sql = "SELECT Number AS value1, Name AS value2, Code AS value3, Password AS value4 From Cashier_Table ORDER BY Number ASC";
        return this.getForJson(sql, databaseUrl);
    }

    /**
     * 方法序号：1_2 查询收银员最大编号
     */
    public String getCashiersMaxNumber(String databaseUrl) throws Exception {
        String sql = " SELECT MAX(Number) AS MAXNUM FROM Cashier_Table";
        return this.getOneRecard(sql, databaseUrl);
    }

    /**
     * 方法序号：1_3 查询收银员最大code
     */
    public String getCashiersMaxCode(String databaseUrl) throws Exception {
        String sql = " SELECT MAX(Code) AS MAXNUM FROM Cashier_Table";
        return this.getOneRecard(sql, databaseUrl);
    }

    /**
     * 方法序号：1_4 验证收银员code是否存在
     */
    public String verifyCashierCode(String databaseUrl, String barcode) throws Exception {
        String sql = " SELECT COUNT(*) AS COUNTS FROM Cashier_Table WHERE Code=?";
        return Integer.toString(this.getCount(sql, databaseUrl, barcode));
    }

    /**
     * 方法序号：1_5 保存收银员
     */
    public String saveCashier(String databaseUrl, Cashier cashier) throws Exception {
        String sql = "INSERT INTO Cashier_Table (Number,Name,Code,Password,Flag) VALUES (?,?,?,?,?)";
        return Integer.toString(this.saveOrUpdateOrDelete(sql, databaseUrl, cashier.getNumber(), cashier.getName(), cashier.getCode(), cashier.getPassword(), cashier.getFlag()));
    }
    /**
     * 方法序号：1_6 保存修改后的收银员
     */
    public String modifyCashier(String databaseUrl, Cashier cashier) throws Exception {
        String sql = "UPDATE Cashier_Table SET Name=?,Password=? WHERE Code=?";
        return Integer.toString(this.saveOrUpdateOrDelete(sql, databaseUrl, cashier.getName(), cashier.getPassword(), cashier.getCode()));
    }
    /**
     * 方法序号：1_7 删除收银员
     */
    public String deleteOneCashier(String databaseUrl, Cashier cashier) throws Exception {
        String sql = "DELETE FROM Cashier_Table WHERE Number=?";
        return Integer.toString(this.saveOrUpdateOrDelete(sql, databaseUrl, cashier.getNumber()));
    }
//	/**
//	 * 方法序号：4_2 查询商品总记录数
//	 */
//	public int getGoodsCount(String id) throws Exception {
//		String sql = " SELECT count(*) as COUNTS from goods_info";
//		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
//		return this.getCount(sql,url);
//	}
//

//

//
//	/**
//	 * 方法序号：4_5 保存单品
//	 */
//	public boolean saveGoods(PLU plu) throws Exception {
//		String sql = "INSERT INTO goods_info (Number,Name,Barcode,Price,RRP,Tax_Index,Stock_Control,Stock_Amount) VALUES (?,?,?,?,?,?,?,?)";
//		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
//		int result = this.saveOrUpdateOrDelete(sql,url, plu.getName(), plu.getBarcode(),
//				plu.getPrice(), plu.getRRP(), plu.getTax_Index(),
//				plu.getStock_Control(), plu.getStock_Amount(),plu.getNumber());
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
//		String sql = "SELECT Number AS value1 from Tax_Tariff  ORDER BY Number ASC";
//		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"systemDB.db ";
//		return this.getForJson(sql, url);
//	}
//
//	/**
//	 * 方法序号： 4_6_1 查询所有税种税目记录
//	 *
//	 * @return json数组
//	 */
//	public String getAllGoodsTaxTariff() throws Exception {
//		String sql = "SELECT Number AS value1,Invoice_Code AS value2,Invoice_Name AS value3,Tax_Code AS value4,Tax_Name AS value5,Tax_Rate AS value6,Exempt_Flag AS value7,CRC32 AS value8 from Tax_Tariff  ORDER BY Number ASC";
//		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"systemDB.db ";
//		return this.getForJson(sql,url);
//	}
//
//	/**
//	 * 方法序号：4_7 删除一条商品
//	 */
//	public int deleteOneGoods(String goodsNumber) throws Exception {
//		String sql = "DELETE FROM goods_info where Number=?";
//		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
//		return this.saveOrUpdateOrDelete(sql, url, goodsNumber);
//	}
//
//	/**
//	 * 方法序号：4_8 修改一个商品
//	 */
//	public boolean updateOneGoods(PLU plu) throws Exception {
//		String sql = "UPDATE goods_info SET Name=?,Barcode=?,Price=?,Tax_Index=?,Stock_Control=?,Stock_Amount=? where Number=? ";
//		String url = URL+"cbb418cc-8520-459f-ab02-ae3516388eb5/"+"goodsDB.db";
//		int result = this.saveOrUpdateOrDelete(sql,url, plu.getName(),
//				plu.getBarcode(), plu.getPrice(),
//				plu.getTax_Index(), plu.getStock_Control(),
//				plu.getStock_Amount(),plu.getNumber()
//				);
//		if (result > 0)
//			return true;
//		else
//			return false;
//	}

}
