import java.net.*;
import java.io.*;
public class TCPServer
{
	public static final int PORT=8081;
	static ServerSocket serverSocket;
	static Socket socket;

	public static void main(String[] args)throws IOException
	{
		System.out.println("*************SERVER*******************");
		serverSocket=new ServerSocket(PORT);
		BufferedReader in;
		PrintWriter out;
		System.out.println("Started : "+serverSocket);
		
		try
		{
			Console con=System.console();
		 	socket =serverSocket.accept();
			System.out.println("Connection Accepted: "+socket);
			in =new BufferedReader(new InputStreamReader(socket.getInputStream()));

			out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
  	
  			String str=null;
  			while(true)
  			{

				str=in.readLine();
				if(str.equals("END"))
					break;
				System.out.println("Echoing: "+str);
				
				String s=con.readLine();
				out.println(""+s);
			}
		}//End of try
		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Closing Client Connections");
			socket.close();
			serverSocket.close();
		}
	}
}