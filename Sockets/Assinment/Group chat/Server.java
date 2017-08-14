import java.net.*;
import java.io.*;
import java.util.*;
public class Server extends Thread
{
	static final int PORT=8081;
	static ServerSocket serverSocket;
	static Socket socket;
	static Vector v=new Vector();
	public Vector getVec()
	{
		return v;
	}
	public void run()
	{
		try
		{
		    serverSocket=new ServerSocket(PORT);
		    System.out.println("Started : "+serverSocket);
		    Send s=new Send();
    	    Thread t1=new Thread(s);
            t1.start();
		    while(true)
		    {
		      socket =serverSocket.accept();
		      v.add(socket);	
              Recieve r=new Recieve(socket);
    	      Thread t2=new Thread(r);
              t2.start();
		    }     
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String args[])
    {
    	Server s=new Server();
    	s.start();
    }
}
class Send extends Server implements Runnable
{
	Vector vec=new Vector();
	Socket socket;
	public void run()
	{
        	try
	    	{
	    	 System.out.println("send thread start");
			 Console con=System.console();	 	
	         String s=con.readLine();
	         vec=super.getVec();
	         for(int i=0;i<vec.size();i++)
	         {
	         	socket=(Socket)vec.get(i);
	         	PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
	         	out.println(""+s);
	         }
	         	
		    }
	 	   catch(Exception c)
	    	{
	 		  c.printStackTrace();
	 	    }	
	}
}
class Recieve implements Runnable
{
	Socket socket;
	public Recieve(Socket s)
	{
	     socket=s;
	}
	public void run()
	{
		try
		{
			System.out.println("Connection Accepted: "+socket);
			BufferedReader in;
		    in =new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str=null;
  			while(true)
  			{
				str=in.readLine();
				if(str.equals("END"))
				break;
				System.out.println("Echoing: "+str);
		   }
		   socket.close();
		}
		catch(Exception s)
		{
			s.printStackTrace();
		}
	}
		
}