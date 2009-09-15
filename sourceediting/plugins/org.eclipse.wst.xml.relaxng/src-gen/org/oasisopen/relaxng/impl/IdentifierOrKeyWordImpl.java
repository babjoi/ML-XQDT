/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.oasisopen.relaxng.ExceptNameClass;
import org.oasisopen.relaxng.IdentifierOrKeyWord;
import org.oasisopen.relaxng.Inherit;
import org.oasisopen.relaxng.Literal;
import org.oasisopen.relaxng.Name;
import org.oasisopen.relaxng.NameClass;
import org.oasisopen.relaxng.RelaxngPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Identifier Or Key Word</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.impl.IdentifierOrKeyWordImpl#getExceptClassName <em>Except Class Name</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.IdentifierOrKeyWordImpl#getNc <em>Nc</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.IdentifierOrKeyWordImpl#getParmValue <em>Parm Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IdentifierOrKeyWordImpl extends ParamImpl implements IdentifierOrKeyWord
{
  /**
   * The cached value of the '{@link #getExceptClassName() <em>Except Class Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExceptClassName()
   * @generated
   * @ordered
   */
  protected ExceptNameClass exceptClassName;

  /**
   * The cached value of the '{@link #getNc() <em>Nc</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNc()
   * @generated
   * @ordered
   */
  protected NameClass nc;

  /**
   * The cached value of the '{@link #getParmValue() <em>Parm Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParmValue()
   * @generated
   * @ordered
   */
  protected Literal parmValue;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IdentifierOrKeyWordImpl()
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
    return RelaxngPackage.Literals.IDENTIFIER_OR_KEY_WORD;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExceptNameClass getExceptClassName()
  {
    return exceptClassName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExceptClassName(ExceptNameClass newExceptClassName, NotificationChain msgs)
  {
    ExceptNameClass oldExceptClassName = exceptClassName;
    exceptClassName = newExceptClassName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME, oldExceptClassName, newExceptClassName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExceptClassName(ExceptNameClass newExceptClassName)
  {
    if (newExceptClassName != exceptClassName)
    {
      NotificationChain msgs = null;
      if (exceptClassName != null)
        msgs = ((InternalEObject)exceptClassName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME, null, msgs);
      if (newExceptClassName != null)
        msgs = ((InternalEObject)newExceptClassName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME, null, msgs);
      msgs = basicSetExceptClassName(newExceptClassName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME, newExceptClassName, newExceptClassName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NameClass getNc()
  {
    return nc;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetNc(NameClass newNc, NotificationChain msgs)
  {
    NameClass oldNc = nc;
    nc = newNc;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.IDENTIFIER_OR_KEY_WORD__NC, oldNc, newNc);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNc(NameClass newNc)
  {
    if (newNc != nc)
    {
      NotificationChain msgs = null;
      if (nc != null)
        msgs = ((InternalEObject)nc).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.IDENTIFIER_OR_KEY_WORD__NC, null, msgs);
      if (newNc != null)
        msgs = ((InternalEObject)newNc).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.IDENTIFIER_OR_KEY_WORD__NC, null, msgs);
      msgs = basicSetNc(newNc, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.IDENTIFIER_OR_KEY_WORD__NC, newNc, newNc));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Literal getParmValue()
  {
    return parmValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParmValue(Literal newParmValue, NotificationChain msgs)
  {
    Literal oldParmValue = parmValue;
    parmValue = newParmValue;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.IDENTIFIER_OR_KEY_WORD__PARM_VALUE, oldParmValue, newParmValue);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParmValue(Literal newParmValue)
  {
    if (newParmValue != parmValue)
    {
      NotificationChain msgs = null;
      if (parmValue != null)
        msgs = ((InternalEObject)parmValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.IDENTIFIER_OR_KEY_WORD__PARM_VALUE, null, msgs);
      if (newParmValue != null)
        msgs = ((InternalEObject)newParmValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.IDENTIFIER_OR_KEY_WORD__PARM_VALUE, null, msgs);
      msgs = basicSetParmValue(newParmValue, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.IDENTIFIER_OR_KEY_WORD__PARM_VALUE, newParmValue, newParmValue));
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
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME:
        return basicSetExceptClassName(null, msgs);
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__NC:
        return basicSetNc(null, msgs);
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__PARM_VALUE:
        return basicSetParmValue(null, msgs);
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
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME:
        return getExceptClassName();
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__NC:
        return getNc();
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__PARM_VALUE:
        return getParmValue();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME:
        setExceptClassName((ExceptNameClass)newValue);
        return;
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__NC:
        setNc((NameClass)newValue);
        return;
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__PARM_VALUE:
        setParmValue((Literal)newValue);
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
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME:
        setExceptClassName((ExceptNameClass)null);
        return;
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__NC:
        setNc((NameClass)null);
        return;
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__PARM_VALUE:
        setParmValue((Literal)null);
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
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME:
        return exceptClassName != null;
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__NC:
        return nc != null;
      case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__PARM_VALUE:
        return parmValue != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
  {
    if (baseClass == ExceptNameClass.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == NameClass.class)
    {
      switch (derivedFeatureID)
      {
        case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME: return RelaxngPackage.NAME_CLASS__EXCEPT_CLASS_NAME;
        case RelaxngPackage.IDENTIFIER_OR_KEY_WORD__NC: return RelaxngPackage.NAME_CLASS__NC;
        default: return -1;
      }
    }
    if (baseClass == Name.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == Inherit.class)
    {
      switch (derivedFeatureID)
      {
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
  {
    if (baseClass == ExceptNameClass.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == NameClass.class)
    {
      switch (baseFeatureID)
      {
        case RelaxngPackage.NAME_CLASS__EXCEPT_CLASS_NAME: return RelaxngPackage.IDENTIFIER_OR_KEY_WORD__EXCEPT_CLASS_NAME;
        case RelaxngPackage.NAME_CLASS__NC: return RelaxngPackage.IDENTIFIER_OR_KEY_WORD__NC;
        default: return -1;
      }
    }
    if (baseClass == Name.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    if (baseClass == Inherit.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

} //IdentifierOrKeyWordImpl
