import java.util.ArrayList;
import java.util.Date;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class Instance {
	private final Logger LOGGER = LogManager.getLogger();
	private int instanceID;
	private String instanceText;
	private ArrayList<Label> assignedLabels = new ArrayList<Label>();
	private User user;
	private Date date;
	
	public Instance(int instanceID, String instanceText){
		this.instanceID = instanceID;
		this.instanceText = instanceText;
		LOGGER.info("The instance \" {}-{} \" is created.",this::getInstanceID,this::getInstanceText);
	}

	public int getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(int instanceID) {
		this.instanceID = instanceID;
	}

	public String getInstanceText() {
		return instanceText;
	}

	public void setInstanceText(String instanceText) {
		this.instanceText = instanceText;
	}
	
	public ArrayList<Label> getAssignedLabels() {
		return assignedLabels;
	}

	public void setAssignedLabels(ArrayList<Label> assignedLabels) {
		this.assignedLabels = assignedLabels;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
