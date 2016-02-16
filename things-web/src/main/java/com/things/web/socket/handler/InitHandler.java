package com.things.web.socket.handler;

import com.things.web.socket.domain.GameRequest;
import com.things.web.socket.domain.GameResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("socket.init")
public class InitHandler implements GameHandler {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public void execute(GameRequest request, GameResponse response) {
		this.logger.error(request.readString());
		response.write("I am ok!");
	}
}
