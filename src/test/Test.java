package test;
import dao.BaseDAO_Sqlite;


public class Test {
	public static void main(String[] args) {
        String sql = " SELECT name FROM internet";
        BaseDAO_Sqlite a = new BaseDAO_Sqlite();

		System.out.println(a.getForJson(sql));
		System.out.println("美国队长");
	}
}
