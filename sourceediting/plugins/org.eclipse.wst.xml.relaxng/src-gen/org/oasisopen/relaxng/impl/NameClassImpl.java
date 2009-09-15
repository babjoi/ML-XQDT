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
import org.oasisopen.relaxng.NameClass;
import org.oasisopen.relaxng.RelaxngPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Name Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.impl.NameClassImpl#getExceptClassName <em>Except Class Name</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.NameClassImpl#getNc <em>Nc</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NameClassImpl extends ExceptNameClassImpl implements NameClass
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NameClassImpl()
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
    return RelaxngPackage.Literals.NAME_CLASS;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.NAME_CLASS__EXCEPT_CLASS_NAME, oldExceptClassName, newExceptClassName);
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
        msgs = ((InternalEObject)exceptClassName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.NAME_CLASS__EXCEPT_CLASS_NAME, null, msgs);
      if (newExceptClassName != null)
        msgs = ((InternalEObject)newExceptClassName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.NAME_CLASS__EXCEPT_CLASS_NAME, null, msgs);
      msgs = basicSetExceptClassName(newExceptClassName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.NAME_CLASS__EXCEPT_CLASS_NAME, newExceptClassName, newExceptClassName));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.NAME_CLASS__NC, oldNc, newNc);
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
        msgs = ((InternalEObject)nc).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.NAME_CLASS__NC, null, msgs);
      if (newNc != null)
        msgs = ((InternalEObject)newNc).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.NAME_CLASS__NC, null, msgs);
      msgs = basicSetNc(newNc, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.NAME_CLASS__NC, newNc, newNc));
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
      case RelaxngPackage.NAME_CLASS__EXCEPT_CLASS_NAME:
        return basicSetExceptClassName(null, msgs);
      case RelaxngPackage.NAME_CLASS__NC:
        return basicSetNc(null, msgs);
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
      case RelaxngPackage.NAME_CLASS__EXCEPT_CLASS_NAME:
        return getExceptClassName();
      case RelaxngPackage.NAME_CLASS__NC:
        return getNc();
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
      case RelaxngPackage.NAME_CLASS__EXCEPT_CLASS_NAME:
        setExceptClassName((ExceptNameClass)newValue);
        return;
      case RelaxngPackage.NAME_CLASS__NC:
        setNc((NameClass)newValue);
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
      case RelaxngPackage.NAME_CLASS__EXCEPT_CLASS_NAME:
        setExceptClassName((ExceptNameClass)null);
        return;
      case RelaxngPackage.NAME_CLASS__NC:
        setNc((NameClass)null);
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
      case RelaxngPackage.NAME_CLASS__EXCEPT_CLASS_NAME:
        return exceptClassName != null;
      case RelaxngPackage.NAME_CLASS__NC:
        return nc != null;
    }
    return super.eIsSet(featureID);
  }

} //NameClassImpl
