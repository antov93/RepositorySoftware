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

public class Release extends Frame {

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	
	private JTextField nome;
	private JTextField versione;
	private JTextField data;
	private JTextField progetto;
	
	private JScrollPane scrollPaneRelease;
	private JTable release;
	
	public Release(){
		super();
		inizializzaComponentiGrafiche();
		setFrameTable(release);
		setNomeTabella("RELEASE");
		visualizzaTabella();
	}
	
private void inizializzaComponentiGrafiche(){
		
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		
		nome=new JTextField();
		versione=new JTextField();
		data=new JTextField();
		progetto=new JTextField();
		
		scrollPaneRelease= new JScrollPane();
		release = new JTable();
		
		label1.setText("Nome: ");
		label2.setText("Versione: "); 
		label3.setText("Data: "); 
		label4.setText("Progetto: "); 
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Release");
        setLocation(new java.awt.Point(340, 280));
        setResizable(true);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        add(label1);
        add(nome);
        add(label2);
        add(versione);
        add(label3);
        add(data);
        add(label4);
        add(progetto);
        
        scrollPaneRelease.setViewportView(release);
        add(scrollPaneRelease);
        scrollPaneRelease.setEnabled(true);
        release.setEnabled(true);
        getAccessibleContext().setAccessibleDescription("");
        pack();

	}

	private void visualizzaTabella(){
		 
	    Connection conn;
	    PreparedStatement ps;
	    String query = "SELECT * FROM RELEASE";
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
	
	@Override
	protected PreparedStatement getComandoInserimento(Connection conn) throws SQLException {
		  String comando;
	      PreparedStatement ps;
	      comando = "INSERT INTO RELEASE (idRelease,nome,versione,dataRilascio,progetto) "
	      		+ "VALUES(?,?,?,?,?)";
	      ps =conn.prepareStatement(comando);
	      ps.setInt(1, Integer.decode(getIdTabella().getText()));
	      ps.setString(2, nome.getText());
	      ps.setString(3, versione.getText());
	      ps.setString(4, data.getText());
	      ps.setString(5, progetto.getText());
	      return ps;
		
	}
	
	

	@Override
	protected PreparedStatement getComandoModifica(Connection conn) throws SQLException {
		  String comando;
	      PreparedStatement ps;
	      int colonna=0;
	      if(nome.getText().length()==0){
	    	  if(versione.getText().length()==0){
	    		  if(data.getText().length()==0){
	    			  if(progetto.getText().length()==0){
	    				  	  	colonna=0;
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
	      if(colonna==2) ps.setString(1, versione.getText());
	      if(colonna==3) ps.setString(1, data.getText());
	      if(colonna==4) ps.setString(1, progetto.getText());
	      ps.setInt(2, Integer.decode(getIdTabella().getText()));
	      
	      return ps;
		
	}
	
	protected void pulisci() {
	      super.pulisci();
	      nome.setText("");
	      versione.setText("");
	      data.setText("");
	      progetto.setText("");
	      
	   }
	

}
