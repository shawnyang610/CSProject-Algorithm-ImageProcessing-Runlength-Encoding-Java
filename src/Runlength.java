//Proj.7.1 Run-Length Encoder
import java.io.PrintWriter;

public class Runlength {
    private ImageData image;
    private int numRows;
    private int numCols;
    private int minValue;
    private int maxValue;
    private int count;
    private int currentValue;
    private int nextValue;
    private int selectorFlag;
    private PrintWriter outfile;
    private int row;
    private int col;

    Runlength(ImageData in_image, PrintWriter in_outfile, int in_selectorFlag){
        image = in_image;
        numRows = image.numRows;
        numCols = image.numCols;
        minValue = image.minNum;
        maxValue = image.maxNum;
        outfile = in_outfile;
        count=0;
        currentValue =0;
        nextValue =0;
        selectorFlag = in_selectorFlag;
        outfile.println(numRows+" "+numCols+" "+minValue+" "+maxValue);
        outfile.println(selectorFlag);
        run();
    }
    void run (){
        if (selectorFlag ==1)
            methodOne();
        else if (selectorFlag ==2)
            methodTwo();
        else if (selectorFlag ==3)
            methodThree();
        else if (selectorFlag ==4)
            methodFour();
        else{
            System.out.println("Error: Unknown selector");
            System.exit(1);
        }

    }
    //encode without zero's and no wrap-around
    void methodOne(){
        //step0
        row=0;
        while (row<numRows) { //step8 repeat step1-7
            //step1
            col = 0;
            count = 0;
            currentValue = image.imgMatrix[row][col];
            //step2
            if (currentValue!=0)
                outfile.print("   " + row + " " + col + " " + currentValue + " ");
            count++;
            while (col < numCols - 1) {
                //step3
                col++;
                //step4
                nextValue = image.imgMatrix[row][col];
                //step5
                if (nextValue == currentValue)
                    count++;
                else {
                    if (currentValue!=0)
                        outfile.println(count);
                    currentValue = nextValue;
                    count = 1;
                    if (currentValue!=0)
                        outfile.print("   " + row + " " + col + " " + currentValue + " ");
                }
            }//step6 repeat step3-5
            //6.5
            if (currentValue!=0)
                outfile.println(count);
            //step7
            row++;
        }//step8 repeat step1-7

    }
    //encode without zero's and use wrap-around
    void methodTwo(){
        int row=0;
        int col=0;

        while (row < numRows) {
            boolean lock = false;
            boolean lock2 = false;
            //1.find the start of a new run

            while (row < numRows){
                while (col<numCols) {
                    if (image.imgMatrix[row][col] != 0) {
                        count = 1;
                        col +=1; //next search starts from 1 step forward
                        currentValue = image.imgMatrix[row][col];
                        outfile.print("   " + row + " " + (col-1) + " " + currentValue + " ");
                        lock = true;
                    }
                    if (lock == true)
                        break;
                    col++;
                }
                if (lock == true)
                    break;
                col=0;
                row++;
            }
            //2.
            while (row < numRows && (row!=numRows-1 || col!=numCols-1)) {
                while (col < numCols) {
                    if (image.imgMatrix[row][col] == currentValue) {
                        count++;
                        if (row==numRows-1 && col==numCols-1){
                            outfile.println(count);
                        }
                    } else {
                        outfile.println(count);
                        lock2 = true;
                    }
                    if (lock2 == true)
                        break;
                    col++;
                }
                if (lock2 == true)
                    break;
                col = 0;
                row++;
            }
            if (row==numRows-1 && col>=numCols-1)
                break;
        }

    }

    //encode with zero's, no wrap-around
    void methodThree(){
        //step0
        row=0;
        while (row<=numRows-1) { //step8 repeat step1-7
            //step1
            col = 0;
            count = 0;
            currentValue = image.imgMatrix[row][col];
            //step2
            outfile.print("   " + row + " " + col + " " + currentValue + " ");
            count++;
            while (col < numCols - 1) {
                //step3
                col++;
                //step4
                nextValue = image.imgMatrix[row][col];
                //step5
                if (nextValue == currentValue)
                    count++;
                else {
                    outfile.println(count);
                    currentValue = nextValue;
                    count = 1;
                    outfile.print("   " + row + " " + col + " " + currentValue + " ");
                }
            }//step6 repeat step3-5
            //6.5
            outfile.println(count);
            //step7
            row++;
        }//step8 repeat step1-7
    }

    //encode with zero's, and use wrap-around
    void methodFour(){
        currentValue = image.imgMatrix[0][0];
        outfile.print("   0 0 "+ currentValue + " ");
        for (int r=0; r<numRows;r++){
            for (int c=0; c<numCols;c++){
                nextValue = image.imgMatrix[r][c];
                //when next pixel is in the same run
                if (currentValue == nextValue) {
                    count++;
                    currentValue=nextValue;
                }
                //when next pixel is in a new run
                else{
                    outfile.println (count);
                    currentValue=nextValue;
                    outfile.print("   " + r + " " + c + " " + currentValue + " ");
                    count=1;
                }
                //output the count anyway when it reaches the end of the image
                if (r==numRows-1 && c==numCols-1){
                    outfile.println (count);
                }
            }
        }
    }



}
