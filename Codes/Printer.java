import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.simple.JSONObject;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Printer extends Thread{
	private JSONObject obj ;
	ArrayList<JSONObject> lblAssigment = new ArrayList<JSONObject>();
	private final Logger LOGGER = LogManager.getLogger();
	@SuppressWarnings({ "static-access", "unchecked" })
	public  void writeToConsole(){
		Config config = new Config();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");

	    obj = new JSONObject();
		ArrayList<JSONObject> dataset = new ArrayList<JSONObject>();

		config.getDatasets().forEach((n) -> {
			writeToJsonFile(obj);
			System.out.println("Dataset ID: " + n.getDatasetID());
			System.out.println("Dataset Name: " + n.getDatasetName());
			System.out.println("Maximum Number of Labels per Instance: " + n.getMaxNumLabsPerIns());
			JSONObject datasetInfo = new JSONObject();
			datasetInfo.put("Dataset ID", n.getDatasetID());
			datasetInfo.put("Dataset Name", n.getDatasetName());
			datasetInfo.put("Maximum Number of Labels per Instance", n.getMaxNumLabsPerIns());

			ArrayList<JSONObject> objLabel = new ArrayList<JSONObject>();
			System.out.println("Class Labels:");
			n.getArListLab().forEach((m) -> {
				writeToJsonFile(obj);
				System.out.println("\tLabel ID: " + m.getLabelID() + " | Label Text: " + m.getLabelText());
				JSONObject lbl = new JSONObject();
				lbl.put("Label ID", m.getLabelID());
				lbl.put("Label Text", m.getLabelText());
				objLabel.add(lbl);
				});
			datasetInfo.put("Labels",objLabel);

			ArrayList<JSONObject> objInstance = new ArrayList<JSONObject>();
			System.out.println("Instances:[");
			n.getArListIns().forEach((m) ->{
				writeToJsonFile(obj);
				System.out.println("\tInstance ID: " + m.getInstanceID() + " | Instance Text: " + m.getInstanceText());
				JSONObject instance = new JSONObject();
				instance.put("Instance ID", m.getInstanceID());
				instance.put("Instance Text", m.getInstanceText());
				objInstance.add(instance);
				LOGGER.info("The Instance:{} in {} is handled",m.getInstanceID(),n.getDatasetName());
				});
			datasetInfo.put("Instance",objInstance);

			ArrayList<JSONObject> objUser = new ArrayList<JSONObject>();
			System.out.print("Users:\n");
			config.getUsers().forEach((m) -> {
				writeToJsonFile(obj);
				System.out.println("\tUser ID: " + m.getUserID() + " | Username: " + m.getUserName() + " | User Type: " + m.getUserType());
				JSONObject usr = new JSONObject();
				usr.put("User ID", m.getUserID());
				usr.put("Username", m.getUserName());
				usr.put("User Type", m.getUserType());
				objUser.add(usr);

				});
			datasetInfo.put("User",objUser);
			dataset.add(datasetInfo);
		});
		obj.put("DATASETS", dataset);
	    writeToJsonFile(obj);
//		ArrayList<JSONObject> lblAssigment = new ArrayList<JSONObject>();
		System.out.println("Class Label Assignments:");
		config.getDatasets().forEach((n) -> {
			n.getArListIns().forEach((m) -> {
				config.getUsers().forEach((k) -> {
				    try {
				    	writeToJsonFile(obj);
				    	JSONObject labelAssigment = new JSONObject();
				    	Date date = new Date();
				    	if(new Test().ccpPercent(config.getCpp()) && !k.getAssignedIns().isEmpty()) {
				    		Instance ins = k.getAssignedIns().get((int)(Math.random() * k.getAssignedIns().size()));
				    		System.out.print("\t Dataset ID: " + ins.getDatasetID() + " | Instance ID: " + ins.getInstanceID() + " | Class Label IDs: ");
				    		RandomLabelling randLab = new RandomLabelling(k, ins, date, config.getDatasets().get(ins.getDatasetID()-1), true);
				    		System.out.print(" | User ID: " + k.getUserID() + " | Date & Time: " + formatter.format(date));
				    		System.out.println(" | Consistency: " + randLab.getConsistency() + " %");
				    		labelAssigment.put("Dataset ID", ins.getDatasetID());
				    		labelAssigment.put("Instance ID", ins.getInstanceID());
				    		labelAssigment.put("Class Label IDs", randLab.getLabelID());
				    		labelAssigment.put("User ID", k.getUserID());
				    		labelAssigment.put(" Date & Time", formatter.format(date));
				    		labelAssigment.put(" Consistency", randLab.getConsistency() + " %");
				    		lblAssigment.add(labelAssigment);
				    		obj.put("Class Label Assignments", lblAssigment);
							LOGGER.info("Consistency of Labelling:%{}",randLab.getConsistency());
				    		writeToJsonFile(obj);
				    	}
				    	else {
				    		System.out.print("\t Dataset ID: " + n.getDatasetID() + " | Instance ID: " + m.getInstanceID() + " | Class Label IDs: ");
				    		RandomLabelling randLab=new RandomLabelling(k, new Instance(m.getInstanceID(), m.getInstanceText(), n.getDatasetID()), date, n, false);
				    		System.out.println(" | User ID: " + k.getUserID() + " | Date & Time: " + formatter.format(date));
				    		labelAssigment.put("Dataset ID", n.getDatasetID());
				    		labelAssigment.put("Instance ID", m.getInstanceID());
				    		labelAssigment.put("Class Label IDs", randLab.getLabelID());
				    		labelAssigment.put("User ID", k.getUserID());
				    		labelAssigment.put(" Date & Time", formatter.format(date));
				    		lblAssigment.add(labelAssigment);
				    		obj.put("Class Label Assignments", lblAssigment);
				    		writeToJsonFile(obj);
				    	}
				    	System.out.println("");
				    	Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
			});
		});
		obj.put("Class Label Assignments", lblAssigment);
		writeToJsonFile(obj);
    }

	@Override
    public void run() {
	    try {
			Thread.sleep(2800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // It provides that the program runs until it terminated by Enter Key!
	    while(true){
			writeToConsole();
			writeToJsonFile(obj);
	    }

    }
    public void writeToJsonFile(JSONObject obj){
    	FileWriter file = null;
    	try {
        	file = new FileWriter("OutputJSON.json");
            file.write(obj.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                file.flush();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
