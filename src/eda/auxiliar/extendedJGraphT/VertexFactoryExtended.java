package eda.auxiliar.extendedJGraphT;

import org.jgrapht.VertexFactory;

public interface VertexFactoryExtended<V> extends VertexFactory<V> {
	V createVertex(String formato);
}
