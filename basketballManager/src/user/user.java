package user;
/**
 * User class to create users that can be used to 
 * log in or register to the application
 */
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
	
	/**
	 * 
	 * User class constructor with parameters accordingly
	 * 
	 * @author EBILGIC20
	 * 
	 * @param nickname
	 * @param password
	 * @param name
	 * @param surname
	 * @param age
	 * @param email
	 * @param imgDir
	 */
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
	
	/**
	 * Getter methods for user
	 * @return of different types
	 */
	public static int getUserCount() {return cnt;}
	public String getNickname() {return this.nickname;}
	public String getName() {return this.name;}
	public String getSurname() {return this.surname;}
	public int getAge() {return this.age;}
	public String getEmail() {return this.email;}
	public String getImgdir() {return this.imgDir;}
	
	/**
	 * Image Directory setter method
	 * @param imgDir
	 */
	public void setImgdir(String imgDir) {this.imgDir = imgDir;}
	
}
