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
 * A representation of the model object '<em><b>Grammar Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.oasisopen.relaxng.GrammarContent#getGrammarContenGrammarContent <em>Grammar Conten Grammar Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.oasisopen.relaxng.RelaxngPackage#getGrammarContent()
 * @model
 * @generated
 */
public interface GrammarContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Grammar Conten Grammar Content</b></em>' containment reference list.
   * The list contents are of type {@link org.oasisopen.relaxng.GrammarContent}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Grammar Conten Grammar Content</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Grammar Conten Grammar Content</em>' containment reference list.
   * @see org.oasisopen.relaxng.RelaxngPackage#getGrammarContent_GrammarContenGrammarContent()
   * @model containment="true"
   * @generated
   */
  EList<GrammarContent> getGrammarContenGrammarContent();

} // GrammarContent
