import java.util.ArrayList;

public class User {
	private ID userID;
	private Text userName;
	private Text userType;
	private ArrayList<Instance> assignedIns = new ArrayList<Instance>();
	
	public User(ID userID, Text userName, Text userType){
		this.userID = userID;
		this.userName = userName;
		this.userType = userType;
	}
	
	public ID getUserID() {
		return userID;
	}
	
	public void setUserId(ID userID) {
		this.userID = userID;
	}
	
	public Text getUserName() {
		return userName;
	}
	
	public void setUserName(Text userName) {
		this.userName = userName;
	}
	
	public Text getUserType() {
		return userType;
	}
	
	public void setUserType(Text userType) {
		this.userType = userType;
	}
	
	public ArrayList<Instance> getAssignedIns() {
		return assignedIns;
	}
}