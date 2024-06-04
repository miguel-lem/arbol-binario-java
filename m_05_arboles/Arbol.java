package m_05_arboles;



public class Arbol {
	//creo las variables de tipo nodo para poder pasarle el valor
	Nodo tronco;
	Nodo auxiliar1;
	int cant;
	//creo el constructor y le inicializo las variables en null ya que por defecto al principio estan vacias
	public Arbol() {
		this.tronco=null;
		this.auxiliar1=null;
	}
	
	//-------------------------------------------------------------------------------------------------
	//funcion para insertar los datos en la cabeza
	public void insertar(int dato) {
		//primero creo el nuevo nodo que viene el otro lado
		Nodo nodo = new Nodo(dato);
		if(tronco==null) {
			//si el tronco esta vacio le cargo alli
			tronco= nodo;
		}else {
			//analizo
			if(dato==existeelemento(dato)) {
				System.out.println("no esta permitido ingresar datos duplicados");
			}else {
				//sino con un auxiliar capturo la cabeza para poder recorrer las posiciones
				Nodo auxiliar = tronco;
				//mientras el auxiliar no sea nulo el seguira ejecutando
				while (auxiliar!=null) {
					//cambio de posicion subo un nivel
					nodo.padre = auxiliar;
					//comparo si el dato es mayor que esa nuevo tronco
					if(nodo.getDato()>=auxiliar.getDato()) {
						//si es mayor debe inclinarse a la derecha
						auxiliar=auxiliar.derecho;
					}else {
						//sino quiere decir que es menor y le debo inlinar hacia la izquierda
						auxiliar=auxiliar.izquierdo;
					}
				}
				//cuando ya encuentre un nulo sale de alli y comparo los datos
				if(nodo.getDato()<nodo.padre.getDato()) {
					//si el dato traido es menor, entondes le coloco en el lado izquiero
					nodo.padre.izquierdo = nodo;
				}else {
					//pero si es meyor a al tronco con el que salio se coloca a lado derecho
					nodo.padre.derecho=nodo;
				}
			}
		}
	}
	//------------------------------------------------------------------------------------------------------
	
	//funcion para medir la altura del arbol
	int altura;//creo la variable para ir acumulando
	public void altura(Nodo arbol,int nivel) {
		if(arbol!=null) {//puedo recorrer el arbol para ir contndo segun nivel
			altura(arbol.izquierdo, nivel+1);//le regreso a si mismo con el puntero isquierdo y sumo uno
			if(nivel>altura) {//comparo el valor si el nivel es mayor es porque ya giro una vez al menos
				altura=nivel;//de serlo refreso la variable altura con ese valor
			}
			//tambien debo recorrer el lado derecho y sumarle el nivel
			altura(arbol.derecho, nivel+1);
		}
	}
	
	//funcion para llamar a nivel
	public int llamadaaltura() {
		//la altrua de igualo a cero porque en si es el contador que acumulara la cantidad de niveles
		altura=0;
		//llamo a la funcion, le paso el tronco y como valor el 1 porque la raiz estara en la posicion 1
		altura(tronco,1);
		//ahora le retorno el valor y eso puedo mostrar mas alla 
		return altura;
	}

	//----------------------------------------------------------------------------------------------------
	
	//funcion para medir altura de lado derecho
	public int llamadaalturaderecho() {
		altura = 0;
		//lo que cambia aqui es que le paso la que va por lado izquierdo
		altura(tronco.derecho,1);
		return altura;
	}
	//funcion para medir altura de lado izquierdo
	public int llamadaalturaizquierdo() {
		altura = 0;
		//lo que cambia en este caso es que le indico que se vaya por el lado derecho del tronco
		altura(tronco.izquierdo,1);
		return altura;
	}
	
	
	//----------------------------------------------------------------------------------------------------
	//recorrer por profundidad el arbol
	public void recorrerprofundidad(Nodo arbol) {
		if(arbol==null) {
			System.out.println("lista esta vacia");
		}else {
			//primero creamos la cola porque en si vamos ir agregando a la cola
			Lista lista = new Lista();
			//le pasamos el primer elemento a la funcion de encole
			encolar(lista, arbol);
			//creo el dato para capturar en si en cada vuelta la cabeza de la lista
			int dato = 0;
			//el auxiliar es para cpaturar el nodo de ese elemento y poder almacenar la informacion
			Nodo auxiliar=null;
			//luego recorremos el arbol a preguntar si hay datos en la lista los imprimo
			while(lista.inicio!=null) {
				//capturo el valor primero de la lista
				dato=lista.inicio.getDato();
				//aqui capturo el nodo equivalente al numero de la lista antes de eliminarle de ella
				auxiliar= existenodo(dato);
				//luego borro de la lista ese numero porque quiero que continue al siguiente nodo de la lista en la proxima vuelta
				lista.eliminarcabeza();
				//le imprimo el valor de la lista
				System.out.print(" "+dato+" ");
				//como estoy seguro que nodo sera el que necesito guardo en la lista los hijos de ambos lados
				encolar(lista, auxiliar.izquierdo);
				encolar(lista, auxiliar.derecho);
			}
		}
	}
	
	public void encolar(Lista lista,Nodo arbol) {
		//consulta si el dato que me envio es diferente de nulo entonces le agrega
		if(arbol!=null) {
			//utilizo la funcion de colocar al final porque asi me aseguro al momento de eliminar ri recorriendo de arriba abajo
			//el arbol
			lista.agregarFinal(arbol.getDato());
		}
	}
	
	//llamada a funcion de ver en profundidad
	public void llamadaprofundidad() {
		if(tronco==null) {
			System.out.println("el arbol esta vacio no se puede recorrer en profundidad");
		}else {
			recorrerprofundidad(tronco);
		}
	}
	//--------------------------------------------------------------------------------------------
	
	//funcion alterna para leer en anchura el arbol
	public void recorreranchura(Nodo arbol,Lista lista) {
		if(arbol==null) {
			return;
		}else {
			//cargo los datos en la lista
			lista.insertarordenado(arbol.getDato());
			//de forma recursiva recorro los dos lados para ir capturando los datos
			//ya que esta es en si la impresion de preorden y la estoy aprovechando a la vez
			recorreranchura(arbol.izquierdo, lista);
			recorreranchura(arbol.derecho, lista);
		}
	}
	
	//funcion para llamar al metodo donde se colocaran los datos para leeerlos en anchura
	public void llamadaanchura() {
		//creo la lista porque le voy a pasar una variable asi y donde se ordenaran los datos
		Lista lista = new Lista();
		//llamo a la funcion y le paso sus parametros, el arbol y la lista
		recorreranchura(tronco, lista);
		//imprimo la lista para ver los datos ordenados de la lectura en orizontal
		lista.verLista();
	}
	
	//-----------------------------------------------------------------------------------
	//funcion para buscar un dato
	public int existeelemento(int dato) {
		//cargo los datos de arbol en un auxiliar
		Nodo auxiliar1 = tronco;
		//si esta vacio el arbol me retorna 0
		if(auxiliar1==null) {
			return 0;
		}else {
			//recorro el auxilair
			while(auxiliar1!=null) {
				//si el numero ingresado es menor que el tronco en este caso ingresara 
				if(dato<auxiliar1.getDato()) {
					//si al comparar el dato coinciden lo devolvera el mismo numero
					if(auxiliar1.getDato()==dato)
						return auxiliar1.getDato();
					auxiliar1=auxiliar1.izquierdo;
				}else {
					//si esta en el lado derecho y al comparar coinciden lo devolvera
					if(auxiliar1.getDato()==dato)
						return auxiliar1.getDato();
					auxiliar1=auxiliar1.derecho;
				}
			}
			//si no encuentra parecido me retorna 0
			return 0;
		}
	}
	//-----------------------------------------------------------------------------------------
	
	//funcion para buscar un dato
		public Nodo existenodo(int dato) {
			//cargo los datos de arbol en un auxiliar
			Nodo auxiliar1 = tronco;
			//si esta vacio el arbol me retorna 0
			if(auxiliar1==null) {
				return null;
			}else {
				//recorro el auxilair
				while(auxiliar1!=null) {
					//si el numero ingresado es menor que el tronco en este caso ingresara 
					if(dato<auxiliar1.getDato()) {
						//si al comparar el dato coinciden lo devolvera el mismo numero
						if(auxiliar1.getDato()==dato)
							return auxiliar1;
						auxiliar1=auxiliar1.izquierdo;
					}else {
						//si esta en el lado derecho y al comparar coinciden lo devolvera
						if(auxiliar1.getDato()==dato)
							return auxiliar1;
						auxiliar1=auxiliar1.derecho;
					}
				}
				//si no encuentra parecido me retorna 0
				return null;
			}
		}
	//---------------------------------------------------------------------------------------------------
		
	//funcion para eliminar un nodo del arbol 
	public void eliminar(int dato) {
		//cargo los datos en un auxiliar
		Nodo auxiliar1=tronco;
		//compruebo que el arbol no este vacio
		if(tronco==null) {
			System.out.println("la lista esta vacia no hay algo para eliminar");
		}else {
			//compruebo que el dato a buscar si exista dentro del nodo
			if(existeelemento(dato)>0) {
				//compruebo en caso de que sea el nodo a eliminar el tronco principal 
				if(dato==tronco.getDato()) {
					//utilizo la funcion de eliminar elemento y le paso el auxiliar y el tronco que en el codigo esta mas abajo
					eliminarelemento(auxiliar1,tronco);
				}else {
					//si no es el tronco principal entonces debo empezar a recorrer el arbol en busca
					while (auxiliar1!=null) {
						//compruebo si el dayo es mayor que el auxiliar en este caso el tronco al ser la primera vuelta
						if(dato>auxiliar1.getDato()) {
							//de serlo voy por el lado derecho, de todas maneras compruebo si el valor de nodo es igual al dato
							if(auxiliar1.getDato()==dato) {
								//de serlo le llamo a la funcion de eliminar y le paso el auxiliar y el padre de ese auxiliar
								eliminarelemento(auxiliar1,auxiliar1.padre);
								return;
							}
							//si no es igual sigo rotando una posicion a la derecha y vuelvo al while a comprobar si es diferente de null
							auxiliar1=auxiliar1.derecho;
						}else {//si no es mayor entonces quiere decir que esta al lado izquierdo asi que le recorro hacia ese lado
							if(auxiliar1.getDato()==dato) {//compruebo el nodo con el dato si son entreso iguales
								//de serlo le llamo a la funcion eliminar y le paso el auxiliar y su padre, mas abajo entienden porque necesito al padre
								eliminarelemento(auxiliar1,auxiliar1.padre);
								return;
							}
							//si no cumple aun le vuelvo a correr con un espacio mas hacia la izquiera y compruebo con el while
							auxiliar1=auxiliar1.izquierdo;
						}
					}
				}
			}
			//si el dato no le encuentra le advierto al usuario con un mensaje
			System.out.println("No existe ese numero dentro de este arbol");
		}
	}
	
	//funcion para controlar el eliminado dependiendo de donde estuvo
	public void eliminarelemento(Nodo auxiliar1,Nodo padre) {
		//aqui en esta funcion le pido el nodo a eliminar y el padre del nodo a eliminar porque quiero ver si el nodo a eliminar es, hoja, padre
		//de un solo hijo o es padre de dos hijos
		//1.-) compruebo si el nodo no tiene hijos
		if(auxiliar1.derecho==null && auxiliar1.izquierdo==null) {
			//si no tiene entonces debo ver si es hijo izquierdo del padre
			if(padre.izquierdo==auxiliar1) { 
				//entonces le digo al padre que en su izquierdo deje de apuntar a ese nodo y cambie por null
				padre.izquierdo=null;
			}else {
				//puede ser que el padre en su hijo derecho tenga al nodo
				if(padre.derecho == auxiliar1) {
					//le digo al padre que en se lado apunte a null y deje al otro libre
					padre.derecho=null;
				}
			}
		}else {//2.-) si no cumple con las codiciones de ser una hoja entonces quiere decir que es un padre
			//si es un padre con un hijo a la derecha
			if(auxiliar1.izquierdo==null) {
				//si su dato es menor que el dato del padre
				if(auxiliar1.getDato()<padre.getDato())
					//debo hacer que el padre puntero izquierdo apunte al derecho de su hijo es decir del nodo que quiero eliminar
					padre.izquierdo=auxiliar1.derecho;
				else
					//pero puede ser que sea mayor que el padre entonces el padre puntero derecho apunte al derecho del nodo a eliminar
					padre.derecho=auxiliar1.derecho;
			}else {
				//puede ser que este es un padre con hijo a la izquierda
				if(auxiliar1.derecho==null) {
					//compruebo si el dato del nodo a eliminar en menor que el padre
					if(auxiliar1.getDato()<padre.getDato())
						//de serlo el padre debe con si izquierdo apuntar al isquierdo del nodo a eliminar
						padre.izquierdo=auxiliar1.izquierdo;
					else 
						//si el nodo a elimiar es mayo entonces el padre con su derecho apunta el izquierdo del nodo a eliminar
						padre.derecho=auxiliar1.izquierdo;
				}else {//3.-) puede ser el caso de que este padre tiene dos hijos y alli la situacion cambia
					//primero capturo el dato del nodo que quiero colocar en esa posicion con la funcion que esta mas abajo igual
					//a esta funcion le envio el nodo que quiero eliminar
					Nodo reemplazo = obtenerReemplazo(auxiliar1);
					//si el dato a eliminar es igual al tronco entonces lo reemplazo alli con el nuevo reemplazo que vino
					if(auxiliar1==tronco) tronco=reemplazo;
					else {
						//si el dato a eliminar a sido a lado izquierdo del padre del auxiliar
						if(auxiliar1.getDato()==padre.izquierdo.getDato()) {
							//entonces al lado izquiero del padre coloco todo el nuevo reemplazo
				                padre.izquierdo = reemplazo;
			            }else {
			            	//pero si el lado derecho del padre cumple con la igualdad del dato
			            	if(auxiliar1.getDato()==padre.derecho.getDato()) {
			            		//entonces todo el lado derecho del padre lo reemplazo con el nodo que vino
			            		padre.derecho = reemplazo;
			            	}
						}
					}
					//luego de eso le agrego el primer valor del auxiliar izquierdo porque se pierde cuando ejecuto la funcion de mas abajo
		            reemplazo.izquierdo = auxiliar1.izquierdo;
				}
			}
		}
	}
	
	
	//funcion para obtener el nodo que se va a reemplazar
	 public Nodo obtenerReemplazo(Nodo nodoReemplazo) {
		 //cargo el nodo nuevo con el que quiero eliminar porque lo quiero conservar una posicion por detras
         Nodo  auxiliar01= nodoReemplazo;
         //en otro nodo igual pero este ira cambiando al rotarlo con el while e ir una posicion por delante
         Nodo  auxiliar02 = nodoReemplazo;
         //en el aux le coloco una posicion mas adelante hacia la derecha porque igual recorrere luego una posicion mas adeltante que el anterior
         Nodo auxiliar03 = nodoReemplazo.derecho;
         //recorro el nodo
         while (auxiliar03 != null) {
        	 //corre una posicion detras
        	 auxiliar01 = auxiliar02;
             //corre una posicion adelante de la linea anterior
        	 auxiliar02 = auxiliar03;
             //este se adelante a la linea anterior en una posicion
        	 auxiliar03 = auxiliar03.izquierdo;
         }
         //si el reemplazo es diferente de nodo que vino en su posicion derecha
         if (auxiliar02 != nodoReemplazo.derecho) {
        	 //entonces el auxiliar en su izquierdo se le colara lo que tiene el reemplazo en su derecha
        	 auxiliar01.izquierdo = auxiliar02.derecho;
             //y a este a su vez en su lado derecho se le colara todo lo que traia el nodo a eliminar en su lado derecho
        	 auxiliar02.derecho = nodoReemplazo.derecho;
         }
         //luego regreso al nodo que debo colocar en la posicion del nodo que quiero eliminar
         return auxiliar02;
    }
	//----------------------------------------------------------------------------------------------------------------------
	 
	 
	//funcion para saber el tamanio
	public int cantNodos () {
	        cant = 0;
	        cantNodos(tronco);
	        return cant;
	}
	
	private void cantNodos (Nodo nodo) {
	        if (nodo != null) {
	        	//es similar a la funcion de impresion en preorden, lo que le diferencia es que voy sumando segun va girando
	            cant++;
	            cantNodos(nodo.izquierdo);
	            cantNodos(nodo.derecho);
	        }
	}
	
	//-----------------------------------------------------------------------------------------------------------------
	
	//llamada a funcion de balanceado del arbol
	public void llamadabalance() {
		balancear(tronco);
	}
	//funcion para balancear el arbol
	public void balancear(Nodo arbol) {
		//primero debo medir las alturas
		int derecha = llamadaalturaderecho();
		int izquierda = llamadaalturaizquierdo();
		//le resto el lado derecho menos el izquierdo
		int resultado = derecha-izquierda;
		//si el resultado esta 1 o bien -1 quiere decir que hay un equilibrio de acuerdo a la regla de los arboles y su equilibrio
		if(resultado == 1 || resultado == -1) {
			System.out.println("el arbol esta balanceado");
		}else {		
			//si es mayor que 1 quiere decir que esta el peso hacia el lado derecho y le aplico la rotacion al contrario
			if(resultado>1) {
				rotacionizquierda(arbol);
			}
			else {
				//si es menor que -1 quiere decir que debo rotarle a la derecha porque el peso esta mas en el lado izquierdo
				if(resultado<-1) {
					rotacionderecha(arbol);
				}
				
			}
		}
	}
	
	//rotar arbol a la izquierda
	protected void rotacionizquierda(Nodo nodo) {
		//la parte derecha y todas sus secuencias le capturo en un nodo auxiliar1
		Nodo auxiliar1 = nodo.derecho;
        //el auxiliar1. padre le asigno el padre del nodo si estamos girando la primera cabeza eso vale null sino debe llevar algun valor
        auxiliar1.padre = nodo.padre;
        //lo que aya en el auxiliar1 a su lado derecho le asigno al nodo. derecho, recordemos que este ya se vacio arriba su lado derecho
        nodo.derecho = auxiliar1.izquierdo;
        if (nodo.derecho != null) {
        	//si tiene un valor derecho que le aya entregado el aux1.izq dentra aqui
        	//y le digo que se comvierta en el padre del nodo separado del lado izquierdo mas arriba
            nodo.derecho.padre = nodo;
            //es decir como el nodo quedo con su cabeza y su lado izquierdo, pero su lado derecho se vacio,
            //entonces sus lado izquierdo le dejo entregando lo que le quite al aux1 en su lado derecho
            //es decir se crea un mini arbol aqui
        }
        //luego de esto como al nodo derecho le encargamos valores le decimos que este le entregue al auxiliar1 es su lado izquierdo
        auxiliar1.izquierdo = nodo;
        //y le decimos a ese nodo que su padre nuevo es el que nodo auxiliar1
        nodo.padre = auxiliar1;

        //este es en caso de que el auxiliar1 tenga un padre
        if (auxiliar1.padre != null) {//este caso en si se esta trabajando con un nodo mas abajo del tronco, es decir un mini arbol que le voy operar
        	//le reviso que si el nodo que le pase en igual al auxiliar1.padre decendiendo por lado izquierdo tuvo un padre
            if (nodo == auxiliar1.padre.izquierdo) {
            	//entonces le asigno ese auxiliar en su lado izquiero y que ese de alli le reconozca como padre al que ya venia por defecto alli
                auxiliar1.padre.izquierdo = auxiliar1;
            } else {
            	//sino le digo que por el lado derecho haga ese trabajo si el numero es descendiente por el lado izquierdo
                auxiliar1.padre.derecho = auxiliar1;
            }
        } else {
        	//luego le asignamos todo el nuevo arbol al tronco para que se guarde
            tronco = auxiliar1;
        }
    }
	
	//rotacion a la derecha
    protected void rotacionderecha(Nodo nodo) {
    	//capturo el trozo de informacion que tiene el arbol en su lado derecho
        Nodo auxiliar1 = nodo.izquierdo;
        //el auxiliar1 en su padre le digo que guarde lo que venga como padre de lado del arbol
        auxiliar1.padre = nodo.padre;
        //si hay alguna informacion en el lado derecho le cargo en el apuntador que quedo vacio
        nodo.izquierdo = auxiliar1.derecho;
        if (nodo.izquierdo != null) {
        	//si hay algun dato a este le digo que en su padre por el lado izquierdo es el nodo que habia quedado con el tronco y el lado derecho
        	//recordemos que el lado vacio del izquierdo le agregue el lado derecho del auxilia1
            nodo.izquierdo.padre = nodo;
        }
        //luego de salir del if en caso de entrar estos datos le guardo en el lado derecho del auxiliar1
        auxiliar1.derecho = nodo;
        //a continuacion le digo que su nuevo padre es el auxiliar1
        nodo.padre = auxiliar1;

        //esta parte de aqui es si se esta operando con un subarbol del arbol grande
        if (auxiliar1.padre != null) {
            if (nodo == auxiliar1.padre.izquierdo) {
            	//si tiene un padre por el lado izquierdo entonces le digo que el auxiliar1 es su padre
            	auxiliar1.padre.izquierdo = auxiliar1;
            } else {
            	//esto es en caso que este por el lado derecho
            	auxiliar1.padre.derecho = auxiliar1;
            }
        } else {
        	//luego guardo toda la informacion en el tronco principal
            tronco = auxiliar1;
        }
    }
 
	//-------------------------------------------------------------------------------------------------------------------
	//funcion para leer los datos en preorden
	public void preorden(Nodo arbol) {
		if(arbol==null) {
			return;
		}else {
			System.out.print(arbol.getDato()+" ");
			preorden(arbol.izquierdo);
			preorden(arbol.derecho);
		}
	}
	
	//funcion para que se ejecute el llamado al preorden
	public void preordenarranque() {
		preorden(tronco);
	}
	
	//---------------------------------------------------------------------------------------------------------------
	//funcion para leer los datos en inorden
	public void inorden(Nodo arbol) {
		if(arbol==null) {
			return;
		}else {
			preorden(arbol.izquierdo);
			System.out.print(arbol.getDato()+" ");
			preorden(arbol.derecho);
		}
	}
		
	//funcion para que se ejecute el llamado al inorden
	public void inordenarranque() {
		inorden(tronco);
	}
	//---------------------------------------------------------------------------------------------------------
	
	//funcion para leer los datos en postorden
		public void postorden(Nodo arbol) {
			if(arbol==null) {
				return;
			}else {
				preorden(arbol.izquierdo);
				preorden(arbol.derecho);
				System.out.print(arbol.getDato()+" ");
			}
		}
			
		//funcion para que se ejecute el llamado al preorden
		public void postordenarranque() {
			postorden(tronco);
		}
	//----------------------------------------------------------------------------------------------------------
}
