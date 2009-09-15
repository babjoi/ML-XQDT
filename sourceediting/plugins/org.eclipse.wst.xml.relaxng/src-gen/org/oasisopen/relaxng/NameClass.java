/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Name Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.NameClass#getExceptClassName <em>Except Class Name</em>}</li>
 *   <li>{@link org.oasisopen.relaxng.NameClass#getNc <em>Nc</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.oasisopen.relaxng.RelaxngPackage#getNameClass()
 * @model
 * @generated
 */
public interface NameClass extends ExceptNameClass
{
  /**
   * Returns the value of the '<em><b>Except Class Name</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Except Class Name</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Except Class Name</em>' containment reference.
   * @see #setExceptClassName(ExceptNameClass)
   * @see org.oasisopen.relaxng.RelaxngPackage#getNameClass_ExceptClassName()
   * @model containment="true"
   * @generated
   */
  ExceptNameClass getExceptClassName();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.NameClass#getExceptClassName <em>Except Class Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Except Class Name</em>' containment reference.
   * @see #getExceptClassName()
   * @generated
   */
  void setExceptClassName(ExceptNameClass value);

  /**
   * Returns the value of the '<em><b>Nc</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nc</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Nc</em>' containment reference.
   * @see #setNc(NameClass)
   * @see org.oasisopen.relaxng.RelaxngPackage#getNameClass_Nc()
   * @model containment="true"
   * @generated
   */
  NameClass getNc();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.NameClass#getNc <em>Nc</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Nc</em>' containment reference.
   * @see #getNc()
   * @generated
   */
  void setNc(NameClass value);

} // NameClass
