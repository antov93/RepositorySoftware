package it.unina;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;


abstract public class Frame extends JFrame {
	
	private TableModel modelloTabellaFrame;
	private String nomeTabellaFrame; 
	private JTable tabellaFrame;
	
	protected ResultSet rs;
	protected String query;
	
	private	JButton inserisciButton;
	private JButton salvaButton;
	private JButton cercaButton;
	private JButton modificaButton;
	private JButton eliminaButton;
	private JButton chiudiButton;
	
	private JLabel label;
	private JTextField id;
	
	private JPanel pannelloBottoni;
	private JPanel pannello;
	
	public Frame(){
		inizializzaComponentiGrafiche();
	}
	
	abstract protected PreparedStatement getComandoInserimento(Connection c)
	           throws SQLException;
	
	abstract protected PreparedStatement getComandoModifica(Connection c)
	           throws SQLException;
	
	private void inizializzaComponentiGrafiche() {
	 	
		inserisciButton = new JButton();
	 	modificaButton = new JButton();
	 	cercaButton = new JButton();
	 	salvaButton = new JButton();
	 	eliminaButton = new JButton();
        chiudiButton = new JButton();

        label = new JLabel();
        id = new JTextField();
    
        pannelloBottoni =new JPanel();
        pannello =new JPanel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new Point(300,300));
		setSize(500, 400);
		setResizable(true);
		setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		pannelloBottoni.setLayout(new FlowLayout());
	    pannello.setLayout(new BoxLayout(pannello, BoxLayout.Y_AXIS));
	    
		inserisciButton.setText("INSERISCI");
		modificaButton.setText("MODIFICA");
		cercaButton.setText("CERCA");
		salvaButton.setText("SALVA");
		eliminaButton.setText("ELIMINA");
		chiudiButton.setText("CHIUDI TABELLA");
		
		label.setText("ID elemento: ");
		id.setSize(430,129);
		
		 inserisciButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                inserisciButtonActionPerformed(evt);
	            }
	        });
		 modificaButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                modificaButtonActionPerformed(evt);
	            }

				
	        });
		 cercaButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                cercaButtonActionPerformed(evt);
	            }

				
	        });
		 salvaButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	salvaButtonActionPerformed(evt);
	            }
	        });
		 eliminaButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	eliminaButtonActionPerformed(evt);
	            }
	        });
		 chiudiButton.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                chiudiButtonActionPerformed(evt);
	            }
	        });
		 
		 getContentPane().add(pannelloBottoni);
		 getContentPane().add(pannello);
		 pannelloBottoni.add(inserisciButton);
		 pannelloBottoni.add(modificaButton);
		 pannelloBottoni.add(cercaButton);
		 pannelloBottoni.add(eliminaButton);
		 pannelloBottoni.add(salvaButton);
		 pannelloBottoni.add(chiudiButton);
		 pannello.add(label);
		 pannello.add(id);
}
	protected JTextField getIdTabella(){
		return id;
	}
	String getNomeTabella() {
	      return nomeTabellaFrame;
	}

	void setNomeTabella(String n) {
	      this.nomeTabellaFrame = n;
	}
	
	TableModel getModelloTabella() {
	      return modelloTabellaFrame;
	}

	void setModelloTabella(TableModel t) {
	      this.modelloTabellaFrame = t;
	}

	String getQuery() {
	      return query;
	}

	void setQuery(String q) {
	      this.query = q;
	}
	
	protected void setFrameTable(JTable t) {
	      this.tabellaFrame = t;
	      modelloTabellaFrame = new TableModel(this);
	      tabellaFrame.setModel(modelloTabellaFrame);
	      tabellaFrame.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	      tabellaFrame.addMouseListener(new java.awt.event.MouseAdapter() {
	    	  									public void mouseReleased(java.awt.event.MouseEvent evt) {
	    	  									tabellaCambiata();
	    	  								}
	      								});
	      tabellaFrame.getSelectionModel().addListSelectionListener(
	              	new javax.swing.event.ListSelectionListener() {
	              			public void valueChanged(
	                        javax.swing.event.ListSelectionEvent e) {
	              				tabellaCambiata();
	              			}
	              	});
	}
    
    private void tabellaCambiata() {
        try {
           rs.absolute(
                   tabellaFrame.getSelectionModel().getMinSelectionIndex() + 1);
          
        } catch (SQLException e) {
           System.out.println(e.getMessage());
        } catch (Exception a) {
            System.out.println(a.getMessage());

        }
     }
    
	protected void selectStatement() {
		setQuery( "SELECT * FROM " + ConnessioneDB.schema + "." + nomeTabellaFrame + " ");
	}
	
	private void eseguiQuery() {
			Connection conn=null;
			PreparedStatement ps;
			try{
				selectStatement();
				conn=ConnessioneDB.getConnection();
				ps=conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = ps.executeQuery();
				modelloTabellaFrame.setRS(rs);
			}catch (SQLException e) {
	    	  System.out.println(e.getMessage());
	    	  System.out.println("errore in eseguiQuery di Frame");
	       } 
	}
	
	protected void pulisci() {
	      id.setText("");
	   }
	      
    protected void chiudiButtonActionPerformed(ActionEvent evt) {
		dispose();		
	}

	protected void apriButtonActionPerformed(ActionEvent evt) {
		
	}

	protected void eliminaButtonActionPerformed(ActionEvent evt) {
			Connection conn=null;
			String comando;
		 	comando = "DELETE FROM " + ConnessioneDB.schema + "." + nomeTabellaFrame + " WHERE "+modelloTabellaFrame.getColumnName(0) +" = ?";
		 	
		 	try {
	          
	           conn = ConnessioneDB.getConnection();
	           PreparedStatement ps = conn.prepareStatement(comando);
	           ps.setInt(1, Integer.decode(id.getText()));
	           ps.executeUpdate();
	       }catch (SQLException e) {
	    	   System.out.println(e.getMessage());
	        }
		 pulisci();
		 eseguiQuery();
	}

	protected void salvaButtonActionPerformed(ActionEvent evt) {
		Connection conn=null;
		String comando;
	 	comando = "COMMIT";
	 	
	 	try {
          
           conn = ConnessioneDB.getConnection();
           PreparedStatement ps = conn.prepareStatement(comando);
           ps.executeUpdate();
       }catch (SQLException e) {
    	   System.out.println(e.getMessage());
        }
	 pulisci();
	 eseguiQuery();
	 System.out.println("Commit eseguito.");
		
	}

	private void inserisciButtonActionPerformed(ActionEvent evt) {
		 	
		   PreparedStatement ps;
	       Connection conn = null;
	       try {
	          conn = ConnessioneDB.getConnection();
	          ps = getComandoInserimento(conn);
	          ps.executeUpdate();
	         
	       }catch (SQLException e) {
	           System.out.println(e.getMessage());
	           System.out.println("errore in inserisci BUTTON ACTION PERFORM");
	        }
	       pulisci();
	       eseguiQuery();
	       
	}
	
	private void modificaButtonActionPerformed(ActionEvent evt) {
		
		
		PreparedStatement ps;
	       Connection conn = null;
	       
	       try {
	          conn = ConnessioneDB.getConnection();
	          ps = getComandoModifica(conn);
	          ps.executeUpdate();
	         
	       }catch (SQLException e) {
	             System.out.println("errore in modifica BUTTON ACTION PERFORM");
	             System.out.println(e.getMessage());
	        }
	       pulisci();
	       eseguiQuery();	
	}
	
	private void cercaButtonActionPerformed(ActionEvent evt) {
		pulisci();
		eseguiQuery();		
	}
}








