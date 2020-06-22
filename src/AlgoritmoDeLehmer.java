import java.lang.Math;
import java.util.Calendar;

// Hector Humberto Herrera Macias A01632115

public class AlgoritmoDeLehmer {
	static int z0 = 1;
	
	public static void siguienteValor(int a, int m) {
		int valorInicial=z0;
		double normalizado;
		do {
			z0 = (a*z0)%m;
			System.out.print(z0 + ", ");
			normalizado = normalizar(m, z0);
			System.out.println("valor normalizado: " + normalizado);
//			x++;
		} while(z0 != valorInicial);//z0 != 1);
		System.out.println();
	}
	
//	public static float random10() {
//		Calendar cal = Calendar.getInstance();
//		int a = cal.get(Calendar.SECOND);
//		
//		
//		return 0.00;
//	}
	
	public static double normalizar(int m, int res) {
		double calculo = (double) res/ (double) (m-1);
		return (double)Math.round(calculo * 100d) / 100d; // redondenado a 2 decimales
	}
	
	public static void main(String[] args) {
		siguienteValor(11,13);
//		calcular(16807,2147483647);
	}
}
