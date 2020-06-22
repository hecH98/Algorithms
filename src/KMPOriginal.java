
public class KMPOriginal {
	private String text, pattern;
	private int[][] tabla;
	
	public KMPOriginal(String text, String pattern) {
		this.text = text;
		this.pattern = pattern;
		this.tabla = new int[pattern.length()+1][2];
		llenarTabla();
		encontrar();
	}
	
	private void llenarTabla() {
		String[] tablaString = new String[this.pattern.length()+1];
		int contador = 0;
		
		for (int i = 0; i < this.tabla.length; i++) {
			tablaString[i]=this.pattern.substring(0,i); // agrego a tablaString en la posicion i, el length de 0 a i de pattern
			this.tabla[i][0]=i; // agrego a la tabla de saltos el length de la palabra resultante de tablaString[i]
			int fin = tablaString[i].length(); // recorro el string de tablaString[i] hasta la mitad
			for (int j = 0; j < tablaString[i].length()/2; j++) {
				if(tablaString[i].substring(0,j+1).equals(tablaString[i].substring(fin-j-1,fin)) ) { // checa los primeros n caracteres y los ultimos n caracteres
					contador=j+1;
				}
			}
			this.tabla[i][1]=contador;
			contador=0;
		}
	
		for (int i = 0; i < tablaString.length; i++) {
			System.out.println(tablaString[i]);
		}
		for (int i = 0; i < this.tabla.length; i++) {
			for (int j = 0; j < this.tabla[i].length; j++) {
				System.out.print(this.tabla[i][j]+",");
			}
			System.out.println();
		}
	}
	
	private void encontrar() {
		int cuentaPattern=0;
		boolean bandera = true;
		for (int i = 0; i < this.text.length(); i++) {
			if(this.text.charAt(i) == this.pattern.charAt(cuentaPattern)) {
				cuentaPattern++;
				if(cuentaPattern == this.pattern.length()) {
					System.out.println("si esta en el char " + (i - this.pattern.length()));
					cuentaPattern=0;
					bandera = false;
				}
			}
			else {
				cuentaPattern = this.tabla[cuentaPattern][1];
				i--;
			}
		}
		if(bandera) {
			System.out.println("no");
		}
		
	}

	
	public static void main(String[] args) {
		String text = "abcabdabcabdabpaabcabdabp";
		String pattern = "abcabdabp";
		new KMPOriginal(text, pattern);
	}
}
