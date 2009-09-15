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
 * A representation of the model object '<em><b>Include Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.IncludeContent#getIncludeGrammarContent <em>Include Grammar Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.oasisopen.relaxng.RelaxngPackage#getIncludeContent()
 * @model
 * @generated
 */
public interface IncludeContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Include Grammar Content</b></em>' containment reference list.
   * The list contents are of type {@link org.oasisopen.relaxng.GrammarContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Include Grammar Content</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Include Grammar Content</em>' containment reference list.
   * @see org.oasisopen.relaxng.RelaxngPackage#getIncludeContent_IncludeGrammarContent()
   * @model containment="true"
   * @generated
   */
  EList<GrammarContent> getIncludeGrammarContent();

} // IncludeContent
