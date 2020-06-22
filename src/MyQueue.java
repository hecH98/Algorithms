import java.util.NoSuchElementException;

public class MyQueue <E>{
	private MiListaEnlazada<E> lista;
	
	public MyQueue() {
		this.lista=new MiListaEnlazada<>();
	}
	
	public MyQueue(MiListaEnlazada<E> lista) {
		this.lista=new MiListaEnlazada<>();
		NodoLE<E> nodo = lista.inicio;
		System.out.println(nodo.getDato());
		while(!lista.estaVacia()) {
			this.lista.insertarInicio(nodo.getDato());
			nodo = nodo.getNext();
		}
	}
	
	public void enqueue(E dato) {
		lista.insertarFin(dato);
	}
	
	public E dequeue() {
		try {
			return this.lista.borrarInicio();
		} 
		catch(IndexOutOfBoundsException e) {
			throw new NoSuchElementException("No se puede hacer un dequeue en una cola vacia");
		}
		
	}
	
	public E next() {
		try {
			return this.lista.inicio();
		} 
		catch(IndexOutOfBoundsException e) {
			throw new NoSuchElementException("No se puede ver el siguiente elemento en una cola vacia");
		}
	}
	
	public int size() {
		return this.lista.size();
	}
	
	public boolean isEmpty() {
		return this.lista.estaVacia();
	}
	
	public void flush() {
		this.lista.flush();
	}
	
	public String toString() {
		return this.lista.toString();
	}
	
	public static void main(String[] args) {
		MyQueue<String> cola=new MyQueue<>();
		cola.enqueue("Hola ");
		cola.enqueue("que ");
		cola.enqueue("tal ");
		cola.enqueue("veamos ");
		cola.enqueue("si ");
		cola.enqueue("funciona ");
		cola.enqueue("esto ");
		cola.enqueue("no?");
		
		System.out.println(cola);
		
		System.out.println(cola.next());
		
		while(!cola.isEmpty()) {
			System.out.print(cola.dequeue());
		}
	}
}
