import java.util.Scanner;
import java.lang.Math;
class Rectangle
{
//Main function
public static void main(String args[])
{
Rectangle sangam = new Rectangle();
System.out.printf("\nPerimeter : %.4f",sangam.getPerimeter());
System.out.printf("\nArea : %.4f",sangam.getArea());
System.out.printf("Length : %.4f",sangam.getLength());
System.out.printf("\nWidth : %.4f",sangam.getWidth());
System.out.print("\nIs given Rectangle a squaree? "+sangam.isSquare());
}

//take cordinats variable
private double x[] = new double[5];
private double y[] = new double[5];

//default constructor
public Rectangle()
{
set();
}

//input and check
public void set()
{
Scanner sc = new Scanner(System.in);
for(int i=1;i<5;i++)
{
	
//Taking user's input
System.out.println("Enter "+i+" co-ordinate :");
x[i] = sc.nextDouble();

//check
if(x[i] < 0 || x[i] > 50){
System.out.println("Wrong input! x co-ordinate should not be negative and >50. Please enter again");
i--;
continue;
}
y[i] = sc.nextDouble();
if(y[i] < 0 || y[i] > 50){
System.out.println("Wrong input! y co-ordinate should not be negative and >50. Please enter again");
i--;
continue;
}
}

//calculating slope of all lines
double slope_1,slope_2,slope_3,slope_4;
slope_1 = (y[2]-y[1])/(x[2]-x[1]);
slope_2 = (y[3]-y[2])/(x[3]-x[2]);
slope_3 = (y[3]-y[4])/(x[3]-x[4]);
slope_4 = (y[4]-y[1])/(x[4]-x[1]);

//Checking rectangle can be formed or not
if ( slope_1 != slope_3 || slope_2 != slope_4 || !((slope_1==0 && (x[3]-x[2])==0) ||(slope_2==0 && (x[2]-x[1])==0) || slope_1 * slope_2 == -1))
{
System.out.println("Given co-ordinates do not make a rectangle. Pls again entervalid co-ordinates.");
set();
}
}

//check perimeter
public double getPerimeter()
{
return 2*(getLength()+getWidth());
}

//check area
public double getArea()
{
return getLength()*getWidth();
}

//get length(larger dimension)
public double getLength()
{
double d1 = Math.sqrt((y[2]-y[1])*(y[2]-y[1])+(x[2]-x[1])*(x[2]-x[1]));
double d2 = Math.sqrt((y[3]-y[2])*(y[3]-y[2])+(x[3]-x[2])*(x[3]-x[2]));
if(d1>d2)
return d1;
return d2;
}

//get width
public double getWidth()
{
double d1 = Math.sqrt((y[2]-y[1])*(y[2]-y[1])+(x[2]-x[1])*(x[2]-x[1]));
double d2 = Math.sqrt((y[3]-y[2])*(y[3]-y[2])+(x[3]-x[2])*(x[3]-x[2]));
if(d1>d2)
return d2;
return d1;
}

//check is a square
public boolean isSquare()
{
if(getLength() == getWidth())
return true;
return false;
}
}

