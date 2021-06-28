package system;

//import parking_officer.Database;

public class SignInAdmin {
	private boolean logged_in = false;
	private ManageOfficers mo = new ManageOfficers();

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
	
	public boolean addOfficer(String f_name, String l_name, String email, String password) {
		if(logged_in == true) {
			if(mo.addOfficer(f_name, l_name, email, password)) {
				return true;
			}
			return false;
		}
		return false;
	}
	public boolean removeOfficer(String username) {
		if(logged_in == true) {
			if(mo.removeOfficer(username)) {
				return true;
			}
			return false;
		}
		return false;
	}
}