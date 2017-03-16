package org.eclipse.scanning.test.event.queues.beans.models;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.scanning.api.event.queues.beans.MoveAtom;
import org.eclipse.scanning.api.event.queues.models.arguments.IQueueValue;
import org.eclipse.scanning.api.event.queues.models.arguments.QueueValue;
import org.eclipse.scanning.event.queues.builder.models.MoveAtomModel;
import org.junit.Test;

public class MoveAtomModelTest {
	
	@Test
	public void testDefine() {
		MoveAtomModel mvAtMod = new MoveAtomModel("ShrtNm", "Long name", Arrays.asList("motor1", "motor2"));
		
		assertEquals("ShrtNm", mvAtMod.getName());
		assertEquals("Long name", mvAtMod.getAtomName());
		assertEquals("Incorrect positioners in list", Arrays.asList("motor1", "motor2"), mvAtMod.getArgNames());
		assertEquals("Number of arguments to build atom incorrectly reported", 2, mvAtMod.nrOfArgs());
		
		mvAtMod = new MoveAtomModel("ShrtNm", "Long name", Arrays.asList("heater3", "pump4", "badger5"));
		Map<String, Class<?>> mvAtArgs = new HashMap<>();
		for (String poser : Arrays.asList("heater3", "pump4", "badger5")) {
			mvAtArgs.put(poser, Double.class);
		}
		assertEquals("Incorrect args list", mvAtArgs, mvAtMod.getArgs());
		assertEquals("Number of arguments to build atom incorrectly reported", 3, mvAtMod.nrOfArgs());
	}
	
	@Test
	public void testBuild() {
		MoveAtom handMade = new MoveAtom("My lovely move", "motor1", 23.5);
		handMade.putPositioner("motor2", -459.1);
		
		List<IQueueValue<?>> config = Arrays.asList(new QueueValue<>(23.5), new QueueValue<>(-459.1));
		MoveAtomModel mvAtMod = new MoveAtomModel("ShrtNm", "My lovely move", Arrays.asList("motor1", "motor2"));
		MoveAtom mvAt = mvAtMod.build(config);
		
		assertEquals("Built atom differs from manually created equivalent!", handMade, mvAt);
	}

}
