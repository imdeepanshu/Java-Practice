import java.net.*;
import java.io.*;
public class Server
{
	static final int PORT=8081;
	static ServerSocket serverSocket;
	static Socket socket;
	public static void main(String args[])
    {
    	try
    	{
    		serverSocket=new ServerSocket(PORT);
		   System.out.println("Started : "+serverSocket);
    	   socket =serverSocket.accept();
    	   Send s=new Send(socket);
           Thread t1=new Thread(s);
           t1.start();
           Recieve r=new Recieve(socket);
    	   Thread t2=new Thread(r);
           t2.start();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
           
    }
}
class Send implements Runnable
{
	Socket socket;
	public Send(Socket s)
	{
		this.socket=s;
	}
	public void run()
	{
        	try
	    	{
			 Console con=System.console();	 	
	        PrintWriter out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
            String s=con.readLine();
	        out.println(""+s);
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
    	this.socket=s;
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