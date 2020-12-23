import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class Label {
	private int labelID;
	private String labelText;
	private final Logger LOGGER = LogManager.getLogger();
	public Label(int labelID, String labelText){
		this.labelID = labelID;
		this.labelText = labelText;
		LOGGER.info("The label {}-{} created.",this.getLabelID(),this.getLabelText());
	}

	public int getLabelID() {
		return labelID;
	}

	public void setLabelID(int labelID) {
		this.labelID = labelID;
	}

	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String labelText) {
		this.labelText = labelText;
	}
}