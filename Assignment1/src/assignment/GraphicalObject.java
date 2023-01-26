package assignment;

public abstract class GraphicalObject {
	public abstract void transform(double [][] matrix);
	public void rotateXAxis(double angle) {
		double [][] matrix = {{1,0,0},{0, Math.cos(angle), -Math.sin(angle)}, {0, Math.sin(angle), Math.cos(angle)}};
		transform(matrix);
	}
	public void rotateYAxis(double angle) {
		double [][] matrix = {{Math.cos(angle), 0, Math.sin(angle)}, {0,1,0}, {-Math.sin(angle), 0, Math.cos(angle)}};
		transform(matrix);
	}
	public void rotateZAxis(double angle) {
		double [][] matrix = {{Math.cos(angle), -Math.sin(angle), 0}, {Math.sin(angle), Math.cos(angle), 0}, {0,0,1}};
		transform(matrix);
	}
}
