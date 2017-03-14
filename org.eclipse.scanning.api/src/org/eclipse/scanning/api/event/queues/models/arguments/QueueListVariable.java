package org.eclipse.scanning.api.event.queues.models.arguments;

import java.util.List;

import org.eclipse.scanning.api.event.queues.models.QueueModelException;

/**
 * {@link IQueueValue} which holds an array/List. The value is selected from 
 * the List using the Integer value supplied as an arg {@link IQueueValue}.
 * 
 * @author Michael Wharmby
 *
 * @param <V> Type of the value held in the array of this argument.
 */
public class QueueListVariable<V> extends QueueVariableDecorator<Integer,V> {
	
	private IQueueValue<List<V>> list;
	
	/**
	 * Construct a QueueListVariable which will return a value from the 
	 * supplied List. 
	 * 
	 * @param arg {@link IQueueValue} with an Integer to specify the index.
	 * @param list {@link IQueueValue} containing the list of values.
	 */
	public QueueListVariable(IQueueValue<Integer> arg, IQueueValue<List<V>> list) {
		super(arg);
		this.list = list;
	}

	@Override
	protected V processArg(Integer parameter) {
		try {
			return list.evaluate().get(parameter);
		} catch (IndexOutOfBoundsException iEOBEx) {
			throw new QueueModelException("No value at index "+parameter+". ", iEOBEx);
		}
	}

}
