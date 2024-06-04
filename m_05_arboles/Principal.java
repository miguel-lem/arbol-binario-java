package m_05_arboles;


import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arbol arbol = new Arbol();
		Scanner teclado = new Scanner(System.in);
		int opcion,paso;
		//int vector1[]= {32,10,54,8,9,15,50,59,6,4,12,27,7,52,2,57,64,23,58};
		int vector1[] = {7,5,9,8,12,10,15};
		//int vector1[] = {10,11,8,9,6,7,4,5};
		do {
			System.out.println("----------Opciones----------");
			System.out.println("1.- para ingresar datos en el arbol");
			System.out.println("2.- para ver en preorden los datos");
			System.out.println("3.- para ver en inorden los datos");
			System.out.println("4.- para ver en postorden los datos");
			System.out.println("5.- para ver en anchura el arbol");
			System.out.println("6.- para ver en profundidad el arbol");
			System.out.println("7.- para ver un dato dentro de arbol");
			System.out.println("8.- para eliminar un dato dentro de arbol");
			System.out.println("9.- para ver el nivel donde esta un numero");
			System.out.println("10.- para ver alturas en lado derecho e izquierdo");
			System.out.println("11.- para ver los balances o desbalances del arbol");
			System.out.println("0.- para salir del programa");
			opcion = teclado.nextInt();
			
			switch (opcion) {
			case 1: 
				do {
					/*System.out.println("");
					System.out.println("Ingrese el dato al arbol");
					int dato = teclado.nextInt();
					arbol.insertar(dato);
					arbol.llamadabalance();*/
					//este for es porque le queria cargar los datos del vector que cree, porque sino me demoraba mucho en las pruebas
					//al tener que cargar datos manualmente
					for(int i =0;i<vector1.length;i++) {
						System.out.println("numero de vueltas: "+i);
						arbol.insertar(vector1[i]);
						arbol.llamadabalance();
					}
					System.out.println("el proceso automatizado a terminado de ingresar los datos");
					System.out.println("");
					System.out.println("1 para continuar ingresando datos, 0 para salir");
					paso = teclado.nextInt();
					System.out.println("");
				}while(paso!=0);
				break;
			case 2: 
				System.out.println("");
				System.out.println("Datos almacenados en el arbol. lectura preorden");
				arbol.preordenarranque();
				System.out.println("");
				System.out.println("cantidad de nodos: "+arbol.cantNodos());
				System.out.println("");
				break;
			case 3: 
				System.out.println("");
				System.out.println("Datos almacenados en el arbol. lectura inorden");
				arbol.inordenarranque();
				System.out.println("");
				break;
			case 4: 
				System.out.println("");
				System.out.println("Datos almacenados en el arbol. lectura postorden");
				arbol.postordenarranque();
				System.out.println("");
				break;
			case 5: 
				System.out.println("");
				System.out.println("Datos almacenados en el arbol. lectura en anchura");
				arbol.llamadaanchura();
				System.out.println("");
				break;
			case 6: 
				System.out.println("");
				System.out.println("Datos almacenados en el arbol. lectura en profundidad");
				arbol.llamadaprofundidad();
				System.out.println("");
				break;
			case 7: 
				System.out.println("");
				System.out.println("Que numero desea buscar dentro del arbol");
				int dato = teclado.nextInt();
				System.out.println("Respuesta: "+arbol.existeelemento(dato));
				System.out.println("");
				break;
			case 8: 
				System.out.println("");
				System.out.println("Que numero desea eliminar dentro de arbol");
				int dat = teclado.nextInt();
				arbol.eliminar(dat);
				System.out.println("");
				break;
			case 9: 
				System.out.println("");
				System.out.println("Altura del arbol");
				System.out.println("altura: "+arbol.llamadaaltura());
				System.out.println("");
				break;
			case 10: 
				System.out.println("");
				System.out.println("Altura del arbol lado izquierdo y derecho");
				System.out.println("altura izquierda: "+arbol.llamadaalturaizquierdo());
				System.out.println("");
				System.out.println("altura derecha: "+arbol.llamadaalturaderecho());
				System.out.println("");
				break;
			case 11: 
				System.out.println("");
				System.out.println("Respuesta de los balances y desbalances del arbol");
				arbol.llamadabalance();
				System.out.println("");
				break;
			}
		}while(opcion!=0);
		teclado.close();
	}

}
