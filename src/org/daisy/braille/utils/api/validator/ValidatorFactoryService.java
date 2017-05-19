package org.daisy.braille.utils.api.validator;

import java.util.Collection;

import org.daisy.braille.utils.api.factory.FactoryFilter;
import org.daisy.braille.utils.api.factory.FactoryProperties;

/**
 * <p>
 * Provides an interface for a ValidatorFactory service. The purpose of
 * this interface is to expose an implementation of a ValidatorFactory
 * as an OSGi service.
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
public interface ValidatorFactoryService {

	public Validator newValidator(String identifier);

	public Collection<FactoryProperties> list();

	public Collection<FactoryProperties> list(FactoryFilter filter);

}
