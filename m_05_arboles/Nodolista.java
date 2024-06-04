package m_05_arboles;

public class Nodolista {
	private int dato;
	public Nodolista siguiente;
	
	public Nodolista(int dato, Nodolista siguiente) {
		this.dato=dato;
		this.siguiente = siguiente;
	}

	public int getDato() {
		return dato;
	}

	public void setDato(int dato) {
		this.dato = dato;
	}

	public Nodolista getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(Nodolista siguiente) {
		this.siguiente = siguiente;
	}
	
}
