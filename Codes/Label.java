
public class Label {
	private ID labelID;
	private Text labelText;
	
	public Label(ID labelID, Text labelText){
		this.labelID = labelID;
		this.labelText = labelText;
	}

	public ID getLabelID() {
		return labelID;
	}

	public void setLabelID(ID instanceID) {
		this.labelID = instanceID;
	}

	public Text getLabelText() {
		return labelText;
	}

	public void setLabelText(Text instanceText) {
		this.labelText = instanceText;
	}
}