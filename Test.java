//import java.io.File;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.simple.*;
import org.json.simple.parser.*;
//import org.json.JSONArray;

public class Test {

	public static void main(String[] args) {
		readJsonFile();
		System.exit(0);

//		JSONObject inputJson = (JSONObject) readJsonFile();
//		System.out.println(inputJson);
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\mikailtorun\\Desktop\\Ödevler\\OOPD\\javaDeneme\\LabelingTest\\sampleInput.json"));

			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;
			System.out.println(jsonObject);
			// A JSON array. JSONObject supports java.util.List interface.
			JSONArray indexInJSON = (JSONArray) jsonObject.get("class labels");

			Object[] classLabelIdList = new Object[indexInJSON.size()];
			Object[] classLabelTextList = new Object[indexInJSON.size()];
			Iterator<JSONObject> iteratorForLabelId = indexInJSON.iterator();
			Iterator<JSONObject> iteratorForLabelText = indexInJSON.iterator();


 for(int i =0; i < indexInJSON.size();i++){
//	 System.out.println(iterator1.next().get("label id"));
//	 System.out.println(indexInJSON.get(i));
	 classLabelIdList[i] =  iteratorForLabelId.next().get("label id");
 }
 for(int i =0; i < indexInJSON.size();i++){
	 classLabelTextList[i] =  iteratorForLabelText.next().get("label text");
//	 System.out.println(iteratorForLabelText.next().get("label text"));
 }
 for (Object string : classLabelIdList) {
	System.out.println("id ler =>" + string);
}
 for (Object string : classLabelTextList) {
	System.out.println("text ler =>" + string);
}

 System.exit(0);
			// An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
			// Iterators differ from enumerations in two ways:
			// 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
			// 2. Method names have been improved.

			Iterator<JSONObject> iterator = indexInJSON.iterator();
			while (iterator.hasNext()) {

				//				System.out.println(iterator.next().get("instance"));
//				System.out.print(iterator.next().get("label id"));
//				System.out.print(iterator.next().get("label text"));
//				System.out.println();
				System.out.println(iterator.next());
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void readJsonFile(){

		JSONParser parser = new JSONParser();
		JSONObject jsonObject=null;
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\mikailtorun\\Desktop\\Ödevler\\OOPD\\javaDeneme\\LabelingTest\\sampleInput.json"));

			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			jsonObject = (JSONObject) obj;
			System.out.println(jsonObject);

			Object dataSetID =  jsonObject.get("dataset id");
			Object datasetName =  jsonObject.get("dataset name");
			Object instanceType = jsonObject.get("instance type");
			Object maximumNumberOfLabelsPerInstance = jsonObject.get("maximum number of labels per instance");

			System.out.println(dataSetID);
			System.out.println(datasetName);
			System.out.println(instanceType);
			System.out.println(maximumNumberOfLabelsPerInstance);



			Object[] classLabelIdList1 = getClassLabelsID(jsonObject);
			Object[] classLabelTextList1 = getClassLabelsTEXT(jsonObject);

//			for (Object object : classLabelIdList1) {
//				System.out.println("ID=>"+object);
//			}
//			for (Object object : classLabelTextList1) {
//				System.out.println("text=>"+object);
//			}
//			System.exit(0);
			Object[] classinstancesIdList1 = getInstancesID(jsonObject);
			Object[] classinstancesTextList1 = getInstancesTEXT(jsonObject);

//			for (Object object : classinstancesIdList1) {
//				System.out.println("ID=>"+object);
//			}
//			for (Object object : classinstancesTextList1) {
//				System.out.println("text=>"+object);
//			}

			System.exit(0);


			// An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
			// Iterators differ from enumerations in two ways:
			// 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
			// 2. Method names have been improved.

//			Iterator<JSONObject> iterator = indexInJSON.iterator();
//			while (iterator.hasNext()) {

				//				System.out.println(iterator.next().get("instance"));
//				System.out.print(iterator.next().get("label id"));
//				System.out.print(iterator.next().get("label text"));
//				System.out.println();
//				System.out.println(iterator.next());
//				break;
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
    private static Object[] getInstancesTEXT(JSONObject jsonObject) {
		// A JSON array. JSONObject supports java.util.List interface.
		JSONArray indexInJSON = (JSONArray) jsonObject.get("instances");


		Object[] instanceTextList = new Object[indexInJSON.size()];

		Iterator<JSONObject> iteratorForinstanceText = indexInJSON.iterator();

		for(int i =0; i < indexInJSON.size();i++){
			instanceTextList[i] =  iteratorForinstanceText.next().get("instance");
		// System.out.println(iteratorForLabelText.next().get("label text"));
		}
		return instanceTextList;
	}
	private static Object[] getInstancesID(JSONObject jsonObject) {
		// A JSON array. JSONObject supports java.util.List interface.
		JSONArray indexInJSON = (JSONArray) jsonObject.get("instances");


		Object[] instanceIDList = new Object[indexInJSON.size()];

		Iterator<JSONObject> iteratorForinstanceID = indexInJSON.iterator();

		for(int i =0; i < indexInJSON.size();i++){
			instanceIDList[i] =  iteratorForinstanceID.next().get("id");
		// System.out.println(iteratorForLabelText.next().get("label text"));
		}
		return instanceIDList;
	}
	private static Object[] getClassLabelsTEXT(JSONObject jsonObject) {
		// A JSON array. JSONObject supports java.util.List interface.
		JSONArray indexInJSON = (JSONArray) jsonObject.get("class labels");


		Object[] classLabelTextList = new Object[indexInJSON.size()];

		Iterator<JSONObject> iteratorForLabelText = indexInJSON.iterator();

		for(int i =0; i < indexInJSON.size();i++){
		 classLabelTextList[i] =  iteratorForLabelText.next().get("label text");
		// System.out.println(iteratorForLabelText.next().get("label text"));
		}
		return classLabelTextList;
	}
	private static Object[] getClassLabelsID(JSONObject jsonObject) {
		// A JSON array. JSONObject supports java.util.List interface.
		JSONArray indexInJSON = (JSONArray) jsonObject.get("class labels");

		Object[] classLabelIdList = new Object[indexInJSON.size()];
		Iterator<JSONObject> iteratorForLabelId = indexInJSON.iterator();
		for(int i =0; i < indexInJSON.size();i++){
			classLabelIdList[i] =  iteratorForLabelId.next().get("label id");
		}

		return classLabelIdList;
	}


}
