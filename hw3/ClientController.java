package hw8;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class ClientController implements Controller {
    private final static String SERVER_ADDR = "localhost";
    private final static int SERVER_PORT = 8189;
    private ClientUI ui;

    private Socket sock;
    private Scanner in;
    private PrintWriter out;
    private int index = new Random().nextInt(3) + 1;
    private LocalHistory localHistory;

    public ClientController() {
        initConnection();
    }

    public void showUI(ClientUI ui) {
        this.ui = ui;
        ui.showUI();
        sendMessage("/auth login" + index + " pass" + index);
        localHistory = new LocalHistory(this.ui); // создаем / открываем запись в файл при инииализации интерфейса
                                                  // т.к. в LocalHistory используется метод ui.addMessage для вывода в textArea
    }

    private void initConnection() {
        try {
            sock = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new Scanner(sock.getInputStream());
            out = new PrintWriter(sock.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // при установлении соединения загружаем историю в textArea
        localHistory.getHistory();
        new Thread(() -> {
            try {
                while (true) {
                    if (in.hasNext()) {
                        String w = in.nextLine();
                        if (w.startsWith("end session")) break;
                        ui.addMessage(w);
                        localHistory.toFile(w); //при появлении сообщения в потоке записываем его в историю
                    }
                }
            } catch (Exception e) {
            }
        }).start();
    }

    @Override
    public void sendMessage(String msg) {
        out.println(msg);
        localHistory.toFile(msg); // при отправке сообщения также записываем его в историю
    }

    @Override
    public void closeConnection() {
        try {
            sendMessage("/exit");
            sock.close();
            out.close();
            in.close();
            localHistory.fsClose(); // при закрытии соединения закрываем запись/чтение в файл истории
        } catch (IOException exc) {
        }
    }
}