package com.redhat.demo;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativePigLatinResourceIT extends PigLatinResourceTest {

    // Execute the same tests but in native mode.
}