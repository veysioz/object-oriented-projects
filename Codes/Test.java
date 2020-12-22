
public class Test{

	public static void main(String[] args) {
		new Test().start();
	}
    private void start() {
        new Starter().start();
        new Printer().start();
    }
    public static boolean ccpPercent(double ccp) {

		int rand = (int)(Math.random() * 100) + 1;
		ccp = (int)(ccp * 100);

		if(rand <= ccp && rand >= 0)
			return true;
		else
			return false;
	}
}
  //İBRETLİK OLSUN DİYE TUTUYORUM
//	@SuppressWarnings("resource")
//	public static void writeToJSONFile(StringBuffer sBuf) throws IOException{
//
//
//		File file = new File("TEST.json");
//		FileWriter fileWrite;
//
//		file.createNewFile();
//		fileWrite = new FileWriter(file);
//		fileWrite.write(sBuf.toString());
//		fileWrite.flush();
//
//    }
//İBRETLİK OLSUN DİYE TUTUYORUM
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

	}*/
