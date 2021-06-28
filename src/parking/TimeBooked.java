package parking;

public class TimeBooked {
	private String start;
	private String finish;
	private double st_h;
	private double st_m;
	private double ft_h;
	private double ft_m;
	public TimeBooked(String start, String finish) {
		this.start = start;
		this.finish = finish;
		this.st_h = Integer.parseInt(this.start.substring(0, this.start.indexOf(':')));
		this.st_m = Integer.parseInt(this.start.substring(this.start.indexOf(':')+1, this.start.length()));
		this.ft_h = Integer.parseInt(this.finish.substring(0, this.finish.indexOf(':')));
		this.ft_m = Integer.parseInt(this.finish.substring(this.finish.indexOf(':')+1, this.finish.length()));
		System.out.println("The minimum time allowed is 15 minutes and the maximum is 12 hours.\n"
				+ "Valid entries for the hours are between 04 and 00 (midnight) inclusive.\nThat is, there is a 20 hour window for when you can park your vehicle.\n"
				+ "Valid entries for the minutes are 00, 15, 30, 45.\n"
				+ "The price to park is $1.80 per hour.");
	}
	public String returnStart() {
		return start;
	}
	public String returnFinish() {
		return finish;
	}
	public double calculateTimeInterval() {
		double hours = 0; double minutes = 0;
		if(this.ft_h == 0) {
			this.ft_h = 24;
		}
		hours = this.ft_h - this.st_h;
		minutes = this.ft_m - this.st_m;
		if(hours > 0) {
			if(hours > 12) {
				System.out.println("Sorry, but you can only park your car for up to 12 hours. The amount that you signed up for is too high!" );
			}
			else {
				hours = hours * 60; //convert into minutes
				hours = hours + minutes;
			}
		}
		else if(hours == 0 && minutes > 0) {
			hours = minutes;
		}
		else if(hours == 0 && minutes == 0) {
			System.out.println("Sorry, but the time interval needs to be at least 15 minutes! The amount that you signed up for is too low!");
		}
		else {
			System.out.println("Sorry, but the finish time was entered incorrectly!");
			return -1;
		}
		double m = 0.0;
		m = hours/60 - Math.floor(hours/60);
		System.out.println("The duration that you booked is "
		+ Math.floor(hours/60) + " hours and " + m * 60 + " minutes.");
		
		return hours/60.0;
	}
}