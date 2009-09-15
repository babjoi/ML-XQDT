/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.Pattern#getVal <em>Val</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.Pattern#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.Pattern#getValue <em>Value</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.Pattern#getParam <em>Param</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.Pattern#getExceptPattern <em>Except Pattern</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.Pattern#getUri <em>Uri</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.Pattern#getInherit <em>Inherit</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.Pattern#getGrammarContent <em>Grammar Content</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.Pattern#getGroup <em>Group</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.Pattern#getContinue <em>Continue</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.oasisopen.relaxng.RelaxngPackage#getPattern()
 * @model
 * @generated
 */
public interface Pattern extends ExceptPattern
{
  /**
   * Returns the value of the '<em><b>Val</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Val</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Val</em>' containment reference.
   * @see #setVal(EObject)
   * @see org.oasisopen.relaxng.RelaxngPackage#getPattern_Val()
   * @model containment="true"
   * @generated
   */
  EObject getVal();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.Pattern#getVal <em>Val</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Val</em>' containment reference.
   * @see #getVal()
   * @generated
   */
  void setVal(EObject value);

  /**
   * Returns the value of the '<em><b>Pattern</b></em>' containment reference list.
   * The list contents are of type {@link org.oasisopen.relaxng.Pattern}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pattern</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pattern</em>' containment reference list.
   * @see org.oasisopen.relaxng.RelaxngPackage#getPattern_Pattern()
   * @model containment="true"
   * @generated
   */
  EList<Pattern> getPattern();

  /**
   * Returns the value of the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference.
   * @see #setValue(DataTypeValue)
   * @see org.oasisopen.relaxng.RelaxngPackage#getPattern_Value()
   * @model containment="true"
   * @generated
   */
  DataTypeValue getValue();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.Pattern#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(DataTypeValue value);

  /**
   * Returns the value of the '<em><b>Param</b></em>' containment reference list.
   * The list contents are of type {@link org.oasisopen.relaxng.Param}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Param</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Param</em>' containment reference list.
   * @see org.oasisopen.relaxng.RelaxngPackage#getPattern_Param()
   * @model containment="true"
   * @generated
   */
  EList<Param> getParam();

  /**
   * Returns the value of the '<em><b>Except Pattern</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Except Pattern</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Except Pattern</em>' containment reference.
   * @see #setExceptPattern(ExceptPattern)
   * @see org.oasisopen.relaxng.RelaxngPackage#getPattern_ExceptPattern()
   * @model containment="true"
   * @generated
   */
  ExceptPattern getExceptPattern();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.Pattern#getExceptPattern <em>Except Pattern</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Except Pattern</em>' containment reference.
   * @see #getExceptPattern()
   * @generated
   */
  void setExceptPattern(ExceptPattern value);

  /**
   * Returns the value of the '<em><b>Uri</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Uri</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Uri</em>' containment reference.
   * @see #setUri(AnyURILiteral)
   * @see org.oasisopen.relaxng.RelaxngPackage#getPattern_Uri()
   * @model containment="true"
   * @generated
   */
  AnyURILiteral getUri();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.Pattern#getUri <em>Uri</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Uri</em>' containment reference.
   * @see #getUri()
   * @generated
   */
  void setUri(AnyURILiteral value);

  /**
   * Returns the value of the '<em><b>Inherit</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Inherit</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Inherit</em>' containment reference.
   * @see #setInherit(Inherit)
   * @see org.oasisopen.relaxng.RelaxngPackage#getPattern_Inherit()
   * @model containment="true"
   * @generated
   */
  Inherit getInherit();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.Pattern#getInherit <em>Inherit</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Inherit</em>' containment reference.
   * @see #getInherit()
   * @generated
   */
  void setInherit(Inherit value);

  /**
   * Returns the value of the '<em><b>Grammar Content</b></em>' containment reference list.
   * The list contents are of type {@link org.oasisopen.relaxng.GrammarContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Grammar Content</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Grammar Content</em>' containment reference list.
   * @see org.oasisopen.relaxng.RelaxngPackage#getPattern_GrammarContent()
   * @model containment="true"
   * @generated
   */
  EList<GrammarContent> getGrammarContent();

  /**
   * Returns the value of the '<em><b>Group</b></em>' containment reference list.
   * The list contents are of type {@link org.oasisopen.relaxng.Pattern}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group</em>' containment reference list.
   * @see org.oasisopen.relaxng.RelaxngPackage#getPattern_Group()
   * @model containment="true"
   * @generated
   */
  EList<Pattern> getGroup();

  /**
   * Returns the value of the '<em><b>Continue</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Continue</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Continue</em>' attribute.
   * @see #setContinue(String)
   * @see org.oasisopen.relaxng.RelaxngPackage#getPattern_Continue()
   * @model
   * @generated
   */
  String getContinue();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.Pattern#getContinue <em>Continue</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Continue</em>' attribute.
   * @see #getContinue()
   * @generated
   */
  void setContinue(String value);

} // Pattern
