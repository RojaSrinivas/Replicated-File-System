package repMgr;

import java.util.Vector;

public class FilesMgr {
	class FileRep{
		public String Name;
		public Vector<Integer> Lockers;
		
		public FileRep(String name) {
			Name= name;
			Lockers = new Vector<Integer>();
		}
	}
	private static final FilesMgr INSTANCE = new FilesMgr();
	public static final String PATH = "c:\\RFS\\";
	public static FilesMgr getInstance(){
		return INSTANCE;
	}
	public Vector<FileRep> Files;
	
	public FilesMgr(){
		Files=new Vector<FilesMgr.FileRep>();
	}
	public void AddFile(String name){
		Files.add(new FileRep(name));
	}
	public String GetFile(int index,String serverName){
		return PATH + serverName + "\\"+Files.elementAt(index).Name;
	}
	public void LockFile(int index,int cid){
		if(Files.elementAt(index).Lockers.contains(cid)) return;
		Files.elementAt(index).Lockers.add(cid);
	}
	public void UnlockFile(int index,int cid){
		if(!Files.elementAt(index).Lockers.contains(cid)) return;
		int ind = Files.elementAt(index).Lockers.indexOf(cid);
		Files.elementAt(index).Lockers.removeElementAt(ind);
	}
	public boolean IsLocked(int index){
		return Files.elementAt(index).Lockers.size()>1;
	}
}
