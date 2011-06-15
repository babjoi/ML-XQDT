package org.eclipse.wst.xquery.debug.core.model;

import org.eclipse.debug.core.DebugException;
import org.eclipse.dltk.debug.core.model.CollectionScriptType;
import org.eclipse.dltk.debug.core.model.IScriptValue;

public class XQDTSequenceType extends CollectionScriptType {

    public XQDTSequenceType(String name) {
        super(name);
    }

    @Override
    public String formatValue(IScriptValue value) {
        String result = "";
        try {
            if (!value.hasVariables()) {
                result = value.getRawValue();
            }
        } catch (DebugException de) {
            de.printStackTrace();
        }
        return result;
    }

    @Override
    public String formatDetails(IScriptValue value) {
        String result = "";
        try {
            if (!value.hasVariables()) {
                result = value.getRawValue();
            }
        } catch (DebugException de) {
            de.printStackTrace();
        }

        return result;
    }
}
