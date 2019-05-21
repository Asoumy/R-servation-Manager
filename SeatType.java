
public enum SeatType {
	SCENE("S",-1.0),
	OBSTACLE("X",-1.0),
	FIRST_CATEGORIE("a",125.0),
	SECOND_CATEGORIE("b",80.0),
	THIRD_CATEGORIE("c",50.0),
	FOURTH_CATEGORIE("d",20.0),
	FIFTH_CATEGORIE("e",10.0);
	
	private String symbole;
	private double price;
	
	
	SeatType(String symbole,double price)
	{
		this.symbole = symbole;
		this.price = price;
	}
	
	public String toString()
	{
		return symbole;
	}
	
	public String getSymbole()
	{
		return this.symbole;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	public static SeatType getSeatTypeFromSymbol(String symbol)
	{
		if (symbol.equals("S")) return SCENE;
		if (symbol.equals("X")) return OBSTACLE;
		if ((symbol.equals("a"))||(symbol.equals("A"))) return FIRST_CATEGORIE;
		if ((symbol.equals("b"))||(symbol.equals("B"))) return SECOND_CATEGORIE;
		if ((symbol.equals("c"))||(symbol.equals("C"))) return THIRD_CATEGORIE;
		if ((symbol.equals("d"))||(symbol.equals("D"))) return FOURTH_CATEGORIE;
		if ((symbol.equals("e"))||(symbol.equals("E"))) return FIFTH_CATEGORIE;
		else return null;
	}

	
}
