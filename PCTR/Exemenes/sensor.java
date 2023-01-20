import java.util.Scanner;

public class sensor extends Thread {
    static int tam;
    static int[] sensor1;
    static int[] sensor2;
    static int[] resultado;
    int inicio;
    int fin;

    
    public sensor(int inicio, int fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public void run(){
        for(int i = inicio; i < fin; i++){
            if(i == 0){
                resultado[i] = sensor1[i] + sensor1[i + 1] + sensor2[i];
            }
            else if(i == tam-1){
                resultado[i] = sensor1[i] + sensor2[i] + sensor2[i - 1];
            }
            else{
                resultado[i] = sensor1[i] + sensor1[i + 1] + sensor1[i] + sensor2[i - 1];
            }
        }
    }
    
    public static void imprimeresultadoVec(){
        System.out.println("Señal 1:\t\t\t\t\tSeñal 2:");
        for(int i = 0; i < tam; i++){
            if(i == tam-1)
                System.out.print(sensor.sensor1[i]);
            else
                System.out.print(sensor.sensor1[i] + ", ");
        }
        System.out.print("\t\t\t\t\t");
        for(int i = 0; i < tam; i++){
            if(i == tam-1)
                System.out.print(sensor.sensor2[i]);
            else
                System.out.print(sensor.sensor2[i] + ", ");
        }
        System.out.println("\n----------------------------------------------------------");
        System.out.println("Resultado:");
        for(int i = 0; i < tam; i++){
            if(i == tam-1)
                System.out.print(sensor.resultado[i]);
            else
                System.out.print(sensor.resultado[i] + ", ");
        }
        System.out.println("\n");
    }

    private static void inicializa(int tam){
        sensor.tam = tam;
        sensor.sensor1 = new int[tam];
        sensor.sensor2 = new int[tam];
        sensor.resultado = new int[tam];
    }

    public static void main(String[] args) throws InterruptedException {

        boolean salir = true;
        while(salir){
            System.out.println("Menu de opciones:");
            System.out.println("1. Modo manual");
            System.out.println("2. Modo automatico");
            System.out.println("3. Salir");
            Scanner entrada = new Scanner(System.in);

            int opcion = entrada.nextInt();
            if(opcion == 1){
                System.out.println("Introduzca el tamaño del array");
                int tam = entrada.nextInt();
                inicializa(tam);
                System.out.println("Introduzca los valores del sensor 1");
                for(int i = 0; i < tam; i++){
                    sensor.sensor1[i] = entrada.nextInt();
                }
                System.out.println("Introduzca los valores del sensor 2");
                for(int i = 0; i < tam; i++){
                    sensor.sensor2[i] = entrada.nextInt();
                }
                System.out.println("Introduzca el de hebras");
                int nHebras = entrada.nextInt();
                int rango;
                if(tam < nHebras){
                    System.out.println("El numero de hebras es mayor que el tamaño del array, por lo que ejecutare solo tantas hebras como elementos tenga el array\n");
                    rango = 1;
                    nHebras = tam;
                }
                else{
                    rango = tam/nHebras;
                }
                Thread[] hebras = new Thread[nHebras];
                for(int i = 0; i < nHebras; i++){
                    if(i == nHebras-1){
                        hebras[i] = new sensor(i*rango, tam);
                    }
                    else{
                        hebras[i] = new sensor(i*rango, (i+1)*rango);
                    }
                    hebras[i].start();
                }
                for(int i = 0; i < nHebras; i++){
                    hebras[i].join();
                }
                imprimeresultadoVec();
            }
            else if(opcion == 2){
                inicializa(10000000);
                for(int i = 0; i < tam; i++){
                    sensor.sensor1[i] = (int) (Math.random() * Integer.MAX_VALUE);
                    sensor.sensor2[i] = (int) (Math.random() * Integer.MAX_VALUE);
                }
                int nNucleos = Runtime.getRuntime().availableProcessors();
                int rango = tam/nNucleos;
                long Inicio = System.currentTimeMillis();
                    Thread secuencial = new sensor(0, tam);
                    secuencial.start();
                    secuencial.join();
                long fin = System.currentTimeMillis();
                System.out.println("Tiempo de ejecucion secuencial: " + (fin - Inicio) + " milisegundos");
                Thread[] hebras = new Thread[nNucleos];
                Inicio = System.currentTimeMillis();
                for(int i = 0; i < nNucleos; i++){
                    if(i == nNucleos-1){
                        hebras[i] = new sensor(i*rango, tam);
                    }
                    else{
                        hebras[i] = new sensor(i*rango, (i+1)*rango);
                    }
                    hebras[i].start();
                }
                for(int i = 0; i < nNucleos; i++){
                    hebras[i].join();
                }
                fin = System.currentTimeMillis();
                System.out.println("Tiempo de ejecucion con " + nNucleos + " hebras: " + (fin - Inicio) + " milisegundos\n");
            }
            else if(opcion == 3){
                System.exit(0);
            }
            else{
                System.out.println("Opcion incorrecta");
            }
        }


    }
}
