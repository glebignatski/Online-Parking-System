package customer;

import java.util.HashMap;
import java.util.Map;

import parking.ParkingSpaces;
import parking.TimeBooked;
import payment.Card;
import payment.Order;

public class SignIn {
	private boolean logged_in;
	private int num_of_spaces_booked;
	private boolean paid_status;
	private ParkingSpaces ps;
	private Order o;
	private String email;

	public SignIn() {
		this.o = new Order();
		this.logged_in = false;
		this.paid_status = false;
		this.num_of_spaces_booked = 0;
		this.ps = new ParkingSpaces();
	}

	public boolean sign_in(String email_address, String password) {
		if (logged_in == false) {
			Database db = new Database();
			if (db.loginVerification(email_address, password)) {
				this.email = email_address;
				logged_in = true;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public String book_space(int id, TimeBooked t, String dt, String plate_number) {
		this.num_of_spaces_booked = this.o.countNumberOfOrders(this.email);
		if (logged_in == true) {
			if (this.email != null) {
				this.num_of_spaces_booked = this.o.countNumberOfOrders(this.email);
				if (this.num_of_spaces_booked < 3) {
					String verification = this.ps.book_space(id, t, plate_number);
					if (!verification.equals("Sorry, but the space is already booked!")) {
						o.addOrder(id, t, verification, dt, this.email);
						return verification;
					} else {
						return "There is an error with the registration!";
					}
				}
				else {
					return "Sorry, but you can only book 3 parking spaces at the same time!";
				}
			}
			else {
				return "You need to sign in first to use the services!";
			}
		} else {
			if (logged_in == false) {
				return "You need to sign in first to use the services!";
			} else {
				return "Sorry, but you can only book 3 parking spaces at the same time!";
			}
		}
	}
	public String getEmail() {
		return this.email;
	}
	public String viewOrders(String email) {
		if(logged_in == true) {
			String all_orders = "";
			all_orders = this.o.viewOrders(email);
			return all_orders;
		}
		return "error";
	}

	public String cancel_space(String id) {
		if (logged_in == true) {
			String verification = "";
			verification = this.ps.cancel_space(id);
			if (!verification.equals("Does not exist")) {
				o.cancelOrder(verification);
				this.num_of_spaces_booked--;
				return id.substring(0, 4);
			} else {
				return "Error. Please retype the booking id associated with your parking space.";
			}
		} else {
			return "Error. You are not logged in.";
		}
	}

	public String pay(String id, Card card, String key) {
		if (this.o.pay(id, card, key).equals("success")) {
			//ManagePaidStatus m = new ManagePaidStatus();
			//m.requestPartialPaymentStatusChange(this, id);
			return "success";
		}
		return "";
	}
	public double getTotalForOneSpace(String email, String id) {
		double total = 0.0;
		total = this.o.getTotalForOneSpace(email, id);
		return total;
	}
	public double getTotal(String email) {
		double total = 0.0;
		total = this.o.getTotal(email);
		return total;
	}
	public String payTotal(String email, Card card, String key) {
		if (this.o.payTotal(email, card, key).equals("success")) {
			//ManagePaidStatus m = new ManagePaidStatus();
			//m.requestTotalPaymentStatusChange(this);
			return "success";
		}
		return "";
	}

	public int getNumOfBookedSpaces() {
		return this.num_of_spaces_booked;
	}

	public void setPaymentStatus() {
		this.paid_status = true;
	}

	public boolean signOut() {
		if (logged_in == true) {
			logged_in = false;
			return true;
		} else {
			return false;
		}
	}
}