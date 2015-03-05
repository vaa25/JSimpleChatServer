package examples;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;
import java.util.logging.Logger;


/**
 * Created by vaa25 on 25.02.2015.
 */
@WebServlet(name = "MyEcho WebSocket Servlet", urlPatterns = {"/jsimplechat"})
public class MyWebSocketServlet extends WebSocketServlet {
    private static Logger log = Logger.getLogger(MyWebSocket.class.getName());
    @Override
    public void configure(WebSocketServletFactory factory) {
        log.info("MyWebSocketServlet configures");
        factory.getPolicy().setIdleTimeout(15 * 60 * 1000);
        factory.register(MyWebSocket.class);
    }
}
