package org.eclipse.scanning.event.queues.builder.models;

import java.util.List;

import org.eclipse.scanning.api.event.queues.beans.MoveAtom;
import org.eclipse.scanning.api.event.queues.models.IQueueAtomModel;
import org.eclipse.scanning.api.event.queues.models.arguments.IQueueValue;

public class MoveAtomModel extends QueueableModel<MoveAtom> implements IQueueAtomModel<MoveAtom> {

	public MoveAtomModel(String name, String atomName, List<String> positioners) {
		super(name, atomName);
		for (String poser : positioners) {
			addArg(poser, Double.class);
		}
	}

	@Override
	public Class<MoveAtom> getAtomType() {
		return MoveAtom.class;
	}

	@Override
	public MoveAtom build(List<IQueueValue<?>> argValues) {
		// TODO Auto-generated method stub
		return null;
	}

}
