/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.oasisopen.relaxng.AnyURILiteral;
import org.oasisopen.relaxng.DataTypeValue;
import org.oasisopen.relaxng.ExceptPattern;
import org.oasisopen.relaxng.GrammarContent;
import org.oasisopen.relaxng.Inherit;
import org.oasisopen.relaxng.Param;
import org.oasisopen.relaxng.Pattern;
import org.oasisopen.relaxng.RelaxngPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pattern</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.impl.PatternImpl#getVal <em>Val</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.PatternImpl#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.PatternImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.PatternImpl#getParam <em>Param</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.PatternImpl#getExceptPattern <em>Except Pattern</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.PatternImpl#getUri <em>Uri</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.PatternImpl#getInherit <em>Inherit</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.PatternImpl#getGrammarContent <em>Grammar Content</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.PatternImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.PatternImpl#getContinue <em>Continue</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PatternImpl extends ExceptPatternImpl implements Pattern
{
  /**
   * The cached value of the '{@link #getVal() <em>Val</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVal()
   * @generated
   * @ordered
   */
  protected EObject val;

  /**
   * The cached value of the '{@link #getPattern() <em>Pattern</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPattern()
   * @generated
   * @ordered
   */
  protected EList<Pattern> pattern;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected DataTypeValue value;

  /**
   * The cached value of the '{@link #getParam() <em>Param</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParam()
   * @generated
   * @ordered
   */
  protected EList<Param> param;

  /**
   * The cached value of the '{@link #getExceptPattern() <em>Except Pattern</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExceptPattern()
   * @generated
   * @ordered
   */
  protected ExceptPattern exceptPattern;

  /**
   * The cached value of the '{@link #getUri() <em>Uri</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUri()
   * @generated
   * @ordered
   */
  protected AnyURILiteral uri;

  /**
   * The cached value of the '{@link #getInherit() <em>Inherit</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInherit()
   * @generated
   * @ordered
   */
  protected Inherit inherit;

  /**
   * The cached value of the '{@link #getGrammarContent() <em>Grammar Content</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGrammarContent()
   * @generated
   * @ordered
   */
  protected EList<GrammarContent> grammarContent;

  /**
   * The cached value of the '{@link #getGroup() <em>Group</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroup()
   * @generated
   * @ordered
   */
  protected EList<Pattern> group;

  /**
   * The default value of the '{@link #getContinue() <em>Continue</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContinue()
   * @generated
   * @ordered
   */
  protected static final String CONTINUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getContinue() <em>Continue</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContinue()
   * @generated
   * @ordered
   */
  protected String continue_ = CONTINUE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PatternImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return RelaxngPackage.Literals.PATTERN;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getVal()
  {
    return val;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVal(EObject newVal, NotificationChain msgs)
  {
    EObject oldVal = val;
    val = newVal;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.PATTERN__VAL, oldVal, newVal);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVal(EObject newVal)
  {
    if (newVal != val)
    {
      NotificationChain msgs = null;
      if (val != null)
        msgs = ((InternalEObject)val).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.PATTERN__VAL, null, msgs);
      if (newVal != null)
        msgs = ((InternalEObject)newVal).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.PATTERN__VAL, null, msgs);
      msgs = basicSetVal(newVal, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.PATTERN__VAL, newVal, newVal));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Pattern> getPattern()
  {
    if (pattern == null)
    {
      pattern = new EObjectContainmentEList<Pattern>(Pattern.class, this, RelaxngPackage.PATTERN__PATTERN);
    }
    return pattern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataTypeValue getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetValue(DataTypeValue newValue, NotificationChain msgs)
  {
    DataTypeValue oldValue = value;
    value = newValue;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.PATTERN__VALUE, oldValue, newValue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(DataTypeValue newValue)
  {
    if (newValue != value)
    {
      NotificationChain msgs = null;
      if (value != null)
        msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.PATTERN__VALUE, null, msgs);
      if (newValue != null)
        msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.PATTERN__VALUE, null, msgs);
      msgs = basicSetValue(newValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.PATTERN__VALUE, newValue, newValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Param> getParam()
  {
    if (param == null)
    {
      param = new EObjectContainmentEList<Param>(Param.class, this, RelaxngPackage.PATTERN__PARAM);
    }
    return param;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExceptPattern getExceptPattern()
  {
    return exceptPattern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExceptPattern(ExceptPattern newExceptPattern, NotificationChain msgs)
  {
    ExceptPattern oldExceptPattern = exceptPattern;
    exceptPattern = newExceptPattern;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.PATTERN__EXCEPT_PATTERN, oldExceptPattern, newExceptPattern);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExceptPattern(ExceptPattern newExceptPattern)
  {
    if (newExceptPattern != exceptPattern)
    {
      NotificationChain msgs = null;
      if (exceptPattern != null)
        msgs = ((InternalEObject)exceptPattern).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.PATTERN__EXCEPT_PATTERN, null, msgs);
      if (newExceptPattern != null)
        msgs = ((InternalEObject)newExceptPattern).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.PATTERN__EXCEPT_PATTERN, null, msgs);
      msgs = basicSetExceptPattern(newExceptPattern, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.PATTERN__EXCEPT_PATTERN, newExceptPattern, newExceptPattern));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnyURILiteral getUri()
  {
    return uri;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUri(AnyURILiteral newUri, NotificationChain msgs)
  {
    AnyURILiteral oldUri = uri;
    uri = newUri;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.PATTERN__URI, oldUri, newUri);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUri(AnyURILiteral newUri)
  {
    if (newUri != uri)
    {
      NotificationChain msgs = null;
      if (uri != null)
        msgs = ((InternalEObject)uri).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.PATTERN__URI, null, msgs);
      if (newUri != null)
        msgs = ((InternalEObject)newUri).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.PATTERN__URI, null, msgs);
      msgs = basicSetUri(newUri, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.PATTERN__URI, newUri, newUri));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Inherit getInherit()
  {
    return inherit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInherit(Inherit newInherit, NotificationChain msgs)
  {
    Inherit oldInherit = inherit;
    inherit = newInherit;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.PATTERN__INHERIT, oldInherit, newInherit);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInherit(Inherit newInherit)
  {
    if (newInherit != inherit)
    {
      NotificationChain msgs = null;
      if (inherit != null)
        msgs = ((InternalEObject)inherit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.PATTERN__INHERIT, null, msgs);
      if (newInherit != null)
        msgs = ((InternalEObject)newInherit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.PATTERN__INHERIT, null, msgs);
      msgs = basicSetInherit(newInherit, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.PATTERN__INHERIT, newInherit, newInherit));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GrammarContent> getGrammarContent()
  {
    if (grammarContent == null)
    {
      grammarContent = new EObjectContainmentEList<GrammarContent>(GrammarContent.class, this, RelaxngPackage.PATTERN__GRAMMAR_CONTENT);
    }
    return grammarContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Pattern> getGroup()
  {
    if (group == null)
    {
      group = new EObjectContainmentEList<Pattern>(Pattern.class, this, RelaxngPackage.PATTERN__GROUP);
    }
    return group;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getContinue()
  {
    return continue_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContinue(String newContinue)
  {
    String oldContinue = continue_;
    continue_ = newContinue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.PATTERN__CONTINUE, oldContinue, continue_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case RelaxngPackage.PATTERN__VAL:
        return basicSetVal(null, msgs);
      case RelaxngPackage.PATTERN__PATTERN:
        return ((InternalEList<?>)getPattern()).basicRemove(otherEnd, msgs);
      case RelaxngPackage.PATTERN__VALUE:
        return basicSetValue(null, msgs);
      case RelaxngPackage.PATTERN__PARAM:
        return ((InternalEList<?>)getParam()).basicRemove(otherEnd, msgs);
      case RelaxngPackage.PATTERN__EXCEPT_PATTERN:
        return basicSetExceptPattern(null, msgs);
      case RelaxngPackage.PATTERN__URI:
        return basicSetUri(null, msgs);
      case RelaxngPackage.PATTERN__INHERIT:
        return basicSetInherit(null, msgs);
      case RelaxngPackage.PATTERN__GRAMMAR_CONTENT:
        return ((InternalEList<?>)getGrammarContent()).basicRemove(otherEnd, msgs);
      case RelaxngPackage.PATTERN__GROUP:
        return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case RelaxngPackage.PATTERN__VAL:
        return getVal();
      case RelaxngPackage.PATTERN__PATTERN:
        return getPattern();
      case RelaxngPackage.PATTERN__VALUE:
        return getValue();
      case RelaxngPackage.PATTERN__PARAM:
        return getParam();
      case RelaxngPackage.PATTERN__EXCEPT_PATTERN:
        return getExceptPattern();
      case RelaxngPackage.PATTERN__URI:
        return getUri();
      case RelaxngPackage.PATTERN__INHERIT:
        return getInherit();
      case RelaxngPackage.PATTERN__GRAMMAR_CONTENT:
        return getGrammarContent();
      case RelaxngPackage.PATTERN__GROUP:
        return getGroup();
      case RelaxngPackage.PATTERN__CONTINUE:
        return getContinue();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case RelaxngPackage.PATTERN__VAL:
        setVal((EObject)newValue);
        return;
      case RelaxngPackage.PATTERN__PATTERN:
        getPattern().clear();
        getPattern().addAll((Collection<? extends Pattern>)newValue);
        return;
      case RelaxngPackage.PATTERN__VALUE:
        setValue((DataTypeValue)newValue);
        return;
      case RelaxngPackage.PATTERN__PARAM:
        getParam().clear();
        getParam().addAll((Collection<? extends Param>)newValue);
        return;
      case RelaxngPackage.PATTERN__EXCEPT_PATTERN:
        setExceptPattern((ExceptPattern)newValue);
        return;
      case RelaxngPackage.PATTERN__URI:
        setUri((AnyURILiteral)newValue);
        return;
      case RelaxngPackage.PATTERN__INHERIT:
        setInherit((Inherit)newValue);
        return;
      case RelaxngPackage.PATTERN__GRAMMAR_CONTENT:
        getGrammarContent().clear();
        getGrammarContent().addAll((Collection<? extends GrammarContent>)newValue);
        return;
      case RelaxngPackage.PATTERN__GROUP:
        getGroup().clear();
        getGroup().addAll((Collection<? extends Pattern>)newValue);
        return;
      case RelaxngPackage.PATTERN__CONTINUE:
        setContinue((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case RelaxngPackage.PATTERN__VAL:
        setVal((EObject)null);
        return;
      case RelaxngPackage.PATTERN__PATTERN:
        getPattern().clear();
        return;
      case RelaxngPackage.PATTERN__VALUE:
        setValue((DataTypeValue)null);
        return;
      case RelaxngPackage.PATTERN__PARAM:
        getParam().clear();
        return;
      case RelaxngPackage.PATTERN__EXCEPT_PATTERN:
        setExceptPattern((ExceptPattern)null);
        return;
      case RelaxngPackage.PATTERN__URI:
        setUri((AnyURILiteral)null);
        return;
      case RelaxngPackage.PATTERN__INHERIT:
        setInherit((Inherit)null);
        return;
      case RelaxngPackage.PATTERN__GRAMMAR_CONTENT:
        getGrammarContent().clear();
        return;
      case RelaxngPackage.PATTERN__GROUP:
        getGroup().clear();
        return;
      case RelaxngPackage.PATTERN__CONTINUE:
        setContinue(CONTINUE_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case RelaxngPackage.PATTERN__VAL:
        return val != null;
      case RelaxngPackage.PATTERN__PATTERN:
        return pattern != null && !pattern.isEmpty();
      case RelaxngPackage.PATTERN__VALUE:
        return value != null;
      case RelaxngPackage.PATTERN__PARAM:
        return param != null && !param.isEmpty();
      case RelaxngPackage.PATTERN__EXCEPT_PATTERN:
        return exceptPattern != null;
      case RelaxngPackage.PATTERN__URI:
        return uri != null;
      case RelaxngPackage.PATTERN__INHERIT:
        return inherit != null;
      case RelaxngPackage.PATTERN__GRAMMAR_CONTENT:
        return grammarContent != null && !grammarContent.isEmpty();
      case RelaxngPackage.PATTERN__GROUP:
        return group != null && !group.isEmpty();
      case RelaxngPackage.PATTERN__CONTINUE:
        return CONTINUE_EDEFAULT == null ? continue_ != null : !CONTINUE_EDEFAULT.equals(continue_);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (continue: ");
    result.append(continue_);
    result.append(')');
    return result.toString();
  }

} //PatternImpl
