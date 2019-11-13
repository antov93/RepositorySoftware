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

public class Commit extends Frame {
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	
	
	private JTextField descrizione;
	private JTextField data;
	private JTextField classe;
	private JTextField sviluppatore;
	
	
	private JScrollPane scrollPaneCommit;
	private JTable commit;
	
	public Commit(){
		super();
		inizializzaComponentiGrafiche();
		setFrameTable(commit);
		setNomeTabella("TABELLA_COMMIT");
		visualizzaTabella();
	}
	
	private void inizializzaComponentiGrafiche(){
		
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		
		descrizione=new JTextField();
		data=new JTextField();
		classe=new JTextField();
		sviluppatore=new JTextField();
		
		scrollPaneCommit= new JScrollPane();
		commit = new JTable();
		
		label1.setText("Descrizione: ");
		label2.setText("Data: "); 
		label3.setText("Classe: "); 
		label4.setText("Sviluppatore: "); 
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Commit");
        setLocation(new java.awt.Point(340, 280));
        setResizable(true);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        add(label1);
        add(descrizione);
        add(label2);
        add(data);
        add(label3);
        add(classe);
        add(label4);
        add(sviluppatore);
        
        scrollPaneCommit.setViewportView(commit);
        add(scrollPaneCommit);
        scrollPaneCommit.setEnabled(true);
        commit.setEnabled(true);
        getAccessibleContext().setAccessibleDescription("");
        pack();

	}
	
	private void visualizzaTabella(){
    	 
         Connection conn;
         PreparedStatement ps;
         String query = "SELECT * FROM TABELLA_COMMIT";
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
	      comando = "INSERT INTO TABELLA_COMMIT (idCommit,descrizione,data,classe,sviluppatore) "
	      		+ "VALUES(?,?,?,?,?)";
	      ps =conn.prepareStatement(comando);
	      ps.setInt(1, Integer.decode(getIdTabella().getText()));
	      ps.setString(2, descrizione.getText());
	      ps.setString(3, data.getText());
	      ps.setString(4, classe.getText());
	      ps.setString(5, sviluppatore.getText());
	      return ps;
		
	}
	
	@Override
	protected PreparedStatement getComandoModifica(Connection conn) throws SQLException {
		  String comando;
	      PreparedStatement ps;
	      int colonna=0;
	      if(descrizione.getText().length()==0){
	    	  if(data.getText().length()==0){
	    		  if(classe.getText().length()==0){
	    			  if(sviluppatore.getText().length()==0){
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
	      if(colonna==1) ps.setString(1, descrizione.getText());
	      if(colonna==2) ps.setString(1, data.getText());
	      if(colonna==3) ps.setString(1, classe.getText());
	      if(colonna==4) ps.setString(1, sviluppatore.getText());
	      ps.setInt(2, Integer.decode(getIdTabella().getText()));
	      return ps;
	}
	
	protected void pulisci() {
	      super.pulisci();
	      descrizione.setText("");
	      data.setText("");
	      classe.setText("");
	      sviluppatore.setText("");
	      
	   }

}
