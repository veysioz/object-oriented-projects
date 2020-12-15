import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

	@SuppressWarnings({ "resource" })
	public static void main(String[] args) {
		ArrayList<User> users = new ArrayList<User>();
		Dataset dataset = new Dataset();
		Scanner scanner = new Scanner(System.in);
		int input = 0, userID, insNum = -1;
		boolean start = true;

		System.out.println("Welcome to Data Labelling System!");
		while (true) {
			if (insNum == dataset.getArListIns().size() - 1)
				break;
			else {
				System.out.println("\n" + dataset.getArListIns().get(insNum + 1).getInstanceText());
				
				if(start) {
					System.out.print("   1 => Random Type\n Your Input: ");
					input = scanner.nextInt();
					while(input != 1) {
						System.out.print(" Enter a valid choice: ");
						input = scanner.nextInt();
					}
					start = false;
				} else {
					System.out.print("   1 => Random Type(New User)\n   2 => Random Type(Same User)\n   3 => Ramdom Type(Registered User)\n Your Input: ");
					input = scanner.nextInt();
					while(input < 1 || input > 3) {
						System.out.print(" Enter a valid choice: ");
						input = scanner.nextInt();
					}
				}
			}

			if (input == 1) {
				User user = new User(users.size() + 1, "RandomLabelling" + (users.size() + 1), "RandomBot");
				new RandomLabelling(user, new Date(), dataset, ++insNum);
				users.add(user);
			} else if (input == 2) {
				new RandomLabelling(dataset.getArListIns().get(insNum).getUser(), new Date(), dataset, ++insNum);
			} else if (input == 3) {
				System.out.print(" User ID: ");
				userID = scanner.nextInt();
				while(userID > users.size()) {
					System.out.print(" Enter a valid user ID: ");
					userID = scanner.nextInt();
				}
				new RandomLabelling(users.get(userID - 1), new Date(), dataset, ++insNum);
			}
		}
		
		printOutput(dataset, users);
	}

	public static void printOutput(Dataset dataset, ArrayList<User> users) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
		
		System.out.println("\nDataset ID: " + dataset.getDatasetID());
		System.out.println("Dataset Name: " + dataset.getDatasetName());
		System.out.println("Maximum Number of Labels per Instance: " + dataset.getMaxNumLabsPerIns());
		System.out.println("Class Labels:");
		dataset.getArListLab().forEach(
				(n) -> System.out.println("\tLabel ID: " + n.getLabelID() + " | Label Text: " + n.getLabelText()));
		System.out.println("Instances:");
		dataset.getArListIns().forEach((n) -> System.out
				.println("\tInstance ID: " + n.getInstanceID() + " | Instance Text: " + n.getInstanceText()));
		System.out.print("Class Label Assignments:\n\t");
		for (int i = 0; i < dataset.getArListIns().size(); i++) {
			System.out.print("Instance ID: " + dataset.getArListIns().get(i).getInstanceID() + " | Class Label IDs: ");
			dataset.getArListIns().get(i).getAssignedLabels().forEach((n) -> System.out.print(n.getLabelID() + " "));
			System.out.print("| User ID: " + dataset.getArListIns().get(i).getUser().getUserID());
			System.out.println(" | Date & Time: " + formatter.format(dataset.getArListIns().get(i).getDate()));
			if (i != dataset.getArListIns().size() - 1)
				System.out.print("\t");
		}
		System.out.print("Users:\n");
		for (int i = 0; i < users.size(); i++) {
			System.out.println("\tUser ID: " + users.get(i).getUserID() + " | Username: " + users.get(i).getUserName()
					+ " | User Type: " + users.get(i).getUserType());
		}
		try {
			writeJsonToFile(dataset, users, formatter);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	static int counter, k;
	@SuppressWarnings("resource")
	private static void writeJsonToFile(Dataset dataset, ArrayList<User> users, SimpleDateFormat formatter) throws IOException {

		File file = new File("Output.json");
		FileWriter fileWrite;
		
		file.createNewFile();

		fileWrite = new FileWriter(file);

		fileWrite.write("{\n\"Dataset ID\": " + dataset.getDatasetID() + ",\n");

		fileWrite.write("\"Dataset Name\": " + "\"" + dataset.getDatasetName() + "\"" + ",");
		fileWrite.flush();
		fileWrite.write("\"Maximum Number of Labels per Instance\": " + dataset.getMaxNumLabsPerIns() + ",");
		fileWrite.flush();

		fileWrite.write("\n\"Class Labels\":[\n");
		fileWrite.flush();
		counter = 1;
		dataset.getArListLab().forEach((n) -> {
			try {
				if (dataset.getArListLab().size() != counter) {
					fileWrite.write("{\"Label ID\": " + n.getLabelID() + ", \"Label Text\": " + "\"" + n.getLabelText()
							+ "\"},\n");
					fileWrite.flush();
				} else {
					fileWrite.write("{\"Label ID\": " + n.getLabelID() + ", \"Label Text\": " + "\"" + n.getLabelText()
							+ "\"}");
					fileWrite.flush();

				}
				counter++;

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		fileWrite.write("\n],");
		fileWrite.flush();

		fileWrite.write("\n\"Instances\":[\n");
		fileWrite.flush();
		counter = 1;
		dataset.getArListIns().forEach((n) -> {
			try {
				if (dataset.getArListIns().size() != counter) {
					fileWrite.write("{\"Instance ID\":" + n.getInstanceID() + ", \"Instance Text\": \""
							+ n.getInstanceText() + "\"},\n");
					fileWrite.flush();
				} else {
					fileWrite.write("{\"Instance ID\":" + n.getInstanceID() + ", \"Instance Text\": \""
							+ n.getInstanceText() + "\"}\n],");
					fileWrite.flush();

				}
				counter++;
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		fileWrite.write("\n\"Class Label Assignments\":[\n");
		fileWrite.flush();

		for (int i = 0; i < dataset.getArListIns().size(); i++) {
			fileWrite.write(
					"{\"Instance ID\": " + dataset.getArListIns().get(i).getInstanceID() + ", \"Class Label IDs\": [");
			fileWrite.flush();
			counter = 1;
			k = i;
			dataset.getArListIns().get(i).getAssignedLabels().forEach((n) -> {

				try {
					if (dataset.getArListIns().get(k).getAssignedLabels().size() != counter) {
						fileWrite.write(n.getLabelID() + " ,");
						fileWrite.flush();

					} else {
						fileWrite.write(n.getLabelID() + "],");
						fileWrite.flush();

					}
					counter++;
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			fileWrite.write("\"User ID\": " + dataset.getArListIns().get(i).getUser().getUserID() + ", ");
			fileWrite.flush();
			if (i != dataset.getArListIns().size() - 1) {
				fileWrite
						.write("\"Date & Time\": \"" + formatter.format(dataset.getArListIns().get(i).getDate()) + "\"},\n");
				fileWrite.flush();
			} else {
				fileWrite.write(
						"\"Date & Time\": \"" + formatter.format(dataset.getArListIns().get(i).getDate()) + "\"}\n],");
				fileWrite.flush();
			}
		}

		fileWrite.write("\n\"Users\":[\n");
		fileWrite.flush();
		for (int i = 0; i < users.size(); i++) {
			if (i == users.size() - 1) {
				fileWrite.write(
						"{\"User ID\": " + users.get(i).getUserID() + ", \"Username\": \"" + users.get(i).getUserName()
								+ "\", \"User Type\": \"" + users.get(i).getUserType() + "\"}]\n}");
				fileWrite.flush();
			} else {
				fileWrite.write("{\"User ID\": " + users.get(i).getUserID() + ", \"Username\": \""
						+ users.get(i).getUserName() + "\", \"User Type\": \"" + users.get(i).getUserType() + "\"},\n");
				fileWrite.flush();
			}

		}
	}
}