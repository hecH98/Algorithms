//A01633182 Braulio Vargas Camargo
//stringSearch.java
//8 de Octubre del 2019 
//Comentarios:...
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class stringSearch extends JFrame implements ActionListener{
	private JLabel bienvenida, 
					texto, 
					cadena;
	private JTextArea text;
	private JTextPane found;
	private JTextField pattern;
	private JButton leer, 
					buscar;
	
	public stringSearch() {
		JFrame frame=new JFrame("String Search");
		Container contenedor=getContentPane();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.add(contenedor);
		frame.setPreferredSize(new Dimension(700,500));
		contenedor.setPreferredSize(new Dimension(700,500));
		contenedor.setLayout(null);
		
		bienvenida=new JLabel();
		bienvenida.setBounds(190, 5, 200, 30);
		bienvenida.setText("Busqueda de texto");
		contenedor.add(bienvenida);
		
		texto=new JLabel();
		texto.setBounds(30, 50, 100, 30);
		texto.setText("Texto");
		contenedor.add(texto);
		
		text=new JTextArea(5,20);
		text.setWrapStyleWord(true);
		text.setLineWrap(true);
		JScrollPane scrollTxt=new JScrollPane(text);
		scrollTxt.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollTxt.setBounds(30, 80, 300, 200);
		this.add(scrollTxt);
		
		leer=new JButton("Leer archivo");
		leer.setBounds(130, 290, 100, 30);
		leer.addActionListener(this);
		this.add(leer);
		
		cadena=new JLabel();
		cadena.setBounds(30, 320, 100, 30);
		cadena.setText("Palabra");
		contenedor.add(cadena);
		
		pattern=new JTextField();
		pattern.setBounds(30, 350, 300, 30);
		this.add(pattern);
		
		buscar=new JButton("Buscar");
		buscar.setBounds(130, 390, 100, 30);
		buscar.addActionListener(this);
		this.add(buscar);
	
		found=new JTextPane();
		
		found.setEditable(false);
		JScrollPane scrollTxt2=new JScrollPane(found);
		scrollTxt2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollTxt2.setBounds(350, 80, 300, 200);
		this.add(scrollTxt2);
		
		
		frame.setVisible(true);
		frame.pack();
	}
	public void searchFile() {
		//Creamos el objeto JFileChooser
		JFileChooser fc=new JFileChooser();
		 
		//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
		int seleccion=fc.showOpenDialog(getContentPane());
		 
		//Si el usuario, pincha en aceptar
		if(seleccion==JFileChooser.APPROVE_OPTION){
		 
		    //Seleccionamos el fichero
		    File fichero=fc.getSelectedFile();
		 
		    //Ecribe la ruta del fichero seleccionado en el campo de texto
		    //text.setText(fichero.getAbsolutePath());
		 
		    try(FileReader fr=new FileReader(fichero)){
		        String cadena="";
		        int valor=fr.read();
		        while(valor!=-1){
		            cadena=cadena+(char)valor;
		            valor=fr.read();
		        }
		        text.setText(cadena);
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    }
		}
	}
	
	public void searchChain() {
		found.setText("");
		StyledDocument doc = found.getStyledDocument();
        Style style = found.addStyle("I'm a Style", null);
        String texts=""+text.getText()+"";
        String cadenas=""+pattern.getText()+"";
        MyQueue<Integer> posiciones=new MyQueue<Integer>(new KMP().stringSearchKMP(texts, cadenas));
        char[]texto=text.getText().toCharArray();
        for(int i=0;i<texto.length;i++) {

        		if(!posiciones.isEmpty()&&i==(int)posiciones.next()) {
        			StyleConstants.setForeground(style, Color.red);
        			try { doc.insertString(doc.getLength(), texto[i]+"",style); }
        			catch (BadLocationException e){}
        			posiciones.dequeue();
        		}
        		else {
        			StyleConstants.setForeground(style, Color.black);
        			try { doc.insertString(doc.getLength(), texto[i]+"",style); }
        			catch (BadLocationException e){}
        		}

        }
	}

	public static void main(String[]args) {
		stringSearch test=new stringSearch();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(this.leer)) searchFile();
		
		else if(e.getSource().equals(this.buscar)) searchChain();
	}
}
