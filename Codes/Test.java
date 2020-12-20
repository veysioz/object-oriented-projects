import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	
	public static void main(String[] args) {
		Config config = new Config();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");

		config.getDatasets().forEach((n) -> {
			System.out.println("Dataset ID: " + n.getDatasetID().getID());
			System.out.println("Dataset Name: " + n.getDatasetName().getText());
			System.out.println("Maximum Number of Labels per Instance: " + n.getMaxNumLabsPerIns().getNumber());
			System.out.println("Class Labels:");
			n.getArListLab().forEach((m) -> System.out.println("\tLabel ID: " + m.getLabelID().getID() + " | Label Text: " + m.getLabelText().getText()));
			System.out.println("Instances:");
			n.getArListIns().forEach((m) -> System.out.println("\tInstance ID: " + m.getInstanceID().getID() + " | Instance Text: " + m.getInstanceText().getText()));
			System.out.print("Users:\n");
			config.getUsers().forEach((m) -> {
				System.out.println("\tUser ID: " + m.getUserID().getID() + " | Username: " + m.getUserName().getText() + " | User Type: " + m.getUserType().getText());
			});
			System.out.println();
		});
		
		System.out.println("Class Label Assignments:");
		config.getDatasets().forEach((n) -> {
			n.getArListIns().forEach((m) -> {
				config.getUsers().forEach((k) -> {
				    try {
				    	Date date = new Date();
				    	if(ccpPercent(config.getCpp()) && !k.getAssignedIns().isEmpty()) {
				    		Instance ins = k.getAssignedIns().get((int)(Math.random() * k.getAssignedIns().size()));
				    		System.out.print("\t Dataset ID: " + ins.getDatasetID().getID() + " | Instance ID: " + ins.getInstanceID().getID() + " | Class Label IDs: ");
				    		RandomLabelling randLab = new RandomLabelling(k, ins, date, config.getDatasets().get(ins.getDatasetID().getID()-1), true);
				    		System.out.print(" | User ID: " + k.getUserID().getID() + " | Date & Time: " + formatter.format(date));
				    		System.out.println(" | Consistency: " + randLab.getConsistency().getPercentage() + " %");
				    	}
				    	else {
				    		System.out.print("\t Dataset ID: " + n.getDatasetID().getID() + " | Instance ID: " + m.getInstanceID().getID() + " | Class Label IDs: ");
				    		new RandomLabelling(k, new Instance(m.getInstanceID(), m.getInstanceText(), n.getDatasetID()), date, n, false);
				    		System.out.println(" | User ID: " + k.getUserID().getID() + " | Date & Time: " + formatter.format(date));
				    	}
				    	
				    	Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
			});
		});
	}
	
	public static boolean ccpPercent(ConsCheckProb ccp) {
		int rand = (int)(Math.random() * 100) + 1;
		ccp = new ConsCheckProb((int)(ccp.getCCP() * 100));
		
		if(rand <= ccp.getCCP() && rand >= 0)
			return true;
		else
			return false;
	}
	
	/*static int counter, k;
	@SuppressWarnings("resource")
	private static void writeJSON(Dataset dataset, ArrayList<User> users, SimpleDateFormat formatter) throws IOException {

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
	}*/
}