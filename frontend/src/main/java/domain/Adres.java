package domain;

public class Adres {
	private int id;
	private String straat;
	private int straatnummer;
	private String toevoeging;
	private String postcode;
	private String plaats;

	public Adres(int id, String straat, int straatnummer, String toevoeging, String postcode, String plaats) {
		super();
		this.id = id;
		this.straat = straat;
		this.straatnummer = straatnummer;
		this.toevoeging = toevoeging;
		this.postcode = postcode;
		this.plaats = plaats;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public int getStraatnummer() {
		return straatnummer;
	}

	public void setStraatnummer(int straatnummer) {
		this.straatnummer = straatnummer;
	}

	public String getToevoeging() {
		return toevoeging;
	}

	public void setToevoeging(String toevoeging) {
		this.toevoeging = toevoeging;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPlaats() {
		return plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}

}
