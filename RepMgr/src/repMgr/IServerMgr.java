package repMgr;

public interface IServerMgr {
	String GetFile(int cid);
	void Open(int cid);
	void Close(int cid);
	boolean Write(String text,int cid);
}
