/******************************************************************************* 
* Copyright (c) 2008 Australian Nuclear Science and Technology Organisation.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0 
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
* 
* Contributors: 
*    Norman Xiong (nxi@Bragg Institute) - initial API and implementation
*******************************************************************************/
package au.gov.ansto.bragg.quokka.model.core.device;

import au.gov.ansto.bragg.quokka.model.core.device.VirtualDevice.DeviceType;
import au.gov.ansto.bragg.quokka.model.core.exception.DriveDeviceFailedException;

/**
 * @author nxi
 * Created on 27/06/2008
 */
public class SamplePositionZ extends VirtualDevice {

	private static SamplePositionZ instance;

	/**
	 * 
	 */
	public SamplePositionZ() {
		// TODO Auto-generated constructor stub
		super();
		setId("sampleposzmm");
		setName("SamplePosZmm");
		setUnit("mm");
		setDescription("Sample Position in Z");
		deviceType = DeviceType.setable;
	}

	public static SamplePositionZ getInstance(){
		if (instance == null){
			instance = new SamplePositionZ();
		}
		return instance;
	}

	
	/* (non-Javadoc)
	 * @see au.gov.ansto.bragg.quokka.model.core.device.VirtualDevice#changeModel(java.lang.Object)
	 */
	@Override
	protected void changeModel(Object positionObject)
			throws DriveDeviceFailedException {
		// TODO Auto-generated method stub

	}

}
