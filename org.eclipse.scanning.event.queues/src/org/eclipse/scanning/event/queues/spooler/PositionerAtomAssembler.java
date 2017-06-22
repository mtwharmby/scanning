package org.eclipse.scanning.event.queues.spooler;

import java.util.Map;

import org.eclipse.scanning.api.event.queues.beans.PositionerAtom;
import org.eclipse.scanning.api.event.queues.models.QueueModelException;
import org.eclipse.scanning.api.event.queues.models.arguments.IQueueValue;

public class PositionerAtomAssembler implements IBeanAssembler<PositionerAtom> {

	@Override
	public PositionerAtom buildNewBean(PositionerAtom model, Map<String, IQueueValue<?>> localValues) throws QueueModelException {
		PositionerAtom atom = new PositionerAtom();
		atom.setBeamline(model.getBeamline());
		atom.setRunTime(model.getRunTime());
		atom.setShortName(model.getShortName());
		atom.setModel(false);
		
		/*
		 * Loop through the positionerConfig in the model replacing any 
		 * targets which have references in the localValues or the 
		 * globalValues (see {@link QueueBeanFactory})
		 */
		for (String dev : model.getPositionerNames()) {
			Object devTgt = model.getPositionerTarget(dev);
			if (devTgt instanceof String) {
				//It might be a value name...
				String devVarName = (String) devTgt;
				if (localValues.containsKey(devVarName)) {
					devTgt = localValues.get(devVarName).evaluate();
				} else {
					IQueueValue<?> globalValue = getQueueBeanFactory().getGlobalValue(devVarName);
					if (globalValue != null){
						devTgt = globalValue.evaluate();
					}
					//If globalValue was null, the String must have been a real target
				}
			}
			atom.addPositioner(dev, devTgt);
		}
		
		return atom;
	}

	@Override
	public PositionerAtom setBeanName(PositionerAtom bean) {
		// TODO Auto-generated method stub
		return null;
	}

}