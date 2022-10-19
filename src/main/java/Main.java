

import java.awt.EventQueue;

import vue.Interface;

public class Main {
	/**
	 * Lancer l'application
	 */

	public static void main(String[] args) {
	   EventQueue.invokeLater(new Runnable() {
	      @Override
	      public void run() {
	            try {
	            	Interface gui = new Interface();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	      }
	    });
		
	}
}
