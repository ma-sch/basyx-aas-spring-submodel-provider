package org.example;

import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.map.Submodel;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.eclipse.basyx.submodel.metamodel.map.submodelelement.dataelement.property.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SampleSubmodelFactory implements ISubmodelFactory {

	public Submodel createSubmodel() {
		var id = UUID.randomUUID().toString();

		Property property1 = new Property(0);
		property1.setIdShort("currentPosition");

		Property property2 = new Property(0);
		property2.setIdShort("lifterPosition");

		Property property3 = new Property(false);
		property3.setIdShort("physicalSpeed");

		List<Property> propList = new ArrayList<>();
		propList.add(property1);
		propList.add(property2);
		propList.add(property3);

		Submodel sm = new Submodel(id, new Identifier(IdentifierType.CUSTOM, id + "Custom"));
		propList.forEach(sm::addSubmodelElement);
		return sm;
	}
}
