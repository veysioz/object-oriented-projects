import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class Dataset {
	public static int datasetID;
	public static String datasetName;
	public static int maxNumLabsPerIns;
	public static ArrayList<Label> arListLab = new ArrayList<Label>();
	public static ArrayList<Instance> arListIns = new ArrayList<Instance>();

	public Dataset() {
		JSONObject jsonVal = read();
		datasetID = Integer.parseInt(jsonVal.get("dataset id").toString());
		datasetName = jsonVal.get("dataset name").toString();
		maxNumLabsPerIns = Integer.parseInt(jsonVal.get("maximum number of labels per instance").toString());
		getLabels(jsonVal, arListLab);
		getInstances(jsonVal, arListIns);
	}

	private JSONObject read() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			Object obj = parser.parse(new FileReader("Input-2.json"));
			jsonObject = (JSONObject) obj;
		} catch (Exception e) {
			System.out.println(e);
		}
		return jsonObject;
	}

	@SuppressWarnings("unused")
	private Instance readJsonFileForInstance() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			Object obj = parser.parse(new FileReader("Input-2.json"));
			jsonObject = (JSONObject) obj;
			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("unused")
	private Label readJsonFileForLabel() {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			Object obj = parser.parse(new FileReader("Input-2.json"));

			jsonObject = (JSONObject) obj;

			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	private void getLabels(JSONObject jsonObject, ArrayList<Label> arListLab) {
		JSONArray indexInJSON = (JSONArray) jsonObject.get("class labels");

		Iterator<JSONObject> iteratorForLabelID = indexInJSON.iterator();
		Iterator<JSONObject> iteratorForLabelText = indexInJSON.iterator();

		for (int i = 0; i < indexInJSON.size(); i++) {
			arListLab.add(new Label(Integer.parseInt(iteratorForLabelID.next().get("label id").toString()),
					iteratorForLabelText.next().get("label text").toString()));
		}
	}

	@SuppressWarnings("unchecked")
	private void getInstances(JSONObject jsonObject, ArrayList<Instance> arListIns) {
		JSONArray indexInJSON = (JSONArray) jsonObject.get("instances");

		Iterator<JSONObject> iteratorForinstanceText = indexInJSON.iterator();
		Iterator<JSONObject> iteratorForinstanceID = indexInJSON.iterator();

		for (int i = 0; i < indexInJSON.size(); i++) {
			arListIns.add(new Instance(Integer.parseInt(iteratorForinstanceID.next().get("id").toString()),
					iteratorForinstanceText.next().get("instance").toString()));
		}
	}
}
