import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

public class Client implements Serializable{
	static int currentID;
	int id;
	String lastName;
	String firstName;
	String adresse;
	LinkedList<Seat>  Seats = new LinkedList<Seat>();
	public Client(String lastName,String firstName,String adresse)
	{
		currentID++;
		this.id = currentID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.adresse = adresse;
	}
	
	public int getId()
	{
		return this.id;
	}
	public String getFirstName()
	{
		return this.firstName;
	}
	
	public String getLastName()
	{
		return this.lastName;
	}
	
	public String getAdresse()
	{
		return this.adresse;
	}
	
	public String toString()
	{
		return this.getFirstName()+" "+this.getLastName();
	}
	
	public String getFullString()
	{
		return this.id+" : "+this.getFirstName()+" "+this.getLastName()+" ("+this.adresse+")";
	}
	
	public void addSeat(Seat seat)
	{
		this.Seats.add(seat);
	}
	
	public void removeSeat(Seat seat)
	{
		for (int i=0;i<this.Seats.size();i++)
		{
			if((seat.getRow()==this.Seats.get(i).getRow())&&(seat.getCol()==this.Seats.get(i).getCol())) this.Seats.remove(i);
		    break;
		}
		
	}
	
	public LinkedList<Seat> getSeats()
	{
		return this.Seats;
	}
	
	public long getReservationCost()
	{
		long cost = 0;
		for (int i=0;i<Seats.size();i++)
		{
			Seat seat = Seats.get(i);
			cost = (long) (cost + seat.type.getPrice());
		}
		return cost;
	}
	
	public void setCurrectID(int id)
	{
		this.currentID = id;
	}
	
	public String getExplicitedCost()
	{
		long cost = 0;
		String res = "";
		for (int i=0;i<Seats.size();i++)
		{
			Seat seat = Seats.get(i);
			res = res + seat.toString()+" ("+seat.type.getPrice()+"£)\n";
			cost = (long) (cost + seat.type.getPrice());
		}
		res = res+"\n";
		
		res = res + "Total : "+cost;
		return res;
	}
	
	public static void main(String[] args) throws IOException {
		Seat s1 = new Seat(2,3,SeatType.FIRST_CATEGORIE,true);
		Seat s2 = new Seat(2,4,SeatType.SECOND_CATEGORIE,true);
		Client c1 = new Client("ClientTest","azaizia","biri");
		c1.addSeat(s1);
		c1.addSeat(s2);
		c1.removeSeat(s1);
		c1.getExplicitedCost();
	}
}
