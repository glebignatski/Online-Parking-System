package customer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Signup {
	private String f_name;
	private String l_name;
	private String email_address;
	private String date_of_birth;
	private String password;
	private double age;
	SignIn session;
	public Signup() {
		
	}
	public boolean register(String f_name, String l_name, String email_address, String date_of_birth, String password) {
		if(date_of_birth.length() < 10 || date_of_birth.length() > 10) {
			return false;
		}
		long currentDate = getCurrentDate();
		Database db = new Database();
		if(email_address.length() < 4) {
			return false;
		}
		boolean check = db.checkEmailUniqeness(email_address);
		double age = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date birthDate = null;
		try {
			birthDate = sdf.parse(date_of_birth);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		if(Integer.parseInt(date_of_birth.substring(3, 5)) == 1 || Integer.parseInt(date_of_birth.substring(3, 5)) == 3
				|| Integer.parseInt(date_of_birth.substring(3, 5)) == 5 || Integer.parseInt(date_of_birth.substring(3, 5)) == 7
				|| Integer.parseInt(date_of_birth.substring(3, 5)) == 8 || Integer.parseInt(date_of_birth.substring(3, 5)) == 10
				|| Integer.parseInt(date_of_birth.substring(3, 5)) == 12) {
			if(Integer.parseInt(date_of_birth.substring(0, 2)) < 1 || Integer.parseInt(date_of_birth.substring(0, 2)) > 31) {
				return false;
			}
		}
		else if(Integer.parseInt(date_of_birth.substring(3, 5)) == 4
				|| Integer.parseInt(date_of_birth.substring(3, 5)) == 5 || Integer.parseInt(date_of_birth.substring(3, 5)) == 7
				|| Integer.parseInt(date_of_birth.substring(3, 5)) == 8 || Integer.parseInt(date_of_birth.substring(3, 5)) == 10
				|| Integer.parseInt(date_of_birth.substring(3, 5)) == 12) {
			if(Integer.parseInt(date_of_birth.substring(0, 2)) < 1 || Integer.parseInt(date_of_birth.substring(0, 2)) > 30) {
				return false;
			}
		}
		// leap year
		else if(Integer.parseInt(date_of_birth.substring(3, 5)) == 2 && Integer.parseInt(date_of_birth.substring(6, 10)) % 4 == 0){
			if(Integer.parseInt(date_of_birth.substring(0, 2)) < 1 || Integer.parseInt(date_of_birth.substring(0, 2)) > 29) {
				return false;
			}
		}
		// not a leap year
		else if(Integer.parseInt(date_of_birth.substring(3, 5)) == 2 && Integer.parseInt(date_of_birth.substring(6, 10)) % 4 != 0){
			if(Integer.parseInt(date_of_birth.substring(0, 2)) < 1 || Integer.parseInt(date_of_birth.substring(0, 2)) > 28) {
				return false;
			}
		}
		if(birthDate.getDay() < 0 || birthDate.getDay() > 6) {
			return false;
		}
		if(birthDate.getMonth() < 0 || birthDate.getMonth() > 11) {
			return false;
		}
		age = calculateAge(birthDate.getTime(), currentDate);
		if (age >= 16 && check == true) {
			this.age = age;
			this.f_name = f_name;
			this.l_name = l_name;
			this.email_address = email_address;
			this.date_of_birth = date_of_birth;
			this.password = password;
			if(this.f_name.length() < 3) {
				return false;
			}
			if(this.l_name.length() < 3) {
				return false;
			}
			if(!(this.password.length() > 5 && this.password.length() < 17)) {
				return false;
			}
			db.addUserData(f_name, l_name, email_address, date_of_birth, password);
			String fn = "CustomerCount.txt";
			String currentCount = read(fn);
			int num = Integer.parseInt(currentCount);
			num++;
			write(fn, num+"");
			return true;
		} else {
			/*if(!(password.length() > 0 && password.length() < 17)) {
				System.out.println("The password must be between 1 and 16 characters inclusive.");
			}
			if(age < 16) {
				System.out.println("Sorry, but the minimum age to sign up as a user is 16.");
			}
			if(check == false) {
				System.out.println("Sorry, but this email address is already registered in the system. You can only"
						+ "have one account with us.");
			}
			*/
			return false;
		}
	}
	public boolean emailCheck(String email) {
		Database db = new Database();
		boolean check = false;
		check = db.checkEmailUniqeness(email);
		return check;
	}

	/*public void setAccountPassword(String password) {
		if (this.age >= 16 && password.length() > 0 && password.length() < 17) {
			this.password = password;
			Database db = new Database();
			if (db.checkEmailUniqeness(email_address) == false) {
				db.addUserData(f_name, l_name, email_address, date_of_birth, password);
				System.out.println("Thank you for completing the set up process!");
			}
		} else {
			System.out.println("Sorry, but you are not at least 16 years old to use the services. Additionally, you are not authorized to go further until you complete the sign up process.");
		}
	}*/

	/*private LocalDate getCurrentDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		int day = 0, month = 0, year = 0;
		Date dateobj = new Date();
		String dt = df.format(dateobj).toString();
		if (dt.substring(0, 1) == "0") {
			day = Integer.parseInt(dt.substring(1, 2));
		}
		if (dt.substring(0, 1) != "0") {
			day = Integer.parseInt(dt.substring(0, 2));
		}
		if (dt.substring(3, 4) == "0") {
			month = Integer.parseInt(dt.substring(4, 5));
		}
		if (dt.substring(3, 4) != "0") {
			month = Integer.parseInt(dt.substring(3, 5));
		}
		year = Integer.parseInt(dt.substring(6, 10));
		LocalDate currentDate = null;
		currentDate = LocalDate.of(year, month, day);
		return currentDate;
	}
	

	private static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthDate, currentDate).getYears();
		} else {
			return 0;
		}
	}
	*/
	public int getNumberOfCustomer() {
		String fn = "CustomerCount.txt";
		String currentCount = ""; 
		currentCount = read(fn); 
		int num = 0;
		num = Integer.parseInt(currentCount);
		return num;
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
	private long getCurrentDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		int day = 0, month = 0, year = 0;
		Date dateobj = new Date();
		String dt = df.format(dateobj).toString();
		if (dt.substring(0, 1) == "0") {
			day = Integer.parseInt(dt.substring(1, 2));
		}
		if (dt.substring(0, 1) != "0") {
			day = Integer.parseInt(dt.substring(0, 2));
		}
		if (dt.substring(3, 4) == "0") {
			month = Integer.parseInt(dt.substring(4, 5));
		}
		if (dt.substring(3, 4) != "0") {
			month = Integer.parseInt(dt.substring(3, 5));
		}
		year = Integer.parseInt(dt.substring(6, 10));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = null;
		String date = "";
		date = day + "/" + month + "/" + year;
		try {
			currentDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// LocalDate currentDate = null;
		// currentDate = LocalDate.of(year, month, day);
		// return currentDate;
		return currentDate.getTime();
	}
	private static double calculateAge(long birthDate, long currentDate) {
		/*
		 * if ((birthDate != null) && (currentDate != null)) { return
		 * Period.between(birthDate, currentDate).getYears(); } else { return 0; }
		 */
		long diffInMillies = Math.abs(currentDate - birthDate);
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		double diff_precise = 0.0;
		diff_precise = (double) diff / 365.25;
		return diff_precise;
	}
}