package m_05_arboles;



public class Nodo {
	//creo primero las variables en si
	public int dato;
	//el nodo padre siempre sera el que este arriba y este sera la referencia
	public Nodo padre;
	//este sera el que ira colocado a lado izquierdo
	public Nodo izquierdo;
	//este ira colocado a lado derecho
	public Nodo derecho;
	
	//creo el consructor
	public Nodo(int dato) {
		//inicializo las variables
		this.dato=dato;
		this.padre=null;
		this.izquierdo=null;
		this.derecho=null;
	}

	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

}
