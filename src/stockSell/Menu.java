package stockSell;

import java.util.Arrays;
import java.util.Scanner;

class Product {

	public String name = "";
	public float price = 0.0f;
	public int amount = 0;
	public int length = 0;

	public Product products[];
	private int maxProductCount = 0;

	public Product(int maxProductCount) {
		this.maxProductCount = maxProductCount;
		products = new Product[this.maxProductCount];
	}

	public Product() {

	}

	public void createProduct(String name, float price, int amount) {
		Product product = new Product();
		product.name = name;
		product.price = price;
		product.amount = amount;

		products[length] = product;

		length++;
	}

	public Product getProduct(int getProductAt) {
		return products[getProductAt];
	}

	public Product[] getProductToArray() {
		return products;
	}
}

class Transaction {
	Product product = null;
	int BuyAmount = 0;
	float price = 0.0f;
	float total_price = 0.0f;

	Transaction[] transactions = null;
	int MaxList = 0;
	int length = 0;

	Transaction(int MaxList) {
		this.MaxList = MaxList;
		transactions = new Transaction[this.MaxList];
	}

	Transaction() {

	}

	public boolean addProductToTransaction(Product product, int amount) {
		if (product.amount < amount) {
			return false;
		} else {
			Transaction transaction = new Transaction();
			transaction.product = product;
			transaction.BuyAmount = amount;

			transactions[length] = transaction;
			length++;
			return true;
		}
	}

	public Transaction getTransaction(int getTransactionAt) {
		return transactions[getTransactionAt];
	}

	public Transaction[] TransactionToArray() {
		return transactions;
	}

	public float getTotalPrice(Transaction transactions) {

		for (int i = 0; i < transactions.length; i++) {
			this.total_price += getTransaction(i).product.price * getTransaction(i).BuyAmount;
		}
		return this.total_price;
	}
}

public class Menu {

	Product product = new Product(100);
	Transaction transaction = new Transaction(100);
	Scanner next = new Scanner(System.in);

	Menu() {
		product.createProduct("Orange Juice", 20, 20);
		product.createProduct("Apple Juice", 10, 20);
		product.createProduct("Grape Juice", 15, 20);
		product.createProduct("Coconut Water", 15, 20);
		product.createProduct("Tomato Juice", 15, 20);
		product.createProduct("Avocado Juice", 15, 20);

		viewMenu();
	}

	public static void main(String[] args) {
		new Menu();
	}

	public void viewMenu() {
		for (int i = 0; i < product.length; i++) {
			System.out.println(i+1+". "+product.getProduct(i).name + "" + product.getProduct(i).price + "	"
					+ product.getProduct(i).amount);
		}
		System.out.println("-----------------------------------------");
		setTransaction();
	}

	public void setTransaction() {
		int num_product;
		int amount;
		int command;
		boolean runloop = true;
		transaction = new Transaction(100);
		while (runloop) {
			System.out.print("รายการอาหาร : ");
			num_product = next.nextInt()-1;
			System.out.print("จำนวน : ");
			num_product = next.nextInt() - 1;
			amount = next.nextInt();
			if (transaction.addProductToTransaction(product.getProduct(num_product), amount)) {
				System.out.print("คิดเงินกด 1 ทำรายการต่อกด 0 :");
				command = next.nextInt();
				if (command == 1) {
					runloop = false;
					viewTransaction();
					System.out.println("-----------------------------------------");
					setInventory();
					System.out.println("-----------------------------------------");
				}
			} else {
				System.out.println("สินค้าไม่พอจำหน่าย : ");
			}
		}
	}

	public void viewTransaction() {
		for (int i = 0; i < transaction.length; i++) {
			System.out.println(i+1+". "+transaction.getTransaction(i).product.name + " "
					+ transaction.getTransaction(i).BuyAmount + "x" + transaction.getTransaction(i).product.price
					+ "	" + transaction.getTransaction(i).BuyAmount * transaction.getTransaction(i).product.price);
		}
		System.out.println("ราคารวม : " + transaction.getTotalPrice(transaction));
	}

	public void setInventory() {
		for (int i = 0; i < transaction.length; i++) {
			transaction.getTransaction(i).product.amount -= transaction.getTransaction(i).BuyAmount;
		}
		
		transaction = null;
		viewMenu();
	}
}