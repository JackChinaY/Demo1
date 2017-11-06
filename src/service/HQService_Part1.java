package service;

import dao.HQDao_Part1;
import entity.Cashier;
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
//	/**
//	 * 方法序号： 4_2 查询商品总记录数
//	 */
//	public int getGoodsCount(String id) {
//		try {
//			 return this.hqDao_part2.getGoodsCount(id);
//		} catch (Exception e) {
//			System.out.println("4_2 查询商品总记录数时出错！");
//			e.printStackTrace();
//		}
//		return -1;
//	}


//	/**
//	 * 方法序号：4_5 保存单品
//	 */
//	public boolean saveGoods(PLU plu) {
//		try {
//			return this.hqDao_part2.saveGoods(plu);// 添加
//		} catch (Exception e) {
//			System.out.println("4_5 保存单品时出错！");
//			e.printStackTrace();
//		}
//		return false;
//	}
//	/**
//	 * 方法序号：4_6 查询所有税种税目索引
//	 * @return json数组字符串
//	 */
//	public String getGoodsTaxTariff(String userId) {
//		try {
//			return this.hqDao_part2.getGoodsTaxTariff(userId);
//		} catch (Exception e) {
//			System.out.println("4_6 查询所有税种税目索引时出错！");
//			e.printStackTrace();
//			return null;//程序运行出错，服务器出错
//		}
//	}
//	/**
//	 * 方法序号：4_6_1 查询所有税种税目记录
//	 * @return json数组字符串
//	 */
//	public String getAllGoodsTaxTariff() {
//		try {
//			return this.hqDao_part2.getAllGoodsTaxTariff();
//		} catch (Exception e) {
//			System.out.println("4_6_1 查询所有税种税记录时出错！");
//			e.printStackTrace();
//			return null;//程序运行出错，服务器出错
//		}
//	}
//	/**
//	 * 方法序号：4_7 删除一条商品
//	 */
//	public int deleteOneGoods(String goodsId) {
//		try {
//			return this.hqDao_part2.deleteOneGoods(goodsId);
//		} catch (Exception e) {
//			System.out.println("4_7 删除一条商品时出错！");
//			e.printStackTrace();
//			return -1;//程序运行出错，服务器出错
//		}
//	}
//	/**
//	 * 方法序号：4_8 修改一个商品
//	 */
//	public boolean updateOneGoods(PLU plu) {
//		try {
//			return this.hqDao_part2.updateOneGoods(plu);// 添加
//		} catch (Exception e) {
//			System.out.println("4_8 修改一个商品时出错！");
//			e.printStackTrace();
//		}
//		return false;
//	}
//


}
