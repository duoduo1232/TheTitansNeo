package net.byAqua3.thetitansneo.loader;

import net.byAqua3.thetitansneo.network.PacketAnimation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class TheTitansNeoNetworks {

	public static void registerNetworks(IEventBus modEventBus) {
		modEventBus.addListener(TheTitansNeoNetworks::onRegisterPayloadHandlers);
	}

	@SubscribeEvent
	public static void onRegisterPayloadHandlers(RegisterPayloadHandlersEvent event) {
		PayloadRegistrar registrar = event.registrar("1");

		registrar = registrar.executesOn(HandlerThread.MAIN);
		// 26.1.2: playBidirectional 的 3 参重载只登记服务端处理器（客户端处理器为 null），
		// 会在启动时报 "Some clientbound payloads are missing client-side handlers"。
		// 本包是服务端 -> 客户端的动画同步，用 playToClient 登记客户端处理器即可。
		registrar.playToClient(PacketAnimation.TYPE, PacketAnimation.STREAM_CODEC, new PacketAnimation.Handler());
	}}
