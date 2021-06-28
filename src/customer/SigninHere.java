package customer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import parking.ParkingSpaces;
import parking.TimeBooked;
import payment.Card;
import payment.Order;
import payment.Payment;

public class SigninHere {

	public static void main(String[] args) {
		/*SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	    Date firstDate = null;
		try {
			firstDate = sdf.parse("07/06/1999");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    Date secondDate = null;
		/*DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		String dateobj = "";
		dateobj = df.format(dateobj);
		Date now = null;
		try {
			now = sdf.parse(dateobj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(df.format(dateobj));
	    long diffInMillies = Math.abs(now.getDate() - firstDate.getDate());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
	    System.out.println(diff/365.25);
	    
	    LocalDate birthDate = LocalDate.of(1999, 7, 6);
        // exercise
        //System.out.println(actual);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        int day = 0, month = 0, year = 0;
        Date dateobj = new Date();
        String dt = df.format(dateobj).toString();
        if(dt.substring(0, 1) == "0") {
        	day = Integer.parseInt(dt.substring(1, 2));
        }
        if(dt.substring(0, 1) != "0") {
        	day = Integer.parseInt(dt.substring(0, 2));
        }
        if(dt.substring(3, 4) == "0") {
        	month = Integer.parseInt(dt.substring(4, 5));
        }
        if(dt.substring(3, 4) != "0") {
        	month = Integer.parseInt(dt.substring(3, 5));
        }
        year = Integer.parseInt(dt.substring(6, 10));
        LocalDate currentDate = LocalDate.of(year, month, day);
        //System.out.println(df.format(dateobj).toString());
        //System.out.println(year);
        int actual = calculateAge(LocalDate.of(1999, 7, 6), currentDate);
        System.out.print(actual);
        
		LocalDate birthDate = LocalDate.of(2004, 7, 6);
		Signup user1 = new Signup("Gleb", "Ignatski", "glebignatski@gmail.com", birthDate);
		user1.setAccountDetails("gleb99", "gleb2000-1");*/
		//LocalDate birthDate2 = LocalDate.of(2002, 7, 6);
		//Signup user6 = new Signup("Sergey", "Borisov", "sergey93@gmail.com", birthDate2);
		//user5.setAccountPassword("srg");
		
		/*ParkingSpaces ps = new ParkingSpaces();
		LocalDate birthDate = LocalDate.of(2004, 7, 6);
		StartPage sp = new StartPage();
		sp.signIn("gerasimignatski@gmail.com", "moose2015!");
		TimeBooked t = new TimeBooked("11:30", "13:00");
		sp.book_space(1010, t, "AZ238590");
		
		Order o = new Order();
		o.addOrder(1000, new TimeBooked("12:30", "18:30"));
		o.addOrder(1001, new TimeBooked("19:30", "21:30"));
		o.addOrder(1002, new TimeBooked("20:00", "00:00"));
		System.out.println(o.getAmountDue());
		Card c = new Card("Visa", "4101452200228735", "0623", 19);
		Payment t1 = new Payment();
		t1.attemptPayment(c, o.getAmountDue());
		System.out.println(c.getBalance());
		*/
		//System.out.println(o.getAmount());
		
		TimeBooked t = new TimeBooked("12:00", "14:45");
		//System.out.println(t.calculateTimeInterval());
		
		SignIn sp = new SignIn();
		sp.sign_in("glebignatski@gmail.com", "gleb2000-1");
		//sp.book_space(1017, t, "WX774N");
		
		//sp.signUp("Gerasim", "Ignatski", "gerasimignatski@gmail.com", birthDate);
		//sp.setAccount("moose2015!");
		//sn.signIn("glebborisov7@gmail.com", "glebgleb");
		//sn.signOut();
		//sn.signOut();
		
	}
	public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}