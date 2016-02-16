package com.things.web.socket;

import java.net.InetSocketAddress;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.things.web.socket.domain.ERequestType;
import com.things.web.socket.netty.ServerInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.DisposableBean;

public class NettyServer implements DisposableBean {

	private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

	/** 用于分配处理业务线程的线程组个数 */
	protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2; 

	/** 业务出现线程大小 */
	protected static final int BIZTHREADSIZE = 4;

	private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);

	private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

	private ServerInitializer initializer;

	private final int port;

	private Channel channel = null;

	public NettyServer(int port) {
		this.port = port;

		DestroyWorkThread destroyWorkThread = new DestroyWorkThread(this);
		Runtime.getRuntime().addShutdownHook(destroyWorkThread);
	}

	public void setInitializer(ServerInitializer initializer) {
		this.initializer = initializer;
	}

	public void run() throws Exception {
		if(channel != null && channel.isOpen()){
			return ;
		}

		ServerBootstrap serverBootstrap = new ServerBootstrap();
		serverBootstrap.group(bossGroup, workerGroup);
		serverBootstrap.channel(NioServerSocketChannel.class);
		serverBootstrap.childHandler(this.initializer);

		this.logger.info(ERequestType.parse(this.initializer.getRequestType()).getValue() + " server started at port " + this.port + '.');

		if (ERequestType.HTTP.equals(ERequestType.parse(this.initializer.getRequestType()))) {
			channel = serverBootstrap.bind(this.port).sync().channel();
		} else{
			channel = serverBootstrap.bind(new InetSocketAddress(this.port)).sync().channel();
		}
		//channel.closeFuture().sync();
	}

	public Boolean isRun(){
		if(channel != null){
			return channel.isOpen();
		}

		return false;
	}

	public void stop(){
		if(channel != null && channel.isOpen()){
			channel.close();
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	@Override
	public void destroy() throws Exception {
		stop();
	}

	@Override
	protected void finalize(){
		stop();
	}

	class DestroyWorkThread extends Thread{

		private NettyServer nettyServer;

		public DestroyWorkThread(NettyServer nettyServer){
			nettyServer = nettyServer;
		}

		@Override
		public void run() {
			nettyServer.stop();
		}
	}
}
