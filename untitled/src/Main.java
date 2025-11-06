import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static void pr_ascensores(int num_ascensores, int piso_llamada, int[] pisos_ascensor) {
        int[] distacias = new int[pisos_ascensor.length];

        for(int i = 0; i < num_ascensores; i++) {
            distacias[i] = Math.abs(piso_llamada - pisos_ascensor[i]);
        }

        int[] orden = new int[pisos_ascensor.length];
        for(int i = 0; i < num_ascensores; i++) {
            orden[i] = i;
        }
        

    }

    private static void tests() {
        int[] pisos_ascensor = new int[] {0,2,3};
        boolean res = test(3, 0, pisos_ascensor, new int[] {0,0,3});

        System.out.printf("Test %d: %s",
                1, // index
                (res) ? "Superado": "Error" // valores
        );
    }

    private static boolean test(int num_ascensores, int piso_llamada, int[] pisos_ascensor, int[] esperado) {
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