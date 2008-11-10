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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.apt.core.util.AptPreferenceConstants;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;

/**
 * 
 * @author sclarke
 *
 */
public class JAXWSAnnotationProcessorFactory implements AnnotationProcessorFactory {
    private static List<String> ANNOTATIONS = new ArrayList<String>();
    
    static {
        ANNOTATIONS.add("javax.jws.WebService"); //$NON-NLS-1$
        ANNOTATIONS.add("javax.jws.WebMethod"); //$NON-NLS-1$
        ANNOTATIONS.add("javax.jws.Oneway"); //$NON-NLS-1$
        ANNOTATIONS.add("javax.jws.WebParam"); //$NON-NLS-1$
        ANNOTATIONS.add("javax.jws.WebResult"); //$NON-NLS-1$
        ANNOTATIONS.add("javax.jws.SOAPBinding"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.jws.HandlerChain"); //$NON-NLS-1$
        ANNOTATIONS.add("javax.xml.ws.ServiceMode"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.xml.ws.WebFault"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.xml.ws.WebServiceClient"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.xml.ws.WebEndpoint"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.xml.ws.WebServiceProvider"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.xml.ws.BindingType"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.xml.ws.WebServiceRef"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.xml.ws.WebServiceRefs"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.xml.ws.Action"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.xml.ws.FaultAction"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.xml.ws.spi.WebServiceFeatureAnnotation"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.xml.ws.soap.Addressing"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.xml.ws.soap.MTOM"); //$NON-NLS-1$
		ANNOTATIONS.add("javax.xml.ws.RespectBinding"); //$NON-NLS-1$
        ANNOTATIONS.add("javax.xml.ws.RequestWrapper"); //$NON-NLS-1$
        ANNOTATIONS.add("javax.xml.ws.ResponseWrapper"); //$NON-NLS-1$
    }
	
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> annotationSet,
            AnnotationProcessorEnvironment processorEnvironment) {
        
    	List<AnnotationProcessor> annotationProcessors = new ArrayList<AnnotationProcessor>();
        for (AnnotationTypeDeclaration annotationTypeDeclaration : annotationSet) {
         	if (annotationTypeDeclaration.getSimpleName().equals("WebService")) { //$NON-NLS-1$
         		annotationProcessors.add(new WebServiceAnnotationProcessor(processorEnvironment));	
        	} else if (annotationTypeDeclaration.getSimpleName().equals("WebMethod")) { //$NON-NLS-1$
        		annotationProcessors.add(new WebMethodAnnotationProcessor(processorEnvironment));	
        	} else if (annotationTypeDeclaration.getSimpleName().equals("WebParam")) { //$NON-NLS-1$
        		annotationProcessors.add(new WebParamAnnotationProcessor(processorEnvironment));	
        	} else if (annotationTypeDeclaration.getSimpleName().equals("RequestWrapper")) { //$NON-NLS-1$
        		annotationProcessors.add(new RequestWrapperAnnotationProcessor(processorEnvironment));	
			} else if (annotationTypeDeclaration.getSimpleName().equals("ResponseWrapper")) { //$NON-NLS-1$
				annotationProcessors.add(new ResponseWrapperAnnotationProcessor(processorEnvironment));	
			} else if (annotationTypeDeclaration.getSimpleName().equals("Oneway")) { //$NON-NLS-1$
                annotationProcessors.add(new OneWayAnnotationProcessor(processorEnvironment)); 
            }
        }
        return new JAXWSAnnotationProcessor(annotationProcessors);
    }

    public Collection<String> supportedAnnotationTypes() {
        return JAXWSAnnotationProcessorFactory.ANNOTATIONS;
    }

    public Collection<String> supportedOptions() {
        List<String> options = new ArrayList<String>();
        options.add(AptPreferenceConstants.PROCESSING_IN_EDITOR_DISABLED_OPTION);
//        options.add(AptPreferenceConstants.RTTG_ENABLED_OPTION);
        return options;
    }
}
