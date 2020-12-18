import java.util.ArrayList;
import java.util.Date;

public class RandomLabelling {
	
	@SuppressWarnings("unchecked")
	public RandomLabelling(User user, Instance ins, Date assignedDate, Dataset dataset) {
		ArrayList<Label> temp = (ArrayList<Label>)dataset.getArListLab().clone();
		int rand1 = (int)(Math.random() * dataset.getMaxNumLabsPerIns()) + 1, rand2;
		for(int i = 0; i < rand1; i++) {
			rand2 = (int)(Math.random() * temp.size());
			ins.getAssignedLabs().add(temp.get(rand2));
			temp.remove(rand2);
		}
		
		ins.setAssignedDate(assignedDate);
		user.getAssignedIns().add(ins);
		
		ins.getAssignedLabs().forEach((n) -> System.out.print(n.getLabelID() + " "));
	}
}
