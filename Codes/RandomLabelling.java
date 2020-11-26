import java.util.ArrayList;

public class RandomLabelling extends Labelling {
	public RandomLabelling() {
		ArrayList<Label> temp = (ArrayList)Dataset.arListLab.clone();
		int forRand = (int)(Math.random() * super.getMaxAssignedLabel()) + 1;
		for(int i = 0; i < forRand; i++) {
			int rand = (int)(Math.random() * temp.size());
			Dataset.arListIns.get(insNum).getAssignedLabels().add(temp.get(rand));
			temp.remove(rand);
		}
	}
}
