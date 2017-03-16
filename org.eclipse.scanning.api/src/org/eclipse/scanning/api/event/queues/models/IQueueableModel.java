package org.eclipse.scanning.api.event.queues.models;

import java.util.List;
import java.util.Map;

import org.eclipse.scanning.api.event.queues.models.arguments.IQueueValue;

public interface IQueueableModel<T> {
	
	public String getName();
	
	public String getAtomName();
	
	public void addArg(String argName, Class<?> type);
	
	public Map<String, Class<?>> getArgs();
	
	public List<String> getArgNames();
	
	public int nrOfArgs();
	
	public Class<T> getAtomType();
	
	public T build(List<IQueueValue<?>> argValues);

}
