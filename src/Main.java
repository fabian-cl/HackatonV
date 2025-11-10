import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    static final char[] CODES = {'D', 'A', 'M', 'I', 'C'};
    static final String[] NOMBRES = {"Desayunos", "COMIDAS", "MERIENDAS","CENAS", "COPAS"};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        float[] sumaPorCategoria = new float[CODES.length];
        int[] contarEntradasPorCategoria = new int[CODES.length];

        String linea;
        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
            if (linea.isEmpty()) continue;;

            StringTokenizer st = new StringTokenizer(linea);
            String textoCategoria = st.nextToken();
            float importe = Float.parseFloat(st.nextToken());

            if (textoCategoria.equals("N") && importe == 0.0f) {
                String res = procesarDia(sumaPorCategoria, contarEntradasPorCategoria);

                // escribe por pantalla
                out.write(res,0, res.length());
                out.flush();

            } else {
                char codigo = textoCategoria.charAt(0);
                int posQueOcupaCategoria = getPosCategoria(codigo);
                if (posQueOcupaCategoria > 0) {
                    añadirVenta(sumaPorCategoria,
                            posQueOcupaCategoria, importe,
                            contarEntradasPorCategoria);

                }
            }
        }
    }

    static float getMax(float[] sum) {
        float vMax = Float.MIN_VALUE;
        for (int i = 0; i < sum.length; i++) {
            if (vMax < sum[i]) {
                vMax = sum[i];
            }
        }

        return vMax;
    }

    static float getMin(float[] sum) {
        float vMin = sum[0];
        for (int i = 0; i < sum.length; i++) {
            if (vMin > sum[i]) {
                vMin = sum[i];
            }
        }

        return vMin;
    }

    static int getMaxIndex(float[] sum) {
        float vMax = sum[0];
        int index = 0;
        for (int i = 0; i < sum.length; i++) {
            if (vMax < sum[i]) {
                vMax = sum[i];
                index = i;
            }
        }

        return index;
    }

    static int getMinIndex(float[] sum) {
        float vMin = sum[0];
        int index = 0;
        for (int i = 0; i < sum.length; i++) {
            if (vMin > sum[i]) {
                vMin = sum[i];
                index = i;
            }
        }

        return index;
    }

    static int vecesNum (float[] sum, float valor) {
        int contador = 0;
        for (int i = 0; i < sum.length; i++) {
            if (valor < sum[i]) {
                contador++;
            }
        }

        return contador;
    }

    static float mediaComida(float[] sum, int[] contador) {
        int pos = getPosCategoria('A');
        return sum[pos] / contador[pos];
    }

    static float mediaTotalDia(float[] sum, int[] contador) {
        float sumTotal = .0f;
        int contadorTotal = 0;
        for (int i = 0; i < CODES.length; i++) {
            sumTotal += sum[i];
            contadorTotal += contador[i];
        }

        return sumTotal/contadorTotal;
    }

    static String procesarDia(float[] sum, int[] contador) {
        float max = getMax(sum);
        float min = getMin(sum);

        float mediaA = mediaComida(sum, contador);
        float mediaTotal = mediaTotalDia(sum, contador);

        String supera = (mediaA > mediaTotal) ? "SI" : "NO";

        // indices del valor max
        int contador_indices_max = vecesNum(sum, max);
        // indices del valor minimo
        int contador_indices_min = vecesNum(sum, min);

        String res = "";

        if (contador_indices_max > 1) {
            res += "EMPATE";
        } else {
            int index = getMaxIndex(sum);
            res += NOMBRES[index];
        }

        res += "#";

        if (contador_indices_min > 1) {
            res += "EMPATE";
        } else {
            int index = getMinIndex(sum);
            res += NOMBRES[index];
        }

        res += "#" + supera;

        return res;
    }

    static void  añadirVenta(float[] sumaParcial, int pos, float impo, int[] cont) {
        sumaParcial[pos] = sumaParcial[pos] + impo;
        cont[pos] = cont[pos] + 1;
    }

    static int getPosCategoria(char cod) {
        for (int i = 0; i <CODES.length; i++) {
            if (CODES[i] == cod) {
                return i;
            }
        }

        return -1;
    }
}