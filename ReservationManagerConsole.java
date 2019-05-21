import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class ReservationManagerConsole {
  
	
	public static void main(String[] args) throws IOException {
		boolean boucle = true;
		LinkedList<Client>  listClient = new LinkedList<Client>();
		String fileName = "Clients";
		Theater theater_1;
		try{
			
			theater_1 = new Theater("Theater.csv.bak");
			System.out.println("houssem");
		}
		catch(FileNotFoundException e){
		 theater_1 = new Theater("Theater.csv");
		
		}
		Scanner sc ;
		System.out.println("Welcome to the reservation manager");
		System.out.println("File Theater.csv loaded with success :");
		while (boucle)
		{
			
			System.out.println("What do you want to do :");
			System.out.println("h for help");
			System.out.println("st Show Theater");
			System.out.println("mr Make reservation");
			System.out.println("cr Cancel reservation");
			System.out.println("sr Show Reservation");
			System.out.println("lc List clients ");
			System.out.println("ac Add client");
			System.out.println("rc Remove client");
			
			System.out.println("q to quit");
			sc = new Scanner(System.in);
			String str = sc.nextLine();
			if (str.equals("h"))
			{
				System.out.println("h: Print this help");
			}
			if (str.equals("st"))
			{
				theater_1.Affich();
			}
			if (str.equals("mr"))
			{
				System.out.println("Clients list");
				Client c1 = null;
				try{
					listClient = Serializer.loadFromFile(fileName);
				}
				catch(Exception e)
				{
					
				}
				System.out.println();
				
				for (int i=0;i<listClient.size();i++)
				{
					System.out.println("Clien n°"+listClient.get(i).getFullString());
				}
				System.out.println("Please enter the id of the client or -1 to cancel the action");
				sc = new Scanner(System.in);
				int id = sc.nextInt();
				boolean bol = false;
				int index = 0;
				for (int i=0;i<listClient.size();i++)
				{
					if(listClient.get(i).getId()==id)
					{
						 c1 = listClient.get(i);
						 index = i;
						bol = true;
					}
				}
				if (!bol) System.out.println("Oups, This is not a valid number !");
				try{
					Serializer.saveToFile(fileName, listClient);
				}
				catch(Exception e)
				{
					
				}
				
				theater_1.Affich();
				System.out.println("Please enter row number");
				sc = new Scanner(System.in);
				str = sc.nextLine();
				 char ch = str.charAt(0);
				int row = (int)(ch-'A');
				System.out.println("Please enter line number");
				sc = new Scanner(System.in);
				int col = sc.nextInt();
				boolean bo = theater_1.makeReservation(row, col);
				theater_1.Affich();
				if (bo == true)
				{
				theater_1.saveC2(theater_1);
				Seat seat = new Seat(row,col,theater_1.seats[row][col].getType(),true);
				
				Client c2 = c1;
				listClient.remove(c1);
				c2.addSeat(seat);
				listClient.add(index, c2);
				try{
					Serializer.saveToFile(fileName, listClient);
				}
				catch(Exception e)
				{
					
				}
				}
				
				
			}
			if (str.equals("cr"))
			{
				System.out.println("Clients list");
				Client c1 = null;
				try{
					listClient = Serializer.loadFromFile(fileName);
				}
				catch(Exception e)
				{
					
				}
				System.out.println();
				
				for (int i=0;i<listClient.size();i++)
				{
					System.out.println("Clien n°"+listClient.get(i).getFullString());
				}
				System.out.println("Please enter the id of the client or -1 to cancel the action");
				sc = new Scanner(System.in);
				int id = sc.nextInt();
				boolean bol = false;
				int index = 0;
				for (int i=0;i<listClient.size();i++)
				{
					if(listClient.get(i).getId()==id)
					{
						 c1 = listClient.get(i);
						 index = i;
						bol = true;
					}
				}
				if (!bol) System.out.println("Oups, This is not a valid number !");
				try{
					Serializer.saveToFile(fileName, listClient);
				}
				catch(Exception e)
				{
					
				}
				
				theater_1.Affich();
				System.out.println("Please enter row number");
				sc = new Scanner(System.in);
				str = sc.nextLine();
				 char ch = str.charAt(0);
				int row = (int)(ch-'A');
				System.out.println("Please enter line number");
				sc = new Scanner(System.in);
				int col = sc.nextInt();
				boolean bo = theater_1.cancelReservation(row, col);
				theater_1.Affich();
				theater_1.saveC2(theater_1);
				if (bo == true)
				{
				
				Seat seat =  new Seat(row,col,theater_1.seats[row][col].getType(),false);
				listClient.remove(c1);
				c1.removeSeat(seat);
				listClient.add(index, c1);;
				
				try{
					Serializer.saveToFile(fileName, listClient);
				}
				catch(Exception e)
				{
					
				}
				}
			}
			
			if (str.equals("sr"))
			{
				System.out.println("Clients list");
				Client c1 = null;
				try{
					listClient = Serializer.loadFromFile(fileName);
				}
				catch(Exception e)
				{
					
				}
				System.out.println();
				
				for (int i=0;i<listClient.size();i++)
				{
					System.out.println("Clien n°"+listClient.get(i).getFullString());
				}
				System.out.println("Please enter the id of the client or -1 to cancel the action");
				sc = new Scanner(System.in);
				int id = sc.nextInt();
				boolean bol = false;
				for (int i=0;i<listClient.size();i++)
				{
					if(listClient.get(i).getId()==id)
					{
						 c1 = listClient.get(i);
						bol = true;
						c1.getExplicitedCost();
					}
				}
				if (!bol) System.out.println("Oups, This is not a valid number !");
				try{
					Serializer.saveToFile(fileName, listClient);
				}
				catch(Exception e)
				{
					
				}
				
			}
			
			if (str.equals("lc"))
			{
				try{
					listClient = Serializer.loadFromFile(fileName);
				}
				catch(Exception e)
				{
					
				}
				System.out.println();
				System.out.print("[");
				for (int i=0;i<listClient.size();i++)
				{
					System.out.print(listClient.get(i).toString()+", ");
				}
				System.out.println("]");
			}
			
			if (str.equals("ac"))
			{
				System.out.println("LastName :");
				sc = new Scanner(System.in);
				String last = sc.nextLine();
				System.out.println("FirstName :");
				sc = new Scanner(System.in);
				String first = sc.nextLine();
				System.out.println("Adresse :");
				sc = new Scanner(System.in);
				String adresse = sc.nextLine();
				System.out.println("Choose client type :");
				System.out.println("1 - Client");
				System.out.println("2 - VIP");
				System.out.println("3- Group");
				sc = new Scanner(System.in);
				int choix = sc.nextInt();
				try{
					listClient = Serializer.loadFromFile(fileName);
				}
				catch(Exception e)
				{
					
				}
				System.out.println();
				Client c = null;
				if(listClient.size()!=0)
				{
					c = listClient.getLast();
			    c.setCurrectID(c.getId());
				}
				if (choix == 1) 
				{
					Client c1 = new Client(last,first,adresse);
					listClient.add(c1);
					System.out.println(c1.toString()+" was add with success");
				}
				
				if (choix == 2) 
				{
					ClientVIP c1 = new ClientVIP(last,first,adresse);
					listClient.add(c1);
					System.out.println(c1.toString()+" was add with success");
				}
				if (choix == 3) 
				{
					ClientGroup c1 = new ClientGroup(last,first,adresse);
					listClient.add(c1);
					System.out.println(c1.toString()+" was add with success");
				}
				
				try{
					Serializer.saveToFile(fileName, listClient);
				}
				catch(Exception e)
				{
					
				}
				
			}
			
			if (str.equals("rc"))
			{
				System.out.println();
				boolean bol = false;
				for (int i=0;i<listClient.size();i++)
				{
					System.out.println("Clien n°"+listClient.get(i).getFullString());
				}
				System.out.println("Please enter the id of the client or -1 to cancel the action");
				sc = new Scanner(System.in);
				int id = sc.nextInt();
				for (int i=0;i<listClient.size();i++)
				{
					if(listClient.get(i).getId()==id)
					{
						Client c1 = listClient.get(i);
						listClient.remove(i);
						System.out.println(c1.toString()+" was removed with success");	
						bol = true;
					}
				}
				if (!bol) System.out.println("Oups, This is not a valid number !");
				try{
					Serializer.saveToFile(fileName, listClient);
				}
				catch(Exception e)
				{
					
				}
				
			}
			
			if (str.equals("q"))
			{
				System.out.println("Bye Bye");
				boucle = false;
			}
		}
		

	}

}
