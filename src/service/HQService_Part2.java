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
	public String findAllGoods(String userId,int pageIndex,int pageSize) {
		try {
			return this.hqDao_part2.findAllGoods(userId ,pageIndex, pageSize);
		} catch (Exception e) {
			System.out.println("4_1 查询所有商品时出错！");
			e.printStackTrace();
			return null;//程序运行出错，服务器出错
		}
	}
	/**
	 * 方法序号： 4_2 查询商品总记录数
	 */
	public int getGoodsCount(String id) {
		try {
			 return this.hqDao_part2.getGoodsCount(id);
		} catch (Exception e) {
			System.out.println("4_2 查询商品总记录数时出错！");
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 方法序号： 4_3 查询商品最大编号
	 */
	public int getGoodsMaxNumber(String id) {
		try {
			 return this.hqDao_part2.getGoodsMaxNumber(id);
		} catch (Exception e) {
			System.out.println("4_3 查询商品最大编号时出错！");
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 方法序号： 4_4 验证商品条形码是否存在
	 */
	public int verifyGoodsbarcode(String barcode) {
		try {
			 return this.hqDao_part2.verifyGoodsbarcode(barcode);
		} catch (Exception e) {
			System.out.println("4_4 验证商品条形码是否存在时出错！");
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 方法序号：4_5 保存单品
	 */
	public boolean saveGoods(PLU plu) {
		try {
			return this.hqDao_part2.saveGoods(plu);// 添加
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
	public String getGoodsTaxTariff(String userId) {
		try {
			return this.hqDao_part2.getGoodsTaxTariff(userId);
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
	public String getAllGoodsTaxTariff() {
		try {
			return this.hqDao_part2.getAllGoodsTaxTariff();
		} catch (Exception e) {
			System.out.println("4_6_1 查询所有税种税记录时出错！");
			e.printStackTrace();
			return null;//程序运行出错，服务器出错
		}
	}
	/**
	 * 方法序号：4_7 删除一条商品
	 */
	public int deleteOneGoods(String goodsId) {
		try {
			return this.hqDao_part2.deleteOneGoods(goodsId);
		} catch (Exception e) {
			System.out.println("4_7 删除一条商品时出错！");
			e.printStackTrace();
			return -1;//程序运行出错，服务器出错
		}
	}
	/**
	 * 方法序号：4_8 修改一个商品
	 */
	public boolean updateOneGoods(PLU plu) {
		try {
			return this.hqDao_part2.updateOneGoods(plu);// 添加
		} catch (Exception e) {
			System.out.println("4_8 修改一个商品时出错！");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 方法序号：4_9 按商品编号查询商品信息
	 */
	public String findGoodsInfByNumber(String goodsNumber) {
		try {
			return this.hqDao_part2.findGoodsInfByNumber(goodsNumber);
		} catch (Exception e) {
			System.out.println("4_9 按商品编号查询商品信息时出错！");
			e.printStackTrace();
			return null;//程序运行出错，服务器出错
		}
	}

	/**
	 * 方法序号：4_10 按商品名称查询商品信息
	 */
	public String findGoodsInfByName(String goodsName) {
		try {
			return this.hqDao_part2.findGoodsInfByName(goodsName);
		} catch (Exception e) {
			System.out.println("4_9 按商品编号查询商品信息时出错！");
			e.printStackTrace();
			return null;//程序运行出错，服务器出错
		}
	}

	/**
	 * 方法序号：4_11 按商品名称查询商品信息
	 */
	public String findGoodsInfByBarcode(String goodsBarcode) {
		try {
			return this.hqDao_part2.findGoodsInfByBarcode(goodsBarcode);
		} catch (Exception e) {
			System.out.println("4_9 按商品编号查询商品信息时出错！");
			e.printStackTrace();
			return null;//程序运行出错，服务器出错
		}
	}

	/**
	 * 方法序号：5_1 查询所有商品
	 * @return json数组字符串
	 */
	public String findAllDeptInf() {
		try {
			return this.hqDao_part2.findAllDeptInf();
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
	public String findAllGoodsInf() {
		try {
			return this.hqDao_part2.findAllGoodsInf();
		} catch (Exception e) {
			System.out.println("5_2 查询所有商品信息时出错！");
			e.printStackTrace();
			return null;//程序运行出错，服务器出错
		}
	}

	/**
	 * 方法序号：5_2 查询所有商品
	 * @return json数组字符串
	 */
	public boolean updateOneDeptInf(String Dept_No,String PLU_No,Integer Id) {
		try {
			return this.hqDao_part2.updateOneDeptInf(Dept_No,PLU_No,Id);
		} catch (Exception e) {
			System.out.println("5_3 更新部门关联信息时出错！");
			e.printStackTrace();
			return false;//程序运行出错，服务器出错
		}
	}

}
