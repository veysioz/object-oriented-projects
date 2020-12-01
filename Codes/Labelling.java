import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class Labelling {
	public static int insNum = -1;
	private int maxAssignedLabel;
	private final Logger logger = LogManager.getLogger();
	public Labelling() {
		if(Dataset.maxNumLabsPerIns > Dataset.arListLab.size())
			maxAssignedLabel = Dataset.arListLab.size();
		else
			maxAssignedLabel = Dataset.maxNumLabsPerIns;
		
		insNum++;
	}
	
	public int getMaxAssignedLabel() {
		return maxAssignedLabel;
	}
	
	public void setMaxAssignedLabel(int maxAssignedLabel) {
		this.maxAssignedLabel = maxAssignedLabel;
	}
}
