[![Build Status](https://travis-ci.org/brailleapps/braille-utils.api.svg?branch=master)](https://travis-ci.org/brailleapps/braille-utils.api)
[![Type](https://img.shields.io/badge/type-api-blue.svg)](https://github.com/brailleapps/wiki/wiki/Types)
[![License: LGPL v2.1](https://img.shields.io/badge/License-LGPL%20v2%2E1%20%28or%20later%29-blue.svg)](https://www.gnu.org/licenses/lgpl-2.1)

Note: This project has been merged into dotify.api (https://github.com/brailleapps/dotify.api) and
will not be updated anymore. Please use dotify.api instead.

## braille-utils.api ##
This contains the Braille Utils API.

## Using ##
To implement the API, download the [latest release](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.daisy.braille%22%20%20a%3A%22braille-utils.api%22) from maven central.

To use the API, you also need to download at least one implementation (for example braille-utils.impl).

## Building ##
Build with `gradlew build` (Windows) or `./gradlew build` (Mac/Linux)

## Testing ##
Tests are run with `gradlew test` (Windows) or `./gradlew test` (Mac/Linux)

## Requirements & Compatibility ##
- Requires Java 8
- Implementations can be provided with SPI and/or OSGi

## Javadoc ##
Javadoc for the latest Braille Utils API development is available [here](http://brailleapps.github.io/braille-utils.api/latest/javadoc/).

## More information ##
See the [common wiki](https://github.com/brailleapps/wiki/wiki) for more information.
