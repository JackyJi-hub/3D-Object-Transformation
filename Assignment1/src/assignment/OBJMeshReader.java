package assignment;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class OBJMeshReader implements MeshReader{

	@Override
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException, Exception{
		// TODO Auto-generated method stub
		Pattern OBJ1 = Pattern.compile("^v\\s+(-?\\d+\\.?\\d*(E-?\\d+)?\\s+){2}(-?\\d+\\.?\\d*(E-?\\d+)?)\\s*$");
		Pattern OBJ2 = Pattern.compile("^f(\\s+\\d+){1,}\\s*$");
		int set = 1;
		Matcher matcher;
		
		HashSet<Polygon> hs = new HashSet<Polygon> ();
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		try {
			
			Scanner input = new Scanner(new File (filename));
			String line;
			while (input.hasNextLine()) {
				line = input.nextLine();
				if (set == 1) { // Matches the first set (the vertices)
					matcher = OBJ1.matcher(line);
//					System.out.print(line+"\n");
					if (matcher.matches() == false) { //if not match move to second set
//						System.out.print(line+"bruh \n");
						set = 2;
					}else { // store the vertex
						String [] temp = line.split("\\s+"); // String version of all vertex cords
						//Start at 1 bc first character is a "v"
						Vertex v = new Vertex(Double.parseDouble(temp[1]), Double.parseDouble(temp[2]), Double.parseDouble(temp[3]));
						vertices.add(v); // add vertex to vertices arrayList
					}
				}
				if (set == 2) {
					matcher = OBJ2.matcher(line);
					if (!matcher.matches()) { // if does not match format for faces throw exception
						throw new WrongFileFormatException("Format does not match OBJ : " + line + " does not match");
					}else {
//						System.out.println(line +  "\n");
						LinkedHashSet<Vertex> poly = new LinkedHashSet<Vertex>(); // vertices that will create the polygon
						String [] vertices_index = line.split("\\s+"); //Get the vertices for this face
						for (int i = 1; i < vertices_index.length; i ++) { // starts at 1 bc first character is a "f"
//							System.out.println(i+ " index\n");
							Vertex v = vertices.get(Integer.parseInt(vertices_index[i]) - 1); // -1 bc OBJ starts first vertex count at 1
							Vertex v_copy = new Vertex (v.x1, v.x2, v.x3); 
							poly.add(v_copy); //add the vertex object
						}
						hs.add(new Polygon(poly)); // add the new polygon to the hashset
					}
				}
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
