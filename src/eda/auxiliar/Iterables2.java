package eda.auxiliar;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.google.common.base.*;
import com.google.common.collect.*;


public class Iterables2 {
	
	
	public static <F> void modify(Iterable<F> fromIterable, Function<? super F,? extends F> function){
		Iterables.transform(fromIterable, function);
	}
	
	public static Integer sum(Iterable<Integer> fromIterable){
		Integer a = 0;
		for(Integer e : fromIterable){
			a = a+e;
		}
		return a;
	}
	
	
	public static Double multiply(Iterable<Double> fromIterable){
		Double a = 1.;
		for(Double e : fromIterable){
			a = a*e;
		}
		return a;
	}
	public static Double average(Iterable<Double> fromIterable){
		Double a = 1.;
		for(Double e : fromIterable){
			a = a*e;
		}
		return a/Iterables.size(fromIterable);
	}
	
	public static Iterable<String> from(String nombreFichero){
		return new FlujoEntrada(nombreFichero);
	}
	
	public static Iterable<Double> from(Double a, Double b, Double c){
		return new SecuenciaAritmetica(a,b,c);
	}
	
	public static Iterable<Double> from(Double a, Double b){		
		return new SecuenciaAritmetica(a,b);
	}
	
	
	public static <T> Iterable<T> from(T[] a){
		return Arrays.asList(a);
	}
	
	private static class FlujoEntrada implements Iterable<String>{
		private String nf;
		
		public FlujoEntrada(String f) {
			nf = f;
		}
		
		public Iterator<String> iterator(){
			return new IteradorFlujoEntrada();	
		}
		
		private class IteradorFlujoEntrada implements Iterator<String>{
			private BufferedReader bf;
			private String linea;	
			
			public IteradorFlujoEntrada(){			
				try{
					bf = new BufferedReader(new FileReader(nf));
					linea = bf.readLine();
				} catch (IOException e) {throw new IllegalArgumentException("No se puede acceder al fichero de entrada");}
			}
			
			public boolean hasNext() { 	
				return linea!=null; 
			}

			public String next() {
				if(!hasNext())  throw new IllegalArgumentException("No se puede acceder a next");
				String pal = linea;
				try{
					linea=bf.readLine();
				}catch(IOException e){throw new IllegalArgumentException("No se puede acceder al fichero de entrada");}
				return pal;
			}

			public void remove() {
				
			}		
		}
	}
	
	private static class SecuenciaAritmetica implements Iterable<Double> {

		private Double primero,ultimo,incremento;
		
		public SecuenciaAritmetica(Double a, Double b, Double c){
			primero =    a.doubleValue();
			ultimo =     b.doubleValue();
			incremento=  c.doubleValue();
			
			if((ultimo-primero)*incremento < 0 ){
				throw new IllegalArgumentException("Parametros inapropiados");
			}
		}
		
		public SecuenciaAritmetica(Double a, Double b){
			primero =    a.doubleValue();
			ultimo =     b.doubleValue();
			incremento= ultimo > primero ? 1. : -1. ;
			if(ultimo-primero != 0. ){
				throw new IllegalArgumentException("Parametros inapropiados");
			}
		}
		
		public Iterator<Double> iterator(){
			return new IteradorSecuenciaAritmetica();
		}

		private class IteradorSecuenciaAritmetica implements Iterator<Double>{
			private Double actual;
			
			public IteradorSecuenciaAritmetica( ){
				actual=primero; 
	  		}

			public Double next() { 
	        		Double r=actual; 
	        		actual = actual + incremento;
					return r;
	  		}
	    
	  		public boolean hasNext() { 
	        		return actual<ultimo; 
	  		} 

	  		public void remove() {
	  			
	  		}

	 	}
	}

}

