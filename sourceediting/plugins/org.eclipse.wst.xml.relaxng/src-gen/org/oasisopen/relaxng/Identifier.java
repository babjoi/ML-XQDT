/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Identifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.Identifier#getKeyWord <em>Key Word</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.oasisopen.relaxng.RelaxngPackage#getIdentifier()
 * @model
 * @generated
 */
public interface Identifier extends IdentifierOrKeyWord
{
  /**
   * Returns the value of the '<em><b>Key Word</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Key Word</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Key Word</em>' containment reference.
   * @see #setKeyWord(KeyWord)
   * @see org.oasisopen.relaxng.RelaxngPackage#getIdentifier_KeyWord()
   * @model containment="true"
   * @generated
   */
  KeyWord getKeyWord();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.Identifier#getKeyWord <em>Key Word</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Key Word</em>' containment reference.
   * @see #getKeyWord()
   * @generated
   */
  void setKeyWord(KeyWord value);

} // Identifier
