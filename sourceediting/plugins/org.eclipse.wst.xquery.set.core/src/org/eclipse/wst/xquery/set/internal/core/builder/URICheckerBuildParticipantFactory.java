package org.eclipse.wst.xquery.set.internal.core.builder;

import java.net.URI;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantFactory;
import org.eclipse.wst.xquery.set.core.SETProjectConfig;
import org.eclipse.wst.xquery.set.core.SETProjectConfigUtil;

public class URICheckerBuildParticipantFactory implements IBuildParticipantFactory {

    public IBuildParticipant createBuildParticipant(IScriptProject project) throws CoreException {
        SETProjectConfig projectConfig = SETProjectConfigUtil.readProjectConfig(project.getProject());
        URI logicalURI = projectConfig.getLogicalUri();
        return new SETURICheckerParticipant(logicalURI);
    }

}
