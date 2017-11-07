package test;
import dao.BaseDAO_Sqlite;
import org.json.JSONArray;


public class Test {
	public static void main(String[] args) {
        String sql = " SELECT name FROM internet";
        BaseDAO_Sqlite a = new BaseDAO_Sqlite();
		JSONArray array = new JSONArray();
//		System.out.println(a.getForJson(sql));
		System.out.println(array.toString());
	}
}
