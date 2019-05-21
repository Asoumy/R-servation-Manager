import java.io.Serializable;

public class Seat implements Serializable{
	private int row;
	private int col;
	SeatType type;
	boolean isBooked;

	
	public Seat(int row,int col,SeatType type,boolean isBooked)
	{
		this.row = row;
		this.col = col;
		this.type = type;
		this.isBooked = isBooked;
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getCol()
	{
		return this.col;
	}
	
	public SeatType getType()
	{
		return this.type;
	}
	
	public void setBooked(boolean isBooked)
	{
		this.isBooked = isBooked;
	}
/*
	* On utilise l'artithmetique des caracteres ('A'+1 => 'B')
	* pour convertir les nombres 0, 1, 2, ... en caracteres A, B, C, ...*/
	
	public String toString()
	{
		char rowLetter = (char)('A'+row);
		return(""+rowLetter+col);
	}

}
