package org.eclipse.wst.xquery.internal.ui.templates;

import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateVariableResolver;

public class ZorbaTemplateVariables {

    public static class CollectionProperties extends TemplateVariableResolver {

        public CollectionProperties() {
            super("collection_properties", "Zorba DDF: collection properties");
        }

        protected String[] resolveAll(TemplateContext context) {
            return ((ZorbaTemplateContext)context).getCollectionProperties();
        }
    }

    public static class IndexProperties extends TemplateVariableResolver {

        public IndexProperties() {
            super("index_properties", "Zorba DDF: index properties");
        }

        protected String[] resolveAll(TemplateContext context) {
            return ((ZorbaTemplateContext)context).getIndexProperties();
        }
    }

}