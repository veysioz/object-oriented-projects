import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
	static ArrayList<User> users = new ArrayList<User>();
	static FileWriter fileWrite;

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) {
		Dataset dataset = new Dataset();
		Scanner scanner = new Scanner(System.in);
		int input = 0, userID;
		boolean start = true;

		System.out.println("Welcome to Data Labelling System!");
		while (true) {
			if (Labelling.insNum == Dataset.arListIns.size() - 1)
				break;
			else {
				System.out.println("\n" + Dataset.arListIns.get(Labelling.insNum + 1).getInstanceText());
				
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
				new RandomLabelling(user, new Date());
				users.add(user);
			} else if (input == 2) {
				new RandomLabelling(Dataset.arListIns.get(Labelling.insNum).getUser(), new Date());
			} else if (input == 3) {
				System.out.print(" User ID: ");
					userID = scanner.nextInt();
					new RandomLabelling(users.get(userID - 1), new Date());
			}
		}
		
		printOutput();
	}

	static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");

	public static void printOutput() {
		System.out.println("\nDataset ID: " + Dataset.datasetID);
		System.out.println("Dataset Name: " + Dataset.datasetName);
		System.out.println("Maximum Number of Labels per Instance: " + Dataset.maxNumLabsPerIns);
		System.out.println("Class Labels:");
		Dataset.arListLab.forEach(
				(n) -> System.out.println("\tLabel ID: " + n.getLabelID() + " | Label Text: " + n.getLabelText()));
		System.out.println("Instances:");
		Dataset.arListIns.forEach((n) -> System.out
				.println("\tInstance ID: " + n.getInstanceID() + " | Instance Text: " + n.getInstanceText()));
		System.out.print("Class Label Assignments:\n\t");
		for (int i = 0; i < Dataset.arListIns.size(); i++) {
			System.out.print("Instance ID: " + Dataset.arListIns.get(i).getInstanceID() + " | Class Label IDs: ");
			Dataset.arListIns.get(i).getAssignedLabels().forEach((n) -> System.out.print(n.getLabelID() + " "));
			System.out.print("| User ID: " + Dataset.arListIns.get(i).getUser().getUserID());
			System.out.println(" | Date & Time: " + formatter.format(Dataset.arListIns.get(i).getDate()));
			if (i != Dataset.arListIns.size() - 1)
				System.out.print("\t");
		}
		System.out.print("Users:\n");
		for (int i = 0; i < users.size(); i++) {
			System.out.println("\tUser ID: " + users.get(i).getUserID() + " | Username: " + users.get(i).getUserName()
					+ " | User Type: " + users.get(i).getUserType());
		}
		try {
			writeJsonToFile();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	static int k, counter;
	private static void writeJsonToFile() throws IOException {

		File file = new File("Output.json");
		file.createNewFile();

		fileWrite = new FileWriter(file);

		fileWrite.write("{\n\"Dataset ID\": " + Dataset.datasetID + ",\n");

		fileWrite.write("\"Dataset Name\": " + "\"" + Dataset.datasetName + "\"" + ",");
		fileWrite.flush();
		fileWrite.write("\"Maximum Number of Labels per Instance\": " + Dataset.maxNumLabsPerIns + ",");
		fileWrite.flush();

		fileWrite.write("\n\"Class Labels\":[\n");
		fileWrite.flush();
		counter = 1;
		Dataset.arListLab.forEach((n) -> {
			try {
				if (Dataset.arListLab.size() != counter) {
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
		Dataset.arListIns.forEach((n) -> {
			try {
				if (Dataset.arListIns.size() != counter) {
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

		for (int i = 0; i < Dataset.arListIns.size(); i++) {
			fileWrite.write(
					"{\"Instance ID\": " + Dataset.arListIns.get(i).getInstanceID() + ", \"Class Label IDs\": [");
			fileWrite.flush();
			counter = 1;
			k = i;
			Dataset.arListIns.get(i).getAssignedLabels().forEach((n) -> {

				try {

					if (Dataset.arListIns.get(k).getAssignedLabels().size() != counter) {
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
			fileWrite.write("\"User ID\": " + Dataset.arListIns.get(i).getUser().getUserID() + ", ");
			fileWrite.flush();
			if (i != Dataset.arListIns.size() - 1) {
				fileWrite
						.write("\"Date & Time\": \"" + formatter.format(Dataset.arListIns.get(i).getDate()) + "\"},\n");
				fileWrite.flush();
			} else {
				fileWrite.write(
						"\"Date & Time\": \"" + formatter.format(Dataset.arListIns.get(i).getDate()) + "\"}\n],");
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