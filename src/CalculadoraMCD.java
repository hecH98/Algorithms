import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Hector Humberto Herrera Macias A01632115
@SuppressWarnings("serial")
public class CalculadoraMCD extends JFrame {
	private Panel panel;
	
	public CalculadoraMCD() {
		super("Calculadora MCD");
		this.panel = new Panel();
		this.add(this.panel);
		this.setBackground(Color.gray);
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new CalculadoraMCD();
	}
}

@SuppressWarnings({"serial","rawtypes","unchecked"})
class Panel extends JPanel{
	private TextField a1, b1, a2, b2, c1, c2;
	private JButton igual;
	private JComboBox operacion;
	
	public Panel() {
		super();
		this.setPreferredSize(new Dimension(800,400));
		this.setLayout(null);
		
		elementos();
	}
	
	public void calcularMCD() {
		int numA=Integer.parseInt(this.a1.getText());
		int denA;
		if(this.b1.getText().equals("")) {
			denA=1;
		}else{
			denA=Integer.parseInt(this.b1.getText());
		}
		
		int numB=Integer.parseInt(this.a2.getText());
		int denB;
		if(this.b2.getText().equals("")) {
			denB=1;
		}else {
			denB=Integer.parseInt(this.b2.getText());
		}
		if(denA == 0 || denB == 0) {
			System.out.println("Division entre 0!!");
			return;
		}
		
		int resNum, resDen;
		System.out.println("a: "+numA);
		System.out.println("a2: "+denA);
		System.out.println("b: "+numB);
		System.out.println("b2: "+denB);
		String x = (String) this.operacion.getSelectedItem();
		System.out.println("operacion: "+x);
		
		switch (x) {
		case "+":
			resDen = denA*denB;
			resNum = ((resDen/denA)*numA)+((resDen/denB)*numB);
			break;
		case "-":
			resDen = denA*denB;
			resNum = ((resDen/denA)*numA)-((resDen/denB)*numB);
			break;
		case "*":
			resNum = numA*numB;
			resDen = denA*denB;
			break;
		case "/":
			resNum = numA*denB;
			resDen = numB*denA;
			break;
		case "^":
			if(numB>=0) {
				resNum = (int) Math.pow(numA,numB);
				resDen = (int) Math.pow(denA, numB);
			}
			else {
				resNum = (int) Math.pow(denA,Math.abs(numB));
				resDen = (int) Math.pow(numA, Math.abs(numB));
			}
			break;

		default:
			resNum=0;
			resDen=1;
			break;
		}
		
		if(resNum != 0) {
			
			
		}
		int mcd=obtenerMCD(resNum, resDen);
		System.out.println("res: "+resNum);
		System.out.println("res2: "+resDen);
		System.out.println("mcd: "+mcd);
		System.out.println("res/mcd: "+resNum/mcd);
		System.out.println("res2/mcd: "+resDen/mcd);
		this.c1.setText(resNum/mcd+"");
		this.c2.setText(resDen/mcd+"");
		
	}

	public int obtenerMCD(int a, int b) {
		System.out.println("res: "+a+", aux: "+b);
		if(b == 0) {
			return a;
		}
		else {
			return obtenerMCD(b, a%b);
		}
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(145, 136, 60, 5);
		g.fillRect(295, 136, 60, 5);
		g.fillRect(445, 136, 60, 5);
	}
	
	public void elementos() {
		
		this.a1 = new TextField();
		this.a1.setBounds(150,100,50,20);
		this.add(this.a1);
		
		this.b1 = new TextField();
		this.b1.setBounds(150,160,50,20);
		this.add(this.b1);
		
		this.a2 = new TextField();
		this.a2.setBounds(300,100,50,20);
		this.add(this.a2);
		
		this.b2 = new TextField();
		this.b2.setBounds(300,160,50,20);
		this.add(this.b2);
		
		this.c1 = new TextField();
		this.c1.setBounds(450,100,50,20);
		this.add(this.c1);
		
		this.c2 = new TextField();
		this.c2.setBounds(450,160,50,20);
		this.add(this.c2);
		
		this.igual = new JButton("=");
		this.igual.setBounds(375, 115, 50, 50);
		this.igual.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calcularMCD();
				
			}
		});
		this.add(this.igual);
		
		this.operacion = new JComboBox();
		this.operacion.setBounds(220, 130, 65, 20);
		this.operacion.addItem("+");
		this.operacion.addItem("-");
		this.operacion.addItem("*");
		this.operacion.addItem("/");
		this.operacion.addItem("^");
		this.add(this.operacion);
		
	}
	
}
