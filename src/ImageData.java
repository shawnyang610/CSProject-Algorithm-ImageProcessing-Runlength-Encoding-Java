import java.io.IOException;
import java.util.Scanner;

public class ImageData {
    int[][] imgMatrix;
    int numRows;
    int numCols;
    int minNum;
    int maxNum;
    Scanner infile;

    ImageData(int in_numRows, int in_numCols, int in_minNum, int in_maxNum, Scanner in_infile){
        numRows = in_numRows;
        numCols = in_numCols;
        minNum = in_minNum;
        maxNum = in_maxNum;
        infile = in_infile;
        imgMatrix = new int [numRows][numCols];

        for (int i=0; i<numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                imgMatrix[i][j] = infile.nextInt();
            }
        }
    }
}
