package repMgr;

public interface IServer {
	String GetFile();
	void Open();
	void Close();
	void Write(String text);
}
