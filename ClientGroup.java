
public class ClientGroup extends Client {

	public ClientGroup(String lastName, String firstName, String adresse) {
		super(lastName, firstName, adresse);
		// TODO Auto-generated constructor stub
	}
  
	private double getPromotionByQuantity(int nb)
	{
		double promotion = 0;
		if ((nb>4)&&(nb<11)) promotion = 0.1;
		if ((nb>10)) promotion = 0.2;
		else promotion = 0;
		return promotion;
	}
	
	public String toString()
	{
		return super.toString()+" \"Group\"";
		
	}
	
	public long getReservationCost(int nb)
	{
		long cost = 0;
		for (int i=0;i<Seats.size();i++)
		{
			Seat seat = Seats.get(i);
			cost = (long) (cost + seat.type.getPrice());
		}
		if ((nb>4)&&(nb<11)) cost = (long) (cost*0.1);
		if ((nb>10)) cost = (long) (cost*0.2);
		return cost;
	}
	
	public String getExplicitedCost(int nb)
	{
		long cost = 0;
		String res = "";
		for (int i=0;i<Seats.size();i++)
		{
			Seat seat = Seats.get(i);
			res = res + seat.toString()+" ("+seat.type.getPrice()+"£)\n";
			cost = (long) (cost + seat.type.getPrice());
		}
		if ((nb>4)&&(nb<11))
		{
			res = res +"-10%\n";
			cost = (long) (cost*0.1);
		}
			
		if ((nb>10)) {
			res = res +"-20%\n";
			cost = (long) (cost*0.2);
		}
        res = res+"\n";
		
		res = res + "Total : "+cost;
		return res;
	}
	
	
	private static final long serialVersionUID = 1L;

}
