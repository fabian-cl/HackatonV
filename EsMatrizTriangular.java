import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class EsMatrizTriangular {
    public static boolean esTriangular(int[][] matriz) {
        return esSuperior(matriz) || esInferior(matriz);
    }

    public static boolean esSuperior(int [][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < i; j++) {
                if(matriz[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean esInferior(int [][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = i + 1; j < matriz.length; j++) {
                if(matriz[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out));
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

            if(esTriangular(matriz)) {
                output.println("SI");
            }else {
                output.println("NO");
            }
        }
        output.flush();
    }
}
