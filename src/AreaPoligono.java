import java.awt.Point;

public class AreaPoligono {
	private Point[] puntos;
	
	public AreaPoligono(Point[] puntos) {
		this.puntos = puntos;
	}
	
	public float doAlgorithm() {
		float res = 0;
		
		for(int i = 0; i < this.puntos.length; i++) {
			res += (puntos[i].x*puntos[(i+1)%this.puntos.length].y)-(puntos[i].y*puntos[(i+1)%this.puntos.length].x);
		}
		return res /= 2;
	}
	
	public static void main(String[] args) {
		Point[] puntos = {new Point(0,0),new Point(4,0),new Point(1,1),new Point(0,3)};
		
		AreaPoligono ap = new AreaPoligono(puntos);
		System.out.println(ap.doAlgorithm());
	}

}
