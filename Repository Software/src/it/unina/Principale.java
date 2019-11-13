package it.unina;

import java.awt.Dimension;

public class Principale {
	static public final Dimension DIM_JDIALOG=new Dimension(350,150);
	
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
							FinestraLogin.creaFinestraLogin();
						}
				}
		);
	}
}
