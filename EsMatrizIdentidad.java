import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class EsMatrizIdentidad {

    public static boolean esIdentidad(int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (i == j) {
                    if (matriz[i][j] != 1) {
                        return false;
                    }
                } else {
                    if (matriz[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(System.out));
        int[][] matriz;


        String line;
        while ((line = buffer.readLine()) != null && !line.trim().isEmpty()) {
            int T = Integer.parseInt(line.trim());

            if (T == 0) {
                break;
            }
            matriz = new int[T][T];
            for (int i = 0; i < matriz.length; i++) {
                line = buffer.readLine();
                StringTokenizer token = new StringTokenizer(line);
                for (int j = 0; j < matriz.length; j++) {
                    matriz[i][j] = Integer.parseInt(token.nextToken());
                }
            }
            if (esIdentidad(matriz)) {
                writer.println("SI");
            } else {
                writer.println("NO");
            }
        }
        writer.flush();
        
    }
}