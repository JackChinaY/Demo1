package service;

import dao.HQDao;
import dao.HQDao_Part2;
import entity.*;

/**
 * return返回值规定：0表示受影响行数为0，数据库出错；1表示受影响行数为1，
 * -1表示程序运行出错，服务器出错，-2表示该条记录在数据库中已存在，null也表示程序运行出错，服务器出错
 */
public class HQService_Part2 extends BaseService {
	
	private HQDao hqDao = new HQDao(dataSource);
	private HQDao_Part2 hqDao_part2 = new HQDao_Part2();
//	private HQDao hqDao_pluDB = new HQDao(dataSource_pluDB);
//	private HQDao hqDao_dmDB_factory = new HQDao(dataSource_dmDB_factory);

	/**
	 * 方法序号：4_1 查询所有商品
	 * @return json数组字符串
	 */
	public String findAllGoods(String databaseUrl, Page page) {
		try {
			return this.hqDao_part2.findAllGoods(databaseUrl ,page);
		} catch (Exception e) {
			System.out.println("4_1 查询所有商品时出错！");
			e.printStackTrace();
			return null;//程序运行出错，服务器出错
		}
	}
	/**
	 * 方法序号： 4_2 查询商品总记录数
	 */
	public String getGoodsCount(String databaseUrl) {
		try {
			 return this.hqDao_part2.getGoodsCount(databaseUrl);
		} catch (Exception e) {
			System.out.println("4_2 查询商品总记录数时出错！");
			e.printStackTrace();
		}
		return "-1";
	}
	/**
	 * 方法序号： 4_3 查询商品最大编号
	 */
	public String getGoodsMaxNumber(String databaseUrl) {
		try {
			 return this.hqDao_part2.getGoodsMaxNumber(databaseUrl);
		} catch (Exception e) {
			System.out.println("4_3 查询商品最大编号时出错！");
			e.printStackTrace();
		}
		return "-1";
	}
	/**
	 * 方法序号： 4_4 验证商品条形码是否存在
	 */
	public String verifyGoodsbarcode(String databaseUrl,String barcode) {
		try {
			 return this.hqDao_part2.verifyGoodsbarcode(databaseUrl,barcode);
		} catch (Exception e) {
			System.out.println("4_4 验证商品条形码是否存在时出错！");
			e.printStackTrace();
		}
		return "-1";
	}
	/**
	 * 方法序号：4_5 保存单品
	 */
	public boolean saveGoods(String databaseUrl,PLU plu) {
		try {
			return this.hqDao_part2.saveGoods(databaseUrl,plu);// 添加
		} catch (Exception e) {
			System.out.println("4_5 保存单品时出错！");
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 方法序号：4_6 查询所有税种税目索引
	 * @return json数组字符串
	 */
	public String getGoodsTaxTariff(String databaseUrl) {
		try {
			return this.hqDao_part2.getGoodsTaxTariff(databaseUrl);
		} catch (Exception e) {
			System.out.println("4_6 查询所有税种税目索引时出错！");
			e.printStackTrace();
			return null;//程序运行出错，服务器出错
		}
	}
	/**
	 * 方法序号：4_6_1 查询所有税种税目记录
	 * @return json数组字符串
	 */
	public String getAllGoodsTaxTariff(String databaseUrl) {
		try {
			return this.hqDao_part2.getAllGoodsTaxTariff(databaseUrl);
		} catch (Exception e) {
			System.out.println("4_6_1 查询所有税种税记录时出错！");
			e.printStackTrace();
			return null;//程序运行出错，服务器出错
		}
	}
	/**
	 * 方法序号：4_7 删除一条商品
	 */
	public String deleteOneGoods(String databaseUrl,String goodsId) {
		try {
			return this.hqDao_part2.deleteOneGoods(databaseUrl,goodsId);
		} catch (Exception e) {
			System.out.println("4_7 删除一条商品时出错！");
			e.printStackTrace();
			return "-1";//程序运行出错，服务器出错
		}
	}
	/**
	 * 方法序号：4_8 修改一个商品
	 */
	public boolean updateOneGoods(String databaseUrl,PLU plu) {
		try {
			return this.hqDao_part2.updateOneGoods(databaseUrl,plu);// 添加
		} catch (Exception e) {
			System.out.println("4_8 修改一个商品时出错！");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 方法序号：4_9 按条件编号查询商品信息
	 */
	public String findAllGoodsByOption(String databaseUrl,String option, String key) {
		try {
//            查询类型：number、name、tpin
			if (option.equals("number")) {
				return this.hqDao_part2.findAllGoodsByOption_ByNumber(databaseUrl, key);
			} else if (option.equals("name")) {
				return this.hqDao_part2.findAllGoodsByOption_ByName(databaseUrl, key);
			} else if (option.equals("barcode")) {
				return this.hqDao_part2.findAllGoodsByOption_ByBarcode(databaseUrl, key);
			}
		} catch (Exception e) {
			System.out.println("4_9 查询所有商品 按条件查询时出错！");
			e.printStackTrace();
			return "-1";//程序运行出错，服务器出错
		}
		return "[]";
	}

	/**
	 * 方法序号：5_1 查询所有商品
	 * @return json数组字符串
	 */
	public String findAllDeptInf(String databaseUrl) {
		try {
			return this.hqDao_part2.findAllDeptInf(databaseUrl);
		} catch (Exception e) {
			System.out.println("5_1 查询所有部门信息时出错！");
			e.printStackTrace();
			return null;//程序运行出错，服务器出错
		}
	}

	/**
	 * 方法序号：5_2 查询所有商品
	 * @return json数组字符串
	 */
	public String findAllGoodsInf(String databaseUrl) {
		try {
			return this.hqDao_part2.findAllGoodsInf(databaseUrl);
		} catch (Exception e) {
			System.out.println("5_2 查询所有商品信息时出错！");
			e.printStackTrace();
			return null;//程序运行出错，服务器出错
		}
	}

	/**
	 * 方法序号：5_3 更新部门关联信息
	 * @return json数组字符串
	 */
	public boolean updateOneDeptInf(String databaseUrl,String Dept_No,String PLU_No,Integer Id) {
		try {
			return this.hqDao_part2.updateOneDeptInf(databaseUrl,Dept_No,PLU_No,Id);
		} catch (Exception e) {
			System.out.println("5_3 更新部门关联信息时出错！");
			e.printStackTrace();
			return false;//程序运行出错，服务器出错
		}
	}

}
