import java.util.Random;

public class Gasolinera {
	
	private Thread oxxo,
				   seven, 
				   pemex, 
				   huachicol,
				   despachador;
	
	private Object lock = new Object();
	
	private Random ran = new Random();
	
	private int gasDisponible, gasGenerada;
	
	public Gasolinera() {
		race();
	}
	
	public void tomarGasolina() {
		synchronized(lock) {
			gasDisponible--;
			System.out.println("se tomo un litro, total: "+gasDisponible);
		}
	}
	
	public void race() {
		
		despachador = new Thread() {
			@Override
			public void run() {
				try {
					while(true) {
						gasGenerada = ran.nextInt(5) + 5;
						gasDisponible+=gasGenerada;
						System.out.println("Pemex produce " + gasGenerada + " liters");
						System.out.println("Gas total: " + gasDisponible);
						despachador.sleep(3000);
					}
					
				} catch (Exception e) {
					System.out.println("Error en el sleep");
				}
			}
		};
		
		oxxo = new Thread(() -> {
			System.out.println("se inicio oxxo");
			for(int i=0; i<2;i++) {
				if(gasDisponible > 0) {
					tomarGasolina();
					System.out.println("Oxxo tomo gas");
				}
				else {
					break;
				}
			}
		});
		
		seven = new Thread(() -> {
			System.out.println("se inicio seven");
			for(int i=0; i<2;i++) {
				if(gasDisponible > 0) {
					tomarGasolina();
					System.out.println("seven tomo gas");
				}
				else {
					break;
				}
			}
		});
		
		pemex = new Thread(() -> {
			System.out.println("se inicio pemex");
			for(int i=0; i<2;i++) {
				if(gasDisponible > 0) {
					tomarGasolina();
					System.out.println("pemex tomo gas");
				}
				else {
					break;
				}
			}
		});
		
		huachicol = new Thread(() -> {
			System.out.println("se inicio huachicol");
			for(int i=0; i<3;i++) {
				if(gasDisponible > 0) {
					tomarGasolina();
					System.out.println("huachicol tomo gas");
				}
				else {
					break;
				}
			}
		});
		
		despachador.start();
		oxxo.start();
		seven.start();
		pemex.start();
		huachicol.start();
		
		
		try {
			oxxo.join();
			seven.join();
			pemex.join();
			huachicol.join();
//			despachador.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void main(String[] args) {
		new Gasolinera();
	}
}