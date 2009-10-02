/**********************************************************************
 * Copyright (c) 2009 Martin Schmied and others. All rights reserved.   This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Martin Schmied - Initial API and implementation
 **********************************************************************/
package org.eclipse.wst.rng.core.internal.validator;

import org.eclipse.wst.validation.internal.provisional.core.IValidator;
import org.eclipse.wst.xml.ui.internal.validation.DelegatingSourceValidator;

@SuppressWarnings("restriction")
public class DelegatingSourceValidatorForRngDocuments extends DelegatingSourceValidator {
	private IValidator validationDelegate = new JingValidationDelegate();
	
	public DelegatingSourceValidatorForRngDocuments() {}

	@Override
	protected IValidator getDelegateValidator() {
		return validationDelegate;
	}
}
