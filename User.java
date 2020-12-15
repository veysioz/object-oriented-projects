import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class User {
	private int userID;
	private String userName;
	private String userType;
	private final Logger LOGGER = LogManager.getLogger();
	
	public User(int userID, String userName, String userType){
		this.userID = userID;
		this.userName = userName;
		this.userType = userType;
		LOGGER.info("{}-{} has created as type of {}",this::getUserID,this::getUserName,this::getUserType);
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
}