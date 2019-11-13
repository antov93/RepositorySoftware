package it.unina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class Sviluppatore extends Frame {
	
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	
	private JTextField nome;
	private JTextField cognome;
	private JTextField cellA;
	private JTextField mailA;
	private JTextField cellP;
	private JTextField mailP;
	
	private JScrollPane scrollPaneSviluppatore;
	private JTable sviluppatore;
	
	public Sviluppatore(){
		super();
		inizializzaComponentiGrafiche();
		setFrameTable(sviluppatore);
		setNomeTabella("SVILUPPATORE_VIEW");
		visualizzaTabella();
	}
	
	private void inizializzaComponentiGrafiche(){
		
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		
		nome=new JTextField();
		cognome=new JTextField();
		cellA=new JTextField();
		mailA=new JTextField();
		cellP=new JTextField();
		mailP=new JTextField();
		
		scrollPaneSviluppatore = new JScrollPane();
		sviluppatore = new JTable();
		
		label1.setText("Nome: ");
		label2.setText("Cognome: "); 
		label3.setText("Cellulare Aziendale: "); 
		label4.setText("Email Aziendale: "); 
		label5.setText("Cellulare Personale: "); 
		label6.setText("Email Personale: "); 
	
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sviluppatori");
        setLocation(new java.awt.Point(340, 280));
        setResizable(true);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        add(label1);
        add(nome);
        add(label2);
        add(cognome);
        add(label3);
        add(cellA);
        add(label4);
        add(mailA);
        add(label5);
        add(cellP);
        add(label6);
        add(mailP);
        
        scrollPaneSviluppatore.setViewportView(sviluppatore);
        add(scrollPaneSviluppatore);
        scrollPaneSviluppatore.setEnabled(true);
        sviluppatore.setEnabled(true);
        getAccessibleContext().setAccessibleDescription("");
        pack();

	}
	
	private void visualizzaTabella(){
    	 
         Connection conn;
         PreparedStatement ps;
         String query = "SELECT * FROM SVILUPPATORE";
         try{
            conn=ConnessioneDB.getConnection();
            ps=conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=ps.executeQuery();
            getModelloTabella().setRS(rs);
            }catch (SQLException e) {
                System.out.println(e.getMessage());
            	System.out.println("errore in creaSelectStatemet in visualiz tabella");
             }
     }
	
	

	
	protected PreparedStatement getComandoInserimento(Connection conn) throws SQLException {
		  String comando;
	      PreparedStatement ps;
	      comando = "INSERT INTO SVILUPPATORE (idSviluppatore,nome,cognome,cellAziendale,emailAziendale,cellPersonale,emailPersonale) "
	      		+ "VALUES(?,?,?,?,?,?,?)";
	      ps =conn.prepareStatement(comando);
	      ps.setInt(1, Integer.decode(getIdTabella().getText()));
	      ps.setString(2, nome.getText());
	      ps.setString(3, cognome.getText());
	      ps.setString(4, cellA.getText());
	      ps.setString(5, mailA.getText());
	      ps.setString(6, cellP.getText());
	      ps.setString(7, mailP.getText());
	      return ps;
		
	}
	
	protected PreparedStatement getComandoModifica(Connection conn) throws SQLException {
		  String comando;
	      PreparedStatement ps;
	      int colonna=0;
	      if(nome.getText().length()==0){
	    	  if(cognome.getText().length()==0){
	    		  if(cellA.getText().length()==0){
	    			  if(mailA.getText().length()==0){
	    				  if(cellP.getText().length()==0){
	    					  if(mailP.getText().length()==0){
	    						  colonna=0;
	    					  }else colonna=6;
	    				  }else colonna=5;
	    			  }else colonna=4; 
	    		  }else colonna =3;
	    	  }else colonna=2;
	      }else colonna=1;
	       
	      
	        comando = "UPDATE "+ConnessioneDB.schema +"."+getNomeTabella()+" SET "+getModelloTabella().getColumnName(colonna)
	    		  +" = ? WHERE "+getModelloTabella().getColumnName(0)+" =?";
	      
	      System.out.println(comando);
	      System.out.println(colonna);
	      ps =conn.prepareStatement(comando);
	      
	      if(colonna==0) ps.setString(1, getIdTabella().getText());
	      if(colonna==1) ps.setString(1, nome.getText());
	      if(colonna==2) ps.setString(1, cognome.getText());
	      if(colonna==3) ps.setString(1, cellA.getText());
	      if(colonna==4) ps.setString(1, mailA.getText());
	      if(colonna==5) ps.setString(1, cellP.getText());
	      if(colonna==6) ps.setString(1, mailP.getText());
	      ps.setInt(2, Integer.decode(getIdTabella().getText()));
	      
	      return ps;
		
	}
	
	protected void pulisci() {
	      super.pulisci();
	      nome.setText("");
	      cognome.setText("");
	      cellA.setText("");
	      mailA.setText("");
	      cellP.setText("");
	      mailP.setText("");
	      
	   }
}
