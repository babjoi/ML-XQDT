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

import org.oasisopen.relaxng.Identifier;
import org.oasisopen.relaxng.KeyWord;
import org.oasisopen.relaxng.RelaxngPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Identifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.impl.IdentifierImpl#getKeyWord <em>Key Word</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IdentifierImpl extends IdentifierOrKeyWordImpl implements Identifier
{
  /**
   * The cached value of the '{@link #getKeyWord() <em>Key Word</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKeyWord()
   * @generated
   * @ordered
   */
  protected KeyWord keyWord;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IdentifierImpl()
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
    return RelaxngPackage.Literals.IDENTIFIER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KeyWord getKeyWord()
  {
    return keyWord;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetKeyWord(KeyWord newKeyWord, NotificationChain msgs)
  {
    KeyWord oldKeyWord = keyWord;
    keyWord = newKeyWord;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.IDENTIFIER__KEY_WORD, oldKeyWord, newKeyWord);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKeyWord(KeyWord newKeyWord)
  {
    if (newKeyWord != keyWord)
    {
      NotificationChain msgs = null;
      if (keyWord != null)
        msgs = ((InternalEObject)keyWord).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.IDENTIFIER__KEY_WORD, null, msgs);
      if (newKeyWord != null)
        msgs = ((InternalEObject)newKeyWord).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.IDENTIFIER__KEY_WORD, null, msgs);
      msgs = basicSetKeyWord(newKeyWord, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.IDENTIFIER__KEY_WORD, newKeyWord, newKeyWord));
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
      case RelaxngPackage.IDENTIFIER__KEY_WORD:
        return basicSetKeyWord(null, msgs);
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
      case RelaxngPackage.IDENTIFIER__KEY_WORD:
        return getKeyWord();
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
      case RelaxngPackage.IDENTIFIER__KEY_WORD:
        setKeyWord((KeyWord)newValue);
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
      case RelaxngPackage.IDENTIFIER__KEY_WORD:
        setKeyWord((KeyWord)null);
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
      case RelaxngPackage.IDENTIFIER__KEY_WORD:
        return keyWord != null;
    }
    return super.eIsSet(featureID);
  }

} //IdentifierImpl
