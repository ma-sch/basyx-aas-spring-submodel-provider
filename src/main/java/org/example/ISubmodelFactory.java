package org.example;

import org.eclipse.basyx.submodel.metamodel.map.Submodel;

public interface ISubmodelFactory {

    Submodel createSubmodel(String aasIdShort);

}
