/*******************************************************************************
 * Copyright (c) 2007 Australian Nuclear Science and Technology Organisation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tony Lam (Bragg Institute) - initial API and implementation
 *******************************************************************************/

package org.gumtree.sics.io;

public class SicsExecutionException extends Exception {

	private static final long serialVersionUID = 8972265077946840132L;

	public SicsExecutionException(String message) {
		super(message);
	}

	public SicsExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

}
