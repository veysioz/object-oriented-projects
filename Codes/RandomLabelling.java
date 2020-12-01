import java.util.ArrayList;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class RandomLabelling extends Labelling {
	private final Logger logger = LogManager.getLogger();
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RandomLabelling(User user, Date date) {
		ArrayList<Label> temp = (ArrayList)Dataset.arListLab.clone();
		int forRand = (int)(Math.random() * super.getMaxAssignedLabel()) + 1;
		for(int i = 0; i < forRand; i++) {
			int rand = (int)(Math.random() * temp.size());
			Dataset.arListIns.get(insNum).getAssignedLabels().add(temp.get(rand));
			temp.remove(rand);
		}
		Dataset.arListIns.get(insNum).setUser(user);
		Dataset.arListIns.get(insNum).setDate(date);
		logger.info("The instance-{} labelled to {}-{}-{}",Dataset.arListIns.get(insNum).getInstanceID(),user.getUserID(),user.getUserName(),user.getUserType());
	}
}
