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

public class Metodo extends Frame {
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	
	private JTextField nome;
	private JTextField descrizione;
	private JTextField aggiunto;
	private JTextField modificato;
	private JTextField eliminato;
	private JTextField classe;
	
	private JScrollPane scrollPaneMetodo;
	private JTable metodo;
	
	public Metodo(){
		super();
		inizializzaComponentiGrafiche();
		setFrameTable(metodo);
		setNomeTabella("METODO");
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
		descrizione=new JTextField();
		aggiunto=new JTextField();
		modificato=new JTextField();
		eliminato=new JTextField();
		classe=new JTextField();
		
		scrollPaneMetodo= new JScrollPane();
		metodo = new JTable();
		
		label1.setText("Nome: ");
		label2.setText("Descrizione: "); 
		label3.setText("Aggiunto: "); 
		label4.setText("Modificato: "); 
		label5.setText("Eliminato: "); 
		label6.setText("Classe: "); 
	
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Metodi");
        setLocation(new java.awt.Point(340, 280));
        setResizable(true);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        add(label1);
        add(nome);
        add(label2);
        add(descrizione);
        add(label3);
        add(aggiunto);
        add(label4);
        add(modificato);
        add(label5);
        add(eliminato);
        add(label6);
        add(classe);
        
        scrollPaneMetodo.setViewportView(metodo);
        add(scrollPaneMetodo);
        scrollPaneMetodo.setEnabled(true);
        metodo.setEnabled(true);
        getAccessibleContext().setAccessibleDescription("");
        pack();

	}
	
	private void visualizzaTabella(){
    	 
         Connection conn;
         PreparedStatement ps;
         String query = "SELECT * FROM METODO";
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
	      comando = "INSERT INTO METODO (idMetodo,nome,descrizione,aggiunto,modificato,eliminato,classe) "
	      		+ "VALUES(?,?,?,?,?,?,?)";
	      ps =conn.prepareStatement(comando);
	      ps.setInt(1, Integer.decode(getIdTabella().getText()));
	      ps.setString(2, nome.getText());
	      ps.setString(3, descrizione.getText());
	      ps.setString(4, aggiunto.getText());
	      ps.setString(5, modificato.getText());
	      ps.setString(6, eliminato.getText());
	      ps.setString(7, classe.getText());
	      return ps;
		
	}
	
	@Override
	protected PreparedStatement getComandoModifica(Connection conn) throws SQLException {
		  String comando;
	      PreparedStatement ps;
	      int colonna=0;
	      if(nome.getText().length()==0){
	    	  if(descrizione.getText().length()==0){
	    		  if(aggiunto.getText().length()==0){
	    			  if(modificato.getText().length()==0){
	    				  if(eliminato.getText().length()==0){
	    					  if(classe.getText().length()==0){
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
	      if(colonna==2) ps.setString(1, descrizione.getText());
	      if(colonna==3) ps.setString(1, aggiunto.getText());
	      if(colonna==4) ps.setString(1, modificato.getText());
	      if(colonna==5) ps.setString(1, eliminato.getText());
	      if(colonna==6) ps.setString(1, classe.getText());
	      ps.setInt(2, Integer.decode(getIdTabella().getText()));
	      return ps;
	}
	
	protected void pulisci() {
	      super.pulisci();
	      nome.setText("");
	      descrizione.setText("");
	      aggiunto.setText("");
	      modificato.setText("");
	      eliminato.setText("");
	      classe.setText("");
	      
	   }
}
