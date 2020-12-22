
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
