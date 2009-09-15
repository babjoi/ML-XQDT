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
import org.oasisopen.relaxng.GrammarContent;
import org.oasisopen.relaxng.IncludeContent;
import org.oasisopen.relaxng.Inherit;
import org.oasisopen.relaxng.Literal;
import org.oasisopen.relaxng.NamespaceURILiteral;
import org.oasisopen.relaxng.RelaxngPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.impl.LiteralImpl#getGrammarContenGrammarContent <em>Grammar Conten Grammar Content</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.LiteralImpl#getInherit <em>Inherit</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.LiteralImpl#getIncludeContent <em>Include Content</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.impl.LiteralImpl#getLiteralSegment <em>Literal Segment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LiteralImpl extends DataTypeValueImpl implements Literal
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
   * The default value of the '{@link #getLiteralSegment() <em>Literal Segment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLiteralSegment()
   * @generated
   * @ordered
   */
  protected static final String LITERAL_SEGMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLiteralSegment() <em>Literal Segment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLiteralSegment()
   * @generated
   * @ordered
   */
  protected String literalSegment = LITERAL_SEGMENT_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LiteralImpl()
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
    return RelaxngPackage.Literals.LITERAL;
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
      grammarContenGrammarContent = new EObjectContainmentEList<GrammarContent>(GrammarContent.class, this, RelaxngPackage.LITERAL__GRAMMAR_CONTEN_GRAMMAR_CONTENT);
    }
    return grammarContenGrammarContent;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RelaxngPackage.LITERAL__INHERIT, oldInherit, newInherit);
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
        msgs = ((InternalEObject)inherit).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.LITERAL__INHERIT, null, msgs);
      if (newInherit != null)
        msgs = ((InternalEObject)newInherit).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RelaxngPackage.LITERAL__INHERIT, null, msgs);
      msgs = basicSetInherit(newInherit, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.LITERAL__INHERIT, newInherit, newInherit));
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
      includeContent = new EObjectContainmentEList<IncludeContent>(IncludeContent.class, this, RelaxngPackage.LITERAL__INCLUDE_CONTENT);
    }
    return includeContent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLiteralSegment()
  {
    return literalSegment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLiteralSegment(String newLiteralSegment)
  {
    String oldLiteralSegment = literalSegment;
    literalSegment = newLiteralSegment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, RelaxngPackage.LITERAL__LITERAL_SEGMENT, oldLiteralSegment, literalSegment));
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
      case RelaxngPackage.LITERAL__GRAMMAR_CONTEN_GRAMMAR_CONTENT:
        return ((InternalEList<?>)getGrammarContenGrammarContent()).basicRemove(otherEnd, msgs);
      case RelaxngPackage.LITERAL__INHERIT:
        return basicSetInherit(null, msgs);
      case RelaxngPackage.LITERAL__INCLUDE_CONTENT:
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
      case RelaxngPackage.LITERAL__GRAMMAR_CONTEN_GRAMMAR_CONTENT:
        return getGrammarContenGrammarContent();
      case RelaxngPackage.LITERAL__INHERIT:
        return getInherit();
      case RelaxngPackage.LITERAL__INCLUDE_CONTENT:
        return getIncludeContent();
      case RelaxngPackage.LITERAL__LITERAL_SEGMENT:
        return getLiteralSegment();
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
      case RelaxngPackage.LITERAL__GRAMMAR_CONTEN_GRAMMAR_CONTENT:
        getGrammarContenGrammarContent().clear();
        getGrammarContenGrammarContent().addAll((Collection<? extends GrammarContent>)newValue);
        return;
      case RelaxngPackage.LITERAL__INHERIT:
        setInherit((Inherit)newValue);
        return;
      case RelaxngPackage.LITERAL__INCLUDE_CONTENT:
        getIncludeContent().clear();
        getIncludeContent().addAll((Collection<? extends IncludeContent>)newValue);
        return;
      case RelaxngPackage.LITERAL__LITERAL_SEGMENT:
        setLiteralSegment((String)newValue);
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
      case RelaxngPackage.LITERAL__GRAMMAR_CONTEN_GRAMMAR_CONTENT:
        getGrammarContenGrammarContent().clear();
        return;
      case RelaxngPackage.LITERAL__INHERIT:
        setInherit((Inherit)null);
        return;
      case RelaxngPackage.LITERAL__INCLUDE_CONTENT:
        getIncludeContent().clear();
        return;
      case RelaxngPackage.LITERAL__LITERAL_SEGMENT:
        setLiteralSegment(LITERAL_SEGMENT_EDEFAULT);
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
      case RelaxngPackage.LITERAL__GRAMMAR_CONTEN_GRAMMAR_CONTENT:
        return grammarContenGrammarContent != null && !grammarContenGrammarContent.isEmpty();
      case RelaxngPackage.LITERAL__INHERIT:
        return inherit != null;
      case RelaxngPackage.LITERAL__INCLUDE_CONTENT:
        return includeContent != null && !includeContent.isEmpty();
      case RelaxngPackage.LITERAL__LITERAL_SEGMENT:
        return LITERAL_SEGMENT_EDEFAULT == null ? literalSegment != null : !LITERAL_SEGMENT_EDEFAULT.equals(literalSegment);
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
    if (baseClass == GrammarContent.class)
    {
      switch (derivedFeatureID)
      {
        case RelaxngPackage.LITERAL__GRAMMAR_CONTEN_GRAMMAR_CONTENT: return RelaxngPackage.GRAMMAR_CONTENT__GRAMMAR_CONTEN_GRAMMAR_CONTENT;
        default: return -1;
      }
    }
    if (baseClass == AnyURILiteral.class)
    {
      switch (derivedFeatureID)
      {
        case RelaxngPackage.LITERAL__INHERIT: return RelaxngPackage.ANY_URI_LITERAL__INHERIT;
        case RelaxngPackage.LITERAL__INCLUDE_CONTENT: return RelaxngPackage.ANY_URI_LITERAL__INCLUDE_CONTENT;
        default: return -1;
      }
    }
    if (baseClass == NamespaceURILiteral.class)
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
    if (baseClass == GrammarContent.class)
    {
      switch (baseFeatureID)
      {
        case RelaxngPackage.GRAMMAR_CONTENT__GRAMMAR_CONTEN_GRAMMAR_CONTENT: return RelaxngPackage.LITERAL__GRAMMAR_CONTEN_GRAMMAR_CONTENT;
        default: return -1;
      }
    }
    if (baseClass == AnyURILiteral.class)
    {
      switch (baseFeatureID)
      {
        case RelaxngPackage.ANY_URI_LITERAL__INHERIT: return RelaxngPackage.LITERAL__INHERIT;
        case RelaxngPackage.ANY_URI_LITERAL__INCLUDE_CONTENT: return RelaxngPackage.LITERAL__INCLUDE_CONTENT;
        default: return -1;
      }
    }
    if (baseClass == NamespaceURILiteral.class)
    {
      switch (baseFeatureID)
      {
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
    result.append(" (literalSegment: ");
    result.append(literalSegment);
    result.append(')');
    return result.toString();
  }

} //LiteralImpl
