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

import org.eclipse.jdt.apt.core.internal.declaration.ClassDeclarationImpl;
import org.eclipse.jdt.apt.core.internal.declaration.InterfaceDeclarationImpl;
import org.eclipse.jst.ws.internal.cxf.apt.core.CXFAptCoreMessages;

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
@SuppressWarnings("restriction")
public class WebServiceAnnotationProcessor implements AnnotationProcessor {

    private AnnotationProcessorEnvironment environment;

    public WebServiceAnnotationProcessor(AnnotationProcessorEnvironment environment) {
        this.environment = environment;
    }

    public void process() {
        Messager messager = environment.getMessager();

        AnnotationTypeDeclaration annotationDeclaration = (AnnotationTypeDeclaration) environment
                .getTypeDeclaration("javax.jws.WebService"); //$NON-NLS-1$

        Collection<Declaration> annotatedTypes = environment
                .getDeclarationsAnnotatedWith(annotationDeclaration);

        for (Declaration declaration : annotatedTypes) {
            Collection<AnnotationMirror> annotationMirrors = declaration.getAnnotationMirrors();

            for (AnnotationMirror mirror : annotationMirrors) {
                Map<AnnotationTypeElementDeclaration, AnnotationValue> valueMap = mirror.getElementValues();
                Set<Map.Entry<AnnotationTypeElementDeclaration, AnnotationValue>> valueSet = valueMap
                        .entrySet();
                for (Map.Entry<AnnotationTypeElementDeclaration, AnnotationValue> annotationKeyValue : valueSet) {
                    AnnotationValue annotationValue = annotationKeyValue.getValue();
                    if (declaration instanceof InterfaceDeclarationImpl) {
                        if (annotationKeyValue.getKey().getSimpleName().equals("serviceName")) { //$NON-NLS-1$
                            messager.printError(mirror.getPosition(), CXFAptCoreMessages
                                    .WEBSERVICE_ANNOTATION_PROCESSOR_SERVICENAME_SEI_ERROR_MESSAGE);
                        } else if (annotationKeyValue.getKey().getSimpleName().equals("endpointInterface")) { //$NON-NLS-1$
                            messager.printError(mirror.getPosition(), CXFAptCoreMessages
                                    .WEBSERVICE_ANNOTATION_PROCESSOR_ENDPOINTINTERFACE_SEI_ERROR_MESSAGE);
                        } else if (annotationKeyValue.getKey().getSimpleName().equals("portName")) { //$NON-NLS-1$
                            messager.printError(mirror.getPosition(), CXFAptCoreMessages
                                    .WEBSERVICE_ANNOTATION_PROCESSOR_PORTNAME_SEI_ERROR_MESSAGE);
                        }
                    } else if (declaration instanceof ClassDeclarationImpl) {
                        if (annotationKeyValue.getKey().getSimpleName().equals("name")) { //$NON-NLS-1$
                            Object nameValue = annotationValue.getValue();
                            if (nameValue instanceof String) {
                            }
                        } else if (annotationKeyValue.getKey().getSimpleName().equals("targetNamespace")) { //$NON-NLS-1$
                            Object targetNamespaceValue = annotationValue.getValue();
                            if (targetNamespaceValue instanceof String) {
                            }
                        } else if (annotationKeyValue.getKey().getSimpleName().equals("serviceName")) { //$NON-NLS-1$
                            Object serviceNameValue = annotationValue.getValue();
                            if (serviceNameValue instanceof String) {
                            }
                        } else if (annotationKeyValue.getKey().getSimpleName().equals("wsdlLocation")) { //$NON-NLS-1$
                            Object wsdlLocationValue = annotationValue.getValue();
                            if (wsdlLocationValue instanceof String) {
                            }
                        } else if (annotationKeyValue.getKey().getSimpleName().equals("endpointInterface")) { //$NON-NLS-1$
                            Object endpointInterfaceValue = annotationValue.getValue();
                            if (endpointInterfaceValue instanceof String) {
                            }
                            checkWebMethods(messager);
                        } else if (annotationKeyValue.getKey().getSimpleName().equals("portName")) { //$NON-NLS-1$
                            Object portNameValue = annotationValue.getValue();
                            if (portNameValue instanceof String) {
                            }
                        }
                    }
                }
                checkWebServiceProvider(mirror, messager);
            }
        }
    }
    
    private void checkWebMethods(Messager messager) {
        AnnotationTypeDeclaration webMethodDeclaration = (AnnotationTypeDeclaration) environment
            .getTypeDeclaration("javax.jws.WebMethod"); //$NON-NLS-1$

        Collection<Declaration> webMethodAnnotatedTypes = environment
            .getDeclarationsAnnotatedWith(webMethodDeclaration);

        for (Declaration declaration : webMethodAnnotatedTypes) {
            Collection<AnnotationMirror> annotationMirrors = declaration.getAnnotationMirrors();
            for (AnnotationMirror mirror : annotationMirrors) {
                if (mirror.getAnnotationType().toString().equals(webMethodDeclaration
                        .getQualifiedName())) {
                    messager.printError(mirror.getPosition(), CXFAptCoreMessages
                     .WEBSERVICE_ANNOTATION_PROCESSOR_WEBSERVICE_ENPOINTINTERFACE_NO_WEBMETHOS_ERROR_MESSAGE); 
                }
            }
        }
    }

    private void checkWebServiceProvider(AnnotationMirror mirror, Messager messager) {
        if (mirror.getAnnotationType().toString().equals("javax.xml.ws.WebServiceProvider")) { //$NON-NLS-1$
            messager.printError(mirror.getPosition(), CXFAptCoreMessages
                    .WEBSERVICE_ANNOTATION_PROCESSOR_WEBSERVICE_WEBSERVICEPROVIDER_ERROR_MESSAGE);
        }
    }   
}
