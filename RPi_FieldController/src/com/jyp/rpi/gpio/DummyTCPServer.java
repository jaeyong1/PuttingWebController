package com.jyp.rpi.gpio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class DummyTCPServer {
	static ServerSocket server;

	public static void startServer() {

		try {

			server = new ServerSocket(9999);
			System.out.println("Wating Connect ..");

			while (true) {
				// wait connect
				Socket sock = server.accept();

				InetAddress inetaddr = sock.getInetAddress();
				//System.out.println(inetaddr.getHostAddress() + " 로부터 접속했습니다.");

				OutputStream out = sock.getOutputStream();
				InputStream in = sock.getInputStream();
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String line = null;

				line = br.readLine();

				/////////////
				System.out.println("> [dummy server]" + line);
				pw.println(line);
				pw.flush();

				/////////////////

				pw.close();
				br.close();
				sock.close();
			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
		}

	}
}
