package payment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Card {
	private String type;
	private String id;
	private String expDate;
	private String f_name;
	private String l_name;
	private double balance;
	private String card_file;
	private XOREncryption x;
	public Card(){
		this.x = new XOREncryption();
	}
	public String[] readCardFile(String card_file, String key) {
		this.card_file = card_file;
		this.x.encryptDecrypt(this.card_file, key);
		int num_of_lines = 0;
		File myObj = new File(card_file);
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
		//this.x.encryptDecrypt(this.card_file, key);
		return content;
	}
	public boolean scan_card_file(String card_file, String type, String card_number, String expdate, String f, String l, String key) {
		String[] content = readCardFile(card_file, key);
		int index = 0;
		for(int i = 1; i < content.length; i+=6) {
			if(content[i-1].equals(type) && content[i].equals(card_number) && content[i+1].equals(expdate) 
					&& content[i+2].equals(f) && content[i+3].equals(l)) {
				index = i;
				break;
			}
		}
		if(index > 0) {
			this.type = content[index-1];
			this.id = content[index];
			this.expDate = content[index+1];
			this.f_name = content[index+2];
			this.l_name = content[index+3];
			this.balance = Double.parseDouble(content[index+4]);
			return true;
		}
		return false;
	}
	public String getType() {
		return this.type;
	}
	public String getId() {
		return this.id;
	}
	public String getExpDate() {
		return this.expDate;
	}
	public double getBalance() {
		return this.balance;
	}
	public String getCardFile() {
		return this.card_file;
	}
	public void upDateBalance(double amount, String key) {
		this.balance = this.balance + amount;
		this.x.encryptDecrypt(this.card_file, key);
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(this.card_file, true);
		} catch (IOException e) {
			e.printStackTrace();
		} //Set true for append mode
	    int index = 0;
	    String[] content = readCardFile(getCardFile(), key);
		for(int i = 1; i < content.length; i+=6) {
			if(content[i].equals(getId())) {
				index = i;
				break;
			}
		}
		content[index+4] = "" + this.balance;
		write(this.card_file, "");
		
		PrintWriter printWriter = new PrintWriter(fileWriter);
		for(int i = 0; i < content.length; i++) {
			printWriter.println(content[i]);
		}
	    /*printWriter.println(this.type);
	    printWriter.println(this.id);
	    printWriter.println(this.expDate);
	    printWriter.println(this.f_name);
		printWriter.println(this.l_name);
		printWriter.println(this.balance);
		*/
		
		printWriter.close();
		this.x.encryptDecrypt(this.card_file, key);
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