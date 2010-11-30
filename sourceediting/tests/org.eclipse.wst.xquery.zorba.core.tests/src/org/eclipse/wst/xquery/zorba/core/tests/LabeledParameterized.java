package org.eclipse.wst.xquery.zorba.core.tests;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.Suite;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;

public class LabeledParameterized extends Suite {

    /**
     * Annotation for a method which provides parameters to be injected into the test class
     * constructor by <code>LabeledParameterized</code>
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public static @interface LabeledParameters {
    }

    private final ArrayList<Runner> runners = new ArrayList<Runner>();

    private class TestClassRunnerForLabeledParameters extends BlockJUnit4ClassRunner {
        private final String fParameterSetName;

        private final Object[] fParameterList;

        TestClassRunnerForLabeledParameters(Class<?> type, String setName, Object[] parameterList)
                throws InitializationError {
            super(type);
            fParameterSetName = setName;
            fParameterList = parameterList;
        }

        @Override
        public Object createTest() throws Exception {
            return getTestClass().getOnlyConstructor().newInstance(computeParams());
        }

        private Object[] computeParams() throws Exception {
            try {
                return fParameterList;
            } catch (ClassCastException e) {
                throw new Exception(String.format("%s.%s() must return a Collection of arrays.", getTestClass()
                        .getName(), getParametersMethod(getTestClass()).getName()));
            }
        }

        @Override
        protected String getName() {
            return fParameterSetName;
        }

        @Override
        protected String testName(final FrameworkMethod method) {
            return fParameterSetName + "#" + method.getName();
        }

        @Override
        protected void validateConstructor(List<Throwable> errors) {
            validateOnlyOneConstructor(errors);
        }

        @Override
        protected Statement classBlock(RunNotifier notifier) {
            return childrenInvoker(notifier);
        }
    }

    public LabeledParameterized(Class<?> klass) throws Throwable {
        super(klass, Collections.<Runner> emptyList());
        Map<String, Object[]> parametersList = getParametersList(getTestClass());
        Set<String> sets = parametersList.keySet();
        for (String name : sets) {
            runners.add(new TestClassRunnerForLabeledParameters(getTestClass().getJavaClass(), name, parametersList
                    .get(name)));
        }
    }

    @Override
    protected List<Runner> getChildren() {
        return runners;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object[]> getParametersList(TestClass klass) throws Throwable {
        return (Map<String, Object[]>)getParametersMethod(klass).invokeExplosively(null);
    }

    private FrameworkMethod getParametersMethod(TestClass testClass) throws Exception {
        List<FrameworkMethod> methods = testClass.getAnnotatedMethods(LabeledParameters.class);
        for (FrameworkMethod each : methods) {
            int modifiers = each.getMethod().getModifiers();
            if (Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers)) {
                return each;
            }
        }

        throw new Exception("No public static parameters method on class " + testClass.getName());
    }

}
