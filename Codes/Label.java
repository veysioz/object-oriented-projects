import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Label {
	private int labelID;
	private String labelText;
	private final Logger logger = LogManager.getLogger();
	public Label(int labelID, String labelText){
		this.labelID = labelID;
		this.labelText = labelText;
		logger.info("The label {}-{} created.",this::getLabelID,this::getLabelText);
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