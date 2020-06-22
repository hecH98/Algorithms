import java.util.Iterator;

public class KMP {
	public MiListaEnlazada<Integer> stringSearchKMP(String text, String pattern) 
    { 
        int P = pattern.length(); 
        int T = text.length(); 
        int lps[] = new int[P]; 
        MiListaEnlazada<Integer> posiciones=new MiListaEnlazada<Integer>();
        int j = 0; 
        getL(pattern, P, lps); 
        int i = 0; 
        while (i < T) { 
            if (pattern.charAt(j) == text.charAt(i)) { 
                j++; 
                i++; 
            } 
            if (j == P) { 
            		for(int e=0;e<pattern.length();e++) {
            			posiciones.insertarFin(i-j+e); 
            		}
                j = lps[j - 1]; 
            } 
            else if (i < T && pattern.charAt(j) != text.charAt(i)) { 
                if (j != 0) j = lps[j - 1]; 
                else i = i + 1; 
            } 
        } 
        removeDuplicates(posiciones);
        return posiciones;
    } 
	public void removeDuplicates(MiListaEnlazada<Integer> lista) { 
        NodoLE<Integer> current=lista.inicio;
        int pos=0;
		while(current.getNext()!=null) {
        	  	if(current.getDato()>=current.getNext().getDato()) {
        	  		lista.borrarEn(pos+1);
        	  	}
        	  	else {
        	  		current=current.getNext();
        	  		pos++;
        	  	}
        }
        
    } 
    public void getL(String pattern, int P, int lps[]) { 
        int len = 0; 
        int i = 1; 
        lps[0] = 0; 
        while (i < P) { 
            if (pattern.charAt(i) == pattern.charAt(len)) { 
                len++; 
                lps[i] = len; 
                i++; 
            } 
            else { 
                if (len != 0) len = lps[len - 1]; 
                else lps[i++] = len;   
            } 
        } 
    } 
}
/*public static void main(String args[]) 
    { 
        String text = "aabaabaa"; 
        String pattern = "aabaa"; 
        MiListaEnlazada e=new KMP().stringSearchKMP(text, pattern); 
        Iterator<Integer> it = e.iterator();
		int ent;
		while (it.hasNext()) {
			ent = it.next();
			System.out.println(ent);
		}
    } */