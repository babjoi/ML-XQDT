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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.oasisopen.relaxng.Decl;
import org.oasisopen.relaxng.GrammarContent;
import org.oasisopen.relaxng.Pattern;
import org.oasisopen.relaxng.RelaxngPackage;
import org.oasisopen.relaxng.TopLevel;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Top Level</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.impl.TopLevelImpl#getDecls <em>Decls</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.TopLevelImpl#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.TopLevelImpl#getGrammarContent <em>Grammar Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TopLevelImpl extends MinimalEObjectImpl.Container implements TopLevel
{
  /**
   * The cached value of the '{@link #getDecls() <em>Decls</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDecls()
   * @generated
   * @ordered
   */
  protected EList<Decl> decls;

  /**
   * The cached value of the '{@link #getPattern() <em>Pattern</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPattern()
   * @generated
   * @ordered
   */
  protected Pattern pattern;

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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TopLevelImpl()
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
    return RelaxngPackage.Literals.TOP_LEVEL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Decl> getDecls()
  {
    if (decls == null)
    {
      decls = new EObjectContainmentEList<Decl>(Decl.class, this, RelaxngPackage.TOP_LEVEL__DECLS);
    }
    return decls;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Pattern getPattern()
  {
    return pattern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPattern(Pattern newPattern, NotificationChain msgs)
  {
    Pattern oldPattern = pattern;
    pattern = newPattern;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.TOP_LEVEL__PATTERN, oldPattern, newPattern);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPattern(Pattern newPattern)
  {
    if (newPattern != pattern)
    {
      NotificationChain msgs = null;
      if (pattern != null)
        msgs = ((InternalEObject)pattern).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.TOP_LEVEL__PATTERN, null, msgs);
      if (newPattern != null)
        msgs = ((InternalEObject)newPattern).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.TOP_LEVEL__PATTERN, null, msgs);
      msgs = basicSetPattern(newPattern, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.TOP_LEVEL__PATTERN, newPattern, newPattern));
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
      grammarContent = new EObjectContainmentEList<GrammarContent>(GrammarContent.class, this, RelaxngPackage.TOP_LEVEL__GRAMMAR_CONTENT);
    }
    return grammarContent;
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
      case RelaxngPackage.TOP_LEVEL__DECLS:
        return ((InternalEList<?>)getDecls()).basicRemove(otherEnd, msgs);
      case RelaxngPackage.TOP_LEVEL__PATTERN:
        return basicSetPattern(null, msgs);
      case RelaxngPackage.TOP_LEVEL__GRAMMAR_CONTENT:
        return ((InternalEList<?>)getGrammarContent()).basicRemove(otherEnd, msgs);
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
      case RelaxngPackage.TOP_LEVEL__DECLS:
        return getDecls();
      case RelaxngPackage.TOP_LEVEL__PATTERN:
        return getPattern();
      case RelaxngPackage.TOP_LEVEL__GRAMMAR_CONTENT:
        return getGrammarContent();
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
      case RelaxngPackage.TOP_LEVEL__DECLS:
        getDecls().clear();
        getDecls().addAll((Collection<? extends Decl>)newValue);
        return;
      case RelaxngPackage.TOP_LEVEL__PATTERN:
        setPattern((Pattern)newValue);
        return;
      case RelaxngPackage.TOP_LEVEL__GRAMMAR_CONTENT:
        getGrammarContent().clear();
        getGrammarContent().addAll((Collection<? extends GrammarContent>)newValue);
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
      case RelaxngPackage.TOP_LEVEL__DECLS:
        getDecls().clear();
        return;
      case RelaxngPackage.TOP_LEVEL__PATTERN:
        setPattern((Pattern)null);
        return;
      case RelaxngPackage.TOP_LEVEL__GRAMMAR_CONTENT:
        getGrammarContent().clear();
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
      case RelaxngPackage.TOP_LEVEL__DECLS:
        return decls != null && !decls.isEmpty();
      case RelaxngPackage.TOP_LEVEL__PATTERN:
        return pattern != null;
      case RelaxngPackage.TOP_LEVEL__GRAMMAR_CONTENT:
        return grammarContent != null && !grammarContent.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //TopLevelImpl
