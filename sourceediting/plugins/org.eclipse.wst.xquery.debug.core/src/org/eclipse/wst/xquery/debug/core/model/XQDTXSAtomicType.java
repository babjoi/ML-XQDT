package org.eclipse.wst.xquery.debug.core.model;

import org.eclipse.dltk.debug.core.model.AtomicScriptType;
import org.eclipse.dltk.debug.core.model.IScriptValue;

public class XQDTXSAtomicType extends AtomicScriptType {

    public XQDTXSAtomicType(String name) {
        super(name);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    @Override
    public String formatDetails(IScriptValue value) {
        // TODO Auto-generated method stub
        return super.formatDetails(value);
    }

    @Override
    public String formatValue(IScriptValue value) {
        // TODO Auto-generated method stub
        return super.formatValue(value);
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }

    @Override
    public boolean isAtomic() {
        // TODO Auto-generated method stub
        return super.isAtomic();
    }

    @Override
    public boolean isCollection() {
        // TODO Auto-generated method stub
        return super.isCollection();
    }

    @Override
    public boolean isComplex() {
        // TODO Auto-generated method stub
        return super.isComplex();
    }

    @Override
    public boolean isString() {
        // TODO Auto-generated method stub
        return super.isString();
    }

}
