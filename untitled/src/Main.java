import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {

    static boolean es_debug = false;
    private static void pr_ascensores(int num_ascensores, int piso_llamada, int[] pisos_ascensor) {
        int[] distancias = new int[pisos_ascensor.length];

        for (int i = 0; i < num_ascensores; i++) {
            int aux =  Math.abs(piso_llamada - pisos_ascensor[i]);
            distancias[i] = aux;
        }

        List<Integer> indices = new ArrayList<Integer>();
        int[] orden = new int[pisos_ascensor.length];
        for (int i = 0; i < num_ascensores; i++) {
            indices.add(i);
        }

        // ordenar
        indices.sort(Comparator.comparingInt(i -> distancias[i]));

        if (es_debug) {
            System.out.println("##################################");
            System.out.printf("Pisos Ascensor: ");

            for (int i = 0; i < num_ascensores; i++) {
                System.out.printf("%d ", pisos_ascensor[i]);
            }

            System.out.printf("\nIndices:");

            for (int i = 0; i < num_ascensores; i++) {
                System.out.printf("%d ", indices.get(i));
            }

            System.out.printf("\nDistancias:");

            for (int i = 0; i < num_ascensores; i++) {
                System.out.printf("%d ", distancias[i]);
            }

            System.out.println("\n##################################");
        }
        pisos_ascensor[indices.get(0)] = piso_llamada;

        // buscar si hay alguno en otro en el indice 0
        boolean en_cero = false;
        for (int i = 0; !en_cero & i < num_ascensores; i++) {
            if (i == indices.get(0)) continue;
            en_cero = pisos_ascensor[i] == 0;
        }

        if (!en_cero)
            pisos_ascensor[indices.get(1)] = 0;

    }

    private static void tests () {
        test(1,3, 0, new int[] {0,2,3}, new int[] {0,0,3});
        test(2,3, 7, new int[] {0,2,3}, new int[] {0,2,7});
        test(3,3, 1, new int[] {-1,0,7}, new int[] {0,1,7});
        test(4, 5, 10,
                new int[] {3,6,0,14,90}, new int[]{3,10,0,14,90}); // tanto el indice 1 y 4 pueden ir a 10
        test(5, 5, 1,
                new int[] {-1,0,2,5,-1}, new int[]{-1,1,0,5,-1}); // tanto el indice 1 y 4 pueden ir a 10


    }

    private static void test(int index, int num_ascensores, int piso_llamada, int[] pisos_ascensor, int[] esperado) {
        boolean res = run(num_ascensores, piso_llamada, pisos_ascensor, esperado);

        System.out.printf("Test %d: %s\n",
                index,
                (res) ? "Superado": "Error" // valores
        );

        if (!res) {
            System.out.printf("\tObtenido: ");

            for(int i = 0; i < num_ascensores; i++) {
                System.out.printf("%d ", pisos_ascensor[i]);
            }

            System.out.printf("\n\tEsperado: ");
            for(int i = 0; i < num_ascensores; i++) {
                System.out.printf("%d ", esperado[i]);
            }
            System.out.println();
        }
    }

    private static boolean run(int num_ascensores, int piso_llamada, int[] pisos_ascensor, int[] esperado) {
        // chequear valores
        if (num_ascensores != pisos_ascensor.length) // error
            return false;

        if (esperado.length != pisos_ascensor.length) // error
            return false;

        if (num_ascensores < 2 || num_ascensores > 10)
            return false;

        if (num_ascensores < -2 || num_ascensores > 100)
            return false;

        // ejecuta el ejercicio
        pr_ascensores(num_ascensores, piso_llamada, pisos_ascensor);

        boolean valido = true;
        for(int i = 0; i < num_ascensores && valido; i++) {
            valido &= pisos_ascensor[i] == esperado[i];
        }

        return valido;
    }

    /* *
     *  N ascensores
     *  Siempre hay un ascensor en la planta 0
     *  Al llamar acude el más cercano.
     *  Si se llama desde la planta baja, el ascensor más cercano acude a la planta baja
     */

    public static void main(String[] args) {

        // Prueba que funcione correctamente.
        tests();
    /*
        int num_ascensores; // 2 <= N <= 10
        int piso_llamada;  // -2 <= P <= 100

        Scanner sc = new Scanner(System.in);

        num_ascensores = sc.nextInt();
        piso_llamada =  sc.nextInt();

        int[] piso_ascensor = new int[num_ascensores];

        for (int i = 0; i < num_ascensores; i++) // -2 <= P <= 100
        {
            System.out.printf("Introduce valor para el piso[%d]: \n", i);
            piso_ascensor[i] = sc.nextInt();
        }*/
    }
}