package system;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Database {

	public boolean loginVerification(String email_address, String password) {
		String location = "Database_admins.txt";
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
		boolean check = false;
		int num_of_users = 0;
		num_of_users = num_of_lines/5;
		int index_e = 3;
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
