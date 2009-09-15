/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Identifier Or Key Word</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.IdentifierOrKeyWord#getParmValue <em>Parm Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.oasisopen.relaxng.RelaxngPackage#getIdentifierOrKeyWord()
 * @model
 * @generated
 */
public interface IdentifierOrKeyWord extends Param, Name, Inherit
{
  /**
   * Returns the value of the '<em><b>Parm Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parm Value</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parm Value</em>' containment reference.
   * @see #setParmValue(Literal)
   * @see org.oasisopen.relaxng.RelaxngPackage#getIdentifierOrKeyWord_ParmValue()
   * @model containment="true"
   * @generated
   */
  Literal getParmValue();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.IdentifierOrKeyWord#getParmValue <em>Parm Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parm Value</em>' containment reference.
   * @see #getParmValue()
   * @generated
   */
  void setParmValue(Literal value);

} // IdentifierOrKeyWord
