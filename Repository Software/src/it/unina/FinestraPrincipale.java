package it.unina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;


public class FinestraPrincipale extends JFrame {
	 
		private JButton esciButton;
	  
	    private JMenuBar menuBar;
	    
	    private JMenu menuFile;
	    private JMenu menuTabelle;
	    
	    private JMenuItem esci;
	    private JMenuItem progetti;
	    private JMenuItem sviluppatori;
	    private JMenuItem release;
	    private JMenuItem package_;
	    private JMenuItem classi;
	    private JMenuItem metodi;
	    private JMenuItem attributi;
	    private JMenuItem commit;
	    private JMenuItem test;
	    private JMenuItem sviluppatoriAssegnati;
	    private JMenuItem testEseguiti;
	    private JLabel label;
	    private JLabel label1;
	    
	    private JTable tabella;
	    private TableModel modelloTabella;
	    private JScrollPane spTabella;
	    private ResultSet rs;
	    
	    public FinestraPrincipale(){
	    	inizializzaComponentiGrafiche();
	    }
	    
	     private void inizializzaComponentiGrafiche(){
	    	 
	         menuBar = new JMenuBar();
	         
	         menuFile = new JMenu();
	         menuTabelle = new JMenu();
	         
	         esci = new JMenuItem();
	         progetti = new JMenuItem();
	         sviluppatori = new JMenuItem();
	         release = new JMenuItem();
	         package_ = new JMenuItem();
	         classi = new JMenuItem();
	         metodi = new JMenuItem();
	         attributi = new JMenuItem();
	         commit = new JMenuItem();
	         test = new JMenuItem();
	         sviluppatoriAssegnati = new JMenuItem();
	         testEseguiti = new JMenuItem();
	         
	         tabella = new JTable();
	         spTabella =new JScrollPane(tabella);
	         modelloTabella=new TableModel(this);
	         
	         tabella.setModel( modelloTabella);
	         tabella.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	         tabella.addMouseListener(new java.awt.event.MouseAdapter() {

	             
	             public void mouseReleased(java.awt.event.MouseEvent evt) {
	                      tabellaCambiata();
	             }
	          });

	          tabella.getSelectionModel().addListSelectionListener(
	                  new javax.swing.event.ListSelectionListener() {

	                     
	                     public void valueChanged(
	                             javax.swing.event.ListSelectionEvent e) {
	                        tabellaCambiata();
	                     }
	                  });
	         spTabella.setViewportView(tabella);
	         
	         label=new JLabel("Per visualizzare in dettaglio tutti gli elementi del repository usare il menu TABELLE");
	         label1=new JLabel("PROGETTI PRESENTI NEL REPOSITORY");
	         
	         esciButton = new javax.swing.JButton("ESCI");
	         
	         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	         setTitle("REPOSITORY SOFTWARE");
	         setLocation(new java.awt.Point(440, 150));
	         setResizable(true);
	         getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	         
	         menuBar.add(menuFile);
	         menuBar.add(menuTabelle);
	         
	         menuFile.setText("File");
	         menuTabelle.setText("Tabelle");
	         esci.setText("Esci"); 
	         esci.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent evt) {
	                 esciActionPerformed(evt);
	             }
	         });
	         
	         progetti.setText("Progetti");  
	         progetti.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 progettiActionPerformed(evt);
	             }
	         });
	         
	         sviluppatori.setText("Sviluppatori"); 
	         sviluppatori.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 sviluppatoriActionPerformed(evt);
	             }
	         });
	         
	         release.setText("Release"); 
	         release.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 releaseActionPerformed(evt);
	             }
	         });
	         
	         package_.setText("Package"); 
	         package_.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 package_ActionPerformed(evt);
	             }
	         });
	         
	         classi.setText("Classi");  
	         classi.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 classiActionPerformed(evt);
	             }
	         });
	         
	         metodi.setText("Metodi");  
	         metodi.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 metodiActionPerformed(evt);
	             }
	         });
	         
	         attributi.setText("Attributi");  
	         attributi.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 attributiActionPerformed(evt);
	             }
	         });
	         
	         commit.setText("Commit");  
	         commit.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 commitActionPerformed(evt);
	             }
	         });
	         
	         test.setText("Test");  
	         test.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 testActionPerformed(evt);
	             }
	         });
	         
	         sviluppatoriAssegnati.setText("Sviluppatori e Progetti");  
	         sviluppatoriAssegnati.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 sviluppatoriAssegnatiActionPerformed(evt);
	             }
	         });
	         
	         testEseguiti.setText("Test Eseguiti");  
	         testEseguiti.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 testEseguitiActionPerformed(evt);
	             }
	         });
	         
	         menuFile.add(esci);
	         
	         menuTabelle.add(progetti);
	         menuTabelle.add(sviluppatori);
	         menuTabelle.add(release);
	         menuTabelle.add(package_);
	         menuTabelle.add(classi);
	         menuTabelle.add(metodi);
	         menuTabelle.add(attributi);
	         menuTabelle.add(commit);
	         menuTabelle.add(test);
	         menuTabelle.add(sviluppatoriAssegnati);
	         menuTabelle.add(testEseguiti);
	         
	         setJMenuBar(menuBar);
	        
	         esciButton.addActionListener(new java.awt.event.ActionListener() {
	             public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	 esciButtonActionPerformed(evt);
	             }
	         });
	         
	         getContentPane().add(label1);
	         getContentPane().add(spTabella);
	         getContentPane().add(label);
	         getContentPane().add(esciButton);
	         spTabella.setEnabled(true);
	         tabella.setEnabled(true);
	         setVisible(true);
	         pack();

	     }
	     
	     private void visualizzaTabella(){
	    	 
	         Connection conn;
	         PreparedStatement ps;
	         String query = "SELECT * FROM PROGETTO";
	         try{
	            conn=ConnessioneDB.getConnection();
	            ps=conn.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	            rs=ps.executeQuery();
	            modelloTabella.setRS(rs);
	           
	            }catch (SQLException e) {
	                System.out.println(e.getMessage());
	                System.out.println("errore in creaSelectStatemet in visualiz tabella");
	             }
	     }
	     
	     private void esciActionPerformed(java.awt.event.ActionEvent evt) {
	         dispose();
	     }
	     
	     private void progettiActionPerformed(java.awt.event.ActionEvent evt) {
	         Progetto p;
	         p= new Progetto();
	         p.setVisible(true);
	     }
	     
	     private void sviluppatoriActionPerformed(java.awt.event.ActionEvent evt) {
	         Sviluppatore s;
	         s=new Sviluppatore();
	         s.setVisible(true);
	    }
	     
	     private void releaseActionPerformed(java.awt.event.ActionEvent evt) {
	    	 Release r;
	    	 r=new Release();
	    	 r.setVisible(true);
	     }
	     
	     private void package_ActionPerformed(java.awt.event.ActionEvent evt) {
	    	 Package p;
	    	 p=new Package();
	    	 p.setVisible(true);
	     }
	     
	     private void classiActionPerformed(java.awt.event.ActionEvent evt) {
	    	 Classe c;
	    	 c=new Classe();
	    	 c.setVisible(true);
	     }
	     
	     private void metodiActionPerformed(java.awt.event.ActionEvent evt) {
	    	 Metodo m;
	    	 m=new Metodo();
	    	 m.setVisible(true);
	     }
	     
	     private void attributiActionPerformed(java.awt.event.ActionEvent evt) {
	    	 Attributo a;
	    	 a = new Attributo();
	    	 a.setVisible(true);
	     }
	     
	     private void commitActionPerformed(java.awt.event.ActionEvent evt) {
	         Commit c;
	         c=new Commit();
	         c.setVisible(true);
	     }
	     
	     private void testActionPerformed(java.awt.event.ActionEvent evt) {
	         Test t;
	         t = new Test();
	         t.setVisible(true);
	     }
	     
	     private void sviluppatoriAssegnatiActionPerformed(java.awt.event.ActionEvent evt) {
	         AssegnazioneProgetto as;
	         as = new AssegnazioneProgetto();
	         as.setVisible(true);
	     }
	     
	     private void testEseguitiActionPerformed(java.awt.event.ActionEvent evt) {
	    	 TestEseguito ts;
	    	 ts = new TestEseguito();
	    	 ts.setVisible(true);
	     }
	     
	     private void esciButtonActionPerformed(java.awt.event.ActionEvent evt) {
	         dispose();
	     }
	     
	     private void tabellaCambiata() {
	         try {
	            rs.absolute(
	                    tabella.getSelectionModel().getMinSelectionIndex() + 1);
	         } catch (SQLException e) {
	             System.out.println(e.getMessage());

	         } catch (Exception a) {
	             System.out.println(a.getMessage());

	         }
	      }
	    
	     public static void creaFinestra(){
			 java.awt.EventQueue.invokeLater(new Runnable() {
		         public void run() {
		            FinestraPrincipale finestra = new FinestraPrincipale();
		            finestra.visualizzaTabella();
		         }
		      });
		}
}
