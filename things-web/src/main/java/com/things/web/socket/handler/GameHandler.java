package com.things.web.socket.handler;


import com.things.web.socket.domain.GameRequest;
import com.things.web.socket.domain.GameResponse;

/**
* @project:		demo
* @Title:		GameHandler.java
* @Package:		cpgame.demo.handler
  @author: 		chenpeng
* @email: 		46731706@qq.com
* @date:		2015年8月20日 下午2:25:51 
* @description:
* @version:
*/

public abstract interface GameHandler
{
  public abstract void execute(GameRequest paramGameRequest, GameResponse paramGameResponse);
}
