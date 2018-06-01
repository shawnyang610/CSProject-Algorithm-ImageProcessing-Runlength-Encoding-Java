import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class main {
    public static void main (String[] args){
        Scanner infile=null;
        Scanner usrInput=null;
        String usrStrInput="";
        PrintWriter outfile = null;
        ImageData image = null;
        int numRows=0;
        int numCols=0;
        int minNum=0;
        int maxNum=0;

        if ( args.length<2) {
            System.out.println("Please enter input and output files.");
            System.exit(1);
        }
        try{
            infile = new Scanner (new FileReader(args[0]));
            outfile = new PrintWriter(args[1]);
            numRows  = infile.nextInt();
            numCols = infile.nextInt();
            minNum = infile.nextInt();
            maxNum = infile.nextInt();
        } catch(IOException e){
            System.out.println(e);
            System.exit(1);
        }
        //instantiate data structure
        image = new ImageData(numRows, numCols, minNum, maxNum, infile);
        System.out.println("Please select one of the following four methods to proceed:");
        System.out.println("Method 1) Encode without zero and no wrap-around");
        System.out.println("Method 2) Encode without zero and use wrap-around");
        System.out.println("Method 3) Encode with zero and no wrap-around");
        System.out.println("Method 4) Encode with zero and use wrap-around");
        usrInput = new Scanner (System.in);
        if (usrInput.hasNext()){
            usrStrInput = usrInput.nextLine();
        }
        else {
            System.out.println("Please make a selection");
        }
        if (usrStrInput.equals("1")){
            System.out.println("Method 1 is selected");
            new Runlength(image,outfile,1);
        }
        else if (usrStrInput.equals("2")){
            System.out.println("Method 2 is selected");
            new Runlength(image, outfile, 2);
        }
        else if (usrStrInput.equals("3")){
            System.out.println("Method 3 is selected");
            new Runlength(image, outfile, 3);
        }
        else if (usrStrInput.equals("4")){
            System.out.println("Method 4 is selected");
            new Runlength(image, outfile, 4);
        }
        else {
            System.out.println("Wrong format. please enter a single digit from 1 to 4 only");
            System.exit(1);
        }
        infile.close();
        outfile.close();

    }
}
