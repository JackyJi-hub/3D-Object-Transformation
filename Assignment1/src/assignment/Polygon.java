package assignment;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Polygon extends GraphicalObject{
	LinkedHashSet<Vertex> vertices;
	
	Polygon (LinkedHashSet<Vertex> l){
		vertices = l;
	}

	@Override
	public void transform(double[][] matrix) {
		for (Vertex v : vertices) {
			v.transform(matrix);
		}
	}
	
	@Override 
	public int hashCode() {
		int total_mag = 0;
		for (Vertex v : vertices) { // total magnitude of all vertices that make up this polygon
			total_mag += (int)Math.sqrt(v.x1*v.x1 + v.x2*v.x2 + v.x3*v.x3);
		}
		return vertices.size() * total_mag; // multiply by the total number of vertices
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Polygon)){
            return false;
        }
        Polygon p = (Polygon)obj;
        Iterator<Vertex> it1 = p.vertices.iterator();
        Iterator<Vertex> it2 = vertices.iterator();
        while (it1.hasNext()) {
        	if (!it1.next().equals(it2.next())){
        		return false;
        	}
        }
        return true;
    }
//	public boolean equals(Object new_poly) {
//		Polygon new_p = (Polygon)new_poly;
//		for (Vertex v : vertices) {
//			if (new_p.vertices.contains(v) == false) {
//				return false;
//			}
//		}
//		return true;
//	}
}
