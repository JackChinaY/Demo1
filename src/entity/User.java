package entity;

public class User {
	/**
	 * 用户注册
	 */
	private String id;
	private String username;
	private String password;
	private String email;
	private String tel;
	private String adress;
	private String machineType;
	private String machineId;

	public User() {
		this.id = "";
		this.username = "";
		this.password = "";
		this.email = "";
		this.tel = "";
		this.adress = "";
		this.machineType = "";
		this.machineId = "";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	@Override
	public String toString() {
		return "UserLogin [id=" + id + ", username=" + username + ", password="
				+ password + ", email=" + email + ", tel=" + tel + ", adress="
				+ adress + ", machineType=" + machineType + ", machineId="
				+ machineId + "]";
	}

	

}
