/*******************************************************************************
 * Copyright (c) 2008, 2013 Oracle. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0, which accompanies this distribution
 * and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Contributors:
 *     Oracle - initial API and implementation
 ******************************************************************************/
package org.eclipse.jpt.nosql.eclipselink.core.internal.context.java;

import java.util.List;
import org.eclipse.jpt.common.core.resource.java.JavaResourceAnnotatedElement;
import org.eclipse.jpt.common.utility.internal.ArrayTools;
import org.eclipse.jpt.common.utility.internal.iterable.IterableTools;
import org.eclipse.jpt.jpa.core.context.JpaContextNode;
import org.eclipse.jpt.jpa.core.context.java.JavaConverter;
import org.eclipse.jpt.jpa.core.context.java.JavaPersistentAttribute;
import org.eclipse.jpt.jpa.core.internal.context.java.AbstractJavaVersionMapping;
import org.eclipse.jpt.nosql.eclipselink.core.context.EclipseLinkMutable;
import org.eclipse.jpt.nosql.eclipselink.core.context.EclipseLinkVersionMapping;
import org.eclipse.jpt.nosql.eclipselink.core.context.java.EclipseLinkJavaConvertibleMapping;
import org.eclipse.jpt.nosql.eclipselink.core.context.java.JavaEclipseLinkConverterContainer;
import org.eclipse.jpt.nosql.eclipselink.core.internal.DefaultEclipseLinkJpaValidationMessages;
import org.eclipse.jpt.nosql.eclipselink.core.internal.EclipseLinkJpaValidationMessages;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;

public class JavaEclipseLinkVersionMapping
	extends AbstractJavaVersionMapping
	implements EclipseLinkVersionMapping, EclipseLinkJavaConvertibleMapping
{
	protected final JavaEclipseLinkMutable mutable;

	protected final JavaEclipseLinkConverterContainer converterContainer;


	public JavaEclipseLinkVersionMapping(JavaPersistentAttribute parent) {
		super(parent);
		this.mutable = new JavaEclipseLinkMutable(this);
		this.converterContainer = this.buildConverterContainer();
	}


	// ********** synchronize/update **********

	@Override
	public void synchronizeWithResourceModel() {
		super.synchronizeWithResourceModel();
		this.mutable.synchronizeWithResourceModel();
		this.converterContainer.synchronizeWithResourceModel();
	}

	@Override
	public void update() {
		super.update();
		this.mutable.update();
		this.converterContainer.update();
	}


	// ********** mutable **********

	public EclipseLinkMutable getMutable() {
		return this.mutable;
	}


	// ********** converters **********

	public JavaEclipseLinkConverterContainer getConverterContainer() {
		return this.converterContainer;
	}

	protected JavaEclipseLinkConverterContainer buildConverterContainer() {
		return new JavaEclipseLinkConverterContainerImpl(this);
	}

	// ********** converter container parent adapter **********

	public JpaContextNode getConverterContainerParent() {
		return this;  // no adapter
	}

	public JavaResourceAnnotatedElement getJavaResourceAnnotatedElement() {
		return this.getResourceAttribute();
	}

	public boolean parentSupportsConverters() {
		return ! this.getPersistentAttribute().isVirtual();
	}

	// ********** converter adapters **********

	/**
	 * put the EclipseLink convert adapter first - this is the order EclipseLink searches
	 */
	@Override
	protected Iterable<JavaConverter.Adapter> getConverterAdapters() {
		return IterableTools.insert(JavaEclipseLinkConvert.Adapter.instance(), super.getConverterAdapters());
	}


	// ********** validation **********

	@Override
	public void validate(List<IMessage> messages, IReporter reporter) {
		super.validate(messages, reporter);
		this.mutable.validate(messages, reporter);
	}
	
	@Override
	protected void validateAttributeType(List<IMessage> messages) {
		if (!ArrayTools.contains(SUPPORTED_TYPE_NAMES, this.getPersistentAttribute().getTypeName())) {
			if (this.getPersistentAttribute().isVirtual()) {
				messages.add(
						DefaultEclipseLinkJpaValidationMessages.buildMessage(
								IMessage.HIGH_SEVERITY,
								EclipseLinkJpaValidationMessages.PERSISTENT_ATTRIBUTE_INVALID_VERSION_MAPPING_TYPE,
								new String[] {this.getName()},
								this,
								this.getVirtualPersistentAttributeTextRange()
						)
				);
			}
			else {
				messages.add(
						DefaultEclipseLinkJpaValidationMessages.buildMessage(
								IMessage.HIGH_SEVERITY,
								EclipseLinkJpaValidationMessages.PERSISTENT_ATTRIBUTE_INVALID_VERSION_MAPPING_TYPE,
								new String[] {this.getName()},
								this,
								this.getValidationTextRange()
						)
				);
			}
		}
	}
}
