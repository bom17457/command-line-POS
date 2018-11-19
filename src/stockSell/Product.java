package stockSell;

public class Product {
	/*
		class นี้จะเกี่ยวกับการเก็บข้อมูลที่เกี่ยวกับสินค้า เช่น ชื่อ จำนวน หรือ ราคา ของสินค้านั้น ๆ และยังสามารถทำการตัดสต็อกโดยใช้ 
		method updateInventory
	*/
	public String name = ""; // สร้าง name ไว้เก็บชื่อ
	public float price = 0.0f;// สร้าง price ไว้เก็บราคา
	public int amount = 0;// สร้าง amount ไว้เก็บจำนวนสินค้า
	public int length = 0;// เก็บจำนวนของรายการสินค้า
	public boolean hasOption = false;
	
	private Option option;
	private Product products[];
	private int maxProduct = 0;

	/**
	 * method นี้จะเรียกว่า Constructor จะถูกเรียกใช้เมื่อ object ถูกสร้าง
	 * เมื่อถูกสร้างแล้วจะกำหนดให้ products เก็บค่า array ที่มีสมาชิก maxProduct ตัว
	 * 
	 * @param maxProduct คือ ค่าความกว้างของ array เป็นจำนวนเต็ม(int)
	 */
	public Product(int maxProduct) {
		this.maxProduct = maxProduct;
		products = new Product[this.maxProduct];// สร้าง array ที่มีสมาชิกทั้งหมด maxProduct ตัว
	}

	public Product() {

	}

	/**
	 * method นี้ทำหน้าที่เก็บชื่อสินค้า ราคา และ จำนวน โดยใช้ค่า พารามิเตอร์
	 * จะถูกกำหนดให้กับ name, price, amount แล้วจะนำค่าที่ได้ เก็บไว้ใน อ็อบเจ็ก
	 * ที่ชื่อ product สุดท้ายก็จะนำไปเก็บไว้ ใน array
	 * 
	 * @param name   คือ ชื่อสินค้า
	 * @param price  คือ ราคาของสินค้า
	 * @param amount คือ จำนวนของสินค้า
	 */
	public void createProduct(String name, float price, int amount) {
		Product product = new Product();
		product.name = name;
		product.price = price;
		product.amount = amount;
		products[length] = product;

		length++;
	}
	/**
	*
	*
	*/
	public void createProduct(String name, float price, Option option) {
		Product product = new Product();
		product.name = name;
		product.price = price;
		product.amount = option.getAmount();
		product.option = option;
		product.hasOption = true;
		
		products[length] = product;
		length++;
	}

	public Option getOption() {
		return option;
	}
	/**
	 * @return Product
	 */
	public Product getProductAt(int getProductAt) {
		return products[getProductAt];
	}

	/**
	 * 
	 * @return Products array
	 */
	public Product[] toArray() {
		return products;
	}

	/**
	 * method นี้จะทำหน้าที่ตัดสต็อก
	 * การที่จะตัดสต็อกได้นั้นจำเป็นต้องรู้ว่าการขายมีอะไรบ้าง ซึ่งก็คือ receipts
	 * ที่เป็น พารามิเตอร์ ของ method แล้วก็จำนำมาวนลูปรายการที่สั่งซื้อ
	 * เพื่อตัดสต็อกใน Product
	 * 
	 * @param receipts คือ ข้อมูลรายการที่ขายไป
	 */
	public void updateInventory(Receipt receipts) {
		for (int i = 0; i < receipts.length; i++) {
			receipts.getReceiptAt(i).product.amount -= receipts.getReceiptAt(i).BuyAmount;
		}
	}
}

class Option {
	String name = "";
	float addValue = 0;
	int amount = 0;
	int length = 0;
	int maxOption = 0;
	private Option options[] = null;

	Option(int maxOption) {
		this.maxOption = maxOption;
		options = new Option[maxOption];
	}

	Option() {

	}

	public Option createOption(String name, float addValue, int amount) {
		Option option = new Option();
		option.name = name;
		option.addValue = addValue;
		option.amount = amount;
		this.length++;
		
		return option;
	}

	public Option createOptionArray(Option... option) {

		for (int i = 0; i < option.length && (option.length < this.maxOption); i++) {
			this.options[this.length] = option[i];
			this.length++;
		}
		return this;
	}

	public Option getOptionAt(int index) {
		return this.options[index];
	}

	public Option[] toArray() {
		return this.options;
	}

	public int getAmount() {
		for (int i = 0; i < this.length; i++) {
			this.amount += options[i].amount;
		}
		return this.amount;
	}
}
