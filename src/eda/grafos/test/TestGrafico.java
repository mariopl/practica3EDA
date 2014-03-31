package eda.grafos.test;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JApplet;

import org.jgraph.JGraph;

import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;

import sierranevada.modelo.Enlace;
import sierranevada.modelo.Zona;

import eda.grafos.FabricaVerticeZona;
import eda.grafos.FactoryAristEnlace;
import eda.grafos.Grafos;



public class TestGrafico<V,E> extends JApplet {

	private static final long serialVersionUID = -6402263715575496113L;
	private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);
	
	private JGraphModelAdapter<String,String> m_jgAdapter;
	//private JGraphModelAdapter<Zona, Enlace> m_jgAdapter;
	
	/**
	 * @see java.applet.Applet#init().
	 */
	public void init() {		
	
		
		String stringFileVertex="c:/StringVertices.txt";
		String stringFileEdges="c:/StringAristas.txt";
		String stringFileAdj = "c:/StringAdyacencia.txt";
		
		/*String stringFileVertex="c:/ClaseVerticesBig.txt";
		String stringFileEdges="c:/ClaseAristasBig.txt";
		String stringFileAdj = "c:/ClaseAdyacenciaBig.txt";
		*/
		
		Graph<String, String> grafo= Grafos.newStringGraph(stringFileAdj);
		//Graph<Zona, Enlace> grafo= Grafos.newGraph(stringFileVertex,stringFileEdges, stringFileAdj,new FabricaVerticeZona(), new FactoryAristEnlace());
		
		
		m_jgAdapter = new JGraphModelAdapter<String,String>(grafo);
		//m_jgAdapter = new JGraphModelAdapter<Zona,Enlace>(grafo);
		
		
		JGraph jgraph = new JGraph(m_jgAdapter);
		jgraph.setAntiAliased(true);
		jgraph.setAutoResizeGraph(true);
		
		adjustDisplaySettings(jgraph);
		getContentPane().add(jgraph);
		resize(DEFAULT_SIZE);
		
		
	}
	
	private void adjustDisplaySettings(JGraph jg) {
		jg.setPreferredSize(DEFAULT_SIZE);

		Color c = Color.LIGHT_GRAY;
		String colorStr = null;

		try {
			colorStr = getParameter("bgcolor");
		} catch (Exception e) {
		}

		if (colorStr != null) {
			c = Color.decode(colorStr);
		}

		jg.setBackground(c);
	}
	

}
