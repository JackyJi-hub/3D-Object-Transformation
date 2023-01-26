package assignment;

public class Vertex extends GraphicalObject{
	double x1,x2,x3;
	Vertex(double a1, double a2, double a3){
		x1 = a1;
		x2 = a2;
		x3 = a3;
	}
	
	@Override
	public void transform(double [][] matrix) {
        
        double new_x = matrix[0][0]*x1 + matrix[0][1]*x2 + matrix[0][2]*x3;
        double new_y = matrix[1][0]*x1 + matrix[1][1]*x2 + matrix[1][2]*x3;
        double new_z = matrix[2][0]*x1 + matrix[2][1]*x2 + matrix[2][2]*x3;

        x1=new_x;
        x2=new_y;
        x3=new_z;

        //System.out.println(x+", "+y+", "+z +"\t vs \t"+xX+", "+yY+", "+zZ);
    }
	
	@Override
	public int hashCode() {
		return (int)Math.sqrt(x1*x1 + x2*x2 + x3*x3);	
	}
	
	@Override
	public boolean equals(Object new_vect) {
		if (!(new_vect instanceof Vertex)){
            return false;
        }
		Vertex new_v = (Vertex)new_vect;
		if (new_v.x1 == x1 && new_v.x2 == x2 && new_v.x3 == x3) {
			return true;
		}
		return false;
	}
	
	@Override 
	public String toString () {
		return x1 + " "  + x2 + " " + x3;
		
	}
}
