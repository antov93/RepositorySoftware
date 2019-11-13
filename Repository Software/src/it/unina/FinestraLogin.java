package it.unina;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class FinestraLogin {

	static private JTextField hostText;
	static private JTextField portText;
	static private JTextField nomeDbText;
	static private JTextField userText;
	static private JPasswordField pwText;
	static private JTextField nomeSchemaText;
	static protected JFrame frame1=new JFrame("Connessione Database");
	
	public static final Dimension DIM_TEXT_FIELD=new Dimension(100,25);
	
	public static void creaFinestraLogin(){
		
	
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setLocation(new Point(300,300));
		frame1.setSize(400, 350);
		frame1.setResizable(true);
		
		JPanel pannello=new JPanel();  
		pannello.setLayout(new BoxLayout(pannello,BoxLayout.Y_AXIS)); 
		
		JPanel panel1=new JPanel();
		JLabel label1=new JLabel("Host: ");
		hostText= new JTextField();
		hostText.setPreferredSize(DIM_TEXT_FIELD);
		hostText.setText("127.0.0.1");
		
		JPanel panel2=new JPanel();
		JLabel label2=new JLabel("Porta: ");
		portText= new JTextField();
		portText.setPreferredSize(DIM_TEXT_FIELD);
		portText.setText("1521");
		
		JPanel panel3=new JPanel();
		JLabel label3=new JLabel("User: ");
		userText= new JTextField();
		userText.setPreferredSize(DIM_TEXT_FIELD);
		userText.setText("antov");
		
		JPanel panel4=new JPanel();
		JLabel label4=new JLabel("Password: ");
		pwText= new JPasswordField();
		pwText.setPreferredSize(DIM_TEXT_FIELD);
		pwText.setText("oracle");
		
		JPanel panel5=new JPanel();
		JLabel label5=new JLabel("Nome DB: ");
		nomeDbText= new JTextField();
		nomeDbText.setPreferredSize(DIM_TEXT_FIELD);
		nomeDbText.setText("dbg");
		
		JPanel panel7=new JPanel();
		JLabel label7=new JLabel("Nome Schema: ");
		nomeSchemaText= new JTextField();
		nomeSchemaText.setPreferredSize(DIM_TEXT_FIELD);
		nomeSchemaText.setText("antov");

		
		JPanel panel6=new JPanel();
		JButton bottoneOK=new JButton("LOGIN");
		JButton bottoneCANCEL= new JButton("Annulla");
		
		bottoneOK.addActionListener(new ActionListener() {
			
				@Override
				public void actionPerformed(ActionEvent event) {
					
						loginButtonActionPerformed(event);
					
				}
			});
		
		bottoneCANCEL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
					cancelButtonActionPerformed(event);
				
			}
		});
		
		frame1.getContentPane().add(pannello);
		
		pannello.add(panel1);//host
		pannello.add(panel2);//porta
		pannello.add(panel3);//nomeDB
		pannello.add(panel4);//user
		pannello.add(panel5);//pass
		pannello.add(panel7);//schema
		pannello.add(panel6);//bottoni
		
		panel1.add(label1);
		panel1.add(hostText);
		
		panel2.add(label2);
		panel2.add(portText);
		
		panel3.add(label3);
		panel3.add(userText);
		
		panel4.add(label4);
		panel4.add(pwText);
		
		panel5.add(label5);
		panel5.add(nomeDbText);
		
		panel7.add(label7);
		panel7.add(nomeSchemaText);
		
		panel6.add(bottoneOK);
		panel6.add(bottoneCANCEL);
		
		frame1.setVisible(true);
		
	}//fine creaGUI
	
	static public void loginButtonActionPerformed(ActionEvent event) {
		
		ConnessioneDB.host=hostText.getText();
		try{
			ConnessioneDB.porta=Integer.parseInt(portText.getText());
		}catch (NumberFormatException e){
			JOptionPane.showMessageDialog(frame1, "La porta dev'essere"
             + " un numero", "Errore", JOptionPane.ERROR_MESSAGE);
         }
		ConnessioneDB.user=userText.getText();
		ConnessioneDB.password=(new String(pwText.getPassword()));
		ConnessioneDB.schema=nomeSchemaText.getText();
		ConnessioneDB.servizio=nomeDbText.getText();
		ConnessioneDB.connetti();
		
		
		if(ConnessioneDB.getConnection()!=null){
			javax.swing.SwingUtilities.invokeLater(
						new Runnable(){
							public void run(){
								FinestraPrincipale.creaFinestra();
								
							}
						}
					);
			frame1.dispose();
		
		}
		frame1.dispose();
	}
	
	static public void cancelButtonActionPerformed(ActionEvent event){
		ConnessioneDB.disconnetti();
		System.exit(1);
	}
}
