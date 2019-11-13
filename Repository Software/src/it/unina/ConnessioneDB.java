package it.unina;
import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import oracle.jdbc.pool.OracleDataSource;

/**
 * Classe principale di connessione al database.
 *
 * @author Antonio
 * @author Vanacore
 * @version 2019
 */

public class ConnessioneDB {
	
	
	static public String host = "127.0.0.1";
	static public String servizio = "dbg";
	static public int porta = 1521;
	static public String user = "antov";
	static public String password = "oracle";
	static public String schema = user;
	static private OracleDataSource ods;
	static private Connection conn=null;


	static private JDialog ok;
	
	
	static public Connection getConnection(){
		if(conn==null ){
			conn=connetti();
		}
		return conn;
	}
	
	static public void setDefaultConnection(Connection c) {
	      conn = c;
	   }
	
	static public void disconnetti(){
		
		if(conn!=null){
		try {
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		 }
		System.out.println("Connessione al Database chiusa correttamente.");
		}else{
			System.out.println("!!!connessione gia chiusa!!!");
		 }
	
	}
	
	static public Connection connetti() {	
		
		try{	
			ods=new OracleDataSource();
			System.out.println("Dati acquisiti, attendere...\n");
			ods.setDriverType("thin");
		    ods.setServerName(host);
		    ods.setPortNumber(porta);
			ods.setUser(user);
			ods.setPassword(password);
		    ods.setDatabaseName(servizio);

			conn =ods.getConnection();
			
			if(conn==null){
				
				System.out.println("Problemi nella connessione...\n");
				System.exit(1);
			}else {
				ok=new JDialog(FinestraLogin.frame1,"ATTENZIONE!!!",ModalityType.APPLICATION_MODAL);
				ok.setLocation(500, 300);
				ok.setSize(Principale.DIM_JDIALOG);
				
				JPanel panel1=new JPanel();

				ok.getContentPane().add(panel1,BorderLayout.CENTER);
				
				JPanel panel2=new JPanel();
				ok.getContentPane().add(panel2,BorderLayout.SOUTH);
				
				JLabel label=new JLabel("Connessione riuscita!!");
				panel1.add(label);
				
				JButton button=new JButton("OK");
				panel2.add(button);
				
				button.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent event){
						ok.dispose();
					}
				});
				
				ok.setVisible(true);
				System.out.println("Connessione riuscita!\n");
			}
			
			
		}catch(SQLException e){
			System.out.println("Errore durante la connessione al Database!");
			
			System.out.println(e.getMessage());
			ok=new JDialog(FinestraLogin.frame1,"ATTENZIONE!!!",ModalityType.APPLICATION_MODAL);
			ok.setLocation(500, 300);
			ok.setSize(Principale.DIM_JDIALOG);
			
			JPanel panel1=new JPanel();

			ok.getContentPane().add(panel1,BorderLayout.CENTER);
			
			JPanel panel2=new JPanel();
			ok.getContentPane().add(panel2,BorderLayout.SOUTH);
			
			JLabel label=new JLabel("Problemi durante connessione al DB!");
			panel1.add(label);
			
			JButton button=new JButton("OK");
			panel2.add(button);
			

			button.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent event){
					buttonActionPerformed();
					
				}
			});
			
			ok.setVisible(true);
			
		}
		return conn;	
	}
	
	static public void buttonActionPerformed(){ 
		ok.dispose();
	}
}
