package repMgr;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import client.ClientGui;

public class ReManager extends JFrame implements ActionListener,IServerMgr {

	private final int FRAME_WIDTH = 250;
    private final int FRAME_HEIGHT = 300;
    private Vector<ServerMgr> _servers;
    private Map<Integer,IServer> _clients;
	public ReManager(){
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Replication Server");
        FilesMgr.getInstance().AddFile("info.txt");
        _servers= new Vector<ServerMgr>();
        _servers.add(new ServerMgr("Server1"));
        _servers.add(new ServerMgr("Server2"));
        _servers.add(new ServerMgr("Server3"));
        _clients=new HashMap<Integer, IServer>();
        JPanel p1 = new JPanel();
        JLabel lbl1 = new JLabel("Servers:");
        JList<String> servers_list = new JList<String>();
        servers_list.setListData(new String[]{"Server1","Server2","Server3"});
        p1.add(lbl1);
        p1.add(servers_list);
        JButton bc = new JButton("Open Client");
        add(p1,BorderLayout.NORTH);
        bc.addActionListener(this);
        add(bc,BorderLayout.SOUTH);
	}
	
	private void CreateClient(){
		final IServerMgr serv = this;
		final ClientGui cg= new ClientGui(serv,_clients.size());
		_clients.put(cg.CID,_servers.elementAt( (int) (Math.random()*_servers.size())));
		SwingUtilities.invokeLater(new Runnable() {
            
            public void run() {                
                JFrame ex = cg;
                ex.setVisible(true);                
            }
        });
	}
	public void actionPerformed(ActionEvent arg0) {
		CreateClient();
	}
	
	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
            
            public void run() {                
                JFrame ex = new ReManager();
                ex.setVisible(true);                
            }
        });
	}
	public String GetFile(int cid) {
		return _clients.get(cid).GetFile();
	}
	public void Open(int cid) {
		FilesMgr.getInstance().LockFile(0, cid);
		//_clients.get(cid).Open();	
	}
	public void Close(int cid) {
		FilesMgr.getInstance().UnlockFile(0, cid);
		//_clients.get(cid).Close();
	}
	public boolean Write(String text,int cid) {
		if(FilesMgr.getInstance().IsLocked(0)) 
			return false;
		for(ServerMgr s: _servers){
			s.Write(text);
		}
		//_clients.get(cid).Write(text);
		return true;
	}
}
