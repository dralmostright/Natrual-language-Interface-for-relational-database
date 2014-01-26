import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({"serial"})
public class LoginPage extends JFrame {

	private JTextField txtname;
	private JPasswordField txtpass;
	
	public LoginPage(){
		super("ASCOL's Natural Lnaguage Interface for Databases (V.1.0.1)");
		this.setContentPane(new JLabel(new ImageIcon("mainpage.png")));
		this.setLayout(new FlowLayout());
		this.add(MainPanel());
		this.pack();
		this.setSize(808, 530);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
	}
	
	private JPanel MainPanel(){
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(350, 0, 0, 0));
		panel.setLayout(new GridLayout(3, 1, 5, 5));
		panel.add(this.UserName());
		panel.add(this.UserPassword());
		panel.add(this.InOut());
		panel.setOpaque(false);
		return panel;
	}
	
	private JPanel UserName(){
		JPanel name = new JPanel();
		name.setLayout(new GridLayout(1,2));
		JLabel lblname = new JLabel("Username :-");
		txtname = new JTextField(15);
		name.add(lblname);
		name.add(txtname);
		name.setOpaque(false);
		return name;
	}
	
	private JPanel UserPassword(){
		JPanel name = new JPanel();
		name.setLayout(new GridLayout(1,2));
		JLabel lblpass = new JLabel("Password :-");
		txtpass = new JPasswordField(15);
		name.add(lblpass);
		name.add(txtpass);
		name.setOpaque(false);
		LoginHandler hdl = new LoginHandler();
		txtpass.addActionListener(hdl);
		return name;
	}
	
	public JPanel InOut(){
		JPanel name = new JPanel();
		name.setLayout(new GridLayout(1,2));
		JButton btnLogin = new JButton("Login");
		JButton btnReset = new JButton("Reset");
		name.add(btnLogin);
		name.add(btnReset);
		LoginHandler hdl = new LoginHandler();
		btnLogin.addActionListener(hdl);
		name.setOpaque(false);
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtname.setText("");
				txtpass.setText("");
			}
		});
		return name;
	}
	
	public class LoginHandler implements ActionListener{

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
		String uname = txtname.getText();
		String pass = txtpass.getText();
		if(uname.equals("") || pass.equals("")){
			JOptionPane.showMessageDialog(null, "Sorry! All fields are required.", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons\\error.png"));
		}
		else if(SqlLibrary.TestUser(uname, pass,"project")){
			//JOptionPane.showMessageDialog(null, "The work is in progress.", "Success", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons\\correct.png"));
			dispose();
			new Dashboard();
		}
		else{
			JOptionPane.showMessageDialog(null, "Sorry! Your credentials are not valid.", "Error", JOptionPane.ERROR_MESSAGE, new ImageIcon("icons\\error.png"));
		}
		
		}
		
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception unused) {
			; // Ignore exception because we can't do anything. Will use
				// default.
		}
		new LoginPage();
	}

}