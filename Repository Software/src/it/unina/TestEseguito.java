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

public class TestEseguito extends Frame {

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	
	private JTextField test;
	private JTextField release;
	private JTextField package_;
	private JTextField classe;
	private JTextField data;
	private JTextField esito;
	
	private JScrollPane scrollPaneTestEseguito;
	private JTable testeseguito;
	
	public TestEseguito(){
		super();
		inizializzaComponentiGrafiche();
		setFrameTable(testeseguito);
		setNomeTabella("TEST_ESEGUITO");
		visualizzaTabella();
	}
	
	private void inizializzaComponentiGrafiche(){
		
		label1 = new JLabel();
		label2 = new JLabel();
		label3 = new JLabel();
		label4 = new JLabel();
		label5 = new JLabel();
		label6 = new JLabel();
		
		test=new JTextField();
		release=new JTextField();
		package_=new JTextField();
		classe=new JTextField();
		data=new JTextField();
		esito=new JTextField();
		
		scrollPaneTestEseguito= new JScrollPane();
		testeseguito = new JTable();
		
		label1.setText("Test: ");
		label2.setText("Release: "); 
		label3.setText("Package: "); 
		label4.setText("Classe: "); 
		label5.setText("Data: "); 
		label6.setText("Esito: "); 
	
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Test Eseguiti");
        setLocation(new java.awt.Point(340, 280));
        setResizable(true);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        
        add(label1);
        add(test);
        add(label2);
        add(release);
        add(label3);
        add(package_);
        add(label4);
        add(classe);
        add(label5);
        add(data);
        add(label6);
        add(esito);
        
        scrollPaneTestEseguito.setViewportView(testeseguito);
        add(scrollPaneTestEseguito);
        scrollPaneTestEseguito.setEnabled(true);
        testeseguito.setEnabled(true);
        getAccessibleContext().setAccessibleDescription("");
        pack();

	}
	
	private void visualizzaTabella(){
    	 
         Connection conn;
         PreparedStatement ps;
         String query = "SELECT * FROM TEST_ESEGUITO";
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
	      comando = "INSERT INTO TEST_ESEGUITO (idEsecuzione,test,release,package,classe,data,esito) "
	      		+ "VALUES(?,?,?,?,?,?,?)";
	      ps =conn.prepareStatement(comando);
	      ps.setInt(1, Integer.decode(getIdTabella().getText()));
	      ps.setString(2, test.getText());
	      ps.setString(3, release.getText());
	      ps.setString(4, package_.getText());
	      ps.setString(5, classe.getText());
	      ps.setString(6, data.getText());
	      ps.setString(7, esito.getText());
	      return ps;
		
	}
	
	protected PreparedStatement getComandoModifica(Connection conn) throws SQLException {
		  String comando;
	      PreparedStatement ps;
	      int colonna=0;
	      if(test.getText().length()==0){
	    	  if(release.getText().length()==0){
	    		  if(package_.getText().length()==0){
	    			  if(classe.getText().length()==0){
	    				  if(data.getText().length()==0){
	    					  if(esito.getText().length()==0){
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
	      if(colonna==1) ps.setString(1, test.getText());
	      if(colonna==2) ps.setString(1, release.getText());
	      if(colonna==3) ps.setString(1, package_.getText());
	      if(colonna==4) ps.setString(1, classe.getText());
	      if(colonna==5) ps.setString(1, data.getText());
	      if(colonna==6) ps.setString(1, esito.getText());
	      ps.setInt(2, Integer.decode(getIdTabella().getText()));
	      
	      return ps;
		
	}
	
	protected void pulisci() {
	      super.pulisci();
	      test.setText("");
	      release.setText("");
	      package_.setText("");
	      classe.setText("");
	      data.setText("");
	      esito.setText("");
	      
	   }
}
