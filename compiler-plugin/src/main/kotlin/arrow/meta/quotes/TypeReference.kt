package arrow.meta.quotes

import arrow.meta.Meta
import arrow.meta.phases.ExtensionPhase
import org.jetbrains.kotlin.psi.KtAnnotation
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtTypeElement
import org.jetbrains.kotlin.psi.KtTypeReference

/**
 * A [KtTypeReference] [Quote] with a custom template destructuring [TypeReferenceScope].
 *
 * @param match designed to to feed in any kind of [KtTypeReference] predicate returning a [Boolean].
 * @param map map a function that maps over the resulting action from matching on the transformation at the PSI level.
 */
fun Meta.typeReference(
  match: KtTypeReference.() -> Boolean,
  map: TypeReferenceScope.(KtTypeReference) -> Transform<KtTypeReference>
): ExtensionPhase =
  quote(match, map) { TypeReferenceScope(it) }

/**
 * A template destructuring [Scope] for a [KtTypeReference]
 */
class TypeReferenceScope(
  override val value: KtTypeReference,
  val typeElement: Scope<KtTypeElement>? = Scope(value.typeElement),  // TODO KtTypeElement scope and quote template
  val `@annotations`: ScopedList<KtAnnotation> = ScopedList(value.annotations),
  val `@annotationEntries`: ScopedList<KtAnnotationEntry> = ScopedList(value.annotationEntries)
): Scope<KtTypeReference>(value)