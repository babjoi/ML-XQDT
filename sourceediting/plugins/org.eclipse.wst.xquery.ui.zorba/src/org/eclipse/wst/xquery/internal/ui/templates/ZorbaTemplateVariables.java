package org.eclipse.wst.xquery.internal.ui.templates;

import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateVariableResolver;

public class ZorbaTemplateVariables {

    public static class CollectionProperties extends TemplateVariableResolver {

        public CollectionProperties() {
            super("collection_properties", "Zorba DDL: collection properties");
        }

        protected String[] resolveAll(TemplateContext context) {
            return ((ZorbaTemplateContext)context).getCollectionProperties();
        }
    }

    public static class NodeModifier extends TemplateVariableResolver {

        public NodeModifier() {
            super("node_modifier", "Zorba DDL: collection node modifiers");
        }

        protected String[] resolveAll(TemplateContext context) {
            return ((ZorbaTemplateContext)context).getNodeModifiers();
        }
    }

    public static class IndexProperties extends TemplateVariableResolver {

        public IndexProperties() {
            super("index_properties", "Zorba DDL: index properties");
        }

        protected String[] resolveAll(TemplateContext context) {
            return ((ZorbaTemplateContext)context).getIndexProperties();
        }
    }

}