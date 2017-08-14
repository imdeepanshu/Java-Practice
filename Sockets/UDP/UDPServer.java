import java.net.*;
import java.io.*;

public class UDPServer
{

	public static void main(String[] args) throws IOException
	{
		DatagramSocket socket = new DatagramSocket(8081);
		System.out.println("Started : " + socket);
		DatagramPacket packet;
		byte arr[];
		InetAddress clientAddr;
		int clientPort;
		try
		{
			while(true)
			{
				arr = new byte[100];
				packet = new DatagramPacket(arr, 100);
				socket.receive(packet);
				clientAddr = packet.getAddress();
				clientPort = packet.getPort();
				System.out.println("Address is "+clientAddr+" and port is "+clientPort);
				String str = new String(arr, 0, packet.getLength());
				System.out.println("Echoing : " + str);
				if(str.equalsIgnoreCase("end"))
					break;
			}
		}
		finally
		{
			System.out.println("Closing connection..");
			socket.close();
		}
	}
}