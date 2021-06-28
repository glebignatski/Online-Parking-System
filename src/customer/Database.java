package customer;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Database {
	
	public static String[] getUserData() {
		String location = "Database_customers.txt";
		int num_of_lines = 0;
		File myObj = new File(location);
		Scanner myReader = null;
		try {
			myReader = new Scanner(myObj);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String data = "";
		while (myReader.hasNextLine()) {
			data = data + myReader.nextLine();
			num_of_lines++;
			if (myReader.hasNextLine()) {
				data = data + "\n";
			}
		}
		// data = data + "";
		myReader.close();
		System.out.print(data);
		System.out.println("\nThere are " + num_of_lines + " lines in the Database.txt file.");
		Scanner myReader2 = null;
		try {
			myReader2 = new Scanner(myObj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String [] content = new String[num_of_lines];
		int i = 0;
		while (myReader2.hasNextLine()) {
			content[i] = myReader2.nextLine();
			i++;
		}
		myReader2.close();
		return content;
	}
	public void addUserData(String f_name, String l_name, String email_address, String date_of_birth, String password) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("Database_customers.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		} //Set true for append mode
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.println(f_name);
	    printWriter.println(l_name);
	    printWriter.println(email_address);
	    printWriter.println(date_of_birth.toString());
	    printWriter.println(password);
	    printWriter.close();
	}
	public boolean checkEmailUniqeness(String email_address) {
		String location = "Database_customers.txt";
		int num_of_lines = 0;
		File myObj = new File(location);
		Scanner myReader = null;
		try {
			myReader = new Scanner(myObj);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String data = "";
		while (myReader.hasNextLine()) {
			data = data + myReader.nextLine();
			num_of_lines++;
			if (myReader.hasNextLine()) {
				data = data + "\n";
			}
		}
		// data = data + "";
		myReader.close();
		//System.out.print(data);
		//System.out.println("\nThere are " + num_of_lines + " lines in the Database.txt file.");
		Scanner myReader2 = null;
		try {
			myReader2 = new Scanner(myObj);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String [] content = new String[num_of_lines];
		int i = 0;
		while (myReader2.hasNextLine()) {
			content[i] = myReader2.nextLine();
			i++;
		}
		myReader2.close();
		boolean check = true;
		int num_of_users = 0;
		num_of_users = num_of_lines/5;
		int index = 2;
		for(i = 0; i < num_of_users; i++) {
			if(content[index].equals(email_address)) {
				check = false;
				break;
			}
			index = index + 5;
		}
		if(check == false) {
			System.out.println("Sorry, but a user of the Parking System is already registered with this email address: " + email_address);
		}
		return check;
	}
	public boolean loginVerification(String email_address, String password) {
		String location = "Database_customers.txt";
		int num_of_lines = 0;
		File myObj = new File(location);
		Scanner myReader = null;
		try {
			myReader = new Scanner(myObj);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String data = "";
		while (myReader.hasNextLine()) {
			data = data + myReader.nextLine();
			num_of_lines++;
			if (myReader.hasNextLine()) {
				data = data + "\n";
			}
		}
		// data = data + "";
		myReader.close();
		//System.out.print(data);
		//System.out.println("\nThere are " + num_of_lines + " lines in the Database.txt file.");
		Scanner myReader2 = null;
		try {
			myReader2 = new Scanner(myObj);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String [] content = new String[num_of_lines];
		int i = 0;
		while (myReader2.hasNextLine()) {
			content[i] = myReader2.nextLine();
			i++;
		}
		myReader2.close();
		boolean check = false;
		int num_of_users = 0;
		num_of_users = num_of_lines/5;
		int index_e = 2;
		int index_p = 4;
		for(i = 0; i < num_of_users; i++) {
			if(content[index_e].equals(email_address) && content[index_p].equals(password)) {
				check = true;
				break;
			}
			index_e = index_e + 5;
			index_p = index_p + 5;
		}
		if(check == false) {
			System.out.println("Sorry, but there is an error with the email or password you entered.\nPlease try again.");
		}
		return check;
	}
}