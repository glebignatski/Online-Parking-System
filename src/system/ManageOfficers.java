package system;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ManageOfficers {

	private String f_name;
	private String l_name;
	private String email;
	private String username;
	private String password;

	public ManageOfficers() {
	}

	public boolean addOfficer(String f_name, String l_name, String email, String password) {
		this.f_name = f_name;
		this.l_name = l_name;
		this.email = email;
		if (checkEmailUniqeness(email) == false) {
			if (password != "" && password != null) {
				this.password = password;
				String receive = "";
				String fn = "OfficerID.txt";
				receive = read(fn);
				this.username = "" + this.f_name.substring(0, 1).toLowerCase()
						+ this.l_name.substring(0, 1).toLowerCase() + receive;
				int id = 0;
				id = Integer.parseInt(receive);
				id++;
				write(fn, id + "");
				addOfficerData(this.f_name, this.l_name, this.email, this.username, this.password);
				String fn2 = "OfficerID.txt";
				String currentCount = read(fn2);
				int num = Integer.parseInt(currentCount);
				num++;
				write(fn2, num + "");
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean removeOfficer(String username) {
		if (checkUserNameExists(username)) {
			String location = "Database_officers.txt";
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
			for (i = 3; i < num_of_lines; i += 5) {
				if (content[i].equals(username)) {
					index = i;
					break;
				}
			}
			if (index >= 0) {
				int lowerbound = 0;
				int upperbound = 0;
				lowerbound = index - 3;
				upperbound = index + 1;
				write(location, "");
				FileWriter fileWriter = null;
				try {
					fileWriter = new FileWriter("Database_officers.txt", true);
				} catch (IOException e) {
					e.printStackTrace();
				} // Set true for append mode
				PrintWriter printWriter = new PrintWriter(fileWriter);
				for (i = 0; i < num_of_lines; i++) {
					if (i < lowerbound || i > upperbound) {
						printWriter.println(content[i]);
					}
				}
				printWriter.close();
				String fn2 = "OfficerCount.txt";
				String currentCount = read(fn2);
				int num = Integer.parseInt(currentCount);
				num--;
				write(fn2, num + "");
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean checkUserNameExists(String username) {
		String location = "Database_officers.txt";
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
		boolean check = false;
		int num_of_users = 0;
		num_of_users = num_of_lines / 5;
		int index = 3;
		for (i = 0; i < num_of_users; i++) {
			if (content[index].equals(username)) {
				check = true;
				break;
			}
			index = index + 5;
		}
		return check;
	}

	private void addOfficerData(String f_name, String l_name, String email_address, String username, String password) {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("Database_officers.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		} // Set true for append mode
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println(f_name);
		printWriter.println(l_name);
		printWriter.println(email_address);
		printWriter.println(username);
		printWriter.println(password);
		printWriter.close();
	}

	public boolean checkEmailUniqeness(String email_address) {
		String location = "Database_officers.txt";
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
		boolean check = false;
		int num_of_users = 0;
		num_of_users = num_of_lines / 5;
		int index = 2;
		for (i = 0; i < num_of_users; i++) {
			if (content[index].contains(email_address)) {
				check = true;
				break;
			}
			index = index + 5;
		}
		if (check == true) {
			System.out.println("Sorry, but a user of the Parking System is already registered with this email address: "
					+ email_address);
		}
		return check;
	}

	private static void write(String filename, String data) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			writer.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String read(String filename) {
		File myObj = new File(filename);
		Scanner myReader = null;
		try {
			myReader = new Scanner(myObj);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String data = "";
		while (myReader.hasNextLine()) {
			data = data + myReader.nextLine();
			if (myReader.hasNextLine()) {
				data = data + "\n";
			}
		}
		return data;
	}

	public int getID() {
		String id = "";
		id = read("OfficerCount.txt");
		return Integer.parseInt(id);
	}

	public int getNumberOfOfficers() {
		String fn2 = "OfficerCount.txt";
		String currentCount = "";
		currentCount = read(fn2);
		int num = 0;
		num = Integer.parseInt(currentCount);
		return num;
	}
}