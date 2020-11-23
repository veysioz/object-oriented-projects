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
		Label    lblObj       = readJsonFileForLabel();
		Instance instanceObj  = readJsonFileForInstance();

		//Örnek kullnum aþaðýdadýr, eriþim içim get ve set Methodlarýný kullanýn.
		System.out.println("getDataSetID---"+lblObj.getDataSetID());
		System.out.println("getDataSetName---"+lblObj.getDataSetName());
		System.out.println("InstanceType---"+lblObj.getInstanceType());
		System.out.println("MaxNumberOfLabelsPerInstance"+lblObj.getMaxNumberOfLabelsPerInstance());
		System.out.println("getLabelID[0]---"+lblObj.getLabelID()[0]); // objeleri indexlerle çaðýrýn.
		System.out.println("LabelTEXT[0]---"+lblObj.getLabelTEXT()[0]);// objeleri indexlerle çaðýrýn.

		System.out.println("getDataSetID---"+instanceObj.getDataSetID());
		System.out.println("getDataSetName"+instanceObj.getDataSetName());
		System.out.println("getInstanceType"+instanceObj.getInstanceType());
		System.out.println("getMaxNumberOfLabelsPerInstance"+instanceObj.getMaxNumberOfLabelsPerInstance());
		System.out.println("getInstanceID[0]---"+instanceObj.getInstanceID()[0]);// objeleri indexlerle çaðýrýn.
		System.out.println("getInstanceTEXT[0]---"+instanceObj.getInstanceTEXT()[0]);// objeleri indexlerle çaðýrýn.




	}
	public static Instance readJsonFileForInstance(){

		JSONParser parser = new JSONParser();
		JSONObject jsonObject=null;
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\mikailtorun\\Desktop\\Ödevler\\OOPD\\javaDeneme\\LabelingTest\\sampleInput.json"));

			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			jsonObject = (JSONObject) obj;
//			System.out.println(jsonObject);

			Object dataSetID    = jsonObject.get("dataset id");
			Object dataSetName  = jsonObject.get("dataset name");
			Object instanceType = jsonObject.get("instance type");
			Object maximumNumberOfLabelsPerInstance = jsonObject.get("maximum number of labels per instance");


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
			Instance instObje  = new Instance(classinstancesIdList1,classinstancesTextList1,dataSetID,dataSetName,instanceType,maximumNumberOfLabelsPerInstance);
			Label    labelObje = new Label(classLabelIdList1,classLabelTextList1,dataSetID,dataSetName,instanceType,maximumNumberOfLabelsPerInstance);

//			System.out.println(instObje.getInstanceTEXT()[2]);
//			System.out.println(instObje.getInstanceID()[2]);

			return instObje;

//			System.exit(0);


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
		return null;

	}
	public static Label readJsonFileForLabel(){

		JSONParser parser = new JSONParser();
		JSONObject jsonObject=null;
		try {
			Object obj = parser.parse(new FileReader("C:\\Users\\mikailtorun\\Desktop\\Ödevler\\OOPD\\javaDeneme\\LabelingTest\\sampleInput.json"));

			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			jsonObject = (JSONObject) obj;
//			System.out.println(jsonObject);

			Integer dataSetID    = Integer.parseInt(jsonObject.get("dataset id").toString());

//			System.exit(0);
			Object dataSetName  = jsonObject.get("dataset name");
			Object instanceType = jsonObject.get("instance type");
			Object maximumNumberOfLabelsPerInstance = jsonObject.get("maximum number of labels per instance");

//			Instance i = new Instance();
//			i.setDataSetID(dataSetID );
//
//			System.out.println(i.getDataSetID());
//			System.exit(0);
//			System.out.println(datasetName);
//			System.out.println(instanceType);
//			System.out.println(maximumNumberOfLabelsPerInstance);



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
			Instance instObje  = new Instance(classinstancesIdList1,classinstancesTextList1,dataSetID,dataSetName,instanceType,maximumNumberOfLabelsPerInstance);
			Label    labelObje = new Label(classLabelIdList1,classLabelTextList1,dataSetID,dataSetName,instanceType,maximumNumberOfLabelsPerInstance);
//			System.out.println(labelObje.getLabelID()[0]);
//			System.out.println(labelObje.getLabelTEXT()[0]);
			return labelObje;
//			System.out.println(instObje.getInstanceTEXT()[2]);
//			System.out.println(instObje.getInstanceID()[2]);
//			System.exit(0);


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
		return null;

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
