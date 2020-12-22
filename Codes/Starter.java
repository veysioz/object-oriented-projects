import java.util.Scanner;

public class Starter extends Thread{
    @Override
    public void run() {
       Scanner reader = new Scanner(System.in);
        while(true){
//            System.out.println("1 = ON \n 0 = OFF\n**************************************");
            System.out.println("INPUT 0 = OFF(TERMINATE THE PROGRAM)\n**************************************");
            n= reader.nextLine();
            if(n.equals("0")){
				System.exit(0);
            }
        }
    }
//    static public String n="";
     public String n="";
}