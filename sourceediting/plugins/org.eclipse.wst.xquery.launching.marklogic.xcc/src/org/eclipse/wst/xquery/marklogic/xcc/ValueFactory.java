/*
 * Copyright (c) 2003-2009 Mark Logic Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Mark Logic, Inc.
 */
package org.eclipse.wst.xquery.marklogic.xcc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Locale;
import java.util.TimeZone;

import org.eclipse.wst.xquery.marklogic.xcc.types.AtomicType;
import org.eclipse.wst.xquery.marklogic.xcc.types.Duration;
import org.eclipse.wst.xquery.marklogic.xcc.types.NodeType;
import org.eclipse.wst.xquery.marklogic.xcc.types.SequenceType;
import org.eclipse.wst.xquery.marklogic.xcc.types.ValueType;
import org.eclipse.wst.xquery.marklogic.xcc.types.XName;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSBoolean;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSDate;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSDateTime;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSDuration;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSInteger;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSString;
import org.eclipse.wst.xquery.marklogic.xcc.types.XSTime;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmAtomic;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmBinary;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmDuration;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmElement;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmItem;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmNode;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmSequence;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmText;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmValue;
import org.eclipse.wst.xquery.marklogic.xcc.types.XdmVariable;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.BinaryImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.ElementImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.SequenceImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.TextImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsAnyUriImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsBase64BinaryImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsBooleanImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsDateImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsDateTimeImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsDayTimeDurationImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsDecimalImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsDoubleImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsDurationImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsFloatImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsGDayImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsGMonthDayImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsGMonthImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsGYearImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsGYearMonthImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsHexBinaryImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsIntegerImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsQNameImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsStringImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsTimeImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsUntypedAtomicImpl;
import org.eclipse.wst.xquery.marklogic.xcc.types.impl.XsYearMonthDurationImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


/**
 * <p>
 * This class contains various static factory methods that return instances of {@link XdmValue} and
 * {@link XdmVariable}.
 * </p>
 */
public final class ValueFactory {
    private ValueFactory() {
        // Cannot be instantiated
    }

    /**
     * <p>
     * Generic {@link XdmValue} creation factory method. Value types are enumerated in
     * {@link ValueType}. Examples are {@link ValueType#XS_INTEGER}, {@link ValueType#XS_BOOLEAN},
     * {@link ValueType#SEQUENCE}, {@link ValueType#XS_STRING}, etc.
     * </p>
     * <p>
     * NOTE: If you pass a valueType of {@link ValueType#NODE}, it will be treated as
     * {@link ValueType#ELEMENT}. Using {@link ValueType#NODE} directly is discouraged, it is
     * defined as the common super-type for all node types. Other node types that may be constructed
     * are {@link ValueType#TEXT} and {@link ValueType#BINARY}. In future releases, creation of
     * additional node value types will be supported.
     * </p>
     * 
     * @param valueType
     *            An concrete subclass of {@link ValueType} which indicates the type of value to
     *            create.
     * @param value
     *            An {@link Object} containing the actual value to construct the object with. The
     *            specific class of this object is be dependent on the valueType argument. If the
     *            provided value is not consistent with the valueType then a
     *            {@link IllegalArgumentException} may be thrown.
     * @return An instance of {@link XdmValue}.
     * @throws IllegalArgumentException
     *             If the provided value is not consistent with the valueType.
     */
    public static XdmValue newValue(ValueType valueType, Object value) {
        if (valueType instanceof SequenceType) {
            return newSequenceValue(value);
        }

        if (valueType instanceof NodeType) {
            return newNodeValue(valueType, value);
        }

        if (valueType instanceof AtomicType) {
            return (newAtomicValue(valueType, value));
        }

        throw new IllegalArgumentException("Unrecognized ValueType: " + valueType);
    }

//	public static XdmValue newValue (Object value)
//	{
//		if (value == null) {
//			throw new IllegalArgumentException ("null parameter");
//		}
//
//		if (value instanceof Boolean) {
//			return newValue (ValueType.XS_BOOLEAN, value);
//		}
//
//		if ((value instanceof Double) || (value instanceof Float)) {
//			return newValue (ValueType.XS_DOUBLE, value);
//		}
//
//		if (value instanceof BigInteger) {
//			return newValue (ValueType.XS_DOUBLE, value);
//		}
//
//		if ((value instanceof Integer) || (value instanceof Long)
//			|| (value instanceof BigInteger)) {
//			return newValue (ValueType.XS_INTEGER, value);
//		}
//
//		// TODO: finish this and add tests
//
//		throw new IllegalArgumentException ("Unrecognized object type: " + value.getClass().getName());
//	}

    /**
     * <p>
     * A convenience method to construct an {@link XdmElement} value. {@link XdmElement} objects can
     * be constructed from an XML {@link String}, a W3C DOM {@link Element} or an
     * {@link InputStream}.
     * </p>
     * 
     * @param value
     *            An instance of {@link String}, {@link Element} or {@link InputStream}.
     * @return An instance of {@link XdmElement}.
     * @throws IllegalArgumentException
     *             If value is not a {@link String}, {@link Element} or {@link InputStream}.
     */
    public static XdmElement newElement(Object value) {
        if (value instanceof String) {
            return new ElementImpl((String)value);
        }

        if (value instanceof InputStream) {
            return new ElementImpl((InputStream)value);
        }

        if (value instanceof Element) {
            byte[] bytes = ContentFactory.bytesFromW3cNode((Element)value);

            return new ElementImpl(new ByteArrayInputStream(bytes));
        }

        throw new IllegalArgumentException("String, org.w3c.dom.Element or InputStream value required to construct "
                + ValueType.ELEMENT);
    }

    /**
     * A convenience method to construct an {@link XdmText} value. {@link XdmText} objects can be
     * constructed from an XML {@link String}, a W3C DOM {@link Text} node or an {@link InputStream}
     * .
     * 
     * @param value
     *            An instance of {@link String}, {@link Text} or {@link InputStream}.
     * @return An instance of {@link XdmText}.
     * @throws IllegalArgumentException
     *             If value is not a {@link String}, {@link Text} or {@link InputStream}.
     */
    public static XdmText newTextNode(Object value) {
        if (value instanceof String) {
            return new TextImpl((String)value);
        }

        if (value instanceof InputStream) {
            return new TextImpl((InputStream)value);
        }

        if (value instanceof Text) {
            Text text = (Text)value;

            return new TextImpl(text.getNodeValue());
        }

        throw new IllegalArgumentException("String, org.w3c.dom.Text or InputStream value required to construct "
                + ValueType.ELEMENT);
    }

    public static XdmBinary newBinaryNode(Object value) {
        if (value instanceof String) {
            byte[] bytes = null;

            try {
                bytes = ((String)value).getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                // unlikely to happen
                bytes = ((String)value).getBytes();
            }

            return new BinaryImpl(new ByteArrayInputStream(bytes), true);
        }

        if (value instanceof byte[]) {
            return new BinaryImpl(new ByteArrayInputStream((byte[])value), true);
        }

        if (value instanceof InputStream) {
            return new BinaryImpl((InputStream)value, true);
        }

        throw new IllegalArgumentException("String, org.w3c.dom.Text or InputStream value required to construct "
                + ValueType.ELEMENT);
    }

    /**
     * A convenience method to construct an {@link XSString} value.
     * 
     * @param value
     *            A String to construct the {@link XSString} object with.
     * @return An instance of {@link XSString}.
     */
    public static XSString newXSString(String value) {
        return (XSString)newValue(ValueType.XS_STRING, value);
    }

    /**
     * A convenience method to construct an {@link XSInteger} value. Note that an XQuery xs:integer
     * can hold values larger than a Java int or long.
     * 
     * @param value
     *            A long to construct the {@link XSInteger} object with.
     * @return An instance of {@link XSInteger}.
     */
    public static XSInteger newXSInteger(long value) {
        return (XSInteger)newValue(ValueType.XS_INTEGER, new Long(value));
    }

    /**
     * A convenience method to construct an {@link XSInteger} value. Note that an XQuery xs:integer
     * can hold values larger than a Java int or long, but these large value may be represented with
     * a {@link BigInteger} object.
     * 
     * @param value
     *            A {@link BigInteger} to construct the {@link XSInteger} object with.
     * @return An instance of {@link XSInteger}.
     */
    public static XSInteger newXSInteger(BigInteger value) {
        return (XSInteger)newValue(ValueType.XS_INTEGER, value);
    }

    /**
     * A convenience method to construct an {@link org.eclipse.wst.xquery.marklogic.xcc.types.XSBoolean} value.
     * 
     * @param value
     *            A boolean to construct the {@link org.eclipse.wst.xquery.marklogic.xcc.types.XSBoolean} object with.
     * @return An instance of {@link org.eclipse.wst.xquery.marklogic.xcc.types.XSBoolean}.
     */
    public static XSBoolean newXSBoolean(boolean value) {
        return new XsBooleanImpl(Boolean.valueOf(value));
    }

    /**
     * A convenience method to construct an {@link XSDateTime} value.
     * 
     * @param value
     *            A {@link String} representation of the date/time in standard XQuery form (ie
     *            2006-04-23T11:32:46).
     * @param timeZone
     *            A {@link TimeZone} object to apply to value, null for default.
     * @param locale
     *            A {@link Locale} object to apply to the value, null for default.
     * @return An instance of {@link XSDateTime}.
     */
    public static XSDateTime newXSDateTime(String value, TimeZone timeZone, Locale locale) {
        return new XsDateTimeImpl(value, timeZone, locale);
    }

    /**
     * A convenience method to construct an {@link XSDate} value.
     * 
     * @param value
     *            A {@link String} representation of the date/time in standard XQuery form (ie
     *            2006-04-23).
     * @param timeZone
     *            A {@link TimeZone} object to apply to value, null for default.
     * @param locale
     *            A {@link Locale} object to apply to the value, null for default.
     * @return An instance of {@link XSDate}.
     */
    public static XSDate newXSDate(String value, TimeZone timeZone, Locale locale) {
        return new XsDateImpl(value, timeZone, locale);
    }

    /**
     * A convenience method to construct an {@link XSTime} value.
     * 
     * @param value
     *            A {@link String} representation of the date/time in standard XQuery form (ie
     *            11:32:46).
     * @param timeZone
     *            A {@link TimeZone} object to apply to value, null for default.
     * @param locale
     *            A {@link Locale} object to apply to the value, null for default.
     * @return An instance of {@link XSDate}.
     */
    public static XSTime newXSTime(String value, TimeZone timeZone, Locale locale) {
        return new XsTimeImpl(value, timeZone, locale);
    }

    /**
     * A convenience method to construct an {@link XSDuration} value.
     * 
     * @param value
     *            A {@link String} representation of the duration (ie P2Y3M141DT12H46M12.34S).
     * @return An instance of {@link XSDuration}.
     */
    public static XSDuration newXSDuration(String value) {
        return new XsDurationImpl(value);
    }

    /**
     * A convenience method to construct an {@link XSDuration} value from an {@link XdmDuration}
     * object.
     * 
     * @param duration
     *            An instance XdmDuration
     * @return An instance of {@link XSDuration}.
     */
    public static XSDuration newXSDuration(XdmDuration duration) {
        return newXSDuration(duration.toString());
    }

    /**
     * Convenience method to construct an {@link XdmDuration} value.
     * 
     * @param serializedString
     *            A {@link String} representation of the duration (ie P2Y3M141DT12H46M12.34S).
     * @return An instance of {@link XdmDuration}.
     */
    public static XdmDuration newDuration(String serializedString) {
        return new Duration(serializedString);
    }

    // ------------------------------------------------------------

    /**
     * Factory method to construct an {@link XdmSequence} from an array of {@link XdmValue} objects.
     * 
     * @param values
     *            An array of {@link XdmValue} instances.
     * @return A new {@link XdmSequence} object.
     */
    public static XdmSequence<XdmItem> newSequence(XdmValue[] values) {
        return (new SequenceImpl(values));
    }

    // ------------------------------------------------------------

    /**
     * Factory method to create a variable (named value) from the given {@link XName} and
     * {@link XdmValue} objects.
     * 
     * @param name
     *            An {@link XName} that defines the name and (optional) namespace of the
     *            {@link XdmVariable}.
     * @param value
     *            An instance of {@link XdmValue} which is the value of the variable.
     * @return An instance of {@link XdmVariable} that encapsulates the name and value parameters.
     */
    public static XdmVariable newVariable(XName name, XdmValue value) {
        return new XdmVar(name, value);
    }

    // ------------------------------------------------------------

    private static XdmSequence<XdmItem> newSequenceValue(Object values) {
        if (!(values instanceof XdmValue[])) {
            throw new IllegalArgumentException("Value must be array of XdmValue");
        }

        return newSequence((XdmValue[])values);
    }

    private static XdmNode newNodeValue(ValueType valueType, Object value) {
        if (valueType == ValueType.ELEMENT) {
            return newElement(value);
        }

        if (valueType == ValueType.NODE) {
            return newElement(value);
        }

        if (valueType == ValueType.TEXT) {
            return newTextNode(value);
        }

        if (valueType == ValueType.BINARY) {
            return newBinaryNode(value);
        }

        throw new InternalError("Unrecognized valueType: " + valueType);
    }

    private static XdmAtomic newAtomicValue(ValueType valueType, Object value) {
        if (valueType == ValueType.XS_STRING) {
            assertStringArg(value, valueType);

            return new XsStringImpl((String)value);
        }

        if (valueType == ValueType.XS_INTEGER) {
            return new XsIntegerImpl(value);
        }

        if (valueType == ValueType.XS_DECIMAL) {
            return new XsDecimalImpl(value);
        }

        if (valueType == ValueType.XS_DOUBLE) {
            return new XsDoubleImpl(value);
        }

        if (valueType == ValueType.XS_FLOAT) {
            return new XsFloatImpl(value);
        }

        if (valueType == ValueType.XS_BOOLEAN) {
            if (value instanceof Boolean) {
                return new XsBooleanImpl((Boolean)value);
            } else if (value instanceof String) {
                return new XsBooleanImpl((String)value);
            }

            throw new IllegalArgumentException("Illegal value type (" + value.getClass()
                    + "), must be Boolean or String");
        }

        if (valueType == ValueType.XS_ANY_URI) {
            assertStringArg(value, valueType);

            return new XsAnyUriImpl((String)value);
        }

        if (valueType == ValueType.XS_QNAME) {
            assertStringArg(value, valueType);

            return new XsQNameImpl((String)value);
        }

        if (valueType == ValueType.XS_UNTYPED_ATOMIC) {
            assertStringArg(value, valueType);

            return new XsUntypedAtomicImpl((String)value);
        }

        if (valueType == ValueType.XS_DURATION) {
            if (value instanceof XdmDuration) {
                return new XsDurationImpl(value.toString());
            }

            assertStringArg(value, valueType);

            return new XsDurationImpl((String)value);
        }

        if (valueType == ValueType.XS_DAY_TIME_DURATION) {
            if (value instanceof XdmDuration) {
                return new XsDayTimeDurationImpl(value.toString());
            }

            assertStringArg(value, valueType);

            return new XsDayTimeDurationImpl((String)value);
        }

        if (valueType == ValueType.XS_YEAR_MONTH_DURATION) {
            if (value instanceof XdmDuration) {
                return new XsYearMonthDurationImpl(value.toString());
            }

            assertStringArg(value, valueType);

            return new XsYearMonthDurationImpl((String)value);
        }

        if (valueType == ValueType.XS_DATE_TIME) {
            // TODO: make constructor that takes a Date
//			if (value instanceof Date) {
//				return new XsDateTimeImpl ((Date) value);
//			}

            assertStringArg(value, valueType);

            return new XsDateTimeImpl((String)value, TimeZone.getDefault(), Locale.getDefault());
        }

        if (valueType == ValueType.XS_DATE) {
            // TODO: make constructor that takes a Date
//			if (value instanceof Date) {
//				return new XsDateImpl ((Date) value);
//			}

            assertStringArg(value, valueType);

            return new XsDateImpl((String)value, TimeZone.getDefault(), Locale.getDefault());
        }

        if (valueType == ValueType.XS_TIME) {
            // TODO: make constructor that takes a Date
//			if (value instanceof Date) {
//				return new XsTimeImpl ((Date) value);
//			}

            assertStringArg(value, valueType);

            return new XsTimeImpl((String)value, TimeZone.getDefault(), Locale.getDefault());
        }

        if (valueType == ValueType.XS_GDAY) {
            assertStringArg(value, valueType);

            return new XsGDayImpl((String)value, null, null);
        }

        if (valueType == ValueType.XS_GMONTH) {
            assertStringArg(value, valueType);

            return new XsGMonthImpl((String)value, null, null);
        }

        if (valueType == ValueType.XS_GMONTH_DAY) {
            assertStringArg(value, valueType);

            return new XsGMonthDayImpl((String)value, null, null);
        }

        if (valueType == ValueType.XS_GYEAR) {
            assertStringArg(value, valueType);

            return new XsGYearImpl((String)value, null, null);
        }

        if (valueType == ValueType.XS_GYEAR_MONTH) {
            assertStringArg(value, valueType);

            return new XsGYearMonthImpl((String)value, null, null);
        }

        if (valueType == ValueType.XS_HEX_BINARY) {
            // TODO: add byte array constructor
//			if (value instanceof byte []) {
//				return new XsHexBinaryImpl ((byte []) value);
//			}

            assertStringArg(value, valueType);

            return new XsHexBinaryImpl((String)value);
        }

        if (valueType == ValueType.XS_BASE64_BINARY) {
            // TODO: add byte array constructor
//			if (value instanceof byte []) {
//				return new XsHexBinaryImpl ((byte []) value);
//			}

            assertStringArg(value, valueType);

            return new XsBase64BinaryImpl((String)value);
        }

        throw new IllegalStateException("Unhandled type: " + valueType);
    }

    private static void assertStringArg(Object value, ValueType valueType) {
        if (value instanceof String) {
            return;
        }

        throw new IllegalArgumentException("String value required to construct " + valueType);
    }

    // ------------------------------------------------------------

    private static class XdmVar implements XdmVariable {
        private final XName name;
        private final XdmValue value;

        public XdmVar(XName name, XdmValue value) {
            this.name = name;
            this.value = value;
        }

        public XName getName() {
            return name;
        }

        public XdmValue getValue() {
            return value;
        }

        // --------------------------------------------

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof XdmVariable) {
                XdmVariable var = (XdmVariable)obj;

                return name.equals(var.getName());
            }

            return false;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }
}
