import java.util.ArrayList;

public class User {
	private int userID;
	private String userName;
	private String userType;
	private ArrayList<Instance> assignedIns = new ArrayList<Instance>();
	
	public User(int userID, String userName, String userType){
		this.userID = userID;
		this.userName = userName;
		this.userType = userType;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserId(int userID) {
		this.userID = userID;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserType() {
		return userType;
	}
	
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	public ArrayList<Instance> getAssignedIns() {
		return assignedIns;
	}
}