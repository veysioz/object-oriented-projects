import java.util.ArrayList;
import java.util.Date;

public class Instance {
	private ID instanceID;
	private Text instanceText;
	private ArrayList<Label> assignedLabs = new ArrayList<Label>();
	private Date assignedDate;
	private ID datasetID;
	
	public Instance(ID instanceID, Text instanceText, ID datasetID){
		this.instanceID = instanceID;
		this.instanceText = instanceText;
		this.datasetID = datasetID;
	}

	public ID getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(ID instanceID) {
		this.instanceID = instanceID;
	}

	public Text getInstanceText() {
		return instanceText;
	}

	public void setInstanceText(Text instanceText) {
		this.instanceText = instanceText;
	}
	
	public ArrayList<Label> getAssignedLabs() {
		return assignedLabs;
	}
	
	public void setAssignedLabs(ArrayList<Label> assignedLabs) {
		this.assignedLabs = assignedLabs;
	}
	
	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}
	
	public ID getDatasetID() {
		return datasetID;
	}
	
	public void setDataset(ID datasetID) {
		this.datasetID = datasetID;
	}
}
