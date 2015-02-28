package examples;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;


/**
 * Created by vaa25 on 25.02.2015.
 */
@WebServlet(name = "MyEcho WebSocket Servlet", urlPatterns = {"/jsimplechat"})
public class MyWebSocketServlet extends WebSocketServlet {

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(15 * 60 * 1000);
        factory.register(MyWebSocket.class);
    }
}
