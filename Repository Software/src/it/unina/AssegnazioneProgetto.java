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

public class AssegnazioneProgetto extends Frame {
	private JLabel label1;
	private JLabel label2;

	
	private JTextField sviluppatore;
	private JTextField progetto;
	
	private JScrollPane scrollPaneAssegnazioneProgetto;
	private JTable assegnazioneprogetto;
	
	public AssegnazioneProgetto(){
		super();
		inizializzaComponentiGrafiche();
		setFrameTable(assegnazioneprogetto);
		setNomeTabella("ASSEGNAZIONE_PROGETTO");
		visualizzaTabella();
	}
	
	private void inizializzaComponentiGrafiche(){
		
		label1 = new JLabel();
		label2 = new JLabel();
		
		sviluppatore=new JTextField();
		progetto=new JTextField();
		
		scrollPaneAssegnazioneProgetto= new JScrollPane();
		assegnazioneprogetto = new JTable();
		
		label1.setText("Sviluppatore: ");
		label2.setText("Progetto: "); 
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sviluppatori e Progetti");
        setLocation(new java.awt.Point(340, 280));
        setResizable(true);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        add(label1);
        add(sviluppatore);
        add(label2);
        add(progetto);
        
        scrollPaneAssegnazioneProgetto.setViewportView(assegnazioneprogetto);
        add(scrollPaneAssegnazioneProgetto);
        scrollPaneAssegnazioneProgetto.setEnabled(true);
        assegnazioneprogetto.setEnabled(true);
        getAccessibleContext().setAccessibleDescription("");
        pack();

	}
	
	private void visualizzaTabella(){
    	 
         Connection conn;
         PreparedStatement ps;
         String query = "SELECT * FROM ASSEGNAZIONE_PROGETTO";
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
	      comando = "INSERT INTO ASSEGNAZIONE_PROGETTO (sviluppatore,progetto) "
	      		+ "VALUES(?,?)";
	      ps =conn.prepareStatement(comando);
	      ps.setInt(1, Integer.decode(sviluppatore.getText()));
	      ps.setInt(2, Integer.decode(progetto.getText()));
	      
	      return ps;
		
	}
	
	protected PreparedStatement getComandoModifica(Connection conn) throws SQLException {
		  String comando;
	      PreparedStatement ps;
	      int colonna=0;
	      if(sviluppatore.getText().length()==0){
	    	  		if(progetto.getText().length()==0){
	    		  			  			colonna=0;
	    	  		}else colonna=2;
	      }else colonna=1;
	       
	      
	        comando = "UPDATE "+ConnessioneDB.schema +"."+getNomeTabella()+" SET "+getModelloTabella().getColumnName(colonna)
	    		  +" = ? WHERE "+getModelloTabella().getColumnName(0)+" =?";
	      
	      System.out.println(comando);
	      System.out.println(colonna);
	      ps =conn.prepareStatement(comando);
	      
	      if(colonna==1) ps.setString(1, sviluppatore.getText());
	      if(colonna==2) ps.setString(1, progetto.getText());
	      ps.setInt(2, Integer.decode(getIdTabella().getText()));
	      
	      return ps;
	}
	
	protected void pulisci() {
	      super.pulisci();
	      sviluppatore.setText("");
	      progetto.setText("");
	      
	   }

}
