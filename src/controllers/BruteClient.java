package controllers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

//import javax.swing.JComboBox;

/**
 * JOptionPane code adapted from 
 * http://www.java2s.com/Tutorial/Java/0240__Swing/Todisplaysadialogwithalistofchoicesinadropdownlistbox.htm
 * 
 * Decimal format adapted from 
 * http://stackoverflow.com/questions/16098046/how-to-print-double-value-without-scientific-notation-using-java
 * 
 * JTextField adpted from 
 * http://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog
 */
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class BruteClient
{
	String path = "././data/terms.txt";
	String testpath = "././data/termsTest.txt";
	BruteAutocomplete brute;
	DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));


	public static void main(String[] args)
	{
		BruteClient bruteClient = new BruteClient();

		bruteClient.run();
	}

	public BruteClient()
	{
		brute = new BruteAutocomplete(path);
		df.setMaximumFractionDigits(340);
	}

	public void run()
	{	
		JOptionPane.showMessageDialog(null, "Welcome to the BruteAutocomplete Client. \nClick OK to continue.", "Welcome", JOptionPane.PLAIN_MESSAGE);

		String[] choices = { "Weight of a term", "Best matching term", "List of matching terms"};

		String input = (String) JOptionPane.showInputDialog(null, "Operation modes",
				"Please select an option", JOptionPane.QUESTION_MESSAGE, null, // Use default icon
				choices, // Array of choices
				choices[0]); // Initial choice

		if (input != null) 
		{
  		switch(input)
  		{
  		
  		case "Weight of a term":
  			String input1 = JOptionPane.showInputDialog(null, "Enter term");
  			JOptionPane.showMessageDialog ( 
  					null, "The weight is " + df.format(brute.weightOf(input1)) + "\n\nNote: Term not found if weight 0.0", "Results", 
  					JOptionPane.PLAIN_MESSAGE); 
  			break;
  
  			
  			
  		case "Best matching term":
  			String input2 = JOptionPane.showInputDialog(null, "Enter prefix");
  			JOptionPane.showMessageDialog ( 
  					null, "Best match: \n" + brute.bestMatch(input2) + "\n\nNote: Term not found if null", "Results", 
  					JOptionPane.PLAIN_MESSAGE); 
  			break;
  
  			
  			
  		case "List of matching terms":
  
  			JTextField term = new JTextField();
  			JTextField k = new JTextField();
  			Object[] message = {
  					"Enter prefix:", term,
  					"Num of results:", k
  			};
  			JOptionPane.showConfirmDialog(null, message);
  
  			JOptionPane.showMessageDialog ( 
  					null, "List of matches: \n" + brute.matches(term.getText(), Integer.parseInt(k.getText())) + "\n\nNote: Term(s) not found if null or []", "Results", 
  					JOptionPane.PLAIN_MESSAGE); 
  			break;
  
  		}
		}
		System.exit(0);
	}
}



