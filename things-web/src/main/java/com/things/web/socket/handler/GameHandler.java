package com.things.web.socket.handler;


import com.things.web.socket.domain.GameRequest;
import com.things.web.socket.domain.GameResponse;

public abstract interface GameHandler
{
  public abstract void execute(GameRequest paramGameRequest, GameResponse paramGameResponse);
}
