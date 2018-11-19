package stockSell;

import java.util.Arrays;
import java.util.Scanner;

public class Transaction {

	Product product = new Product(100);
	Product shirt = new Product(100);
	Receipt receipt = new Receipt(100);
	Scanner getKey = new Scanner(System.in);

	/**
	 * 
	 * method นี้จะเรียกว่า Constructor จะถูกเรียกใช้เมื่อ object ถูกสร้าง
	 * เราเลยนำมาใช้ในการ กำหนดค่าเริ่มต้น ตัวอย่างนี้จะเป็นการ สร้างเมนู
	 * และกำหนดค่าต่าง ๆ ให้กับเมนู
	 * 
	 */
	Transaction() {
		product.createProduct("Orange Juice", 20, 20); // ใส่ ชื่อ ราคา และ จำนวน ให้กับพารามิเตอร์
		product.createProduct("Apple Juice", 10, 20); // ใส่ ชื่อ ราคา และ จำนวน ให้กับพารามิเตอร์
		product.createProduct("Grape Juice", 15, 20); // ใส่ ชื่อ ราคา และ จำนวน ให้กับพารามิเตอร์
		product.createProduct("Coconut Water", 15, 20); // ใส่ ชื่อ ราคา และ จำนวน ให้กับพารามิเตอร์
		product.createProduct("Tomato Juice", 15, 20); // ใส่ ชื่อ ราคา และ จำนวน ให้กับพารามิเตอร์
		product.createProduct("Avocado Juice", 15, 20); // ใส่ ชื่อ ราคา และ จำนวน ให้กับพารามิเตอร์

		
		
		/*product.createProduct("T-shirt", 230, new Option(100).createOptionArray(new Option().createOption("Orange", 0, 5),
				new Option().createOption("Black", 5, 5), new Option().createOption("gray", 10, 5))); //เป็นการสร้าง product แบบมี option ให้เลือก*/
		
		
		
		viewMenu();// เรียกให้ method viewMenu() เริ่มทำงาน
	}

	public static void main(String[] args) {
		new Transaction();
	}

	public void viewMenu() {
		System.out.println("id\tName      \t\tprice\tAmount");
		for (int i = 0; i < product.length; i++) {
			System.out.println(i + 1 + ".\t" + product.getProductAt(i).name + "\t\t" + product.getProductAt(i).price
					+ "	" + product.getProductAt(i).amount);
			
			/*for(int x=0;product.getProductAt(i).hasOption&&(x<product.getProductAt(i).getOption().length);x++) {
				System.out.println("\t-"+product.getProductAt(i).getOption().getOptionAt(x).name+
											"\t"+product.getProductAt(i).getOption().getOptionAt(x).addValue);
			}*/
		}
		System.out.println("-----------------------------------------------");
		setReceipt();
	}

	public void setReceipt() {
		int num_product;
		int amount;
		int command;
		boolean runloop = true;
		while (runloop) {
			System.out.print("Select juice : ");

			num_product = getKey.nextInt() - 1;

			System.out.print("How many dso you want? : ");
			amount = getKey.nextInt();

			if (receipt.addProductToReceipt(product.getProductAt(num_product), amount)) {

				System.out.print("1 To Finish Receipt 0 To Continue.");

				command = getKey.nextInt();

				if (command == 1) {
					runloop = false;

					System.out.println("\n\n\n------------------Receipt------------------");
					viewReceipt();

					System.out.println("-----------------------------------------------");
					setInventory();
					System.out.println("-----------------------------------------------");
				}
			} else {
				System.out.println("cargo not enough.");
			}
		}
	}

	public void viewReceipt() {
		System.out.println("  \tProduct\t\tAmount*price\tTotal");
		for (int i = 0; i < receipt.length; i++) {

			System.out.println(i + 1 + ".\t" + receipt.getReceiptAt(i).product.name + "\t"
					+ receipt.getReceiptAt(i).BuyAmount + "x" + receipt.getReceiptAt(i).product.price + "\t\t"
					+ receipt.getReceiptAt(i).BuyAmount * receipt.getReceiptAt(i).product.price);
		}
		System.out.println("Total : " + receipt.getTotalPrice());
	}

	public void setInventory() {
		product.updateInventory(receipt);
		receipt = new Receipt(100);
		viewMenu();
	}
}
