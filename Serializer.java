import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class Serializer {
	
private Serializer(){}

public static void saveToFile(String fileName,Serializable object) throws IOException
{
	FileOutputStream file = new FileOutputStream(fileName);
	ObjectOutputStream oos = new  ObjectOutputStream(file);
    oos.writeObject(object);
    oos.flush();
    oos.close();
   
}

public static <T> T loadFromFile(String fileName) throws ClassNotFoundException, IOException
{
	FileInputStream file = new FileInputStream(fileName);
	ObjectInputStream ois = new  ObjectInputStream(file);
	return (T)ois.readObject();
}

}
