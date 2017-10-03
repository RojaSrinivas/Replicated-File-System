package repMgr;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

public class ServerMgr implements IServer {
	public String Name;
	public ServerMgr(String name){
		Name=name;
	}

	public String GetFile() {
		try {
			FileReader fr = new FileReader(FilesMgr.getInstance().GetFile(0, Name));
			StringBuilder builder = new StringBuilder();
			int charsRead = -1;
			char[] chars = new char[100];
			do{
			    try {
					charsRead = fr.read(chars,0,chars.length);
				} catch (IOException e) {
					e.printStackTrace();
				}
			    //if we have valid chars, append them to end of string.
			    if(charsRead>0)
			        builder.append(chars,0,charsRead);
			}while(charsRead>0);
			fr.close();
			return builder.toString();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	public void Open() {
		//FilesMgr.getInstance().LockFile(0, Name);
	}

	public void Close() {
		//FilesMgr.getInstance().UnlockFile(0, Name);
	}

	public void Write(String text) {
		try {
			FileWriter fw= new FileWriter(FilesMgr.getInstance().GetFile(0, Name));
			fw.write(text);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
