package payment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import parking.ParkingSpaces;
import parking.TimeBooked;

public class Order {
	/*private static int num_of_orders = 0;
	private static double total = 0.0;
	private static int[] arrPspaceID = new int[3];
	private static String[] arrBookingID = new String[3];
	private static String[] emailList = new String[3];
	private static double totalOnEachOrder[] = new double[3];
	private static TimeBooked times[] = new TimeBooked[3];
	*/

	public Order() {
	}

	public void addOrder(int id, TimeBooked t, String booking_id, String dt, String email) {
		String row = "";
		boolean check = true;
		double total = t.calculateTimeInterval()*1.8;
		row = "" + email + "," + booking_id + "," + dt + "," + t.returnStart() + "," + t.returnFinish() + ";" + total;
		String data_to_verify[] = getData("BookedSpaces.txt");
		for(int i = 0; i < data_to_verify.length; i++) {
			if(data_to_verify[i].equals(row)) {
				check = false;
				break;
			}
		}
		// does not exist
		if(check) {
			//write("BookedSpaces.txt", "");
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("BookedSpaces.txt", true);
			} catch (IOException e) {
				e.printStackTrace();
			} //Set true for append mode
		    PrintWriter printWriter = new PrintWriter(fileWriter);
			//for(int i = 0; i < data_to_verify.length; i++) {
			printWriter.println(row);
			//}
			printWriter.close();
		}
		updateTotal(email, total);
	}
	
	public void addRequest(int id, TimeBooked t, String dt, String email) {
		String row = "";
		boolean check = true;
		double total = t.calculateTimeInterval()*1.8;
		ParkingSpaces ps = new ParkingSpaces();
		String booking_id = ps.getID(id, t);
		row = "" + email + "," + booking_id + "," + dt + "," + t.returnStart() + "," + t.returnFinish() + ";" + total;
		String data_to_verify[] = getData("RequestedSpaces.txt");
		for(int i = 0; i < data_to_verify.length; i++) {
			if(data_to_verify[i].equals(row)) {
				check = false;
				break;
			}
		}
		// does not exist
		if(check) {
			//write("BookedSpaces.txt", "");
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("RequestedSpaces.txt", true);
			} catch (IOException e) {
				e.printStackTrace();
			} //Set true for append mode
		    PrintWriter printWriter = new PrintWriter(fileWriter);
			//for(int i = 0; i < data_to_verify.length; i++) {
			printWriter.println(row);
			//}
			printWriter.close();
		}
		//updateTotal(email, total);
	}
	
	private boolean updateTotal(String email, double total) {
		int index = checkExists(email);
		if(index > -1) {
			String [] contents = getData("Orders.txt");
			int index2 = contents[index].indexOf(",");
			index2 = index2 + 1;
			double amount_due = 0.0;
			amount_due = Double.parseDouble(contents[index].substring(index2));
			amount_due = amount_due + total;
			String updated_row = "";
			updated_row = updated_row + email + "," + amount_due;
			contents[index] = updated_row;
			
			write("Orders.txt", "");
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter("Orders.txt", true);
			} catch (IOException e) {
				e.printStackTrace();
			} //Set true for append mode
		    PrintWriter printWriter = new PrintWriter(fileWriter);
			for(int i = 0; i < contents.length; i++) {
				printWriter.println(contents[i]);
			}
			printWriter.close();
			return true;	
		}
		return false;
	}
	
	private int checkExists(String email) {
		String [] contents = getData("Orders.txt");
		int index = -1;
		for(int i = 0; i < contents.length; i++) {
			if(contents[i].contains(email)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	private int checkExistsBookingID(String id) {
		String [] contents = getData("BookedSpaces.txt");
		int index = -1;
		for(int i = 0; i < contents.length; i++) {
			if(contents[i].contains(id)) {
				index = i;
				break;
			}
		}
		return index;
	}
	public String viewRequests(String email) {
		String [] orders = getOrderInformation("RequestedSpaces.txt");
		String userBookedInfo = "";
		for(int i = 0; i < orders.length; i++) {
			if(orders[i].contains(email)) {
				int index2 = orders[i].indexOf(",");
				
				index2 = index2 + 1;
				int index3 = orders[i].indexOf(",", index2);
				String id = orders[i].substring(index2, index3);
				
				index3 = index3 + 1;
				int index4 = orders[i].indexOf(",", index3);
				String dt = orders[i].substring(index3, index4);
				
				index4 = index4 + 1;
				int index5 = orders[i].indexOf(",", index4);
				String start = orders[i].substring(index4, index5);
				
				index5 = index5 + 1;
				int index6 = orders[i].indexOf(";", index5);
				String finish = orders[i].substring(index5, index6);
				
				index6 = index6 + 1;
				String cost = orders[i].substring(index6);
				
				userBookedInfo = userBookedInfo + "PID: " + id + " Date: " + dt + " From: " + start + "  To: " + finish + "  Cost: $" + cost + "\n";
			}
		}
		if(!userBookedInfo.equals("")) {
			//userBookedInfo = userBookedInfo + "\nTotal: $" + getTotal(email);
		}
		else {
			userBookedInfo = "Nothing was requested!";
		}
		return userBookedInfo;
	}
	public int countNumberOfOrders(String email) {
		String [] data = getData("BookedSpaces.txt");
		int count_orders = 0;
		for(int i = 0; i < data.length; i++) {
			if(data[i].contains(email + ",")) {
				count_orders++;
			}
		}
		return count_orders;
	}
	public int countNumberOfRequests(String email) {
		String [] data = getData("RequestedSpaces.txt");
		int count_orders = 0;
		for(int i = 0; i < data.length; i++) {
			if(data[i].contains(email + ",")) {
				count_orders++;
			}
		}
		return count_orders;
	}
	
	private static String[] getData(String filename) {
		String location = filename;
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
		String [] content = new String[num_of_lines];
		int i = 0;
		while (myReader2.hasNextLine()) {
			content[i] = myReader2.nextLine();
			i++;
		}
		myReader2.close();
		return content;
	}
	
	public String[] getOrderInformation(String filename) {
		String location = filename;
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
		String [] content = new String[num_of_lines];
		int i = 0;
		while (myReader2.hasNextLine()) {
			content[i] = myReader2.nextLine();
			i++;
		}
		myReader2.close();
		return content;
	}
	public String viewOrders(String email) {
		String [] orders = getOrderInformation("BookedSpaces.txt");
		String userBookedInfo = "";
		int count = 0;
		for(int i = 0; i < orders.length; i++) {
			if(orders[i].contains(email)) {
				count++;
				int index2 = orders[i].indexOf(",");
				
				index2 = index2 + 1;
				int index3 = orders[i].indexOf(",", index2);
				String id = orders[i].substring(index2, index3);
				
				index3 = index3 + 1;
				int index4 = orders[i].indexOf(",", index3);
				String dt = orders[i].substring(index3, index4);
				
				index4 = index4 + 1;
				int index5 = orders[i].indexOf(",", index4);
				String start = orders[i].substring(index4, index5);
				
				index5 = index5 + 1;
				int index6 = orders[i].indexOf(";", index5);
				String finish = orders[i].substring(index5, index6);
				
				index6 = index6 + 1;
				String cost = orders[i].substring(index6);
				
				userBookedInfo = userBookedInfo + "PID: " + id + " Date: " + dt + " From: " + start + "  To: " + finish + "  Cost: $" + cost + "\n";
			}
		}
		userBookedInfo = userBookedInfo + "\nTotal number of spaces booked: " + count + "\nTotal due: $" + getTotal(email);
		return userBookedInfo;
	}
	public String viewAllOrders() {
		String [] orders = getOrderInformation("BookedSpaces.txt");
		String userBookedInfo = "";
		for(int i = 0; i < orders.length; i++) {
				int index2 = orders[i].indexOf(",");
				String email = orders[i].substring(0, index2);
				
				index2 = index2 + 1;
				int index3 = orders[i].indexOf(",", index2);
				String id = orders[i].substring(index2, index3);
				
				index3 = index3 + 1;
				int index4 = orders[i].indexOf(",", index3);
				String start = orders[i].substring(index3, index4);
				
				index4 = index4 + 1;
				int index5 = orders[i].indexOf(";", index4);
				String finish = orders[i].substring(index4, index5);
				
				index5 = index5 + 1;
				String cost = orders[i].substring(index5);
				
				userBookedInfo = userBookedInfo + (i+1) + ".  Email: " + email + "  ID: " + id + "  From: " + start + "  To: " + finish + "  Cost: $" + cost + "\n";
		}
		return userBookedInfo;
	}
	
	private void removeOrderedData(String id) {
		String location = "BookedSpaces.txt";
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

		for (i = 0; i < num_of_lines; i++) {
			if (content[i].contains(id)) {
				index = i;
				break;
			}
		}
		write("BookedSpaces.txt", "");

		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter("BookedSpaces.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter printWriter = new PrintWriter(fileWriter);
		for (i = 0; i < num_of_lines; i++) {
			if (i != index) {
				printWriter.println(content[i]);
			}
		}
		printWriter.close();
	}
	private void removeRequestedData(String id) {
		String location = "RequestedSpaces.txt";
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

		for (i = 0; i < num_of_lines; i++) {
			if (content[i].contains(id)) {
				index = i;
				break;
			}
		}
		write("RequestedSpaces.txt", "");

		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter("RequestedSpaces.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter printWriter = new PrintWriter(fileWriter);
		for (i = 0; i < num_of_lines; i++) {
			if (i != index) {
				printWriter.println(content[i]);
			}
		}
		printWriter.close();
	}

	public void cancelOrder(String booking_id) {	
		removeOrderedData(booking_id);
	}
	
	public void cancelRequest(String booking_id) {	
		removeRequestedData(booking_id);
	}
	

	public String pay(String booking_id, Card card, String key) {
			
			double due = 0.0;
			String content[] = getOrderInformation("BookedSpaces.txt");
			int index = checkExistsBookingID(booking_id);
			if(index >= 0) {
				int index2 = content[index].indexOf(";");
				index2 = index2 + 1;
				String due_str = content[index].substring(index2);
				due = Double.parseDouble(due_str);
				Payment p = new Payment();
				
				if (p.attemptPayment(card, due, key).equals("success")) {
					removeOrderedData(booking_id);
					return "success";
				}
			}
			return "";
	}

	public String payTotal(String email, Card card, String key) {
		double total = getTotal(email);
		Payment p = new Payment();
		if (p.attemptPayment(card, total, key).equals("success")) {
			String[] data = getBookedList(email);
			for(int i = 0; i < data.length && data[i] != null; i++) {
				removeOrderedData(data[i]);
			}
			return "success";
		}
		return "";
	}
	public String[] getBookedList(String email) {
		String data[] = getData("BookedSpaces.txt");
		String [] lis = new String[3];
		int index = 0;
		for(int i = 0; i < data.length; i++) {
			if(data[i].contains(email)) {
				int index2 = data[i].indexOf(",");
				index2 = index2 + 1;
				int index3 = data[i].indexOf(",", index2);
				lis[index] = data[i].substring(index2, index3);
				index++;
			}
		}
		return lis;
	}
	/*private void removeAllOrders(String email) {
		int indexes[] = new int[3];
		indexes[0] = -1;
		indexes[1] = -1;
		indexes[2] = -1;
		int s_index = 0;
		String data[] = getOrderInformation("BookedSpaces");
		for(int i = 0; i < data.length; i++) {
			if(data[i].contains(email)) {
				indexes[s_index] = i;
				s_index++;
			}
		}
		String location = "BookedSpaces.txt";
		int num_of_lines = 0;
		File myObj = new File(location);
		Scanner myReader = null;
		try {
			myReader = new Scanner(myObj);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String dt = "";
		while (myReader.hasNextLine()) {
			dt = dt + myReader.nextLine();
			num_of_lines++;
			if (myReader.hasNextLine()) {
				dt = dt + "\n";
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
		write("BookedSpaces.txt", "");

		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		printWriter = new PrintWriter(fileWriter);

		try {
			fileWriter = new FileWriter("BookedSpaces.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (i = 0; i < num_of_lines; i++) {
			if(i != indexes[0] && i != indexes[1] && i != indexes[2]) {
				printWriter.println(content[i]);
			}
		}
		printWriter.close();
		
	}
	*/
	public double getTotal(String email) {
		String data[] = getData("BookedSpaces.txt");
		double total = 0.0;
		for(int i = 0; i < data.length; i++) {
			if(data[i].contains(email)) {
				int index2 = data[i].indexOf(";");
				index2 = index2 + 1;
				total = total + Double.parseDouble(data[i].substring(index2));
			}
		}
		return total;
	}
	public double getTotalForOneSpace(String email, String id) {
		String data[] = getData("BookedSpaces.txt");
		double total = 0.0;
		for(int i = 0; i < data.length; i++) {
			if(data[i].contains(email) && data[i].contains("," + id + ",")) {
				int index2 = data[i].indexOf(";");
				index2 = index2 + 1;
				total = Double.parseDouble(data[i].substring(index2));
				break;
			}
		}
		return total;
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
}