import java.util.Scanner;

public class Starter extends Thread{
    @Override
    public void run() {
       Scanner reader = new Scanner(System.in);
        while(true){
//            System.out.println("1 = ON \n 0 = OFF\n**************************************");
            System.out.println("\033[0;32m"+"Press Enter to terminate\n**************************************"+"\033[0m");
            n= reader.nextLine();
            if(n.equals("")){
				System.exit(0);
            }
        }
    }
//    static public String n="";
     public String n="";
}