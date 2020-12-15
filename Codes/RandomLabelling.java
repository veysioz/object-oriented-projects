import java.util.ArrayList;
import java.util.Date;

public class RandomLabelling {
	private int insNum;
	private int maxAssignedLabel;
	private Dataset dataset;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RandomLabelling(User user, Date date, Dataset dataset, int insNum) {
		if(dataset.getMaxNumLabsPerIns() > dataset.getArListLab().size())
			maxAssignedLabel = dataset.getArListLab().size();
		else
			maxAssignedLabel = dataset.getMaxNumLabsPerIns();
		
		ArrayList<Label> temp = (ArrayList)dataset.getArListLab().clone();
		int forRand = (int)(Math.random() * maxAssignedLabel) + 1;
		for(int i = 0; i < forRand; i++) {
			int rand = (int)(Math.random() * temp.size());
			dataset.getArListIns().get(insNum).getAssignedLabels().add(temp.get(rand));
			temp.remove(rand);
		}
		
		dataset.getArListIns().get(insNum).setUser(user);
		dataset.getArListIns().get(insNum).setDate(date);
	}
	
	public int getInsNum() {
		return insNum;
	}
	
	public void setInsNum(int insNum) {
		this.insNum = insNum;
	}
	
	public int getMaxAssignedLabel() {
		return maxAssignedLabel;
	}
	
	public void setMaxAssignedLabel(int maxAssignedLabel) {
		this.maxAssignedLabel = maxAssignedLabel;
	}
	
	public Dataset getDataset() {
		return dataset;
	}
	
	public void setDataset(Dataset dataset) {
		this.dataset = dataset;
	}
}
