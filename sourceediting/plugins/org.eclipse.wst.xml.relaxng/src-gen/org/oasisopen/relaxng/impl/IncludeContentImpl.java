/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.oasisopen.relaxng.GrammarContent;
import org.oasisopen.relaxng.IncludeContent;
import org.oasisopen.relaxng.RelaxngPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Include Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.impl.IncludeContentImpl#getIncludeGrammarContent <em>Include Grammar Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IncludeContentImpl extends MinimalEObjectImpl.Container implements IncludeContent
{
  /**
   * The cached value of the '{@link #getIncludeGrammarContent() <em>Include Grammar Content</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIncludeGrammarContent()
   * @generated
   * @ordered
   */
  protected EList<GrammarContent> includeGrammarContent;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IncludeContentImpl()
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
    return RelaxngPackage.Literals.INCLUDE_CONTENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GrammarContent> getIncludeGrammarContent()
  {
    if (includeGrammarContent == null)
    {
      includeGrammarContent = new EObjectContainmentEList<GrammarContent>(GrammarContent.class, this, RelaxngPackage.INCLUDE_CONTENT__INCLUDE_GRAMMAR_CONTENT);
    }
    return includeGrammarContent;
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
      case RelaxngPackage.INCLUDE_CONTENT__INCLUDE_GRAMMAR_CONTENT:
        return ((InternalEList<?>)getIncludeGrammarContent()).basicRemove(otherEnd, msgs);
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
      case RelaxngPackage.INCLUDE_CONTENT__INCLUDE_GRAMMAR_CONTENT:
        return getIncludeGrammarContent();
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
      case RelaxngPackage.INCLUDE_CONTENT__INCLUDE_GRAMMAR_CONTENT:
        getIncludeGrammarContent().clear();
        getIncludeGrammarContent().addAll((Collection<? extends GrammarContent>)newValue);
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
      case RelaxngPackage.INCLUDE_CONTENT__INCLUDE_GRAMMAR_CONTENT:
        getIncludeGrammarContent().clear();
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
      case RelaxngPackage.INCLUDE_CONTENT__INCLUDE_GRAMMAR_CONTENT:
        return includeGrammarContent != null && !includeGrammarContent.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //IncludeContentImpl
