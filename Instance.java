
public class Instance {

	public Instance(Object[] instanceID,Object[] instanceTEXT,Object dataSetID,Object dataSetName,Object instanceType,Object maxNumberOfLabelsPerInstance){
		this.dataSetID   =dataSetID;
		this.dataSetName =dataSetName;
		this.instanceID  =instanceID.clone();//copyied object array
		this.instanceTEXT=instanceTEXT.clone();//copyied object array
		this.instanceType=instanceType;
		this.maxNumberOfLabelsPerInstance=maxNumberOfLabelsPerInstance;
	}

	private Object[] instanceID;
	private Object[] instanceTEXT;
	private Object dataSetID;
	private Object dataSetName;
	private Object instanceType;
	private Object maxNumberOfLabelsPerInstance;

	public Object[] getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(Object[] instanceID) {
		this.instanceID = instanceID;
	}


	public Object[] getInstanceTEXT() {
		return instanceTEXT;
	}


	public void setInstanceTEXT(Object[] instanceTEXT) {
		this.instanceTEXT = instanceTEXT;
	}

	public Object getDataSetID() {
		return dataSetID;
	}

	public void setDataSetID(Object dataSetID) {
		this.dataSetID = dataSetID;
	}

	public Object getDataSetName() {
		return dataSetName;
	}

	public void setDataSetName(String dataSetName) {
		this.dataSetName = dataSetName;
	}

	public Object getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}

	public Object getMaxNumberOfLabelsPerInstance() {
		return maxNumberOfLabelsPerInstance;
	}

	public void setMaxNumberOfLabelsPerInstance(int maxNumberOfLabelsPerInstance) {
		this.maxNumberOfLabelsPerInstance = maxNumberOfLabelsPerInstance;
	}

	public static void main(String[] args) {



	}

}
