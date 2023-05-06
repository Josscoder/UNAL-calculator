package josscoder.unal;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Ingresa el nombre de la carrera: ");
        String nombreDeLaCarrera = input.nextLine();

        System.out.print("Ingresa el puntaje máximo posible de la prueba: ");
        double puntajeMaximo = input.nextDouble();

        System.out.print("Ingresa el puntaje de corte del ciclo 1: ");
        double puntajeCorteCiclo1 = input.nextDouble();

        System.out.print("Ingresa el puntaje de corte del ciclo 2: ");
        double puntajeCorteCiclo2 = input.nextDouble();

        System.out.print("Ingresa la suma de admitidos de los dos ciclos: ");
        int admitidosSumaCiclos = input.nextInt();

        System.out.print("Ingresa tu puntaje obtenido: ");
        double miPuntaje = input.nextDouble();

        int cuposTotales = (int) Math.round(admitidosSumaCiclos * 1.2);

        double probabilidad;
        if (miPuntaje >= puntajeCorteCiclo1 && miPuntaje >= puntajeCorteCiclo2) {
            probabilidad = 1.0;
        } else if (miPuntaje >= puntajeCorteCiclo1 || miPuntaje >= puntajeCorteCiclo2) {
            int admitidosRestantes = cuposTotales - admitidosSumaCiclos;
            double probabilidadCiclo1 = admitidosSumaCiclos > 0 ? ((admitidosSumaCiclos - admitidosRestantes) / (double) admitidosSumaCiclos) : 0.0;
            double probabilidadCiclo2 = admitidosSumaCiclos > 0 ? ((admitidosSumaCiclos - admitidosRestantes) / (double) admitidosSumaCiclos) : 0.0;
            double probabilidadRestante = (1.0 - probabilidadCiclo1 - probabilidadCiclo2);
            double porcentajeCorte1 = ((puntajeMaximo - puntajeCorteCiclo1) / puntajeMaximo);
            double porcentajeCorte2 = ((puntajeMaximo - puntajeCorteCiclo2) / puntajeMaximo);
            double promedioCorte = (porcentajeCorte1 + porcentajeCorte2) / 2.0;
            probabilidad = (promedioCorte * probabilidadRestante) + (probabilidadCiclo1 * porcentajeCorte1) + (probabilidadCiclo2 * porcentajeCorte2);
            if (admitidosRestantes > 0) {
                probabilidad += (probabilidadRestante * ((admitidosRestantes / (double) cuposTotales) * (1.0 - promedioCorte)));
            }
        } else {
            probabilidad = 0.0;
        }

        System.out.printf("La probabilidad de entrar a la carrera (%s) es del %.2f%%\n", nombreDeLaCarrera, probabilidad * 100);
    }
}