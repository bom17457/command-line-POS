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

	public Product getProductAt(int getProductAt) {
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

	public Transaction getTransactionAt(int getTransactionAt) {
		return transactions[getTransactionAt];
	}

	public Transaction[] TransactionToArray() {
		return transactions;
	}

	public float getTotalPrice() {

		for (int i = 0; i < this.length; i++) {
			this.total_price += getTransactionAt(i).product.price * getTransactionAt(i).BuyAmount;
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
		System.out.println("id\tName\t\tprice\tAmount");
		for (int i = 0; i < product.length; i++) {
			System.out.println(i + 1 + ".\t" + product.getProductAt(i).name + "\t" + product.getProductAt(i).price + "	"
					+ product.getProductAt(i).amount);
		}
		System.out.println("-----------------------------------------------");
		setTransaction();
	}

	public void setTransaction() {
		int num_product;
		int amount;
		int command;
		boolean runloop = true;
		while (runloop) {
			System.out.print("Select juice : ");

			num_product = next.nextInt() - 1;

			System.out.print("How many do you want? : ");
			amount = next.nextInt();

			if (transaction.addProductToTransaction(product.getProductAt(num_product), amount)) {

				System.out.print("1 To Finish Transaction 0 To Continue.");

				command = next.nextInt();

				if (command == 1) {
					runloop = false;

					System.out.println("\n\n\n------------------Transaction------------------");
					viewTransaction();

					System.out.println("-----------------------------------------------");
					setInventory();

					System.out.println("-----------------------------------------------");
				}
			} else {
				System.out.println("cargo not enough.");
			}
		}
	}

	public void viewTransaction() {

		System.out.println("  \tProduct\t\tAmount*price\tTotal");

		for (int i = 0; i < transaction.length; i++) {

			System.out.println(i + 1 + ".\t" + transaction.getTransactionAt(i).product.name + "\t"
					+ transaction.getTransactionAt(i).BuyAmount + "x" + transaction.getTransactionAt(i).product.price
					+ "\t\t"
					+ transaction.getTransactionAt(i).BuyAmount * transaction.getTransactionAt(i).product.price);
		}

		System.out.println("Total : " + transaction.getTotalPrice());
	}

	public void setInventory() {
		for (int i = 0; i < transaction.length; i++) {
			transaction.getTransactionAt(i).product.amount -= transaction.getTransactionAt(i).BuyAmount;
		}

		transaction = new Transaction(100);
		viewMenu();
	}
}