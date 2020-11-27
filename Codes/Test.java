import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

public class Test{
	static ArrayList<User> users = new ArrayList<User>();
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		Dataset dataset = new Dataset();
		Scanner scanner = new Scanner(System.in);
		int input = 0, exitNum, userID;
		
		System.out.println("Welcome to Data Labelling System!");
		while(true){
			if(Labelling.insNum == Dataset.arListIns.size() - 1)
				break;
			
			if(input == 0) {
				System.out.println("\n" + Dataset.arListIns.get(Labelling.insNum + 1).getInstanceText());
				System.out.print("   1 => Random Type\n   2 => Exit\n Your Input: ");
				exitNum = 2;
			} else {
				System.out.println("\n" + Dataset.arListIns.get(Labelling.insNum + 1).getInstanceText());
				System.out.print("   1 => Random Type(New User)\n   2 => Random Type(Same User)\n   3 => Ramdom Type(Registered User)\n   4 => Exit\n Your Input: ");
				exitNum = 4;
			}
			
			input = scanner.nextInt();
			
			if(input == exitNum) {
				break;
			} else if(input == 1) {
				User user = new User(users.size() + 1, "RandomLabelling" + (users.size() + 1), "RandomBot");
				new RandomLabelling(user, new Date());
				users.add(user);
			} else if(input == 2) {
				new RandomLabelling(Dataset.arListIns.get(Labelling.insNum).getUser(), new Date());
			} else if(input == 3) {
				System.out.print(" User ID: ");
				userID = scanner.nextInt();
				new RandomLabelling(users.get(userID - 1), new Date());
			}
		}
		
		printOutput();
	}
	
	public static void printOutput() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
		
		System.out.println("\nDataset ID: " + Dataset.datasetID);
		System.out.println("Dataset Name: " + Dataset.datasetName);
		System.out.println("Maximum Number of Labels per Instance: " + Dataset.maxNumLabsPerIns);
		System.out.println("Class Labels:");
		Dataset.arListLab.forEach((n) -> System.out.println("\tLabel ID: " + n.getLabelID() + " | Label Text: " + n.getLabelText()));
		System.out.println("Instances:");
		Dataset.arListIns.forEach((n) -> System.out.println("\tInstance ID: " + n.getInstanceID() + " | Instance Text: " + n.getInstanceText()));
		System.out.print("Class Label Assignments:\n\t");
		for(int i = 0; i < Dataset.arListIns.size(); i++) {
			System.out.print("Instance ID: " + Dataset.arListIns.get(i).getInstanceID() + " | Class Label IDs: ");
			Dataset.arListIns.get(i).getAssignedLabels().forEach((n) -> System.out.print(n.getLabelID() + " "));
			System.out.print("| User ID: " + Dataset.arListIns.get(i).getUser().getUserID());
			System.out.println(" | Date & Time: " + formatter.format(Dataset.arListIns.get(i).getDate()));
			if(i != Dataset.arListIns.size() - 1)
				System.out.print("\t");
		}
		System.out.print("Users:\n");
		for(int i = 0; i < users.size(); i++) {
			System.out.println("\tUser ID: " + users.get(i).getUserID() + " | Username: " + users.get(i).getUserName() + " | User Type: " + users.get(i).getUserType());
		}
	}
}