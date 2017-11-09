package dao;

import entity.*;

public class HQDao_Part1 extends BaseDAO_Sqlite {

    String URL = "jdbc:sqlite:D:/database/";

    /**
     * 方法序号： 1_1 查询所有收银员
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

    /**
     * 方法序号： 2_1 查询所有税率
     */
    public String findAllFiscals(String databaseUrl) throws Exception {
        String sql = "SELECT Number AS value1,Invoice_Code AS value2,Invoice_Name AS value3,Tax_Code AS value4,Tax_Name AS value5,Tax_Rate AS value6," +
                "Exempt_Flag AS value7,CRC32 AS value8 FROM Tax_Tariff ORDER BY Number ASC";
        return this.getForJson(sql, databaseUrl);
    }

    /**
     * 方法序号： 3_1 查询所有外汇
     */
    public String findAllCurrency(String databaseUrl) throws Exception {
        String sql = "SELECT Number AS value1,Abbreviation AS value2,Exchange_Rate AS value3 FROM Currency_Table ORDER BY Number ASC";
        return this.getForJson(sql, databaseUrl);
    }

    /**
     * 方法序号：3_2 保存修改后的外汇
     */
    public String modifyAbbreviation(String databaseUrl, ForeignCurrency currency) throws Exception {
        String sql = "UPDATE Currency_Table SET Abbreviation=?,Exchange_Rate=? WHERE Number=?";
        return Integer.toString(this.saveOrUpdateOrDelete(sql, databaseUrl, currency.getAbbreviation(), currency.getExchangeRate(), currency.getNumber()));
    }

    /**
     * 方法序号： 4_1 查询客户总记录数
     */
    public String getBuyersCount(String databaseUrl) throws Exception {
        String sql = "SELECT COUNT(*) AS COUNTS FROM Buyer_Info";
        return Integer.toString(this.getCount(sql, databaseUrl));
    }

    /**
     * 方法序号： 4_2 查询所有客户
     */
    public String findAllBuyers(String databaseUrl, Page page) throws Exception {
        String sql = "SELECT Number AS value1,Name AS value2,BPN AS value3,VAT AS value4,Address AS value5,Tel AS value6 FROM " +
                "(SELECT * FROM Buyer_Info ORDER BY Number ASC LIMIT ?) LIMIT ? offset ?;";
        return this.getForJson(sql, databaseUrl, page.getPageSize() * page.getPageIndex(), page.getPageSize(), page.getPageSize() * (page.getPageIndex() - 1));
    }

    /**
     * 方法序号：4_3 查询客户最大编号
     */
    public String getBuyersMaxNumber(String databaseUrl) throws Exception {
        String sql = " SELECT MAX(Number) AS MAXNUM FROM Buyer_Info";
        return this.getOneRecard(sql, databaseUrl);
    }

    /**
     * 方法序号：4_4 验证客户TPIN是否存在
     */
    public String verifyBuyerTPIN(String databaseUrl, String tpin) throws Exception {
        String sql = " SELECT COUNT(*) AS COUNTS FROM Buyer_Info WHERE BPN=?";
        return Integer.toString(this.getCount(sql, databaseUrl, tpin));
    }

    /**
     * 方法序号：4_5 保存客户
     */
    public String saveBuyer(String databaseUrl, Buyer buyer) throws Exception {
        String sql = "INSERT INTO Buyer_Info (Number,Name,BPN,VAT,Address,Tel,Bank_Account_No,Remark,Reserved) VALUES (?,?,?,?,?,?,?,?,?)";
        return Integer.toString(this.saveOrUpdateOrDelete(sql, databaseUrl, buyer.getNumber(), buyer.getName(), buyer.getTpin(), buyer.getVat(), buyer.getAddress(), buyer.getTel(), buyer.getBankAccount(), buyer.getRemark(), buyer.getReserved()));
    }

    /**
     * 方法序号：4_6 保存修改后的客户
     */
    public String modifyBuyer(String databaseUrl, Buyer buyer) throws Exception {
        String sql = "UPDATE Buyer_Info SET Name=?,BPN=?,VAT=?,Address=?,Tel=? WHERE Number=?";
        return Integer.toString(this.saveOrUpdateOrDelete(sql, databaseUrl, buyer.getName(), buyer.getTpin(), buyer.getVat(), buyer.getAddress(), buyer.getTel(), buyer.getNumber()));
    }

    /**
     * 方法序号：4_7 删除客户
     */
    public String deleteOneBuyer(String databaseUrl, Buyer buyer) throws Exception {
        String sql = "DELETE FROM Buyer_Info WHERE Number=?";
        return Integer.toString(this.saveOrUpdateOrDelete(sql, databaseUrl, buyer.getNumber()));
    }

    /**
     * 方法序号： 4_8 查询所有客户 按条件查询 按number
     */
    public String findAllBuyersByOption_ByNumber(String databaseUrl, String key) throws Exception {
        String sql = "SELECT Number AS value1,Name AS value2,BPN AS value3,VAT AS value4,Address AS value5,Tel AS value6 FROM Buyer_Info WHERE Number LIKE ?";
        return this.getForJson(sql, databaseUrl, "%" + key + "%");
    }

    /**
     * 方法序号： 4_8 查询所有客户 按条件查询 按Name
     */
    public String findAllBuyersByOption_ByName(String databaseUrl, String key) throws Exception {
        String sql = "SELECT Number AS value1,Name AS value2,BPN AS value3,VAT AS value4,Address AS value5,Tel AS value6 FROM Buyer_Info WHERE Name LIKE ?";
        return this.getForJson(sql, databaseUrl, "%" + key + "%");
    }

    /**
     * 方法序号： 4_8 查询所有客户 按条件查询 按TPIN
     */
    public String findAllBuyersByOption_ByTPIN(String databaseUrl, String key) throws Exception {
        String sql = "SELECT Number AS value1,Name AS value2,BPN AS value3,VAT AS value4,Address AS value5,Tel AS value6 FROM Buyer_Info WHERE BPN LIKE ?";
        return this.getForJson(sql, databaseUrl, "%" + key + "%");
    }


}
