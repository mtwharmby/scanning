/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.scanning.example.malcolm;

import org.epics.pvaccess.client.ChannelProvider;
import org.epics.pvaccess.server.impl.remote.ServerContextImpl;
import org.epics.pvdatabase.PVDatabase;
import org.epics.pvdatabase.PVDatabaseFactory;
import org.epics.pvdatabase.pva.ChannelProviderLocalFactory;

/**
 * This class creates an Epics V4 service, that listens for connections and handles RPC, GET, PUT etc. 
 * The modelled device is meant to represent a typical Malcolm Device, and has attributes and methods
 * set up accordingly. Any RPC call made to the device just pause for 2 seconds and then return an empty Map
 * 
 * @author Matt Taylor
 *
 */
public class EPICSv4ExampleDevice extends AbstractEPICSv4Device{
    
    public EPICSv4ExampleDevice(String deviceName) {
    	super(deviceName);
    }
    
    public void start() throws Exception {
    	PVDatabase master = PVDatabaseFactory.getMaster();
    	ChannelProvider channelProvider = ChannelProviderLocalFactory.getChannelProviderLocal();
    	pvRecord = DummyMalcolmRecord.create(recordName);
    	pvRecord.setTraceLevel(traceLevel);
    	master.addRecord(pvRecord);
    	ServerContextImpl context = ServerContextImpl.startPVAServer(channelProvider.getProviderName(),0,true,System.out);
    	latch.await();
    	master.removeRecord(pvRecord);
    	context.destroy();
    }
}
