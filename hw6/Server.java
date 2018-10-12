package hw8;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Slf4j
@Data
public class Server {
    //private static final Logger log = LoggerFactory.getLogger(Server.class);
    //private static final Logger log = LogManager.getLogger(Server.class);
    private static final Logger log = Logger.getLogger(Server.class.getName());
    private ServerSocket serverSocket;
    private AuthService authService;
    private Map<String, ClientHandler> clients = new HashMap<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public Server(AuthService authService) {
        this.authService = authService;
        try {
            serverSocket = new ServerSocket(8189);
            //System.out.println("Сервер запущен, ожидаем подключения...");
            getInfoLogg("Сервер запущен, ожидаем подключения...");
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
            close();
        }
    }

    public void close() {
        try {
            serverSocket.close();
            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        //AuthService baseAuthService = new BaseAuthService();
        AuthService dbAuthService = new DbAuthService();
        Server server = new Server(dbAuthService);
        server.start();
    }

    private void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void sendBroadcastMessage(String msg) {
        for (ClientHandler client : clients.values()) {
            client.sendMessage(msg);
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNickTaken(String nick) {
        return clients.containsKey(nick);
    }

    public void subscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " подключился";
        sendBroadcastMessage(msg);
        //System.out.println(msg);
        getInfoLogg(msg);
        clients.put(clientHandler.getNick(), clientHandler);
    }

    public void unsubscribe(ClientHandler clientHandler) {
        String msg = "Клиент " + clientHandler.getNick() + " отключился";
        sendBroadcastMessage(msg);
        //System.out.println(msg);
        getInfoLogg(msg);
        clients.remove(clientHandler.getNick());
    }

    public void sendPrivateMessage(String nickFrom, String nickTo, String message) {
        ClientHandler fromClient = clients.get(nickFrom);
        if (fromClient != null)
            fromClient.sendMessage(message);

        if (clients.containsKey(nickTo))
            clients.get(nickTo).sendMessage(message);
    }

    public void getInfoLogg(String msg){
        log.info(msg);
    }
}