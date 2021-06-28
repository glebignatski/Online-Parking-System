package parking_officer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import payment.Order;

public class SignInParkingOfficer {
	private boolean logged_in = false;

	public boolean sign_in(String email_address, String password) {
		if (logged_in == false) {
			Database db = new Database();
			if (db.loginVerification(email_address, password)) {
				logged_in = true;
				return true;
			}
			else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void signOut() {
		logged_in = false;
		System.out.println("Thank you for using our services!");
	}
	public String viewAllOrders() {
		if(logged_in == true) {
			Order o = new Order();
			String data = "";
			data = o.viewAllOrders();
			return data;
		}
		return "error";
	}
	public String viewCustomerEmails() {
		if(logged_in == true) {
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
			myReader.close();
			Scanner myReader2 = null;
			try {
				myReader2 = new Scanner(myObj);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String[] content = new String[num_of_lines];
			int i = 0;
			while (myReader2.hasNextLine()) {
				content[i] = myReader2.nextLine();
				i++;
			}
			myReader2.close();
			
			int index = -1;
			
			String email_list = "";
			int index2 = 1;
			for (i = 2; i < num_of_lines; i+=5) {
				email_list = email_list + index2 + ". " + content[i] + "\n";
				index2++;
			}
			return email_list;
		}
		return "error";
	}
}
