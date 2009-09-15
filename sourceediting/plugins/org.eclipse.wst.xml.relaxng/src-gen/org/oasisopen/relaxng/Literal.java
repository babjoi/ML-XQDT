/**
 * <copyright>
 * </copyright>
 *
 */
package org.oasisopen.relaxng;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.Literal#getLiteralSegment <em>Literal Segment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.oasisopen.relaxng.RelaxngPackage#getLiteral()
 * @model
 * @generated
 */
public interface Literal extends DataTypeValue, AnyURILiteral, NamespaceURILiteral
{
  /**
   * Returns the value of the '<em><b>Literal Segment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Literal Segment</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Literal Segment</em>' attribute.
   * @see #setLiteralSegment(String)
   * @see org.oasisopen.relaxng.RelaxngPackage#getLiteral_LiteralSegment()
   * @model
   * @generated
   */
  String getLiteralSegment();

  /**
   * Sets the value of the '{@link org.oasisopen.relaxng.Literal#getLiteralSegment <em>Literal Segment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Literal Segment</em>' attribute.
   * @see #getLiteralSegment()
   * @generated
   */
  void setLiteralSegment(String value);

} // Literal
