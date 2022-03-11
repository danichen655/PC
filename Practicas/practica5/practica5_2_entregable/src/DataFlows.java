import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataFlows {

	ObjectOutputStream out;
	ObjectInputStream in;
	
	public DataFlows(ObjectOutputStream out, ObjectInputStream in) {
		
		this.out = out;
		this.in = in;
	}
	
	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public ObjectInputStream getIn() {
		return in;
	}

	public void setIn(ObjectInputStream in) {
		this.in = in;
	}
}
