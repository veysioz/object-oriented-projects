import org.json.simple.JSONObject;
public class User {
	JSONObject userArJ = new JSONObject();
	JSONObject userClassJ= new JSONObject();
	private int userId;
	private String userName;
	private String userType;

	public User(){

	}
	@SuppressWarnings("unchecked")
	public void createUSer(int id, String name , String type){
		this.userArJ.put("id",id)  ;
		this.userArJ.put("user name",name+id)  ;
		this.userArJ.put("user type",type);
		this.userClassJ.put(id,userArJ.clone());
	}
	public JSONObject getUserArJ() {
		return userArJ;
	}
	public void setUserArJ(JSONObject userArJ) {
		this.userArJ = userArJ;
	}
	public JSONObject getUserClassJ() {
		return userClassJ;
	}
	public void setUserClassJ(JSONObject userClassJ) {
		this.userClassJ = userClassJ;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
