package service;

import dao.HQDao_Part1;
import entity.Cashier;
import entity.ForeignCurrency;
import entity.PLU;

/**
 * return返回值规定：0表示受影响行数为0，数据库出错；1表示受影响行数为1，
 * -1表示程序运行出错，服务器出错，-2表示该条记录在数据库中已存在，null也表示程序运行出错，服务器出错
 */
public class HQService_Part1 extends BaseService {

    private HQDao_Part1 hqDao_part1 = new HQDao_Part1();

    /**
     * 方法序号：1_1 查询所有收银员
     *
     * @return json数组字符串
     */
    public String findAllCashiers(String databaseUrl) {
        try {
            return this.hqDao_part1.findAllCashiers(databaseUrl);
        } catch (Exception e) {
            System.out.println("1_1 查询所有收银员时出错！");
            e.printStackTrace();
            return "-1";//程序运行出错，服务器出错
        }
    }

    /**
     * 方法序号： 1_2 查询收银员最大编号
     */
    public String getCashiersMaxNumber(String databaseUrl) {
        try {
            return this.hqDao_part1.getCashiersMaxNumber(databaseUrl);
        } catch (Exception e) {
            System.out.println("1_2 查询收银员最大编号时出错！");
            e.printStackTrace();
        }
        return "-1";
    }

    /**
     * 方法序号： 1_3 查询收银员最大code
     */
    public String getCashiersMaxCode(String databaseUrl) {
        try {
            return this.hqDao_part1.getCashiersMaxCode(databaseUrl);
        } catch (Exception e) {
            System.out.println("1_3 查询收银员最大code时出错！");
            e.printStackTrace();
        }
        return "-1";
    }

    /**
     * 方法序号： 1_4 验证收银员code是否存在
     */
    public String verifyCashierCode(String databaseUrl, String barcode) {
        try {
            return this.hqDao_part1.verifyCashierCode(databaseUrl, barcode);
        } catch (Exception e) {
            System.out.println("1_4 验证收银员code是否存在时出错！");
            e.printStackTrace();
        }
        return "-1";
    }

    /**
     * 方法序号： 1_5 保存收银员
     */
    public String saveCashier(String databaseUrl, Cashier cashier) {
        try {
            return this.hqDao_part1.saveCashier(databaseUrl, cashier);
        } catch (Exception e) {
            System.out.println("1_5 保存收银员时出错！");
            e.printStackTrace();
        }
        return "-1";
    }

    /**
     * 方法序号： 1_6 保存修改后的收银员
     */
    public String modifyCashier(String databaseUrl, Cashier cashier) {
        try {
            return this.hqDao_part1.modifyCashier(databaseUrl, cashier);
        } catch (Exception e) {
            System.out.println("1_6 保存修改后的收银员时出错！");
            e.printStackTrace();
        }
        return "-1";
    }

    /**
     * 方法序号： 1_7 删除收银员
     */
    public String deleteOneCashier(String databaseUrl, Cashier cashier) {
        try {
            return this.hqDao_part1.deleteOneCashier(databaseUrl, cashier);
        } catch (Exception e) {
            System.out.println("1_7 删除收银员时出错！");
            e.printStackTrace();
        }
        return "-1";
    }

    /**
     * 方法序号：2_1 查询所有税率
     */
    public String findAllFiscals(String databaseUrl) {
        try {
            return this.hqDao_part1.findAllFiscals(databaseUrl);
        } catch (Exception e) {
            System.out.println("2_1 查询所有税率时出错！");
            e.printStackTrace();
            return "-1";//程序运行出错，服务器出错
        }
    }
    /**
     * 方法序号：3_1 查询所有外汇
     */
    public String findAllCurrency(String databaseUrl) {
        try {
            return this.hqDao_part1.findAllCurrency(databaseUrl);
        } catch (Exception e) {
            System.out.println("3_1 查询所有外汇时出错！");
            e.printStackTrace();
            return "-1";//程序运行出错，服务器出错
        }
    }
    /**
     * 方法序号： 3_2 保存修改后的外汇
     */
    public String modifyAbbreviation(String databaseUrl, ForeignCurrency currency) {
        try {
            return this.hqDao_part1.modifyAbbreviation(databaseUrl, currency);
        } catch (Exception e) {
            System.out.println("3_2 保存修改后的外汇时出错！");
            e.printStackTrace();
        }
        return "-1";
    }
}
