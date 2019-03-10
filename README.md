# SQL-DB2-histogram-stored-procedure-and-JDBC

In this homework, you will implement a stored procedure to compute the histogram for employee's salaries from the employee table in DB2 sample database (employee table). If you want to create the table by yourself, you need to create a table employee with this schema and load the sample data into the database first.  You can find examples on stored procedures and JDBC.

A histogram is the probability distribution of a continuous variable, represented by frequencies of the variables falling into a bin. In this homework, your histogram program takes an initial value (inclusive), an end value (exclusive), and the number of bins, e.g., histogram(start, end, number), and it returns a set of bins with bin number, frequency, bin's start value, and bin's end value, i.e., (binnum, frequency, binstart, binend). For example:
binnum  frequency   binstart  binend
1         2          35000    40000
2         3          40000    45000

You will write a stored procedure in SQL/PL to implement it. Please consider performance in your implementation. For example, multiple queries on the same table will involve multiple scans, and could lead to bad performance. 

When the stored procedure is called, the histogram is returned and stored in a predefined table. Alternatively, you can return the result as an array. Refer to "Exchange data using arrays in SQL PL".


Homework 2 Submission (10 points)

To test your code, please use lower bound 30,000, upper bound 170,000, and interval 20,000 to generate a sample result.  

e.g., run the query "call gen_salary_histogram(30000, 170000, 7)"

Please zip your SQL code sp.sql,  sample result ( a file or screenshot), and a readme file on how to run your program.

Please go to blackboard, and submit it under homework 2.

Extra Credit (2 points)

Implement the same function with another approach, e.g., JDBC, Java Stored Procedure, or User Defined Function in PL/SQL. Again, you should consider only querying the table once to generate the result to maximize performance. 

Submit all the files zipped (including your codes, a screenshot of result, and a readme file on how to run the program) as extra.zip.
