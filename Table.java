import java.util.*;

public class Table {
    //Fields
    private int paddingTop = 0;
    private int paddingBottom = 0;
    private int paddingRight = 0;
    private int paddingLeft = 0;
    private int borderWidthTop = 0;
    private int borderWidthBottom = 0;
    private int borderWidthRight = 0;
    private int borderWidthLeft = 0;
    private char borderSign = ' ';
    private int colomns = 0;
    private int[] rowLengths;
    private String[] headers;
    private ArrayList<String[]> rows = new ArrayList<>();

    //Constuctors
    public Table(String[] headers, int[] rowLengths) {
        this.colomns = colomns;
        this.rowLengths = rowLengths;
        this.headers = headers;
        colomns = rowLengths.length;
    }

    //Padding setters
    public void setPadding(int paddingAll) {
        this.paddingTop = paddingAll;
        this.paddingBottom = paddingAll;
        this.paddingRight = paddingAll;
        this.paddingLeft = paddingAll;
    }

    public void setPadding(int paddingTopAndBottom, int paddingLeftAndRight) {
        this.paddingTop = paddingTopAndBottom;
        this.paddingBottom = paddingTopAndBottom;
        this.paddingRight = paddingLeftAndRight;
        this.paddingLeft = paddingLeftAndRight;
    }

    public void setPadding(int paddingTop, int paddingRight, int paddingBottom, int paddingLeft) {
        this.paddingTop = paddingTop;
        this.paddingRight = paddingRight;
        this.paddingBottom = paddingBottom;
        this.paddingLeft = paddingLeft;
    }

    //Border setter
    public void setBorder(int borderAll, char sign) {
        this.borderWidthTop = borderAll;
        this.borderWidthBottom = borderAll;
        this.borderWidthRight = borderAll;
        this.borderWidthLeft = borderAll;
        this.borderSign = sign;
    }

    private int fullWidth() {
        int total = borderWidthRight; for(int i = 0; i < rowLengths.length; i++) { total += rowLengths[i] + paddingLeft + paddingRight + borderWidthLeft; } return total;
    }

    public void row(String[] arr){
        rows.add(arr);
    }


    public void print() {
        printBorderTop();
        printHeaders();

        for(String[] row : rows) {
            printRow(row);
        }
    }

    private void printHeaders() {
        printPadding(headers, paddingTop);
        printData(headers);
        printPadding(headers, paddingBottom);
        printBorderBottom();
    }

    private void printBorderTop() {
        for(int i = 0; i < borderWidthTop; i++) {
            for(int j = 0; j < fullWidth(); j++) {
                System.out.print(borderSign);
            }
            System.out.println();
        }
    }

    private void printRow(String[] arr) {
        printPadding(arr, paddingTop);
        printData(arr);
        printPadding(arr, paddingBottom);
        printBorderBottom();
    }

    private void printBorderBottom() {
        for(int i = 0; i < borderWidthBottom; i++) {
            for(int j = 0; j < fullWidth(); j++) {
                System.out.print(borderSign);
            }
            System.out.println();
        }
    }

    private void printData(String[] arr) {
        String[] row = arr;
        int count = 0;

        for(String s : row) {
            printBorderLeft();
            printPaddingLeft();

            int restWidth = rowLengths[count] - s.length();
            System.out.print(s);
            for(int j = 0; j < restWidth; j++) {
                System.out.print(" ");
            }

            printPaddingRight();

            count++;
        }

        printBorderRight();

        System.out.println();
    }

    private void printPadding(String[] row, int padding) {
        for(int i = 0; i < padding; i++) {
            int count = 0;

            for(String s : row) {
                printBorderLeft();
                printPaddingLeft();

                for(int j = 0; j < rowLengths[count]; j++) {
                    System.out.print(" ");
                }

                printPaddingRight();

                count++;
            }

            printBorderRight();

            System.out.println();
        }
    }

    private void printBorderLeft() {
        for(int i = 0; i < borderWidthLeft; i++) {
            System.out.print(borderSign);
        }
    }

    private void printBorderRight() {
        for(int j = 0; j < borderWidthRight; j++) {
            System.out.print(borderSign);
        }
    }

    private void printPaddingLeft() {
        for(int i = 0; i < paddingLeft; i++) {
            System.out.print(" ");
        }
    }

    private void printPaddingRight() {
        for(int i = 0; i < paddingRight; i++) {
            System.out.print(" ");
        }
    }
}
