import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class CombinePrintln {
    public static void main(String[] args) {

        String readFilePath = args[0];
        String writeFilePath = readFilePath.replace(".", "Println.");

        Scanner sc = new Scanner(System.in);
        String command;

        do {
            System.out.print("入出力ファイルの文字コード指定する？(y/n)：");
            command = sc.next();
        } while (!command.equals("y") && !command.equals("n"));

        if (command.equals("n")) {
            combinePrintln(readFilePath, writeFilePath);
        } else if (command.equals("y")) {
            System.out.print("入力ファイルの文字コード：");
            String inputCharEncoding = sc.next();
            System.out.print("出力ファイルの文字コード：");
            String outputCharEncoding = sc.next();
            combinePrintln(readFilePath, inputCharEncoding, writeFilePath, outputCharEncoding);
        }
        
        System.out.println(writeFilePath + "で出力した");
    }

    private static void combinePrintln(String readFilePath, String writeFilePath) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(readFilePath)));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeFilePath)));) {

            String line;
            while ((line = br.readLine()) != null) {
                writeEscapedPrintlnLine(bw,line);
            }

        } catch (IOException e) {
            System.out.println("なんかエラーしたよ");
            e.printStackTrace();
        }

    }

    private static void combinePrintln(String readFilePath, String inputCharEncoding, String writeFilePath,
            String outputCharEncoding) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(readFilePath), inputCharEncoding));
                BufferedWriter bw = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(writeFilePath), outputCharEncoding));) {

            String line;
            while ((line = br.readLine()) != null) {
                writeEscapedPrintlnLine(bw, line);
            }

        } catch (IOException e) {
            System.out.println("なんかエラーしたよ");
            e.printStackTrace();
        }
    }

    private static void writeEscapedPrintlnLine(BufferedWriter bw, String line) throws IOException {
        // バックスラッシュをエスケープ処理
        line = line.replace("\\", "\\\\");
        // ダブルクォーテーションをエスケープ処理
        line = line.replace("\"", "\\\"");
        line = "pw.println(\" " + line + " \");";
        bw.write(line);
        bw.newLine();
    }
}