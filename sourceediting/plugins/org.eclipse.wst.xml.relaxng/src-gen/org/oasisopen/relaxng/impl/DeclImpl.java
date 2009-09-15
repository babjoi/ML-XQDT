/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.oasisopen.relaxng.Decl;
import org.oasisopen.relaxng.RelaxngPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Decl</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.impl.DeclImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.DeclImpl#getUri <em>Uri</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.DeclImpl#getDatatypeId <em>Datatype Id</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.DeclImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeclImpl extends MinimalEObjectImpl.Container implements Decl
{
  /**
   * The default value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrefix()
   * @generated
   * @ordered
   */
  protected static final String PREFIX_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrefix()
   * @generated
   * @ordered
   */
  protected String prefix = PREFIX_EDEFAULT;

  /**
   * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUri()
   * @generated
   * @ordered
   */
  protected static final String URI_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUri()
   * @generated
   * @ordered
   */
  protected String uri = URI_EDEFAULT;

  /**
   * The default value of the '{@link #getDatatypeId() <em>Datatype Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDatatypeId()
   * @generated
   * @ordered
   */
  protected static final String DATATYPE_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDatatypeId() <em>Datatype Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDatatypeId()
   * @generated
   * @ordered
   */
  protected String datatypeId = DATATYPE_ID_EDEFAULT;

  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final String VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected String value = VALUE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DeclImpl()
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
    return RelaxngPackage.Literals.DECL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPrefix()
  {
    return prefix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrefix(String newPrefix)
  {
    String oldPrefix = prefix;
    prefix = newPrefix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.DECL__PREFIX, oldPrefix, prefix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getUri()
  {
    return uri;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUri(String newUri)
  {
    String oldUri = uri;
    uri = newUri;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.DECL__URI, oldUri, uri));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDatatypeId()
  {
    return datatypeId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDatatypeId(String newDatatypeId)
  {
    String oldDatatypeId = datatypeId;
    datatypeId = newDatatypeId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.DECL__DATATYPE_ID, oldDatatypeId, datatypeId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(String newValue)
  {
    String oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.DECL__VALUE, oldValue, value));
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
      case RelaxngPackage.DECL__PREFIX:
        return getPrefix();
      case RelaxngPackage.DECL__URI:
        return getUri();
      case RelaxngPackage.DECL__DATATYPE_ID:
        return getDatatypeId();
      case RelaxngPackage.DECL__VALUE:
        return getValue();
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
      case RelaxngPackage.DECL__PREFIX:
        setPrefix((String)newValue);
        return;
      case RelaxngPackage.DECL__URI:
        setUri((String)newValue);
        return;
      case RelaxngPackage.DECL__DATATYPE_ID:
        setDatatypeId((String)newValue);
        return;
      case RelaxngPackage.DECL__VALUE:
        setValue((String)newValue);
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
      case RelaxngPackage.DECL__PREFIX:
        setPrefix(PREFIX_EDEFAULT);
        return;
      case RelaxngPackage.DECL__URI:
        setUri(URI_EDEFAULT);
        return;
      case RelaxngPackage.DECL__DATATYPE_ID:
        setDatatypeId(DATATYPE_ID_EDEFAULT);
        return;
      case RelaxngPackage.DECL__VALUE:
        setValue(VALUE_EDEFAULT);
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
      case RelaxngPackage.DECL__PREFIX:
        return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
      case RelaxngPackage.DECL__URI:
        return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
      case RelaxngPackage.DECL__DATATYPE_ID:
        return DATATYPE_ID_EDEFAULT == null ? datatypeId != null : !DATATYPE_ID_EDEFAULT.equals(datatypeId);
      case RelaxngPackage.DECL__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
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
    result.append(" (prefix: ");
    result.append(prefix);
    result.append(", uri: ");
    result.append(uri);
    result.append(", datatypeId: ");
    result.append(datatypeId);
    result.append(", value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }

} //DeclImpl
