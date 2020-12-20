import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Config {
	private ArrayList<Dataset> datasets = new ArrayList<Dataset>();
	private ArrayList<User> users = new ArrayList<User>();
	private ConsCheckProb ccp;
	
	@SuppressWarnings("unchecked")
	public Config() {
		JSONObject config = getJSONFile("config.json");
		JSONArray dataset = (JSONArray)config.get("dataset");
		JSONArray user = (JSONArray)config.get("users");

		Iterator<JSONObject> iter1 = dataset.iterator(), iter2, iter3;
		
		for(int i = 0; i < dataset.size(); i++)
			datasets.add(new Dataset(iter1.next().get("path").toString()));
		
		iter1 = user.iterator();
		iter2 = user.iterator();
		iter3 = user.iterator();
		
		for(int i = 0; i < user.size(); i++)
			users.add(new User(new ID(Integer.parseInt(iter1.next().get("userID").toString())), 
												new Text(iter2.next().get("username").toString()),
												new Text(iter3.next().get("userType").toString())));
		
		ccp = new ConsCheckProb(Double.parseDouble(config.get("consistencyCheckProbability").toString()));
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
	
	public ArrayList<Dataset> getDatasets() {
		return datasets;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public ConsCheckProb getCpp() {
		return ccp;
	}
	
	public void setCpp(ConsCheckProb ccp) {
		this.ccp = ccp;
	}
}
