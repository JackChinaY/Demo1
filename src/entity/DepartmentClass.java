package entity;

public class DepartmentClass {
	/**
	 * 部类信息
	 * number部类编号；price部类价格；name部类名称；vaxindex税种索引；upperlimit上限；
	 * pluno；subgroup；flag标志位
	 * 
	 */
		private String id;
		private String number;
		private String name;
		private String price;
		private String vaxIndex;
		private String subGroup;
		private String pluNo;
		private String upperLimit;
		private String flag;
		public DepartmentClass() {
			this.id = "";
			this.number = "";
			this.name = "";
			this.price = "";
			this.vaxIndex = "";
			this.subGroup = "";
			this.pluNo = "";
			this.upperLimit = "";
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
		public String getPrice() {
			return price;
		}
		public void setPrice(String price) {
			this.price = price;
		}
		public String getVaxIndex() {
			return vaxIndex;
		}
		public void setVaxIndex(String vaxIndex) {
			this.vaxIndex = vaxIndex;
		}
		public String getSubGroup() {
			return subGroup;
		}
		public void setSubGroup(String subGroup) {
			this.subGroup = subGroup;
		}
		public String getPluNo() {
			return pluNo;
		}
		public void setPluNo(String pluNo) {
			this.pluNo = pluNo;
		}
		public String getUpperLimit() {
			return upperLimit;
		}
		public void setUpperLimit(String upperLimit) {
			this.upperLimit = upperLimit;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String flag) {
			this.flag = flag;
		}
		@Override
		public String toString() {
			return "DepartmentClass [id=" + id + ", number=" + number
					+ ", name=" + name + ", price=" + price + ", vaxIndex="
					+ vaxIndex + ", subGroup=" + subGroup + ", pluNo=" + pluNo
					+ ", upperLimit=" + upperLimit + ", flag=" + flag + "]";
		}
		
		
		
		
}
