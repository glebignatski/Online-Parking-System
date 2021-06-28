package customer;

import java.time.LocalDate;

import parking.TimeBooked;

public class StartPage {
	private Signup su;
	private SignIn si;
	private boolean sign_up_status = false;
	public StartPage() {
		
	}
	public void signIn(String email, String password) {
		si = new SignIn();
		si.sign_in(email, password);
	}
	public void signUp(String f_name, String l_name, String email_address, String date_of_birth, String password) {
		su = new Signup();
		su.register(f_name, l_name, email_address, date_of_birth, password);
		sign_up_status = true;
	}
	public void setAccount(String password) {
		if(sign_up_status == true) {
			//su.setAccountPassword(password);
		}
	}
	public void book_space(int id, TimeBooked t, String plate_number) {
		si.book_space(id, t, "02/03/1999", plate_number);
	}
}