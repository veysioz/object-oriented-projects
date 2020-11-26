import java.util.Scanner;

public class Test{
	static User userForLabeling = new User();
	
	public static void main(String[] args) {
		Dataset dataset = new Dataset();
		Scanner scanner = new Scanner(System.in);
		int selection = 0;
		
		while(true){
			System.out.println("1 => Random type");
			System.out.println("2 => Exit");
			System.out.print(Dataset.arListIns.get(Labelling.insNum + 1).getInstanceText() + ": ");
			selection = scanner.nextInt();
			
			if(selection == 1){
				userForLabeling.createUSer(userForLabeling.getUserClassJ().size() + 1, "RandomLabelingMechanism", "RandomBot");
				new RandomLabelling();
			}
			if(selection == 2 || Labelling.insNum == Dataset.arListIns.size() - 1){
				break;
			}
		}
		
		printOutput();
	}
	
	public static void printDataset() {
	
		Dataset.arListLab.forEach((n) -> System.out.println("Label ID: " + n.getLabelID() + " Label Text: " + n.getLabelText()));
		Dataset.arListIns.forEach((n) -> System.out.println("Instance ID: " + n.getInstanceID() + " Instance Text: " + n.getInstanceText()));
	}
	
	public static void printOutput() {
		System.out.println("Dataset ID: " + Dataset.datasetID);
		System.out.println("Dataset Name: " + Dataset.datasetName);
		System.out.println("Maximum Number of Labels per Instance: " + Dataset.maxNumLabsPerIns);
		System.out.println("Class Labels:");
		Dataset.arListLab.forEach((n) -> System.out.println("\tLabel ID: " + n.getLabelID() + " | Label Text: " + n.getLabelText()));
		System.out.println("Instances:");
		Dataset.arListIns.forEach((n) -> System.out.println("\tInstance ID: " + n.getInstanceID() + " | Instance Text: " + n.getInstanceText()));
		System.out.println("Class Label Assignments:");
		System.out.print("\t");
		for(int i = 0; i < Dataset.arListIns.size(); i++) {
			System.out.print("Instance ID: " + Dataset.arListIns.get(i).getInstanceID() + " | Class Label IDs: ");
			Dataset.arListIns.get(i).getAssignedLabels().forEach((n) -> System.out.print(n.getLabelID() + " "));
			if(i != Dataset.arListIns.size() - 1)
				System.out.print("\n\t");
		}
		System.out.println("\nUsers:");
		System.out.println("\t" + userForLabeling.getUserClassJ());
		
	}
}