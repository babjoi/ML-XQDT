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
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.oasisopen.relaxng.AnyURILiteral;
import org.oasisopen.relaxng.IncludeContent;
import org.oasisopen.relaxng.Inherit;
import org.oasisopen.relaxng.RelaxngPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Any URI Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.impl.AnyURILiteralImpl#getInherit <em>Inherit</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.AnyURILiteralImpl#getIncludeContent <em>Include Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnyURILiteralImpl extends GrammarContentImpl implements AnyURILiteral
{
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
   * The cached value of the '{@link #getIncludeContent() <em>Include Content</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIncludeContent()
   * @generated
   * @ordered
   */
  protected EList<IncludeContent> includeContent;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AnyURILiteralImpl()
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
    return RelaxngPackage.Literals.ANY_URI_LITERAL;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.ANY_URI_LITERAL__INHERIT, oldInherit, newInherit);
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
        msgs = ((InternalEObject)inherit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.ANY_URI_LITERAL__INHERIT, null, msgs);
      if (newInherit != null)
        msgs = ((InternalEObject)newInherit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.ANY_URI_LITERAL__INHERIT, null, msgs);
      msgs = basicSetInherit(newInherit, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.ANY_URI_LITERAL__INHERIT, newInherit, newInherit));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<IncludeContent> getIncludeContent()
  {
    if (includeContent == null)
    {
      includeContent = new EObjectContainmentEList<IncludeContent>(IncludeContent.class, this, RelaxngPackage.ANY_URI_LITERAL__INCLUDE_CONTENT);
    }
    return includeContent;
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
      case RelaxngPackage.ANY_URI_LITERAL__INHERIT:
        return basicSetInherit(null, msgs);
      case RelaxngPackage.ANY_URI_LITERAL__INCLUDE_CONTENT:
        return ((InternalEList<?>)getIncludeContent()).basicRemove(otherEnd, msgs);
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
      case RelaxngPackage.ANY_URI_LITERAL__INHERIT:
        return getInherit();
      case RelaxngPackage.ANY_URI_LITERAL__INCLUDE_CONTENT:
        return getIncludeContent();
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
      case RelaxngPackage.ANY_URI_LITERAL__INHERIT:
        setInherit((Inherit)newValue);
        return;
      case RelaxngPackage.ANY_URI_LITERAL__INCLUDE_CONTENT:
        getIncludeContent().clear();
        getIncludeContent().addAll((Collection<? extends IncludeContent>)newValue);
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
      case RelaxngPackage.ANY_URI_LITERAL__INHERIT:
        setInherit((Inherit)null);
        return;
      case RelaxngPackage.ANY_URI_LITERAL__INCLUDE_CONTENT:
        getIncludeContent().clear();
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
      case RelaxngPackage.ANY_URI_LITERAL__INHERIT:
        return inherit != null;
      case RelaxngPackage.ANY_URI_LITERAL__INCLUDE_CONTENT:
        return includeContent != null && !includeContent.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //AnyURILiteralImpl
