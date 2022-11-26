import java.io.*;

public class LongestCommonSubsequence {

    public static String dashedLine(int k)
    {
        StringBuilder sb = new StringBuilder(150);
        for(int n = 0; n < k; ++n)
            sb.append('-');
        return sb.toString();
    }

    public static void lcs(String X, String Y, int m, int n)
    {
        int[][] L = new int[m + 1][n + 1];
        String m2 = X;
        int nip = 0;

        // #Finding the count and storing the comparsion results
        for (int i = 0; i <= m; i++) {
            // #iterating through first string
            for (int j = 0; j <= n; j++) {
                // #iterating through second string and comparing every letter with 'i'th letter of the first string
                if (i == 0 || j == 0){ // #if either i or j is 0, then comparison result is o and comparsion notation is "  "
                    L[i][j] = 0;
                    System.out.print("   "+L[i][j]);
                }
                else if (X.charAt(i - 1) == Y.charAt(j - 1)){ // # if the letters are same, then the comparison result is diagonal+1 and notation is "\"
                    L[i][j] = L[i - 1][j - 1] + 1;
                    System.out.print(" \\ "+ L[i][j]);
                }
                else{ // # if letters are not same, then comparison result is maximum of the value between the value to the left and to the top
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                    if(L[i][j] == L[i-1][j]){ // # if the value on top is the max then comparison result will be the same value as the top and notation is "^"
                        System.out.print(" ^ "+ L[i][j]);
                    }
                    else{ // # if the value on left is the max then comparison result will be the same value as the left and notation is "<"
                        System.out.print(" < "+L[i][j]);
                    }
                }
            }
            if(nip < m){
                if(nip < 9){ // # if the number is 2 digits then spaces should be adjusted
                    System.out.print("\n"+ String.valueOf(nip + 1) +"  "+ m2.charAt(nip)+ "|");
                }
                else{
                    System.out.print("\n"+ String.valueOf(nip + 1) +" "+ m2.charAt(nip)+ "|");
                }
                nip += 1;
            }
        }

        // #Constructing LCS
        int index = L[m][n];
        int temp = index;

        char[] lcs = new char[index + 1];
        lcs[index] = '\u0000'; // Set the terminating character

        int i = m;
        int j = n;
        while (i > 0 && j > 0) { // # iterating through the stored comparison values from bottom up and storing each matches value in variable 'subsequence'
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                lcs[index - 1] = X.charAt(i - 1);
                i--;
                j--;
                index--;
            }
            else if (L[i - 1][j] > L[i][j - 1])
                i--;
            else
                j--;
        }

        System.out.println("\n");
        //  #printing the length and value of LCS
        System.out.println("Length of the Longest Common Subsequence is: "+ String.valueOf(temp));
        System.out.print("The Longest Common Subsequence of "+ "'" + X + "'"+" and " +"'"+ Y + "'"+" is ");
        for (int k = 0; k <= temp -1; k++)
            System.out.print(lcs[k]);
    }

    public static void main(String[] args) throws IOException {
        String S1 = "";
        String S2 = "";

        FileInputStream fstream = new FileInputStream("C:\\Users\\balur\\OneDrive\\Desktop\\LCS1.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String strLine;
        while ((strLine = br.readLine()) != null)   {
            String[] arrOfStr = strLine.split(",");
            S1 = arrOfStr[0];
            S2 = arrOfStr[1];

            int m = S1.length(); // storing length of first string in variable m
            int n = S2.length(); // storing length of second string in variable n
            int k = 8 + (4*n) + 2; // #here variable k represents the number of hyphens to print in the first line
            System.out.print(dashedLine(k)); // #printing k number of hyphens
            System.out.print("\n    |    ");
            //     #printing 2nd line of the output where we print the second string letter by letter
            for(int i = 1; i <= n; i++){
                System.out.print("   "+ i);
            }
            System.out.print("\n    |   Y");
            for(int i = 1; i <= n; i++){
                System.out.print("   "+S2.charAt(i-1));
            }
            System.out.print("\n");
            System.out.print(dashedLine(k)); // #printing a line of hyphens
            System.out.print("\n");
            System.out.print("   X|");
            lcs(S1,S2,m,n);
            System.out.println("\n\n\n");
        }
        fstream.close();

    }
}
