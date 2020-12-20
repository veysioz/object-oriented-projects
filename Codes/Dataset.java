import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

public class Dataset {
	private ID datasetID;
	private Text datasetName;
	private MaxNumLabsPerIns maxNumLabsPerIns;
	private ArrayList<Label> arListLab = new ArrayList<Label>();
	private ArrayList<Instance> arListIns = new ArrayList<Instance>();
	
	public Dataset(String path) {
		JSONObject jsonVal = getJSONFile(path);
		datasetID = new ID(Integer.parseInt(jsonVal.get("dataset id").toString()));
		datasetName = new Text(jsonVal.get("dataset name").toString());
		maxNumLabsPerIns = new MaxNumLabsPerIns(Integer.parseInt(jsonVal.get("maximum number of labels per instance").toString()));
		getLabels(jsonVal, arListLab);
		getInstances(jsonVal, arListIns);
	}

	private JSONObject getJSONFile(String path) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;

		try {
			Object obj = parser.parse(new FileReader(path));
			jsonObject = (JSONObject) obj;
		} catch (Exception e) {
			System.out.println(e);
		}
		return jsonObject;
	}
	
	@SuppressWarnings("unchecked")
	private void getLabels(JSONObject jsonObject, ArrayList<Label> arListLab) {
		JSONArray indexInJSON = (JSONArray) jsonObject.get("class labels");

		Iterator<JSONObject> iteratorForLabelID = indexInJSON.iterator();
		Iterator<JSONObject> iteratorForLabelText = indexInJSON.iterator();

		for (int i = 0; i < indexInJSON.size(); i++) {
			arListLab.add(new Label(new ID(Integer.parseInt(iteratorForLabelID.next().get("label id").toString())),
					new Text(iteratorForLabelText.next().get("label text").toString())));
		}
	}

	@SuppressWarnings("unchecked")
	private void getInstances(JSONObject jsonObject, ArrayList<Instance> arListIns) {
		JSONArray indexInJSON = (JSONArray) jsonObject.get("instances");

		Iterator<JSONObject> iteratorForinstanceText = indexInJSON.iterator();
		Iterator<JSONObject> iteratorForinstanceID = indexInJSON.iterator();

		for (int i = 0; i < indexInJSON.size(); i++) {
			arListIns.add(new Instance(new ID(Integer.parseInt(iteratorForinstanceID.next().get("id").toString())),
					new Text(iteratorForinstanceText.next().get("instance").toString()), new ID(datasetID.getID())));
		}
	}
	
	public ID getDatasetID() {
		return datasetID;
	}
	
	public void setDatasetID(ID datasetID) {
		this.datasetID = datasetID;
	}
	
	public Text getDatasetName() {
		return datasetName;
	}
	
	public void setDatasetName(Text datasetName) {
		this.datasetName = datasetName;
	}
	
	public MaxNumLabsPerIns getMaxNumLabsPerIns() {
		return maxNumLabsPerIns;
	}
	
	public void setMaxNumLabsPerIns(MaxNumLabsPerIns maxNumLabsPerIns) {
		this.maxNumLabsPerIns = maxNumLabsPerIns;
	}
	
	public ArrayList<Label> getArListLab() {
		return arListLab;
	}
	
	public ArrayList<Instance> getArListIns() {
		return arListIns;
	}
}