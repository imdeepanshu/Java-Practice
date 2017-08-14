import java.net.*;
import java.io.*;
public class Client
{	 
	public static void main(String args[])
	{
		try
		{
		    InetAddress addr= InetAddress.getByName("localhost");
		    Socket socket=new Socket(addr,8081);
		    CRecieve r1=new CRecieve(socket);
		    Thread t1=new Thread(r1);
		    t1.start();
		    CSend s1=new CSend(socket);
		    Thread t2=new Thread(s1);
		    t2.start();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		    
	}	
}
class CRecieve implements Runnable
{
	Socket socket;
    public CRecieve(Socket s)
    {
    	this.socket=s;
    }
	public void run()
	{
		try
	    {
			    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			    String str;
			    while((str=in.readLine())!=null)
			    {
				  System.out.println("Echo: "+str);    	
			    }
		}
		catch(Exception cr)
		{
			cr.printStackTrace();
		}
			
	}
}
class CSend implements Runnable
{
	Socket socket;
	public CSend(Socket s)
	{
		this.socket=s;
	}
	public void run()
	{
		try
		{
		    PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			Console con = System.console();
			System.out.println("Start typing:");
			String st=null;
	     	boolean flag=true;
			while(flag)
			{
	
				st=con.readLine();
			
				if(!st.equals("END"))
				{
					out.println(st);
				}
				else
				{
					flag=false;
					out.println("END");
				}
			}	
		}
		catch(Exception cs)
		{
			cs.printStackTrace();
		}
	}
}