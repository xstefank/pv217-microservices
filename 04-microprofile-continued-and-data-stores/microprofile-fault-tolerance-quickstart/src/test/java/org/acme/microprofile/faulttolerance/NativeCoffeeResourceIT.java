package org.acme.microprofile.faulttolerance;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeCoffeeResourceIT extends CoffeeResourceTest {

    // Execute the same tests but in native mode.
}