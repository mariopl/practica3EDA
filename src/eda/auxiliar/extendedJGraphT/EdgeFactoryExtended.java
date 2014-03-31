package eda.auxiliar.extendedJGraphT;

import org.jgrapht.EdgeFactory;

public interface EdgeFactoryExtended<V,E> extends EdgeFactory<V, E> {
	E createEdge(String formato);

}
