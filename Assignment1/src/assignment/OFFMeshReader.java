package assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OFFMeshReader implements MeshReader{
	@Override
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException, Exception{
		// TODO Auto-generated method stub
		Pattern HEADER = Pattern.compile("^\\s*OFF\\s*$");
		Pattern OFF1 = Pattern.compile("^(-?\\d+\\.?\\d*(E-?\\d+)?\\s+){2}(-?\\d+\\.?\\d*(E-?\\d+)?)\\s*$"); // for vertices
		Pattern OFF2 = Pattern.compile("^(\\d+\\s+){1,}\\d+\\s*$"); // for faces
		Matcher matcher;
		
		HashSet<Polygon> hs = new HashSet<Polygon> ();
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		
		try {
			Scanner input = new Scanner(new File (filename));
			String line = input.nextLine(); // Read "OFF"
			matcher = HEADER.matcher(line);
			if (!matcher.matches()) {
				throw new WrongFileFormatException("Format does not match OFF : " + line + " does not match");
			}
			
			line = input.nextLine(); // Read the second that contains the number of vertices, the number of faces, and the number of edges
			String [] split = line.split("\\s+");
			if (split.length != 3) { // check if there are 3 items in total
				throw new WrongFileFormatException("Format does not match OFF : " + line + " does not match");
			}
			for (int i = 0; i < Integer.parseInt(split[0]); i++) { // read all the vertices
				line = input.nextLine();
				matcher = OFF1.matcher(line);
				if (!matcher.matches()) {
					throw new WrongFileFormatException("Format does not match OFF : " + line + " does not match");
				}else {
					String [] cords = line.split("\\s+");
					Vertex v = new Vertex(Double.parseDouble(cords[0]), Double.parseDouble(cords[1]), Double.parseDouble(cords[2]));
					vertices.add(v);
				}
			}
			for (int i = 0; i < Integer.parseInt(split[1]); i++) { // read all of the faces
				line = input.nextLine();
				matcher = OFF2.matcher(line);
				if (!matcher.matches()) {
					throw new WrongFileFormatException("Format does not match OFF : " + line + " does not match");
				}else {
					String [] vertices_index = line.split("\\s+");
					LinkedHashSet<Vertex> poly = new LinkedHashSet<Vertex>();
					// Check if the number of vertices in the face actually match up with what is given
					// -4 bc 1st number is num of vertices + 3 colors
					if (Integer.parseInt(vertices_index[0]) != vertices_index.length - 4) {  
//						System.out.println("c");
//						System.out.println(line);
//						System.out.println(vertices_index.length );
//						System.out.println(Integer.parseInt(vertices_index[0]) );
//						
//						for (String s : vertices_index) {
//							System.out.println(s);
//						}
						throw new WrongFileFormatException("Format does not match OFF : " + line + " does not match");
					}
					
					// checks whether the color passed in is valid
					for (int k = Integer.parseInt(vertices_index[0]) + 1; k < vertices_index.length; k++) {
						if (Integer.parseInt(vertices_index[k]) > 255 || Integer.parseInt(vertices_index[k]) < 0) {
							throw new WrongFileFormatException("Format does not match OFF : " + line +" has invalid colors");
						}
					}
					// vertices_index[0] contains the number of vertices that make up this face
					// +1 bc first value is the number of vertices
					for (int j = 1; j < Integer.parseInt(vertices_index[0]) + 1; j ++) { 
						//Starts on 1 bc first number (index 0) is the number of vertices
						Vertex v = vertices.get(Integer.parseInt(vertices_index[j]));
						Vertex v_copy = new Vertex (v.x1, v.x2, v.x3);
						poly.add(v_copy); // Add all the vertices that make up this face
					}
					hs.add(new Polygon(poly));
				}
				
			}
			if (input.hasNextLine() == true) { // check if reached end of file
				throw new WrongFileFormatException("Format does not match OFF : " + "Still more content after reading all faces");
			}
			input.close();
		}
		catch (IOException e) {
			System.out.println("Other exceptions : " + e);
		}
		catch (RuntimeException e) {
			System.out.println("Other exceptions : " + e);
		}
		return hs;
	}
}
