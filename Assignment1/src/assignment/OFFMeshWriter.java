package assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class OFFMeshWriter implements MeshWriter{
	@Override
	public void write(String filename, HashSet<Polygon> polygons) throws IOException{
		// TODO Auto-generated method stub
		try {
			FileWriter output = new FileWriter(filename, false);
			LinkedHashSet<Vertex> vertices = new LinkedHashSet<Vertex>();;
			for (Polygon p : polygons) {
				for (Vertex v : p.vertices) {
					vertices.add(v);
				}
			}
			output.write("OFF\n"); // write OFF on the first line
			output.write(vertices.size() + " " + polygons.size() + " 0\n"); // write the number of vertices, faces, and edges (0)
			for (Vertex v : vertices) {
				output.write(v.x1 + " " + v.x2 + " " + v.x3 + "\n");
			}
			for (Polygon p : polygons) { // all polygons
				int index = 0; // starting index due to format of OFF
				ArrayList<Integer> v_indices = new ArrayList<Integer>(); // store indices of each face
				for (Vertex v : p.vertices) { // vertices within the polygon
					for (Vertex v_list : vertices) { // all vertices
						if (v.equals(v_list)) { // if vertices match add the line number to the list of vertex indices
							v_indices.add(index); 
						}
						index++; 
					}
					index = 0;
				}
				output.write(String.valueOf(p.vertices.size())); // write the number of vertices that make up this face
				for (int i : v_indices) { // write all the line numbers of each vertex that form the polygon
					output.write(" " + i);
				}
				
				output.write(" 220 220 220\n");
			}
			output.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in writing to the files");
		} 
	}
}
