package assignment;

public class WrongFileFormatException extends Exception{
	String message;
	WrongFileFormatException (String m){
        message = m;
    }
}
