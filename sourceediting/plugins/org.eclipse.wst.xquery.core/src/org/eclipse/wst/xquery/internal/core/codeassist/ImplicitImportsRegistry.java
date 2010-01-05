package org.eclipse.wst.xquery.internal.core.codeassist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dltk.core.IScriptProject;

public class ImplicitImportsRegistry {

    private static Map<IScriptProject, List<ImplicitImport>> fImplicitImports = new HashMap<IScriptProject, List<ImplicitImport>>();

    public static List<ImplicitImport> getImplicitImports(IScriptProject project) {
        List<ImplicitImport> result = fImplicitImports.get(project);
        if (result == null) {
            result = readImplicitImports(project);
            fImplicitImports.put(project, result);
        }

        return result;
    }

    private static List<ImplicitImport> readImplicitImports(IScriptProject project) {
        List<ImplicitImport> result = new ArrayList<ImplicitImport>();

        IExtensionPoint extPoint = Platform.getExtensionRegistry().getExtensionPoint(
                "org.eclipse.wst.xquery.launching.implicitImports");
        if (extPoint != null) {
            IExtension[] extensions = extPoint.getExtensions();
            for (IExtension extension : extensions) {
                try {
                    IConfigurationElement[] prefixGroups = extension.getConfigurationElements();
                    for (IConfigurationElement prefixGroup : prefixGroups) {
                        IImplicitImportActivator activator = (IImplicitImportActivator)prefixGroup
                                .createExecutableExtension("activator");
                        if (!activator.activateForProject(project)) {
                            continue;
                        }

                        IConfigurationElement[] prefixes = prefixGroup.getChildren("prefix");
                        for (IConfigurationElement prefix : prefixes) {
                            String name = prefix.getAttribute("name");
                            if (name == null) {
                                continue;
                            }
                            String path = prefix.getAttribute("path");
                            if (path == null) {
                                continue;
                            }
                            result.add(new ImplicitImport(name, path));
                        }
                    }

                } catch (InvalidRegistryObjectException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (CoreException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
