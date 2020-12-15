
public class Label {
	private int labelID;
	private String labelText;
	
	public Label(int labelID, String labelText){
		this.labelID = labelID;
		this.labelText = labelText;
	}

	public int getLabelID() {
		return labelID;
	}

	public void setLabelID(int instanceID) {
		this.labelID = instanceID;
	}

	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String instanceText) {
		this.labelText = instanceText;
	}
}