import java.io.IOException;


public class Station {
public String nom;
public int x;
public int y;


public Station(int a, int b,String n)throws IOException{
	nom=n;
	x=a;
	y=b;
	
	
}
public int getx (){
	
	
	return x;
	
}
public int gety (){
	
	
	return y;
	
}

}
