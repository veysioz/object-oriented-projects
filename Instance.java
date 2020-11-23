
public class Instance {
	private int instanceID;
	private String instanceText;
	
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
}
