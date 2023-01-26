package assignment;

import java.util.HashSet;


public class Mesh extends GraphicalObject{
	HashSet<Polygon> polygons;
	MeshReader reader;
	MeshWriter writer;
	public void setReader(MeshReader mr) {
		reader = mr;
	}
	public void setWriter(MeshWriter mw) {
		writer = mw;
	}
	public void readFromFile(String filename) throws WrongFileFormatException, Exception {
		polygons = reader.read(filename);
	}
	public void writeToFile(String filename) throws WrongFileFormatException, Exception {
		writer.write(filename, polygons);
	}
	
	@Override
	public void transform(double[][] matrix) {
		// TODO Auto-generated method stub
		for (Polygon p : polygons) {
			p.transform(matrix);
		}
	}
	
	@Override
	public int hashCode() {
		int code = 0;
		for (Polygon p : polygons) { // add the x1 cords of each vertex in each polygon that make up this mesh
			for (Vertex v : p.vertices) {
				code += v.x1; 
			}
		}
		code /= polygons.size(); // divide by the total number of polygons that make up this mesh
		return code;
	}
	
	@Override
	public boolean equals(Object mesh) {
		if (!(mesh instanceof Mesh)){
            return false;
        }
		Mesh m = (Mesh)mesh;
		if (m.polygons.containsAll(polygons)) {
			return true;
		}
		return false;
	}
}
