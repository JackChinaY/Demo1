package entity;

public class Cashier {
	/**
	 * 收营员信息
	 * number收银员编号；name收银员名称；code收银员密码；flag标志位
	 */
		private String id;
		private String number;
		private String name;
		private String code;
		private String flag;
		public Cashier() {
			this.id = "";
			this.number = "";
			this.name = "";
			this.code = "";
			this.flag = "";
		}
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}

		@Override
		public String toString() {
			return "Cashier [id=" + id + ", number=" + number + ", name="
					+ name + ", code=" + code + ", flag=" + flag + "]";
		}
		
		
		
		
}
