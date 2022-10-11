class Prueba_Hilo1 //Hace uso de la clase anterior
{ 	//hola
	public static void main(String[] arg) throws InterruptedException {
		Ejemplo_Hilos1 Hilo1 = new Ejemplo_Hilos1(5);
		Ejemplo_Hilos1 Hilo2 = new Ejemplo_Hilos1(15);

		Hilo1.start(); //Ahora se lanzan ambos hilos...
		Hilo2.start(); //con apertura de co-rutina

		Hilo1.join();
		Hilo2.join(); // y cierre de co-rutina

		System.out.println("Hilos terminados");
	}
}
