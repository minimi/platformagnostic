package com.github.minimi.platformagnostic.inject

/**
 * Identifies injectable constructors, methods, and fields. May apply to static
 * as well as instance members. An injectable member may have any access
 * modifier (private, package-private, protected, public). Constructors are
 * injected first, followed by fields, and then methods. Fields and methods
 * in superclasses are injected before those in subclasses. Ordering of
 * injection among fields and among methods in the same class is not specified.
 *
 * Injectable constructors are annotated with @[Inject] and accept
 * zero or more dependencies as arguments. @[Inject] can apply to at most
 * one constructor per class.
 *
 *
 * <tt><blockquote style="padding-left: 2em; text-indent: -2em;">@Inject
 *       <i>ConstructorModifiers<sub>opt</sub></i>
 *       <i>SimpleTypeName</i>(<i>FormalParameterList<sub>opt</sub></i>)
 *       <i>Throws<sub>opt</sub></i>
 *       <i>ConstructorBody</i></blockquote></tt>
 *
 *
 * @[Inject] is optional for public, no-argument constructors when no
 * other constructors are present. This enables injectors to invoke default
 * constructors.
 *
 *
 * <tt><blockquote style="padding-left: 2em; text-indent: -2em;">
 *       {@literal @}Inject<sub><i>opt</i></sub>
 *       <i>Annotations<sub>opt</sub></i>
 *       public
 *       <i>SimpleTypeName</i>()
 *       <i>Throws<sub>opt</sub></i>
 *       <i>ConstructorBody</i></blockquote></tt>
 *
 *
 * Injectable fields:
 * <ul>
 *   <li>are annotated with {@code @Inject}.
 *   <li>are not final.
 *   <li>may have any otherwise valid name.</li></ul>
 *
 * <tt><blockquote style="padding-left: 2em; text-indent: -2em;">@Inject
 *       <i>FieldModifiers<sub>opt</sub></i>
 *       <i>Type</i>
 *       <i>VariableDeclarators</i>;</blockquote></tt>
 *
 * Injectable methods:
 * <ul>
 *   <li>are annotated with {@code @Inject}.</li>
 *   <li>are not abstract.</li>
 *   <li>do not declare type parameters of their own.</li>
 *   <li>may return a result</li>
 *   <li>may have any otherwise valid name.</li>
 *   <li>accept zero or more dependencies as arguments.</li></ul>
 *
 * <tt><blockquote style="padding-left: 2em; text-indent: -2em;">@Inject
 *       <i>MethodModifiers<sub>opt</sub></i>
 *       <i>ResultType</i>
 *       <i>Identifier</i>(<i>FormalParameterList<sub>opt</sub></i>)
 *       <i>Throws<sub>opt</sub></i>
 *       <i>MethodBody</i></blockquote></tt>
 *
 *
 * The injector ignores the result of an injected method, but
 * non-{@code void} return types are allowed to support use of the method in
 * other contexts (builder-style method chaining, for example).
 *
 * Examples:
 *
 * <pre>
 *   public class Car {
 *     // Injectable constructor
 *     &#064;Inject public Car(Engine engine) { ... }
 *
 *     // Injectable field
 *     &#064;Inject private Provider&lt;Seat> seatProvider;
 *
 *     // Injectable package-private method
 *     &#064;Inject void install(Windshield windshield, Trunk trunk) { ... }
 *   }</pre>
 *
 * A method annotated with {@code @Inject} that overrides another method
 * annotated with {@code @Inject} will only be injected once per injection
 * request per instance. A method with <i>no</i> {@code @Inject} annotation
 * that overrides a method annotated with {@code @Inject} will not be
 * injected.
 *
 * Injection of members annotated with {@code @Inject} is required. While an
 * injectable member may use any accessibility modifier (including
 * <tt>private</tt>), platform or injector limitations (like security
 * restrictions or lack of reflection support) might preclude injection
 * of non-public members.
 *
 * <h3>Qualifiers</h3>
 *
 * A {@linkplain Qualifier qualifier} may annotate an injectable field
 * or parameter and, combined with the type, identify the implementation to
 * inject. Qualifiers are optional, and when used with {@code @Inject} in
 * injector-independent classes, no more than one qualifier should annotate a
 * single field or parameter. The qualifiers are bold in the following example:
 *
 * <pre>
 *   public class Car {
 *     &#064;Inject private <b>@Leather</b> Provider&lt;Seat> seatProvider;
 *
 *     &#064;Inject void install(<b>@Tinted</b> Windshield windshield,
 *         <b>@Big</b> Trunk trunk) { ... }
 *   }</pre>
 *
 * If one injectable method overrides another, the overriding method's
 * parameters do not automatically inherit qualifiers from the overridden
 * method's parameters.
 *
 * <h3>Injectable Values</h3>
 *
 * For a given type T and optional qualifier, an injector must be able to
 * inject a user-specified class that:
 *
 * <ol type="a">
 *   <li>is assignment compatible with T and</li>
 *   <li>has an injectable constructor.</li>
 * </ol>
 *
 * For example, the user might use external configuration to pick an
 * implementation of T. Beyond that, which values are injected depend upon the
 * injector implementation and its configuration.
 *
 * <h3>Circular Dependencies</h3>
 *
 * Detecting and resolving circular dependencies is left as an exercise for
 * the injector implementation. Circular dependencies between two constructors
 * is an obvious problem, but you can also have a circular dependency between
 * injectable fields or methods:
 *
 * <pre>
 *   class A {
 *     &#064;Inject B b;
 *   }
 *   class B {
 *     &#064;Inject A a;
 *   }</pre>
 *
 * When constructing an instance of {@code A}, a naive injector
 * implementation might go into an infinite loop constructing an instance of
 * {@code B} to set on {@code A}, a second instance of {@code A} to set on
 * {@code B}, a second instance of {@code B} to set on the second instance of
 * {@code A}, and so on.
 *
 * A conservative injector might detect the circular dependency at build
 * time and generate an error, at which point the programmer could break the
 * circular dependency by injecting {@link Provider Provider&lt;A>} or {@code
 * Provider<B>} instead of {@code A} or {@code B} respectively. Calling {@link
 * Provider#get() get()} on the provider directly from the constructor or
 * method it was injected into defeats the provider's ability to break up
 * circular dependencies. In the case of method or field injection, scoping
 * one of the dependencies (using {@linkplain Singleton singleton scope}, for
 * example) may also enable a valid circular relationship.
 *
 * @see Qualifier @Qualifier
 * @see Provider
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CONSTRUCTOR, AnnotationTarget.PROPERTY, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
expect annotation class Inject()
