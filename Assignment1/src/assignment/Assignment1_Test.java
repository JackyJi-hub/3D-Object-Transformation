package assignment;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.util.HashSet;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;

class Assignment1_Test {
//		
//		// OBJ transformer
////		mesh.setReader(new OBJMeshReader());
////		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car.obj");
////		mesh.rotateXAxis(Math.PI/3);
////		mesh.setWriter(new OBJMeshWriter());
////		mesh.writeToFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car_obj.obj");
//		
//		//OFF transformer
////		mesh.setReader(new OFFMeshReader());
////		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car.off");
////		mesh.rotateXAxis(Math.PI/3);
////		mesh.setWriter(new OFFMeshWriter());
////		mesh.writeToFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car_off.off");
//		
//		//PLY transformer
//		
//		
//		
////		mesh.setWriter(new PLYMeshWriter());
////		mesh.writeToFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car_lmao.ply");
//		
////		Mesh mesh = new Mesh();
////		mesh.setReader(new OBJMeshReader());
////		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/block.obj");
////		mesh.rotateYAxis(Math.PI);
////		mesh.setWriter(new OBJMeshWriter());
////		mesh.writeToFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/block_lmao.obj");
//	
//	
//	
//	
//	
//	
	@Test
	void testReadWriteOBJ() throws WrongFileFormatException, Exception {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car.obj");
		mesh.setWriter(new OBJMeshWriter());
		mesh.writeToFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car_not_rotated.obj");
	}
	
	@Test
	void testReadWriteOFF() throws WrongFileFormatException, Exception {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car.off");
		mesh.setWriter(new OFFMeshWriter());
		mesh.writeToFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car_not_rotated.off");
	}
	
	@Test
	void testReadWritePLY() throws WrongFileFormatException, Exception {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car.ply");
		mesh.setWriter(new PLYMeshWriter());
		mesh.writeToFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car_not_rotated.ply");
	}
	
	@Test
	void testOBJTransform() throws WrongFileFormatException, Exception {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car.obj");
		mesh.rotateXAxis(Math.PI/3);
		mesh.rotateYAxis(Math.PI/6);
		mesh.rotateZAxis(Math.PI/9);
		mesh.setWriter(new OBJMeshWriter());
		mesh.writeToFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/test_car_rotated.obj");
	}
	
	@Test
	void testPLYTransform() throws WrongFileFormatException, Exception {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car.ply");
		mesh.rotateXAxis(Math.PI/3);
		mesh.rotateYAxis(Math.PI/6);
		mesh.rotateZAxis(Math.PI/9);
		mesh.setWriter(new PLYMeshWriter());
		mesh.writeToFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/test_car_rotated.ply");
	}
	
	@Test
	void testOFFTransform() throws WrongFileFormatException, Exception {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car.off");
		mesh.rotateXAxis(Math.PI/3);
		mesh.rotateYAxis(Math.PI/6);
		mesh.rotateZAxis(Math.PI/9);
		mesh.setWriter(new OFFMeshWriter());
		mesh.writeToFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/test_car_rotated.off");
	}
	//OBJ Tests****************************************************************************************************************************************
	@Test
	void testOBJInvalidFile() throws WrongFileFormatException, Exception {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/does_not_exist.obj");
	}
	
	@Test
	void testOBJWrongHeader() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/obj_with_header.obj");
		}
		catch (WrongFileFormatException e) {
			assertEquals(e.message, "Format does not match OBJ : OBJ  does not match");
		}
	}
	
	@Test
	void testOBJWrongFaceFormat() throws WrongFileFormatException, Exception {
//		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/obj_invalid_face.obj");
//		}
//		catch ( Exception e) {
//			assertEquals(e, "Other exceptions : java.lang.IndexOutOfBoundsException: Index 7 out of bounds for length 4");
//		}
		
	}
	
	@Test
	void testOBJWrongVertexFormat() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/obj_invalid_vertex.obj");
		}
		catch ( WrongFileFormatException e) {
			assertEquals(e.message, "Format does not match OBJ : v  4.9  1.5  0.3  @  does not match");
		}
	}
	
	@Test
	void testOBJWrongOrdering() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OBJMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/obj_invalid_face_order.obj");
		}
		catch ( WrongFileFormatException e) {
			assertEquals(e.message, "Format does not match OBJ : v  3.1  2.6  7.4  does not match");
		}
	}
//
//	//PLY Tests****************************************************************************************************************************************
	@Test
	void testPLYInvalidFile() throws WrongFileFormatException, Exception {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/does_not_exist.ply");
	}
	@Test
	void testPLYInvalidVertexNum() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/ply_wrong_vertex_number.ply");
		}
		catch ( WrongFileFormatException e) {
		assertEquals(e.message, "Format does not match PLY : 3 1  2  3  does not match");
	}
	}
	@Test
	void testPLYWrongHeaderFormat() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/ply_wrong_header_format.ply");
		}
		catch ( WrongFileFormatException e) {
			assertEquals(e.message, "Format does not match PLY : Header does not match");
		}
	}
	
	@Test
	void testPLYWrongFaceNum() throws WrongFileFormatException, Exception {
//		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/ply_wrong_face_number.ply");
//		}
//		catch ( Exception e) {
//			assertEquals(e, "Other exceptions : java.util.NoSuchElementException: No line found");
//		}
	}
	
	@Test
	void testPLYWrongFaceNum2() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/ply_wrong_face_number2.ply");
		}
		catch ( WrongFileFormatException e) {
			assertEquals(e.message, "Format does not match PLY : Still more content after reading all faces");
		}
	}
	
	@Test
	void testPLYWrongVertex() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/ply_invalid_vertex.ply");
		}
		catch ( WrongFileFormatException e) {
		assertEquals(e.message, "Format does not match PLY : 5.1  1.2  0..  does not match");
	}
	}
	@Test
	void testPLYWrongFace() throws WrongFileFormatException, Exception {
//		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/ply_invalid_face.ply");
//		}
//	catch ( Exception e) {
//	assertEquals(e, "Other exceptions : java.lang.IndexOutOfBoundsException: Index 9 out of bounds for length 4");
//}
	}
	@Test
	void testPLYWrongFace2() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new PLYMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/ply_invalid_face2.ply");
		}
	catch ( WrongFileFormatException e) {
	assertEquals(e.message, "Format does not match PLY : 3 1  2  9.2 5  does not match");
}
	}
	
	

//	//OFF Tests****************************************************************************************************************************************
	@Test
	void testOFFInvalidFile() throws WrongFileFormatException, Exception {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/does_not_exist.off");
	}
	
	@Test
	void testOFFWrongHeaderFormat() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/off_invalid_header.off");
		}
	catch ( WrongFileFormatException e) {
	assertEquals(e.message, "Format does not match OFF : OFF abc  does not match");
}
	}
	
	@Test
	void testOFFWrongVertex() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/off_invalid_vertex.off");
		}
	catch ( WrongFileFormatException e) {
	assertEquals(e.message, "Format does not match OFF : 4.9  1.5  does not match");
}
	}
	
	@Test
	void testOFFWrongFace() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/off_invalid_face.off");
		}
	catch ( WrongFileFormatException e) {
	assertEquals(e.message, "Format does not match OFF : 3  0  1  2  220  220   does not match");
}
	}
	
	@Test
	void testOFFWrongFace2() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/off_invalid_face2.off");
		}
	catch ( WrongFileFormatException e) {
		
	assertEquals(e.message, "Format does not match OFF : 3  0  1  a  220  220  220 does not match");
}
	}
	
	@Test
	void testOFFWrongColor() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/off_invalid_color.off");
		}
		catch ( WrongFileFormatException e) {
		assertEquals(e.message, "Format does not match OFF : 3  0  1  2  300  300  300    has invalid colors");
	}
	}
	@Test
	void testInvalidFaceIndex() throws WrongFileFormatException, Exception {
//		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/off_invalid_face_index.off");
//		}
//	catch ( Exception e) {
//	assertEquals(e, "Other exceptions : java.lang.IndexOutOfBoundsException: Index 9 out of bounds for length 4");
//	
//}
	}
	
	@Test
	void testWrongFaceNumber() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/off_wrong_face_number.off");
		}
	catch ( WrongFileFormatException e) {
	assertEquals(e.message, "Format does not match OFF : 4  0  1  2  200  200  200    does not match");
}
	}
	
	@Test
	void testWrongHeaderCount() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/off_wrong_header_count.off");
		}
	catch ( WrongFileFormatException e) {
	assertEquals(e.message, "Format does not match OFF : 4  2  0  9 does not match");
}
	}
	
	@Test
	void testWrongVertexNumber() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/off_wrong_vertex_number.off");
		}
	catch ( WrongFileFormatException e) {
	assertEquals(e.message, "Format does not match OFF : 4  0  1  2  200  200  200    does not match");
}
	}
	
	@Test
	void testWrongFaceCount() throws WrongFileFormatException, Exception {
		try {
		Mesh mesh = new Mesh();
		mesh.setReader(new OFFMeshReader());
		mesh.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/off_wrong_face_count.off");
		}
	catch ( WrongFileFormatException e) {
	assertEquals(e.message, "Format does not match OFF : Still more content after reading all faces");
}
	}
	
	
	//miscellaneous tests  ****************************************************************************************************************************************
	@Test
	void testCheckEqualPolygons() {
		Vertex v1 = new Vertex(1,3,3);
		Vertex v2 = new Vertex(5,2,3);
		Vertex v3 = new Vertex(1,6,3);
		
		LinkedHashSet<Vertex> l = new LinkedHashSet<Vertex>();
		l.add(v1);
		l.add(v2);
		l.add(v3);
		Polygon p = new Polygon(l);
		Polygon p2 = new Polygon(l);
		assertTrue(p.equals(p2));
	}
	
	@Test
	void testEqualCheckNotInstanceOfPolygons() {
		Vertex v1 = new Vertex(1,3,3);
		Vertex v2 = new Vertex(5,2,3);
		Vertex v3 = new Vertex(1,6,3);
		
		LinkedHashSet<Vertex> l = new LinkedHashSet<Vertex>();
		l.add(v1);
		l.add(v2);
		l.add(v3);
		Polygon p = new Polygon(l);
		
		assertTrue(!p.equals(v1));
	}
	
	@Test 
	void testToStringVertex() {
		Vertex v1 = new Vertex(1,2,3);
		assertEquals(v1.toString(), v1.x1 + " " + v1.x2 + " " + v1.x3);
	}
	
	@Test
	void testMeshNotEqual () throws WrongFileFormatException, Exception {
		Mesh mesh1 = new Mesh(); 
        mesh1.setReader(new PLYMeshReader()); 
        mesh1.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car.ply");
        mesh1.rotateYAxis(Math.PI/3);
		
        Mesh mesh2 = new Mesh(); 
        mesh2.setReader(new PLYMeshReader()); 
        mesh2.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car.ply");
        
        assertTrue(!mesh1.equals(mesh2));
	}
	
	@Test
	void testEqualCheckNotInstanceOfMesh() throws WrongFileFormatException, Exception {
		Vertex v1 = new Vertex(1,3,3);
		
		Mesh mesh1 = new Mesh(); 
        mesh1.setReader(new PLYMeshReader()); 
        mesh1.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car.ply");
		
		assertTrue(!mesh1.equals(v1));
	}
	
	@Test
	void testEqualCheckNotInstanceOfVertex() throws WrongFileFormatException, Exception {
		Vertex v1 = new Vertex(1,3,3);
		Vertex v2 = new Vertex(5,2,3);
		Vertex v3 = new Vertex(1,6,3);
		
		LinkedHashSet<Vertex> l = new LinkedHashSet<Vertex>();
		l.add(v1);
		l.add(v2);
		l.add(v3);
		Polygon p = new Polygon(l);
		
		assertTrue(!v1.equals(p));
	}
	
	@Test
	void testMeshEqual () throws WrongFileFormatException, Exception {

		Mesh mesh1 = new Mesh(); 
        mesh1.setReader(new OFFMeshReader()); 
        mesh1.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car.off");
        mesh1.rotateYAxis(Math.PI/3);
		
        Mesh mesh2 = new Mesh(); 
        mesh2.setReader(new OBJMeshReader()); 
        mesh2.readFromFile("/Users/jackyji/eclipse-workspace/Assignment1/src/assignment/TestObjects/car_rotated.obj");
   
        assertTrue(mesh1.equals(mesh2));


	}
	
	@Test
	void testMeshHashCode () {
		Vertex v1 = new Vertex(1,3,3);
		Vertex v2 = new Vertex(5,2,3);
		Vertex v3 = new Vertex(1,6,3);
		
		LinkedHashSet<Vertex> l = new LinkedHashSet<Vertex>();
		l.add(v1);
		l.add(v2);
		l.add(v3);
		Polygon p1 = new Polygon(l);
		
		Vertex x1 = new Vertex(4,5,3);
		Vertex x2 = new Vertex(1,6,43);
		Vertex x3 = new Vertex(23,2,2);
		
		LinkedHashSet<Vertex> l2 = new LinkedHashSet<Vertex>();
		l.add(x1);
		l.add(x2);
		l.add(x3);
		Polygon p2 = new Polygon(l2);
		
		HashSet<Polygon> hs = new HashSet<Polygon>();
		
		hs.add(p1);
		hs.add(p2);
		
		Mesh m1 = new Mesh();
		
		m1.polygons = hs;
		
		assertEquals(m1.hashCode(), 17);
		
	}
}
