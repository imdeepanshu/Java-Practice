import java.net.*;
import java.io.*;
public class TCPClient
{
	public static void main(String[] args)throws IOException
	{
		System.out.println("*************CLIENT*******************");
		InetAddress addr = InetAddress.getByName("localhost");
		Socket socket=new Socket(addr,8081);
		try
		{
			System.out.println("Socket= "+socket);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	
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
				String str=in.readLine();
	
				System.out.println("Echo: "+str);
			}

		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Closing Socket...");
			socket.close();
		}
	}
}