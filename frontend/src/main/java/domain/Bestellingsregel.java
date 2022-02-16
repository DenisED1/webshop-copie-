package domain;

public class Bestellingsregel {
	private int id;
	private int aantal;
	private double prijs;
	private Product product;

	public Bestellingsregel(int id, int aantal, double prijs, Product product) {
		super();
		this.id = id;
		this.aantal = aantal;
		this.prijs = prijs;
		this.product = product;
	}

	public Bestellingsregel(int aantal, double prijs, Product product) {
		super();
		this.aantal = aantal;
		this.prijs = prijs;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
