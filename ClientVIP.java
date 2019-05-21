
public class ClientVIP extends Client {

	public ClientVIP(String lastName, String firstName, String adresse) {
		super(lastName, firstName, adresse);
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
	
	private double getPromotionBySeatType(SeatType type)
	{
		double promotion = 0;
		if(type == SeatType.FIRST_CATEGORIE)
		{
			promotion =  0.3;
		}
		
		if(type == SeatType.SECOND_CATEGORIE)
		{
			promotion =  0.2;
		}
		
		if(type == SeatType.THIRD_CATEGORIE)
		{
			promotion = 0.1;
		}
		if(type == SeatType.FOURTH_CATEGORIE)
		{
			promotion =  0.0;
		}
		if(type == SeatType.FIFTH_CATEGORIE)
		{
			promotion =  0.0;
		}
		return promotion;
	}
	
	private double getReducedPrice(SeatType type)
	{
		double price = 0;
		if(type == SeatType.FIRST_CATEGORIE)
		{
			price =  0.3*125;
			price = 125 - price;
		}
		
		if(type == SeatType.SECOND_CATEGORIE)
		{
			price =  0.2*80;
			price = 80 - price;
		}
		
		if(type == SeatType.THIRD_CATEGORIE)
		{
			price = 0.1*50;
			price = 50 - price;
		}
		
		if(type == SeatType.FOURTH_CATEGORIE)
		{
			price = 20;
		}
		if(type == SeatType.FIFTH_CATEGORIE)
		{
			price = 10;
		}
		
		
		return price;
	}
	
	public String toString()
	{
		return super.toString()+" \"VIP\"";
		
	}
	
	public long getReservationCost()
	{
		long cost = 0;
		for (int i=0;i<Seats.size();i++)
		{
			Seat seat = Seats.get(i);
			cost = (long) (cost + this.getReducedPrice(seat.getType()));
		}
		return cost;
	}
	
	public String getExplicitedCost()
	{
		double cost = 0;
		String res = "";
		for (int i=0;i<Seats.size();i++)
		{
			Seat seat = Seats.get(i);
			res = res + seat.toString()+" ("+seat.type.getPrice()+"£ - "+this.getPromotionBySeatType(seat.type)*100+"% = "+this.getReducedPrice(seat.getType())+"£)\n";
			cost =  (cost + this.getReducedPrice(seat.getType()));
		}
        res = res+"\n";
		
		res = res + "Total : "+cost;
		return res;
	}

}
