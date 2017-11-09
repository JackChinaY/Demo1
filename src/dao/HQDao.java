package dao;

import entity.User;

import javax.sql.DataSource;

public class HQDao extends BaseDAO {

    public HQDao(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 方法序号：1 登录
     */
    public String login(String username, String password) throws Exception {
        String sql = " SELECT Id FROM User_Table WHERE Username=? AND Password=?";
        return this.getOneRecard(sql, username, password);
    }

    /**
     * 方法序号：1_2验证用户名是否存在
     */
    public int verifyUsername(String id) throws Exception {
        String sql = "SELECT count(*) AS count FROM User_Table WHERE Username=?";
        return this.getCount(sql, id);
    }

    /**
     * 方法序号：1_3 注册
     */
    public boolean register(User user) throws Exception {
        String sql = "INSERT INTO user_table VALUES (?,?,?,?,?,?,?,?)";
        int result = this.saveEntity(sql, user);
        if (result > 0)
            return true;
        else
            return false;
    }

    /**
     * 方法序号：1_4验证该找回密码的用户名是否存在
     */
    public int verifyUsernameIsExist(User user) throws Exception {
        String sql = "SELECT count(*) AS count FROM User_Table WHERE Username=? AND Email=? AND Telephone=? AND MachineType=? AND MachineId=?";
        return this.getCount(sql, user.getUsername(), user.getEmail(),
                user.getTel(), user.getMachineType(), user.getMachineId());
    }

    /**
     * 方法序号：1_5重置密码
     */
    public boolean resetPassord(User user) throws Exception {
        String sql = "UPDATE user_table SET Password=? WHERE Username=?";
        int result = this.saveOrUpdateOrDelete(sql, user.getPassword(),
                user.getUsername());
        if (result > 0)
            return true;
        else
            return false;
    }

    /**
     * 方法序号：1_6 验证验证机器是否为正品
     */
    public int verifyMachine(String machineType, String machineId)
            throws Exception {
        String sql = " SELECT count(*) AS count FROM Machine_Table WHERE MachineType=? AND MachineId=?";
        return this.getCount(sql, machineType, machineId);
    }

    /**
     * 方法序号： 5_1 查询用户个人信息
     */
    public String findUserInfo(String id) throws Exception {
        String sql = "SELECT MachineType AS value1,MachineId AS value2,Username AS value3,Email AS value4,Telephone AS value5,Address AS value6 FROM user_table WHERE Id=?";
        return this.getForJson(sql, id);
    }

    /**
     * 方法序号： 5_2 保存修改后用户个人信息
     */
    public String saveUserInfo(User user) throws Exception {
        String sql = "UPDATE user_table SET Email=?,Telephone=?,Address=? WHERE Id=?";
        return Integer.toString(this.saveOrUpdateOrDelete(sql, user.getEmail(), user.getTel(), user.getAdress(), user.getId()));
    }
}
