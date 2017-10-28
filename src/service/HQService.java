package service;

import dao.HQDao;
import entity.Cashier;
import entity.DepartmentClass;
import entity.Discount;
import entity.ForeignCurrency;
import entity.HeaderOfInvoice;
import entity.PLU;
import entity.Tax;
import entity.User;

/**
 * return返回值规定：0表示受影响行数为0，数据库出错；1表示受影响行数为1，
 * -1表示程序运行出错，服务器出错，-2表示该条记录在数据库中已存在，null也表示程序运行出错，服务器出错
 */
public class HQService extends BaseService {
	
	private HQDao hqDao = new HQDao(dataSource);
//	private HQDao hqDao_pluDB = new HQDao(dataSource_pluDB);
//	private HQDao hqDao_dmDB_factory = new HQDao(dataSource_dmDB_factory);

	/**
	 * 方法序号：1_1 登录
	 */
	public String login(String username, String password) {
		try {
			return this.hqDao.login(username, password);
		} catch (Exception e) {
			System.out.println("1_1登录时出错！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 方法序号： 1_2验证用户名是否存在
	 */
	public int verifyUsername(String id) {
		try {
			 return this.hqDao.verifyUsername(id);
		} catch (Exception e) {
			System.out.println("1_2验证用户名是否存在时出错！");
			e.printStackTrace();
		}
		return 2;
	}
	
	/**
	 * 方法序号：1_3 注册
	 */
	public boolean register(User user) {
		try {
			return this.hqDao.register(user);// 添加
		} catch (Exception e) {
			System.out.println("1_3注册时出错！");
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 方法序号： 1_4验证该找回密码的用户名是否存在
	 */
	public int verifyUsernameIsExist(User user) {
		try {
			 return this.hqDao.verifyUsernameIsExist(user);
		} catch (Exception e) {
			System.out.println("1_4验证该找回密码的用户名是否存在时出错！");
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 方法序号：1_5重置密码
	 */
	public boolean resetPassord(User user) {
		try {
			return this.hqDao.resetPassord(user);
		} catch (Exception e) {
			System.out.println("1_5重置密码时出错！");
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 方法序号：1_6  验证验证机器是否为正品
	 */
	public int verifyMachine(String machineType, String machineId) {
		try {
			return this.hqDao.verifyMachine(machineType, machineId);
		} catch (Exception e) {
			System.out.println("2_1 验证验证机器是否为正品时出错！");
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 方法序号：2_2 保存发票抬头
	 */
	public boolean saveHeaderOfInvoice(HeaderOfInvoice header) {
		try {
			return this.hqDao.saveHeaderOfInvoice(header);// 添加
		} catch (Exception e) {
			System.out.println("2_2 保存发票抬头时出错！");
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 方法序号：2_3 保存收银员
	 */
	public boolean saveCashier(Cashier cashier) {
		try {
			return this.hqDao.saveCashier(cashier);// 添加
		} catch (Exception e) {
			System.out.println("2_3收银员时出错！");
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 方法序号：2_4保存税率
	 */
	public boolean saveTax(Tax tax) {
		try {
			return this.hqDao.saveTax(tax);// 添加
		} catch (Exception e) {
			System.out.println("2_4保存税率时出错！");
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 方法序号：2_5保存部类
	 */
	public boolean saveDepartmentClass(DepartmentClass dc) {
		try {
			return this.hqDao.saveDepartmentClass(dc);// 添加
		} catch (Exception e) {
			System.out.println("2_5保存部类时出错！");
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 方法序号：2_6保存折扣加成及发票限额
	 */
	public boolean saveDiscount(Discount dis) {
		try {
			return this.hqDao.saveDiscount(dis);// 添加
		} catch (Exception e) {
			System.out.println("2_6保存折扣加成及发票限额时出错！");
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 方法序号：2_7保存折扣加成及发票限额
	 */
	public  boolean saveForeignCurrency(ForeignCurrency fc) {
		try {
			return this.hqDao.saveForeignCurrency(fc);// 添加
		} catch (Exception e) {
			System.out.println("2_7保存折扣加成及发票限额时出错！");
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 方法序号：3_1_1查询部类日报表
	 * @return json数组字符串
	 */
	public String findDayDep() {
		try {
			return this.hqDao.findDayDep();
		} catch (Exception e) {
			System.out.println("3_1_1查询部类日报表时出错！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 方法序号：3_1_2查询单品日报表
	 * @return json数组字符串
	 */
	public String findDayPlu() {
		try {
			return this.hqDao.findDayPlu();
		} catch (Exception e) {
			System.out.println("3_1_2查询单品日报表时出错！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 方法序号：3_1_3查询时段日报表
	 * @return json数组字符串
	 */
	public String findDayTime() {
		try {
			return this.hqDao.findDayTime();
		} catch (Exception e) {
			System.out.println("3_1_1查询时段日报表时出错！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 方法序号：3_1_4查询交易日报表
	 * @return json数组字符串
	 */
	public String findDayTrade() {
		try {
			return this.hqDao.findDayDep();
		} catch (Exception e) {
			System.out.println("3_1_1查询交易日报表时出错！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 方法序号：4_1 查询所有商品
	 * @return json数组字符串
	 */
	public String findAllGoods(String userId,int pageIndex,int pageSize) {
		try {
			return this.hqDao.findAllGoods(userId, pageIndex, pageSize);
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
			 return this.hqDao.getGoodsCount(id);
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
			 return this.hqDao.getGoodsMaxNumber(id);
		} catch (Exception e) {
			System.out.println("4_3 查询商品最大编号时出错！");
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 方法序号： 4_4 验证商品条形码是否存在
	 */
	public int verifyGoodsbarcode(String id,String barcode) {
		try {
			 return this.hqDao.verifyGoodsbarcode(id, barcode);
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
			return this.hqDao.saveGoods(plu);// 添加
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
			return this.hqDao.getGoodsTaxTariff(userId);
		} catch (Exception e) {
			System.out.println("4_6 查询所有税种税目索引时出错！");
			e.printStackTrace();
			return null;//程序运行出错，服务器出错
		}
	}
	/**
	 * 方法序号：4_7 删除一条商品
	 */
	public int deleteOneGoods(String userId, String goodsId) {
		try {
			return this.hqDao.deleteOneGoods(userId, goodsId);
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
			return this.hqDao.updateOneGoods(plu);// 添加
		} catch (Exception e) {
			System.out.println("4_8 修改一个商品时出错！");
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
