import java.net.*;
import java.io.*;
public class UDPClient
{
	public static void main(String[] args) throws IOException
	{
		DatagramSocket socket = new DatagramSocket();
		Console con = System.console();
		DatagramPacket packet;
		byte arr[];
		try
		{
			System.out.println("Start typing :");
			String str = null;
		
			boolean flag = true;
			while(flag)
			{
				str = con.readLine();
				arr = str.getBytes();
				packet = new DatagramPacket(arr, arr.length,InetAddress.getByName("localhost"), 8081);
				socket.send(packet);
				if(str.equalsIgnoreCase("END"))
					flag=false;
			}
		}
		finally
		{
			System.out.println("Closing connection..");
			socket.close();
		}
	}
}

