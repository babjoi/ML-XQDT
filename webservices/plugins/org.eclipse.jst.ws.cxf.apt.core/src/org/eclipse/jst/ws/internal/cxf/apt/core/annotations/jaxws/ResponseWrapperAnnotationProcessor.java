/*******************************************************************************
 * Copyright (c) 2008 IONA Technologies PLC
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IONA Technologies PLC - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ws.internal.cxf.apt.core.annotations.jaxws;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.Messager;
import com.sun.mirror.declaration.AnnotationMirror;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.AnnotationTypeElementDeclaration;
import com.sun.mirror.declaration.AnnotationValue;
import com.sun.mirror.declaration.Declaration;

/**
 * 
 * @author sclarke
 *
 */
public class ResponseWrapperAnnotationProcessor implements AnnotationProcessor {
	
    private AnnotationProcessorEnvironment environment;

	public ResponseWrapperAnnotationProcessor(AnnotationProcessorEnvironment environment) {
		this.environment = environment;
	}

	public void process() {
//        Messager messager = environment.getMessager();
//
//        AnnotationTypeDeclaration annotationDeclaration = (AnnotationTypeDeclaration) environment
//                .getTypeDeclaration("javax.xml.ws.ResponseWrapper"); //$NON-NLS-1$
//
//        Collection<Declaration> annotatedTypes = environment
//                .getDeclarationsAnnotatedWith(annotationDeclaration);
//
//        for (Declaration declaration : annotatedTypes) {
//            Collection<AnnotationMirror> annotationMirrors = declaration.getAnnotationMirrors();
//
//            for (AnnotationMirror mirror : annotationMirrors) {
//                Map<AnnotationTypeElementDeclaration, AnnotationValue> valueMap = mirror.getElementValues();
//                Set<Map.Entry<AnnotationTypeElementDeclaration, AnnotationValue>> valueSet = valueMap
//                        .entrySet();
//                for (Map.Entry<AnnotationTypeElementDeclaration, AnnotationValue> annotationKeyValue : valueSet) {
//                    AnnotationValue annotationValue = annotationKeyValue.getValue();
//                }
//            }
//        }
	}

}
