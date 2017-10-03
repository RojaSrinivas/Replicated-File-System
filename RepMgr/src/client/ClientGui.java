package client;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import repMgr.ClientInterface;
import repMgr.IServerMgr;

public class ClientGui extends JFrame implements ClientInterface{
	private final int FRAME_WIDTH = 250;
    private final int FRAME_HEIGHT = 500;
    IServerMgr Server;
    public int CID;
    private JTextArea txt;
	public ClientGui(IServerMgr server,int cliendId){
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Replication Client");
        JButton b1 = new JButton("Read");
        JButton b2 = new JButton("Write");
        txt = new JTextArea();
        b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txt.setText(Read());
			}
		});
        b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Write(txt.getText());
			}
		});
        add(b1,BorderLayout.NORTH);
        add(txt,BorderLayout.CENTER);
        add(b2,BorderLayout.SOUTH);
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e){
        		Close();
        	}
		});
        Server=server;
        CID=cliendId;
	}
	public void Open() {
		Server.Open(CID);
	}
	public void Close() {
		Server.Close(CID);
	}
	public String Read() {
		Open();
		return Server.GetFile(CID);
	}
	public void Write(String text) {
		boolean b = Server.Write(txt.getText(), CID);
		if(!b) JOptionPane.showMessageDialog(getParent(), "File is locked!");
	}

}
