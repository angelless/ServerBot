package serverbot;

import java.net.*;


import java.io.*;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerDeathEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;


public class Main extends PluginBase implements Listener {
	public final static int port = {You select Server Port};

	public static Inet4Address server;
	public static DatagramSocket dsock;

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		try {
			this.getLogger().info("1차 성공");
			Main.server = (Inet4Address) Inet4Address.getByName({You select Server IP});

			Main.dsock = new DatagramSocket();

		} catch (UnknownHostException e) {
			this.getLogger().info("server를 정상적으로 초기화 하지 못하였습니다");
		} catch (SocketException e) {
			e.printStackTrace();
		}
		this.getLogger().info(server.getHostAddress() + server.getHostName() + "정상적으로 완료");

		this.sendPacket(this.getServer().getIp() + ":" + this.getServer().getPort() + "에서 서버가 실행되었습니다");
	}

	@EventHandler
	public void onChat(PlayerChatEvent event) {
		StringBuilder mes = new StringBuilder();
		mes.append("`" + event.getPlayer().getName() + "` : " + event.getMessage());

		this.sendPacket(mes.toString());

	}

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {

		StringBuilder str = new StringBuilder();
		str.append("DeathEvent : ").append(event.getEntity().getName()).append("님이 사망하셨습니다");
		this.sendPacket(str.toString());

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		this.sendPacket(event.getPlayer().getName() + "님이 접속하셨습니다");
	}

	public void sendPacket(String message) {
		DatagramPacket packet;

		try {
			byte[] data = message.getBytes("utf-8");
			packet = new DatagramPacket(data, data.length, server, port);
			dsock.send(packet);
		} catch (UnsupportedEncodingException e1) {
			this.getLogger().info("오류!");
		} catch (IOException e) {
			this.getLogger().info("오류!");
		}

	}

}
