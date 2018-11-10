package stockSell;

public class Receipt {
	public Product product = null;
	public int BuyAmount = 0;
	public float price = 0.0f;
	public float total_price = 0.0f;

	private Receipt[] receipts = null;
	private int maxReceipt = 0;

	public int length = 0;

	Receipt() {

	}

	Receipt(int maxReceipt) {
		this.maxReceipt = maxReceipt;
		receipts = new Receipt[this.maxReceipt];
	}

	public boolean addProductToReceipt(Product product, int amount) {
		if (product.amount < amount) {
			return false;
		} else {
			Receipt receipt = new Receipt();
			receipt.product = product;
			receipt.BuyAmount = amount;

			receipts[length] = receipt;
			length++;
			return true;
		}
	}

	public Receipt getReceiptAt(int getReceiptAt) {
		return receipts[getReceiptAt];
	}

	public Receipt[] toArray() {
		return receipts;
	}

	/**
	 * 
	 * @return float ราคารวมของที่ซื้อทั้งหมด
	 */
	public float getTotalPrice() {

		for (int i = 0; i < this.length; i++) {
			this.total_price += getReceiptAt(i).product.price * getReceiptAt(i).BuyAmount;
		}
		return this.total_price;
	}
}
