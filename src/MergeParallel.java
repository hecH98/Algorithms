import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

// Hector Herrera
// David Perez
// Carlos Lopez

public class MergeParallel {
	public static <E extends Comparable<E>> void mergesort(E[] datos) {
		int min=0,
			max=datos.length-1;
		mergesort(datos, min, max);
	}
	
	private static <E extends Comparable<E>> void mergesort(E[] datos, int min, int max) {
		if(min<max) {
			int central=(min+max)/2;
			mergesort(datos, min, central);
			mergesort(datos, central+1, max);
			merge(datos,min , max);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <E extends Comparable<E>> void merge(E[] datos, int min, int max) {
		E[] tmp=(E[]) new Comparable[max-min+1];
		
		int central=(max+min)/2,
		    j=min,
			k=central+1;
		
		for (int i = 0; i < tmp.length; i++) {	
			if(j<=central && k<=max) {
				if(datos[j].compareTo(datos[k])<0) {
					tmp[i]=datos[j++];
				}
				else {
					tmp[i]=datos[k++];
				}
			}
			else if(j<=central) {
				tmp[i]=datos[j++];
			}
			else {
				tmp[i]=datos[k++];
			}
			
		}
		System.arraycopy(tmp, 0, datos, min, tmp.length);
	}
	
	public static <E> void imprimeArreglo(E[] numeros) {
		for (int i = 0; i < numeros.length; i++) {
			System.out.print(numeros[i]+",");
		}
		System.out.println();
	}
	
	
	public static class Parallel extends RecursiveTask<Integer[]> {
		
		private Integer[] array;
		private int min, max, minSize;
		
		public Parallel(Integer[] array, int min, int max, int minSize) {
			this.array = array;
			this.min = min;
			this.max = max;
			this.minSize = minSize;
			compute();
		}
		
		public Integer[] compute() {
			mergesort(this.min, this.max);
			return this.array;
		}
		
		public void mergesort(int min, int max) {
			if (min + max <= minSize) {		//max
				int mid = (min + max) / 2;
				Parallel merge1 = new Parallel(this.array, min, mid, this.minSize);
				Parallel merge2 = new Parallel(this.array, mid+1, max, this.minSize);
				
				ForkJoinTask.invokeAll(merge1, merge2);
			} else {
				quickSort(array, min, max);
			}
			merge(array, min, max);
		}
		
//----------------------------Merge---------------------------------------------------		
		
		private static void merge(Integer[] array, int min, int max) {
			Integer[] temp = new Integer[max - min + 1];
			int mid = (min + max)/2,
				j = min,
				k = mid + 1;
			
			for(int i = 0; i < temp.length; i++) {
				if(j <= mid && k <= max) {
					
					if(array[j].compareTo(array[k]) < 0) {
						temp[i] = array[j++];
					} else {
						temp[i] = array[k++];
					}
				} else if(j <= mid) {
					temp[i] = array[j++];
					
				} else {
					temp[i] = array[k++];
				}
			}
			
			System.arraycopy(temp, 0, array, min, temp.length);
		}
	}
//----------------------------Merge END--------------------------------------------------
		
	
//----------------------------Array Settings---------------------------------------------	
	public static void imprimeArreglo(Integer[] datos) {
		for(int i = 0; i < datos.length;i++) {
			System.out.print(datos[i] + ",");
		}
		System.out.println();
	}
	
	public static Integer[] generaArreglo (int tamanio) {
		Random rand1 = new Random();
		Integer[] datos = new Integer[tamanio];
		for (int i = 0; i<tamanio; i++) {
			datos[i] = rand1.nextInt(1000000);
		}
		return datos;
	}
//----------------------------Array Settings END------------------------------------------	
	
//----------------------------Quick Sort--------------------------------------------------
	
	public static void quickSort(Integer[] arr, int start, int end){
		 
        Integer partition = partition(arr, start, end);
 
        if(partition-1>start) {
            quickSort(arr, start, partition - 1);
        }
        if(partition+1<end) {
            quickSort(arr, partition + 1, end);
        }
    }
 
	
    public static int partition(Integer[] arr, int start, int end){
        Integer pivot = arr[end];
 
        for(int i=start; i<end; i++){
            if(arr[i]<pivot){
                Integer temp= arr[start];
                arr[start]=arr[i];
                arr[i]=temp;
                start++;
            }
        }
 
        int temp = arr[start];
        arr[start] = pivot;
        arr[end] = temp;
 
        return start;
    }
    
//----------------------------Quick Sort END-----------------------------------------------
	
	public static void main(String[] args) {
//----------------------------Arrays-------------------------------------------------------
		Integer[] datos = generaArreglo(5);
		Integer[] datos1 = new Integer[datos.length];
		Integer[] datos2 = new Integer[datos.length];
		for (int i =0;i<datos.length ;i++) {
			datos1[i] = datos[i];
		}
		for (int i =0;i<datos.length ;i++) {	//creando nuevos arreglos debido a que si igualo datos = datos1 y datos2 se hace apuntador y se ordena en la primera vuelta.
			datos2[i] = datos[i];
		}
//----------------------------Arrays END----------------------------------------------------
		
//----------------------------Recursive MergeSort-------------------------------------------	
		imprimeArreglo(datos1);
		long inicio = System.nanoTime();
		mergesort(datos1);
		long fin = System.nanoTime();
		long total = (fin - inicio);
		imprimeArreglo(datos1);
		System.out.println("tiempo de ejecucion 1 (mergeSort Recursivo): " + total);
//----------------------------Recursive MergeSort END---------------------------------------
		
		inicio=fin=0;
		
//----------------------------Parallel MergeSort--------------------------------------------		
		imprimeArreglo(datos2);
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		inicio = System.nanoTime();
		int minSize = 1;		//tamanio donde va a utilizar el quickSort
	    Integer[] res = pool.invoke(new Parallel(datos2, 0, datos2.length-1, minSize));
	    fin = System.nanoTime();
	    total = (fin - inicio);
		imprimeArreglo(res);
		System.out.println("tiempo de ejecucion 2 (mergeSort Paralelo): " + total);	
//----------------------------Parallel MergeSort END----------------------------------------
		
		
	}
}

