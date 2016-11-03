/**
 * @author Mathana Sreedaran
 * 
 * BruteClient acts as a user client to interact with the BruteAutocomplete class. 
 * 
 * JOptionPane code adapted from 
 * http://www.java2s.com/Tutorial/Java/0240__Swing/Todisplaysadialogwithalistofchoicesinadropdownlistbox.htm
 * 
 * Decimal format adapted from 
 * http://stackoverflow.com/questions/16098046/how-to-print-double-value-without-scientific-notation-using-java
 * 
 * JTextField adpted from 
 * http://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog
 */
package controllers;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.google.common.base.Preconditions;

import edu.princeton.cs.introcs.Stopwatch;



public class BruteClient
{
	String path = "././data/terms.txt"; //Path to file containing data
	//String testpath = "././data/termsTest.txt";
	BruteAutocomplete brute; //Object of BruteAutocomplete class
	DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH)); //Formating decimal to prevent scientific notation


	public static void main(String[] args) throws Exception
	{
		BruteClient bruteClient = new BruteClient();

		bruteClient.run();
	}

	public BruteClient() throws Exception
	{
		brute = new BruteAutocomplete(path);
		df.setMaximumFractionDigits(340); //Preventing scientific notation 
	}

	public void run()
	{	
		//Welcome dialog box
		JOptionPane.showMessageDialog(null, "Welcome to the BruteAutocomplete Client. \nClick OK to continue.", "Welcome", JOptionPane.PLAIN_MESSAGE);
		
		//List of choices (methods)
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
				String input1 = JOptionPane.showInputDialog(null, "Enter term"); //Prompt input

				Stopwatch stopwatch = new Stopwatch(); //Initialise timer
				double weightOf = brute.weightOf(input1); //Do method
				double time = stopwatch.elapsedTime(); //Measure time
				System.out.println("Brute - weightOf - " + time); //Output time to console

				//Output dialog box
				JOptionPane.showMessageDialog ( 
						null, "The weight is " + df.format(weightOf) + "\n\nNote: Term not found if weight 0.0", "Results", 
						JOptionPane.PLAIN_MESSAGE); 

				break;



			case "Best matching term":
				String input2 = JOptionPane.showInputDialog(null, "Enter prefix"); //Prompt input
				
				Stopwatch stopwatch2 = new Stopwatch(); //Start timer
				String bestMatch = brute.bestMatch(input2); //Do method
				double time2 = stopwatch2.elapsedTime(); //Measure time
				System.out.println("Brute - bestMatch - " + time2); //Print to console
				
				JOptionPane.showMessageDialog ( 
						null, "Best match: \n" + bestMatch + "\n\nNote: Term not found if null", "Results", 
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

				Preconditions.checkArgument(k.getText().length() > 0, "Null value of k");
				
				Stopwatch stopwatch3 = new Stopwatch();
				Iterable<String> matches = brute.matches(term.getText(), Integer.parseInt(k.getText()));
				double time3 = stopwatch3.elapsedTime(); 
				System.out.println("Brute - matches - " + time3);
				
				JOptionPane.showMessageDialog ( 
						null, "List of matches: \n" + matches + "\n\nNote: Term(s) not found if null or []", "Results", 
						JOptionPane.PLAIN_MESSAGE); 
				break;

			}
		}
		System.exit(0);
	}
}



