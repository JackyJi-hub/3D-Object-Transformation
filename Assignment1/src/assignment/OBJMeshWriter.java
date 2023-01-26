package assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class OBJMeshWriter implements MeshWriter{

	@Override
	public void write(String filename, HashSet<Polygon> polygons) throws IOException{
		// TODO Auto-generated method stub
		try {
			FileWriter output = new FileWriter(filename, false);
			LinkedHashSet<Vertex> vertices = new LinkedHashSet<Vertex>();
			for (Polygon p : polygons) {
				for (Vertex v : p.vertices) {
					vertices.add(v);
				}
			}
			for (Vertex v : vertices) {
				output.write("v " + v.x1 + " " + v.x2 + " " + v.x3 + "\n");
			}
			for (Polygon p : polygons) { // all polygons
				int index = 1;
				ArrayList<Integer> v_indices = new ArrayList<Integer>();
				for (Vertex v : p.vertices) { // vertices within the polygon
					for (Vertex v_list : vertices) { // all vertices
						if (v.equals(v_list)) { // if vertices match add the line number to the list of vertex indices
							
							v_indices.add(index); 
							break;
						}
						index++; 
					}
					index = 1;
				}
				output.write("f"); // write all the line numbers of each vertex that form the polygon
				for (int i : v_indices) {
					output.write(" " + i);
				}
				output.write("\n");
			}
			output.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in writing to the files");
		} 
	}
	
}
