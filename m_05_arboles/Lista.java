package m_05_arboles;



public class Lista {
	//primero creo dos variables de tipo nodo en si son dos objetos en la practica
		protected Nodolista inicio;
		protected Nodolista auxiliar;
		public Lista() {
			//inicializo los valores en nulo porque por defecto al principio estran vacios
			this.inicio = null;
		}
		//creo la funcion para poder agregarle al principio
		public void agregarInicio(int elemento) {
			//creo el objeto que va almacenar el elemento le paso el elemento y el objeto fin
			Nodolista nodo = new Nodolista(elemento,inicio);
			//en el objeto inicio le almaceno el contenido de nodo
			inicio=nodo;
		}

		//funcion para recorrer los nodos y colocar al final el elemento
		public void agregarFinal(int numero) {
			//instancio el nodo para pasarle el dato, en si es la posicion final le que se crea aqui
			Nodolista nodo = new Nodolista(numero,null);
			//compruebo que no este vacio
			if(estaVacia()) {
				//si esta vacio le coloco en el inicio el valor del nodo
				inicio=nodo;
			}else {
				//si esta ya con datos, le coloco en un auxiliar los valores
				auxiliar = inicio;
				//recorro el auxiliar con su pintero hasta encontrar las datos
				while(auxiliar.siguiente!=null) {
					auxiliar = auxiliar.siguiente;
				}
				//entonces el apuntador se auxiliar debe referencias al valor igresado en el nodo
				auxiliar.siguiente = nodo;
			}
		}
		
		//funcion para ingresar numeros en orden, esta funcion fue creada como una alternativa para ordenar los datos del arbol
		public void insertarordenado(int numero) {
			//creo el nodo nuevo
			Nodolista nodo = new Nodolista(numero,null);
			if(estaVacia()) {
				//si esta vacia la lista le cargo en la cabeza el primer dato
				inicio=nodo;
				System.out.println("primero dato del otro lado: "+nodo.getDato());
			}else {
				//necesito dos auxiliares porque asi podre seguir la secuencia de los datos uno mas adelante en posicion
				Nodolista auxiliar2=inicio;
				auxiliar = inicio.siguiente;
				if(nodo.getDato()<auxiliar2.getDato()) {
					//si el dato que viene es menor que el de cabeza tonce reutilizo la funcion de insertar al inicio
					agregarInicio(nodo.getDato());
					return;
				}else {
					//recorro cada uno de los nodos para buscar el dato
					while(auxiliar!=null) {
						//si el dato es menor que el siguiente tonce debe ser colocado alli
						if(nodo.getDato()<auxiliar.getDato()) {
							//el nodo apunta al otro es decir 12,13,17 existe, pero viene un 15, en al comparacion 15 es menor que 17 que esta 
							//en el aux1, por ende su siguiente de nodo apunta al aux1 
							nodo.siguiente = auxiliar;
							//ahora como el aux2 viene por detras el y su siguiente apunta al 15 es decir el 13.sig va a 15
							auxiliar2.siguiente=nodo;
							//ahora solo cargo los datos de aux2 en el aux1
							auxiliar=auxiliar2;
							return;
						}
						//aqui controlo la secuencia de los datos, una ira una posicion detras de la otra
						auxiliar2=auxiliar2.siguiente;
						auxiliar=auxiliar.siguiente;
					}
					//si no esta por dentro entonces quiere decir que el valor es mayor que todos entonces 
					//reutilizo la funcions de agregar al final
					agregarFinal(nodo.getDato());
					return;
					
				}
			}
		}
		
		//funcion para eliminar la cabeza en la lista
		public void eliminarcabeza() {
			//compruebo que este vacia
			if(estaVacia()) {
				System.out.println("La lista esta vacia");
			}else {
				//para eliminar la cabeza hago que la cabeza apunte a su siguiente y asi la cabea queda libre
				inicio=inicio.siguiente;
			}
		}
		
		//creo la funcion para saber si la lista esta vacia
		public boolean estaVacia() {
			//que lea la variable inicio si contiene informacion
			if(inicio==null) {
				//si esta con datos me devuelve el valor de verdadero
				return true;
			}
			//caso contrario me debe devoler false
			return false;
		}
		
		//funcion para poder recorrer la lista y mostrar los valores
		public void verLista() {
			//creo un objeto auxiliar para poder alli almacenar los valores que contenga el nodo
			auxiliar = inicio;
			//primero compruebo si la lista esta vacia
			if(estaVacia()) {
				System.out.println("La lista esta vacia");
			}else {
				//si en el caso de estar llena le empiezo a recorrer el objeto que cree arriba
				while(auxiliar!=null) {
					//empiezo a desglozar los datos
					System.out.print("["+auxiliar.getDato()+"]"+"->");
					auxiliar = auxiliar.getSiguiente();
				}
				//si ya termina de correr la lista que me muestre el valor de null
				System.out.print("null");
			}
		}
}
