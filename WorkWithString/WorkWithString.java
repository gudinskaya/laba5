package org.anngudin.laba5.WorkWithString;

import java.io.*;

public class WorkWithString {
    String data;
    String filename;
    String choice;
    String choice2;
    RandomAccessFile file;
    BufferedReader in;

    public WorkWithString() throws UnsupportedEncodingException {
        this.in = new BufferedReader(new InputStreamReader(System.in));
    }

    public void menu() throws IOException {
        while (true) {
            printMenu();
            choice = in.readLine();

            if (choice.compareTo("1") == 0) {

                writeFile();
            } else if (choice.compareTo("2") == 0) {
                printRedactMenu();// вызов метода вывода возможных действий
                choice2 = in.readLine();// ввод выбранного пункта
                if (choice2.compareTo("1") == 0) {
                    addStart();// вызов метода добавления текста в начало файла
                } else if (choice2.compareTo("2") == 0) {
                    addEnd();// вызов метода добавления текста в конец файла
                } else if (choice2.compareTo("3") == 0) {
                    addRandom();// вызов метода добавления текста в указаную
                }
            } else if (choice.compareTo("3") == 0) {
                readFile();
                calcWords();// вызов метода чтения текста из файла и
            } else if (choice.compareTo("4") == 0) {
                readFile();
            } else if (choice.compareTo("5") == 0) {
                return;// выход из программы
            }
        }
    }

    public void printMenu() {// метод вывода меню на экран
        System.out.println("\nВведите ваш выбор: ");
        System.out.println("1.Ввести текст и записать его в файл ");
        System.out.println("2.Редактировать текст в файле");
        System.out.println("3.Посчитать кол-во четных/нечетных слов");
        System.out.println("4.Прочитать текст из файла");
        System.out.println("5.Выход ");
    }

    public void writeFile() throws IOException {
        System.out.println("\nВведите текст:");
        data = in.readLine();
        System.out.println("\nВведите имя файла:");
        filename = in.readLine();
        file = new RandomAccessFile(new File(filename), "rw");
        file.writeBytes(data);
        file.close();
        System.out.println("\nСохранено.");
    }

    public void printRedactMenu() {
        System.out.println("\nВыберите действие: ");
        System.out.println("1 - добавление текста в начало файла ");
        System.out.println("2 - добавление текста в конец файла");
        System.out.println("3 - добавление текста в произвольную позицию в файле ");
    }

    public void addStart() throws IOException {
        System.out.println("\nВведите имя файла:");
        filename = in.readLine();
        file = new RandomAccessFile(new File(filename), "rw");
        data = file.readLine();
        System.out.println("\nВведите строку для добавления в начало: ");
        String s;
        s = in.readLine();
        file.seek(0);
        file.writeBytes(s);
        file.seek(s.length());
        file.writeBytes(data);

        file.close();
        System.out.println("\nCтрока записана в начало файла. ");
    }

    public void addEnd() throws IOException {
        System.out.println("Введите имя файла: ");
        filename = in.readLine();

        file = new RandomAccessFile(new File(filename), "rw");
        data = file.readLine();
        System.out.println("\nВведите строку для добавления в конец:");
        String s;
        s = in.readLine();
        file.seek(file.length());
        file.writeBytes(s);
        file.close();
        System.out.println("\nCтрока записана в конец файла.  ");
    }

    public void addRandom() throws IOException {
        System.out.println("\nВведите имя файла: ");
        filename = in.readLine();
        file = new RandomAccessFile(new File(filename), "rw");
        System.out.println("\nВведите строку для добавления в указанную позицию в файле: ");
        String s;
        s = in.readLine();
        System.out.println("\nВведите необходимую позицию в файле:  ");
        int n;
        n = Integer.parseInt(in.readLine());
        file.seek(n);
        data = file.readLine();
        file.seek(n);
        file.writeBytes(s);
        file.writeBytes(data);
        file.close();
        System.out.println("\nCтрока записана в файл.");
    }

    public void readFile() throws IOException {

        System.out.println("\nВведите имя файла: ");
        filename = in.readLine();
        file = new RandomAccessFile(new File(filename), "r");
        data = file.readLine();
        file.close();
        System.out.println("\nИнформация из файла:  " + data);
    }

   
    public void calcWords() throws IOException {
        int evenWords = 0;
        int oddWords = 0;
        int words = 0;
        for (int i = 0; i < data.length(); i++) {

            if (Character.isWhitespace(data.charAt(i))) {
                words++;

            }
        }
        if (words % 2 == 0) {
            evenWords = words / 2;
            oddWords = evenWords;
            // return;
        } else { // if (words % 2 != 0)
            evenWords = (words - 1) / 2;
            oddWords = evenWords + 1;
        }

        System.out.println("Количество четных слов: " + evenWords + "\nКоличество нечетных слов: " + oddWords);

    }

}
