package org.eclipse.wst.xquery.set.launching;

import java.util.Map;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.dltk.launching.process.ScriptRuntimeProcessFactory;

public class SETRuntimeProcessFactory extends ScriptRuntimeProcessFactory {

    public static final String PROCESS_FACTORY_ID = "org.eclipse.wst.xquery.set.launching.processFactory"; //$NON-NLS-1$

    @SuppressWarnings("unchecked")
    @Override
    public IProcess newProcess(ILaunch launch, Process process, String label, Map attributes) {
        return new SETRuntimeProcess(launch, process, label, attributes);
    }
}
