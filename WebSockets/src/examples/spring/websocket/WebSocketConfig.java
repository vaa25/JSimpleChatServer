package examples.spring.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import java.util.logging.Logger;

/**
 * Created by vaa25 on 09.03.2015.
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private static Logger log = Logger.getLogger(WebSocketConfig.class.getName());
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        log.info("register MyHandler");
        registry.addHandler(myHandler(), "/JSimpleChatServer/jsimplechat");
    }

    @Bean
    public WebSocketHandler myHandler() {
        return new MyHandler();
    }

}