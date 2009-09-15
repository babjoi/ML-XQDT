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
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.Element#getName <em>Name</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.Element#getPattern <em>Pattern</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.Element#getCardinality <em>Cardinality</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.Element#getContinue <em>Continue</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.oasisopen.relaxng.RelaxngPackage#getElement()
 * @model
 * @generated
 */
public interface Element extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' containment reference.
   * @see #setName(NameClass)
   * @see org.oasisopen.relaxng.RelaxngPackage#getElement_Name()
   * @model containment="true"
   * @generated
   */
  NameClass getName();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.Element#getName <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' containment reference.
   * @see #getName()
   * @generated
   */
  void setName(NameClass value);

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
   * @see org.oasisopen.relaxng.RelaxngPackage#getElement_Pattern()
   * @model containment="true"
   * @generated
   */
  EList<Pattern> getPattern();

  /**
   * Returns the value of the '<em><b>Cardinality</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cardinality</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cardinality</em>' attribute.
   * @see #setCardinality(String)
   * @see org.oasisopen.relaxng.RelaxngPackage#getElement_Cardinality()
   * @model
   * @generated
   */
  String getCardinality();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.Element#getCardinality <em>Cardinality</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cardinality</em>' attribute.
   * @see #getCardinality()
   * @generated
   */
  void setCardinality(String value);

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
   * @see org.oasisopen.relaxng.RelaxngPackage#getElement_Continue()
   * @model
   * @generated
   */
  String getContinue();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.Element#getContinue <em>Continue</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Continue</em>' attribute.
   * @see #getContinue()
   * @generated
   */
  void setContinue(String value);

} // Element
