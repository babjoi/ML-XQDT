package org.eclipse.wst.xquery.set.internal.launching.variables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.variables.IDynamicVariable;
import org.eclipse.core.variables.IDynamicVariableResolver;
import org.eclipse.core.variables.VariablesPlugin;

public class CoreSdkVersionResolver implements IDynamicVariableResolver {

    private static final String VERSION_PREFIX = "Sausalito Core SDK, (ver. ";

    public static final String VARIABLE = "sdk_version";

    public String resolveValue(IDynamicVariable variable, String argument) throws CoreException {
        String result = "";
        String coreSdk = VariablesPlugin.getDefault().getStringVariableManager()
                .performStringSubstitution("${" + CoreSdkLocationResolver.VARIABLE + "}");
        if (coreSdk == null) {
            return "";
        }

        IPath scriptPath = new Path(coreSdk).append("bin").append("sausalito");
        if (Platform.getOS().equals(Platform.OS_WIN32)) {
            scriptPath = scriptPath.addFileExtension("bat");
        }

        ProcessBuilder pb = new ProcessBuilder(scriptPath.toOSString());
        try {
            final Process p = pb.start();
            final List<String> lines = new ArrayList<String>();
            Thread reader = new Thread(new Runnable() {
                public void run() {
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    try {
                        String line = br.readLine();
                        while (line != null) {
                            lines.add(line + "\n");
                            line = br.readLine();
                        }
                    } catch (IOException e) {
                    }
                }
            });
            reader.start();

            p.waitFor();

            String version = null;
            for (String line : lines) {
                if (line.startsWith(VERSION_PREFIX)) {
                    version = line.substring(VERSION_PREFIX.length(), line.lastIndexOf(')'));
                    break;
                }
            }
            if (version != null) {
                result = version;
            }

        } catch (Exception e) {
        }

        return result;
    }
}
