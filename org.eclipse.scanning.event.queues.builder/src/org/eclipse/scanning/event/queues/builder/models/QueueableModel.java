package org.eclipse.scanning.event.queues.builder.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.scanning.api.event.queues.models.IQueueableModel;

public abstract class QueueableModel<T> implements IQueueableModel<T> {
	
	private final Map<String, Class<?>> args;
	private final String name;
	private String atomName;
	
	protected QueueableModel(String name, String atomName) {
		this.name = name;
		this.atomName = atomName;
		args = new LinkedHashMap<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getAtomName() {
		return atomName;
	}

	@Override
	public void addArg(String argName, Class<?> type) {
		if (type == null) {
			type = Double.class;
		}
		args.put(argName, type);
	}
	
	@Override
	public Map<String, Class<?>> getArgs() {
		return args;
	}

	@Override
	public List<String> getArgNames() {
		return new ArrayList<>(args.keySet());
	}

	@Override
	public int nrOfArgs() {
		return args.size();
	}

}
