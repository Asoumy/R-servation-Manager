import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;



public class Theater {
	private int nbRow;
	private int nbCol;
	Seat[][] seats;
	String fileName;
	int curentRow;
	int curentCol;
	int curentX;
	int curentY;
	private Scanner sc;
	
	
	
	public Theater(int nbRow, int nbCol) throws IOException
	{
		this.nbRow = nbRow;
		this.nbCol = nbCol;
		
		seats = new Seat[nbRow][nbCol];
		for (int i=0;i<nbRow;i++)
		{
			for (int j=0;j<nbCol;j++)
			{
				Seat seat = new Seat(i,j,SeatType.FIRST_CATEGORIE,false);
				seats[i][j] = seat;	
			}	
			
		}
		saveC1();
		
	}
	
	public Theater(String fileName) throws IOException
	{
		this.fileName = fileName;
		File fichier = new File(fileName);
		Scanner scane = new Scanner("ABC;DEF");
		BufferedReader br = new BufferedReader(new FileReader(fichier));
        String line,line1;
	  	while ((line1 = br.readLine()) != null) 
	  	{
	  		 scane = new Scanner(line1);
	  		scane.useDelimiter(";");
	  		
	  		while(scane.hasNext())
	  		{
	  			
	  			this.nbRow = Integer.parseInt(scane.next());
	  			this.nbCol = Integer.parseInt(scane.next());
	  			break;
	  		}
	  		break;
	  	}
	  	seats = new Seat[this.nbRow][this.nbCol];
		for (int i=0;i<nbRow;i++)
		{
			line1 = br.readLine();
			int j = 0;
			scane = new Scanner(line1);
	  		scane.useDelimiter(";");
	  		while(scane.hasNext())
	  		{
	  			String symbol = scane.next().substring(0, 1);
	  			//System.out.println(SeatType.getSeatTypeFromSymbol(symbol));
	  			Seat seat = new Seat(i,j,SeatType.getSeatTypeFromSymbol(symbol),Character.isUpperCase(symbol.charAt(0)));
	  		//System.out.println(SeatType.getSeatTypeFromSymbol(symbol));
	  			this.seats[i][j] = seat;
	  		    j++;
	  		}
		}
	  		
		scane.close();
		saveC2(this);
	}
	
	
	
	
	public void saveC1() throws IOException
	{
		File fichier2 = new File("Theater.csv");
		FileWriter f2 = new FileWriter(fichier2);
		f2.write(this.nbRow+";"+this.nbCol+"\n");
		for (int i=0;i<nbRow;i++)
		{
			for (int j=0;j<nbCol;j++)
			{
				if (seats[i][j].isBooked) f2.write(seats[i][j].getType().getSymbole().toUpperCase()+";");
				else f2.write(seats[i][j].getType().getSymbole().toLowerCase()+";");
			}	
			f2.write("\n");
		}
		f2.close();
	}
	
	public void saveC2(Theater theater_1) throws IOException
	{
		File fichier2 = new File("Theater.csv.bak");
		FileWriter f2 = new FileWriter(fichier2);
		f2.write(theater_1.nbRow+";"+theater_1.nbCol+"\n");
		for (int i=0;i<theater_1.nbRow;i++)
		{
			for (int j=0;j<theater_1.nbCol;j++)
			{
				if (theater_1.seats[i][j].isBooked) f2.write(theater_1.seats[i][j].getType().getSymbole().toUpperCase()+";");
				else f2.write(theater_1.seats[i][j].getType().getSymbole().toLowerCase()+";");
			}	
			f2.write("\n");
		}
		f2.close();
	}
	
	public boolean makeReservation(int row, int col)
	{
		if(seats[row][col].isBooked==true) 
		{
			System.out.println("Erreur, cette salle est deja reservée");
			return false;
		}
			
		else seats[row][col].isBooked = true;
		return true;
	}

	public boolean cancelReservation(int row, int col)
	{
		if(!seats[row][col].isBooked)
		{
			System.out.println("Erreur, cette salle n'est pas reservé");
			return false;
		}
		else seats[row][col].isBooked = false;
		return true;
	}
	
	public void Affich()
	{
		for (int i=0;i<this.nbRow;i++)
		{
			System.out.print(""+(char)('A'+i));
			for (int j=0;j<this.nbCol;j++)
			{
				
				if(seats[i][j].isBooked == true)
				{
					try{
						System.out.print(" "+seats[i][j].type.getSymbole().toUpperCase());
					}
					catch(Exception e1)
					{
						System.out.print(" "+seats[i][j].type.getSymbole());
					}
					
				}
				else 
				{
					try{
						System.out.print(" "+seats[i][j].type.getSymbole().toLowerCase());
					}
					catch(Exception e)
					{
						System.out.print(" "+seats[i][j].type.getSymbole());
					}
					
				}
			}	
			System.out.println();
		}
		for (int j=-1;j<this.nbCol;j++)
		{
			if (j==-1) System.out.print(" ");
			else System.out.print(" "+j);
		}
		System.out.println();
		System.out.println();
	}
	
	public int getNbRow()
	{
		return this.nbRow;
	}
	
	public int getNbCol()
	{
		return this.nbCol;
	}
	
	
	
	public static void main(String[] args) throws IOException {

	}
	
	
}
