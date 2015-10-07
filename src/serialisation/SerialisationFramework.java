package serialisation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialisationFramework {
	public byte[] buildByteMessage(Object o){
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		try {
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(o);
			oos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return os.toByteArray();
	}
	
	public Object readFromByteArray(byte[] b){
		ByteArrayInputStream is = new ByteArrayInputStream(b);
		ObjectInputStream ois;
		
		try {
			ois = new ObjectInputStream(is);
			Object deserialisedObject = ois.readObject();
			ois.close();
			
			return deserialisedObject;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
