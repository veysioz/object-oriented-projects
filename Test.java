
public class Test {

	public static void main(String[] args) {
		Dataset dset = new Dataset();
		
		dset.getArListLab().forEach((n) -> System.out.println("Label ID: " + n.getLabelID() + " Label Text: " + n.getLabelText()));
		dset.getArListIns().forEach((n) -> System.out.println("Instance ID: " + n.getInstanceID() + " Instance Text: " + n.getInstanceText()));
		
		System.out.println("\n");
		
		Dataset dset1 = new Dataset();
		
		dset1.getArListLab().forEach((n) -> System.out.println("Label ID: " + n.getLabelID() + " Label Text: " + n.getLabelText()));
		dset1.getArListIns().forEach((n) -> System.out.println("Instance ID: " + n.getInstanceID() + " Instance Text: " + n.getInstanceText()));
	}

}
