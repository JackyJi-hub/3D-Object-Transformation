package assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PLYMeshReader implements MeshReader{
	@Override
	public HashSet<Polygon> read(String filename) throws WrongFileFormatException, Exception{
		// TODO Auto-generated method stub
		
		Pattern HEADER = Pattern.compile("\\s*ply(\\s)*" // header
				+ "format(\\s)ascii(\\s)1.0(\\s)*"
				+ "element(\\s)vertex(\\s)+(\\d)+(\\s)*"
				+ "property(\\s)float32(\\s)x(\\s)*"
				+ "property(\\s)float32(\\s)y(\\s)*"
				+ "property(\\s)float32(\\s)z(\\s)*"
				+ "element(\\s)face(\\s)+(\\d)+(\\s)*"
				+ "property(\\s)list(\\s)uint8\\sint32\\svertex_indices(\\s)*"
				+ "end_header(\\s)*");
		Pattern END_HEADER = Pattern.compile("^\s*end_header\s*$"); // end header
		Pattern PLY1 = Pattern.compile("^element\\svertex\\s+\\d+\\s*$"); // num vertices
		Pattern PLY2 = Pattern.compile("^element\\sface\\s+\\d+\\s*$"); // num faces
		Pattern PLY3 = Pattern.compile("^(-?\\d+\\.?\\d*(E-?\\d+)?\\s+){2}(-?\\d+\\.?\\d*(E-?\\d+)?)\\s*$"); // check vertifes
		Pattern PLY4 = Pattern.compile("^(\\d+\\s+){1,}\\d+\\s*$"); // check faces
		Matcher matcher;
		
		HashSet<Polygon> hs = new HashSet<Polygon> ();
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		
		try {
			Scanner input = new Scanner(new File (filename));
			String line;
			String [] split = null;
			int vertex_num = 0;
			int face_num = 0;
			String header = "";
			while (input.hasNextLine()) {
				line = input.nextLine();
				header = header+line;
				
				matcher = PLY1.matcher(line); // get num of vertices
				if (matcher.matches()) {
					split = line.split("\\s+");
					vertex_num = Integer.parseInt(split[2]);
				}
				matcher = PLY2.matcher(line); // get num of faces
				if (matcher.matches()) {
					split = line.split("\\s+");
					face_num = Integer.parseInt(split[2]);
				}
				matcher = END_HEADER.matcher(line);
				if (matcher.matches()) {
					break;
				}
			}
			matcher = HEADER.matcher(header);
			if (!matcher.matches()) {
//				System.out.println(header);
				throw new WrongFileFormatException("Format does not match PLY : " + "Header does not match");
			}
//			System.out.println(vertex_num);
			for (int i = 0; i < vertex_num; i++) {
				line = input.nextLine();
				matcher = PLY3.matcher(line);
				if (!matcher.matches()) {
					throw new WrongFileFormatException("Format does not match PLY : " + line + " does not match");
				}else {
					String [] cords = line.split("\\s+");
					Vertex v = new Vertex(Double.parseDouble(cords[0]), Double.parseDouble(cords[1]), Double.parseDouble(cords[2]));
					vertices.add(v);
				}
			}
			for (int i = 0; i < face_num; i++) {
				line = input.nextLine();
				matcher = PLY4.matcher(line);
				if (!matcher.matches()) {
					throw new WrongFileFormatException("Format does not match PLY : " + line + " does not match");
				}else {
					String [] vertices_index = line.split("\\s+");
					LinkedHashSet<Vertex> poly = new LinkedHashSet<Vertex>();
					for (int j = 0; j < Integer.parseInt(vertices_index[0]); j ++) {
						//Starts on 1 bc first number is the number of vertices
						Vertex v = vertices.get(Integer.parseInt(vertices_index[1 + j]));  // Add all the vertices that make up this face
						Vertex v_copy = new Vertex (v.x1, v.x2, v.x3);
						poly.add(v_copy);

					}
					hs.add(new Polygon(poly));
				}
			}
			if (input.hasNextLine() == true) { // check if reached end of file
				throw new WrongFileFormatException("Format does not match PLY : " + "Still more content after reading all faces");
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
