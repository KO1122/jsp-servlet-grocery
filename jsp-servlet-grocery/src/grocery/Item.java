package grocery;

public class Item {
	
	private int id;
	private String name;
	private int quantity;
	private float price;
	
	public Item(String name, int quantity, float price) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public Item(int id, String name, int quantity, float price) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}
}
