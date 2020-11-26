import java.util.ArrayList;

public class Instance {
	private int instanceID;
	private String instanceText;
	private ArrayList<Label> assignedLabels = new ArrayList<Label>();
	
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
	
	public ArrayList<Label> getAssignedLabels() {
		return assignedLabels;
	}

	public void setAssignedLabels(ArrayList<Label> assignedLabels) {
		this.assignedLabels = assignedLabels;
	}
}
