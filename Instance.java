import java.util.ArrayList;
import java.util.Date;

public class Instance {
	private int instanceID;
	private String instanceText;
	private ArrayList<Label> assignedLabs = new ArrayList<Label>();
	private Date assignedDate;
	
	public Instance(int instanceID, String instanceText){
		this.instanceID = instanceID;
		this.instanceText = instanceText;
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
	
	public ArrayList<Label> getAssignedLabs() {
		return assignedLabs;
	}
	
	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}
}
