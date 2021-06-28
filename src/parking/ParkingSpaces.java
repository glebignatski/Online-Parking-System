package parking;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ParkingSpaces {
	
	public ParkingSpaces(){
	}
	
	public String book_space(int id, TimeBooked t, String plate_number) {
		if(checkSpaceAvailability(id) == -2) {
			return "Sorry, but the space you are trying to book does not exist!";
		}
		else if(checkSpaceAvailability(id) == -1) {
			return "Sorry, but the space is already booked!";
		}
		else {
			int index = -1;
			index = checkSpaceAvailability(id);
			String booking_id = "";
			booking_id = reserve_space(id, index, t);
			return booking_id;
		}
	}
	public String makeSpaceVacant(String booking_id) {
		int indexV = -1;
		if(booking_id.length() == 4) {
			indexV = getIndexOfParkingID(booking_id);
		}
		else {
			indexV = getIndexOfBookingID(booking_id);
		}
		String location = "ParkingSpaces.txt";
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
		content[indexV] = "0";
		write("ParkingSpaces.txt", "");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("ParkingSpaces.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		} //Set true for append mode
	    PrintWriter printWriter = new PrintWriter(fileWriter);
		for(i = 0; i < num_of_lines; i++) {
			printWriter.println(content[i]);
		}
		printWriter.close();
		return booking_id;	
	}
	public String makeSpaceUnavailable(String booking_id) {
		int indexV = -1;
		indexV = getIndexOfBookingID(booking_id);
		String location = "ParkingSpaces.txt";
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
		content[indexV] = "Unavailable";
		write("ParkingSpaces.txt", "");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("ParkingSpaces.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		} //Set true for append mode
	    PrintWriter printWriter = new PrintWriter(fileWriter);
		for(i = 0; i < num_of_lines; i++) {
			printWriter.println(content[i]);
		}
		printWriter.close();
		return booking_id;	
	}
	
	public String reserve_space(int id, int index, TimeBooked t) {
		String location = "ParkingSpaces.txt";
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
		String booking_id = "";
		booking_id = getID(id, t);
		content[index] = booking_id;
		write("ParkingSpaces.txt", "");
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("ParkingSpaces.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		} //Set true for append mode
	    PrintWriter printWriter = new PrintWriter(fileWriter);
		for(i = 0; i < num_of_lines; i++) {
			printWriter.println(content[i]);
		}
		printWriter.close();
		return booking_id;		
	}
	public String getAllAvailable() {
		String location = "ParkingSpaces.txt";
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
		
		String available = "";
		int j = 1;
		int row_i = 1;
		for(j = 1; j < content.length; j+=2) {
			if(content[j].equals("0")) {
				if(row_i % 4 != 0) {
					available = available + row_i + ". " + content[j-1] + "   ";
				}
				else if(row_i % 4 == 0) {
					available = available + row_i + ". " + content[j-1] + "\n";
				}
				row_i++;
			}
		}
		return available;
	}
	public String[] getListOfAvailable() {
		String location = "ParkingSpaces.txt";
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
		int number_of_available = 0;
		for(i = 1; i < content.length; i+=2) {
			if(content[i].equals("0")) {
				number_of_available++;
			}
		}
		String[] good_array = new String[number_of_available];
		int j = 0;
		int len = 0;
		if(content.length > 50) {
			len = 50;
		}
		else {
			len = content.length;
		}
		for(i = 0; i < len; i+=2) {
			if(content[i+1].equals("0")) {
				good_array[j] = "" + content[i];
				j++;
			}
		}
		return good_array;	
	}
	public int checkSpaceAvailability(int id) {
		String location = "ParkingSpaces.txt";
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
		String str_id = Integer.toString(id);
		num_of_users = num_of_lines / 2;
		int index = 0;
		for (i = 0; i < num_of_users; i++) {
			if (content[index].equals(str_id)) {
				check = true;
				break;
			}
			index = index + 2;
		}
		if(check == false) {
			return -2;
		}
		else {
			if(content[index+1].equals("0")) {
				return index+1;
			}
			else {
				return -1;
			}
		}
	}
	public String cancel_space(String booking_id) {
		String v = "";
		v = matchBookingID(booking_id);
		if(!v.equals("Does not exist")) {
			makeSpaceVacant(booking_id);
			return v;
		}
		else {
			return "Does not exist";
		}
	}
	private String matchBookingID(String booking_id) {
		String location = "ParkingSpaces.txt";
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
		//String str_id = Integer.toString(id);
		num_of_users = num_of_lines / 2;
		int index = 1;
		for (i = 0; i < num_of_users; i++) {
			if (content[index].equals(booking_id)) {
				check = true;
				break;
			}
			index = index + 2;
		}
		if(check == false) {
			return "Does not exist";
		}
		else {
			return content[index];
		}
	}
	private int getIndexOfBookingID(String booking_id) {
		String location = "ParkingSpaces.txt";
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
		//String str_id = Integer.toString(id);
		num_of_users = num_of_lines / 2;
		int index = 1;
		for (i = 0; i < num_of_users; i++) {
			if (content[index].equals(booking_id)) {
				check = true;
				break;
			}
			index = index + 2;
		}
		if(check == false) {
			return -1;
		}
		else {
			return index;
		}
	}
	private int getIndexOfParkingID(String parking_id) {
		String location = "ParkingSpaces.txt";
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
		//int num_of_users = 0;
		//String str_id = Integer.toString(id);
		//num_of_users = num_of_lines / 2;
		int index = -1;
		for (i = 0; i < num_of_lines; i+=2) {
			if (content[i].equals(parking_id)) {
				index = i;
				break;
			}
		}
		if(index < 0) {
			return -1;
		}
		else {
			return index+1;
		}
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
	public String getID(int id, TimeBooked t) {
		return "" + id + t.hashCode() + "";
	}
}