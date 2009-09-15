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
import org.oasisopen.relaxng.RelaxngPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Grammar Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.impl.GrammarContentImpl#getGrammarContenGrammarContent <em>Grammar Conten Grammar Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GrammarContentImpl extends MinimalEObjectImpl.Container implements GrammarContent
{
  /**
   * The cached value of the '{@link #getGrammarContenGrammarContent() <em>Grammar Conten Grammar Content</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGrammarContenGrammarContent()
   * @generated
   * @ordered
   */
  protected EList<GrammarContent> grammarContenGrammarContent;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GrammarContentImpl()
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
    return RelaxngPackage.Literals.GRAMMAR_CONTENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GrammarContent> getGrammarContenGrammarContent()
  {
    if (grammarContenGrammarContent == null)
    {
      grammarContenGrammarContent = new EObjectContainmentEList<GrammarContent>(GrammarContent.class, this, RelaxngPackage.GRAMMAR_CONTENT__GRAMMAR_CONTEN_GRAMMAR_CONTENT);
    }
    return grammarContenGrammarContent;
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
      case RelaxngPackage.GRAMMAR_CONTENT__GRAMMAR_CONTEN_GRAMMAR_CONTENT:
        return ((InternalEList<?>)getGrammarContenGrammarContent()).basicRemove(otherEnd, msgs);
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
      case RelaxngPackage.GRAMMAR_CONTENT__GRAMMAR_CONTEN_GRAMMAR_CONTENT:
        return getGrammarContenGrammarContent();
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
      case RelaxngPackage.GRAMMAR_CONTENT__GRAMMAR_CONTEN_GRAMMAR_CONTENT:
        getGrammarContenGrammarContent().clear();
        getGrammarContenGrammarContent().addAll((Collection<? extends GrammarContent>)newValue);
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
      case RelaxngPackage.GRAMMAR_CONTENT__GRAMMAR_CONTEN_GRAMMAR_CONTENT:
        getGrammarContenGrammarContent().clear();
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
      case RelaxngPackage.GRAMMAR_CONTENT__GRAMMAR_CONTEN_GRAMMAR_CONTENT:
        return grammarContenGrammarContent != null && !grammarContenGrammarContent.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //GrammarContentImpl
