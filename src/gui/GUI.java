package gui;
import java.awt.event.ActionListener;

import customer.SignIn;
import customer.Signup;
import parking.ParkingSpaces;
import parking.TimeBooked;
import parking_officer.SignInParkingOfficer;
import payment.Card;
import payment.Order;
import system.SignInAdmin;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI implements ActionListener {

	private static JLabel welcomelabel;
	private static JLabel createAccount;
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JButton button_c, button_o, button_a, button_signUp;
	private static JLabel success;
	

	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.execute_application();
	}

	public void execute_application() {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		// frame.setBackground(Color.black);

		// JLabel imageLogo = new JLabel();
		// imageLogo.setIcon(new ImageIcon("src\\more\\PLOT.jpg"));
		// panel.add(imageLogo);
		panel.setBackground(Color.blue.CYAN.brighter());
		panel.setLayout(null);
		welcomelabel = new JLabel("Welcome to the Online Parking Application!");
		welcomelabel.setBounds(170, 20, 450, 40);
		welcomelabel.setBackground(Color.black);
		welcomelabel.setFont(new Font("Serif", Font.PLAIN, 20));
		panel.add(welcomelabel);

		createAccount = new JLabel("Don't have an account?");
		createAccount.setBounds(400, 420, 220, 20);
		createAccount.setBackground(Color.black);
		panel.add(createAccount);

		button_signUp = new JButton("Sign up");
		button_signUp.setBounds(400, 460, 120, 25);
		button_signUp.addActionListener(new GUI());

		userLabel = new JLabel("Username");
		userLabel.setForeground(Color.blue.darker().darker());
		// format:
		// userlabel.setBounds(x, y, width, height)
		userLabel.setBounds(10, 120, 80, 25);
		panel.add(userLabel);

		userText = new JTextField(20); // 20 is length of text field
		userText.setBounds(100, 120, 220, 25);
		panel.add(userText);

		passwordLabel = new JLabel("Password");
		passwordLabel.setForeground(Color.blue.darker().darker());
		passwordLabel.setBounds(10, 170, 80, 25);
		panel.add(passwordLabel);

		passwordText = new JPasswordField();
		passwordText.setBounds(100, 170, 220, 25);
		panel.add(passwordText);

		button_c = new JButton("Login as customer");
		button_c.setBounds(10, 210, 220, 25);
		button_c.addActionListener(new GUI());
		button_o = new JButton("Login as officer");
		button_o.setBounds(240, 210, 220, 25);
		button_o.addActionListener(new GUI());
		button_a = new JButton("Login as administrator");
		button_a.setBounds(470, 210, 220, 25);
		button_a.addActionListener(new GUI());
		panel.add(button_c);
		panel.add(button_o);
		panel.add(button_a);
		panel.add(button_signUp);

		success = new JLabel("");
		success.setBounds(10, 250, 300, 25);
		panel.add(success);
		frame.setVisible(true);
	}
	private void book_space(SignIn si) {
		JButton button2, view, pay, cancel, request, pay_total, return_b;
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		JLabel success = new JLabel();
		JLabel userLabel, userLabel2, pay_label, cancel_label;
		userLabel = new JLabel("Enter a Parking ID:");
		userLabel.setBounds(10, 20, 280, 25);
		userLabel2 = new JLabel("Request a Parking Space ID:");
		userLabel2.setBounds(10, 110, 280, 25);
		panel.add(userLabel);
		panel.add(userLabel2);
		pay_label = new JLabel("PAY FOR PARKING SPACE ID:");
		pay_label.setBounds(300, 370, 220, 25);
		cancel_label = new JLabel("CANCEL PARKING SPACE ID:");
		cancel_label.setBounds(300, 410, 220, 25);
		panel.add(cancel_label);
		panel.add(pay_label);
		
		// entry to pay for space
		JTextField pay_label1_text = new JTextField(20);
		pay_label1_text.setBounds(500, 370, 120, 25);
		panel.add(pay_label1_text);
		
		// entry to cancel space
		JTextField pay_label2_text = new JTextField(20);
		pay_label2_text.setBounds(500, 410, 120, 25);
		panel.add(pay_label2_text);
		
		// entry to book space
		JTextField userText2 = new JTextField(20);
		userText2.setBounds(160, 20, 80, 25);
		panel.add(userText2);
		
		// entry to request space
		JTextField request_text = new JTextField(20);
		request_text.setBounds(220, 110, 80, 25);
		panel.add(request_text);
		
		JTextArea available_spots = new JTextArea();
		available_spots.setBounds(5, 200, 265, 200);
		JTextArea orders = new JTextArea();
		orders.setBounds(290, 140, 500, 150);
		
		//JTextArea ja = new JTextArea();
		//ja.setBounds(10, 150, 450, 400);
		
		panel.setLayout(null);
		panel.setBackground(Color.blue.CYAN.brighter());
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		success.setText("Welcome!");
		success.setBounds(300, 10, 300, 25);
		panel.add(success);
		
		button2 = new JButton("Book this parking space");
		button2.setBounds(10, 65, 240, 25);
		button2.addActionListener(new GUI());
		
		request = new JButton("Request this parking space");
		request.setBounds(10, 150, 240, 25);
		panel.add(request);
		// view bookings button
		view = new JButton("View Bookings");
		view.setBounds(380, 100, 200, 25);
		view.addActionListener(new GUI());
		
		// pay for space button
		pay = new JButton("PAY");
		pay.setBounds(630, 370, 90, 25);
		pay.addActionListener(new GUI());
		
		// cancel parking space button
		cancel = new JButton("CANCEL");
		cancel.setBounds(630, 410, 90, 25);
		cancel.addActionListener(new GUI());
		
		pay_total = new JButton("PAY FOR ALL");
		pay_total.setBounds(380, 450, 180, 25);
		pay_total.addActionListener(new GUI());
		
		ParkingSpaces ps = new ParkingSpaces();
		String s = ps.getAllAvailable();
		String arr[] = ps.getListOfAvailable();
		available_spots.setText("Here are a list of available spaces..."
				+ "\n\n" + s);
		panel.add(available_spots);
		panel.add(orders);
		panel.add(button2);
		panel.add(view);
		panel.add(pay);
		panel.add(cancel);
		panel.add(pay_total);
		
		return_b = new JButton("Sign Out");
		return_b.setBounds(500, 510, 100, 25);
		return_b.addActionListener(new GUI());
		panel.add(return_b);
		
		frame.setVisible(true);
		
		pay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					transaction(si, pay_label1_text, "One");
				}
		});
		
		pay_total.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transaction(si, pay_label1_text, "All");
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pay_label2_text.getText().length() > 3) {
					si.cancel_space(pay_label2_text.getText());
					String s = ps.getAllAvailable();
					available_spots.setText("Here are a list of available spaces..."
							+ "\n\n" + s);
					String order_list = "";
					order_list = si.viewOrders(si.getEmail());
					orders.setText(order_list);
				}
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = userText2.getText();
				int dt = Integer.parseInt(data);
				if (dt < 1000 || dt > 1024) {
					invalid_parking_id();
				} else {
					int index = -1;
					for(int i = 0; i < arr.length; i++) {
						if(arr[i].equals(data)) {
							index = i;
							break;
						}
					}
					if(index >= 0) {
						select_time(data, si);
						
					}
					else {
						invalid_parking_id();
					}
				}
			}
		});
		view.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String order_list = "";
				order_list = si.viewOrders(si.getEmail());
				orders.setText(order_list);
			}
			
		});
		return_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				si.signOut();
				execute_application();
			}
		});
		request.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select_request_time(request_text.getText(), si, "Good");
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button_c) {
			String user = userText.getText();
			String password = passwordText.getText();
			// System.out.println(user + ", " + password);
			SignIn si = new SignIn();
			boolean check = si.sign_in(user, password);
			if (check) {
				// user.equals("a") && password.equals("1")
				// loginVerification(user, password);
				//success.setText("Login successful!");
				book_space(si);
				/*JButton button2, view, pay, cancel, request, pay_total;
				JPanel panel = new JPanel();
				JFrame frame = new JFrame();
				JLabel success = new JLabel();
				JLabel userLabel, userLabel2, pay_label, cancel_label;
				userLabel = new JLabel("Enter a Parking ID:");
				userLabel.setBounds(10, 20, 240, 25);
				userLabel2 = new JLabel("Request a Parking Space with ID:");
				userLabel2.setBounds(10, 110, 300, 25);
				panel.add(userLabel);
				panel.add(userLabel2);
				pay_label = new JLabel("PAY FOR PARKING SPACE ID:");
				pay_label.setBounds(300, 370, 210, 25);
				cancel_label = new JLabel("CANCEL PARKING SPACE ID:");
				cancel_label.setBounds(300, 410, 210, 25);
				panel.add(cancel_label);
				panel.add(pay_label);
				
				// entry to pay for space
				JTextField pay_label1_text = new JTextField(20);
				pay_label1_text.setBounds(475, 370, 110, 25);
				panel.add(pay_label1_text);
				
				// entry to cancel space
				JTextField pay_label2_text = new JTextField(20);
				pay_label2_text.setBounds(475, 410, 110, 25);
				panel.add(pay_label2_text);
				
				// entry to book space
				JTextField userText2 = new JTextField(20);
				userText2.setBounds(120, 20, 80, 25);
				panel.add(userText2);
				
				// entry to request space
				JTextField request_text = new JTextField(20);
				request_text.setBounds(200, 110, 80, 25);
				panel.add(request_text);
				
				JTextArea available_spots = new JTextArea();
				available_spots.setBounds(10, 200, 280, 300);
				JTextArea orders = new JTextArea();
				orders.setBounds(300, 140, 355, 150);
				
				//JTextArea ja = new JTextArea();
				//ja.setBounds(10, 150, 450, 400);
				
				panel.setLayout(null);
				panel.setBackground(Color.blue.CYAN.brighter());
				frame.setSize(700, 600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(panel);
				success.setText("Welcome!");
				success.setBounds(300, 10, 300, 25);
				panel.add(success);
				
				button2 = new JButton("Book this parking space.");
				button2.setBounds(10, 65, 190, 25);
				button2.addActionListener(new GUI());
				
				request = new JButton("Request this parking space");
				request.setBounds(10, 150, 210, 25);
				panel.add(request);
				// view bookings button
				view = new JButton("View Bookings");
				view.setBounds(380, 100, 200, 25);
				view.addActionListener(new GUI());
				
				// pay for space button
				pay = new JButton("PAY");
				pay.setBounds(590, 370, 95, 25);
				pay.addActionListener(new GUI());
				
				// cancel parking space button
				cancel = new JButton("CANCEL");
				cancel.setBounds(590, 410, 95, 25);
				cancel.addActionListener(new GUI());
				
				pay_total = new JButton("PAY FOR ALL");
				pay_total.setBounds(380, 450, 180, 25);
				pay_total.addActionListener(new GUI());
				
				ParkingSpaces ps = new ParkingSpaces();
				String s = ps.getAllAvailable();
				String arr[] = ps.getListOfAvailable();
				available_spots.setText("Here are a list of available parking spaces..."
						+ "\n\n" + s);
				panel.add(available_spots);
				panel.add(orders);
				panel.add(button2);
				panel.add(view);
				panel.add(pay);
				panel.add(cancel);
				panel.add(pay_total);
				frame.setVisible(true);
				
				pay.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(pay_label1_text.getText().length() > 3) {
							transaction(si, pay_label1_text);
						}
					}
				});
				
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(pay_label2_text.getText().length() > 3) {
							si.cancel_space(pay_label2_text.getText());
						}
					}
				});
				
				button2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String data = userText2.getText();
						int dt = Integer.parseInt(data);
						if (dt < 1000 || dt > 1024) {
							invalid_parking_id();
						} else {
							int index = -1;
							for(int i = 0; i < arr.length; i++) {
								if(arr[i].equals(data)) {
									index = i;
									break;
								}
							}
							if(index >= 0) {
								select_time(data, si);
							}
							else {
								invalid_parking_id();
							}
						}
					}
				});
				view.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						String order_list = "";
						order_list = si.viewOrders(si.getEmail());
						orders.setText(order_list);
					}
					
				});
				*/
				userText.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String data = userText.getText();
						if (data.length() == 0) {
							System.out.println("Nothing was entered!");
						}
					}
				});
			} else {
				success.setText("Incorrect Login! Please try again.");
			}
		} else if (e.getSource() == button_signUp) {
			JPanel panel = new JPanel();
			JFrame frame = new JFrame();
			JLabel message;
			JLabel f_name_error;
			JLabel l_name_error;
			JLabel date_of_birth_error;
			JLabel email_error;
			JLabel password_error;
			JLabel f_name;
			JLabel l_name;
			JLabel email;
			JLabel password;
			JLabel date_of_birth;
			JTextField f_name_field;
			JTextField l_name_field;
			JTextField email_field;
			JTextField password_field;
			JTextField date_of_birth_field;
			JButton submit;
			frame.setSize(800, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			panel.setBackground(Color.blue.CYAN.brighter());
			panel.setLayout(null);
			frame.add(panel);
			JLabel userLabel;
			userLabel = new JLabel("Please provide the following information to sign up.");
			userLabel.setBounds(100, 50, 480, 40);
			userLabel.setBackground(Color.black);
			userLabel.setFont(new Font("Serif", Font.PLAIN, 17));

			message = new JLabel("");
			message.setBounds(10, 370, 420, 25);
			panel.add(message);

			f_name_error = new JLabel("");
			f_name_error.setBounds(10, 405, 300, 20);
			panel.add(f_name_error);

			l_name_error = new JLabel("");
			l_name_error.setBounds(10, 440, 300, 20);
			panel.add(l_name_error);
			
			date_of_birth_error = new JLabel("");
			date_of_birth_error.setBounds(10, 475, 300, 20);
			panel.add(date_of_birth_error);

			email_error = new JLabel("");
			email_error.setBounds(10, 510, 300, 20);
			panel.add(email_error);

			password_error = new JLabel("");
			password_error.setBounds(10, 545, 340, 20);
			panel.add(password_error);

			f_name = new JLabel("First name");
			f_name.setForeground(Color.blue.darker().darker());
			f_name.setBounds(10, 120, 80, 25); // username
			panel.add(f_name);

			f_name_field = new JTextField(); // 20 is length of text field
			f_name_field.setBounds(200, 120, 220, 25);
			panel.add(f_name_field);

			l_name = new JLabel("Last name");
			l_name.setForeground(Color.blue.darker().darker());
			l_name.setBounds(10, 160, 80, 25); // password
			panel.add(l_name);

			l_name_field = new JTextField();
			l_name_field.setBounds(200, 160, 220, 25);
			panel.add(l_name_field);

			email = new JLabel("Email");
			email.setForeground(Color.blue.darker().darker());
			email.setBounds(10, 200, 80, 25); // email
			panel.add(email);

			email_field = new JTextField();
			email_field.setBounds(200, 200, 220, 25);
			panel.add(email_field);

			date_of_birth = new JLabel("Date of Birth (dd/mm/yyyy)");
			date_of_birth.setForeground(Color.blue.darker().darker());
			date_of_birth.setBounds(10, 240, 230, 25); // email
			panel.add(date_of_birth);

			date_of_birth_field = new JTextField();
			date_of_birth_field.setBounds(200, 240, 220, 25);
			panel.add(date_of_birth_field);

			password = new JLabel("Password");
			password.setForeground(Color.blue.darker().darker());
			password.setBounds(10, 280, 80, 25); // email
			panel.add(password);

			password_field = new JPasswordField();
			password_field.setBounds(200, 280, 220, 25);
			panel.add(password_field);

			submit = new JButton("Submit");
			submit.setBounds(230, 320, 100, 25);
			submit.addActionListener(new GUI());
			panel.add(submit);
			
			JButton return_s = new JButton("Return to start page");
			return_s.setBounds(420, 470, 180, 25);
			return_s.addActionListener(new GUI());
			panel.add(return_s);

			panel.add(userLabel);
			frame.setVisible(true);
			
			submit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (f_name_field.getText().length() > 1 && l_name_field.getText().length() > 1
							&& email_field.getText().length() > 2 && date_of_birth_field.getText().length() == 10
							&& password_field.getText().length() > 5) {
						Signup su = new Signup();
						boolean check = su.register(f_name_field.getText(), l_name_field.getText(),
								email_field.getText(), date_of_birth_field.getText(), password_field.getText());

						if (check) {
							//message.setText("An account with this email is already registered.");
							message.setText("Thank you for signing up!");
							f_name_error.setText("");
							l_name_error.setText("");
							date_of_birth_error.setText("");
							email_error.setText("");
							password_error.setText("");
						} else {
							message.setText("An account with this email is already registered.");
							f_name_error.setText("");
							l_name_error.setText("");
							date_of_birth_error.setText("");
							email_error.setText("");
							password_error.setText("");
						}
					} else {
						if (f_name_field.getText().length() <= 1) {
							f_name_error.setText("First name needs to be at least 2 letters.");
						}
						if (f_name_field.getText().length() > 1) {
							f_name_error.setText("");
						}
						if (l_name_field.getText().length() <= 1) {
							l_name_error.setText("Last name needs to be at least 2 letters.");
						}
						if (l_name_field.getText().length() > 1) {
							l_name_error.setText("");
						}
						if(date_of_birth_field.getText().length() != 10) {
							date_of_birth_error.setText("Date of birth is not complete.");
						}
						if(date_of_birth_field.getText().length() == 10) {
							date_of_birth_error.setText("");
						}
						if (email_field.getText().length() <= 2) {
							email_error.setText("Email needs to be at least 3 characters.");
						}
						if (email_field.getText().length() > 2) {
							email_error.setText("");
						}
						if (password_field.getText().length() <= 5) {
							password_error.setText("Password needs to be at least 6 characters.");
						}
						if (password_field.getText().length() > 5) {
							password_error.setText("");
						}
					}
				}
			});
			return_s.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					execute_application();
				}
			});
		} else if (e.getSource() == button_o) {
			String user = userText.getText();
			String password = passwordText.getText();
			SignInParkingOfficer po = new SignInParkingOfficer();
			boolean check = po.sign_in(user, password);
			if (check) {
				JButton button2;
				JPanel panel = new JPanel();
				JFrame frame = new JFrame();
				JLabel userLabel;

				panel.setLayout(null);
				frame.setSize(800, 600);
				panel.setBackground(Color.blue.CYAN.brighter());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(panel);
				button2 = new JButton("Manage Parking Spaces");
				button2.setBounds(260, 230, 240, 40);
				button2.addActionListener(new GUI());
				panel.add(button2);
				
				JButton return_b = new JButton("Sign Out");
				return_b.setBounds(600, 440, 100, 25);
				return_b.addActionListener(new GUI());
				panel.add(return_b);
				frame.setVisible(true);
				
				button2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						manage_parking_spaces(po);
					}
					
				});
				return_b.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						po.signOut();
						execute_application();
					}
					
				});
			} else {
				success.setText("Incorrect Login! Please try again.");
			}
		} else if (e.getSource() == button_a) {
			String user = userText.getText();
			String password = passwordText.getText();
			SignInAdmin admin = new SignInAdmin();
			boolean check = admin.sign_in(user, password);
			if (check) {
				manage_officers(admin);
			} else {
				success.setText("Incorrect Login! Please try again.");
			}
		}
	}
	
	private void select_time(String data, SignIn si) {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		JButton return_b;
		return_b = new JButton("Go Back");
		return_b.setBounds(580, 440, 100, 25);
		return_b.addActionListener(new GUI());
		panel.add(return_b);
		JLabel entry_date = new JLabel();
		JLabel entry_start = new JLabel();
		JLabel entry_finish = new JLabel();
		JLabel plate_number = new JLabel();
		JLabel error = new JLabel();
		JButton book = new JButton();
		JButton view = new JButton();
		JTextField userText0 = new JTextField(20);
		JTextField userText1 = new JTextField(20);
		JTextField userText2 = new JTextField(20);
		JTextField userText3 = new JTextField(20);
		userText0.setBounds(300, 10, 250, 25);
		userText1.setBounds(300, 50, 250, 25);
		userText2.setBounds(300, 90, 250, 25);
		userText3.setBounds(300, 130, 250, 25);
		
		panel.setLayout(null);
		panel.setBackground(Color.blue.CYAN.brighter());
		panel.add(userText0);
		panel.add(userText1);
		panel.add(userText2);
		panel.add(userText3);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		entry_date.setText("Enter the date (dd/mm/yyyy):");
		entry_date.setBounds(10, 10, 270, 25);
		entry_start.setText("Enter start time:");
		entry_start.setBounds(10, 50, 270, 25);
		entry_finish.setText("Enter finish time:");
		entry_finish.setBounds(10, 90, 270, 25);
		plate_number.setText("Enter your car's plate number:");
		plate_number.setBounds(10, 130, 270, 25);
		error.setBounds(10, 250, 320, 25);
		panel.add(error);
		
		String book_id = data;
		book.setText("Book " + "Parking Space " + data);
		book.setBounds(10, 210, 300, 30);
		view.setText("View Bookings");
		view.setBounds(420, 210, 150, 30);
		JTextArea available_spots = new JTextArea();
		available_spots.setBounds(10, 360, 520, 100);
		panel.add(book);
		panel.add(view);
		panel.add(available_spots);
		panel.add(entry_date);
		panel.add(entry_start);
		panel.add(entry_finish);
		panel.add(plate_number);
		frame.setVisible(true);
		book.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					Order o = new Order();
					if(o.countNumberOfOrders(si.getEmail()) > 2) {
						error.setText("You cannot book more than 3 spaces!");
					}
					else {
						TimeBooked t = new TimeBooked(userText1.getText(), userText2.getText());
						si.book_space(Integer.parseInt(data), t, userText0.getText(), userText3.getText());
						error.setText("Thank you for booking!");
					}
				}		
		});
		view.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String orders = "";
				orders = si.viewOrders(si.getEmail());
				available_spots.setText(orders);
			}
			
		});
		return_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_space(si);
			}
		});
	}
	private void manage_parking_spaces(SignInParkingOfficer po) {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		JButton return_b;
		//JLabel entry_start = new JLabel();
		//JLabel entry_finish = new JLabel();
		//JLabel plate_number = new JLabel();
		//JButton book = new JButton();
		//JButton view = new JButton();
		panel.setLayout(null);
		panel.setBackground(Color.blue.CYAN.brighter());
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		/*entry_start.setText("Enter start time:");
		entry_start.setBounds(10, 20, 180, 25);
		entry_finish.setText("Enter finish time:");
		entry_finish.setBounds(10, 80, 180, 25);
		plate_number.setText("Enter your car's plate number:");
		plate_number.setBounds(10, 140, 180, 25);*/
		JTextField email_entry, grant_entry, cancel_entry, add_entry, remove_entry;
		JButton email_check, cancel_request, grant_request, add_space, remove_space;
		
		JTextArea email_list = new JTextArea();
		email_list.setBounds(540, 280, 240, 220);
		email_list.setText(po.viewCustomerEmails());
		panel.add(email_list);
		
		
		email_check = new JButton("Check Requests associated with email:");
		email_check.setBounds(10, 280, 315, 25);
		email_entry = new JTextField(20);
		email_entry.setBounds(335, 280, 190, 25);
		panel.add(email_check);
		panel.add(email_entry);
		
		grant_request = new JButton("Grant Request");
		grant_request.setBounds(10, 320, 180, 25);
		grant_entry = new JTextField(20);
		grant_entry.setBounds(200, 320, 120, 25);
		panel.add(grant_request);
		panel.add(grant_entry);
		
		cancel_request = new JButton("Cancel Request");
		cancel_request.setBounds(10, 360, 180, 25);
		cancel_entry = new JTextField(20);
		cancel_entry.setBounds(200, 360, 120, 25);
		panel.add(cancel_request);
		panel.add(cancel_entry);
		
		add_space = new JButton("Add Space");
		add_space.setBounds(10, 400, 180, 25);
		add_entry = new JTextField(20);
		add_entry.setBounds(200, 400, 120, 25);
		panel.add(add_space);
		panel.add(add_entry);
		
		remove_space = new JButton("Remove Space");
		remove_space.setBounds(10, 440, 180, 25);
		remove_entry = new JTextField(20);
		remove_entry.setBounds(200, 440, 120, 25);
		panel.add(remove_space);
		panel.add(remove_entry);
		
		return_b = new JButton("Sign Out");
		return_b.setBounds(500, 510, 100, 25);
		return_b.addActionListener(new GUI());
		panel.add(return_b);
		
		JTextArea available_spots = new JTextArea();
		available_spots.setBounds(10, 70, 720, 180);
		panel.add(available_spots);
		
		return_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				po.signOut();
				execute_application();
			}
		});
		cancel_request.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Not possible to implement, but in reality, the officer sends an email to the customer informing them
				// that the request cannot be granted.
				// Additionally, the customer could have made an error and requested a parking space that is already available
			}
		});
		/*Visa
		2410024527339075
		08/24
		Ben
		Gibbin*/
		email_check.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Order o = new Order();
				available_spots.setText(o.viewRequests(email_entry.getText()));		
			}
			
		});
		remove_space.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ParkingSpaces ps = new ParkingSpaces();
				ps.makeSpaceUnavailable(add_entry.getText());		
			}
			
		});
		add_space.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ParkingSpaces ps = new ParkingSpaces();
				ps.makeSpaceVacant(add_entry.getText());
				//available_spots.setText(o.viewRequests(email_entry.getText()));		
			}
			
		});
		grant_request.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Order o = new Order();
				o.cancelRequest(remove_entry.getText());
				//available_spots.setText(o.viewRequests(email_entry.getText()));		
			}
			
		});
		
		//panel.add(remove_space);
		//panel.add(add_space);
		//panel.add(grant_request);
		//panel.add(cancel_request);
		//panel.add(entry_start);
		//panel.add(entry_finish);
		//panel.add(plate_number);
		frame.setVisible(true);
	}
	private void manage_officers(SignInAdmin admin) {
		JButton button2, button3, return_b;
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		JLabel success = new JLabel();
		JLabel userLabel2;
		JLabel f_label, l_label, email_label, password_label;
		JTextField f_name_field, l_name_field, email_field;
		JPasswordField password_field;
		
		f_label = new JLabel("Enter first name:");
		l_label = new JLabel("Enter surname:");
		email_label = new JLabel("Enter email:");
		password_label = new JLabel("Enter password");
		
		JLabel add_error = new JLabel("");
		add_error.setBounds(10, 230, 460, 25);
		JLabel remove_error = new JLabel("");
		remove_error.setBounds(10, 370, 460, 25);
		
		f_label.setBounds(10, 20, 200, 25);
		l_label.setBounds(10, 60, 200, 25);
		email_label.setBounds(10, 100, 200, 25);
		password_label.setBounds(10, 140, 200, 25);
		
		userLabel2 = new JLabel("Enter Id here");
		userLabel2.setBounds(10, 300, 100, 25);
		
		panel.add(f_label);
		panel.add(l_label);
		panel.add(email_label);
		panel.add(password_label);
		panel.add(add_error);
		panel.add(remove_error);
		
		panel.add(userLabel2);

		f_name_field = new JTextField(20);
		f_name_field.setBounds(150, 20, 150, 25);
		l_name_field = new JTextField(20);
		l_name_field.setBounds(150, 60, 150, 25);
		email_field = new JTextField(20);
		email_field.setBounds(150, 100, 150, 25);
		password_field = new JPasswordField(20);
		password_field.setBounds(150, 140, 150, 25);
		
		JTextField userText3 = new JTextField(20);
		userText3.setBounds(150, 300, 150, 25);
		
		panel.setLayout(null);
		
		panel.add(f_name_field);
		panel.add(l_name_field);
		panel.add(email_field);
		panel.add(password_field);
		panel.add(userText3);
		
		frame.setSize(800, 600);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setBackground(Color.blue.CYAN.brighter());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		//success.setText("Welcome!");
		//success.setBounds(300, 10, 300, 25);
		panel.add(success);
		button2 = new JButton("Add Officer");
		button2.setBounds(10, 200, 180, 25);
		button2.addActionListener(new GUI());
		button3 = new JButton("Remove Officer");
		button3.setBounds(10, 340, 180, 25);
		button3.addActionListener(new GUI());
		panel.add(button2);
		panel.add(button3);
		
		return_b = new JButton("Sign Out");
		return_b.setBounds(500, 440, 100, 25);
		return_b.addActionListener(new GUI());
		panel.add(return_b);
		
		frame.setVisible(true);
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(admin.addOfficer(f_name_field.getText(), l_name_field.getText(), email_field.getText(), password_field.getText())) {
					add_error.setText("The officer is added.");
				}
				else {
					add_error.setText("An officer is already registered with this email address.");
				}
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(admin.removeOfficer(userText3.getText())){
					remove_error.setText("The officer is removed.");
				}
				else {
					remove_error.setText("There is no officer with this ID.");
				}
			}
		});
		return_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				admin.signOut();
				execute_application();
			}
		});
	}
	
	private void transaction(SignIn si, JTextField id, String flag) {
		JButton button2, return_display;
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		JLabel card_type, card_number, expiry_date, f_name, l_name, security_code;
		JTextField card_type_field, card_number_field, expiry_date_field, f_name_field, l_name_field, security_code_field;
		
		card_type = new JLabel("Please enter the card type (Visa, Debit, etc):");
		card_number = new JLabel("Please enter the number on your card:");
		expiry_date = new JLabel("Please enter the expiry date (mm/dd):");
		f_name = new JLabel("First name:");
		l_name = new JLabel("Last name:");
		security_code = new JLabel("Security code:");
		
		card_type.setBounds(10, 20, 410, 25);
		card_number.setBounds(10, 60, 280, 25);
		expiry_date.setBounds(10, 100, 280, 25);
		f_name.setBounds(10, 140, 130, 25);
		l_name.setBounds(10, 180, 130, 25);
		security_code.setBounds(10, 220, 170, 25);
		
		panel.add(card_type);
		panel.add(card_number);
		panel.add(expiry_date);
		panel.add(f_name);
		panel.add(l_name);
		panel.add(security_code);

		card_type_field = new JTextField(20);
		card_type_field.setBounds(430, 20, 100, 25);
		card_number_field = new JTextField(16);
		card_number_field.setBounds(430, 60, 180, 25);
		expiry_date_field = new JTextField(20);
		expiry_date_field.setBounds(430, 100, 80, 25);
		f_name_field = new JTextField(20);
		f_name_field.setBounds(430, 140, 120, 25);
		l_name_field = new JTextField(20);
		l_name_field.setBounds(430, 180, 120, 25);
		security_code_field = new JTextField(4);
		security_code_field.setBounds(430, 220, 60, 25);
		
		panel.setLayout(null);
		
		panel.add(card_type_field);
		panel.add(card_number_field);
		panel.add(expiry_date_field);
		panel.add(f_name_field);
		panel.add(l_name_field);
		panel.add(security_code_field);
		
		frame.setSize(800, 600);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.setBackground(Color.blue.CYAN.brighter());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		//success.setText("Welcome!");
		//success.setBounds(300, 10, 300, 25);
		//panel.add(success);
		button2 = new JButton("Process Transaction");
		button2.setBounds(10, 270, 220, 25);
		button2.addActionListener(new GUI());
		
		JLabel error = new JLabel();
		error.setBounds(10, 320, 430, 25);
		panel.add(error);
		
		panel.add(button2);
		return_display = new JButton("Go Back");
		return_display.setBounds(420, 470, 180, 25);
		return_display.addActionListener(new GUI());
		panel.add(return_display);
		
		JLabel success2 = new JLabel("");
		success2.setBounds(10, 380, 100, 25);
		panel.add(success2);
		frame.setVisible(true);
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (flag.equals("One")) {
					Card card = new Card();
					if (card.scan_card_file("Card.card", card_type_field.getText(), card_number_field.getText(),
							expiry_date_field.getText(), f_name_field.getText(), l_name_field.getText(),
							security_code_field.getText())) {
						if (si.pay(id.getText(), card, security_code_field.getText()).equals("success")) {
							success2.setText("Success!");
						}
					}
				} else if (flag.equals("All")) {
					Card card = new Card();
					if (card.scan_card_file("Card.card", card_type_field.getText(), card_number_field.getText(),
							expiry_date_field.getText(), f_name_field.getText(), l_name_field.getText(),
							security_code_field.getText())) {
						if (si.payTotal(si.getEmail(), card, security_code_field.getText()).equals("success")) {
							success2.setText("Success!");
						}
					}
				}
			}
		});
			
		return_display.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_space(si);
			}
		});
	}
		/*JFrame frame = new JFrame();
	    frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(700, 600);
	    //frame.setLocation(430, 100);

	    JPanel panel = new JPanel();
	    panel.setBackground(Color.blue.CYAN.brighter());
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // added code

	    frame.add(panel);

	    JLabel card_type = new JLabel("Please select the card you would like to use");
	    card_type.setBounds(10, 20, 300, 20);
	    //card_type.setAlignmentX(Component.CENTER_ALIGNMENT);
	    //lbl.setVisible(true); // Not needed

	    panel.add(card_type);

	    String[] types = {"Visa", "Mastercard", "American Express", "Debit"};

	    final JComboBox<String> cb = new JComboBox<String>(types);
	    //cb.setBounds(340, 20, 240, 20); // added code
	    cb.setAlignmentX(Component.CENTER_ALIGNMENT);// added code
	    cb.setVisible(true); // Not needed
	    panel.add(cb);
	    
	    JLabel card_number = new JLabel();
	    card_number.setBounds(10, 200, 200, 25);
	    card_number.setText("Please enter the number on your card: ");
	    panel.add(card_number);
	    
	    JTextField card_number_text = new JTextField();
	    card_number_text.setBounds(10, 240, 200, 25);
	    panel.add(card_number_text);
	    //frame.setLayout(null);
	    frame.setVisible(true); // added code
	    */
	 
	private void invalid_parking_id() {
		JPanel panel2 = new JPanel();
		JFrame frame2 = new JFrame();
		JLabel userError = new JLabel("You need to enter a valid ID.");
		userError.setBounds(45, 100, 260, 25);
		panel2.setLayout(null);
		panel2.setBackground(Color.blue.CYAN.brighter());
		frame2.setSize(300, 300);
		frame2.add(panel2);
		panel2.add(userError);
		frame2.setVisible(true);
	}
	private void select_request_time(String data, SignIn si, String flag) {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		JButton return_b;
		return_b = new JButton("Go Back");
		return_b.setBounds(600, 440, 100, 25);
		return_b.addActionListener(new GUI());
		panel.add(return_b);
		JLabel entry_date = new JLabel();
		JLabel entry_start = new JLabel();
		JLabel entry_finish = new JLabel();
		JLabel plate_number = new JLabel();
		JLabel error = new JLabel();
		JButton book = new JButton();
		JButton view = new JButton();
		JTextField userText0 = new JTextField(20);
		JTextField userText1 = new JTextField(20);
		JTextField userText2 = new JTextField(20);
		JTextField userText3 = new JTextField(20);
		userText0.setBounds(300, 10, 250, 25);
		userText1.setBounds(300, 50, 250, 25);
		userText2.setBounds(300, 90, 250, 25);
		userText3.setBounds(300, 130, 250, 25);

		panel.setLayout(null);
		panel.setBackground(Color.blue.CYAN.brighter());
		panel.add(userText0);
		panel.add(userText1);
		panel.add(userText2);
		panel.add(userText3);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		entry_date.setText("Enter the date (dd/mm/yyyy):");
		entry_date.setBounds(10, 10, 270, 25);
		entry_start.setText("Enter start time:");
		entry_start.setBounds(10, 50, 270, 25);
		entry_finish.setText("Enter finish time:");
		entry_finish.setBounds(10, 90, 270, 25);
		plate_number.setText("Enter your car's plate number:");
		plate_number.setBounds(10, 130, 270, 25);
		error.setBounds(10, 250, 250, 25);
		panel.add(error);

		String book_id = data;
		book.setText("Request " + "Parking Space " + data);
		book.setBounds(10, 210, 300, 30);
		view.setText("View Requests");
		view.setBounds(420, 210, 150, 30);
		JTextArea available_spots = new JTextArea();
		available_spots.setBounds(10, 360, 510, 100);
		panel.add(book);
		panel.add(view);
		panel.add(available_spots);
		panel.add(entry_date);
		panel.add(entry_start);
		panel.add(entry_finish);
		panel.add(plate_number);
		frame.setVisible(true);
		book.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					Order o = new Order();
					if (o.countNumberOfOrders(si.getEmail()) + o.countNumberOfRequests(si.getEmail()) > 2) {
						error.setText("You cannot book more than 3 spaces!");
					} else {
						TimeBooked t = new TimeBooked(userText1.getText(), userText2.getText());
						//si.book_space(Integer.parseInt(data), t, userText3.getText());
						o.addRequest(Integer.parseInt(data), t, userText0.getText(), si.getEmail());
					}
			}
		});
		view.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String orders = "";
				Order o = new Order();
				orders = o.viewRequests(si.getEmail());
				available_spots.setText(orders);
			}

		});
		return_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				book_space(si);
			}
		});
		
	}
	
}