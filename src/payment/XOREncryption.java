package payment;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XOREncryption {
	
	/*public XOREncryption(String filename, int pin) {
		if(pin == 4) {
			encryptDecrypt(filename, "" + pin);
		}
	}
	*/
	public static void main(String[] args) {
		XOREncryption encr = new XOREncryption();
		encr.encryptDecrypt("Card.card", "4286");
	}
	
	public void encryptDecrypt(String filename, String key) {
		if(key.equals("4286")) {
		String data_to_encrypt = "";
		data_to_encrypt = read(filename);
		String outputString = "";
		int len = data_to_encrypt.length();
		int len_key = key.length();
		for (int i = 0; i < len; i++) {
			outputString = outputString + (char) (data_to_encrypt.charAt(i) ^ key.charAt(i % len_key));
		}
		write(filename, outputString);
		}
	}

	private void write(String filename, String data) {
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

	public String read(String filename) {
		String text = "";
		try {
			text = new String(Files.readAllBytes(Paths.get(filename)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
}