package eda.grafos;

import java.util.Map;

import eda.auxiliar.Iterables2;

public class Grafos {
	
	public static Graph<String,String> newStringGraph(String
			file_vertex, String file_adyacencia) {
		Graph<String,String> ret = new SimpleGraph<String,String>(String.class);
		
		Iterable<String> itv = Iterables2.from(file_vertex);
		for(String s:itv)
			ret.addVertex(s);
		
		Iterable<String> ite = Iterables2.from(file_adyacencia);
		for(String s:ite)
		{	
			String[] vector = s.split(";");
			ret.addEdge(vector[0],vector[1],vector[2]);
		}
		
		return ret;
	}
	
	public static Graph<String,String> new StringGraph(String stringFileAdy)
	{
		Graph<String,String> ret = new SimpleGraph<String,String>(String.class);
		
		Iterable<String> ita = Iterables2.from(stringFileAdy);
		for(String s: ita)
		{
			String[] vector = s.split(";");
			ret.addVertex(vector[0]);
			ret.addVertex(vector[1]);
			ret.addEdge(vector[0],vector[1],vector[2]);
		}
		return ret;
	}
	
	public static <V,E> Graph<V,E> newGraph(String file_vertex,String file_edges,String file_ady,
			final VertexFactoryExtended<V> vf, final EdgeFactoryExtended<V,E> ef)
			{
				Graph<V,E> ret = new SimpleGraph<V,E>(ef);
				
				Map<String,V> vertices = Maps.newHashMap();
				Map<String,E> aristas = Maps.newHashMap();
				
				Function<String,V> StringAVertice = new Function<String,V>()
				{
					public V apply(String s)
					{
						return vf.createVertex(s);
					}
				};
				
				Function<String,E> StringAArista = new Function<String,E>()
				{
					public E apply(String s)
					{
						return ef.createEdge(s);
					}
				};
				
				Iterable<String> itv = Iterables2.from(file_vertex);
				for(String s:itv)
				{
					String[] vector;
					vector = s.split(";");
					vertices.put(vector[0],StringAVertice,apply(vector[1]));
				}
				
				Iterable<String> ite = Iterables2.from(file_edges);
				for(String s:ite)
				{
					String[] vector;
					vector = s.split(";");
					aristas.put(vector[0],StringAArista.apply(vector[1]));
				}
				
				Iterable<String> ita = Iterables2.from(file_ady);
				for(String s:ita)
				{
					String[] vector;
					vector = s.split(":");
					
					V v1 = vertices.get(vector[0]);
					V v2 = vertices.get(vector[1]);
					E e = aristas.get(vector[2]);
					
					ret.addVertex(v1);
					ret.addVertex(v2);
					ret.addEdge(v1,v2,e);
				}
				
				return ret;
			}
}
