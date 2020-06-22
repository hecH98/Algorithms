import java.util.NoSuchElementException;

//Hector Humberto Herrera Macías A01632115
// LinkedList.java
// 24/04/19

public class MiListaEnlazada<E>{
	public NodoLE<E> inicio,
					  fin;
	private int size;
	
	public MiListaEnlazada(){
		this.inicio=this.fin=null;
		this.size=0;
	}
	
	public MiListaEnlazada(E[] datos) {
		//Inicializa la lista con los elementos que contiene el arreglo
		for (int i = 0; i < datos.length; i++) {
			insertarInicio(datos[i]);
		}
	}
	
	public int size() {
		return this.size;
	}
	
	public E inicio() {
		try {
			return this.inicio.getDato();
		} catch (NullPointerException e) {
			throw new NoSuchElementException("No se puede regresar el primer elemento de una lista vacia");
		}
		
	}
	
	public NodoLE<E> nodoInicio() {
		return this.inicio;
	}
	
	public E fin() {
		try {
			return this.fin.getDato();
		} catch (NullPointerException e) {
			throw new NoSuchElementException("No se puede regresar el ultimo elemento de una lista vacia");
		}
		
	}
	
	public boolean estaVacia() {
		return this.size==0;
	}
	
	public void insertarInicio(E dato) {
		NodoLE<E> nuevo=new NodoLE<>(dato, this.inicio);
		this.inicio=nuevo;
		if(this.size==0) {
			this.fin=nuevo;
		}
		size++;
	}
	
	public void insertarFin(E dato) {
		if(this.estaVacia()) {
			this.insertarInicio(dato);
		}
		else {
			NodoLE<E> nuevo=new NodoLE<>(dato);
			this.fin.setNext(nuevo);
			this.fin=nuevo;
			size++;
		}
	}
	
	public void insertarEn(E dato, int pos) throws IndexOutOfBoundsException{
		//Inserta el dato en la posició especificada. La primer posición se considera la 0. Debe arrojar una
		//exception si la posición especificada es < 0 o > al size.
		if(pos<0 || pos>size) {
			 throw new IndexOutOfBoundsException("La posicion no es valida");
		}
		else if (pos==0) {
			insertarInicio(dato);
		}
		else if (pos==size) {
			insertarFin(dato);
		}
		else {
			NodoLE<E> nuevo=new NodoLE<>(dato);
			NodoLE<E> aInsertar=this.inicio;
			for (int i = 0; i < pos-1; i++) {
				aInsertar=aInsertar.getNext();
			}
			nuevo.setNext(aInsertar.getNext());
			aInsertar.setNext(nuevo);
			size++;
		}
	}
	
	public E borrarInicio() {
		try {
			E res=this.inicio();
			this.inicio=this.inicio.getNext();
			size--;
			if(size==0) {
				this.fin=null;
			}
			return res;
		}catch(NoSuchElementException e) {
			throw new IndexOutOfBoundsException("No se puede borrar el primer elemento de una lista vacia");
		}
		
	}
	
	public E borrarFin() {
		if(size>1) {
			NodoLE<E> curr=this.inicio;
			E res=this.inicio();
			for (int i = 0; i < size-2; i++) {
				curr=curr.getNext();
			}
			curr.setNext(null);
			this.fin=curr;
			size--;
			return res;
		}
		else {
			try {
				return this.borrarInicio();
			}
			catch(IndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException("No se puede borrar el ultimo elemento de una lista vacia");
			}
		}
	}
	
	public E borrarNodo(NodoLE<E> nodo) {
		E dato= nodo.getDato();
		
		nodo.setDato(nodo.getNext().getDato());
		nodo.setNext(nodo.getNext().getNext());
		
		return dato;
	}
	
	public NodoLE<E> getPrev(NodoLE<E> curr){
		NodoLE<E> prev=this.inicio;
		while(prev.getNext()!=curr) {
			prev=prev.getNext();
		}
		return prev;
	}
	
	public void flush() {
		this.inicio=this.fin=null;
		this.size=0;
		System.gc();
	}
	
	public E borrarEn(int pos) throws IndexOutOfBoundsException{
		NodoLE<E> curr=this.inicio;
		for (int i = 0; i < pos; i++) {
			curr=curr.getNext();
		}
		E res=curr.getNext().getDato();
		curr.setNext(curr.getNext().getNext());
		
		return res;
	}
	
	public NodoLE<E> getNode() {
		NodoLE<E> curr=this.inicio;
		return curr.getNext().getNext().getNext();
	}
	
	public NodoLE<E> getNodoEn(int pos){
		NodoLE<E> curr=this.inicio;
		for (int i = 0; i < pos; i++) {
			curr=curr.getNext();
		}
		
		return curr;
	}
	
	public E getEn(int pos) {
		if(pos<0 || pos>size) {
			throw new IndexOutOfBoundsException("No se puede regresar el elemento en la posicion "+pos+" en una lista de tamaño "+this.size);
		}
		else {
			NodoLE<E> curr=this.inicio;
			for (int i = 0; i < pos; i++) {
				curr=curr.getNext();
			}
			return curr.getDato();
		}
		
	}
	
	public void setEn(E dato, int pos) {
		if(pos<0 || pos>size) {
			throw new IndexOutOfBoundsException("No se puede regresar el elemento en la posicion "+pos+" en una lista de tamaño "+this.size);
		}
		else {
			NodoLE<E> curr=this.inicio;
			for (int i = 0; i < pos; i++) {
				curr=curr.getNext();
			}
			curr.setDato(dato);
		}
	}
	
	public String toString() {
		String res="";
		NodoLE<E> current=this.inicio;
		while(current.getNext()!=null) {
			res+=current.getDato()+",";
			current=current.getNext();
		}
		res+=current.getDato();
		return res;
	}

	public static void main(String[] args) {
		Integer[] arr= {10,50,18,498,1,4,23,78,43};
		
		MiListaEnlazada<Integer> list=new MiListaEnlazada<>(arr);
		
		System.out.println(list);
		System.out.println(list.getNode());
		System.out.println(list.getPrev(list.getNode()));
		list.borrarNodo(list.getPrev(list.getNode()));
		
		System.out.println(list);
//		
//		MiListaEnlazada<Integer> lista1=new MiListaEnlazada<>();
//		for (int i = 0; i < 9; i++) {
//			lista1.insertarInicio(i);
//		}
//		System.out.println(lista1);
//		
//		lista1.insertarFin(2);
//		
//		System.out.println(lista1);
//		
//		lista1.insertarEn(23, 1);
//		
//		System.out.println(lista1);
//		
//		lista1.borrarInicio();
//		
//		System.out.println(lista1);
//		
//		lista1.borrarFin();
//		
//		System.out.println(lista1);

	}

}

class NodoLE<E>{
	private E dato;
	private NodoLE<E> next;
	
	public NodoLE(E dato) {
		this(dato,null);
	}
	
	public NodoLE(E dato, NodoLE<E> next) {
		this.dato = dato;
		this.next = next;
	}
	public E getDato() {
		return dato;
	}
	public void setDato(E dato) {
		this.dato = dato;
	}
	public NodoLE<E> getNext() {
		return next;
	}
	public void setNext(NodoLE<E> next) {
		this.next = next;
	}
	
	public String toString() {
		return this.dato+"";
	}
	
	
}
