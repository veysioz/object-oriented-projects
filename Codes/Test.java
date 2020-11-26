import java.util.Scanner;

public class Test{
	private static User userForLabeling = new User();
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int selection = 0;
		while(true){
			System.out.println("Please selecet a user type .[For iteration one, just you can select number 1]");
			System.out.println("1 => Random type");
			System.out.println("2 => Start labeling");
			System.out.println("3 => Exit");
			System.out.print("Choose what you want :");
			selection = scan.nextInt();
			if(selection == 1){
				userForLabeling.createUSer(userForLabeling.getUserClassJ().size()+1,"RandomLabelingMechanism" , "RandomBot");
			}
			if(selection == 2){

			}
			if(selection == 3){
				break;
			}

		}

		System.out.println(userForLabeling.getUserClassJ());
//DATASET VERİLERİ KULLANMAK İÇİN ÖRNEK
//		Dataset dset = new Dataset();
//		System.out.println("getDataSetId   :"+dset.getDataSetId());
//		System.out.println("getDatasetName :"+dset.getDatasetName());
//		System.out.println("MaxNumberOfLabelsPerInstance: "+dset.getMaxNumberOfLabelsPerInstance());
//		dset.getArListLab().forEach((n) -> System.out.println("Label ID: " + n.getLabelID() + " Label Text: " + n.getLabelText()));
//		dset.getArListIns().forEach((n) -> System.out.println("Instance ID: " + n.getInstanceID() + " Instance Text: " + n.getInstanceText()));
//
//		System.out.println("\n");
//
//		Dataset dset1 = new Dataset();
//		System.out.println(dset1.getDataSetId());
//		System.out.println(dset1.getDatasetName());
//		System.out.println(dset1.getMaxNumberOfLabelsPerInstance());
//		dset1.getArListLab().forEach((n) -> System.out.println("Label ID: " + n.getLabelID() + " Label Text: " + n.getLabelText()));
//		dset1.getArListIns().forEach((n) -> System.out.println("Instance ID: " + n.getInstanceID() + " Instance Text: " + n.getInstanceText()));
	}
	public static User getUserForLabeling() {
		return userForLabeling;
	}
	public static void setUserForLabeling(User userForLabeling) {
		Test.userForLabeling = userForLabeling;
	}

}