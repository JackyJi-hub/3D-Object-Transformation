package assignment;
import java.util.HashSet;

public interface MeshReader{
	public abstract HashSet<Polygon> read(String filename) throws WrongFileFormatException, Exception;
}
