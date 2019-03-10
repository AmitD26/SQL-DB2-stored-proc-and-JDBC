package hw2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Histogram_HW2 {
    public static void main(String[] args) {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Please include Classpath Where your DB2 Driver is located");
            e.printStackTrace();
            return;
        }
        System.out.println("DB2 driver is loaded successfully");
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset=null;
        
        int start = Integer.parseInt(args[0]);
        int end = Integer.parseInt(args[1]);
        int numberOfBins = Integer.parseInt(args[2]);
        
        int binWidth = (end - start) / numberOfBins;
        
        int binStart = 0;
        int binEnd = 0;
        int binNum = 0;
        
        int [][] histogram = new int[numberOfBins][4];
        
        for (int i = 0; i < numberOfBins; i++) {
        	binNum = i + 1;
        	binStart = start + i * binWidth;
        	binEnd = binStart + binWidth;
        	
        	histogram[i][0] = binNum;
        	histogram[i][2] = binStart;
        	histogram[i][3] = binEnd;
        }
        
        try {
            conn = DriverManager.getConnection("jdbc:db2://localhost:50000/sample", "db2inst1", "aaaa1234");
            if (conn != null)
            {
                System.out.println("DB2 Database Connected");
            }
            else
            {
                System.out.println("Db2 connection Failed ");
            }
            pstmt=conn.prepareStatement("select salary from employee");
            rset=pstmt.executeQuery();
            
            int salary, binIndex;
            if(rset!=null)
            {
                while(rset.next())
                {
                    salary = (int) Float.parseFloat(rset.getString("salary"));
                    binIndex = (salary - start) / binWidth;
                    histogram[binIndex][1] += 1;
                }
            }
        } catch (SQLException e) {
            System.out.println("DB2 Database connection Failed");
            e.printStackTrace();
        }
        System.out.println("\nBin num       Frequency       Bin Start       Bin End");
        for (int i = 0; i < histogram.length; i++) {
        	for (int j = 0; j < histogram[0].length; j++) {
        		System.out.print(histogram[i][j] + "\t\t");
        	}
        	System.out.print("\n");
        }
    }
}