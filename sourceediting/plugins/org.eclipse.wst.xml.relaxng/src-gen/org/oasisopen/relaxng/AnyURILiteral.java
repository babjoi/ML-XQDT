/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Any URI Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.AnyURILiteral#getInherit <em>Inherit</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.AnyURILiteral#getIncludeContent <em>Include Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.oasisopen.relaxng.RelaxngPackage#getAnyURILiteral()
 * @model
 * @generated
 */
public interface AnyURILiteral extends GrammarContent
{
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
   * @see org.oasisopen.relaxng.RelaxngPackage#getAnyURILiteral_Inherit()
   * @model containment="true"
   * @generated
   */
  Inherit getInherit();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.AnyURILiteral#getInherit <em>Inherit</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Inherit</em>' containment reference.
   * @see #getInherit()
   * @generated
   */
  void setInherit(Inherit value);

  /**
   * Returns the value of the '<em><b>Include Content</b></em>' containment reference list.
   * The list contents are of type {@link org.oasisopen.relaxng.IncludeContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Include Content</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Include Content</em>' containment reference list.
   * @see org.oasisopen.relaxng.RelaxngPackage#getAnyURILiteral_IncludeContent()
   * @model containment="true"
   * @generated
   */
  EList<IncludeContent> getIncludeContent();

} // AnyURILiteral
