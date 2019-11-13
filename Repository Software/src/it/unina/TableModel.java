package it.unina;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

	private ResultSet rs;
	private Frame frame;
	private JFrame jframe;
	
	public TableModel(Frame f) {
		this.frame=f;
	}
	
	public TableModel(JFrame f) {
		this.jframe=f;
	}
	
	
	public void setRS(ResultSet r) {
	      this.rs = r;
	      fireTableStructureChanged();
	}
	
	public String getColumnName(int c) {
	      c++;
	      if (rs == null) {
	         return "";
	      }
	      try {
	         return rs.getMetaData().getColumnName(c);
	      } catch (SQLException e) {
	           System.out.println(e.getMessage());
	    	  System.out.println("errore in table model getColumn Name");
	    	  return "";
	      }
	   }
	
	  
	
	public int getColumnCount() {
		if (rs == null) {
	         return 0;
	    }try{
	         return rs.getMetaData().getColumnCount();
	        }catch (SQLException e) {
	            System.out.println(e.getMessage());
	            System.out.println("errore in table model getColumnc ");
	        	return 0;
	         }
	}

	
	public int getRowCount() {
		   if (rs == null) {
			   System.out.println("rs == null!");
		         return 0;
		   }try {
		         int posizione;
		         int ultimaPosizione;
		         posizione = rs.getRow();
		         rs.last();
		         ultimaPosizione = rs.getRow();
		         if (posizione == 0) rs.first();
		         else rs.absolute(posizione);
		         return ultimaPosizione;
		    }catch (SQLException e) {
		           System.out.println(e.getMessage());
		           System.out.println("errore in table model getRowCount ");
		         return 0;
		      }
		
	}

	
	public Object getValueAt(int r, int c) {
		int posizione;
		Object obj;
		r++;
		c++;
		try{
			posizione=rs.getRow();
			rs.absolute(r);
			obj=rs.getObject(c);
			rs.absolute(posizione);
			return obj;		
		}catch(SQLException e){
	           System.out.println(e.getMessage());
	           System.out.println("errore in table model getValue");
			return null;
		 }	
	}

}
