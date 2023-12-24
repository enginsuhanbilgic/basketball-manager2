package user;

import java.io.FileNotFoundException;
import java.util.Formatter;

import userGUI.*;

public class user {
	
	private String nickname;
	private String password;
	private String name;
	private String surname;
	private int age;
	private String email;
	private String imgDir="src//defaultProfileImage.jpg";
	private static int cnt=0;
	
	public user(String nickname, String password, String name, String surname, 
			int age, String email, String imgDir) throws FileNotFoundException {
		
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.imgDir = imgDir;
		
		Formatter output = new Formatter(String.format("src//user//user.txt"));
		if(cnt==0) {
			output.format("Nickname / Password / Name / Surname / Age / Email / Image Directory\n");
		}
		output.format(String.format("%s,%s,%s,%s,%d,%s,%s\n",nickname, password, name, surname, age, email, imgDir));
		cnt++;
	}
}
