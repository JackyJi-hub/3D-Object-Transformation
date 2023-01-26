package assignment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class PLYMeshWriter implements MeshWriter{
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
			output.write("ply\n"); // write header for ply format
			output.write("format ascii 1.0\n");
			output.write("element vertex " + vertices.size() + "\n");
			output.write("property float32 x\n");
			output.write("property float32 y\n");
			output.write("property float32 z\n");
			output.write("element face " + polygons.size() + "\n");
			output.write("property list uint8 int32 vertex_indices\n");
			output.write("end_header\n");
			
			for (Vertex v : vertices) { // write vertices
				output.write(v.x1 + " " + v.x2 + " " + v.x3 + "\n");
			}
			for (Polygon p : polygons) { // all polygons
				int index = 0; // starting index due to format of PLY
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
	
				output.write("\n");
			}
			output.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in writing to the files");
		} 
	}
}
