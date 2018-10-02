package hw8;

import java.io.*;
import java.util.Scanner;

public class LocalHistory {

    FileReader fr = null;
    FileWriter fw = null;
    String fileName = "localhistory.txt";
    ClientUI clientUI;

    public LocalHistory(ClientUI clientUI){ //открытие потоков работы с файлом и его создание если нужно
        this.clientUI = clientUI;
        try {
            fw = new FileWriter(fileName, true);
            fr = new FileReader(fileName);
        } catch (IOException e){ }
    }

    public void toFile(String str){ //запись в файл
        try(BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(str);
            bw.flush();
        } catch (IOException e){ }
    }

    public void getHistory(){ // считывание из файла последних 100 строк
        String s;
        int j = 0;
        try (BufferedReader br = new BufferedReader(fr)) {
            if (getRowNums() < 100){
                while ((s = br.readLine()) != null) clientUI.addMessage(s);
            } else {
                while ((s = br.readLine()) != null){
                    if (j >= (getRowNums() - 100)) clientUI.addMessage(s);
                    j++;
                }
            }
        } catch (IOException e){ }


    }

    private int getRowNums(){ //получение кол-ва строк в файле
        int i = 0;
        Scanner sc = new Scanner(fr);
        while (sc.hasNext()) i++;
        sc.close();
        return i;
    }

    public void fsClose(){ //закрытие потоков рабоыт с файлом
        try {
            if (fw!= null) fw.close();
            if (fr!= null) fr.close();
        } catch (IOException e){ }
    }

}
