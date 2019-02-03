Q: How to Build the Software?
A: It is a maven based software thus just run the following command:  
``mvn clean install``

Q: How to use the software?
A: run the following command:  
``java Main input.txt output.csv``

Some assumptions:
1. Client_Information should be a combination of the CLIENT TYPE, CLIENT NUMBER, ACCOUNT NUMBER, SUBACCOUNT NUMBER fields from Input file
thus in the output.csv file Client_Information is a join of those four strings.
For instance if CLIENT TYPE=ct, CLIENT NUMBER = 001 ACCOUNT NUMBER=a1 SUBACCOUNT NUMBER=suba1  
then the Client_Information is ct001a1suba1

2. Product_Information should be a combination of the EXCHANGE CODE, PRODUCT GROUP CODE, SYMBOL, EXPIRATION DATE
thus in the output.csv file Product_Information is a join of those four strings.
For instance if EXCHANGE CODE=e1 PRODUCT GROUP CODE=code1, SYMBOL=s1, EXPIRATION DATE=20190203  
then Product_Information is e1code1s120190203

3. Total_Transaction_Amount should be a Net Total of the (QUANTITY LONG - QUANTITY SHORT) values for each client per product  
my understood is if we have 3 products, 2 clients then in the outpur.csv it should be
p1_c1_(QUANTITY LONG - QUANTITY SHORT)
p1_c3_(QUANTITY LONG - QUANTITY SHORT)  
p2_c2_(QUANTITY LONG - QUANTITY SHORT)