import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Point;
import java.awt.BorderLayout;

public class ReservationManagerGUI2 extends JFrame implements ActionListener,MouseListener{

		
		private static final long serialVersionUID = 1L;
		
		ImageIcon image;
		Theater theater_1;
		JComboBox clients;
		LinkedList<Client>  listClient;
		String fileName = "Clients";
		JButton neww = new JButton("New client");
		JButton remove = new JButton("Remove client");
		JButton show = new JButton("Show reservation");
		JButton make = new JButton("Make reservation");
		JButton cancel = new JButton("Cancel reservation");
	
		JTextField poidmax = new JTextField();
		public ReservationManagerGUI2() throws IOException
		{
			try {
		 		
				 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				
			 	} 
			 	catch (Exception e) 
			 	{
			 		JOptionPane.showMessageDialog(null, "Erreur de configuration","Erreur",JOptionPane.WARNING_MESSAGE);
			 	}
			 clients = new JComboBox();
			clients.setName("Liste des clients");
			 listClient = new LinkedList<Client>();
			
			
			try{
				
				theater_1 = new Theater("Theater.csv.bak");
			}
			catch(FileNotFoundException e){
			 theater_1 = new Theater("Theater.csv");
			}
			
			try{
				listClient = Serializer.loadFromFile(fileName);
			}
			catch(Exception e)
			{
				
			}
			for (int i=0;i<listClient.size();i++)
			{
				//clients.ad
				clients.addItem(listClient.get(i));
				//System.out.print(listClient.get(i).toString()+", ");
			}
			JPanel pan = new JPanel(new GridLayout(1,6));
			JPanel pan2 = new JPanel(new GridLayout(1,5));
			JPanel pan3 = new JPanel(new GridLayout(1,5));
			JPanel pan4 = new JPanel(new GridLayout(1,5));
			JPanel pan5 = new JPanel(new GridLayout(1,5));
			pan.setBounds(10, 0, 580, 30);
			pan.add(clients);
            pan.add(neww);
            pan.add(remove);
            pan.add(show);
            pan.add(make);
            pan.add(cancel);
			setTitle("Theater Manager");
			clients.addActionListener(this);
			neww.addActionListener(this);
			remove.addActionListener(this);
			show.addActionListener(this);
			make.addActionListener(this);
			cancel.addActionListener(this);
			add(pan);
			add(pan2);
			add(pan3);
			setSize(640,550);
			setLocationRelativeTo(null);
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			addMouseListener(this);
		
		}	
		
		public void paint(Graphics g)
		{
			 super.paint(g);
			int rectSize = 45;
			
			int x = 120;
			int y = 130;
			for (int i=0;i<this.theater_1.getNbRow();i++)
			{
				for (int j=0;j<theater_1.getNbCol();j++)
				{
					
					g.drawRect(x, y, rectSize, rectSize);
					
				}
				x = 120;
				y = y+45;
			}
			
			x = 121;
			y = 131;
			this.image = new ImageIcon("image.gif");
			for (int i=0;i<theater_1.getNbRow();i++)
			{
				for (int j=0;j<theater_1.getNbCol();j++)
				{
					Seat seat = theater_1.seats[i][j];
					if ((seat.isBooked)&&(seat.getType()!=SeatType.OBSTACLE)&&(seat.getType()!=SeatType.SCENE))  g.setColor(Color.red);
					else
					{
					
			        if (seat.getType()==SeatType.OBSTACLE) 
			        {
			        	g.setColor(Color.gray);
			        }
			        else
			        	if (seat.getType()==SeatType.SCENE) g.setColor(Color.yellow);
			        else 
			        {
			        	g.setColor(Color.green);
			        	
			        }
					}
			        g.fillRect(x, y, rectSize-1, rectSize-1);
			        
			        if ((g.getColor()==Color.green)||(g.getColor()==Color.red))
			        {
			        	this.image.paintIcon(this, g, x, y);
			        	g.setFont(new Font("Default",Font.BOLD,14));
			        	String cat = theater_1.seats[i][j].getType().toString().toUpperCase();
			            Rectangle2D stringDim = g.getFontMetrics().getStringBounds(cat, g);
			            g.setColor(Color.black);
			            g.drawString(cat, x+18,y+22);
			        	
			        }
			        x = x+46;
			        try
					{
			        	
					
					}
					catch(Exception e)
					{
						
					}
				}
				x = 121;
				y = y+46;
			}
			
			
			
		}
		
		
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource()==remove)
				{
					boolean bol = false;
					Client c1 = (Client) clients.getSelectedItem();
					int index = clients.getSelectedIndex();
					clients.removeItem(c1);
					System.out.println(c1.toString());
					int id = c1.getId();
					for (int i=0;i<listClient.size();i++)
					{
						if(listClient.get(i).getId()==id)
						{
							 c1 = listClient.get(i);
							listClient.remove(i);
							JOptionPane.showMessageDialog(null, c1.toString()+" was removed with success", "Information",JOptionPane.INFORMATION_MESSAGE);	
							bol = true;
						}
					}
					if (!bol) JOptionPane.showMessageDialog(null, "Oups, This is not a valid number !", "Erreur",JOptionPane.ERROR_MESSAGE);	
				
					try{
						Serializer.saveToFile(fileName, listClient);
					}
					catch(Exception e1)
					{
						
					}
				}
				
				if (e.getSource()==make)
				{
					boolean bol = false;
					Client c1 = (Client) clients.getSelectedItem();
					int index = clients.getSelectedIndex();
					int id = c1.getId();
					for (int i=0;i<listClient.size();i++)
					{
						if(listClient.get(i).getId()==id)
						{
							 c1 = listClient.get(i);
						    	bol = true;
						}
					}
					
					
					boolean bo = theater_1.makeReservation(theater_1.curentRow, theater_1.curentCol);
					if (bo == true)
					{
					try {
						theater_1.saveC2(theater_1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Seat seat = new Seat(theater_1.curentRow,theater_1.curentCol,theater_1.seats[theater_1.curentRow][theater_1.curentCol].getType(),true);
					JOptionPane.showMessageDialog(null,"Reservation"+seat.toString()+ " made with success", "Information",JOptionPane.INFORMATION_MESSAGE);	
					Graphics g = getContentPane().getGraphics();
					g.setColor(Color.red);
					Client c2 = c1;
					listClient.remove(c1);
					c2.addSeat(seat);
					listClient.add(index, c2);
					try{
						Serializer.saveToFile(fileName, listClient);
					}
					
				
					catch(Exception e2)
					{
						
					}
					}
					else  JOptionPane.showMessageDialog(null, "Oups, This place is already reserved !", "Erreur",JOptionPane.ERROR_MESSAGE);	
					this.dispose();
					try {
						new ReservationManagerGUI2();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					};
				}
				
				if (e.getSource()==cancel)
				{
					boolean bol = false;
					Client c1 = (Client) clients.getSelectedItem();
					int index = clients.getSelectedIndex();
					int id = c1.getId();
					for (int i=0;i<listClient.size();i++)
					{
						if(listClient.get(i).getId()==id)
						{
							 c1 = listClient.get(i);
						    	bol = true;
						}
					}
					
					
					boolean bo = theater_1.cancelReservation(theater_1.curentRow, theater_1.curentCol);
					if (bo == true)
					{
					try {
						theater_1.saveC2(theater_1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Seat seat = new Seat(theater_1.curentRow,theater_1.curentCol,theater_1.seats[theater_1.curentRow][theater_1.curentCol].getType(),true);
					JOptionPane.showMessageDialog(null,"Reservation"+seat.toString()+ " cancled with success", "Information",JOptionPane.INFORMATION_MESSAGE);	
					Client c2 = c1;
					listClient.remove(c1);
					c2.removeSeat(seat);
					listClient.add(index, c2);
					try{
						Serializer.saveToFile(fileName, listClient);
					}
					
				
					catch(Exception e2)
					{
						
					}
					}
					else  JOptionPane.showMessageDialog(null, "Oups, This place is not reserved !", "Erreur",JOptionPane.ERROR_MESSAGE);	
					this.dispose();
					try {
						new ReservationManagerGUI2();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					};
				
				}
				
				if (e.getSource()==show)
				{
					boolean bol = false;
					Client c1 = (Client) clients.getSelectedItem();
					int index = clients.getSelectedIndex();
					int id = c1.getId();
					for (int i=0;i<listClient.size();i++)
					{
						if(listClient.get(i).getId()==id)
						{
							 c1 = listClient.get(i);
						    	bol = true;
						}
					}
					JOptionPane.showMessageDialog(null,c1.getExplicitedCost(), "Cost",JOptionPane.INFORMATION_MESSAGE);	
				}
				
				if (e.getSource()==neww)
				{
					JComboBox<String> Choice = new JComboBox<>();
					Choice.addItem("Client");
					Choice.addItem("VIP");
					Choice.addItem("GROUP");
					JTextField lastNameField = new JTextField(5);
					JTextField firstNameField = new JTextField(5);
					JTextField adresseField = new JTextField(5);
					JPanel pan = new JPanel(new GridLayout(4,2));
					pan.add(new JLabel("Type"));
					pan.add(Choice);
					pan.add(new JLabel("LastName"));
					pan.add(lastNameField);
					pan.add(new JLabel("FirstName"));
					pan.add(firstNameField);
					pan.add(new JLabel("Addresse"));
					pan.add(adresseField);
					
					int result =  JOptionPane.showConfirmDialog(null,pan, "Create new client",JOptionPane.OK_CANCEL_OPTION);	
					if (result == JOptionPane.OK_OPTION){
					   String a = (String) Choice.getSelectedItem();
					   if(a=="Client")
					   {
						   Client c1 = new Client(lastNameField.getText(),firstNameField.getText(),adresseField.getText());
							listClient.add(c1);
							JOptionPane.showMessageDialog(null, c1.toString()+" was add with success", "Information",JOptionPane.INFORMATION_MESSAGE);	  
					   }
					   if(a=="VIP")
					   {
						   ClientVIP c1 = new ClientVIP(lastNameField.getText(),firstNameField.getText(),adresseField.getText());
							listClient.add(c1);
							JOptionPane.showMessageDialog(null, c1.toString()+" was add with success", "Information",JOptionPane.INFORMATION_MESSAGE);	  
					   }
					   if(a=="Group")
					   {
						   ClientGroup c1 = new ClientGroup(lastNameField.getText(),firstNameField.getText(),adresseField.getText());
							listClient.add(c1);
							JOptionPane.showMessageDialog(null, c1.toString()+" was add with success", "Information",JOptionPane.INFORMATION_MESSAGE);	  
					   }
					   try{
							Serializer.saveToFile(fileName, listClient);
						}
						catch(Exception e3)
						{
							
						}
					}
					this.dispose();
					try {
						new ReservationManagerGUI2();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}							
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int rectcol = 0;
				int rectrow = 0;
				int x = e.getX();
				int y = e.getY();
				Graphics g = getContentPane().getGraphics();
				g.setColor(Color.red);
				g.drawRect(x, y, 45, 45);
				int col = x/45-2;
				int row = y/45-2 ;
				theater_1.curentRow = row;
				theater_1.curentCol = col;
				theater_1.curentX = x;
				theater_1.curentY = y;
			
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	public static void main(String[] args) throws IOException 
	{
		new ReservationManagerGUI2();
	}
}

