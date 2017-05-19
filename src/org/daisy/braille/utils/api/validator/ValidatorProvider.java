package org.daisy.braille.utils.api.validator;

import org.daisy.braille.utils.api.factory.FactoryProperties;
import org.daisy.braille.utils.api.factory.Provider;

/**
 * <p>
 * Provides an interface for a Validator service. The purpose of this
 * interface is to expose an implementation of Validator as a
 * service.
 * </p>
 *
 * <p>
 * To comply with this interface, an implementation must be thread safe and
 * address both the possibility that only a single instance is created and used
 * throughout and that new instances are created as desired.
 * </p>
 *
 * @author Joel Håkansson
 * @deprecated Use corresponding class in package org.daisy.dotify.api.validity in dotify.task-api instead
 */
@Deprecated
public interface ValidatorProvider extends Provider<FactoryProperties> {

	public Validator newValidator(String identifier);

}
