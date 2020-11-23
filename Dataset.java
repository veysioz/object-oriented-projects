import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class Dataset {
	private ArrayList<Label> arListLab = new ArrayList<Label>();
	private ArrayList<Instance> arListIns = new ArrayList<Instance>();

	public Dataset() {
		JSONObject jsonVal = read();
		getLabels(jsonVal, arListLab);
		getInstances(jsonVal, arListIns);
	}

	public static JSONObject read() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			Object obj = parser.parse(new FileReader("Input-1.json"));
			jsonObject = (JSONObject) obj;
		} catch (Exception e) {
			System.out.println(e);
		}
		return jsonObject;
	}

	public static Instance readJsonFileForInstance() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			Object obj = parser.parse(new FileReader("Input-1.json"));
			jsonObject = (JSONObject) obj;
			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Label readJsonFileForLabel() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			Object obj = parser.parse(new FileReader("Input-1.json"));

			jsonObject = (JSONObject) obj;

			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private static void getLabels(JSONObject jsonObject, ArrayList<Label> arListLab) {
		JSONArray indexInJSON = (JSONArray) jsonObject.get("class labels");

		Iterator<JSONObject> iteratorForLabelID = indexInJSON.iterator();
		Iterator<JSONObject> iteratorForLabelText = indexInJSON.iterator();

		for (int i = 0; i < indexInJSON.size(); i++) {
			arListLab.add(new Label(Integer.parseInt(iteratorForLabelID.next().get("label id").toString()),
					iteratorForLabelText.next().get("label text").toString()));
		}
	}

	private static void getInstances(JSONObject jsonObject, ArrayList<Instance> arListIns) {
		JSONArray indexInJSON = (JSONArray) jsonObject.get("instances");

		Iterator<JSONObject> iteratorForinstanceText = indexInJSON.iterator();
		Iterator<JSONObject> iteratorForinstanceID = indexInJSON.iterator();

		for (int i = 0; i < indexInJSON.size(); i++) {
			arListIns.add(new Instance(Integer.parseInt(iteratorForinstanceID.next().get("id").toString()),
					iteratorForinstanceText.next().get("instance").toString()));
		}
	}

	public ArrayList<Label> getArListLab() {
		return arListLab;
	}

	public void setArListLab(ArrayList<Label> arListLab) {
		this.arListLab = arListLab;
	}
	
	public ArrayList<Instance> getArListIns() {
		return arListIns;
	}

	public void setArListIns(ArrayList<Instance> arListIns) {
		this.arListIns = arListIns;
	}
}
