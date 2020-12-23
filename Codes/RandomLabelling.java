import java.util.ArrayList;
import java.util.Date;
import com.google.gson.JsonArray;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class RandomLabelling {
	private final Logger LOGGER = LogManager.getLogger();
	int consistency = 0;
	private JsonArray labelID;
	public JsonArray getLabelID() {
		return labelID;
	}

	public void setLabelID(JsonArray ar) {
		this.labelID = ar;
	}

	@SuppressWarnings("unchecked")
	public RandomLabelling(User user, Instance ins, Date assignedDate, Dataset dataset, boolean ccp) {

		ArrayList<Label> temp = (ArrayList<Label>)dataset.getArListLab().clone();
		int rand1 = (int)(Math.random() * dataset.getMaxNumLabsPerIns()) + 1, rand2;

		ArrayList<Label> temp1 = new ArrayList<Label>();
		for(int i = 0; i < rand1; i++) {
			rand2 = (int)(Math.random() * temp.size());
			temp1.add(temp.get(rand2));
			temp.remove(rand2);
		}

		if(ccp)
			consistency = calculateConsistency(ins.getAssignedLabs(), temp1);

		ins.setAssignedDate(assignedDate);
		ins.setAssignedLabs(temp1);

		if(!ccp)
			user.getAssignedIns().add(ins);
		JsonArray ar = new JsonArray();
		ins.getAssignedLabs().forEach((n) -> {System.out.print(n.getLabelID() + " ");
			ar.add(n.getLabelID());
		});
		setLabelID(ar);
		LOGGER.info("The user:{} labelled the instance:{} with class label:{} in dataset:{}.{}",user.getUserName(), ins.getInstanceID(),this.getLabelID(),dataset.getDatasetID(),dataset.getDatasetName());

	}

	public int getConsistency() {
		return consistency;
	}

	public void setConsistency(int consistency) {
		this.consistency = consistency;
	}

	@SuppressWarnings("unchecked")
	public int calculateConsistency(ArrayList<Label> before, ArrayList<Label> after) {
		ArrayList<Label> beforeTemp = (ArrayList<Label>)before.clone();
		ArrayList<Label> afterTemp = (ArrayList<Label>)after.clone();

		double max = Math.max(beforeTemp.size(), afterTemp.size());
		afterTemp.retainAll(beforeTemp);
		return (int)((afterTemp.size() / max) * 100);
	}
}