package repMgr;

public interface ClientInterface {
	void Open();
	void Close();
	String Read();
	void Write(String text);
}
