package user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

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
	private int index;
	
	public user(String nickname, String password, String name, String surname, 
			int age, String email, String imgDir) {
		
		this.nickname = nickname;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.imgDir = imgDir;
		this.index = cnt;
		
		cnt++;
	}
	
	public static int getUserCount() {return cnt;}
	public String getNickname() {return this.nickname;}
	public String getName() {return this.name;}
	public String getSurname() {return this.surname;}
	public int getAge() {return this.age;}
	public String getEmail() {return this.email;}
	public String getImgdir() {return this.imgDir;}
	
	public void setImgdir(String imgDir) {this.imgDir = imgDir;}
	
}
